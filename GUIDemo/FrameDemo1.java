import java.util.*;
import java.awt.*;

public class FrameDemo1{
	public static void main(String [] args){
		MyFrame1 mf = new MyFrame1("TestGUI");
		mf.init();
	}
}

class MyFrame1 extends Frame{
	public MyFrame1(String title){
		super(title);
	}
	public void init(){
		//this.setSize(300,300);
		this.setBounds(100,100,300,300);
		this.setBackground(Color.RED);
		this.setVisible(true);
	}
}