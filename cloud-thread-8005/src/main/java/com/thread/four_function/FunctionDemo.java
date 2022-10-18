package com.thread.four_function;

import java.util.function.Function;


/**
 * 四大函数式接口
 * <p>
 * function 函数接口，有一个输入参数，有一个输出参数
 * 只要是函数式接口都可以用lambda表达式简化
 */
public class FunctionDemo {
    public static void main(String[] args) {
        /*Function function = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s;
            }
        };*/

        //=========转化为lambda表达式==========
        Function function=(str)->{return str;};

        System.out.println(function.apply("hello world"));
    }
}
