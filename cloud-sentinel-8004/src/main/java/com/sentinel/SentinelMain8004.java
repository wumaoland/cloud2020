package com.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @ClassName SentinelMain8004
 * @Description TODO
 * @Author lw
 * @Date 2021/3/25 0025 22:52
 * @Vsersion 1.0
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SentinelMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(SentinelMain8004.class,args);
    }
}
