import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlayMouseDemo{
	public static void main(String [] args){
		new Thread(new PlayMouse()).start();
	}
}

class PlayMouse extends JFrame implements Runnable{
	private JLabel back;//背景
	private JLabel [] mouses;//mouse数组,表示9个mouses
	private ImageIcon imgMouse;//图片
	private JLabel score;//得分
	private int num;
	//private JPanel show_Panel;
	//private Container cpane;
	
	public PlayMouse(){
		init();
	}
	public void init(){
		//设置界面为不可变界面
		this.setResizable(false);
		this.setTitle("打地鼠");
		this.setBounds(300,100,350,360);
		score =new JLabel("你的得分是：0分  ",JLabel.RIGHT);//jtf.setText("你的得分是：0分");
		this.getContentPane().add(score,BorderLayout.NORTH);
		
		//设置背景图片
		back=new JLabel();
		ImageIcon icon =new ImageIcon(this.getClass().getResource("1.jpg"));
		back.setIcon(icon);
		//设置鼠标图片
		this.setCursor(Toolkit.getDefaultToolkit()
			.createCustomCursor(Toolkit.getDefaultToolkit().getImage(""),new Point(),"self"));
		
		//设置9只老鼠
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
							score.setText("你的得分是："+num+"分  ");
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口
	}
	
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