package com.thread.p8;

/**
 * 线程同步
 */
public class SynThread {
    public static void main(String[] args) throws InterruptedException {
        Thread addThread = new Thread(new Add());
        Thread decThread = new Thread(new Dec());
        addThread.start();
        addThread.join();
        decThread.join();
        decThread.start();
        Thread.sleep(1000);
        System.out.println(Counter.a);
    }
}

class Counter {
    static int a = 0;
}

class Add implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Counter.a++;
        }
    }
}

class Dec implements Runnable {

    @Override
    public void run() {
        Counter.a--;
    }
}
