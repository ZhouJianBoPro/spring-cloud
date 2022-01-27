package cn.com.pro.common.rocketmq.dto;

import lombok.Data;

/**
 * @author zhoujianbo
 * @Title: 消息发送请求实体
 * @Description:
 * @date Created in 2022/1/27 17:10
 **/
@Data
public class MessageProduceDTO {

    /**
     * 消息主题
     */
    private String topic;

    /**
     * 子主题
     */
    private String tags;

    /**
     * 业务id
     */
    private String bizId;

    /**
     * 业报报文
     */
    private String body;

    /**
     * 延迟级别, 默认不延迟
     */
    private int delayLevel = 0;
}
