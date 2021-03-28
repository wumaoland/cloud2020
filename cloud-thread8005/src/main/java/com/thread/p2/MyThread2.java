package com.thread.p2;

/**
 * 测试线程的执行是随机的
 */
public class MyThread2 extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("MyThread2线程执行：" + i);
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
