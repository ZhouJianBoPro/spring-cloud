package cn.com.pro.provider.rocket;

import cn.com.pro.provider.consts.ConsumeGroupConst;
import cn.com.pro.provider.consts.MessageTagConst;
import cn.com.pro.provider.consts.MessageTopicConst;
import cn.com.pro.provider.rocket.consumer.TestMessageListener;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;


/**
 * @author zhoujianbo
 * @Title: 消费者bean配置
 * @Description:
 * @date Created in 2022/2/17 18:57
 **/
@Slf4j
@Configuration
@RefreshScope
public class ConsumeBeanConfigure {

    @Value("${rocket.nameSrvAddr}")
    private String nameSrvAddr;

    @Value("${rocket.producer.instanceName}")
    private String instanceName;

    @Value("${spring.profiles.active}")
    private String env;

    @Autowired
    private TestMessageListener testMessageListener;

    @Bean
    public void initTestMessageConsumeInstance() throws MQClientException {

        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer();
        defaultMQPushConsumer.setNamesrvAddr(nameSrvAddr);

        String consumerGroup = ConsumeGroupConst.TEST_CONSUME;
        if(!consumerGroup.endsWith("_" + env)) {
            consumerGroup += "_" + env;
        }
        defaultMQPushConsumer.setConsumerGroup(consumerGroup);
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        defaultMQPushConsumer.setMessageListener(testMessageListener);
        Map<String, String> subscription = Maps.newHashMap();

        String topic = MessageTopicConst.TEST;
        if(!topic.endsWith("_" + env)) {
            topic += "_" + env;
        }
        subscription.put(topic, MessageTagConst.TEST);
        defaultMQPushConsumer.setSubscription(subscription);
        defaultMQPushConsumer.setInstanceName(instanceName);
        defaultMQPushConsumer.start();
    }
}
