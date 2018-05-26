package ThreadDemo.Productor;
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

//������
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
//������
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
	//��4��ƻ��
	public synchronized void pushApple(){
		for(int i=0;i<20;i++){
			Apple apple=new Apple(i);
			push(apple);
		}
	}
	//ȡ4��ƻ��
	public synchronized void popApple(){
		for(int i=0;i<20;i++){
			pop();
		}
	}
	
	
	//�����ӷ�ƻ��
	private void push(Apple apple){
		if(basket.size()==5){
			try{
				wait();//�ȴ����ͷŵ�ǰ�������
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
		System.out.println("��ţ�"+apple.toString());
		notify();//֪ͨ������������
	}
	//������ȡƻ��
	private void pop(){
		//��������ƻ����λ0��ʱ��͵ȴ���֪ͨ������������
		if(basket.size()==0){
			try{
				wait();//�ȴ����ͷŵ�ǰ�������
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
		System.out.println("�Ե���"+apple.toString());
		notify();//֪ͨ������������
	}
}
//ƻ����
class Apple{
	private int id;
	public Apple(int id){
		this.id=id;
	}
	
	public String toString(){
		return "ƻ��"+(id+1);
	}
}