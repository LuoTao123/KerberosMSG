package ServerIDPSW;

import Package.IPtoSocket;
import Server.Connection;

import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ServerIDPSW extends Thread{
	public static BigInteger Keysession;
	ServerSocket serverSocket;
	public static Vector<IPtoSocket> SocketList = new Vector<IPtoSocket>();
	
	public ServerIDPSW(){
		try{
			serverSocket = new ServerSocket(40000);
		}catch(IOException e){
			System.out.println(e+"�޷�����������");
		}
		System.out.println("�˺��������������");
		this.start();
	}
	
	public void run(){                                   
		try{
			
			while(true){
				Socket client = serverSocket.accept(); 	
				String ip = client.getInetAddress().getHostAddress();
				@SuppressWarnings("unused")
				Connection connect=new Connection(client,ip);
			}		
		}
		catch(IOException e){
			System.out.println(e+"Not listening");	      
		}
	}
	
	public static void main(String arg[]){
		new ServerIDPSW();
	}
}
