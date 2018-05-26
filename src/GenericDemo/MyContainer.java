package GenericDemo;
import java.util.*;

public class MyContainer{
	public static void main(String [] args){
		IContainer<String> list = new ArrayList<String>();
		for(int i =0;i<12;i++){
			list.add("data:"+(i+1));
		}
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
}

interface IContainer<T>{
	public void add(T obj);
	public T get(int index);
	public int size();
	
}

class ArrayList<T> implements IContainer<T>{
	private Object [] data = null;
	private int size = 0;
	public ArrayList(){
		data = new Object[10];
	}
	public ArrayList(int capacity){
		data = new Object[capacity];
	}
	public void add(T obj){
		ensureCapacity(size+1);
		data[size++] = obj;
	}
	private void ensureCapacity(int capacity){
		if(capacity>data.length){
			int oldCapacity = data.length;
			int newCapacity = oldCapacity + (oldCapacity>>1);
			data = Arrays.copyOf(data,newCapacity);
		}
	}
	@SuppressWarnings("unchecked")
	public T get(int index){
		return (T)data[index];
	}
	public int size(){
		return size;
	}
}