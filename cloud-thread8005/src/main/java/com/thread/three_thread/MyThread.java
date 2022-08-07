package com.thread.three_thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread {


}

/**
 * 静态代理
 */
interface Marry {
    void happyMarried();
}

class You implements Marry {
    String name;

    You(String name) {
        this.name = name;
    }

    @Override
    public void happyMarried() {
        System.out.println(String.format("%s要结婚了", name));
    }
}

class MarryCompany implements Marry {
    Marry marry;

    MarryCompany(Marry marry) {
        this.marry = marry;
    }

    @Override
    public void happyMarried() {
        this.before();
        marry.happyMarried();
        this.after();
    }

    void before() {
        System.out.println("开始布置婚礼现场");
    }

    void after() {
        System.out.println("开始打扫婚礼现场");
    }

    public static void main(String[] args) {
        new MarryCompany(new You("lw")).happyMarried();
    }
}

/**
 * extend Thread 实现多线程下载图片
 */
class DownImgThread extends Thread {
    String url;
    String name;

    DownImgThread(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        new DownImgTool().downImg(url, name);
    }

}

class DownImgTool {
    public void downImg(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
            System.out.println(String.format("%s下载完毕", name));
        } catch (IOException e) {
            System.out.println("io异常");
        }
    }

    public static void main(String[] args) {
        new DownImgThread("https://img2.woyaogexing.com/2022/07/06/0394e4df5da4ebc9!400x400.jpg", "0.jpg").start();
        new DownImgThread("https://img2.woyaogexing.com/2022/07/06/3ade99967ac52b27!400x400.jpg", "1.jpg").start();
        new DownImgThread("https://img2.woyaogexing.com/2022/07/06/8efef3487664d9f6!400x400.jpg", "2.jpg").start();
    }
}

/**
 * 实现runnable 接口多线程下载图片
 */
class DownImgRunnable implements Runnable {
    String url;
    String name;

    DownImgRunnable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        new DownImgTool().downImg(url, name);
    }

    public static void main(String[] args) {
        new Thread(new DownImgRunnable("https://img2.woyaogexing.com/2022/07/06/0394e4df5da4ebc9!400x400.jpg", "0.jpg")).start();
        new Thread(new DownImgRunnable("https://img2.woyaogexing.com/2022/07/06/3ade99967ac52b27!400x400.jpg", "1.jpg")).start();
        new Thread(new DownImgRunnable("https://img2.woyaogexing.com/2022/07/06/8efef3487664d9f6!400x400.jpg", "2.jpg")).start();
    }

}

/**
 * 多个线程操作同一个资源线程不安全解决,问题链接：https://segmentfault.com/q/1010000042083067
 * 多线程Lock的问题，实现多个线程能拿到票，票并且是连续的
 */

class BuyTicketsThread implements Runnable {
    private int ticketNums = 10;
    private final ReentrantLock reentrantLock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
//                reentrantLock.lock();//加锁
                synchronized (BuyTicketsThread.class) {
                    if (ticketNums <= 0) {
                        break;
                    }
                    System.out.println(String.format("%s拿到了第%s张票", Thread.currentThread().getName(), ticketNums--));
                }

            } catch (Exception e) {
                System.out.println("加锁失败");
            } finally {
                //reentrantLock.unlock();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        BuyTicketsThread buyTicketsThread = new BuyTicketsThread();
        new Thread(buyTicketsThread, "线程1").start();
        new Thread(buyTicketsThread, "线程2").start();
        new Thread(buyTicketsThread, "线程3").start();
    }
}

/**
 * 函数式接口：接口里面只有一个抽象方法
 */
interface ILike {
    void lambda();
}

class Like implements ILike {

    @Override
    public void lambda() {
        System.out.println("I Like 1");
    }
}

class InterfaceTest {
    static class Like2 implements ILike {
        @Override
        public void lambda() {
            System.out.println("I Like 2");
        }
    }


    public static void main(String[] args) {
        Like like = new Like();
        like.lambda();
        Like2 like2 = new Like2();
        like2.lambda();
        class Like3 implements ILike {

            @Override
            public void lambda() {
                System.out.println("I Like 3");
            }
        }
        Like3 like3 = new Like3();
        like3.lambda();
        ILike like4 = new ILike() {

            @Override
            public void lambda() {
                System.out.println("I Like 4");
            }
        };
        like4.lambda();

        ILike like5 = () -> {
            System.out.println("I Like 5");
        };
        like5.lambda();
        ILike like6 = () -> System.out.println("I Like 6");
        like6.lambda();
    }
}

/**
 * 推荐标志位停止线程，jdk自带的方法已不要使用
 */

