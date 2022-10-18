package com.sse.controller;

import com.sse.server.SseEmitterServer;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@CrossOrigin
@RestController
@RequestMapping("/sse")
public class MsgController {

    @GetMapping("/connect/{userId}")
    public SseEmitter getConnect(@PathVariable("userId") String userId) {
        return SseEmitterServer.connect(userId);
    }

    @GetMapping("/send/{userId}/{msg}")
    public void senMsg(@PathVariable("userId") String userId,@PathVariable("msg") String msg){
        SseEmitterServer.sendMessage(userId,msg);
    }
}
