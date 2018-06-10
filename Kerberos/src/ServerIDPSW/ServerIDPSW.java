package ServerIDPSW;

import Package.IPtoSocket;

import java.awt.TextArea;
import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JOptionPane;

public class ServerIDPSW extends Thread{
	public static BigInteger Keysession;
	ServerSocket serverSocket;
	TextArea textArea;
	public static Vector<IPtoSocket> SocketList = new Vector<IPtoSocket>();
	
	public void ServerIDPSW(TextArea textArea){
		try{
			this.textArea = textArea;
			serverSocket = new ServerSocket(40000);
		}catch(IOException e){
			System.out.println(e+"无法启动服务器");
			textArea.append(e+"无法启动服务器\n");
		}
		textArea.append("账号密码服务器启动\n");
		System.out.println("账号密码服务器启动");
		this.start();
	}
	
	public ServerIDPSW(){
		
	}
	
	public void run(){                                   
		try{
			
			while(true){
				Socket client = serverSocket.accept(); 	
				String ip = client.getInetAddress().getHostAddress();
				@SuppressWarnings("unused")
				Connection connect=new Connection(client,ip,textArea);
			}		
		}
		catch(IOException e){
			System.out.println(e+"Not listening");	      
		}
	}
	
	public  void Serverfail(TextArea textArea) {
		 try {  
         if(serverSocket!=null){  
         serverSocket.close();  
         //isRun = false;  
        // btn_ok.setEnabled(true);  
        textArea.append("账号密码服务器关闭成功……\n");
         System.out.println("服务器关闭成功……");
     //    return true;
         }  
         else{  
             JOptionPane.showMessageDialog(null, "服务还未启动，请启动服务再停止");  
         //    return false;
         }  
     } catch (IOException e1) {  
         e1.printStackTrace();  
     }
		  
	} 
	
	public static void main(String arg[]){
		new ServerIDPSW();
	}
}
