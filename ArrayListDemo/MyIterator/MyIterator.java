public class MyIterator<T> implements Iterator1<T>{
	private List1<T> list =null;
	private int index=0;
	
	public MyIterator(List1<T> list){
		this.list = list;
	}
	
	
	@Override
	public boolean hasNext(){
		return index<list.size();
	}
	@Override
	public T next(){
		return list.get(index++);
	}
}