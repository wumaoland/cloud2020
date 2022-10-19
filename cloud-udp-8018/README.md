# UDP 练习Demo

### 一、udp发送消息 实现步骤
* 使用“DatagramSocket socket=new DatagramSocket(8888)”设置端口
* 使用“DatagramPacket packet=new DatagramPacket(byte[],0,byte[].length)”构造消息包
* 使用“socket.send(packet)”
* 关闭socket流
> 接收消息同理，send换成recive(packet)
### 二、send_recive
实现消息的发送和接收
### 三、talk
实现两个客户端相互发送消息和接收消息
### 四、thread_talk
每个客户端创建一个发送线程、一个接收线程模拟客户端之间相互聊天
