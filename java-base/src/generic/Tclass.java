package generic;

import java.util.Arrays;

public class Tclass {
    public static void main(String[] args) {
        String[] arry = new String[]{"a", "c", "b", "e", "g"};
        Arrays.sort(arry);
        System.out.println(Arrays.toString(arry));

        Person[] persons = new Person[]{new Person(24, "A"),new Person(24, "C"),new Person(25,"B")};
        Arrays.sort(persons);
        System.out.println(Arrays.toString(persons));
    }
}

//实现其他对象的排序可以实现Comparable 接口,重写compareTo方法，只适用于字符串排序
class Person implements Comparable<Person> {
     int age;
     String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person person) {
        return this.name.compareTo(person.getName());
    }
}
