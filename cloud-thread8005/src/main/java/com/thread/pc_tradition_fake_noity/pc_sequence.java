package com.thread.pc_tradition_fake_noity;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用监视器实现：
 * 1.A执行完 唤醒B
 * 2.B执行完 唤醒C
 * 3.C执行完 唤醒A
 */
public class pc_sequence {
    public static void main(String[] args) {
        Sequence sequence = new Sequence();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sequence.A();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sequence.B();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sequence.C();
            }
        }, "C").start();
    }

}

class Sequence {
    private Lock lock = new ReentrantLock();
    private int nums = 1;
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();


    public void A() {
        lock.lock();
        try {
            //1等待
            while (nums != 1) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName().concat("==>AAAAAAAA"));
            nums = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void B() {
        lock.lock();
        try {
            //2等待
            while (nums != 2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName().concat("==>BBBBBBB"));
            nums = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void C() {
        lock.lock();
        try {
            //3等待
            while (nums != 3) {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName().concat("==>CCCCCCCC"));
            nums = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


}