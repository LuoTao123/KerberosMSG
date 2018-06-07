package Client;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import DataBase.sql;
import DES.*;
import Kerberos.TimeStamp;
import Package.AS_C;
import Package.Authenticator;
import Package.C_AS;
import Package.C_TGS;
import Package.C_V;
import Package.Data_Chat;
import Package.Data_Modify;
import Package.Data_Offline;
import Package.Data_Online;
import Package.Data_Regist;
import Package.Data_Update;
import Package.EK_message;
import Package.IPtoSocket;
import Package.Pack;
import Package.TGS_C;
import Package.Ticket;
import Package.Unpack;
import Package.V_C;
import RSA.Decryption;
import RSA.Hash;
import Server.Server;
import Server.SendThread;

public class STATEC extends Thread{
	public static int IDas = 1000000000;
	public static int IDtgs = 1000000001;
	public static int IDv = 1000000002;
	public static int IDis = 1000000003;
	private int IDC;
	private int ADC;
	private String TS1,TS2,TS3,TS4,TS5,TS6;
	private int lifetime;
	private int[] Keyctgs;
	private int[] Keycv;
	private int[] Key1;
	private Ticket tickettgs,ticketv;
	private Socket C_Ssocket;
	public Socket C_ASsocket;
	public Socket C_TGSSocket;
	public Socket C_VSocket;
	public Socket C_RSocket;
	public  boolean HasError = false;
	
 	public void setIDC(int idc) {
		this.IDC = idc;
	}
	
	public void setADC(int adc) {
		this.ADC = adc;
	}
	
	public int getIDC() {
		return IDC;
	}
	
	public int getADC() {
		return ADC;
	}
	
	public void setLifetime(int lt) {
		this.lifetime= lt;
	}
	
	public int getLifetime() {
		return lifetime;
	}
	public void setTS1(String ts) {
		this.TS1 = ts;
	}
	
	public void setTS2(String ts) {
		this.TS2 = ts;
	}
	
	public void setTS3(String ts) {
		this.TS3 = ts;
	}
	
	public void setTS4(String ts) {
		this.TS4 = ts;
	}
	
	public void setTS5(String ts) {
		this.TS5 = ts;
	}
	
	public String getTS1() {
		return TS1;
	}
	
	public String getTS2() {
		return TS2;
	}
	
	public String getTS3() {
		return TS3;
	}
	
	public String getTS4() {
		return TS4;
	}
	
	public String getTS5() {
		return TS5;
	}
	
	public void setTS6(String ts) {
		this.TS6 = ts;
	}
	
	public String getTS6() {
		return TS6;
	}
	
	public void setKeyctgs(int[] key) {
		this.Keyctgs = key;
	}
	
	public void setKeycv(int[] key) {
		this.Keycv  = key;
	}
	
	public void setKey1(int[] key) {
		this.Key1 = key;
	}
	public int[] getKeyctgs() {
		return Keyctgs;
	}
	
	public int[] getKeycv() {
		return Keycv;
	}
	
