package cn.com.pro.common.rocketmq.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoujianbo
 * @Title: 生产者实例
 * @Description: 只创建一个DefaultMQProducer, 所有生产者使用同一个生产者实例
 * @date Created in 2022/1/27 17:00
 **/
@Slf4j
@Configuration
@RefreshScope
public class ProducerConfigure {

    @Value("${rocket.namSrvAddr}")
    private String nameSrvAddr;

    /**
     * 正常情况下生产者组是没有作用的
     * 但是在发送事务消息时，如果producer中途意外宕机，broker会主动回调producer group 内的任意一台机器来确认事务的状态
     */
    @Value("${rocket.producer.groupName}")
    private String groupName;

    @Value("${rocket.producer.vipChannelEnabled}")
    private boolean vipChannelEnabled;

    @Value("${rocket.producer.retryTimesWhenSendFailed}")
    private int retryTimesWhenSendFailed;

    /**
     * 客户端实例名称
     * 客户端创建的多个Producer、Consumer实际是共用一个内部实例（这个实例包含网络连接、线程资源等）
     * 同一集群使用同一个实例名称，不同集群实例名称需要区分开来
     */
    @Value("${rocket.producer.instanceName}")
    private String instanceName;

    @Bean
    public DefaultMQProducer getDefaultMQProducer() throws MQClientException {

        DefaultMQProducer defaultMQProducer = new DefaultMQProducer();
        defaultMQProducer.setNamesrvAddr(nameSrvAddr);
        defaultMQProducer.setProducerGroup(groupName);
        defaultMQProducer.setVipChannelEnabled(vipChannelEnabled);
        defaultMQProducer.setRetryTimesWhenSendFailed(retryTimesWhenSendFailed);
        defaultMQProducer.setInstanceName(instanceName);
        defaultMQProducer.start();
        return defaultMQProducer;
    }

}
