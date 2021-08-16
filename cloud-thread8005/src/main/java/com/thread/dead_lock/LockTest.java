package com.thread.dead_lock;
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
   /*     List<Integer> strings = Arrays.asList(1, 2, 3);
        strings.stream().filter(i -> i >= 2).peek(System.out::println).count();*/
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
                    System.out.println(tickets--);
                } else {
                    break;
                }
            }
        } finally {
            lock.unlock();
        }
    }
}
