package SocketDemo;
import java.net.*;
import java.io.*;

public class UDPDemo1{
	public static void main(String [] args) throws IOException{
		DatagramSocket socket = new DatagramSocket();
		String str="I love you";
		//�����ݽ��з�װ����װ�����ݰ���
		DatagramPacket packet=new DatagramPacket(str.getBytes(),str.length(),InetAddress.getByName("localhost"),8000);
		socket.send(packet);//����
		
		byte [] buf =new byte[100];
		DatagramPacket packet2=new DatagramPacket(buf,100);
		socket.receive(packet2);
		//System.out.println(new String(packet2.getData()));
		System.out.println(new String(buf,0,packet2.getLength()));
		socket.close();
	}
}