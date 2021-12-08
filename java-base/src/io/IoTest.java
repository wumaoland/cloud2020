package io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class IoTest {
    public static void main(String[] args) throws IOException {
        //File对象的操作，打印该目录下所有的文件及文件夹的名称
        File file = new File("D:\\yuque");
        System.out.println(file.getName());
        printChildFile(file);

        //FileInputStream 文件输入流 ,从外部读取文件流输入到内存中
        try (FileInputStream fileInputStream = new FileInputStream("D:\\test.txt")) {
            //设立字节缓冲区 提高读取效率
            //byte[] bytes = new byte[5];

            int a;
            StringBuilder stringBuilder = new StringBuilder();
            while ((a = fileInputStream.read()) != -1) {
                stringBuilder.append((char) a);
            }
            System.out.println(stringBuilder.toString());
        }

        //FileOutputStream 文件输出流,从内存中输出流到外部文件
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\test2.txt");
        try {
            String a = "hello";
            byte[] bytes = a.getBytes();
            fileOutputStream.write(bytes);
        } finally {
            fileOutputStream.close();
        }

        //使用Files 输出
        Path path = Paths.get("D:\\test3.txt");
        Path write = Files.write(path, "hello world 你好".getBytes());

        //使用Files 输入
        StringBuilder stringBuilder = new StringBuilder();
        for (String b : Files.readAllLines(path, StandardCharsets.UTF_8)) {
            stringBuilder.append(b);
        }
        System.out.println(stringBuilder.toString());

    }

    public static void printChildFile(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File file1 : files) {
                    printChildFile(file1);
                }
            }
            System.out.println(file.getName());
        }
    }


}
