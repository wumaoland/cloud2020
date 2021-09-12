package com.thread.stream_demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * 有五个用户，一行代码筛选出如下条件的用户：
 * 1.年龄是偶数
 * 2.年龄必须大于21岁
 * 3.用户名转为大写字母
 * 4.用户名字母倒叙
 * 5.只输出一个用户
 */
public class StreamDemo {
    public static void main(String[] args) {
        User a = new User(22, "A");
        User b = new User(17, "B");
        User c = new User(25, "C");
        User e = new User(28, "E");
        User d = new User(24, "D");
        List<User> users = Arrays.asList(a, b, c, d, e);
        users.stream()
                .filter((user)->{return user.getAge()%2 == 0;})
                .filter((user)->{return user.getAge() >21;})
                .map((user)->{ user.getName().toUpperCase(); return user;})
                .sorted((u1,u2)->{return  u2.getName().compareTo(u1.getName());})
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