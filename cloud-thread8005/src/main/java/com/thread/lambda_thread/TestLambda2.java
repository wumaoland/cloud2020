package com.thread.lambda_thread;

public class TestLambda2 {
    interface Ilove {
        void love(int a,String b);
    }

    public static void main(String[] args) {
        //1.lambda表达式
        Ilove ilove = (int a,String b) -> {
            System.out.println("I Love You ---->" + a+b);
        };
        ilove.love(520,"zll");

        //2. 继续简化
        ilove = (a,b) -> {
            System.out.println("I Love You ---->" + a+b);
        };
        ilove.love(10000,"zll");

     /*   //3.继续简化,这里多个参数必须得加括号，否则编译报错
        ilove = a,b -> {
            System.out.println("I Love You ---->" + a);
        };
        ilove.love(20000,"zll");

        //4.继续简化,这里多个参数必须得加括号，否则编译报错
        Ilove ilove4 = a,b -> System.out.println("I Love You ---->" + a+b);
        ilove4.love(30000);*/

        //总结
        //1.ambda表必式只能有一行代码的情況下才能化成为一行，如果有多行，那么就代码块包裹。
        //2.前提是接口只有一个抽象方法
        //3.多个参数也可以去掉参数类型，要去掉就都去掉，必须加上括号

        //lambda创建线程
        new Thread(()-> System.out.println("我是一个最简单的线程")).start();
    }
}
