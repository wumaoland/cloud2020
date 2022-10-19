package com.udp.send_recive;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpRecive {
    public static void main(String[] args) throws IOException {
        //设置端口
        DatagramSocket socket = new DatagramSocket(9999);
        //接收数据包
        while (true){
            byte [] bytes=new byte[1024];
            DatagramPacket packet = new DatagramPacket(bytes,0,bytes.length);
            socket.receive(packet);//阻塞接收
            //将接收的字节数据转为string打印
            System.out.println("学生说：" + new String(packet.getData(), 0, packet.getLength()));
            if ("bye".equals(new String(packet.getData(),0, packet.getLength()))) {
                break;
            }
        }
        //一定要记得关闭流
        socket.close();
    }
}
