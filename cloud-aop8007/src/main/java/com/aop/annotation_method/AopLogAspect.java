package com.aop.annotation_method;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@Aspect
public class AopLogAspect {
    @Pointcut(value = "@annotation(com.aop.annotation_method.AroundAopLog)")
    public void aroundAopLog() {
    }

    @Pointcut(value = "@annotation(com.aop.annotation_method.BeforeAopLog)")
    public void beforeAopLog() {
    }

    @Pointcut(value = "@annotation(com.aop.annotation_method.AfterAopLog)")
    public void afterAopLog() {
    }

    /**
     * 环绕通知
     * @param point
     */
    @Around(value = "aroundAopLog()")
    public void aroundMethod(ProceedingJoinPoint point) {
        Object proceed = null;
        log.info("【环绕通知-前置执行】,请求：方法={} 参数={},",point.getSignature().getName(),point.getArgs());
        try {
             proceed = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        log.info("【环绕通知-后置执行】,响应：参数={}",proceed.toString());
    }


    @Before(value = "beforeAopLog()")
    public void beforeMethod(){
        log.info("【前置通知-前置执行】");
    }

    @After(value = "afterAopLog()")
    public void afterMethod(){
        log.info("【后置通知-后置执行】");
    }
}
