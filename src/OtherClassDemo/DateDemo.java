package OtherClassDemo;
import java.util.*;
import java.text.*;
public class DateDemo{
	public static void main(String [] args) throws Exception{
		Date a = new Date();
		System.out.println(a);
		System.out.println(a.getTime());
		
		/* DateFormat df1 = null;
		DateFormat df2 = null;
		df1 = DateFormat.getDateInstance();
		df2 = DateFormat.getDateTimeInstance();
		System.out.println("date:"+df1.format(a));
		System.out.println("dateTime:"+df2.format(a));
		
		df1 = DateFormat.getDateInstance(DateFormat.FULL,new Locale("zh","CN"));
		System.out.println("========:"+df1.format(a)); */
		String str = "2010-10-09 10:11:30.345";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		Date d = sdf.parse(str);
		System.out.println(d);
	}
}