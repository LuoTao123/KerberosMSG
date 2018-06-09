package Server;

import Package.IPtoSocket;

import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server extends Thread{
	public static BigInteger Keysession;
	ServerSocket serverSocket;
	ServerSocket serverSocketResponse;
	public static Vector<IPtoSocket> SocketList = new Vector<IPtoSocket>();
	public static Vector<STATE> StateList = new Vector<STATE>();
	public static boolean flag = false;
	public Server(){
		try{
			serverSocket = new ServerSocket(30000);
			serverSocketResponse = new ServerSocket(30001);
		}catch(IOException e){
			System.out.println(e+"无法启动服务器");
		}
		System.out.println("服务器启动");
//		@SuppressWarnings("unused")
//		UpdateKeys UK = new UpdateKeys();
		this.start();
	}
	
	public void run(){                                   
		try{
			int i=0;
			while(i<20){
				i++;
				Socket client = serverSocket.accept();
				Socket clientResponse =serverSocketResponse.accept();
				String ip = client.getInetAddress().getHostAddress();
				@SuppressWarnings("unused")
				Connection connect = new Connection(client,ip,clientResponse);
				@SuppressWarnings("unused")
				ConnectionR connectResponse = new ConnectionR(clientResponse,ip,client);
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
