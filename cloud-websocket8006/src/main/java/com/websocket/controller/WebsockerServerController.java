package com.websocket.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.websocket.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * webscoket主要服务类
 */
@Component
@ServerEndpoint("/websocket")
@Slf4j
public class WebsockerServerController {
    private Session session;
    private static Map<String,WebsockerServerController> webSocketSets = new ConcurrentHashMap<String,WebsockerServerController>();

    @OnOpen
    public void onOpen(Session session){
        try {
            log.info(String.format("客户端(%s)连接成功",session.getId()));
            this.session=session;
            webSocketSets.put(session.getId(),this);
            Message message = new Message();
            message.setLogin("1");
            message.setMsg(session.getId());
            sendMessage(this,JSON.toJSONString(message));
            this.sendOnlineList();
        } catch (IOException e) {
            log.error("连接IO异常");
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        Message reciveMsg = JSON.parseObject(message, Message.class);
        if (reciveMsg.getIsAll()) {
            for (String key : webSocketSets.keySet()) {
                WebsockerServerController websockerServerController = webSocketSets.get(key);
                this.sendMessage(websockerServerController,JSONObject.toJSONString(reciveMsg));
            }
        }else{
            for (String key : webSocketSets.keySet()) {
                WebsockerServerController websockerServerController = webSocketSets.get(key);
                if (reciveMsg.getToWho().equals(websockerServerController.session.getId())) {
                    this.sendMessage(websockerServerController,JSONObject.toJSONString(reciveMsg));
                }
            }
        }
        log.info(String.format("收到客户端(%s)消息：%s",session.getId(),message));
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        log.info(String.format("客户端(%s)断开连接",session.getId()));
        for (String key : webSocketSets.keySet()) {
            WebsockerServerController websockerServerController = webSocketSets.get(key);
            if (session.getId().equals(websockerServerController.session.getId())) {
                webSocketSets.remove(key);
            }
        }
        //this.sendOnlineList();
    }

    @OnError
    public void onError(Session session, Throwable error) throws IOException {
        log.error(String.format("客户端(%s)连接发生错误",session.getId()));
        for (String key : webSocketSets.keySet()) {
            WebsockerServerController websockerServerController = webSocketSets.get(key);
            if (session.getId().equals(websockerServerController.session.getId())) {
                webSocketSets.remove(key);
            }
        }
        //this.sendOnlineList();
    }


    public void sendMessage(WebsockerServerController websockerServerController,String message) throws IOException {
        websockerServerController.session.getBasicRemote().sendText(message);
    }

    /**
     * 当前在线列表广播
     */
    public void sendOnlineList() throws IOException {
        //广播在线会话给各个客户端
        List<String> onLineIds = new ArrayList<>();
        for (String key : webSocketSets.keySet()) {
            WebsockerServerController websockerServerController = webSocketSets.get(key);
            onLineIds.add(websockerServerController.session.getId());

        }
        for (String key : webSocketSets.keySet()) {
            WebsockerServerController websockerServerController = webSocketSets.get(key);
            Message message = new Message();
            message.setOnOlineIds(onLineIds);
            String s = JSONObject.toJSONString(message);
            this.sendMessage(websockerServerController,s);
        }

    }
}
