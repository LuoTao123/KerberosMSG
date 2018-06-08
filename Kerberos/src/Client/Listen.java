package Client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import Package.Pack;


public class Listen extends Thread{
    
	InputStream fromServer;                     
	OutputStream toServer;                             
	Socket socket;                                                
	String name;  
	int count;
	//*****************************线程构造函数******************************************
	public Listen(String userName,Socket socket){
		this.name = userName;
	 	this.socket = socket;
		this.start();
    }   
    
    //*******************************线程运行方法***************************************
	public void run(){                               
	    	
	    	try{
	    		toServer=this.socket.getOutputStream();
	    		fromServer = this.socket.getInputStream();	
	    		LocalIP LIP = new LocalIP();
				String ip = LIP.getLocalHostLANAddress().getHostAddress().toString();
	    		STATEC state = new STATEC();
	    		//接收请求
				byte[] bytes1 = new byte[2];
				BufferedInputStream bufferedInputStream = new BufferedInputStream(fromServer);
				bufferedInputStream.read(bytes1, 0, 2);
				System.out.println("要炸！");
				System.out.println(bytes1[0]+"是啥？？");
				state.Unpack_Head(bytes1, bufferedInputStream, this.socket, ip);
				bufferedInputStream.close();
				fromServer.close();
				if(state.HasError) {
					return;
				}		
				toServer.close();
				fromServer.close();
				socket.close();
			}
			catch(IOException e){
		    	System.out.println();
		    }
		  
	}
}
	
