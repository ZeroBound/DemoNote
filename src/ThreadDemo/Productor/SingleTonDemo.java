package ThreadDemo.Productor;
/**
 * @author zzw
 */
public class SingleTonDemo{
	public static void main(String [] args){
		/* SingleTon.getInstance();
		SingleTon1.getInstance(); */
		SThread sThread = new SThread();
		new Thread(sThread).start();
		new Thread(sThread).start();
	}
}

class SThread implements Runnable{
	@Override
	public void run(){
		SingleTon.getInstance();
	}
}

/**
 * 懒汉式单例模式
 */
class SingleTon{
	private static SingleTon singleTon=null;
	private SingleTon(){
		System.out.println("懒汉式单例模式");
	}
	//多线程中不安全 需要加上 synchronized
	public static /*synchronized*/ SingleTon getInstance(){
		if(singleTon==null){
			singleTon=new SingleTon();
		}
		return singleTon;
	}

	/* public static SingleTon getInstance(){
		if(singleTon==null){
			synchronized (SingleTon.class){
				if(singleTon==null){
					singleTon=new SingleTon();
				}
			}
		}
		return singleTon;
	} */
}

/**
 * 饿汉式单例模式
 */
class SingleTon1{
	private static SingleTon1 singleTon =new SingleTon1();
	private SingleTon1(){
		System.out.println("饿汉式单例模式");
	}
	public static SingleTon1 getInstance(){
		return singleTon;
	}
}