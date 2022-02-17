package cn.com.pro.provider.rocket;

import cn.com.pro.common.id.IdGenerate;
import cn.com.pro.common.redis.RedisManager;
import cn.com.pro.common.rocketmq.MessageProducer;
import cn.com.pro.common.rocketmq.dto.MessageProduceDTO;
import cn.com.pro.provider.consts.RedisKeyConst;
import cn.com.pro.provider.db.model.FundNodeConfig;
import cn.com.pro.provider.db.model.FundNodeRoute;
import cn.com.pro.provider.db.model.MessageConsume;
import cn.com.pro.provider.db.service.IFundNodeConfigService;
import cn.com.pro.provider.db.service.IFundNodeRouteService;
import cn.com.pro.provider.db.service.IMessageConsumeService;
import cn.com.pro.provider.enums.ExceptionTypeEnum;
import cn.com.pro.provider.exception.BizException;
import cn.com.pro.provider.rocket.model.ConsumeResult;
import cn.com.pro.provider.rocket.model.MQPreBusinessProcessResult;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author zhoujianbo
 * @Title: 消息业务处理，可控每秒请求量
 * @Description:
 * @date Created in 2022/02/17 16:02
 **/
@Slf4j
public abstract class AbstractMessageListener implements MessageListenerConcurrently {

    @Autowired
    private IdGenerate idGenerate;

    @Autowired
    private RedisManager redisManager;

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private IMessageConsumeService messageConsumeService;

    @Autowired
    private IFundNodeConfigService fundNodeConfigService;

    @Autowired
    private IFundNodeRouteService fundNodeRouteService;

    /**
     * 消费者组名
     * @return
     */
    protected abstract String getConsumeGroup();

