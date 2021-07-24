package com.thread.p7;


import java.text.SimpleDateFormat;
import java.util.Date;

public class SleepThread {
    public static void main(String[] args) throws InterruptedException {
        //模拟倒计时
        for (int i = 10; i > 0; i--) {
            System.out.println(i);
            //模拟延迟1秒
            Thread.sleep(1000);
        }
        //每一秒钟打印当前时间
        Date date = new Date();
        while (true) {
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(date));
            Thread.sleep(1000);
            date = new Date();
        }
    }
}