class FlagStopThread implements Runnable {
    Boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            int i = 0;
            System.out.println(String.format("子线程已执行到%d", i++));
        }

    }

    void stop() {
        this.flag = false;
    }

    public static void main(String[] args) {
        FlagStopThread flagStopThread = new FlagStopThread();
        new Thread(flagStopThread).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println(String.format("主线程：%d", i));
            if (i == 50) {
                flagStopThread.stop();
                System.out.println("线程该停止了");
            }
        }
    }
}

/**
 * sleep模式时钟
 */
class SleepTest {
    public static void main(String[] args) {
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 线程礼让 Yield()
 */
class YieldThread implements Runnable {

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(String.format("%s线程开始执行", name));
        //Thread.yield();//礼让会让线程处于就绪状态
        System.out.println(String.format("%s线程结束执行", name));
    }

    public static void main(String[] args) {
        YieldThread yieldThread = new YieldThread();
        new Thread(yieldThread, "A").start();
        new Thread(yieldThread, "B").start();
    }
}

/**
 * 线程插队  join
 */

class JoinThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(String.format("%s线程执行了%d", Thread.currentThread().getName(), i));
        }
    }

    public static void main(String[] args) {


        Thread thread = new Thread(new JoinThread());
        thread.start();
        for (int i = 0; i < 1000; i++) {
            System.out.println(String.format("主线程执行了%d", i));
            if (i == 100) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/**
 * 线程状态 state
 */

class StateThread extends Thread {

    @Override
    public void run() {
//        System.out.println("我在执行");
    }

    public static void main(String[] args) {
        Thread thread = new StateThread();
        State state = thread.getState();
        System.out.println(state);

        thread.start();
        System.out.println(state);

        while (state != State.TERMINATED) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            state = thread.getState();
            System.out.println(state);
        }
    }
}

/**
 * 线程优先级priority
 */
class PriorityThread implements Runnable {

    @Override
    public void run() {
        System.out.println(String.format("%s优先级-%d", Thread.currentThread().getName(), Thread.currentThread().getPriority()));
    }

    public static void main(String[] args) {
        PriorityThread priorityThread = new PriorityThread();
        Thread thread0 = new Thread(priorityThread, "0");
        Thread thread1 = new Thread(priorityThread, "1");
        Thread thread2 = new Thread(priorityThread, "2");
        Thread thread3 = new Thread(priorityThread, "3");
        Thread thread4 = new Thread(priorityThread, "4");
        thread4.setPriority(10);
        thread3.setPriority(8);
        thread2.setPriority(9);
        thread1.setPriority(1);
        thread0.setPriority(5);
        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

/**
 * daemon守护线程-线程分为守护进程，用户线程不执行玩，jvm虚拟机不会退出，守护线程会在jvm退出后退出
 */
class GodThread implements Runnable {


    @Override
    public void run() {
        while (true) {
            System.out.println("oh dear ,god daemon you");
        }
    }
}

class ManThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("人生不过三万天");
        }
        System.out.println("see you ,world!");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new GodThread(), "god");
        thread.setDaemon(true);
        thread.start();
        Thread manThread = new Thread(new ManThread());
        manThread.start();
    }
}

/**
 * 买票线程不安全
 */

class BuyTicketStation implements Runnable {
    int ticketNums = 10;
    Boolean bol = true;

    @Override
    public void run() {
        while (bol) {
            buy();
        }

    }

    private void buy() {

        synchronized (BuyTicketStation.class) {
            if (ticketNums <= 0) {
                stop();
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("%s线程买到第%d张票", Thread.currentThread().getName(), ticketNums--));

        }

    }

    void stop() {
        this.bol = false;
    }

    public static void main(String[] args) {
        BuyTicketStation buyTicketStation = new BuyTicketStation();
        new Thread(buyTicketStation, "牛逼的我").start();
        new Thread(buyTicketStation, "苦逼的你").start();
        new Thread(buyTicketStation, "可恶的黄牛党").start();
        new Thread(buyTicketStation, "可恶车站").start();
    }
}

/**
 * 线程池练习，查看线程池的执行状况
 */

class ThreadPoolTest implements Runnable {
    private static int nums = -1;
    private int yewuNums;

    @Override
    public void run() {
        System.out.println(String.format("%sTask-%d", Thread.currentThread().getName(), yewuNums));
        while (nums != yewuNums) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("Task-%d结束", yewuNums));
    }

    public ThreadPoolTest(int yewuNums) {
        this.yewuNums = yewuNums;
    }
}

