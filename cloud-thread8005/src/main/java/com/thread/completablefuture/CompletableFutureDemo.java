package com.thread.completablefuture;

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
