package ServerIDPSW;

import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ASserverChat extends JFrame{
	JFrame frame;
	JPanel panel;
	JButton button;
	JButton button_1;
	JButton button_2;
	JLabel label;
	TextArea textArea;
	String userName;
	public ASserverChat() {
		
		//userName = name;
		this.setTitle("账号密码服务器管理界面");  	
     	this.setSize(500,400);
     	Dimension us = this.getSize();
    	//获取屏幕的尺寸
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2 ;
		int newY = (them.height - us.height) / 2 ;		
		this.setLocation(newX,newY);
		
		
    	 
    	 panel = new JPanel();
 		getContentPane().add( panel);
 		 panel.setBackground(new Color(205, 205, 205));
 		 panel.setLayout(null);     		//设置框架窗口的布局管理器为null布局管理器
 		Font f = new Font("宋体",Font.PLAIN,12);
		   	    
		 button = new JButton("开启");
         button.setFont(new Font("宋体", Font.PLAIN, 15));
   	     button.setBounds(10, 10, 93, 23);
   	     button.addActionListener(new StartFrame());
		 button.setToolTipText("退出管理员系统");
		 panel.add(button);
		   	    
		 button_1 = new JButton("\u5173\u95ED");
		 button_1.setFont(new Font("宋体", Font.PLAIN, 15));
		 button_1.setBounds(116, 10, 93, 23);
		 button_1.addActionListener(new ExitFrame());
		 button_1.setToolTipText("退出管理员系统");
		 panel.add(button_1);
		 
		   	    
		 button_2 = new JButton("\u91CD\u542F");
		 button_2.setFont(new Font("宋体", Font.PLAIN, 15));
		 button_2.setBounds(219, 10, 93, 23);
		 button_2.addActionListener(new RestartFrame());
		 button_2.setToolTipText("重启管理员系统");
		 panel.add(button_2);
		   	    
		 label = new JLabel("日志：");
		 label.setFont(new Font("宋体", Font.PLAIN, 15));
		 label.setBounds(20, 44, 58, 23);
		 panel.add(label);
		   	    
		 textArea= new TextArea(null,110,300,TextArea.SCROLLBARS_VERTICAL_ONLY);
		 textArea.setEditable(false);   //聊天文本域不可写
		 textArea.setBackground(Color.WHITE);
	     textArea.setBounds(new Rectangle(10, 71, 464, 280));
		 panel.add(textArea);
		   	    
			setResizable(false);
			setVisible(true);

		   	    

		
	}
	
	 public static void main(String[] args) {    
		 ASserverChat f=new ASserverChat();

	    
	    }
	 ServerIDPSW f=new ServerIDPSW(); 
		//＊＊＊＊＊＊＊＊＊＊＊＊＊＊开启管理员界面＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
		class StartFrame implements ActionListener{		
			public void actionPerformed(ActionEvent e){
				String string1 = "open";
	        	String string2 = "AS服务器开启!";
	        	//userExit(string1,string2);
	        
				f.ServerIDPSW(textArea);

			}
		}
	 
		//＊＊＊＊＊＊＊＊＊＊＊＊＊＊退出管理员界面＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
		class ExitFrame implements ActionListener{		
			public void actionPerformed(ActionEvent e){
				String string1 = "quit";
	        	String string2 = "确定退出!";
	        	f.Serverfail(textArea);
	        	//userExit(string1,string2);
			}
		}
		//＊＊＊＊＊＊＊＊＊＊＊＊＊＊重启管理员界面＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
		class RestartFrame implements ActionListener{		
			public void actionPerformed(ActionEvent e){
				String string1 = "string";
	        	String string2 = "确定重启!";
	        	f.Serverfail(textArea);
	        	setVisible(false);
	        	new serverLogin();
	            //	userExit(msg,string);
			}
		}
}
