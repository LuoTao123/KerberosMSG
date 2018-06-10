package TGS;

import Package.IPtoSocket;

import java.awt.TextArea;
import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Server extends Thread{
	public static BigInteger Keysession;
	ServerSocket serverSocket;
	TextArea textArea;
	public static Vector<IPtoSocket> SocketList = new Vector<IPtoSocket>();
	
	public void Server(TextArea textArea){
		this.textArea = textArea;
		try{
			serverSocket = new ServerSocket(20000);
		}catch(IOException e){
			System.out.println(e+"无法启动服务器");
			textArea.append(String.valueOf(e)+"无法启动服务器\n");
		}
		System.out.println("TGS服务器启动");
		textArea.append("TGS服务器启动\n");
		this.start();
	}
	
	public Server(){
		
	}
	
	public void run(){                                   
		try{
			while(true){
				Socket client = serverSocket.accept(); 	
				String ip = client.getInetAddress().getHostAddress();
				@SuppressWarnings("unused")
				Connection connect=new Connection(client,ip,this.textArea);
			}		
		}
		catch(IOException e){
			System.out.println(e+"Not listening");
			textArea.append(String.valueOf(e)+"Not listening\n");
		}
	}
	
	public  void Serverfail(TextArea textArea) {
		 try {  
          if(serverSocket!=null){  
          serverSocket.close();  
          //isRun = false;  
         // btn_ok.setEnabled(true);  
         textArea.append("TGS服务器关闭成功……\n");
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
		new Server();
	}
}
