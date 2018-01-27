public class AnimalDemo{
	public static void main(String [] args){
		Animal animal1 = new Dog("dogs");
		Animal animal2 = new Cat("cats");
		animal1.eat();
		animal2.eat();
		
		// if(animal1 instanceof Cat){
			// Cat cat = (Cat)animal1;
			// System.out.println("---------");
		// }
		if(animal2 instanceof Cat){
			Cat cat = (Cat)animal2;
			System.out.println("---------");
		}

	}
}

class Animal{
	public String name;
	public Animal(String name){
		this.name =name;
	}
	public void eat(){
		System.out.println("Animal eat");
	}
}
class Dog extends Animal{
	public Dog(String name){
		super(name);
	}
	public String getName(){
		return name;
	}
	public void eat(){
		System.out.println(getName()+"Dog eat");
	}
	public void sleep(){
		System.out.println("Dog sleep");
	}
}
class Cat extends Animal{
	public Cat (String name){
		super(name);
	}
	public void eat(){
		System.out.println("Cat eat");
	}
}