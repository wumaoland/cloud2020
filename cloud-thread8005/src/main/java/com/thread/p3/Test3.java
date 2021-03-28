package com.thread.p3;

public class Test3 {
    public static void main(String[] args) {
        MyThread3 myThread3 = new MyThread3();
        Thread thread = new Thread(myThread3);
        thread.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("main3线程执行：" + i);
        }


        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("线程4执行：" + i);
                }
            }
        });
        thread4.start();

    }
}
