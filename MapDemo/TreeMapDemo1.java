import java.util.*;
public class TreeMapDemo1{
	public static void main(String [] args){
		TreeMap<String , String> tmap = new TreeMap<String,String>();
		tmap.put("zhang1","age1");
		tmap.put("zhang2","age2");
		tmap.put("zhang3","age3");
		tmap.put("zhang4","age4");
		System.out.println(tmap);
		
		Set<String> keys = tmap.keySet();
		for(String key:keys){
			//return value
			//System.out.println("Key:"+ tmap.get(key));
			System.out.println(key);
		}
		Collection<String> values = tmap.values();
		for(String value:values){
			System.out.println(value);
		}
		
		Set<Map.Entry<String,String>> entrys = tmap.entrySet();
		for(Map.Entry<String,String> entry:entrys){
			System.out.println(entry.getKey()+"---"+entry.getValue());
		}
	}
}