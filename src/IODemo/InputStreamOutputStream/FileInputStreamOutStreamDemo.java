package IODemo.InputStreamOutputStream;
import java.io.*;

public class FileInputStreamOutStreamDemo{
	public static void main(String [] args){
		try{
			FileCopyUtil.copyFile(new File("e:/Print.java"),new File("e:/Print1.java"));
		}catch(IOException e){
			e.printStackTrace();
		}

	}
}

class FileCopyUtil{
	public static void copyFile(File src,File dst) throws IOException{
		//��ʱ�ϳ�
		FileInputStream fis =new FileInputStream(src);
		FileOutputStream fos =new FileOutputStream(dst);
		long startTime=System.currentTimeMillis();
		/* int data;
		while((data=fis.read())!=-1){
			fos.write(data);
		}
		fis.close();
		fos.close();*/
		byte [] buf = new byte[1024*1024];
		int len=0;//��������ʵ�ʶ������ֽ���
		while((len=fis.read(buf))!=-1){
			fos.write(buf,0,len);
		}
		fis.close();
		
		fos.close();
		long endTime=System.currentTimeMillis();
		System.out.println("��ʱ:"+(endTime-startTime)); 
	}
}