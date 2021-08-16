package com.thread.status_thread;

/**
 * 总结：
 * 标志位停止线程就是让run()方法里的代码不再执行
 */
public class StopThread implements Runnable {
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("线程该停止了" + i++);
        }
    }

    public void stop() {
        this.flag = false;
    }

    public static void main(String[] args) {
        StopThread stopThread = new StopThread();
        new Thread(stopThread).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
            if (i == 666) {
                stopThread.stop();
            }
        }
    }
}
