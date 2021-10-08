package com.mqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class MqttMain8008 {
    public static void main(String[] args) {
        SpringApplication.run(MqttMain8008.class,args);
    }
}
