package base;

/**
 * 数据类型测试
 */
public class DataTypeTest {
    public static void main(String[] args) {
        //引用类型赋值复制了相同的栈内存地址，指向的堆内存数据为同一个，所以改变了原来引用类型的数据其他被赋值的引用类型对象被改变
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
        //从基本类型byte开始 2^7 ~2^7-1  、 short 2^15 ~ 2^15-1 、  int 2^31 ~2^31-1  指数呈双倍递增+1
        //Integer 范围在-128(包含) ~ 127(包含) 可“==”比较
        Integer a = -129;
        Integer b = -129;
        int c = a;
        System.out.println(a == b );//false -129 超过-128的范围
        System.out.println(b == c);//true 这里用基本数据类型int 比较，所以比较的是值，
        System.out.println(Integer.MAX_VALUE);

        String s = new StringBuilder("abc").append(12L).toString();
        System.out.println(s);

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