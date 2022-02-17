package cn.com.pro.provider.rocket.model;

import lombok.Data;

@Data
public class Message {

    /**
     * 资方编号
     */
    private String fundId;

    /**
     * 业务流水号
     */
    private String bizSerialNo;
}