class ThreadMain {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(2), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());
        ThreadPoolTest threadPoolTest = new ThreadPoolTest(1);
        threadPoolExecutor.execute(threadPoolTest);
        System.out.println(String.format("执行线程数:%s", threadPoolExecutor.getPoolSize()));
        System.out.println(String.format("队列:%s", threadPoolExecutor.getQueue()));
        System.out.println("------------");

        ThreadPoolTest threadPoolTest2 = new ThreadPoolTest(2);
        threadPoolExecutor.execute(threadPoolTest2);
        System.out.println(String.format("执行线程数:%s", threadPoolExecutor.getPoolSize()));
        System.out.println(String.format("队列:%s", threadPoolExecutor.getQueue()));
        System.out.println("------------");

        ThreadPoolTest threadPoolTest3 = new ThreadPoolTest(3);
        threadPoolExecutor.execute(threadPoolTest3);
        System.out.println(String.format("执行线程数:%s", threadPoolExecutor.getPoolSize()));
        System.out.println(String.format("队列:%s", threadPoolExecutor.getQueue()));
        System.out.println("------------");

        ThreadPoolTest threadPoolTest4 = new ThreadPoolTest(4);
        threadPoolExecutor.execute(threadPoolTest4);
        System.out.println(String.format("执行线程数:%s", threadPoolExecutor.getPoolSize()));
        System.out.println(String.format("队列:%s", threadPoolExecutor.getQueue()));
        System.out.println("------------");

        ThreadPoolTest threadPoolTest5 = new ThreadPoolTest(5);
        threadPoolExecutor.execute(threadPoolTest5);
        System.out.println(String.format("执行线程数:%s", threadPoolExecutor.getPoolSize()));
        System.out.println(String.format("队列:%s", threadPoolExecutor.getQueue().toString()));
        System.out.println("------------");

        ThreadPoolTest threadPoolTest6 = new ThreadPoolTest(5);
        threadPoolExecutor.execute(threadPoolTest6);
        System.out.println(String.format("执行线程数:%s", threadPoolExecutor.getPoolSize()));
        System.out.println(String.format("队列:%s", threadPoolExecutor.getQueue().toString()));
        System.out.println("------------");


        ThreadPoolTest threadPoolTest7 = new ThreadPoolTest(5);
        threadPoolExecutor.execute(threadPoolTest7);
        System.out.println(String.format("执行线程数:%s", threadPoolExecutor.getPoolSize()));
        System.out.println(String.format("队列:%s", threadPoolExecutor.getQueue().toString()));
        System.out.println("------------");

        ThreadPoolTest threadPoolTest8 = new ThreadPoolTest(5);
        threadPoolExecutor.execute(threadPoolTest8);
        System.out.println(String.format("执行线程数:%s", threadPoolExecutor.getPoolSize()));
        System.out.println(String.format("队列:%s", threadPoolExecutor.getQueue().toString()));
        System.out.println("------------");


    }

}

/**
 * arraylist线程不安全测试,锁就要锁增删改的对象
 */
class ArrayListTest {
    public static void main(String[] args) {
        List arrayList = new ArrayList();
        for (int i = 0; i < 100000; i++) {
            new Thread(() -> {
                synchronized (arrayList) {
                    arrayList.add(Thread.currentThread().getName());
                }
            }).start();

        }
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(arrayList.size());
    }
}

/**
 * 静态代理练习
 * 1.代理对象和被代理对象都实现同一个接口
 * 2.代理对象代理被代理对象
 * 3.被代理对象被当做参数传入代理对象
 */
interface Marry3 {
    void married3();
}

class You3 implements Marry3 {

    @Override
    public void married3() {
        System.out.println("我是苏景顺，我要结婚");
    }
}

class MarryCompany3 implements Marry3 {
    private Marry3 marry3;

    @Override
    public void married3() {
        before();
        marry3.married3();
        after();
    }

    public MarryCompany3(Marry3 marry3) {
        this.marry3 = marry3;
    }

    public void before() {
        System.out.println("布置婚礼现场");
    }

    public void after() {
        System.out.println("打扫婚礼现场");
    }

    public static void main(String[] args) {
        new MarryCompany3(new You3()).married3();
    }

}

/**
 * 单例模式练习
 * 1.确保实例为private static 是唯一的，不让外部访问
 * 2.确保构造方法是private  不让外部去构造
 * 3.获取的对外提供方法为public static
 * 4.饿汉式，类初始化时就创建好了实例
 * 5.饱汉式，只有需要实例的时候去判断需不需要去创建实例
 */
class Single {
    /**
     * 饿汉式
     */
    private static Single single = new Single();

    //无参构造
    private Single() {
    }

    //对外提供实例
    public static Single getSingle() {
        return single;
    }
}

class Single2 {
    /**
     * 饱汉式
     */
    private static Single2 single2 = null;

    //无参构造
    private Single2() {
    }

    ;

