import java.util.*;

public class ArrayListDemo2{
	public static void main(String [] args){
		List<Student> stuList = new ArrayList<Student>();
		Student stu1 = new Student("zhang",11);
		Student stu2 = new Student("lishi",20);
		Student stu3 = new Student("wangwu",21);
		Student stu4 = new Student("liuli",30);
		Student stu5 = new Student("qikong",51);
		stuList.add(stu1);
		stuList.add(stu2);
		stuList.add(stu3);
		stuList.add(stu4);
		stuList.add(stu5);
		Student stu6 = new Student("lishi",20);
		System.out.println(stuList.indexOf(stu6));
	}
}

class Student{
	private String name;
	private int age;
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