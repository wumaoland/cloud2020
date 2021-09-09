package com.thread.block_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 * 1.抛出异常
 * 2.不抛出异常
 * 3.阻塞等待
 * 4.超时等待
 */
public class BlockQueueTest {
    public static void main(String[] args) throws InterruptedException {
        test4();
    }

    /**
     * 抛出异常
     */
    public static void test1() {
        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(3);
        System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));
        //System.out.println(queue.add("d")); //放入超出范围的值
        System.out.println("-----取出------");
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        //System.out.println(queue.remove()); //取出超出范围的值
    }

    /**
     * 不抛出异常
     */
    public static void test2() {
        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(3);
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
        //System.out.println(queue.offer("d"));//放入超出范围的值
        System.out.println("-----取出------");
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        //System.out.println(queue.poll());//取出超出范围的值
    }

    /**
     * 阻塞等待
     */
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(3);
        queue.put("a");
        queue.put("b");
        queue.put("c");
        //queue.put("d");//放入超出范围的值
        System.out.println("-----取出------");
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());//取出超出范围的值
    }

    /**
     * 超时等待
     */
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(3);
        System.out.println(queue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(queue.offer("b", 2, TimeUnit.SECONDS));
        System.out.println(queue.offer("c", 2, TimeUnit.SECONDS));
        System.out.println(queue.offer("d", 2, TimeUnit.SECONDS));//放入超出范围的值,等待2秒后结束
        System.out.println("-----取出------");
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        //System.out.println(queue.poll(2,TimeUnit.SECONDS));//取出超出范围的值，等待2秒后结束
    }

}
