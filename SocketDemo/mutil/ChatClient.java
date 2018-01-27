import java.net.*;
import java.io.*;

public class ChatClient{
	public static void main(String [] args) throws IOException{
		Socket socket=new Socket("127.1.0.1",8888);
		new Thread(new ClientOutputStream(socket)).start();
		new Thread(new ClientInputStream(socket)).start();
		
	}
}