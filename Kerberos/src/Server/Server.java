package Server;

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
	ServerSocket serverSocketResponse;
	public static Vector<IPtoSocket> SocketList = new Vector<IPtoSocket>();
	public static Vector<STATETOIP> StateToIp = new Vector<STATETOIP>();
	public static boolean flag = false;
	public java.awt.List list;
	public TextArea textArea;
	public void Server1(TextArea textArea,java.awt.List list){
		try{
			this.list = list;
			this.textArea = textArea;
			serverSocket = new ServerSocket(30000);
			serverSocketResponse = new ServerSocket(30001);
		}catch(IOException e){
			System.out.println(e+"�޷�����������");
			textArea.append("�޷�����������\n");
		}
		textArea.append("����������\n");
		System.out.println("����������");
//		@SuppressWarnings("unused")
//		UpdateKeys UK = new UpdateKeys();
		this.start();
	}
	
	public Server(){
		
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
				Connection connect = new Connection(client,ip,clientResponse,textArea,list);
				@SuppressWarnings("unused")
				ConnectionR connectResponse = new ConnectionR(clientResponse,ip,client,textArea,list);
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
        textArea.append("Ӧ�÷������رճɹ�����\n");
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
		//new Server();
	}
}
