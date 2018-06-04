package Package;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.net.SocketTimeoutException;
import DataBase.sql;
import DES.*;
import Kerberos.TimeStamp;
import Server.Server;
import Server.SendThread;

public class STATE extends Thread{
	private static final int[] KeycInts = null;
	public static int IDas = 1000000000;
	public static int IDtgs = 1000000001;
	public static int IDv = 1000000002;
	public static int IDis = 1000000003;
	private BigInteger KeyV;
	private int IDC;
	private int ADC;
	private int[] Keyctgs;
	private int[] Keycv;
	private Ticket tickettgs,ticketv;
	
	public void setIDC(int idc) {
		this.IDC = idc;
	}
	
	public void setADC(int adc) {
		this.ADC = adc;
	}
	public int getIDC() {
		return ADC;
		
	}
	
	public int getADC() {
		return ADC;
	}
	
	public void setKeyctgs(int[] key) {
		this.Keyctgs = key;
	}
	
	public void setKeycv(int[] key) {
		this.Keycv  = key;
	}
	
	public int[] getKeyctgs() {
		return Keyctgs;
	}
	
	public int[] getKeycv() {
		return Keycv;
	}
	
	public void setTickettgs(Ticket t) {
		this.tickettgs = t;
	}
	
	public Ticket getTickettgs() {
		return tickettgs;
	}
	
	public void setTicketv(Ticket t) {
		this.ticketv = t;
	}
	
	public Ticket getTicketv() {
		return ticketv;
	}
	
	public void Unpack_Head(byte[] NewByte,BufferedInputStream bufferedInputStream,Socket socket,String ip) throws SocketTimeoutException, IOException{
		Unpack unpack = new Unpack();
		if(NewByte[0]==(byte)0x00){
			switch(NewByte[1]){
				case (byte)0x02:	zhuangtaiji2();break;
				case (byte)0x03:	zhuangtaiji3();break;
				case (byte)0x04:	zhuangtaiji4();break;
				case (byte)0x05:	zhuangtaiji5();break;
				case (byte)0x06:	zhuangtaiji6();break;
				case (byte)0x11:	zhuangtaiji11();break;
				case (byte)0x12:	zhuangtaiji12();break;
				case (byte)0x14:	zhuangtaiji14();break;
				case (byte)0x15:	zhuangtaiji15();break;
				case (byte)0x0d:	zhuangtaiji0d();break;
				case (byte)0x0e:	zhuangtaiji0e();break;
				case (byte)0x0f:	zhuangtaiji0f();break;
				case (byte)0x17:	zhuangtaiji17();break;
				case (byte)0x18:	zhuangtaiji18();break;
				case (byte)0x1a:	zhuangtaiji1a();break;
				case (byte)0x1b:	zhuangtaiji1b();break;
				default : System.out.println("非法数据包");
			}
		}else if(NewByte[0]==(byte)0x01){
			Pack pack = new Pack();
			socket.getOutputStream().write(pack.Server_Return());
			switch(NewByte[1]){
				case (byte)0x00:	zhuangtaiji0(unpack.Unpack_0x00(readFixedLengthArray(bufferedInputStream,132)));break;
				case (byte)0x01:	zhuangtaiji1(unpack.Unpack_0x01(readFixedLengthArray(bufferedInputStream,260)));break;
				case (byte)0x10:	OnlineTransmit(unpack.Unpack_0x10(readFixedLengthArray(bufferedInputStream,4)),socket);break;
				case (byte)0x13:ChatTransmit(socket,	readFixedLengthArray(bufferedInputStream,276));break;
				case (byte)0x07:	zhuangtaiji7(unpack.Unpack_0x07(readFixedLengthArray(bufferedInputStream,26)),ip);break;
				case (byte)0x08:	zhuangtaiji8(unpack.Unpack_0x08(readFixedLengthArray(bufferedInputStream,76)),ip);break;
				case (byte)0x09:	zhuangtaiji9(unpack.Unpack_0x09(readFixedLengthArray(bufferedInputStream,72)),ip);break;
				case (byte)0x0a:	zhuangtaiji0a(unpack.Unpack_0x0a(readFixedLengthArray(bufferedInputStream,72),Keyctgs),ip);break;
				case (byte)0x0b:	zhuangtaiji0b(unpack.Unpack_0x0b(readFixedLengthArray(bufferedInputStream,68),Keycv));break;
				case (byte)0x0c:	zhuangtaiji0c(unpack.Unpack_0x0c(readFixedLengthArray(bufferedInputStream,18)));break;
				case (byte)0x16:	OfflineTransmit(unpack.Unpack_0x16(readFixedLengthArray(bufferedInputStream,4)),socket);break;
				case (byte)0x19:	zhuangtaiji19(unpack.Unpack_0x19(readFixedLengthArray(bufferedInputStream,8)));break;
				case (byte)0x1c:zhuangtaiji1c(unpack.Unpack_0x1c(readFixedLengthArray(bufferedInputStream,276)));break;

				default : System.out.println("非法数据包");
			}
		}else{
			System.out.println(NewByte[0]);
			System.out.println("该包非法！");
			///////////////////////////////////////////////////////////////log
		}
	}
	

