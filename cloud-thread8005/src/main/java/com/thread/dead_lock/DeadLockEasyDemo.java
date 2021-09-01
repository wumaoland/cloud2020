package com.thread.dead_lock;

import java.util.concurrent.TimeUnit;

public class DeadLockEasyDemo {
    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        new Thread(() -> {
            synchronized (a) {
                System.out.println("I get a");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("I want get b");
                synchronized (b) {
                    System.out.println("I get b");
                }
            }

        }).start();
        new Thread(() -> {
            synchronized (b) {
                System.out.println("I get b");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("I want get a");
                synchronized (a) {
                    System.out.println("I get a");
                }
            }

        }).start();
    }
}
