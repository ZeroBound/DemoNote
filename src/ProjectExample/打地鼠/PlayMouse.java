package ProjectExample.打地鼠;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlayMouse extends JFrame implements Runnable{
	public static void main(String [] args){
		PlayMouse p=new PlayMouse();
		Thread t = new Thread(p);
		t.start();
	}

	private JLabel back;
	private JLabel [] mouses;
	private ImageIcon imgMouse;
	private JLabel jtf;
	private int number;

	public PlayMouse(){
		//不能修改界面大小
		this.setResizable(false);
		this.getContentPane().setLayout(null);
		this.setTitle("打地鼠");
		this.setBounds(300,100,600,428);
		back=new JLabel();
		ImageIcon icon =new ImageIcon(this.getClass().getResource("1.jpg"));
		back.setIcon(icon);
		back.setBounds(0,-35,600,438);
		imgMouse =new ImageIcon(this.getClass().getResource("2.jpg"));
		mouses =new JLabel[9];
		for(int i=0;i<9;i++){
			mouses[i]=new JLabel();
			mouses[i].setSize(imgMouse.getIconWidth(),imgMouse.getIconHeight());
			//mouses[i].setIcon(imgMouse);
			mouses[i].addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					Object object =e.getSource();
					System.out.println(object);
					if(object instanceof JLabel){
						JLabel lable=(JLabel)object;
						if(lable.getIcon()!=null){
							number++;
							jtf.setText("你的得分是:"+number+"分");
						}
						lable.setIcon(null);
					}
				}
			});
			this.getContentPane().add(mouses[i]);
		}
		jtf =new JLabel();
		jtf.setBounds(360,10,230,50);
		//jtf.setFont(new Font());
		//jtf.setForeground(Color.black);
		jtf.setText("你的得分是：0分");
		mouses[0].setLocation(88,53);
		mouses[1].setLocation(240,53);
		mouses[2].setLocation(390,53);
		mouses[3].setLocation(55,160);
		mouses[4].setLocation(245,160);
		mouses[5].setLocation(430,160);
		mouses[6].setLocation(36,296);
		mouses[7].setLocation(230,296);
		mouses[8].setLocation(445,296);
		this.getContentPane().add(jtf);
		this.getContentPane().add(back);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



	@Override
	public void run(){
		while(true){
			try{
				Thread.sleep(500);
				//0~8
				int index=(int)(Math.random()*9);
				if(mouses[index].getIcon()==null){
					mouses[index].setIcon(imgMouse);
					//反应时间
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