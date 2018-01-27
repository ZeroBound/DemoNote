import java.net.*;
import java.io.*;

public class UDPDemo2{
	public static void main(String [] args) throws IOException{
		DatagramSocket socket = new DatagramSocket(8000);
		byte [] buf =new byte[100];
		DatagramPacket packet=new DatagramPacket(buf,100);
		socket.receive(packet);//
		//System.out.println(new String(packet.getData()));
		System.out.println(new String(buf,0,packet.getLength()));
		
		String str="Me too";
		DatagramPacket packet2=new DatagramPacket(str.getBytes(),str.length(),packet.getAddress(),packet.getPort());
		socket.send(packet2);
		socket.close();
	}
}