package AS;

import Package.IPtoSocket;

import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server extends Thread{
	public static BigInteger Keysession;
	ServerSocket serverSocket;
	public static Vector<IPtoSocket> SocketList = new Vector<IPtoSocket>();
	
	public Server(){
		try{
			serverSocket = new ServerSocket(10000);
		}catch(IOException e){
			System.out.println(e+"无法启动服务器");
		}
		System.out.println("AS服务器启动");
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
		new Server();
	}
}
