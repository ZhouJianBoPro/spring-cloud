package cn.com.pro.provider.db.service.impl;

import cn.com.pro.provider.db.mapper.FundNodeRouteMapper;
import cn.com.pro.provider.db.model.FundNodeRoute;
import cn.com.pro.provider.db.model.FundNodeRouteExample;
import cn.com.pro.provider.db.service.IFundNodeRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author zhoujianbo
 * @Title:
 * @Description:
 * @date Created in 2022/2/17 13:55
 **/
@Service
public class FundNodeRouteServiceImpl implements IFundNodeRouteService {

    @Autowired
    private FundNodeRouteMapper fundNodeRouteMapper;

    @Override
    public FundNodeRoute queryByFundIdAndNodeCodeAndStatus(String fundId, String nodeCode, Integer status) {

        FundNodeRouteExample example = new FundNodeRouteExample();
        example.createCriteria().andFundIdEqualTo(fundId).andNodeCodeEqualTo(nodeCode).andStatusEqualTo(status);

        List<FundNodeRoute> list = fundNodeRouteMapper.selectByExample(example);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
}
