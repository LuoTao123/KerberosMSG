package Server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;

public class SendThread extends Thread{
	private Socket socket;
	private byte[] Head;
	private byte[] Data;
	
	public SendThread(Socket socket,byte[] Head,byte[] Data){
		this.socket = socket;
		this.Head = Head;
		this.Data = Data;
		this.start();
	}
	
	public void run(){
		try{
			BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
			byte[] bytes = new byte[2];
			socket.getOutputStream().write(Head);
			bufferedInputStream.read(bytes, 0, 2);
			if(bytes[0]==(byte)0x00&&bytes[1]==(byte)0x00){
				socket.getOutputStream().write(Data);
			}else{
				System.out.println("客户端返回出错");
			}
			bufferedInputStream.close();
		}catch(IOException e){
			System.out.println(e);
		}
	}
}
