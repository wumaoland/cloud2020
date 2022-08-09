package com.socket.cloudsocket8013.tcp;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileClientSocket {
    public static void main(String[] args) throws Exception {
        //获取服务端ip
        InetAddress serverHost = InetAddress.getByName("127.0.0.1");
        //连接服务端
        Socket socket = new Socket(serverHost, 8888);
        //向服务端发送流
        OutputStream outputStream = socket.getOutputStream();
        //读取文件客户端本地文件流
        FileInputStream fileInputStream = new FileInputStream(new File("D:/a.txt"));
        //将本地文件流转为字节数组写入向服务端发送的输出流中
        byte[] bytes = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes,0,len);
        }
        //关闭资源
        fileInputStream.close();
        outputStream.close();
        socket.close();

    }

}
