package com.thread.p4;

public class Thread4 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("这是学习p4线程" + i);
        }
    }

    public static void main(String[] args) {
        Thread4 thread4 = new Thread4();
        thread4.start();
        for (int i = 0; i < 20; i++) {
            System.out.println("这是p4线程的main线程" + i);
        }
    }
}
