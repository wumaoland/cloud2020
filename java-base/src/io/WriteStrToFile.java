package io;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class WriteStrToFile {
    public static void main(String[] args) {
        FileOutputStream fileOutputStream =null;
        BufferedReader bufferedReader =null;

        try {
            //创建空txt文件
            File file = new File("D:/a.txt");
            boolean newFile = file.createNewFile();
            //往空文件写入数据,方向是 内存->磁盘文件  所以是输出流
            fileOutputStream = new FileOutputStream("D:/a.txt");
            //控制台输入字符,这个是控制台->内存  所以是输入流
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请写入你想说的话(按q结束):");
            String s = null;
            do {
                s = bufferedReader.readLine();
                byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
                fileOutputStream.write(bytes);
            } while (!s.equals("q"));
        } catch (IOException e) {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
