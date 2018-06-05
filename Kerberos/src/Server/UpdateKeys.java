package Server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.Socket;

import DES.DES;
import DES.Keys;
import DES.Text;
import DataBase.sql;
import Package.Data_Chat;
import Package.Data_Update;
import Package.EK_message;
import Package.IPtoSocket;
import Package.Pack;

public class UpdateKeys extends Thread{
	InputStream inputStream;
	BufferedInputStream bufferedInputStream;
	OutputStream outputStream;
	
	public UpdateKeys(){
		this.start();
	}
	
	@SuppressWarnings("static-access")
	public void run(){
		while(true){
			try{
				Pack pack = new Pack();
				IPtoSocket NewIS = null;
				DES des = new DES();
				BigInteger Key = des.CreateDESKey();
				Server.Keysession = Key;
				Data_Update DU = new Data_Update();
				DU.setKey(Key);
				Text text = new Text();
				Keys kkey = new Keys();
				for(int i=0;i<Server.SocketList.size();i++){
					NewIS = Server.SocketList.elementAt(i);
					sql a = new sql();
					String[] strs = a.HasAIDc(NewIS.IDc).split(" ");
					BigInteger Keyc = text.StringToBigInteger(strs[1]);
					int[] Keycc = kkey.StringToInts(kkey.BigIntegerToString(Keyc));
					@SuppressWarnings("unused")
					SendThread ST = new SendThread(NewIS.socket,pack.Pack_0x19_Cont(),pack.Pack_0x19_Data(DU,Keycc));
				}	
			}catch(Exception e){
				System.out.println(e);
			}
			try {
				this.sleep(360000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}