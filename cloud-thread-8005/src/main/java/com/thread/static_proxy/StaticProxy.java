package com.thread.static_proxy;

/**
 * 静态代理模式：
 * 真实对象和代理对象都要实现同一个接口
 * 代理对象需要代理真实对象
 * 好处：
 * 代理对象可以做很多真实对象做不了的事情
 *真实对象专注于做自己的事情
 */

     interface Marry{
        void happyMarry();
    }

/**
 * 具体结婚对象
 */
class You implements Marry {
        @Override
        public void happyMarry() {
            System.out.println("lw要结婚了");
        }
    }

/**
 * 婚庆公司
 */
class WeddingConmpany implements Marry {
    private Marry target;

    public WeddingConmpany(Marry target) {
        this.target = target;
    }//构造方法要要写对

    @Override
    public void happyMarry() {
        before();
        target.happyMarry();
        after();
    }

    /**
     * 婚庆公司做的事情
     */
    private void before() {
        System.out.println("结婚前布置现场");
    }

    private void after() {
        System.out.println("结婚后收尾款");
    }
}

public class StaticProxy {
    public static void main(String[] args) {
        //婚庆公司帮你结婚
        new WeddingConmpany(new You()).happyMarry();

        //Thread的创建 和结婚这个例子 对比，可以看出继承Thread创建线程的方式也是一种静态代理
        new Thread(() -> {
            System.out.println("lw要结婚了");
        }).start();
    }
}
