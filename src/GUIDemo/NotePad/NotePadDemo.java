package GUIDemo.NotePad;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NotePadDemo{
	public static void main(String [] args){
		new MyFrame("���±�");
	}
}

class MyFrame extends JFrame{
	private JTextArea tv_area =null;//�ı�������
	private JScrollPane s_pane=null;//�������
	private JMenuBar mb=null;//�˵���
	private JMenu m_file=null;//�ļ�
	private JMenu m_help=null;//����
	private JMenuItem item_new=null;//�½�
	private JMenuItem item_open=null;//��
	private JMenuItem item_exit=null;//�˳�
	private JMenuItem item_about=null;//����
	
	//public MyFrame(){};
	public MyFrame(String title){
		super(title);
		init();
		registerListener();
	}
	//��ʼ��������
	private void init(){
		tv_area=new JTextArea();
		s_pane=new JScrollPane(tv_area);
		s_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//s_pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Container cpane =this.getContentPane();
		cpane.add(s_pane,BorderLayout.CENTER);
		this.setSize(500,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رմ���
		
		//��ʼ���˵�
		mb=new JMenuBar();
		m_file=new JMenu("�ļ�");
		item_new=new JMenuItem("�½�");
		item_open=new JMenuItem("��");
		item_exit=new JMenuItem("�˳�");
		m_file.add(item_new);
		m_file.add(item_open);
		m_file.add(item_exit);
		
		m_help=new JMenu("����");
		item_about=new JMenuItem("����");
		m_help.add(item_about);
		
		mb.add(m_file);
		mb.add(m_help);
		this.setJMenuBar(mb);
		
		setWindowCenter();
		this.setVisible(true);
	}
	//�������������¼�������
	private void registerListener(){
		item_new.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				newFile();
			}
		});
		item_open.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				openFile();
			}
		});
		item_exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				exit();
			}
		});
		item_about.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new AboutDialog(MyFrame.this,"����-���±�",true);
			}
		});
	}
	//�½�
	private void newFile(){
		if(!tv_area.getText().equals("")){
			int res=JOptionPane.showConfirmDialog(null,"�Ƿ񽫸��ı��棿","��ʾ��Ϣ",JOptionPane.YES_NO_CANCEL_OPTION);
			if(res==JOptionPane.YES_OPTION){
				FileDialog fd=new FileDialog(this,"�����ļ�",FileDialog.SAVE);
				fd.setVisible(true);
			}else if(res==JOptionPane.NO_OPTION){
				tv_area.setText("");//���
			}else{
				
			}
		}
	}
	//��
	private void openFile(){
		FileDialog fd=new FileDialog(this,"���ļ�",FileDialog.LOAD);
		fd.setVisible(true);
	}
	//�˳�
	private void exit(){
		newFile();
		System.exit(0);
	}
	//���ô��ھ�����ʾ
	public void setWindowCenter(){
		//����һ�����߰�
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize= kit.getScreenSize();
		int screenWidth=screenSize.width;
		int screenHeight=screenSize.height;
		int windowWidth=this.getWidth();
		int windowHeight=this.getHeight();
		this.setLocation((screenWidth-windowWidth)/2,(screenHeight-windowHeight)/2);
	}
	
}

class AboutDialog extends JDialog{
	private JLabel desc=null;
	private JPanel panel=null;
	private JButton btn=null;
	
	public AboutDialog(Frame frame,String title,boolean modal){
		super(frame,title,modal);
		init();
		registerListener();
	}
	private void init(){
		desc=new JLabel();
		desc.setText("<html>����һ���Զ�����±�</html>");
		desc.setHorizontalAlignment(JLabel.CENTER);
		panel=new JPanel();
		btn=new JButton("OK");
		panel.add(btn);
		this.add(desc);
		this.add(panel,BorderLayout.SOUTH);
		this.setSize(300,200);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize= kit.getScreenSize();
		int screenWidth=screenSize.width;
		int screenHeight=screenSize.height;
		int dialogWidth=this.getWidth();
		int dialogHeight=this.getHeight();
		this.setLocation((screenWidth-dialogWidth)/2,(screenHeight-dialogHeight)/2);
		
	}
	private void registerListener(){
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				AboutDialog.this.dispose();//�˳��Ի���
			}
		});
		
		this.setVisible(true);
	}
}