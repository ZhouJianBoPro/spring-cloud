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
@FeignClient(name = "spring-cloud-provider")
public interface TestApi {

    @RequestMapping(value = "/getFundNodeConfigDetail", method = RequestMethod.GET)
    String test(@RequestParam("fundId") String fundId, @RequestParam("nodeCode") String nodeCode);

}
