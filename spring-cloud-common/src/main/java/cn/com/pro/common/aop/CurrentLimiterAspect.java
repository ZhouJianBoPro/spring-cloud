package cn.com.pro.common.aop;

import cn.com.pro.common.redis.RedisManager;
import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zhoujianbo
 * @Title: 限流器aop
 * @Description:
 * @date Created in 2022/2/23 11:22
 **/
@Component
@Aspect
@Slf4j
public class CurrentLimiterAspect {

    private RateLimiter rateLimiter;

    @Autowired
    private RedisManager redisManager;

    @Pointcut("execution(* cn.com.pro.consumer.contoller..*(..))")
    public void pointcut() {}

    @PostConstruct
    public void init() {

        String permitsPerSecond = redisManager.get("current:permitsPerSecond");
        rateLimiter = RateLimiter.create(Double.parseDouble(permitsPerSecond));
    }

    @Around(value = "pointcut()")
    public Object aroundCurrentLimiter(ProceedingJoinPoint pjp) throws Throwable {

        boolean res = rateLimiter.tryAcquire();
        if(!res) {
            throw new RuntimeException("消息并发超限");
        }

        Object[] args = pjp.getArgs();
        return pjp.proceed(args);
    }

}
