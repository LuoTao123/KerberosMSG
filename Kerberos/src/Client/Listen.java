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
	//*****************************线程构造函数******************************************
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
    
    //*******************************线程运行方法***************************************
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
	    		//接收请求
	    		byte[] bytes1 = new byte[2];
				bufferedInputStream.read(bytes1, 0, 2);
				System.out.println("接受头部："+bytes1[0]+" "+bytes1[1]+" ");////////////////////////////////  修改1
				state.Unpack_Head(bytes1, bufferedInputStream, this.socket, ip);
				if(state.HasError) {
					return;
				}		
			}catch(IOException e){
				//System.out.println("Socket两秒内未收到新数据！");
			}
			if(state.Online == false){
				break;
			}
		}
		System.out.println(this.toString()+"已结束！");
	}  
	//******************************以下实现各种方法*************************************
			//获取面板消息
			public void acceptMessage(){
				
			
			}
			
			//******************************添加聊天内容*****************************************
			public void addChatMessage(ChatMessage chat){
				String msg = new String();
					if(chat.chatUser.equals(name)){	
						msg = chat.chattime+"我对"+chat.chatToUser+"说：  "+chat.chatMessage+"\n\n";
					}
					else if(chat.chatToUser.equals(name)){	
						msg = chat.chattime+chat.chatUser+"对我说： "+chat.chatMessage+"\n\n";
					}
					else{
						msg =chat.chattime+ chat.chatUser +"对"+chat.chatToUser+"说： "+chat.chatMessage+"\n\n";
					}
				frame.textMain.append(msg);
			}
}
	
