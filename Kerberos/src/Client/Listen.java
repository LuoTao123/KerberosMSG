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
	//*****************************�̹߳��캯��******************************************
	public Listen(String userName,Socket socket){
		this.name = userName;
	 	this.socket = socket;
		this.start();
    }   
    
    //*******************************�߳����з���***************************************
	public void run(){                               
	    
	    while(true){	
	    	
	    	try{
	    		toServer=this.socket.getOutputStream();
	    		fromServer = this.socket.getInputStream();	
	    		LocalIP LIP = new LocalIP();
				String ip = LIP.getLocalHostLANAddress().getHostAddress().toString();
	    		STATEC state = new STATEC();
	    		//��������
				byte[] bytes1 = new byte[2];
				BufferedInputStream bufferedInputStream = new BufferedInputStream(fromServer);
				bufferedInputStream.read(bytes1, 0, 2);
				System.out.println("Ҫը��");
				System.out.println(bytes1[0]+"��ɶ����");
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
}
	
