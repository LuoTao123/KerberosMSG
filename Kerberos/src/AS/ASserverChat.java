package AS;

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
		this.setTitle("AS�������");  	
     	this.setSize(500,400);
     	Dimension us = this.getSize();
    	//��ȡ��Ļ�ĳߴ�
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2 ;
		int newY = (them.height - us.height) / 2 ;		
		this.setLocation(newX,newY);
		
		
    	 
    	 panel = new JPanel();
 		getContentPane().add( panel);
 		 panel.setBackground(new Color(205, 205, 205));
 		 panel.setLayout(null);     		//���ÿ�ܴ��ڵĲ��ֹ�����Ϊnull���ֹ�����
 		Font f = new Font("����",Font.PLAIN,12);
		   	    
		 button = new JButton("����");
         button.setFont(new Font("����", Font.PLAIN, 15));
   	     button.setBounds(10, 10, 93, 23);
   	     button.addActionListener(new StartFrame());
		 button.setToolTipText("�˳�����Աϵͳ");
		 panel.add(button);
		   	    
		 button_1 = new JButton("\u5173\u95ED");
		 button_1.setFont(new Font("����", Font.PLAIN, 15));
		 button_1.setBounds(116, 10, 93, 23);
		 button_1.addActionListener(new ExitFrame());
		 button_1.setToolTipText("�˳�����Աϵͳ");
		 panel.add(button_1);
		 
		   	    
		 button_2 = new JButton("\u91CD\u542F");
		 button_2.setFont(new Font("����", Font.PLAIN, 15));
		 button_2.setBounds(219, 10, 93, 23);
		 button_2.addActionListener(new RestartFrame());
		 button_2.setToolTipText("��������Աϵͳ");
		 panel.add(button_2);
		   	    
		 label = new JLabel("��־��");
		 label.setFont(new Font("����", Font.PLAIN, 15));
		 label.setBounds(20, 44, 58, 23);
		 panel.add(label);
		   	    
		 textArea= new TextArea(null,110,300,TextArea.SCROLLBARS_VERTICAL_ONLY);
		 textArea.setEditable(false);   //�����ı��򲻿�д
		 textArea.setBackground(Color.WHITE);
	     textArea.setBounds(new Rectangle(10, 71, 464, 280));
		 panel.add(textArea);
		   	    
			setResizable(false);
			setVisible(true);

		   	    

		
	}
	
	 public static void main(String[] args) {    
		 ASserverChat f=new ASserverChat();

	    
	    }
	Server f=new Server(); 
		//������������������������������������Ա���棪������������������������������������������
		class StartFrame implements ActionListener{		
			public void actionPerformed(ActionEvent e){
				String string1 = "open";
	        	String string2 = "AS����������!";
	        	//userExit(string1,string2);
	        
				f.Server(textArea);

			}
		}
	 
		//�����������������������������˳�����Ա���棪������������������������������������������
		class ExitFrame implements ActionListener{		
			public void actionPerformed(ActionEvent e){
				String string1 = "quit";
	        	String string2 = "ȷ���˳�!";
	        	f.Serverfail(textArea);
	        	//userExit(string1,string2);
			}
		}
		//������������������������������������Ա���棪������������������������������������������
		class RestartFrame implements ActionListener{		
			public void actionPerformed(ActionEvent e){
				String string1 = "string";
	        	String string2 = "ȷ������!";
	        	f.Serverfail(textArea);
	        	setVisible(false);
	        	new serverLogin();
	            //	userExit(msg,string);
			}
		}
}
