package com.socket.cloudsocket8013.tcp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServerSocket {
    public static void main(String[] args) throws IOException {
        //创建服务端
        ServerSocket serverSocket = new ServerSocket(8888);
        //接收客户端的输入流
        Socket accept = serverSocket.accept();
        InputStream inputStream = accept.getInputStream();
        //创建服务端本地文件流
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:/b.txt"));
        //将客户端的输入流读入到本地服务端的输出流中
        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes,0,len);
        }
        //关闭资源
        fileOutputStream.close();
        inputStream.close();
        accept.close();
        serverSocket.close();
    }
}
