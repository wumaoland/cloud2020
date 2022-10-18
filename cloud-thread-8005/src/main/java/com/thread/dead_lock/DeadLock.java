package com.thread.dead_lock;

/**
 * 死锁：多个线程之间互相抱着对方需要的资源，形成僵持
 */
public class DeadLock {
    public static void main(String[] args) {
        new EatThread("女孩1",0).start();
        new EatThread("女孩2",1).start();
    }
}

/**
 * 桃子
 */
class Peach {

}

/**
 * 苹果
 */
class Apple {

}

class EatThread extends Thread {
    static Peach peach = new Peach();
    static Apple apple = new Apple();
    String girlName;//想吃水果的人的名字
    int choice;//选择

     EatThread(String girlName, int choice) {
        this.girlName = girlName;
        this.choice = choice;
    }

    @Override
    public void run(){
        wantEat();
    }

    public void wantEat() {
        if (choice == 0) {
            synchronized (peach){
                System.out.println(girlName + "获得桃子对象的锁");
                synchronized (apple) {
                    System.out.println(girlName+"获得苹果对象的锁");
                }
            }
            //如本例子，如果要避免死锁，可以把synchronized(apple)拿到这里执行

            /////
        } else {
            synchronized (apple){
                System.out.println(girlName + "获得苹果对象的锁");
                synchronized (peach) {
                    System.out.println(girlName+"获得桃子对象的锁");
                }
            }

            //如本例子，如果要避免死锁，可以把synchronized(peach)拿到这里执行
        }
    }
}
