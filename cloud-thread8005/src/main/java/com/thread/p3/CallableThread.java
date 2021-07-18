package com.thread.p3;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

public class CallableThread implements Callable<Boolean> {
    private String url;
    private String fileName;

    public CallableThread(String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

    @Override
    public Boolean call() throws Exception {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downFile(url, fileName);
        System.out.println("下载成功"+Thread.currentThread().getName());
        return true;
    }
}

//下载器
class WebDownloader{
    /**
     * 下载文件方法
     */
    public void downFile(String url, String fileName) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("io异常，下载文件出现异常");
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableThread exerciseThread1 = new CallableThread("https://img1.baidu.com/it/u=2298484978,1703903334&fm=26&fmt=auto&gp=0.jpg", "0.jpg");
        CallableThread exerciseThread2 = new CallableThread("https://img2.baidu.com/it/u=3494680980,3429350889&fm=26&fmt=auto&gp=0.jpg", "2.jpg");
        CallableThread exerciseThread3 = new CallableThread("https://img2.baidu.com/it/u=4233395598,3118792923&fm=26&fmt=auto&gp=0.jpg", "3.jpg");

        //创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);

        //提交执行
        Future<Boolean> submit = ser.submit(exerciseThread1);
        Future<Boolean> submit1 = ser.submit(exerciseThread2);
        Future<Boolean> submit2 = ser.submit(exerciseThread3);

        //获取结果
        Boolean aBoolean = submit.get();
        Boolean aBoolean1 = submit1.get();
        Boolean aBoolean2 = submit2.get();

        //关闭服务
        ser.shutdown();
    }
}