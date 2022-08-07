package com.thread.lock_8_demo;

import java.util.concurrent.TimeUnit;

/**
 * 记住syncronized（非公平锁，锁的是方法的调用者）：
 * 1.只要锁的不是同一个对象，看执行时间，谁执行时间短谁先获得锁，如果执行时间相同执行顺序就是随机的
 * 2.static 锁的是class 不是对象
 * 3.锁的同一个对象,就看谁先拿到这个对象谁就先执行
 */
public class Test2 {
    public static void main(String[] args) {
        Cellphone phone = new Cellphone();
        Cellphone phone1 = new Cellphone();

        new Thread(() -> {
            phone1.call();
        }).start();
        new Thread(() -> {
            phone.sms();
        }).start();




       /* try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }
}

class Cellphone {
    //锁的对象是方法的调用者
    //两个方法用的是同一个锁，谁先拿到谁先执行
    public static synchronized void sms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public static synchronized void call() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("打电话");
    }
}


