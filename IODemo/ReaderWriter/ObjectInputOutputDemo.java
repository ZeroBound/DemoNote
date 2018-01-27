import java.io.*;

public class ObjectInputOutputDemo{
	public static void main(String [] args) throws IOException{
		/* Student stu = new Student("zhang",10);
		FileOutputStream fos =new FileOutputStream("e:\\a.txt");
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeObject(stu);
		oos.close();  */
		//反序列化
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream("e:\\a.txt"));
		try{
			Student stu=(Student)ois.readObject();
			System.out.println(stu);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
}
//不实现Serializable接口的话会出现报错
class Student implements Serializable{
	private String name;
	private int age;
	public Student(String name,int age){
		this.name=name;
		this.age=age;
	}
	public String getName(){
		return name;
	}
	public int getAge(){
		return age;
	}
	public String toString(){
		return "Student[name="+"name"+",age="+age+"]";
	}
}