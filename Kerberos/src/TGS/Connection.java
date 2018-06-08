package TGS;
import java.net.*;
import java.io.*;
//import java.util.*;

public class Connection extends Thread{
	String ip;
	Socket client;
	
	InputStream inputStream;
	BufferedInputStream bufferedInputStream;
	OutputStream outputStream;
	
	public Connection(Socket client,String ip){
		this.ip = ip;
		this.client = client;
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
			STATE state = new STATE();
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
