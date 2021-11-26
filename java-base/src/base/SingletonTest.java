package base;

/**
 * 单例模式
 * 只有private构造方法，确保外部无法实例化；
 * 通过private static变量持有唯一实例，保证全局唯一性；
 * 通过public static方法返回此唯一实例，使外部调用方能获取到实例。
 */
public class SingletonTest {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getSingleton();
        Singleton2 singleton2 = Singleton2.getSingleton();
    }
}

//饿汉式 立马创建实例
class Singleton {
    //获取去掉 getSingleton()方法，然后singleton 实例变量改为public 也可以实现
    private static final Singleton  singleton=new Singleton();

    public static Singleton getSingleton(){
        return singleton;
    }

    private Singleton() {

    }
}

//懒汉式 延迟创建实例，只有在调获取实例的时候创建，线程不安全
class Singleton2 {
    private static  Singleton2 singleton = null;

    public static Singleton2 getSingleton(){
        if (singleton == null) {
            singleton=new Singleton2();
        }
        return singleton;
    }
    private Singleton2(){

    }
}


