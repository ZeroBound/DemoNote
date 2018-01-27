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
		System.out.println("使用迭代器进行遍历：");
		Iterator<String> it = nList.iterator();
		while(it.hasNext()){
			String name = it.next();
			System.out.println(name);
		}
		System.out.println("\n使用增强for循环进行遍历：");
		for(String name:nList){
			System.out.println(name);
		}
		System.out.println("=========================");
		System.out.println(nList.indexOf("lisi"));//返回-1表示没有出现过
		System.out.println(nList.remove("lishi"));
		System.out.println(nList.remove(2));
		System.out.println(nList.size());
		System.out.println(nList.contains("z"));
		System.out.println(nList.get(0));
		nList.clear();
		System.out.println(nList.isEmpty());
	}
}