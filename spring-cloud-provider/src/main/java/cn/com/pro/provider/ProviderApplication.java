package cn.com.pro.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhoujianbo
 * @Title: 服务提供者启动类
 * @Description:
 * @date Created in 2022/1/20 17:05
 **/
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"cn.com.pro.common", "cn.com.pro.provider"})
@MapperScan("cn.com.pro.provider.db.mapper")
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
