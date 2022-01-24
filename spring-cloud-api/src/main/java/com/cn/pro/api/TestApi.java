package com.cn.pro.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhoujianbo
 * @Title:
 * @Description:
 * @date Created in 2022/1/21 17:10
 **/
@FeignClient(value = "spring-cloud-provider")
public interface TestApi {

    @RequestMapping(value = "/testMethod1", method = RequestMethod.GET)
    String users(@RequestParam("name") String name);

}
