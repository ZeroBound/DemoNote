public class ReflectionAPIDemo{
	public static void main (String [] args) throws Exception{
		//��ȡEmployee�������������class����
		Class<?> classType = Class.forName("Employee");
		//ͨ���������������һ��Emplo��ʵ������Ĭ�ϵ����޲εĹ��췽��
		Employee employee=(Employee)classType.newInstance();
	
		//����ָ���Ĺ��췽������������޲εĹ��췽��
		
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