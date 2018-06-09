package Server;
import java.net.*;
import java.io.*;
//import java.util.*;

public class ConnectionR extends Thread{
	String ip;
	Socket client;
	public Socket Responseclient;
	public STATE state;
	BufferedInputStream bufferedInputStream;
	InputStream fromClient;                     
	OutputStream toClient;    
	    
	    //*******************************线程运行方法***************************************
	public ConnectionR(Socket client,String ip,Socket ResponseSocket){
		this.ip = ip;
		this.client = client;
		this.Responseclient = ResponseSocket;
		try {
			toClient=this.client.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fromClient = this.client.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		this.start();
	}
	
	public void run(){                               
		state = new STATE();
		state.setIDC(1276338796);
		try {
			this.client.setSoTimeout(2000);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Server.StateList.addElement(state);
		state.ResponseSocket = this.Responseclient;
		bufferedInputStream = new BufferedInputStream(fromClient);
		int i = 0;
		while(true) {
			i++;
	    	//接收请求
			byte[] bytes1 = new byte[2];
			try{
				
				bufferedInputStream.read(bytes1, 0, 2);
/*					if(bytes1[0]==(byte)0x00&&bytes1[1]==(byte)0x00){
				
				}else{*/
				System.out.println("外部接受"+bytes1[0]+" "+bytes1[1]);
				state.Unpack_Head(bytes1, bufferedInputStream, this.client, ip);
//					}
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
	}
}
