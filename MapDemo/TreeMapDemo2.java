import java.util.*;
public class TreeMapDemo2{
	public static void main(String [] args){
		//这里是person类型的数据；String Integer中实现了Comparable接口
		//TreeMap<Person,String> pdata = new TreeMap<Person,String>();
		TreeMap<Person,String> pdata = new TreeMap<Person,String>(new Comparator<Person>(){
			@Override
			public int compare(Person o1,Person o2){
				/* if(o1.getAge()-o2.getAge()>0){
					return 1;
				}else if(o1.getAge()-o2.getAge()<0){
					return -1;
				}
				return 0; */
				//按名字排序，但是如果名字相同会被替换
				//return o1.getName().compareTo(o2.getName());
				if(o1.getName().compareTo(o2.getName())>0){
					return 1;
				}else if(o1.getName().compareTo(o2.getName())<0){
					return -1;
				}
				return o1.getAge()-o2.getAge();
			}
		});
		pdata.put(new Person("zzw",23),"zhang");
		pdata.put(new Person("zzw1",24),"zhang1");
		pdata.put(new Person("zzw2",25),"zhang2");
		pdata.put(new Person("zzw3",26),"zhang3");
		System.out.println(pdata);
	}
}
//也要实现Comparable接口
class Person /* implements Comparable<Person> */ {
	private String name;
	private int age;
	
	public Person(String name,int age){
		this.name = name;
		this.age = age;
	}
	
	public String getName(){
		return name;
	}
	public int getAge(){
		return age;
	}
	/* @Override
	public int compareTo(Person o){
		if(this.age-o.getAge()>0){
			return 1;
		}else if(this.age-o.getAge()<0){
			return -1;
		}
		return 0;
	} */
}