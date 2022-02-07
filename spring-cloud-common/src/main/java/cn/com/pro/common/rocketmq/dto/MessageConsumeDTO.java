package cn.com.pro.common.rocketmq.dto;

import lombok.Data;
import org.apache.rocketmq.client.consumer.listener.MessageListener;

/**
 * @author zhoujianbo
 * @Title: 消息消费实体
 * @Description:
 * @date Created in 2022/2/7 10:16
 **/
@Data
public class MessageConsumeDTO {

    /**
     * 消息主题
     */
    private String topic;

    /**
     * 子主题
     */
    private String tags;

    /**
     * 消息监听器
     */
    private MessageListener messageListener;

}
