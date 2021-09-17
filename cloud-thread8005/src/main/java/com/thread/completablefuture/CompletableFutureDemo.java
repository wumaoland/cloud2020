package com.thread.completablefuture;

<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture 异步回调使用Demo
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步执行");
        });
    }
}

@RestController
class CompletableFutureConstroller {

    /**
     * runAsync(runable)方法的简单使用,该方法无返回值
     */
    @GetMapping("/testRunAsync")
    public void testRunAsync(){
        long startTime = System.currentTimeMillis();
        System.out.println("业务其他逻辑正常执行");
        CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步请求第三方完成");
        });
        System.out.println("本次执行耗时==》》"+(System.currentTimeMillis()-startTime));
    }

    /**
     * runAsync(runable,Executor)的简单使用
     */
    @GetMapping("/testRunAsyncExecutor")
    public void testRunAsyncExecutor() {
        CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步请求第三方完成");
        }, Executors.newSingleThreadExecutor());
        System.out.println("业务其他逻辑执行");
    }

    /**
     * 【异步编排demo1测试】对照 1
     */
    @GetMapping("/testSupplyAsyncFirstControlOne/{userId}")
    public String testSupplyAsyncControlOne(@PathVariable("userId") Long userId) throws ExecutionException, InterruptedException {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("【任务1的结果完成计算】查询到用户信息=lw");
            String task1= "lw(userId=" + userId + ")";


            try {
                TimeUnit.MILLISECONDS.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("【任务2的结果计算完成】根据用户"+task1+"信息生成订单");
            String  task2 = task1.concat("买了一瓶红牛");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("【任务3的结果计算完成】开始扣减红牛库存-1");
            return task2.concat("下单成功");
    }

    /**
     * 【异步编排demo1测试】对照 2
     */
    @GetMapping("/testSupplyAsyncFirstControlTwo/{userId}")
    public String testSupplyAsyncFirstControlTwo(@PathVariable("userId") Long userId) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("【任务1的结果完成计算】查询到用户信息=lw");
            return "lw(userId=" + userId + ")";
        });

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            String task2="";
            try {
                TimeUnit.MILLISECONDS.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("【任务2的结果计算完成】根据用户"+completableFuture1.get()+"信息生成订单");
                task2 = completableFuture1.get().concat("买了一瓶红牛");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                return task2;
            }
        });
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("【任务3的结果计算完成】开始扣减红牛库存-1");
        });
        return completableFuture2.get().concat("下单成功");
    }

}
=======
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
        completableFuture2.get();
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


>>>>>>> 240e59d120cfbf13dbcb6a1ec51ef41052a872e8
