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
		this.setTitle("��½�С���");  	
     	this.setSize(320,640);
     	Dimension us = this.getSize();
    	//��ȡ��Ļ�ĳߴ�
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2 ;
		int newY = (them.height - us.height) / 2 ;		
		this.setLocation(newX,newY);
		
		
    	TextArea textarea; 
    	 panel = new JPanel();
 		getContentPane().add( panel);
 		 panel.setBackground(Color.LIGHT_GRAY);
 		 panel.setLayout(null);     		//���ÿ�ܴ��ڵĲ��ֹ�����Ϊnull���ֹ�����
 		 textarea= new TextArea(null,110,300,TextArea.SCROLLBARS_VERTICAL_ONLY);
 		 textarea.setEnabled(false);
		 textarea.setEditable(false);   //�����ı��򲻿�д
		 textarea.setBackground(Color.LIGHT_GRAY);
	     textarea.setBounds(new Rectangle(0, 0, 314, 611));
		 panel.add(textarea);
		   	    
			setResizable(false);
			setVisible(true);
			 textarea.append("��½��");	
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
				    toServer = new Socket("127.0.0.1",1001);   //���ӵ�������
				    
				    String userName;                                               
				    
				    ObjectOutputStream streamToServer = new ObjectOutputStream(toServer.getOutputStream());
					streamToServer.writeObject((LoginMessage)uObj);      //д�ͻ���ϸ���ϵ�������socket
						
					ObjectInputStream streamFromServer = new ObjectInputStream(toServer.getInputStream());
				
					ValidateMessage msg = (ValidateMessage)streamFromServer.readObject();//�����Է�����socket�ĵ�½״̬
				
					String str = msg.validateMessage;	   
					
					if(str.toString().equals("ok")){      //��ȡ��������������Ϣ�������ж�
					 
					 	try{
					 		
							//userName = txtUserName.getText();
							clientChat chatFrame = new clientChat(uObj.loginName);
							this.dispose();	
						}
						catch(Exception e){
							
							System.out.println("û�������߳�"+e);
							toServer.close();
						}
					}
					else if(str.toString().equals("online")){
						
						JOptionPane jopPassword = new JOptionPane();
						jopPassword.showMessageDialog(null,"�û��ѵ�½!");
						this.dispose();
			        	clientLogin login=new clientLogin();
						return;
					}
					else{
					
						JOptionPane jopPassword = new JOptionPane();
						jopPassword.showMessageDialog(null,"�û������������");
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
					jop.showMessageDialog(null,"�������ӷ��������㻹ûע�ᣡ����");
					this.dispose();
					clientLogin login=new clientLogin();
				}
			 
			 */
			 
			   // JOptionPane jopPassword = new JOptionPane();
				//jopPassword.showMessageDialog(null,"ע��ɹ���");
			//	this.dispose();
				
				//��ȡ��½��Ϣ
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
