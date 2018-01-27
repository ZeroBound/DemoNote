import java.util.*;

public class LinkedListDemo2{
	public static void main(String [] args){
		MyStack<String> myStack =new MyStack<String>();
		myStack.push("zhang1");
		myStack.push("zhang2");
		myStack.push("zhang3");
		myStack.push("zhang4");
		myStack.push("zhang4");
		myStack.pop();
		myStack.pop();
		System.out.println("=================");
		Iterator<String> it = myStack.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		MyQueue<Integer> myQueue = new MyQueue<Integer>();
		myQueue.push(1);
		myQueue.push(2);
		myQueue.push(3);
		myQueue.push(4);
		myQueue.pop();
		myQueue.pop();
		System.out.println("++++++++++++++++++++");
		Iterator<Integer> it1 = myQueue.iterator();
		while(it1.hasNext()){
			System.out.println(it1.next());
		}
		
	}
}

class MyStack<T>{
	private LinkedList<T> data = null;
	public MyStack(){
		data = new LinkedList<T>();
	}
	
	public void push(T obj){
		data.addFirst(obj);
	}
	public T pop(){
		return data.removeFirst();
	}
	
	public Iterator<T> iterator(){
		return data.iterator();
	}
}

class MyQueue<T>{
	private LinkedList<T> data=null;
	public MyQueue(){
		data = new LinkedList<T>();
	}
	public void push(T obj){
		data.addLast(obj);
	}
	public T pop(){
		return data.removeLast();
	}
	public Iterator<T> iterator(){
		return data.iterator();
	}
}