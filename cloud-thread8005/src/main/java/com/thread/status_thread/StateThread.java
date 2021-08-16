package com.thread.status_thread;

/**
 * 观测线程的状态
 */
public class StateThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
            for (int i = 0; i < 3; i++) {
                try {
                    System.out.println("阻塞前");
                    Thread.sleep(1000);
                    System.out.println("阻塞后");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("///////");
        });

        //观察状态(新生)
        Thread.State state = thread.getState();
        System.out.println("我在这里新生"+state);

        //观察状态(就绪,run)
        thread.start();
        state = thread.getState();
        System.out.println("我在这里运行"+state);

        //观察状态(阻塞)
        while (state != Thread.State.TERMINATED) {
            Thread.sleep(1000);
            state = thread.getState();
            System.out.println("我在这里阻塞"+state);
        }
    }
}
