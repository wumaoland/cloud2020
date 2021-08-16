package com.thread.syncronized;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 测试JUC并发包
 */
public class JucThread {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<String>();
        for (int i = 1; i <= 10000; i++) {
            new Thread(()->{
                copyOnWriteArrayList.add(Thread.currentThread().getName());
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(copyOnWriteArrayList.size());
    }
}
