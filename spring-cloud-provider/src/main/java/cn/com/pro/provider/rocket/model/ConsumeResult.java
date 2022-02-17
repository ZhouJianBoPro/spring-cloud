package cn.com.pro.provider.rocket.model;

import cn.com.pro.provider.consts.StatusConst;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsumeResult {

    /**
     * 业务状态
     */
    private Integer businessStatus;

    /**
     * 消息处理业务状态
     */
    private Integer businessFinalStatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * 业务流水号
     */
    private String bizSerialNo;

    public ConsumeResult(Integer businessStatus) {

        this.businessStatus = businessStatus;
        switch (businessStatus) {
            case 1:
                this.remark = "处理成功";
                this.businessFinalStatus = 1;
                break;

            case 0:
                this.remark = "业务处理中";
                break;

            case 2:
                this.remark = "处理失败";
                this.businessFinalStatus = 2;
                break;
        }
    }

    public ConsumeResult(Integer businessStatus, String remark) {

        this.remark = remark;
        this.businessStatus = businessStatus;
        switch (businessStatus) {
            case 1:
                this.businessFinalStatus = 1;
                break;
            case 2:
                this.businessFinalStatus = 12;
                break;
        }
    }

    /**
     * 获取一个自定义消息的是吧响应
     * @param remark
     * @return
     */
    public static ConsumeResult getFail(String remark) {
        ConsumeResult result = new ConsumeResult(StatusConst.FAIL, remark);
        return result;
    }
    
    /**
     * 获取一个默认的失败响应
     * @return
     */
    public static ConsumeResult getFail() {
        ConsumeResult result = new ConsumeResult(StatusConst.FAIL);
        return result;
    }

    /**
     * 获取一个自定义消息的成功响应
     * @param remark
     * @return
     */
    public static ConsumeResult getSuccess(String remark) {
        ConsumeResult result = new ConsumeResult(StatusConst.SUCCESS, remark);
        return result;
    }
    
    /**
     * 获取一个默认的成功响应
     * @return
     */
    public static ConsumeResult getSuccess() {
        ConsumeResult result = new ConsumeResult(StatusConst.SUCCESS);
        return result;
    }


    /**
     * 构建一个处理中的响应
     * @param remark
     * @return
     */
    public static ConsumeResult getProcess(String remark) {
        return new ConsumeResult(StatusConst.IN_PROCESS ,remark);
    }

    /**
     * 获取一个默认的处理中响应
     * @return
     */
    public static ConsumeResult getProcess() {
        return new ConsumeResult(StatusConst.IN_PROCESS);
    }
    
}