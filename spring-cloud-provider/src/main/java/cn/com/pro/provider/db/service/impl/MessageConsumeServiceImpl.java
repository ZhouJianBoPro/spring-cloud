package cn.com.pro.provider.db.service.impl;

import cn.com.pro.provider.db.mapper.MessageConsumeMapper;
import cn.com.pro.provider.db.model.MessageConsume;
import cn.com.pro.provider.db.model.MessageConsumeKey;
import cn.com.pro.provider.db.service.IMessageConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhoujianbo
 * @Title:
 * @Description:
 * @date Created in 2022/2/17 15:13
 **/
@Service
public class MessageConsumeServiceImpl implements IMessageConsumeService {

    @Autowired
    private MessageConsumeMapper messageConsumeMapper;

    @Override
    public void saveMessageConsume(MessageConsume messageConsume) {
        messageConsumeMapper.insertSelective(messageConsume);
    }

    @Override
    public MessageConsume queryByMessageKeyAndConsumeGroup(String keys, String consumeGroup) {

        MessageConsumeKey messageConsumeKey = new MessageConsumeKey();
        messageConsumeKey.setMessageKey(keys);
        messageConsumeKey.setConsumeGroup(consumeGroup);
        return messageConsumeMapper.selectByPrimaryKey(messageConsumeKey);
    }

    @Override
    public void updateByPrimaryKey(MessageConsume messageConsume) {
        messageConsumeMapper.updateByPrimaryKeySelective(messageConsume);
    }
}
