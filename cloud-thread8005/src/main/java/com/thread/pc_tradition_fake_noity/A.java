package com.thread.pc_tradition_fake_noity;

public class A {
    public static void main(String[] args) {
        B b = new B();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    b.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    b.decincr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    b.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    b.decincr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();

    }
}

class B {
    int nums = 0;
    public  synchronized void incr() throws InterruptedException {
        //判断等待,让其他线程跑
        while (nums != 0) {
            this.wait();
        }
        //业务
        nums++;
        System.out.println(Thread.currentThread().getName()+"==>"+nums);
        //通知
        this.notifyAll();
    }

    public synchronized void decincr() throws InterruptedException {
        //判断等待,让其他线程跑
        while (nums == 0) {
            this.wait();
        }
        //业务
        nums--;
        System.out.println(Thread.currentThread().getName()+"==>"+nums);
        //通知
        this.notifyAll();
    }

}