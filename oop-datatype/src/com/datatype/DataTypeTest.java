package com.datatype;


public class DataTypeTest {
    public static void main(String[] args) {
        Person person = new Person();
        System.out.println(person.getAge());
        System.out.println(person.getName());
        Person person1 = person;
        person.setAge(20);
//        person.setName();
        System.out.println(person1.getAge());
        System.out.println(person1.getName());
        System.out.println("---------------我是分割线--------------");
        char[] chararry = {'a', 'b', 'c'};
        char[] chararry1 = chararry;
        chararry[0] = 'x';
        System.out.println(chararry1[0]);
        System.out.println("---------------我是分割线--------------");
        int[] intarry = {1, 2, 3};
        int[] intarry1 = intarry;
        intarry[0] = 4;
        System.out.println(intarry[0]);
        System.out.println(intarry1[0]);
        System.out.println("---------------我是分割线--------------");
        Integer a = -129;
        Integer b = -129;
        int c = a;
        System.out.println(a == b);
        System.out.println(b == c);

    }
}

class Person{
    public String name;
    public int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}