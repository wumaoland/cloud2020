package jvm;

import java.util.ArrayList;
import java.util.Random;

public class JvmTest {
    byte[] bytes = new byte[1 * 1024 * 1024];
    public static void main(String[] args) {
        //-Xms： 初始化内存分配大小 默认为内存的1/64
        // -Xmx： 设置最大分配内存，默认为内存的1/4
        //-XX:+PrintGCDetails  打印GC垃圾回收信息信息
        //-XX:+HeapDumpOnOutOfMemoryError dump内存溢出信息  //oom dump

        //-Xms8m -Xmx8m -XX:+PrintGCDetails
//        long l = Runtime.getRuntime().maxMemory();
//        long l1 = Runtime.getRuntime().totalMemory();
//        System.out.println(l);
//        System.out.println(l1);
//        String a="zllwfsdfdsfdsfds";
//        while (true) {
//            a+=a+new Random().nextInt(999999999)+new Random().nextInt(999999999);
//        }

        //-Xms8m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError
        ArrayList<JvmTest> list = new ArrayList<>();
        int count = 0;
        try {
            while (true) {
                list.add(new JvmTest());
                count = count + 1;
            }
        }catch (Error e){
            System.out.println("count:"+count);
            e.printStackTrace();
        }

    }

}
