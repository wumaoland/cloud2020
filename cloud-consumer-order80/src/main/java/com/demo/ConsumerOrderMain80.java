package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName OrderMain80
 * @Description TODO
 * @Author lw
 * @Date 2021/3/3 0003 22:18
 * @Vsersion 1.0
 **/
//exclude = DataSourceAutoConfiguration.class 可以不配置数据源启动项目
@SpringBootApplication
public class ConsumerOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderMain80.class, args);
    }
}

