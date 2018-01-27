import java.io.*;
//从指定文件夹中复制所有的内容到另一个指定的文件夹中
public class CopyDirDemo{
	public static void main(String [] args){
		try{
			File src=new File("e:\\StudyNote");
			File dst=new File("e:\\a");
			CopyDirUtil.copyDir(src,dst);
			System.out.println("success");
			//dst.delete();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}

class CopyDirUtil{
	public static void copyDir(File src,File dst) throws IOException{
		if(!dst.exists()){
			dst.mkdirs();
		}else{
			System.out.println("存在"+dst.getAbsolutePath()+"该目录,将覆盖它");
		}
		if(src!=null){
			if(src.isFile()||src.listFiles().length==0){
				System.out.println("只能复制目录且目录不能为空");
			}else{
				File [] files =src.listFiles();
				for(File f:files){
					//如果遍历的是文件则复制文件
					if(f.isFile()){
						FileInputStream fis=new FileInputStream(f);
						FileOutputStream fos =new FileOutputStream(dst.getAbsolutePath()+"\\"+f.getName());
						//使用字节数组提高性能
						byte [] buf= new byte[1024*1024];
						int len=0;//读取的文件可能没有放满字节数组,保存最后读取的位置.
						while((len=fis.read(buf))!=-1){
							//从字节数组中开始的位置到结束的位置进行读取
							fos.write(buf,0,len);
						}
						fis.close();
						fos.flush();
						fos.close();
					}else{
						copyDir(f,new File(dst.getAbsolutePath()+"\\"+f.getName()));
					}
				}
			}
			
		}
	}
}