package oop;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class StaticTest {
    public static void main(String[] args) {
    }
}

class Person {
    public String name;
    public int age;
    public int index;
    public static int number;
    public static int count;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        Person.number = number;
    }

    public static void setCount(int count) {
        Person.count = count;
    }

    public Person(String name, int age, int index) {
        this.name = name;
        this.age = age;
        this.index = index;
    }

    public static int getCount() {
        count++;
        return count;
    }


}