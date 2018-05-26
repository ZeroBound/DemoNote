package GUIDemo;
import java.awt.*;
import java.awt.event.*;

public class ListenerDemo{
	public static void main(String [] args){
		MyFrame7 mf= new MyFrame7("Listener");
		//mf.init();
	}
}

class MyFrame7 extends Frame{
	private Panel card_panel=null;
	private Panel ctrol_panel=null;
	private CardLayout cardLayout=null;
	private FlowLayout flowLayout=null;
	private Label lb_1,lb_2,lb_3,lb_4;
	private TextField tf_content;
	private Button bt_first,bt_previous,bt_next,bt_last;
	
	public MyFrame7(String title){
		super(title);
		init();
		registerListener();
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
		bt_previous=new Button("��һ��");
		bt_next=new Button("��һ��");
		bt_last=new Button("���һ��");
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
		
		
		//ʹ����������������д�������������
		/* lb_1.addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseMoved(MouseEvent e){
				
			}
			@Override
			public void mouseDragged(MouseEvent e){
			
			}
		}); */
		//ʹ��������������
		lb_1.addMouseMotionListener(new MouseMotionAdapter(){
			@Override
			public void mouseMoved(MouseEvent e){
				lb_1.setText(e.getX()+":"+e.getY());
			}
		});
		lb_2.addMouseListener(new MouseAdapter(){
			public void mouseExited(MouseEvent e){
				System.out.println("Exited");
			}
			public void mouseEntered(MouseEvent e){
				System.out.println("entered");
			}
		});
		tf_content.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				System.out.println(e.getKeyChar());
			}
		});
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
	
	private void registerListener(){
		//����һ���¼�����������
		BtListener btListener =new BtListener();
		//��4����ťע�����ͬһ���¼�������
		bt_first.addActionListener(btListener);
		bt_previous.addActionListener(btListener);
		bt_next.addActionListener(btListener);
		bt_last.addActionListener(btListener);
	}
	
	class BtListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			//System.out.println(e.getActionCommand());
			//System.out.println(e.getSource());
			Object obj = e.getSource();
			if(obj==bt_first){
				cardLayout.first(card_panel);
			}else if(obj==bt_previous){
				cardLayout.previous(card_panel);
			}else if(obj==bt_next){
				cardLayout.next(card_panel);
			}else if(obj==bt_last){
				cardLayout.last(card_panel);
			}
		}
	}
}


