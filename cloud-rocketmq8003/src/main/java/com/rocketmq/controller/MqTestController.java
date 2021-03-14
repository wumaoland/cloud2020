package com.rocketmq.controller;

import com.rocketmq.basic.SpringProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName MqTestController
 * @Description TODO
 * @Author lw
 * @Date 2021/3/13 0013 22:38
 * @Vsersion 1.0
 **/
@RestController
public class MqTestController {
    private final String topic="TestTopic";

    @Resource
    private SpringProducer producer;

    @GetMapping("/sendMsg")
    public void testRocketMq(String msg){
        this.producer.sendMessage(topic,msg);
    }
}
