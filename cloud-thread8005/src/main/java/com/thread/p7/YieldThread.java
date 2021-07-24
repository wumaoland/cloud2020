package com.thread.p7;

/**
 * 测试线程礼让
 * 礼让不一定成功，看cpu心情
 */
public class YieldThread {
    public static void main(String[] args) {
        YieldThreadDemo yieldThreadDemo = new YieldThreadDemo();
        new Thread(yieldThreadDemo,"a").start();
        new Thread(yieldThreadDemo,"b").start();
    }
}
class YieldThreadDemo implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程开始");
        Thread.yield();//开始礼让
        System.out.println(Thread.currentThread().getName()+"线程结束");
    }
}