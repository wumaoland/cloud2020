package com.thread.unsafe;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 同ArrayList,同理可证
 */
public class UnSafeSetTest {
    public static void main(String[] args) {
        //HashSet 也线程不安全，底层是HashMap
        //Set<String> list = new HashSet<>();
        CopyOnWriteArraySet<String> list = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
