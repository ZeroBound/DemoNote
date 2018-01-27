public class HelloRunnableDemo{
	public static void main(String [] args){
		HelloRunnable t1=new HelloRunnable();
		//t1.setName("线程1");
		new Thread(t1,"a").start();
		HelloRunnable t2=new HelloRunnable();
		//t1.setName("线程2");
		new Thread(t2,"b").start();
	}
}
//1.避免单继承的局限性，一个类可以实现多个接口，但是只能继承一个类
//2.实现数据共享
class HelloRunnable implements Runnable{
	
	public HelloRunnable(){
	}
	public void run(){
		for(int i=0;i<5;i++){
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
}