    //对外提供
    public static Single2 getSingle2() {
        if (single2 == null) {
            single2 = new Single2();
        }
        return single2;
    }
}

class SingleTest {
    public static void main(String[] args) {
        Single single = Single.getSingle();
        System.out.println();
    }
}

/**
 * 异步编排线程
 */
class AsyncTest {
    public static void main(String[] args) {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步执行完成");
        });
        System.out.println("我先响应了");
    }
}


class TestPhone {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone1 = new Phone();

        new Thread(() -> {
            phone1.sms();
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone1.call();
        }).start();


    }
}

class Phone {
    //锁的对象是方法的调用者
    public synchronized void sms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }

    public static void main(String[] args) {

        int g = 1;
        for (int i1 = 1; i1 <= 10; i1++) {
            System.out.println(g);
            ++g;
        }

    }
}

/**
 * 自定义注解练习
 * 1.@target  注解作用的地方，方法上 类上
 * 2.@retention 注解作用的级别， runtime>class>source
 * 3.@docutment 注解会保存在javadoc中
 * 4.@inherited  注解会被继承
 * 5.@interface 自定义注解
 * 6.如果字段就只有一个  默认字段名可以使value
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@interface MyAnnotation {
    String name() default "lw";

    int age() default 18;

    //数组用{}
    String[] school() default {"西北工业大学", "国防科技大学", "武汉大学"};
}

class MyAnnotationTest {
    private int sex;
    private String company;

    public MyAnnotationTest() {
    }

    ;

    public MyAnnotationTest(int sex, String company) {
        this.sex = sex;
        this.company = company;
    }

    @Override
    public String toString() {
        return "MyAnnotationTest{" +
                "sex=" + sex +
                ", company='" + company + '\'' +
                '}';
    }

    @MyAnnotation(name = "李威", age = 15, school = {"上海复旦大学"})
    void test(String name, int age) {
        System.out.println(String.format("我叫%s今年%d岁", name, age));
    }

    /**
     * 反射获取class，性能最高就直接类.class ，
     */
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        //第一种性能最高的获取class
        Class<MyAnnotationTest> myAnnotationTestClass = MyAnnotationTest.class;
        //实例对象获取class
        MyAnnotationTest myAnnotationTest = new MyAnnotationTest();
        Class<? extends MyAnnotationTest> aClass = myAnnotationTest.getClass();
        //包路径获取class
        Class<?> aClass1 = Class.forName("com.thread.three_thread.MyAnnotationTest");
//        MyAnnotationTest myAnnotationTest1 = myAnnotationTestClass.newInstance();
//        myAnnotationTest1.test("李威",26);
        //基本类型类.Type
        Class<Integer> type = Integer.TYPE;
        //获取方法名
        Method[] methods = myAnnotationTestClass.getDeclaredMethods();//加declared就是本类所有，不加就是本类及父类public方法或属性
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        //通过反射构造器构造有参对象
        Constructor<MyAnnotationTest> declaredConstructor = myAnnotationTestClass.getDeclaredConstructor(int.class, String.class);
        MyAnnotationTest myAnnotationTest2 = declaredConstructor.newInstance(25, "简24智能零售");
        System.out.println(myAnnotationTest2);
        //通过反射调用普通方法
        Method test = myAnnotationTestClass.getDeclaredMethod("test", String.class, int.class);
        test.invoke(myAnnotationTest2, "苏景顺", 26);//执行方法
        //通过反射操作属性
        Field company = myAnnotationTestClass.getDeclaredField("company");
        company.set(myAnnotationTest2, "咸亨国际");
        System.out.println(myAnnotationTest2.toString());
        //通过反射操作私有属性
        Field age = myAnnotationTestClass.getDeclaredField("sex");
        age.setAccessible(true);//开启改私有属性的权限
        age.set(myAnnotationTest2, 22);
        System.out.println(myAnnotationTest2.toString());

    }
}

/**
 * 反射复习
 */

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
    }

    ;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name=name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
//        //类名.class 性能最好获取类的方式
        Class<Student> studentClass = Student.class;
//        //反射构造方法获取实例对象
        Constructor<Student> declaredConstructor = studentClass.getDeclaredConstructor(String.class, int.class);
        Student student = declaredConstructor.newInstance("李威", 25);
        System.out.println(student);

        //*****************************************
        //这里所有的反射赋值时，第一个参数是该对象

        //反射普通方法
        Method setName = studentClass.getDeclaredMethod("setName", String.class);
        setName.invoke(student,"苏景顺");
        System.out.println(student.getName());
        //反射对象属性
        Field name = studentClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(student,"邱月园");
        System.out.println(student.getName());

        Map<String,String> map = new HashMap<>();


    }
}



