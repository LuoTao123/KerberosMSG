package Server;

import DataBase.sql;
import Package.Authenticator;
import Package.Pack;
import Package.Ticket;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class UserChat extends JFrame{
	private JTextArea textField;
	private JTextArea textField_3;
	private JTextArea textField_4;
	private JTextArea textField_5;
	private JTextArea textField_6;
	private JTextArea textField_7;
	private JTextArea textField_8;
	private JTextArea textField_9;
	JPanel panel;
	JButton button;
	JButton button_1;
	JButton button_2;
	public java.awt.List userlist;
	JLabel label;
	JLabel label_1;
	JLabel label_2;
	JLabel lblTicketv;
	JLabel lblNewLabel;
	TextArea textArea;
	JLabel lblIdc;
	JButton button_3;
	JLabel lblAdc;
	JLabel lblIdv;
	JLabel lblTs;
	JLabel lblLifetime;
	JLabel lblIdc_1;
	JLabel lblAdc_1;
	JLabel label_6;
	public UserChat() {
		 
		  	    
		   		this.setTitle("应用管理界面");  	
		     	this.setSize(630,520);
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
		   	    panel.add(button);
		   	    
		   	    button_1 = new JButton("关闭");
		   	    button_1.setFont(new Font("宋体", Font.PLAIN, 15));
		   	    button_1.setBounds(116, 10, 93, 23);
		   	 button_1.addActionListener(new ExitFrame());
		   	    panel.add(button_1);
		   	    
		   	    button_2 = new JButton("重启");
		   	    button_2.setFont(new Font("宋体", Font.PLAIN, 15));
		   	    button_2.setBounds(219, 10, 93, 23);
		   	 button_2.addActionListener(new RestartFrame());
		   	    panel.add(button_2);
		   	    
		   	 userlist = new java.awt.List();
		   	 userlist.setBounds(10, 66, 124, 375);
		   	userlist.addActionListener(new NameSelected());
		   	    panel.add(userlist);
		   	    
		   	    label = new JLabel("\u5728\u7EBF\u5217\u8868\uFF1A");
		   	    label.setFont(new Font("宋体", Font.PLAIN, 15));
		   	    label.setBounds(10, 43, 79, 23);
		   	    panel.add(label);
		   	    
		   	    label_1 = new JLabel("\u65E5\u5FD7\uFF1A");
		   	    label_1.setFont(new Font("宋体", Font.PLAIN, 15));
		   	    label_1.setBounds(144, 43, 54, 19);
		   	    panel.add(label_1);
		   	    
		   	    label_2 = new JLabel("\u8BA4\u8BC1\u7ED3\u679C\uFF1A");
		   	    label_2.setFont(new Font("宋体", Font.PLAIN, 15));
		   	    label_2.setBounds(400, 47, 79, 15);
		   	    panel.add(label_2);
		   	    
		   	    lblTicketv = new JLabel("TicketV:");
		   	    lblTicketv.setFont(new Font("宋体", Font.PLAIN, 14));
		   	    lblTicketv.setBounds(400, 66, 98, 29);
		   	    panel.add(lblTicketv);
		   	    
		   	    lblNewLabel = new JLabel("Authenticator:");
		   	    lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		   	    lblNewLabel.setBounds(400, 272, 117, 27);
		   	    panel.add(lblNewLabel);
				
		   	    textArea= new TextArea(null,110,300,TextArea.SCROLLBARS_VERTICAL_ONLY);
		   	    textArea.setEditable(false);   //聊天文本域不可写
		   	    textArea.setBackground(Color.WHITE);
				textArea.setBounds(new Rectangle(144, 66, 246, 375));
		   	    panel.add(textArea);
		   	    
		   	    lblIdc = new JLabel("IDc:");
		   	    lblIdc.setBounds(463, 115, 54, 21);
		   	    panel.add(lblIdc);
		   	    
		   	    textField = new JTextArea();
		   	    textField.setColumns(10);
		   	    textField.setBounds(503, 115, 101, 21);
		   	    textField.setEditable(false);
		   	    textField.setBackground(Color.WHITE);
		   	    panel.add(textField);
		   	    
		   	    lblAdc = new JLabel("ADc:");
		   	    lblAdc.setBounds(463, 146, 54, 21);
		   	    panel.add(lblAdc);
		   	    
		   	    textField_3 = new JTextArea();
		   	    textField_3.setBackground(Color.WHITE);
		   	    textField_3.setColumns(10);
		   	    textField_3.setBounds(503, 146, 101, 21);
		   	    textField_3.setEditable(false);
		   	    panel.add(textField_3);
		   	    
		   	    lblIdv = new JLabel("IDv:");
		   	    lblIdv.setBounds(463, 177, 54, 15);
		   	    panel.add(lblIdv);
		   	    
		   	    textField_4 = new JTextArea();
		   	    textField_4.setBackground(Color.WHITE);
		   	    textField_4.setEditable(false);
		   	    textField_4.setColumns(10);
		   	    textField_4.setBounds(503, 174, 101, 21);
		   	    panel.add(textField_4);
		   	    
		   	    lblTs = new JLabel("TS4:");
		   	    lblTs.setBounds(463, 205, 60, 18);
		   	    panel.add(lblTs);
		   	    
		   	    textField_5 = new JTextArea();
		   	    textField_5.setBackground(new Color(255, 255, 255));
		   	    textField_5.setEditable(false);
		   	    textField_5.setColumns(10);
		   	    textField_5.setBounds(503, 202, 101, 21);
		   	    panel.add(textField_5);
		   	    
		   	    lblLifetime = new JLabel("LifeTime:");
		   	    lblLifetime.setBounds(463, 233, 60, 19);
		   	    panel.add(lblLifetime);
		   	    
		   	    textField_6 = new JTextArea();
		   	    textField_6.setBackground(new Color(255, 255, 255));
		   	    textField_6.setEditable(false);
		   	    textField_6.setColumns(10);
		   	    textField_6.setBounds(525, 230, 79, 21);
		   	    panel.add(textField_6);
		   	    
		   	    lblIdc_1 = new JLabel("IDc:");
		   	    lblIdc_1.setBounds(463, 328, 54, 15);
		   	    panel.add(lblIdc_1);
		   	    
		   	    lblAdc_1 = new JLabel("ADc:");
		   	    lblAdc_1.setBounds(463, 353, 60, 21);
		   	    panel.add(lblAdc_1);
		   	    
		   	    label_6 = new JLabel("TS5:");
		   	    label_6.setBounds(463, 384, 54, 23);
		   	    panel.add(label_6);
		   	    
		   	    textField_7 = new JTextArea();
		   	    textField_7.setBackground(new Color(255, 255, 255));
		   	    textField_7.setEditable(false);
		   	    textField_7.setColumns(10);
		   	    textField_7.setBounds(503, 323, 101, 21);
		   	    panel.add(textField_7);
		   	    
		   	    textField_8 = new JTextArea();
		   	    textField_8.setBackground(new Color(255, 255, 255));
		   	    textField_8.setEditable(false);
		   	    textField_8.setColumns(10);
		   	    textField_8.setBounds(503, 353, 101, 21);
		   	    panel.add(textField_8);
		   	    
		   	    textField_9 = new JTextArea();
		   	    textField_9.setBackground(new Color(255, 255, 255));
		   	    textField_9.setEditable(false);
		   	    textField_9.setColumns(10);
		   	    textField_9.setBounds(503, 384, 101, 21);
		   	    panel.add(textField_9);
		   	    
		   	    button_3 = new JButton("\u6E05\u9664\u8BB0\u5F55");
		   	    button_3.setBounds(280, 448, 93, 23);
		   	    button_3.addActionListener(new ListClear());
		   	    panel.add(button_3);
		   	    
				setResizable(false);
				setVisible(true);
		

	
	}
	 public static void main(String[] args) {    
		 UserChat f=new UserChat();

	    
	    }
		//＊＊＊＊＊＊＊＊＊＊＊＊＊从列表中选中在线对象＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊	
		class NameSelected implements ActionListener{	
			public void actionPerformed(ActionEvent e){
				boolean flag = false;
			    String uName = userlist.getSelectedItem();
			    int ID=Integer.parseInt(uName);
			    Authenticator au = null;
			    Ticket ticket=null;
			    sql a = new sql();
			    try {
					ticket=a.SelectTicket(ID);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			    Pack pack = new Pack();
			    
			    textField.setText(Integer.toString(ticket.getID1()));
			    
			    textField_3.setText(pack.ipIntsToString(ticket.getAD()));  
			    textField_4.setText(Integer.toString(ticket.getID2())); 
			    textField_5.setText(ticket.getTS());
			    textField_6.setText(Integer.toString(ticket.getLT())); 
			   
			       try {
			    	   au=a.SelectAuthenticator(ID);
			    	 
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			       textField_7.setText(Integer.toString(au.getID()));
			       textField_8.setText(pack.ipIntsToString(au.getAD())); 
			       textField_9.setText(au.getTS());
			       
			}
			}
		Server f=new Server(); 
		//＊＊＊＊＊＊＊＊＊＊＊＊＊＊开启管理员界面＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
		class StartFrame implements ActionListener{		
			public void actionPerformed(ActionEvent e){
				String string1 = "open";
	        	String string2 = "AS服务器开启!";
	        	//userExit(string1,string2);
				f.Server1(textArea,userlist);

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
		
		//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊清空聊天内容＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
		class ListClear implements ActionListener{
		 	public void actionPerformed(ActionEvent e){
				Object obj = e.getSource(); 
			    if(obj == (JButton)button_3){
			    	textArea.setText("");
				}
			}
		}
}
