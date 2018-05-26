package ThreadDemo;
public class HelloRunnableDemo{
	public static void main(String [] args){
		HelloRunnable t1=new HelloRunnable();
		//t1.setName("�߳�1");
		new Thread(t1,"a").start();
		HelloRunnable t2=new HelloRunnable();
		//t1.setName("�߳�2");
		new Thread(t2,"b").start();
	}
}
//1.���ⵥ�̳еľ����ԣ�һ�������ʵ�ֶ���ӿڣ�����ֻ�ܼ̳�һ����
//2.ʵ�����ݹ���
class HelloRunnable implements Runnable{
	
	public HelloRunnable(){
	}
	public void run(){
		for(int i=0;i<5;i++){
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
}