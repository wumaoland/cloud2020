package com.demo.basic;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "MyConsumerGroup",topic = "TestTopic",consumeMode = ConsumeMode.CONCURRENTLY,messageModel = MessageModel.BROADCASTING)
public class SpringConsumer implements RocketMQListener<String> {


    @Override
    public void onMessage(String message) {
        System.out.println("8001服务收到来自生产者8003的消息了："+message);
    }
}
