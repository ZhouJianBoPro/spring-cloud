package cn.com.pro.common.rocketmq.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoujianbo
 * @Title: 生产者实例
 * @Description:
 * @date Created in 2022/1/27 17:00
 **/
@Slf4j
@Configuration
@RefreshScope
public class ProducerConfigure {

    @Value("${rocket.namSrvAddr}")
    private String nameSrvAddr;

    @Value("${rocket.producer.groupName}")
    private String groupName;

    @Value("${rocket.producer.vipChannelEnabled}")
    private boolean vipChannelEnabled;

    @Value("${rocket.producer.retryTimesWhenSendFailed}")
    private int retryTimesWhenSendFailed;

    @Value("${rocket.producer.instanceName}")
    private String instanceName;

    @Bean
    public DefaultMQProducer getDefaultMQProducer() {

        DefaultMQProducer defaultMQProducer = new DefaultMQProducer();
        defaultMQProducer.setNamesrvAddr(nameSrvAddr);
        defaultMQProducer.setProducerGroup(groupName);
        defaultMQProducer.setVipChannelEnabled(vipChannelEnabled);
        defaultMQProducer.setRetryTimesWhenSendFailed(retryTimesWhenSendFailed);
        defaultMQProducer.setInstanceName(instanceName);
        return defaultMQProducer;
    }



}
