package ThreadDemo;
/**
 * @author zzw
 */
public class HelloThreadDemo{
	public static void main(String [] args){
		HelloThread t1=new HelloThread("a");
		t1.setName("1");
		t1.start();
		HelloThread t2=new HelloThread("b");
		t1.setName("2");
		t2.start();
	}
}

class HelloThread extends Thread{
	
	public HelloThread(String name){
		super(name);
	}
	@Override
	public void run(){
		for(int i=0, len=5 ;i<len;i++){
			System.out.println(this.getName()+":"+i);
		}
	}
}