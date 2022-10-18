package com.thread.batch_download;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 多线程下载网络图片
 */
public class ExerciseThread extends Thread {
    private String url;
    private String fileName;

    public ExerciseThread(String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downFile(url,fileName);
        System.out.println("下载了文件名为" + fileName);
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
    }

    public static void main(String[] args) {
        ExerciseThread exerciseThread1 = new ExerciseThread("https://img1.baidu.com/it/u=2298484978,1703903334&fm=26&fmt=auto&gp=0.jpg", "0.jpg");
        exerciseThread1.start();
        ExerciseThread exerciseThread2 = new ExerciseThread("https://img2.baidu.com/it/u=3494680980,3429350889&fm=26&fmt=auto&gp=0.jpg", "2.jpg");
        exerciseThread2.start();
        ExerciseThread exerciseThread3 = new ExerciseThread("https://img2.baidu.com/it/u=4233395598,3118792923&fm=26&fmt=auto&gp=0.jpg", "3.jpg");
        exerciseThread3.start();
    }


}
