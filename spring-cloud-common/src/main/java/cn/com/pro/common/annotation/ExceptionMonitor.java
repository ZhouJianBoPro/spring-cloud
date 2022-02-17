package cn.com.pro.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhoujianbo
 * @Title: 自定义异常监控注解(@ExceptionMonitor)
 * @Description:
 * @date Created in 2022/2/10 10:29
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionMonitor {

    /**
     * 输出方法名称
     * @return 输出方法名称
     */
    String name() default "";

    /**
     * 是否需要打印请求参数，请求参数过大建议不打印
     * @return boolean
     */
    boolean printRequestParam() default false;
}
