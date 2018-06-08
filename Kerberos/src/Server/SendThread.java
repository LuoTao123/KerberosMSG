package Server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendThread extends Thread{
	private Socket socket;
	private byte[] Head;
	private byte[] Data;
	
	public SendThread(Socket socket,byte[] Head,byte[] Data){
		this.socket = socket;
		  System.out.println("New connection accepted "+
			      socket.getInetAddress()+":"+socket.getPort());
		this.Head = Head;
		this.Data = Data;
		this.start();
	}
	
	public void run(){
		try{
			BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
			System.out.println("GGGGGGG");
			byte[] bytes = new byte[2];
			//////////////////////////////////////////////////////////////////////
			
			socket.getOutputStream().write(Head);
			System.out.println("GG");
			bufferedInputStream.read(bytes, 0, 2);
			if(bytes[0]==(byte)0x00&&bytes[1]==(byte)0x00){
				socket.getOutputStream().write(Data);

			}else{
				System.out.println("�ͻ��˷��س���");
			}
			//bufferedInputStream.close();
		}catch(IOException e){
			System.out.println(e);
		}
	}
}
