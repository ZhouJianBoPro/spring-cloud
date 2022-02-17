package cn.com.pro.provider.enums;

/**
 * @author zhoujianbo
 * @Title: 异常类型
 * @Description:
 * @date Created in 2022/02/17 16:02
 **/
public enum ExceptionTypeEnum {

    PARAM_LACK("1001", "参数缺失"),

    PARAM_ILLEGAL("1002", "参数不合法"),

    DATA_LACK("1003", "数据缺失");

    private String code;

    private String message;

    ExceptionTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
