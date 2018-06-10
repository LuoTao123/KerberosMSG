package ServerIDPSW;

import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
//********************************该类实现服务器管理员登陆*****************************
public class serverLogin extends JFrame implements ActionListener{
	
	//申明变量
		JLabel labelUserPwd;
		JPasswordField txtUserPwd;
		JButton buttonLogin;
		JLabel label;
		
	public serverLogin() {

		this.setTitle("管理员登录");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	    Font myfont=new Font("宋体",Font.PLAIN,12);
	    
		//设置标签

		labelUserPwd=new JLabel("请输入密 码：");
		labelUserPwd.setFont(myfont);
		

		
		//设置按钮组件
		buttonLogin=new JButton("登录");
		buttonLogin.setFont(myfont);
		buttonLogin.setBackground(new Color(0, 153, 255));
		buttonLogin.setToolTipText("进入管理员界面  ");
		buttonLogin.addActionListener(this);
		
	
		txtUserPwd=new JPasswordField();
		txtUserPwd.setToolTipText(" 输入密码 ") ;
		
		//设置框架窗口布局管理为空布局管理
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
   		//获取屏幕的尺寸
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2 ;
		int newY = (them.height - us.height) / 2 ;		
		this.setLocation(newX,newY);
		setVisible(true);
		
	}
	
	//***********************************按钮事件****************************************
	public void actionPerformed(ActionEvent even){
	
		JButton obj=(JButton)even.getSource();
	  	
         if (obj==buttonLogin){          //提交
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
        	 JOptionPane.showMessageDialog(this,"密码不能为空","提示!",JOptionPane.CANCEL_OPTION);
             return;  
        	 }
        	 }
 
	    }
	
	 public static void main(String[] args) {    
		 serverLogin f=new serverLogin();

	    
	    }
	 

	 
}
