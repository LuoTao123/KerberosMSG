package Client;

import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

import Client.Listen;

import java.awt.event.*;

public class Login extends JFrame implements ActionListener{
	JPanel panel;
	STATEC statec = new STATEC();
	Client client = new Client(statec);
	
	public Login(LoginMessage uObj) {

		//userName = name;
		this.setTitle("登陆中……");  	
     	this.setSize(320,640);
     	Dimension us = this.getSize();
    	//获取屏幕的尺寸
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2 ;
		int newY = (them.height - us.height) / 2 ;		
		this.setLocation(newX,newY);
		
		
    	TextArea textarea; 
    	 panel = new JPanel();
 		getContentPane().add( panel);
 		 panel.setBackground(Color.LIGHT_GRAY);
 		 panel.setLayout(null);     		//设置框架窗口的布局管理器为null布局管理器
 		 textarea= new TextArea(null,110,300,TextArea.SCROLLBARS_VERTICAL_ONLY);
 		 textarea.setEnabled(false);
		 textarea.setEditable(false);   //聊天文本域不可写
		 textarea.setBackground(Color.LIGHT_GRAY);
	     textarea.setBounds(new Rectangle(0, 0, 314, 611));
		 panel.add(textarea);
		   	    
			setResizable(false);
			setVisible(true);
			 textarea.append("登陆中");	
				try {
					client.login(uObj.loginName, uObj.loginPassword,textarea);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Listen listen = new Listen(Integer.valueOf(uObj.loginName),client.state.C_VSocket,client.state);
				STATEC stateR = new STATEC();
				stateR.client = client;
				Listen listenR = new Listen(Integer.valueOf(uObj.loginName),client.ReC_Vsocket,stateR);
				try {
					client.Online();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.dispose();
				clientChat c=new clientChat(uObj.loginName);
			
		/*	 try{
				    Socket toServer;
				    toServer = new Socket("127.0.0.1",1001);   //连接到服务器
				    
				    String userName;                                               
				    
				    ObjectOutputStream streamToServer = new ObjectOutputStream(toServer.getOutputStream());
					streamToServer.writeObject((LoginMessage)uObj);      //写客户详细资料到服务器socket
						
					ObjectInputStream streamFromServer = new ObjectInputStream(toServer.getInputStream());
				
					ValidateMessage msg = (ValidateMessage)streamFromServer.readObject();//读来自服务器socket的登陆状态
				
					String str = msg.validateMessage;	   
					
					if(str.toString().equals("ok")){      //获取服务器传来的消息并进行判断
					 
					 	try{
					 		
							//userName = txtUserName.getText();
							clientChat chatFrame = new clientChat(uObj.loginName);
							this.dispose();	
						}
						catch(Exception e){
							
							System.out.println("没有启动线程"+e);
							toServer.close();
						}
					}
					else if(str.toString().equals("online")){
						
						JOptionPane jopPassword = new JOptionPane();
						jopPassword.showMessageDialog(null,"用户已登陆!");
						this.dispose();
			        	clientLogin login=new clientLogin();
						return;
					}
					else{
					
						JOptionPane jopPassword = new JOptionPane();
						jopPassword.showMessageDialog(null,"用户名或密码错误！");
						this.dispose();
						clientLogin login=new clientLogin();
						
						return;
					}
					streamToServer.close();
		            streamFromServer.close();
		            
				}
				catch(ClassNotFoundException e){}
				catch(InvalidClassException e){}
				catch(NotSerializableException e){}
				
				catch(IOException e){
					JOptionPane jop = new JOptionPane();
					jop.showMessageDialog(null,"不能连接服务器或你还没注册！！！");
					this.dispose();
					clientLogin login=new clientLogin();
				}
			 
			 */
			 
			   // JOptionPane jopPassword = new JOptionPane();
				//jopPassword.showMessageDialog(null,"注册成功！");
			//	this.dispose();
				
				//获取登陆信息
			//	clientLogin login=new clientLogin();
	}
	
	public static void main(String args[]){
		 
	 	
		//new Login(uObj);
  	}




	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
