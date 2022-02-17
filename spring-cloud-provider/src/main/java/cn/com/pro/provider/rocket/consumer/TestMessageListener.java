package cn.com.pro.provider.rocket.consumer;

import cn.com.pro.provider.consts.ConsumeGroupConst;
import cn.com.pro.provider.rocket.AbstractMessageListener;
import cn.com.pro.provider.rocket.model.ConsumeResult;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

/**
 * @author zhoujianbo
 * @Title:
 * @Description:
 * @date Created in 2022/2/17 18:54
 **/
@Component
public class TestMessageListener extends AbstractMessageListener {


    @Override
    protected String getConsumeGroup() {
        return ConsumeGroupConst.TEST_CONSUME;
    }

    @Override
    protected ConsumeResult businessProcess(MessageExt messageExt) {
        return null;
    }
}
