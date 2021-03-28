package com.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * websocket协议是基于TCP的一种新的网络协议。它实现了浏览器与服务器之间的全双工通信--允许服务器主动向客户端发送消息。
 * websocket是一种持久性协议，http是非持久协议
 */
@SpringBootApplication
public class WebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketApplication.class, args);
    }

}
