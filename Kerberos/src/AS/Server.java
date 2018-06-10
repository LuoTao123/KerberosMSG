package AS;

import Package.IPtoSocket;
import Package.STATETOIP;

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
	public static Vector<IPtoSocket> SocketList = new Vector<IPtoSocket>();
	TextArea textArea;
	
	public void Server(TextArea textArea){
		try{
			this.textArea=textArea;
			serverSocket = new ServerSocket(10000);
		}catch(IOException e){
			System.out.println(e+"无法启动服务器");
			textArea.append("AS服务器启动失败……\n");
		}
		System.out.println("AS服务器启动");
		textArea.append("AS服务器启动……\n");
		this.start();
	}
	
	public Server(){
		
	}
	
	public void run(){                                   
		try{
			
			while(true){
				Socket client = serverSocket.accept(); 	
				String ip = client.getInetAddress().getHostAddress();
				textArea.append(ip);
				textArea.append("进行票据申请，已建立连接……\n");  
				@SuppressWarnings("unused")
				Connection connect=new Connection(client,ip,this.textArea);
			}		
		}
		catch(IOException e){
			System.out.println(e+"Not listening");	
			textArea.append(e.toString()+"Not listening\n");  
		}
	}
	
	public  void Serverfail(TextArea textArea) {
		 try {  
          if(serverSocket!=null){  
          serverSocket.close();  
          //isRun = false;  
         // btn_ok.setEnabled(true);  
         textArea.append("AS服务器关闭成功……\n");
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
//		new Server();
	}
}
