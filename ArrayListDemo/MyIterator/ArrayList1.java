public class ArrayList1<T> implements List1<T>{
	private Object [] obj = null;
	private int index;
	private int size;
	
	public ArrayList1(){
		obj =new Object[10];
		index = 0;
		size = 0;
	}
	
	@Override
	public void add(T obj){
		this.obj[index++] =obj;
		size++;
	}
	@SuppressWarnings("unchecked")
	@Override
	public T get(int index){
		return (T)this.obj[index];
	}
	@Override
	public int size(){
		return size;
	}
	@Override
	public Iterator1<T> iterator1(){
		return  new MyIterator<T>(this);
	}
}