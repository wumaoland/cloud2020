package com.thread.p1;

/**
 * 使用继承Thread的方法创建线程
 */
public class Mythread extends Thread {
    @Override
    public void run() {

        for (int i = 0; i < 1000; i++) {
            System.out.println("Thread1线程执行：" + i);
        }
    }
}
