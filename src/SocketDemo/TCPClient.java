package SocketDemo;
import java.net.Socket;
import java.io.*;

public class TCPClient{
	public static void main(String [] args) throws IOException{
		Socket s=new Socket("127.0.0.1",6666);
	
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		BufferedReader br1=new BufferedReader(new InputStreamReader(s.getInputStream()));
		while(true){
			String line=br.readLine();
			bw.write(line);//��ת���ɴ�д���ַ��������ͻ���
			bw.newLine();//���������ж�ȡ,��ȡ�����вŻ᷵��ֵ
			bw.flush();
			if(line.equals("over")){
				break;
			}
			System.out.println(br1.readLine());
		}
		br1.close();
		br.close();
		bw.close();
		s.close();
	}
}