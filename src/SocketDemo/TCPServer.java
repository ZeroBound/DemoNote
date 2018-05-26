package SocketDemo;
import java.net.*;
import java.io.*;

public class TCPServer{
	public static void main(String [] args) throws IOException{
		//����һ��ServerSocket����
		ServerSocket ss =new ServerSocket(6666);
		//����accept()���������ܿͻ��˵�����
		Socket s = ss.accept();
		System.out.println(s.getInetAddress().getHostAddress()+"has connected!");
		//��ȡsocket��������������
		BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		//�Զ�ˢ��,�൱��pw.flush();
		//PrintWriter pw =new PrintWriter(s.getOutputStream(),true);
		String line=null;
		while((line=br.readLine())!=null){
			if(line.equals("over")){
				break;
			}
			System.out.println(line);
			//pw.println(line);//�൱����������
			bw.write(line.toUpperCase());//��ת���ɴ�д���ַ��������ͻ���
			bw.newLine();
			bw.flush();
			
		}
		br.close();
		bw.close();
		s.close();
		System.out.println(s.getInetAddress().getHostAddress()+"has disconnected!");
	}
}