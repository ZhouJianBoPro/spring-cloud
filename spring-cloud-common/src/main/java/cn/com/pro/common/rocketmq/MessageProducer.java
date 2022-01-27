package cn.com.pro.common.rocketmq;

import cn.com.pro.common.rocketmq.dto.MessageProduceDTO;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @author zhoujianbo
 * @Title: 生产者发送消息
 * @Description:
 * @date Created in 2022/1/27 16:58
 **/
@Slf4j
@Component
public class MessageProducer {

    @Value("${application.env}")
    private String env;

    private final DefaultMQProducer defaultMQProducer;

    public MessageProducer(DefaultMQProducer defaultMQProducer) {
        this.defaultMQProducer = defaultMQProducer;
    }

    public boolean produceMessage(MessageProduceDTO messageProduceDto) {

        log.info("消息发送请求报文, messageProduceDto = {}", JSONObject.toJSONString(messageProduceDto));
        try {
            Message message = buildMessage(messageProduceDto);
            log.info("开始发送消息, message = {}", JSONObject.toJSONString(message));

            SendResult sendResult = defaultMQProducer.send(message);
            SendStatus sendStatus = sendResult.getSendStatus();
            log.info("消息发送结果, sendStatus = {}", sendStatus.name());
            return SendStatus.SEND_OK.equals(sendResult.getSendStatus());
        } catch (Exception e) {
            log.error("消息发送失败", e);
            return false;
        }
    }

    private Message buildMessage(MessageProduceDTO messageProduceDto) throws UnsupportedEncodingException {

        Message message = new Message();
        String topic = messageProduceDto.getTopic();
        if(!topic.endsWith("_" + env)) {
            topic += "_" + env;
        }
        message.setTopic(topic);
        message.setTags(messageProduceDto.getTags());
        message.setKeys(messageProduceDto.getBizId());
        message.putUserProperty("producerGroup", defaultMQProducer.getProducerGroup());
        message.setBody(messageProduceDto.getBody().getBytes(RemotingHelper.DEFAULT_CHARSET));
        return message;
    }


}
