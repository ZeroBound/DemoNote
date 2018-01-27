import java.io.*;

public class BufferedReaderWriterDemo{
	public static void main(String [] args) throws IOException{
		FileReader fr=new FileReader("e:\\a.txt");
		BufferedReader bfr=new BufferedReader(fr);
		FileWriter fw=new FileWriter("e:\\a");
		BufferedWriter bfw=new BufferedWriter(fw);
		String line=null;
		while((line=bfr.readLine())!=null){
			System.out.println(line);
			bfw.write(line);
			bfw.newLine();
		}
		bfr.close();
		bfw.flush();
		bfw.close();
	}
}