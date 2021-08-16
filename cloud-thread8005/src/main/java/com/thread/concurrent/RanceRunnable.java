package com.thread.concurrent;

/**
 * 龟兔赛跑
 */
public class RanceRunnable implements Runnable {

    private static String winner;


    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (Thread.currentThread().getName().equals("兔子") &&  i%10==0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Boolean aBoolean = gameOver(i);
            if (aBoolean) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "跑了" + i + "步数");
        }
    }

    private Boolean gameOver(int step){
        if (winner != null) {
            System.out.println("已经有胜利者了");
            return true;
        }else{
            if (step>=100) {
                winner = Thread.currentThread().getName();
                System.out.println("胜利者是"+winner);
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        RanceRunnable ranceRunnable = new RanceRunnable();
        new Thread(ranceRunnable,"兔子").start();
        new Thread(ranceRunnable,"乌龟").start();
    }
}
