package com.thread.three_assist;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();//获取信号量
                    System.out.println("线程"+Thread.currentThread().getName()+"获得车位，开始停车");
                    TimeUnit.SECONDS.sleep(2);//执行业务
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();//释放信号量
                }
            },String.valueOf(i)).start();
        }
    }
}
