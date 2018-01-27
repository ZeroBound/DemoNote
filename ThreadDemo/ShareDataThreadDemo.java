public class ShareDataThreadDemo{
	public static void main(String [] args){
		/* 启用两个线程，产生两个线程对象，每个线程操作的是各自的TicketThread
		TicketThread s1 =new TicketThread("A");
		s1.start();
		TicketThread s2 =new TicketThread("B");
		s2.start(); */
		TicketRunnable runnable = new TicketRunnable();
		//启用两个线程，每个线程操作的是同一个TicketRunnable
		new Thread(runnable,"A").start();
		new Thread(runnable,"B").start();
	}
}

/* class TicketThread extends Thread{
	private int ticket=5;
	
	public TicketThread(String name){
		super(name);
	}
	public void run(){
		while(true){
			System.out.println(this.getName()+":"+(ticket--));
			if(ticket<1){
				break;
			}
		}
	}
} */
class TicketRunnable implements Runnable{
	private int ticket=5;
	
	public void run(){
		while(true){
			System.out.println(Thread.currentThread().getName()+":"+(ticket--));
			if(ticket<1){
				break;
			}
		}
	}
}
