package cn.com.pro.common.aop;

import cn.com.pro.common.annotation.ExceptionMonitor;
import cn.com.pro.common.redis.RedisManager;
import cn.com.pro.common.util.DingTalkUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhoujianbo
 * @Title: 异常监控aop
 * @Description:
 * @date Created in 2022/2/10 11:17
 **/
@Component
@Aspect
@Slf4j
public class ExceptionMonitorAspect {

    @Autowired
    private RedisManager redisManager;

    private static final String DING_TOKEN_KEY = "config:dingToken";

    /**
     * 定义切点
     */
    @Pointcut("execution(public * *(..)) && @annotation(cn.com.pro.common.annotation.ExceptionMonitor)")
    public void pointcut() {}

    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void afterThrowExceptionMonitor(JoinPoint jp, Throwable e) throws Throwable {

        //获取方法签名
        MethodSignature signature = (MethodSignature) jp.getSignature();
        String targetMethodName = signature.getName();

        //获取目标方法
        Class cls = jp.getTarget().getClass();
        Method method = cls.getDeclaredMethod(targetMethodName, signature.getParameterTypes());

        //获取目标方法中的注解
        ExceptionMonitor exceptionMonitor = method.getAnnotation(ExceptionMonitor.class);
        boolean printRequestParam = exceptionMonitor.printRequestParam();

        StringBuilder sb = getContext(cls.getName(), targetMethodName);

        //是否打印请求参数
        if(printRequestParam) {
            Object[] args = jp.getArgs();
            sb.append("\n").append("#### **请求参数：** ").append(JSONObject.toJSONString(args));
        }
        sb.append("\n").append("#### **异常描述：** ").append(e.getMessage());

        String dingToken = redisManager.get(DING_TOKEN_KEY);
        DingTalkUtil.sendByMarkdown("调用异常", sb.toString(), dingToken, Lists.<String>newArrayList(), false);
    }

    private StringBuilder getContext(String className, String methodName) {

        StringBuilder sb = new StringBuilder("## **------接口调用异常告警------** \n");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sb.append("#### **时间：** ").append(df.format(new Date())).append("\n")
                .append("#### **类名：** ").append(className).append("\n")
                .append("#### **接口名称：** ").append(methodName);
        return sb;
    }
}
