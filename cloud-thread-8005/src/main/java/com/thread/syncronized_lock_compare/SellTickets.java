package com.thread.syncronized_lock_compare;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 回顾多线程并发的处理，synchronized与Lock的对比
 * synchronized是全自动，Lock是手动的
 */
public class SellTickets {
    public static void main(String[] args) {
        Tickets tickets = new Tickets();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                tickets.sell();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                tickets.sell();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                tickets.sell();
            }
        }).start();
    }
}

class Tickets {
    int ticketNums = 30;
    private final Lock lock = new ReentrantLock();

    public void sell() {
        lock.lock();
        try {
            if (ticketNums > 0) {
                System.out.println("现在已经卖到第" + (ticketNums--) + "票," + "剩余" + ticketNums + "张票");
            }
        } catch (Exception e) {
            System.out.println("业务执行异常");
        }finally {
            lock.unlock();
        }
    }
}
