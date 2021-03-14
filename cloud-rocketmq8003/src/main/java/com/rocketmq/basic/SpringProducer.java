package com.rocketmq.basic;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName Producer
 * @Description TODO
 * @Author lw
 * @Date 2021/3/13 0013 22:28
 * @Vsersion 1.0
 **/
@Component
public class SpringProducer {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage(String topic,String msg){
        this.rocketMQTemplate.convertAndSend(topic,msg);
    }
}
