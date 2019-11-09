package ThreadDemo.Productor;
import java.util.*;

/**
 * @author zzw
 */
public class ProductorConsumerDemo{
	public static void main(String [] args){
		Basket basket=new Basket();
		Productor productor=new Productor(basket);
		Consumer consumer=new Consumer(basket);
		productor.start();
		consumer.start();
	}
}

/**
 * 产品类
 */
class Productor extends Thread{
	private Basket basket=null;
	
	public Productor(Basket basket){
		super();
		this.basket=basket;
	}
	@Override
	public void run(){
		basket.pushApple();
	}
}

/**
 * 客户类
 */
class Consumer extends Thread{
	private Basket basket=null;
	
	public Consumer(Basket basket){
		super();
		this.basket=basket;
	}
	@Override
	public void run(){
		basket.popApple();
	}
}



class Basket{
	private static final int CAPS = 20;
	private LinkedList<Apple> basket=new LinkedList<Apple>();

	/**
	 *
	 */
	public synchronized void pushApple(){
		for(int i=0;i<CAPS;i++){
			Apple apple=new Apple(i);
			push(apple);
		}
	}
	/***/
	public synchronized void popApple(){
		for(int i=0;i<CAPS;i++){
			pop();
		}
	}


	/***/
	private void push(Apple apple){
		if(5 == basket.size()){
			try{
				wait();//等待线程
			}catch(Exception e){
				e.printStackTrace();
			}
			try{
				Thread.sleep(500);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		basket.addFirst(apple);
		System.out.println("放入 "+apple.toString());
		notify();// 通知其他线程
	}
	/***/
	private void pop(){
		if(basket.size()==0){
			try{
				wait();
			}catch(Exception e){
				e.printStackTrace();
			}
			try{
				Thread.sleep(500);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		Apple apple=basket.removeFirst();
		System.out.println("取出 "+apple.toString());
		notify();
	}
}

/**
 * 苹果类
 */
class Apple{
	private int id;
	public Apple(int id){
		this.id=id;
	}
	
	@Override
	public String toString(){
		return "Apple "+(id+1);
	}
}