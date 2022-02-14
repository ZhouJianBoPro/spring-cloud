package cn.com.pro.common.redis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhoujianbo
 * @Title: redis集群配置
 * @Description:
 * @date Created in 2022/1/27 13:59
 **/
@Slf4j
@Configuration
@RefreshScope
public class RedisClusterConfig {

    /**
     * 最大连接数
     */
    @Value("${spring.redis.pool.maxTotal}")
    private int maxTotal;

    /**
     * 最大空闲连接数
     */
    @Value("${spring.redis.pool.maxIdle}")
    private int maxIdle;

    /**
     * 节点列表
     */
    @Value("${spring.redis.cluster.nodes}")
    private String nodes;

    /**
     * 连接超时时间， 毫秒
     */
    @Value("${spring.redis.pool.connectionTimeout}")
    private int connectionTimeout;

    /**
     * 读写超时时间, 毫秒
     */
    @Value("${spring.redis.pool.socketTimeout}")
    private int socketTimeout;

    /**
     * 连接异常重试次数
     */
    @Value("${spring.redis.pool.maxAttempts}")
    private int maxAttempts;

    /**
     * 密码
     */
    @Value("${spring.redis.password}")
    private String password;

    private JedisPoolConfig jedisPoolConfig() {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        return jedisPoolConfig;
    }

    @Bean
    public JedisCluster getRedisCluster() {

        String[] addresses = nodes.split(",");
        Set<HostAndPort> hostAndPorts = new HashSet<>();
        for (String host : addresses) {
            String[] hosts = host.split(":");
            HostAndPort hostAndPort = new HostAndPort(hosts[0], Integer.parseInt(hosts[1]));
            hostAndPorts.add(hostAndPort);
        }

        return new JedisCluster(hostAndPorts, connectionTimeout, socketTimeout, maxAttempts, password, jedisPoolConfig());
    }

}
