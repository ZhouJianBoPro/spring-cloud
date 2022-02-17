package cn.com.pro.provider.exception;


import cn.com.pro.provider.enums.ExceptionTypeEnum;

/**
 * @author zhoujianbo
 * @Title: 业务异常
 * @Description:
 * @date Created in 2022/02/17 16:02
 **/
public class BizException extends Exception {

	  public BizException() {}

	  public BizException(String message, Throwable cause) {
	    super(message, cause);
	  }

	  public BizException(String message) {
	    super(message);
	  }

	  public BizException(Throwable cause) {
	    super(cause);
	  }

	  public BizException(String errCode, String errMsg) {
	    super(errCode + ":" + errMsg);
	  }

	  public BizException(ExceptionTypeEnum typeEnum) {
		  super(typeEnum.getCode() + ":" + typeEnum.getMessage());
	  }

}
