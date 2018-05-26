package GenericDemo;
public class GenericDemo{
	public static void main (String [] args){
		GenericClass<Dog> dogClass = new GenericClass<Dog>();
		dogClass.setObj(new Dog());
		dogClass.getObj().eat();
		
		
	}
}
//���Ʒ��͵����ͣ�ֻ����animal���͵Ķ���
//�޶�����ʱ�����ܼ̳е��ǳ���������ǽӿڣ�ֻ��ʹ��extends
class GenericClass<T extends Animal> {
	private T obj;
	public void setObj(T obj){
		this.obj = obj;
	}
	public T getObj(){
		return obj;
	}
}
 

abstract class Animal{
	 public abstract void eat();
}

/* interface Animal{
	public abstract void eat();
} */

class Dog extends Animal{
	public void eat(){
		System.out.println("eat gutou");
	}
}

class Cat extends Animal{
	public void eat(){
		System.out.println("eat yu");
	}
}