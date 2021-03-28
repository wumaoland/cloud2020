package com.thread.p2;

/**
 * 测试线程的执行是随机的
 */
public class Test2 {
    public static void main(String[] args) {
        MyThread2 myThread2 = new MyThread2();
        myThread2.start();

        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("main2线程执行：" + i);
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
