package  ProjectExample;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlayMouseDemo{
	public static void main(String [] args){
		new Thread(new PlayMouse()).start();
	}
}

class PlayMouse extends JFrame implements Runnable{
	private JLabel back;//����
	private JLabel [] mouses;//mouse����,��ʾ9��mouses
	private ImageIcon imgMouse;//ͼƬ
	private JLabel score;//�÷�
	private int num;
	//private JPanel show_Panel;
	//private Container cpane;
	
	public PlayMouse(){
		init();
	}
	public void init(){
		//���ý���Ϊ���ɱ����
		this.setResizable(false);
		this.setTitle("�����");
		this.setBounds(300,100,350,360);
		score =new JLabel("��ĵ÷��ǣ�0��  ",JLabel.RIGHT);//jtf.setText("��ĵ÷��ǣ�0��");
		this.getContentPane().add(score,BorderLayout.NORTH);
		
		//���ñ���ͼƬ
		back=new JLabel();
		ImageIcon icon =new ImageIcon(this.getClass().getResource("1.jpg"));
		back.setIcon(icon);
		//�������ͼƬ
		this.setCursor(Toolkit.getDefaultToolkit()
			.createCustomCursor(Toolkit.getDefaultToolkit().getImage(""),new Point(),"self"));
		
		//����9ֻ����
		imgMouse =new ImageIcon(this.getClass().getResource("2.jpg"));
		mouses =new JLabel[9];
		for(int i=0;i<9;i++){
			mouses[i]=new JLabel();
			mouses[i].setSize(imgMouse.getIconWidth(),imgMouse.getIconHeight());
			mouses[i].addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					Object object =e.getSource();
					//System.out.println(object);
					if(object instanceof JLabel){
						JLabel lable=(JLabel)object;
						if(lable.getIcon()!=null){
							num++;
							score.setText("��ĵ÷��ǣ�"+num+"��  ");
						}
						lable.setIcon(null);
					}
				}
			});
			this.getContentPane().add(mouses[i]);
		}
		//this.setIconImage(icon.getImage()); 
		mouses[0].setLocation(50,50);
		mouses[1].setLocation(150,50);
		mouses[2].setLocation(250,50);
		mouses[3].setLocation(50,150);
		mouses[4].setLocation(150,150);
		mouses[5].setLocation(250,150);
		mouses[6].setLocation(50,250);
		mouses[7].setLocation(150,250);
		mouses[8].setLocation(250,250);
		this.getContentPane().add(back);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رմ���
	}
	
	public void run(){
		while(true){
			try{
				Thread.sleep(500);
				//0~8
				int index=(int)(Math.random()*9);
				if(mouses[index].getIcon()==null){
					mouses[index].setIcon(imgMouse);
					//��Ӧʱ��
					Thread.sleep(3000);
					if(mouses[index].isShowing()){
						mouses[index].setIcon(null);
					}
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}			
		}
	}
}