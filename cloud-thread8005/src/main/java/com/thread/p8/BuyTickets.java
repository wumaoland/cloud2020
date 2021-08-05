package com.thread.p8;

import java.util.ArrayList;

/**
 * 添加同步锁，锁方法
 * 弊端是锁的资源太多，影响性能
 */
public class BuyTickets {
    public static void main(String[] args) {
        TicketsThread ticketsThread = new TicketsThread();
        Thread thread = new Thread(ticketsThread, "苦逼的你");
        Thread thread2 = new Thread(ticketsThread, "牛逼的我");
        Thread thread3 = new Thread(ticketsThread, "可恶的黄牛党");
        thread.start();
        thread2.start();
        thread3.start();


    }
}

class TicketsThread implements Runnable {
    private int tickets = 10;
    private Boolean flag = true;

    @Override
    public void run() {
        //买票
        while (flag) {
            try {
                buyTicket();
                //模拟延时
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //锁住方法
    private synchronized void buyTicket() throws InterruptedException {
        if (tickets <= 0) {
            flag = false;
            return;
        }

        //买票
        System.out.println(Thread.currentThread().getName() + "拿到票" + tickets--);
    }
}