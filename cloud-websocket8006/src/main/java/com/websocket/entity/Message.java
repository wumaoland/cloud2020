package com.websocket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private Boolean isAll=true;

    private String login;

    private  String fromWho;

    private String toWho;

    private String msg;

    private List<String> onOlineIds;
}
