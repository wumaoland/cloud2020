package com.example.cloudcache8019;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import site.leewei.service.GirlFriednService;

@SpringBootApplication
@EnableCaching
public class CloudCache8019Application implements CommandLineRunner {
    @Autowired
    private GirlFriednService girlFriednService;

    public static void main(String[] args) {
        SpringApplication.run(CloudCache8019Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 调用打招呼方法
        girlFriednService.say();
    }

}
