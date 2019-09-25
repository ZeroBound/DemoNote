package uiautoz.zzw.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zzw
 *
 * 执行 adb 以及 adb shell
 */
public class ShellUtils {
	public static final String NEW_LINE = System.lineSeparator();
	private static final String OS_NAME = System.getenv("OS");
	private static final String ADB = OS_NAME.contains("Window") ? "adb.exe" : "adb";
	
	
	/**
	 * Check Android Home
	 * @return adb abspath
	 */
	public static String getCheckAndroidHome(){
		String adbPath = File.separator + "platform-tools" + File.separator + ADB;
		String androidHome = System.getenv("ANDROID_HOME");

		if (androidHome == null) {
			System.err.println("Adb not found in $ANDROID_HOME path: " + null);
			System.exit(1);
		}
		
		String path = androidHome + File.separator + ADB;
		if(new File(path).exists()){
			return path;
		}
		return androidHome + adbPath;
	}
	
	/**
	 * Run adb command
	 * @param cmd adb command
	 * @return Process obj
	 */
	public static Process cmd(String cmd){
		return process(getCheckAndroidHome()+" "+ cmd);
	}
	
	/**
	 * Run adb shell command
	 * @param cmd adb shell command
	 * @return Process obj
	 */
	public static Process shell(String cmd){
		return process(getCheckAndroidHome()+" shell "+cmd);
	}
	
	/**
	 * Get process output stream
	 * @param ps Process obj
	 * @return adb out data
	 */
	public static String getShellOut(Process ps){
		StringBuilder sb = new StringBuilder();
		BufferedReader br = shellOut(ps);
		String line;
		try{
			while((line = br.readLine())!=null){
				sb.append(line).append(NEW_LINE);
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return sb.toString().trim();
	}
	
	/**
	 * Process input stream
	 * @param ps Process obj
	 * @return  BufferedReader Stream
	 */
	public static BufferedReader shellOut(Process ps){
		BufferedInputStream bis = new BufferedInputStream(ps.getInputStream());
		return new BufferedReader(new InputStreamReader(bis));
	} 
	
	/**
	 * 
	 * @param command cmd
	 * @return Process object
	 */
	private static Process process(String command){
		//
		Process process=null;
		try{
			process = Runtime.getRuntime().exec(command);
		}catch(IOException e){
			e.printStackTrace();
		}
		return process;
	}
}
