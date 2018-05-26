package ThreadDemo.Productor;
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

//����ʽ����ģʽ,�̲߳���ȫ
class SingleTon{
	private static SingleTon singleTon=null;
	private SingleTon(){
		System.out.println("����ʽ����ģʽ");
	}
	//����ͬ��,��֤�̰߳�ȫ,�������ܽϲ
	public static /*synchronized*/ SingleTon getInstance(){
		if(singleTon==null){
			//����һ�߳̽���,�ڶ����߳�Ҳ����.��������ʵ��������.
			//(�����췽��ִ������)
			singleTon=new SingleTon();
		}
		return singleTon;
	}
	//ʹ��˫�������
	/* public static SingleTon getInstance(){
		//����һ�߳̽���,newһ��ʵ������
		//���ڶ��߳̽���ʱ,��ֱ�ӷ���
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

//����ʽ����ģʽ
class SingleTon1{
	private static SingleTon1 singleTon =new SingleTon1();
	private SingleTon1(){
		System.out.println("����ʽ����ģʽ");
	}
	public static SingleTon1 getInstance(){
		return singleTon;
	}
}