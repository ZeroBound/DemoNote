import java.net.*;
import java.io.*;

public class TCPServer{
	public static void main(String [] args) throws IOException{
		//创建一个ServerSocket对象
		ServerSocket ss =new ServerSocket(6666);
		//调用accept()方法来接受客户端的请求
		Socket s = ss.accept();
		System.out.println(s.getInetAddress().getHostAddress()+"has connected!");
		//获取socket对象的输入输出流
		BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		//自动刷新,相当于pw.flush();
		//PrintWriter pw =new PrintWriter(s.getOutputStream(),true);
		String line=null;
		while((line=br.readLine())!=null){
			if(line.equals("over")){
				break;
			}
			System.out.println(line);
			//pw.println(line);//相当于以下三条
			bw.write(line.toUpperCase());//把转换成大写的字符串传给客户端
			bw.newLine();
			bw.flush();
			
		}
		br.close();
		bw.close();
		s.close();
		System.out.println(s.getInetAddress().getHostAddress()+"has disconnected!");
	}
}