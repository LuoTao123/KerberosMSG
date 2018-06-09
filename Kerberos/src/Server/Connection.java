package Server;
import java.net.*;
import java.io.*;
//import java.util.*;

public class Connection extends Thread{
	String ip;
	Socket client;
	public Socket Responseclient;
	public STATE state;
	BufferedInputStream bufferedInputStream;
	InputStream fromClient;                     
	OutputStream toClient;    
	    
	    //*******************************线程运行方法***************************************
	public Connection(Socket client,String ip,Socket ResponseSocket){
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
		state.ResponseSocket = this.Responseclient;
		System.out.println(state.ResponseSocket.getInetAddress());
		try {
			this.client.setSoTimeout(2000);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("ConnectionR的线程"+this.toString());
		System.out.println("相应状态机"+state.toString());
		System.out.println("被动端口："+Responseclient.toString());
		System.out.println("主动端口："+client.toString());
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
	    	}catch(IOException e){
				//System.out.println("Socket两秒内未收到新数据！");
			}
				if(state.Online == false){
					break;
				}
		}
		System.out.println(this.toString()+"已结束！");
	}
}
