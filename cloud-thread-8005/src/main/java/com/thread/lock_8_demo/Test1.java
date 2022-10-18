package com.thread.lock_8_demo;

import java.util.concurrent.TimeUnit;

/**
 * 记住syncronized（非公平锁）：
 * 1.只要锁的不是同一个对象，看执行时间，谁执行时间短谁先获得锁
 * 2.static 锁的是class 不是对象
 * 3.锁的同一个对象就看执行顺序
 */
public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone1 = new Phone();
        new Thread(() -> {
            phone.sms();
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone1.call();
        }).start();


    }
}

class Phone {
    public  synchronized void sms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized   void call() {
        System.out.println("打电话");
    }
}
