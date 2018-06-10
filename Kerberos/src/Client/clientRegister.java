package Client;
import Client.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.net.*;
//**********************************该类实现用户注册***************************************
public class clientRegister extends JFrame implements ActionListener{
	
	JPanel panelObj;
	JLabel labelName,labelName1;
    JLabel logoimagePosition1;
	JLabel logoimagePosition2;
	JLabel labelPassword1,labelPassword11;
	JLabel labelPassword2,labelPassword22;
	JLabel labelEmail,labelEmail1;
	
	JTextField textName;

	ButtonGroup bg;
	JPasswordField textPassword1;
	JPasswordField textPassword2;
	
	JButton b1;
	JButton b2;
	JButton b3;
	
	JLabel imagePos;
STATEC statec = new STATEC();
Client client = new Client(statec);
	RegisterMessage uobj;	
	//******************************** 设置用户注册窗口************************************
	public clientRegister()
	{
		this.setTitle(" 用户注册");
     	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     	setSize(530,300);
     	Dimension us = this.getSize();
   		//获取屏幕的尺寸
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2 ;
		int newY = (them.height - us.height) / 2 ;		
		this.setLocation(newX,newY);
		
		panelObj = new JPanel();
	    getContentPane().add(panelObj);
		panelObj.setLayout(null);    //设置框架窗口的布局管理器为null布局管理器
		
		//初始化各个组件
		bg = new ButtonGroup();
		Font f = new Font("宋体",Font.PLAIN,12);
		labelName = new JLabel("用户名：");
		labelName.setFont(f);
		labelName1 = new JLabel("(用户名长度限制为2－15字符)");
		labelName1.setFont(f);

		
	   
		
		labelPassword1 = new JLabel("\u5BC6 \u7801 \uFF1A");
		labelPassword1.setFont(f);
		labelPassword11 = new JLabel("(密码不得少于6个字符多于12个字符)");
		labelPassword11.setFont(f);
		labelPassword2 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		labelPassword2.setFont(f);
		labelPassword22 = new JLabel("(请再输一遍确认)");
		labelPassword22.setFont(f);



		
		textName = new JTextField(10);
		textName.setToolTipText("☆请输入您要注册的用户名☆");
		textPassword1 = new JPasswordField(10);
		textPassword1.setToolTipText("☆请输入您的密码☆");
		textPassword2 = new JPasswordField(10);
		textPassword2.setToolTipText("☆请再次输入密码确认☆");

		
		b1 = new JButton("提   交");
		b1.setBackground(Color.WHITE);
		b1.addActionListener(this);
		b2 = new JButton("重   置");
		b2.setBackground(Color.WHITE);
		b2.addActionListener(this);
		b3 = new JButton("返   回");
		b3.setBackground(Color.WHITE);
		b3.addActionListener(this);
		
		Icon image = new ImageIcon("image/register.png");
		imagePos = new JLabel(image);
		
		labelName.setBounds(new Rectangle(40, 50, 60, 30));
		textName.setBounds(new Rectangle(100, 50, 180, 30));
		labelName1.setBounds(new Rectangle(300, 50, 200, 30));


		labelPassword1.setBounds(new Rectangle(40, 90, 60, 30));
		textPassword1.setBounds(new Rectangle(100, 90, 180, 30));
		labelPassword11.setBounds(new Rectangle(300, 90, 220, 30));
		labelPassword2.setBounds(new Rectangle(28, 130, 60, 30));
		textPassword2.setBounds(new Rectangle(100, 130, 180, 30));
		labelPassword22.setBounds(new Rectangle(300, 130, 220, 30));

		b1.setBounds(new Rectangle(60, 195, 80, 30));
		b2.setBounds(new Rectangle(170,195, 80, 30));
		b3.setBounds(new Rectangle(280, 195, 80, 30));
		
		imagePos.setBounds(new Rectangle(0, 0, 525, 273));
		//添加组件到面板
		panelObj.add(labelName);
		panelObj.add(textName);
		panelObj.add(labelName1);

		panelObj.add(labelPassword1);
		panelObj.add(textPassword1);
		panelObj.add(labelPassword11);
		panelObj.add(labelPassword2);
		panelObj.add(textPassword2);
		panelObj.add(labelPassword22);
		panelObj.add(b1);
		panelObj.add(b2);
		panelObj.add(b3);
		
		panelObj.add(imagePos);
		
		this.setResizable(false);
	    this.setVisible(true);
		
	}
	//***************************************监听各类按钮的动作*********************************************
	public void actionPerformed(ActionEvent evt){          
		
		Object obj = evt.getSource();
		
		if(obj == b1){                       //注册
     	    uobj = new RegisterMessage();       //接受用户的详细资料
			uobj.RegisterName = textName.getText();
			uobj.RegisterPassword = new String(textPassword1.getPassword());
			uobj.AffirmPassword = new String(textPassword2.getPassword());
		

			
			String userPassword2 = new String(textPassword2.getPassword()); 

			//判断各项输入是否符合规则
			if(uobj.RegisterName.length() == 0){
				
				JOptionPane jopNamePass = new JOptionPane();
				jopNamePass.showMessageDialog(null,"请输入用户名!","提示",JOptionPane.CANCEL_OPTION);
				return;
			}
			
			if((uobj.RegisterName.length()<2) || (uobj.RegisterName.length()>15)){
			
				JOptionPane jopNamePass = new JOptionPane();
				jopNamePass.showMessageDialog(null,"用户名长度限制为2－15字符!","提示",JOptionPane.CANCEL_OPTION);
				textName.setText("");
				return;
			}
			
	        if(uobj.RegisterPassword.length() == 0){
			
				JOptionPane jopName = new JOptionPane();
				jopName.showMessageDialog(null,"请输入密码!","提示",JOptionPane.CANCEL_OPTION);
				return;
			}
			
			if((uobj.RegisterPassword.length() < 6) || (uobj.RegisterPassword.length() > 12)){
			
				JOptionPane jopName = new JOptionPane();
				jopName.showMessageDialog(null,"密码不得少于6个字符多于12个字符!请重新输入!","提示",JOptionPane.CANCEL_OPTION);
				textPassword1.setText("");
				return;
			}
			
			if(userPassword2.length() == 0){
			
				JOptionPane jopName = new JOptionPane();
				jopName.showMessageDialog(null,"请再次输入密码!","提示",JOptionPane.CANCEL_OPTION);
				return;
			}
			
			if(!uobj.RegisterPassword.equals(userPassword2)){
			
				JOptionPane jopName = new JOptionPane();
				jopName.showMessageDialog(null,"您两次输入的密码不一致!","提示",JOptionPane.CANCEL_OPTION);
				textPassword2.setText("");
				return;
			}
			

		

			
			//与服务器连接并接发消息						
			try{
		    	client=new Client(statec);
		    	client.Regist(uobj.RegisterName,uobj.RegisterPassword);
		    	//Socket toServer;
		    	//toServer = new Socket("127.0.0.1",1001);     //连接到服务器
		    	
		    	//写客户详细资料到服务器socket
		    	//ObjectOutputStream streamToServer=new ObjectOutputStream(toServer.getOutputStream());
			    //streamToServer.writeObject((RegisterMessage)uobj);
				//读来自服务器socket的登陆状态
             	//ObjectInputStream streamFromServer= new ObjectInputStream(toServer.getInputStream());
             	//ValidateMessage msg =(ValidateMessage)streamFromServer.readObject();
				//String str = msg.validateMessage;
				//if(str.toString().equals("ok")){        //注册成功
					
					//JOptionPane jopPassword = new JOptionPane();
					//jopPassword.showMessageDialog(null,"注册成功！");
					//this.dispose();
					
					//获取登陆信息
					//clientLogin login=new clientLogin();
					//login.txtUserName.setText(uobj.RegisterName);
					//login.txtUserPwd.setText(uobj.RegisterPassword);
	
				//}
				//else if(str.toString().equals("no")){   //注册失败
			
					//JOptionPane jopPassword = new JOptionPane();
					//jopPassword.showMessageDialog(null,"该用户名已有人使用！","提示",JOptionPane.CANCEL_OPTION);
					//textName.setText("");
					//return;
				//}
				//streamToServer.close();
            	//streamFromServer.close();
			}			
			catch(Exception e){
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(null,"不能连接服务器！","提示",JOptionPane.CANCEL_OPTION);
			}	
			new clientLogin();
		}
		if(obj == b2){             //清空		
			
			textName.setText("");
			textPassword1.setText("");
			textPassword2.setText("");
			
		}	
		if(obj==b3){               //返回
			
			new clientLogin();		
			this.setVisible(false);
		}
	}
	
}


