package IODemo.InputStreamOutputStream;
import java.io.*;
//��ָ���ļ����и������е����ݵ���һ��ָ�����ļ�����
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
			System.out.println("����"+dst.getAbsolutePath()+"��Ŀ¼,��������");
		}
		if(src!=null){
			if(src.isFile()||src.listFiles().length==0){
				System.out.println("ֻ�ܸ���Ŀ¼��Ŀ¼����Ϊ��");
			}else{
				File [] files =src.listFiles();
				for(File f:files){
					//������������ļ������ļ�
					if(f.isFile()){
						FileInputStream fis=new FileInputStream(f);
						FileOutputStream fos =new FileOutputStream(dst.getAbsolutePath()+"\\"+f.getName());
						//ʹ���ֽ������������
						byte [] buf= new byte[1024*1024];
						int len=0;//��ȡ���ļ�����û�з����ֽ�����,��������ȡ��λ��.
						while((len=fis.read(buf))!=-1){
							//���ֽ������п�ʼ��λ�õ�������λ�ý��ж�ȡ
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