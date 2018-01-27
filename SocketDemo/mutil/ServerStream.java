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
				//获取客户端传来的数据
				String line=br.readLine();
				System.out.println("客户端"+number+":"+line);
				//将传来的数据进行转换成大写并写入到数据流中
				pw.println(line.toUpperCase());
				if(line.equals("bye")){
					System.out.println("客户端"+number+":"+"断开连接");
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