import java.util.*;

public class HashMapDemo2{
	public static void main(String [] args){
		Map<Student,String> map = new HashMap<Student,String>();
		map.put(new Student("zhang4",11),"1");
		map.put(new Student("zhang3",12),"2");
		map.put(new Student("zhang2",13),"3");
		map.put(new Student("zhang1",14),"4");
		map.put(new Student("zhang1",14),"4");
		System.out.println(map);
		System.out.println(map.size());
	}
	
}
class Student{
	private String name;
	private int age;
	@Override
	public int hashCode(){
		final int prime =31;
		int result = 1;
		result = prime*result +age;
		result = prime*result +((name ==null ? 0 :name.hashCode()));
		return result;
	}
	@Override
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		if(obj == null||getClass()!= obj.getClass())
			return false;
		Student other = (Student)obj;
		if(age!=other.age)
			return false;
		if(name == null){
			if(other.name!=null)
				return false;
		}else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
	public Student(String name,int age){
		this.name = name;
		this.age = age;
	}
	public String getName(){
		return name;
	}
	public int getAge(){
		return age;
	}
	
}