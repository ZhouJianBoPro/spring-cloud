package cn.com.pro.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhoujianbo
 * @Title: 自定义接口调用时长监控注解(@InvokeTimeMonitor)
 * @Description:
 * @date Created in 2022/2/10 10:29
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InvokeTimeMonitor {

    /**
     * 输出方法名称
     * @return 输出方法名称
     */
    String name() default "";

    /**
     * 接口超时时间，单位毫米，默认10s
     * @return 设置的超时时间
     */
    long timeout() default 10000L;
}
