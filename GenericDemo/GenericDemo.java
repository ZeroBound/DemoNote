public class GenericDemo{
	public static void main (String [] args){
		GenericClass<Dog> dogClass = new GenericClass<Dog>();
		dogClass.setObj(new Dog());
		dogClass.getObj().eat();
		
		
	}
}
//限制泛型的类型，只能是animal类型的对象
//限定类型时，不管继承的是抽象类或者是接口，只能使用extends
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