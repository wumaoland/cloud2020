package com.mqtt.configuration;

import com.alibaba.fastjson.JSONObject;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/***
 * 因为在MqttClinetConfig中已经初始化该类，该类遗弃
 */
@Component
public class MqttPushClienCallback implements MqttCallback {

    private static Logger logger = LoggerFactory.getLogger(MqttPushClienCallback.class);

    @Autowired
    private MqttClientConfig mqttClientConfig;

    @Override
    public void connectionLost(Throwable throwable) {
        try {
            logger.info("MQTT连接断开，发起重连......");
            logger.info("............正在连接............");
            mqttClientConfig.initMqttClient();
            //心跳主题
            mqttClientConfig.subTopic("mqtt/face/heartbeat");
            //其他主题
            mqttClientConfig.subTopic("mqtt/face/by_ing112233/Rec");
            mqttClientConfig.subTopic("mqtt/face/by_ing112233/Snap");
            mqttClientConfig.subTopic("mqtt/face/by_ing112233/Ack");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收mqttf服务信息
     */
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        System.out.println(".......接收到mqtt信息.......");
        logger.info("topid：{}", topic);
        logger.info("mqttMessage：{}", mqttMessage.getPayload());
    }

    /**
     * 若消息成功发送完成回调用该方法
     * @param iMqttDeliveryToken
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("当前发送消息收到回执："+iMqttDeliveryToken.getResponse()+";"+ JSONObject.toJSONString(iMqttDeliveryToken.getTopics())+";"+iMqttDeliveryToken.getUserContext()+";"+iMqttDeliveryToken.getClient());
        System.out.println("消息已成功发送完毕");
    }


}