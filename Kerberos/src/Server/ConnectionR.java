package Server;
import java.net.*;

import Package.STATETOIP;

import java.awt.TextArea;
import java.io.*;
//import java.util.*;

public class ConnectionR extends Thread{
	String ip;
	Socket client;
	public Socket Responseclient;
	public STATE state;
	BufferedInputStream bufferedInputStream;
	InputStream fromClient;                     
	OutputStream toClient;
	java.awt.List list;
	TextArea textArea;
	    
	    //*******************************�߳����з���***************************************
	public ConnectionR(Socket client,String ip,Socket ResponseSocket,TextArea textArea,java.awt.List list){
		this.ip = ip;
		this.client = client;
		this.list = list;
		this.Responseclient = ResponseSocket;
		this.textArea = textArea;
		try {
			toClient=this.client.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fromClient = this.client.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		this.start();
	}
	
	public void run(){                               
		state = new STATE();
		state.textArea = this.textArea;
		try {
			this.client.setSoTimeout(2000);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		STATETOIP stateip = new STATETOIP();
		stateip.state = state;
		stateip.ip = this.ip;
		Server.StateToIp.addElement(stateip);
		System.out.println("ConnectionR���߳�"+this.toString());
		System.out.println("��Ӧ״̬��"+state.toString());
		System.out.println("�����˿ڣ�"+Responseclient.toString());
		System.out.println("�����˿ڣ�"+client.toString());
		state.ResponseSocket = this.client;
		bufferedInputStream = new BufferedInputStream(fromClient);
		while(true) {
	    	//��������
			byte[] bytes1 = new byte[2];
			try{
				
				bufferedInputStream.read(bytes1, 0, 2);
/*					if(bytes1[0]==(byte)0x00&&bytes1[1]==(byte)0x00){
				
				}else{*/
				System.out.println("�ⲿ����"+bytes1[0]+" "+bytes1[1]);
				state.Unpack_Head(bytes1, bufferedInputStream, this.client, ip);
//					}
				list.removeAll();
				for(int i=0;i<Server.SocketList.size();i++){
					list.add(Integer.toString(Server.SocketList.elementAt(i).IDc));
				}
				if(state.HasError) {
					return;
				}
			}catch(IOException e){
				//System.out.println("Socket������δ�յ������ݣ�");
			}
			if(state.Online == false){
				break;
			}
		}
		System.out.println(this.toString()+"�ѽ�����");
	}
}
