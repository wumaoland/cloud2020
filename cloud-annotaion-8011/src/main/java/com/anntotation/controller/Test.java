package com.anntotation.controller;

import java.lang.annotation.*;

/**
 * 测试元注解
 */
public class Test {

    //如何只有一个参数且参数名为“value”可以省略参数的名称，如果没有默认值，这里必须写参数
    // 如果哪个参数没有默认值就给那个参数赋值
    @MyAnnotation(name = "李威")
    public void test() {

    }
}

//Target表示注解可以用在那些地方
@Target(value = {ElementType.METHOD, ElementType.TYPE})
//Retention表示我们的注解在什么地方有效
//runtime>class>source
@Retention(value = RetentionPolicy.RUNTIME)
//Documented表示是否将我们的注解声称在java doc中
@Documented
//表示子类可以继承父类的注解
@Inherited
@interface MyAnnotation {
    //这是注解的参数，不是方法，定义格式：参数类型 参数名()
    String name() default "";

    //数组用{}
    String[] school() default {"武汉大学","西北工业大学"};
}
