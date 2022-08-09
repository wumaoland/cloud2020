package com.socket.cloudsocket8013.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class StrClientSocket {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream contentStream = null;
        try {
            //获取服务端连接、端口
            InetAddress host = InetAddress.getByName("1.116.37.240");
            int serverPort = 9999;
            //与服务端建立连接
            socket = new Socket(host, serverPort);
            //发送数据
            contentStream = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String content = br.readLine();
                System.out.println(String.format("我对服务端说:%s",content));
                contentStream.write(content.getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //要倒着关闭流
            if (contentStream != null) {
                try {
                    contentStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
