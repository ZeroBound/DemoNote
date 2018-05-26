package IODemo.InputStreamOutputStream;
import java.io.*;

public class DataInOutStream{
	public static void main(String [] args) throws IOException{
		String name="zhang";
		int age=10;
		boolean flag=true;
		char sex='男';
		double money=100.56;
		
		DataOutputStream dos =new DataOutputStream(new FileOutputStream(new File("e:\\z.txt")));
		dos.writeUTF(name);
		dos.writeInt(age);
		dos.writeBoolean(flag);
		dos.writeChar(sex);
		dos.writeDouble(money);
		dos.close();
		dos.close();
	}
}
