package cn.com.pro.provider.db.service;

import cn.com.pro.provider.db.model.MessageConsume;

/**
 * @author zhoujianbo
 * @Title: 消息消费记录数据库操作
 * @Description:
 * @date Created in 2022/2/17 15:11
 **/
public interface IMessageConsumeService {

    /**
     * 保存消息消费记录
     * @param messageConsume
     * @return void
     */
    void saveMessageConsume(MessageConsume messageConsume);

    /**
     * 通过messageKey和consumeGroup查找消费记录
     * @param keys
     * @param consumeGroup
     * @return MessageConsume
     */
    MessageConsume queryByMessageKeyAndConsumeGroup(String keys, String consumeGroup);

    /**
     * 通过message_key、consume_group 主键进行更新
     * @param messageConsume
     */
    void updateByPrimaryKey(MessageConsume messageConsume);
}
