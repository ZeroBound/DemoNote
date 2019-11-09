package ReflectionDemo;

public class ReflectionAPIDemo{
	public static void main (String [] args) throws Exception{
		//获取Employee这个类所关联的class对象
		Class<?> classType = Class.forName("Employee");
		//通过反射机制来构造一个Emplo的实例对象，默认调用无参的构造方法
		Employee employee=(Employee)classType.newInstance();

		//调用指定的构造方法来构造对象，无参的构造方法

	}
}

class Employee{
	private String name;
	private int age;

	public Employee(){
		System.out.println("_____");
	}
	public Employee(String name,int age){
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