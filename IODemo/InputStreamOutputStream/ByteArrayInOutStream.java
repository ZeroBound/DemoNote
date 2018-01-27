import java.io.*;

public class ByteArrayInOutStream{
	public static void main(String [] args) throws IOException{
		String str="hello,shanghai";
		//不会消耗系统资源
		ByteArrayInputStream bis=new ByteArrayInputStream(str.getBytes());
		int data;
		while((data=bis.read())!=-1){
			System.out.println((char)data);
		}
		ByteArrayOutputStream bos =new ByteArrayOutputStream();
		bos.write(97);
		bos.write(65);
		bos.write("hello".getBytes());
		byte [] buff =bos.toByteArray();
		for(byte data1:buff){
			System.out.print((char)data1);
		}
	}
}
