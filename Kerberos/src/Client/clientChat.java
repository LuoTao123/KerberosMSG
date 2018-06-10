package Client;
import Client.*;
import Client.Listen;
import DES.Text;

import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import Package.*;
import RSA.Encryption;
import RSA.Hash;
import DES.*;
import RSA.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.*;

import DES.Text;
import Kerberos.TimeStamp;

//*******************************该类实现用户聊天****************************************
class clientChat extends JFrame{
	JPanel panelObj;
	JLabel status;
	JLabel status1;
	JLabel imagePos1;
	JLabel imagePos2;
	TextArea textMain;
	JTextArea textTalk;
	
	JButton sendMessage;
	JButton sendAdvice;
	JButton bClear;
	JButton bExit;
	
	
	boolean flag;
	String saveAdvice="";
	String userName;
	Listen listen;
	STATEC statec = new STATEC();
	Client client = new Client(statec);
	//**********************************构造函数*****************************************
	public clientChat(String name){
		
		userName = name;
		setTitle("聊天室");  	
     	this.setSize(688,570);
     	Dimension us = this.getSize();
    	//获取屏幕的尺寸
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2 ;
		int newY = (them.height - us.height) / 2 ;		
		this.setLocation(newX,newY);
	
		panelObj = new JPanel();
		getContentPane().add(panelObj);
		panelObj.setBackground(new Color(205, 205, 205));
		panelObj.setLayout(null);     		//设置框架窗口的布局管理器为null布局管理器
		Font f = new Font("宋体",Font.PLAIN,12);
		
		//初始化各个组件并赋值
		Icon image1 = new ImageIcon("image/left.png");
		imagePos1 = new JLabel(image1);
		
		Icon image2 = new ImageIcon("image/right.png");
		imagePos2 = new JLabel(image2);
		
		Icon image3 = new ImageIcon("image/girl2.png");
		
		Icon image4 = new ImageIcon("image/boy2.png");
		
		status = new JLabel("求放过");
		status.setForeground(Color.blue);       
		status1 = new JLabel("在线用户名 :"+userName); 
		status1.setForeground(Color.blue);
		
		//用户列表
		String message1 = "*****用户列表*****";
	
		
		
	   	//聊天文本域	  
		textMain = new TextArea(null,110,300,TextArea.SCROLLBARS_VERTICAL_ONLY);
		textMain.setEditable(false);   //聊天文本域不可写
		textMain.setBackground(Color.WHITE);
		STATEC.textmain=textMain;
		
		//输入消息框
		textTalk = new JTextArea();
		textTalk.setBackground(new Color(255,255,255));
		textTalk.setToolTipText("请输入你聊天的内容");
		
		//用户名选项
		String userlist[] ={"所有人"};
     
	 	//设置按钮
	 	sendMessage = new JButton("发送");
	 	sendMessage.setFont(f);
	 	sendMessage.addMouseListener(new MessageSent());
	 	sendMessage.setToolTipText("发送你聊天的内容");
		

		

		bClear = new JButton("清屏");
		bClear.setFont(f);
		bClear.addActionListener(new ListClear());
		bClear.setToolTipText("清空聊天文本区");
		
		bExit = new JButton("退出");	
		bExit.setFont(f);
		bExit.addActionListener(new ExitFrame());
		bExit.setToolTipText("退出聊天系统");
		
		sendMessage.setBackground(Color.LIGHT_GRAY);
		bClear.setBackground(Color.LIGHT_GRAY);
		bExit.setBackground(Color.LIGHT_GRAY);
		textMain.setBounds(new Rectangle(43, 20, 583, 370));
		textTalk.setBounds(new Rectangle(43, 420, 583, 71));
		sendMessage.setBounds(new Rectangle(560, 502, 70, 22));
		bClear.setBounds(new Rectangle(510, 396, 82, 22));
		bExit.setBounds(new Rectangle(166, 501, 82, 22));
		
		status.setBounds(new Rectangle(640,525,150,22));
		status1.setBounds(new Rectangle(30,525,200,22));
		
		imagePos1.setBounds(new Rectangle(0, 0, 400, 65));
		imagePos2.setBounds(new Rectangle(400, 0, 400, 65));
		panelObj.add(textMain);
		panelObj.add(textTalk);
		panelObj.add(sendMessage);
		panelObj.add(bClear);
		panelObj.add(bExit);
		
		panelObj.add(status);
		panelObj.add(status1);
		
		panelObj.add(imagePos1);
		panelObj.add(imagePos2);

		setResizable(false);
		setVisible(true);	
		
		flag = true;
		//listen = new Listen(userName,this);
		Listen listen = new Listen(Integer.valueOf(userName),client.state.C_VSocket,client.state);
	}	
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊发送聊天信息＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	class MessageSent implements MouseListener{
		
		Object msgObj;
		//ChatMessage chatMessage = new ChatMessage();
		public void mousePressed(MouseEvent e){	
			String message = new String(textTalk.getText());     
			Object obj = e.getSource();
			client.setIDc(Integer.valueOf(userName));
			if(obj == (JButton)sendMessage){
				try {
					client.Chat(message);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
			//try{
				//	Socket toServer = new Socket("127.0.0.1",1001);
					//ObjectOutputStream streamToServer = new ObjectOutputStream(toServer.getOutputStream());
					//if(getSendMessage())
					//streamToServer.writeObject(msgObj);
				//}	
				//catch(IOException ee){}
				//catch(Exception ie){System.out.println(ie);}
			}
		}
		public void mouseReleased(MouseEvent e){		
			Object obj = e.getSource();		
			if(obj == (JButton)sendMessage){
				textTalk.setText("");
			}
		}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		public void mouseClicked(MouseEvent e){}
		
		/////////////////////////////////获取信息/////////////////////////////////////////
		public boolean getSendMessage(){	
			String message = new String(textTalk.getText());       //获取聊天信息	
			if(message.length() == 0)        //判断输入是否为空
			{
				JOptionPane jopNamePass = new JOptionPane();
				jopNamePass.showMessageDialog(null,"无法发送空信息!");
				return false;
			}		
			else{
				
				GregorianCalendar  calendar = new GregorianCalendar();
       			String time ="【"+calendar.get(calendar.HOUR)+":"+calendar.get(calendar.MINUTE)+":"+
                                 calendar.get(calendar.SECOND)+"】";
				//chatMessage.chattime = time;
				//chatMessage.chatUser = userName;
				//chatMessage.chatToUser = toUser;
				//chatMessage.chatMessage = message;
				//msgObj = (ChatMessage)chatMessage;
				return true;
			}
		}
	}
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊清空聊天内容＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	class ListClear implements ActionListener{
	 	public void actionPerformed(ActionEvent e){
			Object obj = e.getSource(); 
		    if(obj == (JButton)bClear){
				textMain.setText("");
			}
		}
	}


	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊离开聊天室＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	class ExitFrame implements ActionListener{		
		public void actionPerformed(ActionEvent e){
			String string1 = "quit";
        	String string2 = "确定退出!";
        	userExit(string1,string2);
		}
	}		
	//＊＊＊＊＊＊＊＊＊＊＊＊＊处理窗口事件＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	protected void processWindowEvent(WindowEvent e) {
    	if (e.getID() == WindowEvent.WINDOW_CLOSING) {
        	String string1 = "quit";
        	String string2 = "确定退出!";
        	userExit(string1,string2);
    	}
	}
	//＊＊＊＊＊＊＊＊＊＊＊＊退出处理＊＊＊＊＊＊＊＊＊
	public void userExit(String str1,String str2){			
		ValidateMessage exObj = new ValidateMessage();
		exObj.validateName = userName;
		exObj.validateMessage =str1 ;
		
		try {
			client.Offline();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	/*	try {
       		Socket toServer = new Socket("127.0.0.1",1001);
       		ObjectOutputStream streamToServer = new ObjectOutputStream(toServer.getOutputStream());
			streamToServer.writeObject((ValidateMessage)exObj);
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null,str2);
			streamToServer.close();
 			toServer.close();
 			System.exit(1);
		}
		catch(IOException ee){
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null,"不能向服务器发送信息!");
		}
		catch(Exception ee){}*/
	}			
}	
	