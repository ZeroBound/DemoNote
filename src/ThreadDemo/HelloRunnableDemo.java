package ThreadDemo;
/**
 * @author zzw
 */
public class HelloRunnableDemo{
	public static void main(String [] args){
		HelloRunnable t1=new HelloRunnable();
		new Thread(t1,"a").start();
		HelloRunnable t2=new HelloRunnable();
		new Thread(t2,"b").start();
	}
}


class HelloRunnable implements Runnable{
	
	public HelloRunnable(){
	}
	@Override
	public void run(){
		for(int i=0;i<5;i++){
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
}