    /**
     * 业务逻辑处理
     * @param messageExt
     * @return ConsumeResult
     * @throws Exception
     */
    protected abstract ConsumeResult businessProcess(MessageExt messageExt) throws Exception;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messageExtList, ConsumeConcurrentlyContext context) {

        for (MessageExt exps : messageExtList) {

            log.info("消费开始, topic = {}, tag = {}, messageKey = {}, messageId = {}", exps.getTopic(), exps.getTags(), exps.getKeys(), exps.getMsgId());
            Thread.currentThread().setName(idGenerate.genSerialNo(exps.getTags()));

            String lockKey = RedisKeyConst.MESSAGE_CONSUME_LOCK + ":" + getConsumeGroup() + ":" + exps.getKeys();
            boolean lock = redisManager.lock(lockKey,"1", 60 * 60);
            if (!lock) {
                log.info("消息正在被处理, 本次不处理, messageKey = {}", exps.getKeys());
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }

            try {

                JSONObject jsonObject = JSONObject.parseObject(new String(exps.getBody()));
                String fundId = jsonObject.getString("fundId");

                FundNodeConfig fundNodeConfig = getMessageNodeConfig(fundId, exps.getTags());
                MessageConsume messageConsume = messageConsumeService.queryByMessageKeyAndConsumeGroup(exps.getKeys(), getConsumeGroup());

                //消息处理前进行并发及重复消费校验
                MQPreBusinessProcessResult preProcessResult = messageConsumeCheck(fundNodeConfig, exps, messageConsume);
                if (!preProcessResult.isSuccess()) {
                    return preProcessResult.getConsumeConcurrentlyStatus();
                }

                ConsumeResult consumeResult = getConsumeResult(exps);
                return afterBusinessCompletion(fundNodeConfig, consumeResult, messageConsume, exps);
            } catch (Exception e) {
                log.error("消息处理异常", e);
            } finally {
                redisManager.unlock(lockKey, "1");

            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    private ConsumeConcurrentlyStatus afterBusinessCompletion(FundNodeConfig fundNodeConfig, ConsumeResult consumeResult, MessageConsume messageConsume, MessageExt messageExt) {

        boolean isRepeatAllow = fundNodeConfig.getFailRetryCount() != 0;
        try {

            Integer repeatCount = saveOrUpdateConsumeMessage(isRepeatAllow, messageConsume, consumeResult, messageExt);
            Integer businessFinalStatus = consumeResult.getBusinessFinalStatus();
            if (consumeResult.getBusinessFinalStatus() != null) {
                log.info("消息处理已有终态，生产下一节点消息, messageKey = {}", messageExt.getKeys());
                produceNextNodes(messageExt, businessFinalStatus);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }

            if(!isRepeatAllow) {
                log.info("业务不允许重复消费, messageKey = {}", messageExt.getKeys());
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }

            Integer retryCount = fundNodeConfig.getFailRetryCount();
            if (retryCount == -1 || repeatCount <= fundNodeConfig.getFailRetryCount()) {
                log.info("重试次数充足，开始重新投递消息, messageKey = {}", messageExt.getKeys());
                sendRetryMessage(messageExt, fundNodeConfig.getRetryDelayLevel());
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        } catch (Exception e) {
            log.error("更新消费表/重新投递消息/生产下一节点消息处理异常，如果是非幂等性业务，请手工介入。", e);
            return isRepeatAllow ? ConsumeConcurrentlyStatus.RECONSUME_LATER : ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
    }

    protected void produceNextNodes(MessageExt messageExt, Integer businessFinalStatus) throws BizException {

        JSONObject jsonObject = JSONObject.parseObject(new String(messageExt.getBody()));
        String fundId = jsonObject.getString("fundId");
        String bizSerialNo = jsonObject.getString("bizSerialNo");

        String nextNodeCodes = getNextNodeRoutes(fundId, messageExt.getTags(), businessFinalStatus);
        if (StringUtils.isEmpty(nextNodeCodes)) {
            log.info("未查询到消息路由配置, fundId = {}, nodeCode = {}", fundId, messageExt.getTags());
            return;
        }

        String[] arr = nextNodeCodes.split(",");
        for(String nextNode : arr) {
            log.info("注册节点消息开始, nextNode = {}", nextNode);
            FundNodeConfig nodeConfig = getMessageNodeConfig(fundId, nextNode);
            String messageKey = bizSerialNo + "-" + nextNode;
            sendMessage(messageExt.getTopic(), nextNode, messageKey, jsonObject.toJSONString(), nodeConfig.getInitDelayLevel());
        }
    }

    private String getNextNodeRoutes(String fundId, String tags, Integer businessFinalStatus) {

        String field = fundId + "-" + tags + "-" + businessFinalStatus;
        String nextNodeCodes = redisManager.hGet(RedisKeyConst.MESSAGE_NODE_ROUTE, field);
        if(StringUtils.isNotEmpty(nextNodeCodes)) {
            return nextNodeCodes;
        }

        FundNodeRoute fundNodeRoute = fundNodeRouteService.queryByFundIdAndNodeCodeAndStatus(fundId, tags, businessFinalStatus);
        nextNodeCodes = fundNodeRoute.getNextNodeCodes();
        redisManager.hSet(RedisKeyConst.MESSAGE_NODE_ROUTE, field, nextNodeCodes);
        return nextNodeCodes;
    }

    private MessageConsume buildMessageConsume(MessageExt messageExt, boolean isRepeatAllow, ConsumeResult consumeResult) {
        MessageConsume messageConsume = new MessageConsume();
        messageConsume.setMessageKey(messageExt.getKeys());
        messageConsume.setConsumeGroup(getConsumeGroup());
        messageConsume.setProducerGroup(messageExt.getUserProperty("producerGroup"));
        messageConsume.setTopic(messageExt.getTopic());
        messageConsume.setTags(messageExt.getTags());
        messageConsume.setMessageId(messageExt.getMsgId());
        messageConsume.setContext(new String(messageExt.getBody()));
        messageConsume.setIsAllowRepeat(isRepeatAllow ? "1" : "0");
        messageConsume.setRepeatCount(0);
        messageConsume.setBusinessStatus(consumeResult.getBusinessStatus());
        messageConsume.setLastConsumedRemark(consumeResult.getRemark());
        return messageConsume;
    }

    private Integer saveOrUpdateConsumeMessage(boolean isRepeatAllow, MessageConsume messageConsume, ConsumeResult consumeResult, MessageExt messageExt) {

        if (messageConsume == null) {
            messageConsumeService.saveMessageConsume(buildMessageConsume(messageExt, isRepeatAllow, consumeResult));
            return 0;
        }

        Integer repeatCount = messageConsume.getRepeatCount() + 1;
        messageConsume.setRepeatCount(repeatCount);
        messageConsume.setMessageId(messageExt.getMsgId());
        messageConsume.setBusinessStatus(consumeResult.getBusinessStatus());
        messageConsume.setUpdateTime(new Date());

        String remark = consumeResult.getRemark();
        if(StringUtils.isNotEmpty(remark)) {
            String consumedRemark = remark.length() > 255 ? remark.substring(0, 255) : remark;
            messageConsume.setLastConsumedRemark(consumedRemark);
        }
        messageConsumeService.updateByPrimaryKey(messageConsume);
        return repeatCount;
    }

    private ConsumeResult getConsumeResult(MessageExt exps) {

        ConsumeResult consumeResult;
        try {
            consumeResult = businessProcess(exps);
            log.info("消息业务处理结果, consumeResult = {}", JSONObject.toJSONString(consumeResult));
        } catch (Exception e) {
            log.error("消息业务处理异常, messageKey = {}", exps.getKeys(), e);
            consumeResult = new ConsumeResult();
            consumeResult.setRemark(e.getMessage());
        }
        return consumeResult;

    }

    private MQPreBusinessProcessResult messageConsumeCheck(FundNodeConfig nodeConfig, MessageExt exps, MessageConsume messageConsume) {

        //每秒并发检查
        boolean concurrentPass = checkConcurrent(nodeConfig, exps);
        if(!concurrentPass) {
            return MQPreBusinessProcessResult.builder().isSuccess(false).consumeConcurrentlyStatus(ConsumeConcurrentlyStatus.CONSUME_SUCCESS).build();
        }

        //消息重复消费检查
        boolean repeatConsumePass = checkRepeatConsume(nodeConfig.getFailRetryCount(), exps, messageConsume);
        if(!repeatConsumePass) {
            return MQPreBusinessProcessResult.builder().isSuccess(false).consumeConcurrentlyStatus(ConsumeConcurrentlyStatus.CONSUME_SUCCESS).build();
        }

        return MQPreBusinessProcessResult.builder().isSuccess(true).build();
    }

    private boolean checkRepeatConsume(Integer maxRetryCount, MessageExt exps, MessageConsume messageConsume) {

        //maxRetryCount == -1 支持无限重试
        if(messageConsume == null || maxRetryCount == -1) {
            return true;
        }

        if (maxRetryCount == 0) {
            log.info("消息不允许重复消费, messageKey = {}", exps.getKeys());
            return false;
        }

        if(messageConsume.getRepeatCount() > maxRetryCount) {
            log.info("消息已超出最大重试次数, messageKey = {}", exps.getKeys());
            return false;
        }
        return true;
    }

    private boolean checkConcurrent(FundNodeConfig nodeConfig, MessageExt exps) {

        Integer maxConcurrentNum = nodeConfig.getMaxConcurrentNum();
        if (maxConcurrentNum <= 0) {
            return true;
        }

        String concurrentKey = RedisKeyConst.MESSAGE_CONSUME_CONCURRENT + ":" + nodeConfig.getFundId() + "-" + nodeConfig.getNodeCode();
        log.info("消费端每秒并发检查, topic = {}, tags = {}, messageKey = {}", exps.getTopic(), exps.getTags(), exps.getKeys());

        boolean isConcurrentPass = redisManager.concurrentCheck(concurrentKey, maxConcurrentNum, 1);
        if (!isConcurrentPass) {
            log.info("并发检查不通过重新投递延时消息, messageKey = {}", exps.getKeys());
            sendRetryMessage(exps, 4);
        }
        return isConcurrentPass;
    }

    private FundNodeConfig getMessageNodeConfig(String fundId, String tags) throws BizException {

        String field = fundId + "-" + tags;
        String nodeConfig = redisManager.hGet(RedisKeyConst.MESSAGE_NODE_CONFIG, field);
        log.info("缓存中消息节点配置, field = {}, nodeConfig = {}", field, nodeConfig);

        if(StringUtils.isNotEmpty(nodeConfig)) {
            return JSONObject.parseObject(nodeConfig, FundNodeConfig.class);
        }

        FundNodeConfig fundNodeConfig = fundNodeConfigService.queryByFundIdAndNodeCode(fundId, tags);
        if(fundNodeConfig == null) {
            log.error("数据库中未查询到消息节点配置, fundId = {}, nodeCode = {}", fundId, tags);
            throw new BizException(ExceptionTypeEnum.DATA_LACK.getCode(), "消息节点配置不存在");
        }

        redisManager.hSet(RedisKeyConst.MESSAGE_NODE_CONFIG, field, JSONObject.toJSONString(fundNodeConfig));
        return fundNodeConfig;
    }

    private void sendMessage(String topic, String tags, String bizId, String body, Integer delayLevel) {

        MessageProduceDTO messageProduceDto = new MessageProduceDTO();
        messageProduceDto.setTopic(topic);
        messageProduceDto.setTags(tags);
        messageProduceDto.setBizId(bizId);
        messageProduceDto.setBody(body);
        messageProduceDto.setDelayLevel(delayLevel);
        messageProducer.produceMessage(messageProduceDto);
    }

    private void sendRetryMessage(MessageExt messageExt, Integer delayLevel) {

        MessageProduceDTO messageProduceDto = new MessageProduceDTO();
        messageProduceDto.setTopic(messageExt.getTopic());
        messageProduceDto.setTags(messageExt.getTags());
        messageProduceDto.setBizId(messageExt.getKeys());
        messageProduceDto.setBody(new String(messageExt.getBody()));
        messageProduceDto.setDelayLevel(delayLevel);
        messageProducer.produceMessage(messageProduceDto);
    }

}
