package com.cn.pro.api.model;

/**
 * @author zhoujianbo
 * @Title: 基础响应报文
 * @Description:
 * @date Created in 2021/12/3 17:45
 **/
public class BaseResp {

    private String respCode;

    private String respMessage;

    private Object data;

    private static final String SUCCESS_CODE = "0000";

    public BaseResp(String respCode, String respMessage) {
        this.respCode = respCode;
        this.respMessage = respMessage;
    }

    public BaseResp(String respMessage, Object data) {
        this.respCode = SUCCESS_CODE;
        this.respMessage = respMessage;
        this.data = data;
    }

    public static BaseResp buildBaseResp(String respCode, String respMessage) {
        return new BaseResp(respCode, respMessage);
    }

    public static BaseResp buildSuccessBaseResp(String respMessage, Object data) {
        return new BaseResp(respMessage, data);
    }

    public boolean isInvokeSuccess() {
        return SUCCESS_CODE.equals(this.respCode);
    }


    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
