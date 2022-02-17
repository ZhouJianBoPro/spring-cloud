package cn.com.pro.provider.rocket.model;

import cn.com.pro.provider.db.model.FundNodeConfig;
import cn.com.pro.provider.db.model.MessageConsume;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MQPreBusinessProcessResult {
	private boolean isSuccess;
	private boolean isConcurrentCheckPassed;
	private ConsumeConcurrentlyStatus consumeConcurrentlyStatus;
	private MessageConsume messageConsume ;
	private FundNodeConfig nodeConfig ;
	private boolean isAllowRepeat ;
	private Message message;
}