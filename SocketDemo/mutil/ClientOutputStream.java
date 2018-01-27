import java.io.*;
import java.net.*;

public class ClientOutputStream implements Runnable{
	private Socket socket=null;
	
	public ClientOutputStream(Socket socket){
		super();
		this.socket=socket;
	}
	
	public void run(){
		try{
			BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
			PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);
			while(true){
				//读取输入的字符串
				String line=br.readLine();
				System.out.println(line+"222");
				//将字符写入到outputStream中
				pw.println(line);
				if(line.equals("bye")){
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