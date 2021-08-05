package com.thread.p7;

/**
 * 测试守护线程
 * 这里如果把上帝线程设置为守护线程，当you线程结束的时候，jvm退出，上帝线程会过一会自动退出
 * 这里如果把上帝线程设置为用户线程，当you线程结束的时候，上帝线程会永远执行，阻止jvm退出
 */
public class DaemonThread {
    public static void main(String[] args) {
        God god = new God();
        Thread thread = new Thread(god);
        thread.setDaemon(false);
        thread.start();

        You you = new You();
        new Thread(you).start();
    }
}

class God implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("上帝守护者你");
        }
    }
}

class You implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("人生不过三万天");
        }
        System.out.println("GoodBye World!");
    }
}
