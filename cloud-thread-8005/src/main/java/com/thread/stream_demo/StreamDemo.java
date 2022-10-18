package com.thread.stream_demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 *
 * stream 流  和四大函数式接口有关，需看传参
 *
 * 有五个用户，一行代码筛选出如下条件的用户：
 * 1.年龄是偶数
 * 2.年龄必须大于21岁
 * 3.用户名转为大写字母
 * 4.用户名字母倒叙
 * 5.只输出一个用户
 */
public class StreamDemo {
    public static void main(String[] args) {
        User a = new User(22, "a");
        User b = new User(17, "b");
        User c = new User(25, "c");
        User e = new User(28, "e");
        User d = new User(24, "d");
        List<User> users = Arrays.asList(a, b, c, d, e);
        users.stream()
                .filter((user)->{return user.getAge()%2 == 0;})
                .filter((user)->{return user.getAge() >21;})
                .map((user)->{return user.getName().toUpperCase();})
                .sorted((u1,u2)->{return  u2.compareTo(u1);})
                .limit(1)
                .forEach(System.out::println);
    }
}




@Data
@NoArgsConstructor
@AllArgsConstructor
class User {
    private int age;
    private String name;
}