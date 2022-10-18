package com.thread.block_queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class BlockQueueController {
  static   LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<String>(1);

    /**
     * 测试只接受一个请求
     */
    @GetMapping("/blockQueue/{flag}")
    public void blockQueue(@PathVariable("flag") String flag)  {
        try {
            boolean offer = linkedBlockingQueue.offer(flag);
            if (offer ) {
                System.out.println("业务");
            }
        }catch (Exception e){
            log.error("出现错误");
        }finally {
            linkedBlockingQueue.poll();
        }
    }
}
