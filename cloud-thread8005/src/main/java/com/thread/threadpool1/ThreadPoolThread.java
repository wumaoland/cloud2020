package com.thread.threadpool1;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池测试
 */
public class ThreadPoolThread {
    public static void main(String[] args) throws InterruptedException {
        //获取CPU的核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        //创建线程池服务
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //放入四个线程
        executorService.execute(new MyThread(1));
        executorService.execute(new MyThread(2));
        executorService.execute(new MyThread(3));
        executorService.execute(new MyThread(4));

        //关闭链接
        executorService.shutdown();

    }
}

class MyThread implements Runnable {
    private static int nums=-1;
    private int threadNums;

    @Override
    public void run() {
        while (nums != threadNums) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
    public MyThread(int threadNums){
        this.threadNums=threadNums;
    }
}
