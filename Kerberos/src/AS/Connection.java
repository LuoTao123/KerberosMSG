package AS;
import java.net.*;
import java.awt.TextArea;
import java.io.*;
//import java.util.*;

public class Connection extends Thread{
	String ip;
	Socket client;
	TextArea textArea;
	InputStream inputStream;
	BufferedInputStream bufferedInputStream;
	OutputStream outputStream;
	STATEA state;
	
	public Connection(Socket client,String ip,TextArea textArea){
		this.ip = ip;
		this.client = client;
		this.textArea=textArea;
		this.state = new STATEA();
		state.textArea = this.textArea;
		try {
			inputStream = client.getInputStream();
			bufferedInputStream =new BufferedInputStream(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.start();
	}
	
	public void run(){
		try{
			byte[] bytes = new byte[2];
			bufferedInputStream.read(bytes, 0, 2);
			state.Unpack_Head(bytes, bufferedInputStream, client, ip);
		}catch(IOException e){
			System.out.println(e);
		}finally{
			try{
				client.close();
			}catch(IOException e){
				
			}
		}
	}
}
