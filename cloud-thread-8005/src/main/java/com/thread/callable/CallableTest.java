package com.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable实现线程可以抛出异常，也有返回值
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread myThread = new MyThread();
        FutureTask<String> stringFutureTask = new FutureTask<>(myThread);
        new Thread(stringFutureTask,"A").start();//只会打印一遍“我tm执行了” ，因为会被缓存
        new Thread(stringFutureTask,"B").start();
        System.out.println(stringFutureTask.get());//这个get方法会阻塞，把他放到最后执行，或者使用异步通信来处理
    }
}

class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("我tm执行了");
        return "我是callable线程的返回值";
    }
}
