package com.thread.syncronized;

import java.util.ArrayList;

/**
 * 锁对象
 */
public class SynObjectThread {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> objects = new ArrayList<String>();
            for (int i = 1; i <= 10000; i++) {
                new Thread(()->{
                    synchronized (objects){
                        objects.add(Thread.currentThread().getName());
                    }
                }).start();
            }
        Thread.sleep(1000);
        System.out.println(objects.size());
    }
}
