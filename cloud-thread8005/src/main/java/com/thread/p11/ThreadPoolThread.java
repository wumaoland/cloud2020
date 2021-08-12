package com.thread.p11;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池测试
 */
public class ThreadPoolThread {
    public static void main(String[] args) throws InterruptedException {
        //获取CPU的核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        //创建线程池服务
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //放入四个线程
        executorService.execute(new MyThread());
        executorService.execute(new MyThread());
        executorService.execute(new MyThread());
        executorService.execute(new MyThread());

        //关闭链接
        executorService.shutdown();

    }
}

class MyThread implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}