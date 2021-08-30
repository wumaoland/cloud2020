package com.aop.controller;

import com.aop.annotation_method.AfterThrowingAopLog;
import com.aop.annotation_method.AroundAopLog;

import com.aop.annotation_method.RedissonApiLimit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@Slf4j
public class AopTestConroller {
    //@BeforeAopLog
    //@AroundAopLog
    //@AfterAopLog
    //@AfterReturningAopLog
    //@AfterThrowingAopLog
    @RedissonApiLimit(tps = 1,rateTime = 10)
    @GetMapping("/testAop/{code}")
    public String testAop(@PathVariable("code") String code) {
        log.info("业务方法执行");
        return "业务方法执行成功";
    }

    public static void main(String[] args) {
        Set<String> objects = new HashSet<>();
        objects.add("1");
        objects.add("2");
        System.out.println(objects.stream().filter(k->k.equals(String.valueOf(3))).findFirst().orElse(null));
    }
}
