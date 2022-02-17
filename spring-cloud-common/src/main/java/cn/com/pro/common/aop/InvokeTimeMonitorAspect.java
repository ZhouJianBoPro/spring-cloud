package cn.com.pro.common.aop;

import cn.com.pro.common.annotation.InvokeTimeMonitor;
import cn.com.pro.common.redis.RedisManager;
import cn.com.pro.common.util.DingTalkUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
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
 * @Title: 接口调用耗时监控aop
 * @Description: 将调用耗时较长的接口通过钉钉预警出来
 * @date Created in 2022/2/10 10:36
 **/
@Component
@Aspect
@Slf4j
public class InvokeTimeMonitorAspect {

    @Autowired
    private RedisManager redisManager;

    private static final String DING_TOKEN_KEY = "config:dingToken";

    /**
     * 定义切点
     */
    @Pointcut("execution(public * *(..)) && @annotation(cn.com.pro.common.annotation.InvokeTimeMonitor)")
    public void pointcut() {}

    @Around(value = "pointcut()")
    public Object aroundInvokeTimeMonitor(ProceedingJoinPoint pjp) throws Throwable {

        //获取方法签名
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String targetMethodName = signature.getName();

        //获取目标方法
        Class cls = pjp.getTarget().getClass();
        Method method = cls.getDeclaredMethod(targetMethodName, signature.getParameterTypes());
        //获取目标方法中的注解
        InvokeTimeMonitor invokeTimeMonitor = method.getAnnotation(InvokeTimeMonitor.class);

        long startTimeMills = System.currentTimeMillis();

        Object[] args = pjp.getArgs();
        Object object = pjp.proceed(args);

        long endTimeMills = System.currentTimeMillis();
        long invokeTimeMills = endTimeMills - startTimeMills;

        if(invokeTimeMills >= invokeTimeMonitor.timeout()) {
            String context = getContext(cls.getName(), targetMethodName, invokeTimeMills);
            String dingToken = redisManager.get(DING_TOKEN_KEY);
            DingTalkUtil.sendByMarkdown("调用耗时", context, dingToken, Lists.<String>newArrayList(), false);
        }

        return object;
    }

    private String getContext(String className, String methodName, long invokeTimeMills) {

        StringBuilder sb = new StringBuilder("## **------接口调用耗时告警------** \n");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sb.append("#### **时间：** ").append(df.format(new Date())).append("\n")
                .append("#### **类名：** ").append(className).append("\n")
                .append("#### **接口名称：** ").append(methodName).append("\n")
                .append("#### **调用耗时：** ").append(invokeTimeMills).append("ms");
        return sb.toString();
    }

}
