package ThreadDemo;
public class BankDemo{
	public static void main(String [] args){
		Bank bank=new Bank();
		BankThread p1=new BankThread(bank);
		p1.start();
		BankThread p2=new BankThread(bank);
		p2.start();
		
	}
}
class BankThread extends Thread{
	private Bank bank=null;
	public BankThread(Bank bank){
		this.bank=bank;
	}
	public void run(){
		System.out.println("ȡǮ"+bank.getMoney(400));
	}
}


class Bank{
	private int  money=500;
	//��һ���߳�ȥ����ͬ��������ʱ������߳̾ͻ�ȡ�˵�ǰ���������
	public synchronized int getMoney(int number){
		//synchronized (this){}
		if(number<0){
			return -1;
		}else if(money<0){
			return -2;
		}else if(number-money>0){
			System.out.println("��������");
			//return -3;
		}else{
			try{
				Thread.sleep(1000);
			}catch(Exception e){
				e.printStackTrace();
			}
			money-=number;
			System.out.println("���"+money);
		}
		return number;
	}
}