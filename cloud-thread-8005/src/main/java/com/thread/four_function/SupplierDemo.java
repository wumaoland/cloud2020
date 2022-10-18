package com.thread.four_function;

import java.util.function.Supplier;

public class SupplierDemo {
    public static void main(String[] args) {
       /* Supplier<String> supplier=new Supplier<String>() {
            @Override
            public String get() {
                return "测试供给型接口";
            }
        };*/
        //=======转化为lambda表达式======
        Supplier<String> supplier=()->{
            return "供给型接口测试";
        };
        System.out.println(supplier.get());
    }
}
