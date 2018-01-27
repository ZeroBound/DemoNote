import java.lang.reflect.*;

public class CopyObjectDemo{
	public static void main(String [] args){
		//2生成一个学生对象，被复制的那个对象，源对象
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

//这是一个copy对象的工具类，内部提供了一个copy对象的方法
class ObjectCopyUtil {
	public static Object copyObj(Object obj) throws Exception{
		//3获取Studnet对象所对应类型的class对象
		Class<?> classType=obj.getClass();
		//4通过class对象的newInstance方法来构建一个目标对象
		Object objRes = classType.newInstance();
		//5获取class对象的get 和set方法
		for(Field field:classType.getDeclaredFields()){
			//得到属性对应的get和set方法
			String getMethodName="get"
				+field.getName().substring(0,1).toUpperCase()
				+field.getName().substring(1);
				System.out.println(getMethodName);
			String setMethodName="set"
				+field.getName().substring(0,1).toUpperCase()
				+field.getName().substring(1);
				System.out.println(setMethodName);
			//调用源对象的get方法获取属性值
			Method getMethod = classType.getDeclaredMethod(getMethodName,new Class[]{});
			Object value=getMethod.invoke(obj,new Object[]{});
			//调用目标对象的set方法来给属性赋值
			Method setMethod = classType.getDeclaredMethod(setMethodName,new Class[]{field.getType()});
			setMethod.invoke(objRes,new Object[]{value});
		}
		return objRes;
	}
}



//1声明一个学生类
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
