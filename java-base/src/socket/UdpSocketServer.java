package socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;


class UdpSocketServer{
    public static void main(String[] args)  {
        try {
            DatagramSocket ds = new DatagramSocket(6666);
            while (true){
                System.out.println(String.format("连接到客户端{}",ds.getRemoteSocketAddress()));
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                ds.receive(packet);
                String reciveData = new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);
                System.out.println(String.format("服务端收到数据{}",reciveData));

                //发送
                packet.setData("ACK".getBytes(StandardCharsets.UTF_8));
                ds.send(packet);

            }

        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }



}
