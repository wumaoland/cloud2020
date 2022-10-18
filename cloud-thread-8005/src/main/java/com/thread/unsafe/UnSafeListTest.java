package com.thread.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class UnSafeListTest {
    public static void main(String[] args) {
        //并发下，ArrayList是线程不安全的，Vector是线程安全的
        /**
         * 这段代码如果是ArrayList 会报ConcurrentModificationException(并发修改异常)
         * 解决方案
         * 1.List<String> list=new Vector<>();
         * 2.List<String> list = Collections.synchronizedList(new ArrayList<>());
         * 3.CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();写入时复制，计算机程序设计领域的一种有优化策略，写入时避免覆盖，这东西读写分离
         */
        ArrayList<String> list = new ArrayList<>();
        //CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
