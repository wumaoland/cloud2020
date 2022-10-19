package com.udp.send_recive;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UdpSend {
    public static void main(String[] args) throws IOException {
        //设置端口
        DatagramSocket socket = new DatagramSocket(8888);
        //创建消息包
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNext()) {
                String msg = scanner.next();
                byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
                InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9999);
                DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length, inetSocketAddress.getAddress(), inetSocketAddress.getPort());
                //发送消息
                socket.send(packet);
            } else {
                break;
            }
        }
        //一定要记得关闭流
        scanner.close();
        socket.close();
    }
}
