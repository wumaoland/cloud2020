package com.thread.pc_tradition_fake_noity;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock的生产者消费者的问题
 * 解决虚假唤醒，不能if去判断是否等待，需要循环判断，如while(xx!=sss)
 * 使用：   创建监视器 Condition condition = lock.newCondition(), condition.await() 等待，condition.signalAll()唤醒
 */
public class C {
    public static void main(String[] args) {
        D d = new D();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    d.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    d.decincr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "E").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    d.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "F").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    d.decincr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

    }
}

class D {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    int nums = 0;

    public void incr() throws InterruptedException {
        lock.lock();

        try {
            //判断等待,让其他线程跑
            while (nums != 0) {
                condition.await();
            }
            //业务
            nums++;
            System.out.println(Thread.currentThread().getName() + "==>" + nums);
            //通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }


    }

    public void decincr() throws InterruptedException {
        lock.lock();
        try {
            //判断等待,让其他线程跑
            while (nums == 0) {
                condition.await();
            }
            //业务
            nums--;
            System.out.println(Thread.currentThread().getName() + "==>" + nums);
            //通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

}