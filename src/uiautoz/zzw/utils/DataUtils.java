package uiautoz.zzw.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zzw
 */
public class DataUtils {

	/**
	 *
	 * @return 指定的格式的系统当前时间
	 */
	public static String getCurrentSystemTime() {
		//
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		// new Date()
		return dateFormat.format(new Date());
	}
	
	public static String getCalendar() {

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1; 
		int day = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);

		return year + "-" + month + "-" + day + "-" + hour + "-"
				+ minute + "-" + second;
	}
}
