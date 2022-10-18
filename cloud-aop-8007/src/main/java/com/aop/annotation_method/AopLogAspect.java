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

    @Pointcut(value = "@annotation(com.aop.annotation_method.AfterReturningAopLog)")
    public void afterReturningAopLog() {
    }

    @Pointcut(value = "@annotation(com.aop.annotation_method.AfterThrowingAopLog)")
    public void afterThrowingAopLog() {

    }

    /**
     * 环绕通知
     *
     * @param point
     */
    @Around(value = "aroundAopLog()")
    public void aroundMethod(ProceedingJoinPoint point) {
        Object proceed = null;
        log.info("【环绕通知-前置执行】,请求：方法={} 参数={},", point.getSignature().getName(), point.getArgs());
        try {
            proceed = point.proceed();//执行目标方法
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        log.info("【环绕通知-后置执行】,响应：参数={}", proceed.toString());
        int a = 1;
    }

    /**
     * 前置通知
     */
    @Before("beforeAopLog() && @annotation(befireAopLog)")
    public void beforeMethod(BeforeAopLog befireAopLog) {
        log.info("【前置通知-前置执行】".concat(befireAopLog.name()));
    }

    /**
     * 后置通知(目标方法是否异常都执行)
     */
    @After(value = "afterAopLog()")
    public void afterMethod() {
        log.info("【后置通知-后置执行】");
    }

    /**
     * 后置正常通知(目标方法正常返回才会执行)
     */
    @AfterReturning(value = "afterReturningAopLog()")
    public  void afterReturningMethod(){
        log.info("【后置正常通知-后置正常执行】");
    }

    /**
     * 后置异常通知(目标方法执行异常才会执行)
     */
    @AfterThrowing(value = "afterThrowingAopLog()")
    public void afterThrowingMethod() {
        log.info("【后置异常通知】-后置异常执行");
    }

}
