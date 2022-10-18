package com.thread.unsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UnSafeMap {
    public static void main(String[] args) {
        //map是这样用的吗？不是，工作中不用hashmap
        //默认等价于？ new HashMap(16,0.75)  注：初始化容量16，负载因子0.75
        //需研究下ConcurrentHashMap的原理
        //Map<String, Object> map = new HashMap<>();
        Map<String, Object> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
