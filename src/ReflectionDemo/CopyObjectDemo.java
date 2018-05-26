import java.lang.reflect.*;

public class CopyObjectDemo{
	public static void main(String [] args){
		//2����һ��ѧ�����󣬱����Ƶ��Ǹ�����Դ����
		Student stu = new Student(1,"zhang",30);
		try{
			Student stu2 = (Student)ObjectCopyUtil.copyObj(stu);
			System.out.println("Ok");
			System.out.println(stu2.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}

//����һ��copy����Ĺ����࣬�ڲ��ṩ��һ��copy����ķ���
class ObjectCopyUtil {
	public static Object copyObj(Object obj) throws Exception{
		//3��ȡStudnet��������Ӧ���͵�class����
		Class<?> classType=obj.getClass();
		//4ͨ��class�����newInstance����������һ��Ŀ�����
		Object objRes = classType.newInstance();
		//5��ȡclass�����get ��set����
		for(Field field:classType.getDeclaredFields()){
			//�õ����Զ�Ӧ��get��set����
			String getMethodName="get"
				+field.getName().substring(0,1).toUpperCase()
				+field.getName().substring(1);
				System.out.println(getMethodName);
			String setMethodName="set"
				+field.getName().substring(0,1).toUpperCase()
				+field.getName().substring(1);
				System.out.println(setMethodName);
			//����Դ�����get������ȡ����ֵ
			Method getMethod = classType.getDeclaredMethod(getMethodName,new Class[]{});
			Object value=getMethod.invoke(obj,new Object[]{});
			//����Ŀ������set�����������Ը�ֵ
			Method setMethod = classType.getDeclaredMethod(setMethodName,new Class[]{field.getType()});
			setMethod.invoke(objRes,new Object[]{value});
		}
		return objRes;
	}
}



//1����һ��ѧ����
class Student{
	private int id;
	private String name;
	private int age;
	
	public Student (){
		
	}
	
	public Student(int id,String name,int age){
		super();
		this.id =id;
		this.name=name;
		this.age=age;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id =id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public int getAge(){
		return age;
	}
	public void setAge(int age){
		this.age=age;
	}
	public String toString(){
		return "Student [id="+id+", name="+name+", age="+age+"]";
	}
	
}
