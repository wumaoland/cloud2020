package com.rocketmq.basic;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName SpringConsumer
 * @Description  MessageModel.BROADCASTING是广播模式；MessageModel.CLUSTERING是集群消费模式
 * @Author lw
 * @Date 2021/3/13 0013 22:30
 * @Vsersion 1.0
 **/
@Component
@RocketMQMessageListener(consumerGroup = "MyConsumerGroup",topic = "TestTopic",consumeMode = ConsumeMode.CONCURRENTLY,messageModel = MessageModel.BROADCASTING)
public class SpringConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.println("8003服务收到来自生产者8003的消息了："+message);
    }
}
