package com.aop.annotation_method.practice;

import com.aop.controller.AopTestConroller;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;

@Component
@Aspect
public class UpdateProductAspect {
    //切点
    @Pointcut(value = "@annotation(com.aop.annotation_method.practice.UpdateProductAnnoation)")
    public void  updateProductPoint(){

    }
    //环绕增强
    @Around(value = "updateProductPoint()&&@annotation(updateProductAnnoation)")
    public void updateProduct(ProceedingJoinPoint joinPoint, UpdateProductAnnoation updateProductAnnoation) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        /**
         * 改变传参的对象
         */
        Product2 product2 = (Product2) joinPoint.getArgs()[0];
        product2.setProductName(updateProductAnnoation.name());
        product2.setPrice(new BigDecimal(updateProductAnnoation.price()));
        /**
         * 这里故意用反射构造方法重新构造一个新的实例对象
         */
//        Class<?> aClass = Product2.class;
//        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class, BigDecimal.class);
//        String name = updateProductAnnoation.name();
//        Product2 o = (Product2)declaredConstructor.newInstance(updateProductAnnoation.name(), new BigDecimal(updateProductAnnoation.price()));
        try {
            System.out.println("开始执行目标方法");
            Product2 proceed = (Product2) joinPoint.proceed();//执行目标方法
            System.out.println(String.format("开始执行后置通知%s",proceed));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
