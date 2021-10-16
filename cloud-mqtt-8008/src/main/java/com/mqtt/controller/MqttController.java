package com.mqtt.controller;

import com.mqtt.configuration.MqttClientConfig;
import com.mqtt.model.MyMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags="MQTT消息操作API")
@RestController
public class MqttController {
    @Autowired
    private MqttClientConfig mqttClientConfig;

    @ApiOperation(value = "发送消息", notes = "发送普通消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topic", value = "消费主题", required = true),
            @ApiImplicitParam(name = "msg", value = "发送的消息内容", required = false)
    })
    @GetMapping("/send/{topic}/{msg}")
    public String sendMsg(@PathVariable("topic") String topic,@PathVariable("msg") String msg) throws MqttException {
        mqttClientConfig.pushMessage(topic, msg);
        return "消息发送成功";
    }

    @ApiOperation(value = "Post方式传输消息")
    @PostMapping("/send")
    public String sendMsg(@RequestBody MyMsg myMsg) throws MqttException {
        mqttClientConfig.pushMessage(myMsg.getTopic(), myMsg.getSendMsg());
        return "消息发送成功";
    }



}
