package com.mqtt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Component
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30) // v3 地址
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mqtt.controller.MqttController")) // 设置扫描路径
                .build();
    }
}
