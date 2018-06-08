package Server;
import java.net.*;
import java.io.*;
//import java.util.*;

public class Connection extends Thread{
	String ip;
	Socket client;
	
	BufferedInputStream bufferedInputStream;
	InputStream fromClient;                     
	OutputStream toClient;    
	    
	    //*******************************线程运行方法***************************************
	public Connection(Socket client,String ip){
		this.ip = ip;
		this.client = client;
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
		STATE state = new STATE();
		bufferedInputStream = new BufferedInputStream(fromClient);
		int i = 0;
		while(true) {
			i++;
	    	try{
	    		//接收请求
				byte[] bytes1 = new byte[2];
				bufferedInputStream.read(bytes1, 0, 2);
/*				if(bytes1[0]==(byte)0x00&&bytes1[1]==(byte)0x00){
					
				}else{*/
				System.out.println("外部接受"+bytes1[0]+" "+bytes1[1]);
				state.Unpack_Head(bytes1, bufferedInputStream, this.client, ip);
//				}
				if(state.HasError) {
					return;
				}		
			}
			catch(IOException e){
		    	System.out.println();
		    }
		}  
	}
}
