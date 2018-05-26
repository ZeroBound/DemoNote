package IODemo;
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
			//�����ǿ��ļ������ǿ��ļ��оͷ��ؿ�
			//����ǿ�Ŀ¼,����ζ��Ŀ¼��û�����ⶫ��,��ʱ���뵽File����Ķ���Ϊ�ա�������ĳ���Ϊ0
			if(file.isFile()||file.listFiles().length==0){
				System.out.println( "����һ���ļ����߿�Ŀ¼");
			}else{
				File [] files=file.listFiles();
				files=sort(files);//���Բ�������,window�п��Ƚ�������
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
						level++;//����Ŀ¼����,�㼶+1
						IteratorDir(f);
						level--;//Ŀ¼-1,������һ��Ŀ¼����������ͬ����һ��Ŀ¼
					}
				}
			}
		}else{
			return;
		}
	}
	//��û������õ�File���������������
	private static File [] sort(File [] files){
		List<File> fList=new ArrayList<File>();
		for(File f:files){
			//�ȷ�Ŀ¼
			if(f.isDirectory()){
				fList.add(f);
			}
		}
		for(File f:files){
			//�ٷ��ļ�
			if(f.isFile()){
				fList.add(f);
			}	
		}
		//�������ļ����͵�����,�Ƚ�list���͵�ת��ΪFile����
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