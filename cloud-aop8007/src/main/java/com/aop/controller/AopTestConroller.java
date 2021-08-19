package com.aop.controller;

import com.aop.annotation_method.AfterAopLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AopTestConroller {

    //@BeforeAopLog
    //@AroundAopLog
    @AfterAopLog
    @GetMapping("/testAop/{code}")
    public String testAop(@PathVariable("code") String code) {
        log.info("业务方法执行");
        return "业务方法执行成功";
    }
}
