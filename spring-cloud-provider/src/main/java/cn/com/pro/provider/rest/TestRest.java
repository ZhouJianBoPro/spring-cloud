package cn.com.pro.provider.rest;

import cn.com.pro.common.rocketmq.MessageProducer;
import cn.com.pro.common.rocketmq.dto.MessageProduceDTO;
import cn.com.pro.provider.consts.MessageTagConst;
import cn.com.pro.provider.consts.MessageTopicConst;
import cn.com.pro.provider.db.model.FundNodeConfig;
import cn.com.pro.provider.db.service.IFundNodeConfigService;
import cn.com.pro.provider.rocket.model.Message;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoujianbo
 * @Title:
 * @Description:
 * @date Created in 2022/1/21 17:49
 **/
@RestController
public class TestRest {

    @Autowired
    private IFundNodeConfigService fundNodeConfigService;

    @Autowired
    private MessageProducer messageProducer;

    @RequestMapping(value = "/getFundNodeConfigDetail", method = RequestMethod.GET)
    String getFundNodeConfigDetail(@RequestParam("fundId") String fundId, @RequestParam("nodeCode") String nodeCode) {

        FundNodeConfig fundNodeConfig = fundNodeConfigService.queryByFundIdAndNodeCode(fundId, nodeCode);
        messageProducer.produceMessage(buildMessageProduceDTO());
        return JSONObject.toJSONString(fundNodeConfig);
    }

    private MessageProduceDTO buildMessageProduceDTO() {
        MessageProduceDTO messageProduceDTO = new MessageProduceDTO();
        messageProduceDTO.setTopic(MessageTopicConst.TEST);
        messageProduceDTO.setTags(MessageTagConst.TEST);
        messageProduceDTO.setBizId("111111111111111111111111111111");
        Message message = new Message();
        message.setFundId("30027");
        message.setBizSerialNo("22222222222222222");
        messageProduceDTO.setBody(JSONObject.toJSONString(message));
        return messageProduceDTO;
    }
}
