import java.util.*;

public class ProductorConsumerDemo{
	public static void main(String [] args){
		Basket basket=new Basket();
		Productor productor=new Productor(basket);
		Consumer consumer=new Consumer(basket);
		productor.start();
		consumer.start();
	}
}

//生产者
class Productor extends Thread{
	private Basket basket=null;
	
	public Productor(Basket basket){
		super();
		this.basket=basket;
	}
	public void run(){
		basket.pushApple();
	}
}
//消费者
class Consumer extends Thread{
	private Basket basket=null;
	
	public Consumer(Basket basket){
		super();
		this.basket=basket;
	}
	public void run(){
		basket.popApple();
	}
}



class Basket{
	private LinkedList<Apple> basket=new LinkedList<Apple>();
	//放4轮苹果
	public synchronized void pushApple(){
		for(int i=0;i<20;i++){
			Apple apple=new Apple(i);
			push(apple);
		}
	}
	//取4轮苹果
	public synchronized void popApple(){
		for(int i=0;i<20;i++){
			pop();
		}
	}
	
	
	//向篮子放苹果
	private void push(Apple apple){
		if(basket.size()==5){
			try{
				wait();//等待并释放当前对象的锁
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
		System.out.println("存放："+apple.toString());
		notify();//通知消费者来消费
	}
	//向篮子取苹果
	private void pop(){
		//当篮子中苹果数位0的时候就等待并通知生产者来生产
		if(basket.size()==0){
			try{
				wait();//等待并释放当前对象的锁
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
		System.out.println("吃掉："+apple.toString());
		notify();//通知生产者来生产
	}
}
//苹果类
class Apple{
	private int id;
	public Apple(int id){
		this.id=id;
	}
	
	public String toString(){
		return "苹果"+(id+1);
	}
}