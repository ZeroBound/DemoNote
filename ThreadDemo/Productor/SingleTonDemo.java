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
	public void run(){
		SingleTon.getInstance();
	}
}

//懒汉式单例模式,线程不安全
class SingleTon{
	private static SingleTon singleTon=null;
	private SingleTon(){
		System.out.println("懒汉式单例模式");
	}
	//加上同步,保证线程安全,但是性能较差。
	public static /*synchronized*/ SingleTon getInstance(){
		if(singleTon==null){
			//当第一线程进来,第二个线程也进了.产生了两实例个对象.
			//(及构造方法执行两次)
			singleTon=new SingleTon();
		}
		return singleTon;
	}
	//使用双重锁检查
	/* public static SingleTon getInstance(){
		//当第一线程进来,new一个实例对象
		//当第二线程进来时,就直接返回
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

//饿汉式单例模式
class SingleTon1{
	private static SingleTon1 singleTon =new SingleTon1();
	private SingleTon1(){
		System.out.println("饿汉式单例模式");
	}
	public static SingleTon1 getInstance(){
		return singleTon;
	}
}