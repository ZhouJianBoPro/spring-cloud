package cn.com.pro.provider.db.service.impl;

import cn.com.pro.provider.db.mapper.FundNodeConfigMapper;
import cn.com.pro.provider.db.model.FundNodeConfig;
import cn.com.pro.provider.db.model.FundNodeConfigExample;
import cn.com.pro.provider.db.service.IFundNodeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author zhoujianbo
 * @Title:
 * @Description:
 * @date Created in 2022/2/17 13:53
 **/
@Service
public class FundNodeConfigServiceImpl implements IFundNodeConfigService {

    @Autowired
    private FundNodeConfigMapper fundNodeConfigMapper;

    @Override
    public FundNodeConfig queryByFundIdAndNodeCode(String fundId, String nodeCode) {

        FundNodeConfigExample example = new FundNodeConfigExample();
        example.createCriteria().andFundIdEqualTo(fundId).andNodeCodeEqualTo(nodeCode);

        List<FundNodeConfig> list = fundNodeConfigMapper.selectByExample(example);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
}
