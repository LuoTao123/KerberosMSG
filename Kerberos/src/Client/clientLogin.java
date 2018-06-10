package Client;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import Client.*;
//************************************该类实现用户登陆***********************************
public class clientLogin extends JFrame implements ActionListener{

	//申明变量
	JLabel labelUserName;
	JLabel labelUserPwd;
	JPasswordField txtUserPwd;
	JTextField txtUserName;
	JButton buttonLogin;
	JButton buttonCancel;
	JButton buttonRegister;
	JLabel label;
	JLabel imagePos;
	Login login1;
	Client client1;
//************************************设置登陆窗口方法***********************************	
	public clientLogin(){    
		
		this.setTitle("聊天室");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	    Font myfont=new Font("宋体",Font.PLAIN,12);
	    
		//设置标签
		labelUserName=new JLabel("用户名：");
		labelUserName.setFont(myfont);
		labelUserPwd=new JLabel("密 码：");
		labelUserPwd.setFont(myfont);
		
		Icon image = new ImageIcon("image/login.png");
		imagePos = new JLabel(image);
		
		//设置按钮组件
		buttonLogin=new JButton("登录");
		buttonLogin.setFont(myfont);
		buttonLogin.setBackground(new Color(0, 153, 255));
		buttonLogin.setToolTipText("进入聊天界面  ");
		buttonLogin.addActionListener(this);
		
		buttonRegister=new JButton("注册");
		buttonRegister.setFont(myfont);
		buttonRegister.setBackground(new Color(0, 153, 255));
		buttonRegister.setToolTipText(" 进入注册界面 ");
		buttonRegister.addActionListener(this);
		
		buttonCancel=new JButton("重置");
		buttonCancel.setFont(myfont);
		buttonCancel.setBackground(new Color(0, 153, 255));
		buttonCancel.setToolTipText(" 清除所填内容 ") ;
		buttonCancel.addActionListener(this);
		
		//设置文本框
		txtUserName=new JTextField();
		txtUserName.setToolTipText(" 输入您的姓名 ") ;
	
		txtUserPwd=new JPasswordField();
		txtUserPwd.setToolTipText(" 输入密码 ") ;
		
		//设置框架窗口布局管理为空布局管理
		JPanel panelObj=new JPanel();
		getContentPane().add(panelObj);
		panelObj.setLayout(null);
	
		//手工设置各控件的大小和位置
		labelUserName.setBounds(new Rectangle(40, 20, 60, 20));
		txtUserName.setBounds(new Rectangle(100, 20, 180, 20));
	    labelUserPwd.setBounds(new Rectangle(40, 60, 60, 20));
		txtUserPwd.setBounds(new Rectangle(100, 60, 180, 20));
	
		buttonLogin.setBounds(new Rectangle(40,100,60,20));
		buttonRegister.setBounds(new Rectangle(130,100,60,20));
		buttonCancel.setBounds(new Rectangle(220,100,60,20));
		
		imagePos.setBounds(new Rectangle(0, 0, 316, 133));

		//把各组件加到框架窗口中
		panelObj.add(labelUserName);
		panelObj.add(labelUserPwd);
		panelObj.add(txtUserName);
		panelObj.add(txtUserPwd);
		panelObj.add(buttonLogin);
		panelObj.add(buttonRegister);
		panelObj.add(buttonCancel);
		panelObj.add(imagePos);
	
		setResizable(false);
		setSize(320,160);
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
	  	
	  	if(obj==buttonCancel){               //取消
	    
	    	txtUserName.setText(null);
	        txtUserPwd.setText(null);
	    }
	    else if (obj==buttonLogin){          //提交
        
        	String n=txtUserName.getText();
            String p=String.valueOf(txtUserPwd.getPassword());
          
            if(n.length()==0&&p.length()!=0){
               	JOptionPane.showMessageDialog(this,"您没有输入姓名","提示!",JOptionPane.CANCEL_OPTION);
            	return;
            }
            else if (n.length()!=0&&p.length()==0){
                JOptionPane.showMessageDialog(this,"您没有输入密码","提示!",JOptionPane.CANCEL_OPTION);
            	return;
            }
            else if (n.length()==0&&p.length()==0){
               	JOptionPane.showMessageDialog(this,"您没有输入姓名和密码","提示!",JOptionPane.CANCEL_OPTION);
            	return;
            }
            
            enterChatRoom();     //调用用户登陆方法
        }                      
	  	else if (obj==buttonRegister){       //注册
	  	
	  		setVisible(false);
	  		new clientRegister();
	  	} 
	}
    //*********************************main()方法 ****************************************
	public static void main(String args[]){
	 
	 	 clientLogin f=new clientLogin();
   	}
   	
   	//*********************************用户登陆******************************************
   	public void enterChatRoom(){
   		
		LoginMessage uObj=new LoginMessage();     //接受用户登陆的详细资料
		uObj.loginName = new String(txtUserName.getText());
		uObj.loginPassword = new String(txtUserPwd.getPassword());
		setVisible(false);
  		login1= new Login(uObj);
  		//client1=new Client();
    	//try {
			//client1.Online(uObj.loginName);
		//} catch (IOException e) {
			//// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
    	//login1 = new Login(uObj);
   	}
}
