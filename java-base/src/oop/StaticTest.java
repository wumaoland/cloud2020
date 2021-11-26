package oop;

public class StaticTest {
    public static void main(String[] args) {
        Person san = new Person("张三",24);
        System.out.println(Person.getCount());
        Person si = new Person("李四",25);
        System.out.println(Person.getCount());

    }
}

class Person {
    public String name;
    public int age;
    public static int number;
    public static int count;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static int getCount() {
        count++;
        return count;
    }


}