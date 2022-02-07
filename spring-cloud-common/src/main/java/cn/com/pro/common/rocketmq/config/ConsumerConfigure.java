package cn.com.pro.common.rocketmq.config;

import cn.com.pro.common.rocketmq.dto.MessageConsumeDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoujianbo
 * @Title: 消费者实例
 * @Description:
 * @date Created in 2022/2/7 10:23
 **/
@Slf4j
@Configuration
@RefreshScope
public class ConsumerConfigure {

    @Value("${rocket.nameSrvAddr}")
    private String nameSrvAddr;

    @Value("${rocket.consumer.groupName}")
    private String groupName;

    @Value("${application.env}")
    private String env;

    public DefaultMQPushConsumer getDefaultMQPushConsumer(MessageConsumeDTO messageConsumeDto) {

        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer();
        defaultMQPushConsumer.setNamesrvAddr(nameSrvAddr);
        defaultMQPushConsumer.setConsumerGroup(groupName);
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        defaultMQPushConsumer.setMessageListener(messageConsumeDto.getMessageListener());

        Map<String, String> subscription = new HashMap<>();
        String topic = messageConsumeDto.getTopic();
        if(!topic.endsWith("_" + env)) {
            topic += "_" + env;
        }

        subscription.put(topic, messageConsumeDto.getTags());
        defaultMQPushConsumer.setSubscription(subscription);
        return defaultMQPushConsumer;
    }


}