	public void zhuangtaiji0(Data_Regist DR){
		System.out.println(DR.getIDc());
		System.out.println(DR.getRSA_HASH_PASSWORD().toString());
	}
	
	public void zhuangtaiji1(Data_Modify DM){
		System.out.println(DM.getIDc());
		System.out.println(DM.getRSA_HASH_PASSWORD().toString());
		System.out.println(DM.getRSA_HASH_NPASSWORD().toString());
	}
	
	public void zhuangtaiji2(){
		System.out.println("注册成功");
	}
	
	public void zhuangtaiji3(){
		System.out.println("注册失败，账号存在！");
	}
	
	public void zhuangtaiji4(){
		System.out.println("修改成功");
	}
	
	public void zhuangtaiji5(){
		System.out.println("修改失败,账号不存在");
	}
	
	public void zhuangtaiji6(){
		System.out.println("修改失败,原密码错误");
	}
	
	public void zhuangtaiji7(C_AS CA,String ip){
		int IDc = CA.getIDc();
		setIDC(IDc);
		int IDt = CA.getIDtgs();
		String TS = CA.getTS();
		String str = null;
		Pack pack = new Pack();
		if(IDt == IDtgs) {
			sql a=new sql();
			try {
				str = a.HasAIDc (IDc);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(str !=null) {
				String[] IDPa = str.split(" ");
				String Hash_Pass=IDPa[1] ;
				Text text = new Text();
				Keys kkey = new Keys();
				int[] Keytgs = kkey.ReadKeysFromFile("Keytgs.txt");
				BigInteger Keyc =text.StringToBigInteger(Hash_Pass); 
				
				int[] KeycInts = kkey.StringToInts(kkey.BigIntegerToString(Keyc));

				DES des =new DES();
				AS_C AC = new AS_C();
				BigInteger Keyctgs = des.CreateDESKey();
				
				int[] KeyctgsInts = kkey.StringToInts(kkey.BigIntegerToString(Keyctgs));
				
				//保存Keyctgs
				setKeyctgs(KeyctgsInts);
				
				TimeStamp TS1 = new TimeStamp();
				String TimeS = TS1.getTimeString();
				int lifetime = 20;
				Ticket ticket = new Ticket();
				int ADc = pack.ByteArrayToInt2(pack.IPStringToByte(ip));
				setADC(ADc);
				ticket.setID1(IDc);
				ticket.setAD(ADc);
				ticket.setID2(IDt);
				ticket.setKey(Keyctgs);
				ticket.setTS(TimeS);
				ticket.setLT(lifetime);
				
				//保存tickettgs
				setTickettgs(ticket);
				
				AC.setID(IDc);
				AC.setKey(Keyctgs);
				AC.setLT(lifetime);
				AC.setTicket(ticket);
				AC.setTS(TimeS);
				byte[] msg = pack.Pack_0x08_Data(AC,KeycInts,Keytgs);
				send(msg);
			}else {
				byte[] msg = pack.Pack_0x0d_Cont();
				send(msg);
			}
		}else {
			byte[] msg = pack.Pack_0x0d_Cont();
			send(msg);
		}
		System.out.println("客户端"+CA.getIDc());
		System.out.println("请求访问"+CA.getIDtgs());
		System.out.println("时间戳为"+CA.getTS());
	}
	
	//客户端完成收包拆包
	public void zhuangtaiji8(AS_C AC,String ip){
		int IDc =IDC;
		int IDt = AC.getID();
		String TS = AC.getTS();
		String str = null;
		Pack pack = new Pack();
		Text text = new Text();
		Keys kkey = new Keys();
		DES des =new DES();
		C_TGS CT = new C_TGS();
		int[] Keyctgs = getKeyctgs();
		TimeStamp TS1 = new TimeStamp();
		String TimeS = TS1.getTimeString();
		int lifetime = 20;
		Ticket ticket = new Ticket();
		Authenticator auth = new Authenticator();
	////////////////////////////////
		ticket = getTickettgs();
		int ADc = pack.ByteArrayToInt2(pack.IPStringToByte(ip));
		auth.setAD(ADc);
		auth.setID(IDc);
		auth.setTS(TimeS);
		CT.setID(this.IDv);
		CT.setTicket(ticket);
		CT.setAuthenticator(auth);
		byte[] msg = pack.Pack_0x09_Data(CT,Keyctgs);
		send(msg);
		System.out.println("AS->C成功！");
	}
	
	public void zhuangtaiji9(C_TGS CT,String ip){
		int IDc = IDC;
		int IDv = CT.getID();
		//int IDt = CA.getIDtgs();
		Ticket ticket = CT.getTicket();
		Authenticator auth = CT.getAuthenticator();
		String str = null;
		Pack pack = new Pack();
		if(IDv== this.IDv) {
				Text text = new Text();
				Keys kkey = new Keys();
				BigInteger Keyctgs = ticket.getKey();
				//Kv
				int[] Keyv = kkey.ReadKeysFromFile("Keyv.txt");
				//Kctgs
				int[] KeyctgsInts = kkey.StringToInts(kkey.BigIntegerToString(Keyctgs));
				//Kcv
				DES des =new DES();
				//AS_C AC = new AS_C();
				TGS_C TC = new TGS_C();
				BigInteger Keycv = des.CreateDESKey();
				int[] KeycvInts = kkey.StringToInts(kkey.BigIntegerToString(Keycv));
				
				//保存Keycv
				setKeycv(KeycvInts);
				
				TimeStamp TS1 = new TimeStamp();
				String TimeS = TS1.getTimeString();
				int lifetime = 20;
				Ticket ticketv = new Ticket();
				int ADc = pack.ByteArrayToInt2(pack.IPStringToByte(ip));
				ticketv.setID1(IDc);
				ticketv.setAD(ADc);
				ticketv.setID2(this.IDtgs);
				ticketv.setKey(Keycv);
				ticketv.setTS(TimeS);
				ticketv.setLT(lifetime);
				
				//保存ticketv
				setTicketv(ticketv);
				
				TC.setID(IDv);
				TC.setKey(Keycv);
				TC.setTS(TimeS);
				byte[] msg = pack.Pack_0x0a_Data(TC,KeyctgsInts,Keyv);
				send(msg);
		}else {
			byte[] msg = pack.Pack_0x0e_Cont();
			send(msg);
		}
		System.out.println("发给TGS！");
	}
	
	public void zhuangtaiji0a(TGS_C TC,String ip){
		int IDc =IDC;
		int IDv =TC.getID();
		String TS = TC.getTS();
		BigInteger Keycv = TC.getKey();
		Ticket ticketv  = TC.getTicket();
		Authenticator auth = new Authenticator();	
		
		String str = null;
		Pack pack = new Pack();
		Text text = new Text();
		Keys kkey = new Keys();
		DES des =new DES();
		C_V CV = new C_V();
		int[] Keyctgs = getKeyctgs();
		TimeStamp TS1 = new TimeStamp();
		String TimeS = TS1.getTimeString();
		int lifetime = 20;
		
		int ADc = pack.ByteArrayToInt2(pack.IPStringToByte(ip));
		auth.setAD(ADc);
		auth.setID(IDc);
		auth.setTS(TimeS);
		//CT.setID(this.IDv);
		//CT.setTicket(ticket);
		//CT.setAuthenticator(auth);
		byte[] msg = pack.Pack_0x0b_Data(CV,Keyctgs);
		send(msg);
		System.out.println("TGS->C");
	}
	
	public void zhuangtaiji0b(C_V CV,String ip){
		Ticket ticketv = CV.getTicket();
		Authenticator auth = CV.getAuth();
		int IDc = auth.getID();
		int IDv = ticketv.getID2();
		
		String str = null;
		Pack pack = new Pack();
		if(IDv == this.IDv) {
			sql a=new sql();
			try {
				str = a.HasAIDc (IDc);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(str !=null) {
				//String[] IDPa = str.split(" ");
				//String Hash_Pass=IDPa[1] ;
				Text text = new Text();
				Keys kkey = new Keys();
				BigInteger Keycv = ticketv.getKey();
				int[] KeycvInts = kkey.StringToInts(kkey.BigIntegerToString(Keycv));
				int ADc = pack.ByteArrayToInt2(pack.IPStringToByte(ip));
				V_C VC = new V_C();

				
				TimeStamp TS1 = new TimeStamp();
				
				String TimeS = TS1.getTimeString();
				int lifetime = 20;

				VC.setTS(TimeS);
				byte[] msg = pack.Pack_0x0c_Data(VC,KeycvInts);
				send(msg);
			}else {
				byte[] msg = pack.Pack_0x0f_Cont();
				send(msg);
			}
		}else {
			byte[] msg = pack.Pack_0x0f_Cont();
			send(msg);
		System.out.println("TGS->C");
		}
}
	
	public void zhuangtaiji0c(V_C VC){
		System.out.println("C->V");
	}
	
	public void zhuangtaiji0d(){
		System.out.println("AS->C失败");
	}
	
	public void zhuangtaiji0e(){
		System.out.println("TGS->C失败");
	}
	
	public void zhuangtaiji0f(){
		System.out.println("V->C失败");
	}
	
	public void zhuangtaiji17(){
		System.out.println("下载成功");
	}
	
	public void zhuangtaiji18(){
		System.out.println("下载失败");
	}
	
	public void zhuangtaiji19(Data_Update DU){
		System.out.println("下载失败");
	}
	
	public void zhuangtaiji1a(){
		System.out.println("更新密钥成功");
	}
	
	public void zhuangtaiji1b(){
		System.out.println("更新密钥失败");
	}
	
	public void zhuangtaiji1c(Data_Chat DC){
		System.out.println("更新密钥失败");
	}
	
	public void OnlineTransmit(Data_Online DOn,Socket socket){
		IPtoSocket IS = new IPtoSocket();
		IS.IDc = DOn.getIDc();
		IS.socket = socket;
		Pack pack = new Pack();
		try {
			socket.getOutputStream().write(pack.Pack_0x11_Cont());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Server.SocketList.addElement(IS);
		IPtoSocket NewIS = null;
		for(int i=0;i<Server.SocketList.size();i++){
			NewIS = Server.SocketList.elementAt(i);
			@SuppressWarnings("unused")
			SendThread ST = new SendThread(NewIS.socket,pack.Pack_0x10_Cont(),pack.Pack_0x10_Data(DOn));
		}
		System.out.println(DOn.getIDc()+"上线啦！");
	}
	
	public void zhuangtaiji11(){
		System.out.println("上线成功！");
	}
	
	public void zhuangtaiji12(){
		System.out.println("上线失败！");
	}
	
	public void ChatTransmit(Socket socket,byte[] bytes){
		Pack pack = new Pack();
		try {
			socket.getOutputStream().write(pack.Pack_0x14_Cont());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IPtoSocket NewIS = null;
		for(int i=0;i<Server.SocketList.size();i++){
			NewIS = Server.SocketList.elementAt(i);
			@SuppressWarnings("unused")
			SendThread ST = new SendThread(NewIS.socket,pack.Pack_0x13_Cont(),bytes);
		}
		//EK_message EKm = DC.getEKMSG();
		//System.out.println(DC.getIDc());
		//System.out.println(EKm.getMSG().toString());
		//System.out.println(EKm.getH_MSG().toString());
		//System.out.println(EKm.getSIGN().toString());
	}

	
	public void zhuangtaiji14(){
		System.out.println("发送聊天信息成功！");
	}
	
	public void zhuangtaiji15(){
		System.out.println("发送聊天内容失败！");
	}
	
	public void OfflineTransmit(Data_Offline DOf,Socket socket){
		IPtoSocket IS = new IPtoSocket();
		IS.IDc = DOf.getIDc();
		IS.socket = socket;
		Pack pack = new Pack();
		try {
			socket.getOutputStream().write(pack.Pack_0x17_Cont());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IPtoSocket NewIS = null;
		for(int i=0;i<Server.SocketList.size();i++){
			NewIS = Server.SocketList.elementAt(i);
			if(NewIS.IDc == IS.IDc){
				Server.SocketList.remove(i);
				continue;
			}
			@SuppressWarnings("unused")
			SendThread ST = new SendThread(NewIS.socket,pack.Pack_0x16_Cont(),pack.Pack_0x16_Data(DOf));
		}
		System.out.println(DOf.getIDc()+"上线啦！");
	}

	public byte[] readFixedLengthArray(BufferedInputStream serverSocketBis,int length)
	        throws SocketTimeoutException, IOException{  //读对应长度的byte数组
	    byte [] result = new byte[length];  
	    int readLen = 0;  
	    int getLen = 0;  
	    while(getLen<length){  
	        readLen = serverSocketBis.read(result, getLen, length-getLen);  
	        serverSocketBis.read(result);  
	        if(readLen ==-1){  
	            return null;  
	        }  
	        getLen = getLen + readLen;  
	    }  
	       return result;  
	}
	
	
	public byte[] getIP() {
		
		return null;
	}
	
	public void send(byte[] msg) {
		
	}
}