	public int[] getKey1() {
		return Key1;
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
		this.C_Ssocket = socket;
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
				default : System.out.println("�Ƿ����ݰ�");
			}
		}else if(NewByte[0]==(byte)0x01){
			Pack pack = new Pack();
			socket.getOutputStream().write(pack.Server_Return());
			byte[] bytess  = null;
			switch(NewByte[1]){
				case (byte)0x10:	bytess = new byte[4];
									bufferedInputStream.read(bytess, 0, 4);
									Online(unpack.Unpack_0x10(bytess),socket);
									break;
									//OnlineTransmit(unpack.Unpack_0x10(readFixedLengthArray(bufferedInputStream,4)),socket);break;
				case (byte)0x13:	bytess = new byte[149];
									bufferedInputStream.read(bytess, 0, 149);
									Chat(unpack.Unpack_0x1c(bytess),socket);
									break;
									//ChatTransmit(socket,readFixedLengthArray(bufferedInputStream,149));break;
				case (byte)0x08:	bytess = new byte[102];
									bufferedInputStream.read(bytess, 0, 102);
									zhuangtaiji8(unpack.Unpack_0x08(bytess),ip);
									break;
									//zhuangtaiji8(unpack.Unpack_0x08(readFixedLengthArray(bufferedInputStream,80)),ip);break;
				case (byte)0x09:	bytess = new byte[86];
									bufferedInputStream.read(bytess, 0, 86);
									zhuangtaiji9(unpack.Unpack_0x09(bytess),ip);
									break;
									//zhuangtaiji9(unpack.Unpack_0x09(readFixedLengthArray(bufferedInputStream,75)),ip);break;
				case (byte)0x0a:	bytess = new byte[98];
									bufferedInputStream.read(bytess, 0, 98);
									zhuangtaiji0a(unpack.Unpack_0x0a(bytess),ip);
									break;
									//zhuangtaiji0a(unpack.Unpack_0x0a(readFixedLengthArray(bufferedInputStream,76),Keyctgs),ip);break;
				case (byte)0x0b:	bytess = new byte[82];
									bufferedInputStream.read(bytess, 0, 82);
									zhuangtaiji0b(unpack.Unpack_0x0b(bytess),ip);
									break;
									//zhuangtaiji0b(unpack.Unpack_0x0b(readFixedLengthArray(bufferedInputStream,71),Keycv),ip);break;
				case (byte)0x0c:	bytess = new byte[19];
									bufferedInputStream.read(bytess, 0, 19);
									zhuangtaiji0c(unpack.Unpack_0x0c(bytess));
									break;
									//zhuangtaiji0c(unpack.Unpack_0x0c(readFixedLengthArray(bufferedInputStream,19),Keycv));break;
				case (byte)0x16:	bytess = new byte[4];
									bufferedInputStream.read(bytess, 0, 4);
									Offline(unpack.Unpack_0x16(bytess),socket);
									break;
									//OfflineTransmit(unpack.Unpack_0x16(readFixedLengthArray(bufferedInputStream,4)),socket);break;
				case (byte)0x19:	bytess = new byte[9];
									bufferedInputStream.read(bytess, 0, 9);
									Keys Key1 = new Keys();
									zhuangtaiji19(unpack.Unpack_0x19(bytess,Key1.ReadKeysFromFile("Key1.txt")));
									break;
									//zhuangtaiji19(unpack.Unpack_0x19(readFixedLengthArray(bufferedInputStream,9)));break;

				default : System.out.println("�Ƿ����ݰ�");
			}
		}else{
			System.out.println(NewByte[0]);
			System.out.println("�ð��Ƿ���");
			///////////////////////////////////////////////////////////////log
		}
	}
	
	public void zhuangtaiji2(){
		System.out.println("ע��ɹ�");
	}
	
	public void zhuangtaiji3(){
		this.HasError = true;
		//����
		System.out.println("ע��ʧ�ܣ��˺Ŵ��ڣ�");
	}

	public void zhuangtaiji4(){
		System.out.println("�޸ĳɹ�");
	}
	
	public void zhuangtaiji5(){
		this.HasError = true;
		//����
		System.out.println("�޸�ʧ��,�˺Ų�����");
	}
	
	public void zhuangtaiji6(){
		this.HasError = true;
		//����
		System.out.println("�޸�ʧ��,ԭ�������");
	}
	
	public void zhuangtaiji7(C_AS CA,String ip){
		int IDc = CA.getIDc();
		setIDC(IDc);
		int IDt = CA.getIDtgs();
		String TS1 =CA.getTS();
		setTS1(TS1);
		BigInteger PSW = null;
		Pack pack = new Pack();
		if(IDt == IDtgs) {
			sql a=new sql();
			try {
				PSW = a.HasAIDc (IDc);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(PSW !=null) {
				Keys kkey = new Keys();
				int[] Keytgs = kkey.ReadKeysFromFile("Keytgs.txt");
				System.out.println(kkey.BigIntegerToString(PSW).length());
				int[] KeycInts = kkey.StringToInts(kkey.BigIntegerToString(PSW));
				DES des =new DES();
				AS_C AC = new AS_C();
				BigInteger Keyctgs = des.CreateDESKey();
				TimeStamp TS2 = new TimeStamp();
				String TimeS = TS2.getTimeString();
				setTS2(TimeS);
				int lifetime = 20;
				Ticket ticket = new Ticket();
				int ADc = 0;
				try {
					ADc = pack.ipStringToInts(ip);
				} catch (UnknownHostException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				setADC(ADc);
				System.out.println("ADc"+ADc);
				ticket.setID1(IDc);
				ticket.setAD(ADc);
				ticket.setID2(IDt);
				ticket.setKey(Keyctgs);
				ticket.setTS(TimeS);
				ticket.setLT(lifetime);
				
				AC.setID(IDc);
				AC.setKey(Keyctgs);
				AC.setLT(lifetime);
				AC.setTicket(ticket);
				AC.setTS(TimeS);
				byte[] Headmsg = pack.Pack_0x08_Cont();
				InputStream inputstream = null;
				try {
					inputstream = C_Ssocket.getInputStream();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				send(Headmsg);
				BufferedInputStream bufferedInputStream = new BufferedInputStream(inputstream);
				byte[] bytes = new byte[2];
				try {
					bufferedInputStream.read(bytes,0,2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(bytes[0]==(byte)0x00&&bytes[1]==(byte)0x00){
					byte[] msg = pack.Pack_0x08_Data(AC,KeycInts,Keytgs);
					send(msg);
				}
				System.out.println("Here");
				System.out.println(AC.getTicket().getID1());
				System.out.println(AC.getTicket().getAD());
				System.out.println(AC.getTicket().getTS());
				System.out.println(AC.getTicket().getLT());
			}else {
				byte[] msg = pack.Pack_0x0d_Cont();
				send(msg);
			}
		}else {
			byte[] msg = pack.Pack_0x0d_Cont();
			send(msg);
		}
		System.out.println("�ͻ���"+CA.getIDc());
		System.out.println("�������"+CA.getIDtgs());
		System.out.println("ʱ���Ϊ"+CA.getTS());
	}
	
	//�ͻ�������հ����
	public void zhuangtaiji8(AS_C AC,String ip){
		int IDc =IDC;
		int IDt = AC.getID();
		String TS2 = AC.getTS();
		setTS2(TS2);
		Pack pack = new Pack();
		C_TGS CT = new C_TGS();
		BigInteger Keyctgs = AC.getKey();
		Keys Key1 = new Keys();
		String strbuf = Key1.BigIntegerToString(Keyctgs);
		int[] KeyctgsInts = Key1.StringToInts(strbuf);
		//������Կ���ļ�
		Key1.SaveKeyToFile("Keyctgs.txt", Keyctgs);
		TimeStamp TS1 = new TimeStamp();
		String TimeS = TS1.getTimeString();
		setTS3(TimeS);
		int lifetime = 20;
		setLifetime(lifetime);
		byte[] ticket = new byte[55];
		Authenticator auth = new Authenticator();
		ticket = AC.getChangedTicket();
		int ADc = 0;
		try {
			ADc = pack.ipStringToInts(ip);
		} catch (UnknownHostException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		auth.setAD(ADc);
		auth.setID(IDc);
		auth.setTS(TimeS);
		System.out.println("ip"+ip);
		CT.setID(this.IDv);
		CT.setChangedTicket(ticket);
		CT.setAuthenticator(auth);
		this.C_Ssocket = this.C_TGSSocket;
		byte[] Headmsg = pack.Pack_0x09_Cont();
		send(Headmsg);
		BufferedInputStream bufferedInputStream = null;
		try {
			bufferedInputStream = new BufferedInputStream(C_Ssocket.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		byte[] bytes = new byte[2];
		try {
			bufferedInputStream.read(bytes,0,2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(bytes[0]==(byte)0x00&&bytes[1]==(byte)0x00){
			byte[] msg = pack.Pack_0x09_Data(CT,KeyctgsInts);
			send(msg);
		}
		else {
			System.out.println("�����������ھ���״̬�����ܽ�����֤");
			return;
		}
		System.out.println(IDv);
		System.out.println(this.IDv);
		System.out.println(CT.getAuthenticator().getAD());
		System.out.println(CT.getAuthenticator().getID());
		System.out.println(this.IDtgs);
		System.out.println(CT.getAuthenticator().getTS());
	}
	
	public void zhuangtaiji9(C_TGS CT,String ip){
		int IDc = CT.getTicket().getID1();
		int IDv = CT.getID();
		Ticket ticket = CT.getTicket();
		Authenticator auth = CT.getAuthenticator();
		String TS3 = auth.getTS();
		setTS3(TS3);
		Pack pack = new Pack();
		int LT = CT.getTicket().getLT();
		TimeStamp TSp = new TimeStamp();
		if(IDv== this.IDv&&CT.getTicket().getID1()==CT.getAuthenticator().getID()
				&&CT.getTicket().getAD()==CT.getAuthenticator().getAD()
				&&CT.getTicket().getID2()==this.IDtgs
				&&TSp.IsOverLifeTime(CT.getTicket().getTS(), LT,CT.getAuthenticator().getTS())) {
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
				TimeStamp TS1 = new TimeStamp();
				String TimeS = TS1.getTimeString();
				setTS4(TimeS);
				int lifetime = 20;
				Ticket ticketv = new Ticket();
				int ADc = 0;
				try {
					ADc = pack.ipStringToInts(ip);
				} catch (UnknownHostException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				ticketv.setID1(IDc);
				ticketv.setAD(ADc);
				ticketv.setID2(this.IDtgs);
				ticketv.setKey(Keycv);
				ticketv.setTS(TimeS);
				ticketv.setLT(lifetime);
				
				//����ticketv
				setTicketv(ticketv);
				TC.setID(IDv);
				TC.setKey(Keycv);
				TC.setTS(TimeS);
				byte[] Headmsg = pack.Pack_0x0a_Cont();
				BufferedInputStream bufferedInputStream = null;
				try {
					bufferedInputStream = new BufferedInputStream(this.C_Ssocket.getInputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				send(Headmsg);
				byte[] bytes = new byte[2];
				try {
					bufferedInputStream.read(bytes,0,2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(bytes[0]==(byte)0x00&&bytes[1]==(byte)0x00){
					byte[] msg = pack.Pack_0x0a_Data(TC,KeyctgsInts,Keyv);
					send(msg);
				}
		}else {
			byte[] msg = pack.Pack_0x0e_Cont();
			send(msg);
		}
		System.out.println("����TGS��");
	}
	
	public void zhuangtaiji0a(TGS_C TC,String ip){
		int IDc =IDC;
		int IDv =TC.getID();
		String TS = TC.getTS();
		setTS4(TS);
		BigInteger Keycv = TC.getKey();
		Keys Key3 = new Keys();
		//10ת2
		String strbuf = Key3.BigIntegerToString(Keycv);
		int[] KeycvInts = Key3.StringToInts(strbuf);
		Key3.SaveKeyToFile("Keycv.txt", strbuf);
		byte[] ticketv = TC.getChangedTicket();
		Authenticator auth = new Authenticator();	
		
		Pack pack = new Pack();

		C_V CV = new C_V();
		TimeStamp TS1 = new TimeStamp();
		String TimeS = TS1.getTimeString();
		setTS5(TimeS);
		int lifetime = 20;
		
		int ADc = 0;
		try {
			ADc = pack.ipStringToInts(ip);
		} catch (UnknownHostException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		auth.setAD(ADc);
		auth.setID(IDc);
		auth.setTS(TimeS);
		CV.setAuthenticator(auth);
		CV.setChangedTicket(ticketv);
		this.C_Ssocket = this.C_VSocket;
		byte[] Headmsg = pack.Pack_0x0b_Cont();
		send(Headmsg);
		BufferedInputStream bufferedInputStream = null;
		try {
			bufferedInputStream = new BufferedInputStream(C_Ssocket.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		byte[] bytes = new byte[2];
		try {
			bufferedInputStream.read(bytes,0,2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(bytes[0]==(byte)0x00&&bytes[1]==(byte)0x00){
			byte[] msg = pack.Pack_0x0b_Data(CV,KeycvInts);
			send(msg);
		}
		else {
			System.out.println("�ͻ��˲����ھ���״̬�����ܽ�����֤");
			return;
		}
		System.out.println("TGS->C");
	}
	
	public void zhuangtaiji0b(C_V CV,String ip){
		Ticket ticketv = CV.getTicket();
		Authenticator auth = CV.getAuth();
		int IDc = auth.getID();
		int IDv = ticketv.getID2();
		Pack pack = new Pack();
		TimeStamp TSp = new TimeStamp();
		if(IDv == this.IDv&&ticketv.getID1()==auth.getID()&&ticketv.getAD()==auth.getAD()
				&&TSp.IsOverLifeTime(ticketv.getTS(), ticketv.getLT(), auth.getTS())) {
				Keys kkey = new Keys();
				BigInteger Keycv = ticketv.getKey();
				//10ת2
				int[] KeycvInts = kkey.StringToInts(kkey.BigIntegerToString(Keycv));
				V_C VC = new V_C();
				String TS5 = auth.getTS();
				String TS2 = TSp.TimeStringToTimeStamp(TS5);
				int ts = Integer.valueOf(TS2);
				String Times = String.valueOf(ts+1);
				String TS3 = TSp.TimeStampToTimeString(Times);
				setTS6(TS3);
				VC.setTS(TS3);
				byte[] Headmsg = pack.Pack_0x0c_Cont();
				BufferedInputStream bufferedInputStream = null;
				try {
					bufferedInputStream = new BufferedInputStream(C_Ssocket.getInputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				send(Headmsg);
				byte[] bytes = new byte[2];
				try {
					bufferedInputStream.read(bytes,0,2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(bytes[0]==(byte)0x00&&bytes[1]==(byte)0x00){
					byte[] msg = pack.Pack_0x0c_Data(VC,KeycvInts);
					send(msg);
				}
			}else {
				byte[] msg = pack.Pack_0x0f_Cont();
				send(msg);
			}
		System.out.println("TGS->C");
	}
	
	public void zhuangtaiji0c(V_C VC){
		TimeStamp TSp = new TimeStamp();
		String TS5 = getTS5();
		String TS2 = TSp.TimeStringToTimeStamp(TS5);
		int ts = Integer.valueOf(TS2);
		String Times = String.valueOf(ts+1);
		String TS3 = TSp.TimeStampToTimeString(Times);
		String TM1 = VC.getTS();
		if(TS3.equals(TM1)) {
			System.out.println("C->V");
		}else{
			this.HasError = true;
		}
	}

	public void zhuangtaiji0d(){
		this.HasError = true;
		//����
		System.out.println("AS->Cʧ��");
	}
	
	public void zhuangtaiji0e(){
		this.HasError = true;
		//����
		System.out.println("TGS->Cʧ��");
	}
	
	public void zhuangtaiji0f(){
		this.HasError = true;
		//����
		System.out.println("V->Cʧ��");
	}
	
	
	//����
	public void zhuangtaiji17(){
		System.out.println("���߳ɹ�");
	}
	
	public void zhuangtaiji18(){
		this.HasError = true;
		//����
		System.out.println("����ʧ��");
	}
	
	////////////////////////////////////////////////////////////
	public void zhuangtaiji19(Data_Update DU){
		BigInteger Key1 = DU.getKey();
		//Key1д���ļ�
		Keys key  =new Keys();
		key.SaveKeyToFile("Key1.txt", Key1);
		System.out.println("������Կ");
	}
	
	public void zhuangtaiji1a(){
		System.out.println("������Կ�ɹ�");
	}
	
	public void zhuangtaiji1b(){
		this.HasError = true;
		//����
		System.out.println("������Կʧ��");
	}
	
	
	//���߷�
	public void Online(Data_Online DOn,Socket socket){
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
		System.out.println(IS.IDc+"����");
	}
	
	public void zhuangtaiji11(){
		System.out.println("���߳ɹ���");
	}
	
	public void zhuangtaiji12(){
		System.out.println("����ʧ�ܣ�");
	}
	
	public void Chat(Data_Chat DC,Socket socket){
		EK_message EK_m = DC.getEKMSG();
		int IDc = DC.getIDc();
		String idc = String.valueOf(IDc);
		BigInteger H_msg = EK_m.getH_MSG();
		BigInteger Rsa_msg = EK_m.getSIGN();
		Decryption De =new Decryption();
		Text text  = new Text();
		Hash hash  = new Hash();
		BigInteger mes = De.decryption(Rsa_msg, idc);
		
		String message = text.BigIntegerToString(mes);
		System.out.println(message);
		BigInteger h_msg =hash.getMD5(message);
		
		Pack pack = new Pack();
		if(h_msg.compareTo(H_msg) !=0) {
			try {
				socket.getOutputStream().write(pack.Pack_0x15_Cont());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//////////////////////////////�޸�
		/////////////////////////////////////////////////////////////////////////////////////////
	}

	public void zhuangtaiji14(){
		System.out.println("����������Ϣ�ɹ���");
	}
	
	public void zhuangtaiji15(){
		System.out.println("������������ʧ�ܣ�");
	}
	
	//������
	public void Offline(Data_Offline DOf,Socket socket){
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
		System.out.println(IS.IDc+"����");
	}

	public byte[] readFixedLengthArray(BufferedInputStream serverSocketBis,int length)
	        throws SocketTimeoutException, IOException{  //����Ӧ���ȵ�byte����
	    byte [] result = new byte[length];  
	    int readLen = 0;  
	    int getLen = 0;  
	    while(getLen<length){  
	    	System.out.println(getLen);
	        readLen = serverSocketBis.read(result, getLen, length-getLen);  
	        serverSocketBis.read(result);  
	        if(readLen ==-1){  
	            return null;  
	        }  
	        getLen = getLen + readLen;  
	    }  
	       return result;  
	}
	
	public void send(byte[] msg) {
		try {
			C_Ssocket.getOutputStream().write(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
