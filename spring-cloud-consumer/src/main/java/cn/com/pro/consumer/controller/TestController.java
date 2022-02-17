package cn.com.pro.consumer.controller;

import com.cn.pro.api.TestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoujianbo
 * @Title:
 * @Description:
 * @date Created in 2022/1/22 9:45
 **/
@RestController
public class TestController {

    @Autowired
    private TestApi testApi;

    @GetMapping("/test")
    public String test(@RequestParam String fundId, @RequestParam String nodeCode) {
        return testApi.test(fundId, nodeCode);
    }
}
