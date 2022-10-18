package com.thread.threadpool2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class ExecutorPoolDemo {

    @GetMapping("/executor/{flag}")
    public void executorPool(@PathVariable("flag") String flag) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(()->{
            System.out.println(flag);
        });

    }
}
