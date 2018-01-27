import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NotePadDemo{
	public static void main(String [] args){
		new MyFrame("记事本");
	}
}

class MyFrame extends JFrame{
	private JTextArea tv_area =null;//文本输入域
	private JScrollPane s_pane=null;//滚动面板
	private JMenuBar mb=null;//菜单栏
	private JMenu m_file=null;//文件
	private JMenu m_help=null;//帮助
	private JMenuItem item_new=null;//新建
	private JMenuItem item_open=null;//打开
	private JMenuItem item_exit=null;//退出
	private JMenuItem item_about=null;//关于
	
	//public MyFrame(){};
	public MyFrame(String title){
		super(title);
		init();
		registerListener();
	}
	//初始化相关组件
	private void init(){
		tv_area=new JTextArea();
		s_pane=new JScrollPane(tv_area);
		s_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//s_pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Container cpane =this.getContentPane();
		cpane.add(s_pane,BorderLayout.CENTER);
		this.setSize(500,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口
		
		//初始化菜单
		mb=new JMenuBar();
		m_file=new JMenu("文件");
		item_new=new JMenuItem("新建");
		item_open=new JMenuItem("打开");
		item_exit=new JMenuItem("退出");
		m_file.add(item_new);
		m_file.add(item_open);
		m_file.add(item_exit);
		
		m_help=new JMenu("帮助");
		item_about=new JMenuItem("关于");
		m_help.add(item_about);
		
		mb.add(m_file);
		mb.add(m_help);
		this.setJMenuBar(mb);
		
		setWindowCenter();
		this.setVisible(true);
	}
	//给相关组件设置事件监听器
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
				new AboutDialog(MyFrame.this,"关于-记事本",true);
			}
		});
	}
	//新建
	private void newFile(){
		if(!tv_area.getText().equals("")){
			int res=JOptionPane.showConfirmDialog(null,"是否将更改保存？","提示信息",JOptionPane.YES_NO_CANCEL_OPTION);
			if(res==JOptionPane.YES_OPTION){
				FileDialog fd=new FileDialog(this,"保存文件",FileDialog.SAVE);
				fd.setVisible(true);
			}else if(res==JOptionPane.NO_OPTION){
				tv_area.setText("");//清空
			}else{
				
			}
		}
	}
	//打开
	private void openFile(){
		FileDialog fd=new FileDialog(this,"打开文件",FileDialog.LOAD);
		fd.setVisible(true);
	}
	//退出
	private void exit(){
		newFile();
		System.exit(0);
	}
	//设置窗口居中显示
	public void setWindowCenter(){
		//定义一个工具包
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
		desc.setText("<html>这是一个自定义记事本</html>");
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
				AboutDialog.this.dispose();//退出对话框
			}
		});
		
		this.setVisible(true);
	}
}