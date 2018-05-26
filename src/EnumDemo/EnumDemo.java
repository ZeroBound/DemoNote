package EnumDemo;

import java.util.*;
public class EnumDemo{
	public static void main(String [] args){
		/* System.out.println(Color.RED);
		Color [] colors = Color.values();
		System.out.println(Arrays.toString(colors));
		for (Color c:colors){
			System.out.println(c);
		} */
		System.out.println(Person.P1.toString());
		Person p = Person.P3;
		switch(p){
			case P1:
				System.out.println(Person.P1);
				break;
			case P2:
				System.out.println(Person.P2);
				break;
			case P3:
				System.out.println(Person.P3);
				break;
		}
	}
}

enum Color{
	BULE,RED;
	private Color(){
		System.out.println("���췽��");
	}
/* 	���������䣬������ö�ٵ�ֵ
	public String toString(){
		return "aa";
	} */
}

enum Person{
	P1("zhang",11),P2("li",56),P3("wwwww",123);
	private String name;
	private int age;
	private Person(String name,int age){
		this.name = name;
		this.age = age;
	}
	public String toString(){
		return name+"---"+age;
	}
}