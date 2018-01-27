import java.net.*;
import java.io.*;

public class URLDemo{
	public static void main(String [] args){
		try{
			DownLoadUtil.download("http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=cfb53f93c3177f3e0439f44e18a651b2/6609c93d70cf3bc7814060c9db00baa1cd112a56.jpg","pic.jpg","e:\\a");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

class DownLoadUtil{
	public static void download(String urlString,String fileName,String savePath) throws IOException{
		URL url=new URL(urlString);
		URLConnection conn=url.openConnection();
		InputStream is = conn.getInputStream();
		//InputStream is =url.openStream();
		byte [] buf= new byte[1024];
		int len=0;
		File file=new File(savePath);
		if(!file.exists()){
			file.mkdirs();
		}
		OutputStream os = new FileOutputStream(file.getAbsolutePath()+File.separator+fileName);
		while((len=is.read(buf))!=-1){
			os.write(buf,0,len);
		}
		os.flush();
		is.close();
		os.close();
	}
}