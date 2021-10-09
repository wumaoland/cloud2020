package com.mqtt.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Api(tags="MQTT消息实体类")
//@ApiModel("消息实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyMsg {
    @ApiModelProperty(value = "消息主体", required = false)
    private String topic;
    @ApiModelProperty(value = "消息id", required = false)
    private Long msgId;
    @ApiModelProperty(value = "消息内容", required = false)
    private String sendMsg;
    @ApiModelProperty(value = "发送人id", required = false)
    private Long sendUserId;
    @ApiModelProperty(value = "发送人姓名", required = false)
    private String sendUserName;
    @ApiModelProperty(value = "接收人id", required = false)
    private Long reciveUserId;
    @ApiModelProperty(value = "发送人姓名", required = false)
    private String reciveUserName;

}
