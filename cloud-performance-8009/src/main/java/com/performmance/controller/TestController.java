package com.performmance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring默认单例模式读取配置文件，配置文件只会读取一次，所以不要去修改读取的属性内容，修改一次后就不会改变
 *
 * 如果非要用多例模式，需要在类上加@Scope("prototype") 注解，但是每个关联到的类都要加才会生效
 */

@RestController
@Scope("prototype")
public class TestController {
    @Autowired
    private TestConfigService testConfigService;

    @GetMapping("/test")
    public String test() {
        return testConfigService.test();
    }


    @GetMapping("/testConfig")
    public String testConfig() {
        return testConfigService.testConfig();
    }


}