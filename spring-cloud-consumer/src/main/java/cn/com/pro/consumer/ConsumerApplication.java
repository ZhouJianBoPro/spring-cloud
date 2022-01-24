package cn.com.pro.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhoujianbo
 * @Title: 启动类
 * @Description:
 * @date Created in 2022/1/13 17:30
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.cn.pro.api"})
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}

