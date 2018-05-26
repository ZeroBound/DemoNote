package MapDemo;
import java.util.*;
public class HashMapDemo1{
	public static void main(String [] args){
		Map<String ,String> hmap =new HashMap<String,String>();
		hmap.put("1","zhang1");
		hmap.put("2","zhang2");
		hmap.put("3","zhang3");
		hmap.put("4","zhang3");
		hmap.put("4","zhang");
		System.out.println(hmap);
		
		Set<String> keys = hmap.keySet();
		for(String key:keys){
			System.out.println(key+"--"+hmap.get(key));
		}
		Collection<String> values = hmap.values();
		for(String value:values){
			System.out.println(value);
		}
		System.out.println(hmap.size());
		
	
		Set<Map.Entry<String,String>> entrys = hmap.entrySet();
		for(Map.Entry<String,String> entry:entrys){
			System.out.println(entry.getKey()+"--"+entry.getValue());
		}
	}
}