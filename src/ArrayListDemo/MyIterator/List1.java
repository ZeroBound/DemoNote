package  ArrayListDemo.MyIterator;

public interface List1<T>{
	public void add (T obj);
	public T get(int index);
	public int size();
	public Iterator1<T> iterator1();
}