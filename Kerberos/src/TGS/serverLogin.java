package TGS;

import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
//********************************����ʵ�ַ���������Ա��½*****************************
public class serverLogin extends JFrame implements ActionListener{
	
	//��������
		JLabel labelUserPwd;
		JPasswordField txtUserPwd;
		JButton buttonLogin;
		JLabel label;
		
	public serverLogin() {

		this.setTitle("����Ա��¼");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	    Font myfont=new Font("����",Font.PLAIN,12);
	    
		//���ñ�ǩ

		labelUserPwd=new JLabel("�������� �룺");
		labelUserPwd.setFont(myfont);
		

		
		//���ð�ť���
		buttonLogin=new JButton("��¼");
		buttonLogin.setFont(myfont);
		buttonLogin.setBackground(new Color(0, 153, 255));
		buttonLogin.setToolTipText("�������Ա����  ");
		buttonLogin.addActionListener(this);
		
	
		txtUserPwd=new JPasswordField();
		txtUserPwd.setToolTipText(" �������� ") ;
		
		//���ÿ�ܴ��ڲ��ֹ���Ϊ�ղ��ֹ���
		JPanel panelObj=new JPanel();
		getContentPane().add(panelObj);
		panelObj.setLayout(null);
		labelUserPwd.setBounds(new Rectangle(20, 40, 87, 20));
		txtUserPwd.setBounds(new Rectangle(101, 40, 180, 20));
		buttonLogin.setBounds(new Rectangle(304, 40, 60, 20));
		
		panelObj.add(labelUserPwd);
		panelObj.add(txtUserPwd);
		panelObj.add(buttonLogin);

		setResizable(false);
		setSize(380,120);
		Dimension us = this.getSize();
   		//��ȡ��Ļ�ĳߴ�
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2 ;
		int newY = (them.height - us.height) / 2 ;		
		this.setLocation(newX,newY);
		setVisible(true);
		
	}
	
	//***********************************��ť�¼�****************************************
	public void actionPerformed(ActionEvent even){
	
		JButton obj=(JButton)even.getSource();
	  	
         if (obj==buttonLogin){          //�ύ
        	 String p=String.valueOf(txtUserPwd.getPassword());
        	 String sp=new String("123456");
        	
        	 if(p.length()!=0)
        	 {

        	        if(p.equals(sp))
        	         {
        		  		setVisible(false);
        	        	new ASserverChat();
        	         }
        	 }
        	 else
        	 {
        	 JOptionPane.showMessageDialog(this,"���벻��Ϊ��","��ʾ!",JOptionPane.CANCEL_OPTION);
             return;  
        	 }
        	 }
 
	    }
	
	 public static void main(String[] args) {    
		 serverLogin f=new serverLogin();

	    
	    }
	 

	 
}
