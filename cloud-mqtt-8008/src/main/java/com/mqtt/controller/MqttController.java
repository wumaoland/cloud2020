package com.mqtt.controller;

import com.mqtt.configuration.MqttClientConfig;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttController {
    @Autowired
    private MqttClientConfig mqttClientConfig;

    @GetMapping("/send/{topic}/{msg}")
    public String sendMsg(@PathVariable("topic") String topic,@PathVariable("msg") String msg) throws MqttException {
        mqttClientConfig.pushMessage(topic, msg);
        return "消息发送成功";
    }
}
