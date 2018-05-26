package GUIDemo;
import java.awt.*;

public class FrameDemo2{
	public static void main(String [] args){
		MyFrame2 mf = new MyFrame2("GUIDEMO");
		mf.init();
	}
}

class MyFrame2 extends Frame{
	public MyFrame2(String title){
		super(title);
	}
	public void init(){
		this.setSize(300,300);
		this.setBackground(Color.GREEN);
		this.setLayout(null);
		Panel panel = new Panel();
		panel.setLayout(null);
		panel.setBackground(Color.BLUE);
		this.add(panel);
		panel.setBounds(0,0,200,200);
		Button bt1 = new Button("click me");
		bt1.setBounds(0,0,100,100);
		bt1.setBackground(Color.YELLOW);
		panel.add(bt1);
		this.setVisible(true);
	}
}