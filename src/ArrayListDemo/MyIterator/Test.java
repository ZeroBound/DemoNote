package  ArrayListDemo.MyIterator;

public class Test{
	public static void main(String [] args){
		List1<String> nameList = new ArrayList1<String>();
		nameList.add("zhang");
		nameList.add("zhang1");
		nameList.add("zhang2");
		
		Iterator1<String> it = nameList.iterator1();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		System.out.println("=========");
		/* for(String name:nameList){
			System.out.println(name);
		}*/
	} 
}