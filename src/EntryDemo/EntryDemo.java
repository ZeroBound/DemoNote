package EntryDemo;

import java.util.*;
public class EntryDemo{
	public static void main(String [] args){
		MyContainer con =new MyContainer();
		con.put("java","222222");
		con.put("android","11111");
		con.put("web","333333");
		con.put("js","4444444");
		con.put("css","5555555");
		con.put("css","5555555");
		MyContainer.Entry [] entrys = con.entryArrays();
		//System.out.println(Arrays.toString(entry));
		for(int i =0 ;i<entrys.length;i++){
			MyContainer.Entry entry = entrys[i];
			System.out.println(entry.getKey()+"--"+entry.getValue());
		}
	}
}
class MyContainer{
	private Entry [] entrys = new Entry[5];
	private int count =0;
	
	public void put(String key,String value){
		Entry entry = new Entry();
		entry.setKey(key);
		entry.setValue(value);
		entrys[count++] = entry;
		if(count>=entrys.length){
			int newCapacity = entrys.length*2;
			entrys =Arrays.copyOf(entrys,newCapacity);
		}
	}
	public Entry [] entryArrays(){
		return Arrays.copyOfRange(entrys,0,count);
	}
	public static class Entry{
		private String key;
		private String value;
		public void setKey(String key){
			this.key = key;
		}
		public String getKey(){
			return key;
		}
		public void setValue(String value){
			this.value = value;
		}
		public String getValue(){
			return value;
		}
	}
}