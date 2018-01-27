import java.io.*;

public class FileReaderWriterDemo{
	public static void main(String [] args) throws IOException{
		FileReader fr =new FileReader("e:\\a.txt");
		FileWriter fw =new FileWriter("e:\\a");
		char [] buf=new char[1024];
		int len=0;
		while((len=fr.read(buf))!=-1){
			fw.write(buf,0,len);
		}
		fr.close();
		fw.flush();
		fw.close();
	}
}