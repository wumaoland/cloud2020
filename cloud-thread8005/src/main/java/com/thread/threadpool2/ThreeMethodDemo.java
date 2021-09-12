package com.thread.threadpool2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executor 工具类，三大方法
 * newSingleThreadExecutor();//单个线程
 * newFixedThreadPool(5);//创建一个固定线程池的大小
 * newCachedThreadPool();//可伸缩的，遇强则强，遇弱则弱
 */
public class ThreeMethodDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();//单个线程
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//创建一个固定线程池的大小
        //ExecutorService threadPool = Executors.newCachedThreadPool();//可伸缩的，遇强则强，遇弱则弱
        try {
            for (int i = 0; i < 100; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池用完一定要释放
            threadPool.shutdown();
        }
    }
}
