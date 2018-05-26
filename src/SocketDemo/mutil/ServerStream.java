package SocketDemo.mutil;
import java.io.*;
import java.net.*;

public class ServerStream implements Runnable{
	private Socket socket=null;
	private int number;

	public ServerStream(Socket socket,int number){
		super();
		this.socket=socket;
		this.number=number;
	}
	public void run(){
		try{
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);
			while(true){
				//��ȡ�ͻ��˴���������
				String line=br.readLine();
				System.out.println("�ͻ���"+number+":"+line);
				//�����������ݽ���ת���ɴ�д��д�뵽��������
				pw.println(line.toUpperCase());
				if(line.equals("bye")){
					System.out.println("�ͻ���"+number+":"+"�Ͽ�����");
					break;
				}
			}
			br.close();
			pw.close();
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}