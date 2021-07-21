package com.thread.p7;

/**
 * 总结：
 * 标志位停止线程就是让run()方法里的代码不再执行
 */
public class StatusThread implements Runnable {
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
        StatusThread statusThread = new StatusThread();
        new Thread(statusThread).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
            if (i == 666) {
                statusThread.stop();
            }
        }
    }
}
