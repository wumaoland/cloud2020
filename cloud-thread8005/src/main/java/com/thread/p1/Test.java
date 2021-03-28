package com.thread.p1;

public class Test {
    public static void main(String[] args) {
        System.out.println("JVM主线程开启");

        /**
         * 调用线程的start()方法来启动线程，启动线程的实质就是请求jvm运行相应的线程，
         * 这个线程具体在什么时候运行取决于调度器(Scheduler)
         * 注意：start()方法调用结束并不一定意味着线程开始运行
         * 如果开启多个线程，start()调用顺序并不一定就是线程的启动顺序
         */
        Mythread mythread = new Mythread();
        mythread.start();

        System.out.println("main1线程其他的代码....");
    }
}
