package com.thread.threadpool2;

import java.util.concurrent.*;

/**
 * 使用线程池创建线程
 * 1.线程池七个参数
 * 2.四大拒绝策略：
 * new ThreadPoolExecutor.AbortPolicy()  //线程拒绝策略：当前这个策略是当超出最大可承载线程数(最大运行线程数+阻塞队列的长度=8)，
 * 就抛出异常,三大方法默认都是这个策略
 *
 * new ThreadPoolExecutor.CallerRunsPolicy()  //线程拒绝策略：哪来的回哪去，例如下面第九个线程就交个main线程去处理了
 *
 * new ThreadPoolExecutor.DiscardPolicy() //队列满了，丢掉任务，不抛出异常
 *
 * new ThreadPoolExecutor.DiscardOldestPolicy();//队列满了，尝试和最早的竞争，也不会抛出异常！
 * 被拒绝任务的处理程序，丢弃最早的未处理请求，然后重试execute ，除非执行程序关闭，在这种情况下任务将被丢弃
 *
 * 3.最大线程数确定
 * 线程池最大线程数如何定义
 * CPU密集型 几个逻辑处理器就是几，可以保持CPU的效率最高，比如我的电脑就是6核12个逻辑处理器，最大线程数可以填12
 * IO密集型  数量 >判断你程序中十分耗IO的线程数，一般是两倍
 */
public class SevenArgsDemo {
    public static void main(String[] args) {
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//单个线程
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//创建一个固定线程池的大小
        //ExecutorService threadPool = Executors.newCachedThreadPool();//可伸缩的，遇强则强，遇弱则弱
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,//常用核心运行线程数
                5,//最大运行线程数
                3,//非核心线程允许闲置时间
                TimeUnit.SECONDS,//闲置时间单位
                new LinkedBlockingQueue<>(3),//候客区，阻塞队列放三个线程
                Executors.defaultThreadFactory(),//线程创建工厂
                new ThreadPoolExecutor.AbortPolicy()//线程拒绝策略：当前这个策略是当超出最大可承载线程数(最大运行线程数+阻塞队列的长度=8)，就抛出异常,三大方法默认都是这个策略
        );
        //new ThreadPoolExecutor.CallerRunsPolicy() //线程拒绝策略：哪来的回哪去，例如下面第九个线程就交个main线程去处理了
        //new ThreadPoolExecutor.DiscardPolicy() //线程拒绝策略：队列满了，丢掉任务，不抛出异常
        //new ThreadPoolExecutor.DiscardOldestPolicy();//队列满了，丢弃最早的未处理请求，然后重试execute,也不会抛出异常！
        try {
            for (int i = 1; i <= 8; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + ":ok");
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
