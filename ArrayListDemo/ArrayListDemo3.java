import java.util.*;

public class ArrayListDemo3{
	public static void main(String [] args){
		List<Object> aList1=new ArrayList<Object>();
		aList1.add("dname");
		aList1.add(new Integer(0));
		//aList1.add(2);
		aList1.add(1);
		List<Object> aList=new ArrayList<Object>();
		aList.add("1");
		aList.add("dname");
		aList.add(0);
		aList.add(1);
		for(Object a:aList){
			System.out.println(a);
		}
		System.out.println("--------------------");
		for(Object b:aList1){
			System.out.println(b);
		}
		System.out.println(aList.containsAll(aList1));
		
	}
}