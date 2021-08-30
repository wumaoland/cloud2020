package com.aop.annotation_method;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
@Aspect
public class RedissonApiLimitAspect {
    final String API_LIMIT = "API_LIMIT";
    @Autowired
    private RedissonClient redission;


    @Pointcut(value = "@annotation(com.aop.annotation_method.RedissonApiLimit)")
    public void RedissonApiLimtPoint() {

    }

    @Before(value = "RedissonApiLimtPoint()&& @annotation(redissonApiLimit)")
    public void doBefore(JoinPoint joinPoint, RedissonApiLimit redissonApiLimit) {
        int tps = redissonApiLimit.tps();
        int rateTime = redissonApiLimit.rateTime();
        String key = redissonApiLimit.key();
        if (StringUtils.isEmpty(key)) {
            key=joinPoint.getSignature().getName();
        }
        RRateLimiter rateLimiter = this.redission.getRateLimiter(API_LIMIT.concat(":").concat(key));
        rateLimiter.trySetRate(RateType.OVERALL, tps, rateTime, RateIntervalUnit.SECONDS);
        boolean b = rateLimiter.tryAcquire(1);
        if (!b && tps!=0) {
            throw new RuntimeException("该接口被限流");
        }
    }
}
