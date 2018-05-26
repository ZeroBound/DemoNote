package GUIDemo;
import java.awt.*;

public class CardLayoutDemo{
	public static void main(String [] args){
		MyFrame6 mf= new MyFrame6("FlowLayout");
		mf.init();
	}
}

class MyFrame6 extends Frame{
	private Panel card_panel=null;
	private Panel ctrol_panel=null;
	private CardLayout cardLayout=null;
	private FlowLayout flowLayout=null;
	private Label lb_1,lb_2,lb_3,lb_4;
	private TextField tf_content;
	private Button bt_first,bt_previous,bt_next,bt_last;
	
	public MyFrame6(String title){
		super(title);
	}
	public void init(){
		card_panel=new Panel();
		ctrol_panel=new Panel();
		cardLayout=new CardLayout();
		flowLayout=new FlowLayout();
		
		card_panel.setLayout(cardLayout);
		ctrol_panel.setLayout(flowLayout);
		lb_1 = new Label("NOE",Label.CENTER);
		lb_2 = new Label("TWO",Label.CENTER);
		lb_3 = new Label("THREE",Label.CENTER);
		lb_4 = new Label("FOUR",Label.CENTER);
		tf_content=new TextField();
		
		card_panel.add(lb_1);
		card_panel.add(lb_2);
		card_panel.add(tf_content);
		card_panel.add(lb_3);
		card_panel.add(lb_4);
		
		bt_first=new Button("��һ��");
		bt_previous=new Button("�ڶ���");
		bt_next=new Button("������");
		bt_last=new Button("������");
		ctrol_panel.add(bt_first);
		ctrol_panel.add(bt_previous);
		ctrol_panel.add(bt_next);
		ctrol_panel.add(bt_last);
		
		this.add(card_panel,BorderLayout.CENTER);
		this.add(ctrol_panel,BorderLayout.SOUTH);
		ctrol_panel.setBackground(Color.GREEN);
		this.setBackground(Color.BLUE);
		this.setSize(400,300);
		this.setVisible(true);
	}
}