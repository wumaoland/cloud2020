package com.thread.completablefuture;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


/**
 * CompletableFuture  异步回调的使用，提高接口执行效率
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //串行执行时间 2311=500+300+1500 +其他损耗
       /* long startTime = System.currentTimeMillis();
        System.out.println(UserInfo.get().toString());

        //中间的其他操作耗时300毫秒
        TimeUnit.MILLISECONDS.sleep(300);

        System.out.println(ArticleInfo.get().toString());
        System.out.println("总共耗时："+(System.currentTimeMillis()-startTime));
*/


        //使用CompletableFuture 异步执行 总共耗时：2542
        long startTime = System.currentTimeMillis();
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            System.out.println(UserInfo.get().toString());
        });
        //中间其他操作耗时
        TimeUnit.MILLISECONDS.sleep(300);
        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() -> {
            System.out.println(ArticleInfo.get().toString());
        });
        completableFuture2.get();//get会阻塞直到completableFuture2任务完成
        System.out.println("总共耗时："+(System.currentTimeMillis()-startTime));
    }
}

class UserInfo {
    private  int age;
    private  String name;
    private  String sex;

    @Override
    public String toString() {
        return "UserInfo{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public UserInfo(int age, String name, String sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    public static UserInfo get(){
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new UserInfo(25, "李伟", "男");
    }

}

class ArticleInfo {
    private  String title;
    private  Date createTime;
    private  String content;

    @Override
    public String toString() {
        return "ArticleInfo{" +
                "title='" + title + '\'' +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                '}';
    }

    public ArticleInfo(String title, Date createTime, String content) {
        this.title = title;
        this.createTime = createTime;
        this.content = content;
    }

    public static ArticleInfo get(){
        try {
            TimeUnit.MILLISECONDS.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArticleInfo("JAVA编程思想", new Date(), "这是一本java类编程图书大字典");
    }
}


