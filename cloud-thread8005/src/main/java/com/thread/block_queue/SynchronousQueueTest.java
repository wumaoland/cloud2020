package com.thread.block_queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列
 * 1.同步队列不存储元素
 * 2.往同步队列放入元素，必须取出这个元素才能放入下一个元素
 */
public class SynchronousQueueTest {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"投放了1");
                queue.put("1");
                System.out.println(Thread.currentThread().getName()+"投放了2");
                queue.put("2");
                System.out.println(Thread.currentThread().getName()+"投放了3");
                queue.put("3");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"投放线程").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"获取了"+queue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "获取了" + queue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"获取了"+queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"获取线程").start();
    }
}
