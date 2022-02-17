package cn.com.pro.provider.db.service;

import cn.com.pro.provider.db.model.FundNodeConfig;

/**
 * @author zhoujianbo
 * @Title: 消息节点配置数据库操作服务
 * @Description:
 * @date Created in 2022/2/17 13:53
 **/
public interface IFundNodeConfigService {

    /**
     * 通过资金编号和nodeCode查找记录
     * @param fundId
     * @param nodeCode
     * @return FundNodeConfig
     */
    FundNodeConfig queryByFundIdAndNodeCode(String fundId, String nodeCode);
}
