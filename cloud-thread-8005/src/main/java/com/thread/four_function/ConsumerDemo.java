package com.thread.four_function;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 四大函数式接口：
 * <p>
 * 消费型接口，只有输入参数，没有返回参数
 */
public class ConsumerDemo {
    public static void main(String[] args) {
      /*  Consumer<String> consumer=new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("我消费了");
            }
        };*/

        //=========转化为lambda表达式=========
        Consumer<String> consumer = (str) -> {
            System.out.println(str);
        };
        consumer.accept("我消费了");
    }
}
