package com.socket.cloudsocket8013.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class StrServerSocket {
    public static void main(String[] args) {
        java.net.ServerSocket serverSocket = null;
        Socket accept = null;
        InputStream inputStream = null;
        try {
            //新建服务端socket
            serverSocket = new java.net.ServerSocket(9999);
            //接收客户端流
            //while(true)让服务端一直监听
            while (true) {
                accept = serverSocket.accept();
                inputStream = accept.getInputStream();
                //输出从客户端接收到的流
                byte[] bytes = new byte[2048];
                int len;
                while ((len = inputStream.read(bytes)) != -1) {
                    String msg = new String(bytes, 0, len);
                    System.out.println(String.format("收到客户端消息:%s",msg));
                }
                //这里再套接个管道流,假设中文进来超过2048的字节,流会断掉,字符就是乱码
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                byte[] bytes = new byte[1024];
//                int len;
//                while ((len = inputStream.read(bytes)) != -1) {
//                    baos.write(bytes,0,len);
//                }
//                System.out.println(baos.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //要倒着关闭流
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (accept != null) {
                try {
                    accept.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
