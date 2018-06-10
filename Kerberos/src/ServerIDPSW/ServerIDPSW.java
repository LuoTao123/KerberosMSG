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
			System.out.println(e+"�޷�����������");
			textArea.append(e+"�޷�����������\n");
		}
		textArea.append("�˺��������������\n");
		System.out.println("�˺��������������");
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
        textArea.append("�˺�����������رճɹ�����\n");
         System.out.println("�������رճɹ�����");
     //    return true;
         }  
         else{  
             JOptionPane.showMessageDialog(null, "����δ������������������ֹͣ");  
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
