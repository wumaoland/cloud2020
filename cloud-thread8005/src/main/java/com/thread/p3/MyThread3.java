package com.thread.p3;


/**
 * 线程对象有两个构造方法，
 * 1.无参构造方法对应继承Thread类，重写run方法实现
 * 2.有参构造方法对应实现runnable接口，实现run方法,实质是一样的。
 */
public class MyThread3 implements Runnable {
    @Override
    public void run() {

        for (int i = 0; i < 1000; i++) {
            System.out.println("Thread3线程执行：" + i);
        }
    }
}
