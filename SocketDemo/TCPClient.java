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
			bw.write(line);//把转换成大写的字符串传给客户端
			bw.newLine();//服务器以行读取,读取到换行才会返回值
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