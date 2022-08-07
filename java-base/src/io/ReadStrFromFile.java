package io;

import java.io.*;

public class ReadStrFromFile {
    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader=null;
        try {
            System.out.println("请输入你要读取的文件绝对路径(输入q开始执行):");
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String strPath = null;
            String instruction=null;
            do {
                try {
                    instruction = bufferedReader.readLine();
                    if(!instruction.equals("q")){
                        strPath=instruction;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (!instruction.equals("q"));
            File file = new File(strPath);
            if (!file.exists()) {
                System.out.println("文件不存在");
                return;
            }
            //从文件->内存中  所以是输入流
            fileInputStream = new FileInputStream(strPath);
            //建立字符数组缓冲区
            int len;
            byte[] bytes = new byte[2048];
            try {
                while ((len = fileInputStream.read(bytes)) != -1) {
                    String s = new String(bytes, 0, len);
                    System.out.print(s);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

    }
}
