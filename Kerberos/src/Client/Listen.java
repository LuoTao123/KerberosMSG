package Client;

import java.awt.Color;
import Server.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;



public class Listen extends Thread{
    
	InputStream fromServer;                     
	OutputStream toServer;           
	BufferedInputStream bufferedInputStream;
	clientChat frame;
	Server userlist;
	Object obj;
	Socket socket;                                                
	String name;  
	int IDc;
	public STATEC state;
	public STATE states;
	int count;
	//*****************************�̹߳��캯��******************************************
	public Listen(int IDc,Socket socket,STATEC statec){
		this.IDc = IDc;
	 	this.socket = socket;
	 	this.state = statec;
		try {
			toServer=this.socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fromServer = this.socket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		this.start();
    }   
    
    //*******************************�߳����з���***************************************
	public void run(){
		LocalIP LIP = new LocalIP();
		String ip = null;
		try {
			ip = LIP.getLocalHostLANAddress().getHostAddress().toString();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			this.socket.setSoTimeout(2000);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bufferedInputStream = new BufferedInputStream(fromServer);
		int i = 0;
	    while(true){
	    	i++;
	    	try{
	    		//��������
	    		byte[] bytes1 = new byte[2];
				bufferedInputStream.read(bytes1, 0, 2);
				System.out.println("����ͷ����"+bytes1[0]+" "+bytes1[1]+" ");////////////////////////////////  �޸�1
				state.Unpack_Head(bytes1, bufferedInputStream, this.socket, ip);
				if(state.HasError) {
					return;
				}		
			}catch(IOException e){
				//System.out.println("Socket������δ�յ������ݣ�");
			}
			if(state.Online == false){
				break;
			}
		}
		System.out.println(this.toString()+"�ѽ�����");
	}  
	//******************************����ʵ�ָ��ַ���*************************************
			//��ȡ�����Ϣ
			public void acceptMessage(){
				
			
			}
			
			//******************************�����������*****************************************
			public void addChatMessage(ChatMessage chat){
				String msg = new String();
					if(chat.chatUser.equals(name)){	
						msg = chat.chattime+"�Ҷ�"+chat.chatToUser+"˵��  "+chat.chatMessage+"\n\n";
					}
					else if(chat.chatToUser.equals(name)){	
						msg = chat.chattime+chat.chatUser+"����˵�� "+chat.chatMessage+"\n\n";
					}
					else{
						msg =chat.chattime+ chat.chatUser +"��"+chat.chatToUser+"˵�� "+chat.chatMessage+"\n\n";
					}
				frame.textMain.append(msg);
			}
}
	
