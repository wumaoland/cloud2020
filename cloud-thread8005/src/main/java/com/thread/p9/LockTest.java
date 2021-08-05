package com.thread.p9;
/**
 * 可重入锁测试,工作中可用
 */

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        LockThread lockThread = new LockThread();
        new Thread(lockThread).start();
        new Thread(lockThread).start();
        new Thread(lockThread).start();
    }

}

class LockThread implements Runnable {
    private final ReentrantLock lock = new ReentrantLock();
    int tickets = 10;

    @Override
    public void run() {
        try {
            while (true) {
                lock.lock();
                if (tickets > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(tickets--);
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("加锁失败");
        } finally {
            lock.unlock();
        }

    }
}
