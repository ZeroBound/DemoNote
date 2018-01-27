import java.io.*;

public class BufferedInOutStream{
	public static void main(String [] args){
		try{
			BufferFileUtil.copyFile(new File("e:/Print.java"),new File("e:/Print1.java"));
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
}

class BufferFileUtil{
	public static void copyFile(File src,File dst) throws IOException{
		FileInputStream fis=new FileInputStream(src);
		FileOutputStream fos=new FileOutputStream(dst);
		BufferedInputStream bis=new BufferedInputStream(fis);
		BufferedOutputStream bos=new BufferedOutputStream(fos);
		int data=0;
		long startTime=System.currentTimeMillis();
		while((data=bis.read())!=-1){
			bos.write(data);
		}
		bis.close();
		bos.close();
		long endTime=System.currentTimeMillis();
		System.out.println("ºÄÊ±£º"+(endTime-startTime));
	}
}