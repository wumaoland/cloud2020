package com.thread.pc_mode_thread;

import com.thread.threadpool.ThreadPoolThread;

/**
 * TODO 这节有问题，后续需研究
 * 测试：生产者消费者模型-->利用缓冲区解决：信号灯法,
 */
public class FlagThread {
    public static void main(String[] args) throws InterruptedException {
        new ThreadPoolThread().wait();
    }
}
