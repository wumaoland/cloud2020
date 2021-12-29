package reflection;

import javax.xml.crypto.Data;
import java.lang.reflect.*;
import java.util.Date;

public class ReflectionTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        System.out.println("***********************反射  访问字段*******************************");
        Class<Person> personClass = Person.class;
        Field name = personClass.getDeclaredField("name");
        Field age = personClass.getDeclaredField("age");
        System.out.println(name.getName());
        System.out.println(age.getName());

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
        Constructor<Person> constructor = Person.class.getConstructor(String.class,int.class);
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
                if (method.getName().equals("printName") ){
                    System.out.println("被调用了");
                }
                return null;
            }
        };
        Animal animal = (Animal)Proxy.newProxyInstance(Animal.class.getClassLoader(), new Class[]{Animal.class}, invocationHandler);
        animal.printName();

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
}