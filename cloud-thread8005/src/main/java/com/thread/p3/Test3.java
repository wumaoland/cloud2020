package com.thread.p3;

import org.springframework.web.bind.annotation.GetMapping;

public class Test3 {
    public static void main(String[] args) {
       /* MyThread3 myThread3 = new MyThread3();
        Thread thread = new Thread(myThread3);
        thread.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("main3线程执行：" + i);
        }*/


       //简洁创建线程
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("线程4执行：" + i);
                }
            }
        });
        thread4.start();

        //模拟10个线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println("线程：" + Thread.currentThread().getName());
            }).start();
        }


    }
}
