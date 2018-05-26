package  ArrayListDemo;
import java.util.*;

public class LinkedListDemo1{
	public static void main(String [] args){
		LinkedList<String> sList = new LinkedList<String>();
		sList.add("zhang");
		sList.add("lishi");
		sList.add("wangli");
		sList.add("javak");
		sList.add("mary");
		sList.addFirst("ziwen");
		sList.addLast("chengaa");
		Iterator<String> it = sList.iterator();
		while(it.hasNext()){
			String name = it.next();
			System.out.println(name);
		}
		System.out.println("\n====================");
		for(String name:sList){
			System.out.println(name);
		}
		System.out.println(sList.removeFirst());
		System.out.println(sList.size());
		sList.clear();
	}
}