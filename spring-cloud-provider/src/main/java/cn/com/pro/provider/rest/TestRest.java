package cn.com.pro.provider.rest;

import cn.com.pro.common.redis.RedisManager;
import cn.com.pro.provider.db.model.FundNodeConfig;
import cn.com.pro.provider.db.service.IFundNodeConfigService;
import com.alibaba.fastjson.JSONObject;
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
    private IFundNodeConfigService fundNodeConfigService;

    @RequestMapping(value = "/getFundNodeConfigDetail", method = RequestMethod.GET)
    String getFundNodeConfigDetail(@RequestParam("fundId") String fundId, @RequestParam("nodeCode") String nodeCode) {

        FundNodeConfig fundNodeConfig = fundNodeConfigService.queryByFundIdAndNodeCode(fundId, nodeCode);
        return JSONObject.toJSONString(fundNodeConfig);
    }
}
