package SocketDemo.mutil;
import java.io.*;
import java.net.*;

public class ClientInputStream implements Runnable{
	private Socket socket=null;
	public ClientInputStream(Socket socket){
		super();
		this.socket=socket;
	}
	public void run(){
		try{
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true){
				try{
					//读取服务器端写出的数据
					String line=br.readLine();
					System.out.println(line+"111");
				}catch(SocketException e){
					break;
				}
			}
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}