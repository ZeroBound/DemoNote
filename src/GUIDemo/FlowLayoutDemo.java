package GUIDemo;
import java.awt.*;

public class FlowLayoutDemo{
	public static void main(String [] args){
		MyFrame3 mf= new MyFrame3("FlowLayout");
		mf.init();
	}
}

class MyFrame3 extends Frame{
	public MyFrame3(String title){
		super(title);
	}
	public void init(){
		this.setLayout(new FlowLayout(FlowLayout.LEFT,50,50));
		this.setBackground(Color.BLUE);
		this.add(new Button("BT1"));
		this.add(new Button("BT2"));
		this.add(new Button("BT3"));
		this.add(new Button("BT4"));
		this.setSize(300,300);
		this.setVisible(true);
	}
}