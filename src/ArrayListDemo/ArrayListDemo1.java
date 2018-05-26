package  ArrayListDemo;
import java.util.*;

public class ArrayListDemo1{
	public static void main (String [] args){
		List<String> nList = new ArrayList<String>();
		nList.add("zhangziwen");
		nList.add("lisi");
		nList.add("lisi");
		nList.add("wangwu");
		nList.add(1,"adddad");
		nList.add("liuqi");
		nList.set(0,"zhangzw");
		System.out.println("ʹ�õ��������б�����");
		Iterator<String> it = nList.iterator();
		while(it.hasNext()){
			String name = it.next();
			System.out.println(name);
		}
		System.out.println("\nʹ����ǿforѭ�����б�����");
		for(String name:nList){
			System.out.println(name);
		}
		System.out.println("=========================");
		System.out.println(nList.indexOf("lisi"));//����-1��ʾû�г��ֹ�
		System.out.println(nList.remove("lishi"));
		System.out.println(nList.remove(2));
		System.out.println(nList.size());
		System.out.println(nList.contains("z"));
		System.out.println(nList.get(0));
		nList.clear();
		System.out.println(nList.isEmpty());
	}
}