package cn.com.pro.provider.db.service;

import cn.com.pro.provider.db.model.FundNodeRoute;

/**
 * @author zhoujianbo
 * @Title: 消息节点流转数据库操作服务
 * @Description:
 * @date Created in 2022/2/17 13:55
 **/
public interface IFundNodeRouteService {

    /**
     * 通过fund、nodeCode、status查询唯一记录
     * @param fundId
     * @param nodeCode
     * @param status
     * @return
     */
    FundNodeRoute queryByFundIdAndNodeCodeAndStatus(String fundId, String nodeCode, Integer status);
}
