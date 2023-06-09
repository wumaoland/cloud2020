package reflection;

import javax.xml.crypto.Data;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ReflectionTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException, InstantiationException, ClassNotFoundException {
        /******************************************V我50*****************************/
        dependTest2();

        System.out.println("***********************反射  访问字段*******************************");
        Class<Person> personClass = Person.class;
        Field name = personClass.getDeclaredField("name");
        Field age = personClass.getDeclaredField("age");
        System.out.println(name.getName());
        System.out.println(age.getName());
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("当前字段名：" + declaredField.getName());
        }

        System.out.println("***********************反射 调用方法*******************************");
        //例如Math.abs方法
        int a = -123;
        Method m = Math.class.getMethod("abs", int.class);
        //第一个参数是要操作的数据对象，后面的都是调用的方法参数
        int invoke = (int) m.invoke(a, a);
        System.out.println(invoke);

        //String.substring
        String s = "Hello world";
        Method m2 = String.class.getMethod("substring", int.class);
        String t = (String) m2.invoke(s, 6);
        System.out.println(t);

        //Date.getTime()
        Date date = new Date();
        Method m3 = Date.class.getMethod("getTime");
        Long time = (Long) m3.invoke(date);
        System.out.println(time);

        System.out.println("***********************反射 调用构造方法*******************************");
        //平常获取类的实例
        Person person = new Person();

        //反射 调用无参构造方法获取实例,且构造方法为public修饰
        Person person1 = Person.class.newInstance();

        //反射 获取有参构造方法
        Constructor<Person> constructor = Person.class.getConstructor(String.class, int.class);
        //调用构造方法
        Person lw = constructor.newInstance("lw", 24);
        System.out.println(lw.getName());

        System.out.println("*************************动态代理**************************************");
//        在运行期动态创建一个interface实例的方法如下：
//
//        1.定义一个InvocationHandler实例，它负责实现接口的方法调用；
//        2.通过Proxy.newProxyInstance()创建interface实例，它需要3个参数：
//          1.使用的ClassLoader，通常就是接口类的ClassLoader；
//          2.需要实现的接口数组，至少需要传入一个接口进去；
//          3.用来处理接口方法调用的InvocationHandler实例。
//        3.将返回的Object强制转型为接口。
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("printName")) {
                    System.out.println("被调用了");
                }
                return null;
            }
        };
        Animal animal = (Animal) Proxy.newProxyInstance(Animal.class.getClassLoader(), new Class[]{Animal.class}, invocationHandler);
        animal.printName();


        System.out.println("****************************反射复习************************************");
        //获取class性能最好
        Class<Person> personClass1 = Person.class;
        //根据对象获取class
        Class<? extends Person> aClass = person.getClass();
        //根据权限定名获取class
        Class<?> aClass1 = Class.forName("reflection.Person");
        //根据构造方法创建实例
        Constructor<Person> declaredConstructor = personClass1.getDeclaredConstructor(String.class, int.class);
        Person person2 = declaredConstructor.newInstance("邱月圆", 25);
        //调用普通方法
        Method setName = personClass1.getDeclaredMethod("setName", String.class);
        setName.invoke(person2, "邓桥");
        System.out.println(person2.toString());
        //调用字段
        Field age1 = personClass1.getDeclaredField("age");
        age1.setAccessible(true);
        age1.set(person2, 28);
        System.out.println(person2);

    }

    public static void dependTest2() {
        List<List<Integer>> lists = Arrays.asList(
                Arrays.asList(0, 22, 34, 42, 47, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99),
                Arrays.asList(1, 21, 31, 34, 42, 48, 60, 61, 82, 83, 98, 99),
                Arrays.asList(2, 20, 28, 34, 42, 49, 60, 61, 82, 83, 98, 99),
                Arrays.asList(3, 19, 34, 42, 60, 61, 82, 83, 98, 99),
                Arrays.asList(4, 18, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 82, 83, 98, 99), Arrays.asList(5, 17, 34, 42, 73, 74, 82, 83, 98, 99),
                Arrays.asList(6, 16, 34, 36, 43, 48, 73, 74, 82, 83, 98, 99),
                Arrays.asList(7, 15, 33, 34, 43, 47, 73, 74, 82, 83, 98, 99),
                Arrays.asList(8, 14, 31, 34, 44, 45, 50, 73, 74, 82, 83, 98, 99),
                Arrays.asList(9, 13, 30, 34, 43, 44, 50, 73, 74, 82, 83, 98, 99),
                Arrays.asList(10, 12, 31, 34, 41, 45, 48, 73, 74, 82, 83, 98, 99),
                Arrays.asList(11, 34, 46, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99)
        );
        System.out.println(demo(lists));
    }

    static String demo (List<List<Integer>> arr) {
        String str = "";
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < 100; j++) {
                if (arr.get(i).indexOf(j) > -1) {
                    str += "█";
                } else {
                    str += " ";
                }
            }
            str += "\n";
        }
        return str;
    }

}

interface Animal{
    void printName();
}

class Person implements Animal {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
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

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


}