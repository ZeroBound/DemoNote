import java.io.*;
import java.util.*;

public class FileDemo{
	public static void main(String [] args) throws Exception{
		File file = new File("e:\\Java");
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getParent());
		System.out.println(file.isDirectory());
		System.out.println(file.isFile());
		System.out.println(file.length());
		/* File file1= new File("e:\\zz.txt");
		System.out.println(file1.createNewFile());
		System.out.println(new File("e:/zzz").mkdir()); */
		
		/* String [] files=file.list();
		for(String f:files){
			System.out.println(f);
		} */
		
		//Fileter¹ýÂËÆ÷
		/* String [] files=file.list(new FilenameFilter(){
			public boolean accept(File dir,String name){
				return name.endsWith(".txt");
			}
		});
		for(String f:files){
			System.out.println(f);
		} */
		
		
		File [] fileN =new File("E:\\zzz").listFiles();
		for(File f:fileN){
			System.out.println(f.getName()+"--"+f.length());
		}
		System.out.println(Arrays.toString(fileN));
		System.out.println(fileN.length);
	}
}