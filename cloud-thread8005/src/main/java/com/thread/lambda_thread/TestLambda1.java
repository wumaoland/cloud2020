package com.thread.lambda_thread;

public class TestLambda1 {
    //3.静态内部类
    static class Like2 implements ILike {

        @Override
        public void lambda() {
            System.out.println("我是Ilike2");
        }
    }

    public static void main(String[] args) {
        Like1 like1 = new Like1();
        like1.lambda();

        Like2 like2 = new Like2();
        like2.lambda();

        //4.局部内部类
        class Like3 implements ILike {
            @Override
            public void lambda() {
                System.out.println("我是Ilike3");
            }
        }
        Like3 like3 = new Like3();
        like3.lambda();

        //5.匿名内部类
        ILike iLike = new ILike() {
            @Override
            public void lambda() {
                System.out.println("我是Ilike4");
            }
        };
        iLike.lambda();

        //6.用lambda表达式
        ILike iLike5 = () -> {
            System.out.println("我是Ilike5");
        };
        iLike5.lambda();

    }
}

//1.定义一个接口
interface ILike {
    void lambda();
}

//2.实现接口
class Like1 implements ILike {

    @Override
    public void lambda() {
        System.out.println("我是Ilike1");
    }
}
