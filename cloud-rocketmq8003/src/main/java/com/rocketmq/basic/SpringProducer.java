package com.rocketmq.basic;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName Producer
 * @Description 使用RocketMQTemplate 是不需要在代码里注入mq的地址相关配置的，只需要在配置文件里写明
 * @Author lw
 * @Date 2021/3/13 0013 22:28
 * @Vsersion 1.0
 **/
@Component
@Slf4j
public class SpringProducer {
    //超时时间
    private static final int timeout=4*24*60*60*1000;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage(String topic,String msg){
        this.rocketMQTemplate.convertAndSend(topic,msg);
        log.debug("已发送普通消息:message={}",msg);
    }

    public void sendDelayMessage(String topic,String msg,int delayLevel){
        this.rocketMQTemplate.syncSend(topic, MessageBuilder.withPayload(msg).build(), timeout, delayLevel);
        log.info("已同步发送延时消息(等级={}) :message = {}",delayLevel,msg);
    }
}
