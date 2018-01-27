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
		//耗时较长
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
		int len=0;//用来保存实际读到的字节数
		while((len=fis.read(buf))!=-1){
			fos.write(buf,0,len);
		}
		fis.close();
		
		fos.close();
		long endTime=System.currentTimeMillis();
		System.out.println("耗时:"+(endTime-startTime)); 
	}
}