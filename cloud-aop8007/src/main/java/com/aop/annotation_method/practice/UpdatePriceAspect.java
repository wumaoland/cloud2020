package com.aop.annotation_method.practice;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class UpdatePriceAspect {
    @Autowired
    public StringRedisTemplate redisTemplate;

    @Pointcut(value = "@annotation(com.aop.annotation_method.practice.UpdatePriceLogAnnoation)")
    public void updatePricePoint(){

    }

    @Around(value = "updatePricePoint()")
    public void updatePriceArount(ProceedingJoinPoint point){
        //前置通知
        System.out.println(String.format("当前目标方法名称[%s],请求参数[%s]", point.getSignature().getName(), JSON.toJSONString(point.getArgs()[0])));
        Product product = (Product) point.getArgs()[0];
        String beforeProductStr = this.redisTemplate.opsForValue().get("product") ;
        if (!StringUtils.isEmpty(beforeProductStr)) {
            Product beforeProduct = JSON.parseObject(beforeProductStr, Product.class);
            redisTemplate.opsForValue().set(String.format("updatePriceLog:%s",product.getId()),String.format("%s->%s",beforeProduct.getPrice().toString(),product.getPrice().toString()));
        }
        //执行目标方法
        try {
            point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //后置通知(有异常不会执行)
        System.out.println(String.format("后置通知%s",JSON.toJSONString(point.getArgs()[0])));
    }
}
