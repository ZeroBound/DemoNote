package uiautoz.zzw.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zzw
 */
public class ReUtils {

	/**
	 * 正则提取字符串
	 * @param pattern pattern
	 * @param str string
	 * @return ArrayList String
	 */
	public static ArrayList<String> matchString(Pattern pattern, String str) {
		ArrayList<String> result = new ArrayList<String>();
		Matcher mc = pattern.matcher(str);
		
		while (mc.find()) {
			result.add(mc.group());
		}
		
		return result;
	}

	/**
	 * 正则提取数字
	 * @param pattern pattern
	 * @param str string
	 * @return ArrayList Integer
	 */
	public static ArrayList<Integer> matchInteger(Pattern pattern, String str) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Matcher mc = pattern.matcher(str);
		
		while (mc.find()) {
			result.add(Integer.valueOf(mc.group().trim()));
		}
		
		return result;
	}
	
}
