package cn.com.pro.provider.rest;

import cn.com.pro.common.redis.RedisManager;
import cn.com.pro.provider.db.model.TLoanSucceedExtraInfo;
import cn.com.pro.provider.db.service.ITestService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoujianbo
 * @Title:
 * @Description:
 * @date Created in 2022/1/21 17:49
 **/
@RestController
public class TestRest {

    @Autowired
    private ITestService testService;

    @Autowired
    private RedisManager redisManager;

    @RequestMapping(value = "/testMethod1", method = RequestMethod.GET)
    String testMethod1(@RequestParam("name") String name) {

        TLoanSucceedExtraInfo list = testService.getAll();
        String a = redisManager.get("fpm:sit3:config:dingToken");
        return JSONObject.toJSONString(list);
    }
}
