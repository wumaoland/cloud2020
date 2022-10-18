package com.thread.four_function;

import java.util.function.Predicate;

/**
 * 四大函数式接口:
 *
 * predicate:断定性接口，有一个输入参数，返回值是bool值
 */
public class PredicateDemo {
    public static void main(String[] args) {
        //判断字符串是否为空
      /*  Predicate<String> predicate=new Predicate<String>(){

            @Override
            public boolean test(String s) {
                return s.isEmpty();
            }
        };*/
        //========转为lambda表达式 ========
        Predicate<String> predicate=(s)->{
            return  s.isEmpty();
        };

        System.out.println(predicate.test(""));
    }
}
