import java.io.*;
import java.util.*;

public class IteratorDirDemo{
	public static void main(String [] args){
		IteratorUtil.IteratorDir(new File("e:/StudyNote"));
	}
}

class IteratorUtil{
	private static int level=0;
	public static void IteratorDir(File file){
		if(file!=null){
			//假设是空文件或者是空文件夹就返回空
			//如果是空目录,则意味着目录下没有任意东西,这时读入到File数组的东西为空。即数组的长度为0
			if(file.isFile()||file.listFiles().length==0){
				System.out.println( "这是一个文件或者空目录");
			}else{
				File [] files=file.listFiles();
				files=sort(files);//可以不用排序,window中可先进行排序
				//System.out.println(Arrays.toString(files));
				for(File f:files){
					StringBuilder sb =new StringBuilder();
					if(f.isFile()){
						sb.append(getTab(level));
						sb.append(f.getName());
					}else{
						sb.append(getTab(level));
						sb.append(f.getName());
						sb.append("\\");
					}
					System.out.println(sb.toString());
					if(f.isDirectory()){
						level++;//进入目录遍历,层级+1
						IteratorDir(f);
						level--;//目录-1,返回上一级目录，继续遍历同级下一个目录
					}
				}
			}
		}else{
			return;
		}
	}
	//对没有排序好的File类型数组进行排序
	private static File [] sort(File [] files){
		List<File> fList=new ArrayList<File>();
		for(File f:files){
			//先放目录
			if(f.isDirectory()){
				fList.add(f);
			}
		}
		for(File f:files){
			//再放文件
			if(f.isFile()){
				fList.add(f);
			}	
		}
		//方法是文件类型的数组,先将list类型的转换为File类型
		return fList.toArray(new File[fList.size()]);
	}
	private static String getTab(int level){
		StringBuilder sb =new StringBuilder();
		for(int i=0;i<level;i++){
			sb.append("\t");
		}
		return sb.toString();
	}
}