package com.thread.p9;
/**
 * 可重入锁测试,工作中可用
 */

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
       /* LockThread lockThread = new LockThread();
        new Thread(lockThread).start();
        new Thread(lockThread).start();
        new Thread(lockThread).start();*/

        List<Integer> strings = Arrays.asList(1, 2, 3);
        strings.stream().filter(i -> i >= 2).peek(System.out::println).count();
    }

}

class LockThread implements Runnable {
    private final ReentrantLock lock = new ReentrantLock();
    int tickets = 10;

    @Override
    public void run() {

        while (true) {
            try {
                lock.lock();
                if (tickets > 0) {
                /*    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    System.out.println(tickets--);
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("加锁失败");
            } finally {
                lock.unlock();
            }
        }
    }
}
