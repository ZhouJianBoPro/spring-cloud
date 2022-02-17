package cn.com.pro.provider.consts;

/**
 * @author zhoujianbo
 * @Title:
 * @Description:
 * @date Created in 2020/11/18 10:25
 **/
public class RedisKeyConst {

    /**
     * 消息消费端并发控制
     */
    public static final String MESSAGE_CONSUME_CONCURRENT = "message_consume_concurrent";

    /**
     * 消息消费锁
     */
    public static final String MESSAGE_CONSUME_LOCK = "message_consume_lock";

    /**
     * 消息节点配置
     */
    public static final String MESSAGE_NODE_CONFIG = "fpm:fund_node_config";

    /**
     * 消息节点路由
     */
    public static final String MESSAGE_NODE_ROUTE = "fpm:fund_node_route";

}
