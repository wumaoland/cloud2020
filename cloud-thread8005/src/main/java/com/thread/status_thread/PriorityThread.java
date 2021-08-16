package com.thread.status_thread;

/**
 * 测试线程优先级，优先级越大被cpu调用的概率越大，最大为10，最小为1，平常为5
 */
public class PriorityThread {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"主线程");
        MyPriorityThread myPriorityThread = new MyPriorityThread();
        Thread thread = new Thread(myPriorityThread,"线程一");
        Thread thread2 = new Thread(myPriorityThread,"线程二");
        Thread thread3 = new Thread(myPriorityThread,"线程三");
        Thread threa4 = new Thread(myPriorityThread,"线程四");

        Thread.sleep(1000);
        thread.start();
        System.out.println("线程一优先级："+thread.getPriority());

        thread2.setPriority(10);
        thread2.start();
        System.out.println("线程二优先级："+thread2.getPriority());

        thread3.setPriority(2);
        thread3.start();
        System.out.println("线程三优先级："+thread3.getPriority());

        threa4.setPriority(5);
        threa4.start();
        System.out.println("线程四优先级："+threa4.getPriority());

    }
}
class MyPriorityThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始执行了");
    }
}