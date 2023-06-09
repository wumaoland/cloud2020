package com.mqtt.configuration;

import com.fasterxml.uuid.Generators;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MqttClientConfig {

    @Value("${config.mqtt.host}")
    private String host;
    @Value("${config.mqtt.username}")
    private String username;
    @Value("${config.mqtt.password}")
    private String password;
    @Value("${config.mqtt.topic}")
    private String topic;

    //private MqttClient asYncClient = null;

    private MqttClient mqttClient = null;

    @Bean
    public void initMqttClient() {
        try {
            String clientid = Generators.timeBasedGenerator().generate().toString();
            // MQTT的连接设置
            MqttConnectOptions options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(false);
            // 设置连接的用户名
            options.setUserName(username);
            // 设置连接的密码
            options.setPassword(password.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            //是否自动重连
            options.setAutomaticReconnect(true);
            //设置“遗嘱”消息的话题，若客户端与服务器之间的连接意外中断，服务器将发布客户端的“遗嘱”消息
            options.setWill("willTopic/G0001","服务离线".getBytes(),2,false);

            /*
            //同步阻塞式客户端
            // host为主机名，test为clientid即连接MQTT的客户端ID，一般以客户端唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(host, clientid, new MemoryPersistence());
            // 设置回调函数
            client.setCallback(new MqttPushClienCallback());
            client.connect(options);
            //订阅消息
            if (topic.contains(";")) {
                client.subscribe(topic.split(";"));
            } else {
                client.subscribe(topic);
            }*/

            //同步阻塞式客户端
            mqttClient = new MqttClient(host, clientid, new MemoryPersistence());
            //这里可定义回执内容 代替new MqttPushClienCallback()
            //mqttClient.setCallback(new MqttPushClienCallback());
            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {
                    try {
                        log.info("MQTT连接断开，发起重连......");
                        log.info("............正在连接............");
                        //重新初始化客户端连接
                        initMqttClient();
                        //心跳主题
                        mqttClient.subscribe("mqtt/face/heartbeat");
                        //其他主题
                        //mqttClient.subscribe("mqtt/face/by_ing112233/Rec");
                        //mqttClient.subscribe("mqtt/face/by_ing112233/Snap");
                        //mqttClient.subscribe("mqtt/face/by_ing112233/Ack");
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    log.info(".......接收到mqtt信息.......");
                    log.info("topid：{}", topic);
                    log.info("mqttMessage：{}", mqttMessage.getPayload());
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    System.out.println("消息处理完毕");
                }
            });

            mqttClient.connect(options);

            //初始化订阅主题，如设备在线
            if (topic.contains(";")) {
                String[] split = topic.split(";");
                int[] qos = new int[split.length];
                for (int i = 0; i < qos.length; ++i) {
                    qos[i] = 1;
                }
                mqttClient.subscribe(split, qos);
            } else {
                mqttClient.subscribe(topic, 2);
            }
            System.out.println("初始化完成是否连接："+mqttClient.isConnected());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 订阅主题
     *
     * @param topic 主题
     */
    public void subTopic(String topic) throws MqttException {
        subTopic(topic, 1);
    }

    /**
     * 订阅主题
     */
    public void subTopic(String topic, int qos) throws MqttException {
        mqttClient.subscribe(topic, qos);

    }

    /**
     * 发送消息
     */
    public void pushMessage(String topic,String message) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(message.getBytes());
        mqttClient.subscribe(topic);
        mqttClient.publish(topic,mqttMessage);
    }
}