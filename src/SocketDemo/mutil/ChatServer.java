package SocketDemo.mutil;
import java.net.*;
import java.io.*;

public class ChatServer{
	public static void main(String [] args) throws IOException{
		ServerSocket serverSocket=new ServerSocket(8888);
		int number=1;
		while(true){
			Socket socket=serverSocket.accept();
			System.out.println("�ͻ���"+number+"������");
			new Thread(new ServerStream(socket,number)).start();
			number++;
		}
	}
}