package com.thread.p2;

/**
 * 并发抢票问题
 */
public class MyRunnable implements Runnable {
    //票数
    private int ticktNums = 10;
    @Override
    public void run() {
        while (true){
            if (ticktNums <= 0) {
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程"+Thread.currentThread().getName()+"拿到了"+ticktNums--+"票");
        }
    }
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable, "小明").start();
        new Thread(myRunnable,"老师").start();
        new Thread(myRunnable,"黄牛").start();
    }
}
