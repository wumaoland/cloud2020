package com.thread.three_assist;

import org.springframework.ui.context.Theme;

import java.util.concurrent.CountDownLatch;

/**
 * 减法计数器
 */
public class CountDownLauchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"get out");
                countDownLatch.countDown();//数量减一
            }).start();
        }
        //等待计数器归零，然后再向下执行
        countDownLatch.await();
        System.out.println("我关门了");
    }
}

