package ThreadDemo;
public class DieThreadDemo{
	public static void main(String [] args){
		Example example = new Example();
		DieThread1 d1=new DieThread1(example);
		d1.start();
		
		DieThread2 d2=new DieThread2(example);
		d2.start();
	}
}
//避免同步块中嵌套同步块，容易出现死锁
class DieThread1 extends Thread{
	private Example example;
	public DieThread1(Example example){
		super();
		this.example=example;
	}
	public void run(){
		example.method1();
	}
}
class DieThread2 extends Thread{
	private Example example;
	public DieThread2(Example example){
		super();
		this.example=example;
	}
	public void run(){
		example.method2();
	}
}


class Example{
	private Object obj1=new Object();
	private Object obj2=new Object();
	
	public void method1(){
		synchronized (obj1){
			try{
				Thread.sleep(1000);
			}catch(Exception e){
				e.printStackTrace();
			}
			synchronized (obj2){
				System.out.println("method1");
			}
		}
	}
	public void method2(){
		synchronized (obj2){
			try{
				Thread.sleep(1000);
			}catch(Exception e){
				e.printStackTrace();
			}
			synchronized (obj1){
				System.out.println("method2");
			}
		}
	}

}
