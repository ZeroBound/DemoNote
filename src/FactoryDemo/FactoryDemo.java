package FactoryDemo;
public class FactoryDemo{
	public static void main(String [] args){
		IFruit fruit= Factory.getFruit("apple");
		if(fruit!=null){
			System.out.println(fruit.get());
		}else{
			System.out.println("sorry");
		}
	}
}

interface IFruit{
	public String get();
}
class Factory{
	public static IFruit getFruit(String name){
		if(name.equals("apple")){
			return new Apple();
		}else if(name.equals("orange")){
			return new Orange();
		}else{
			return null;
		}
	}
}
class Apple implements IFruit{
	public String get(){
		return "apple";
	}
}

class Orange implements IFruit{
	public String get(){
		return "Orange";
	}
}