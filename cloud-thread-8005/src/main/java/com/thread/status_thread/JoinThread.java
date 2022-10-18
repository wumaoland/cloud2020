package com.thread.status_thread;

public class JoinThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("线程vip来了" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //启动vip线程
        JoinThread joinThread = new JoinThread();
        Thread thread = new Thread(joinThread);
        thread.start();

        //启动主线程
        for (int i = 0; i < 1000; i++) {
            if (i == 200) {
                thread.join();//vip线程插队，如果vip线程前面没有跑完，会在这里跑完了，然后主线程才会执行
            }
            System.out.println("main"+i);
        }
    }
}
