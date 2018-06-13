package Client;

import Package.*;
import DES.*;
import RSA.*;

import java.awt.TextArea;
import java.io.*;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import DES.Text;
import Kerberos.TimeStamp;

public class Client {

		public static int IDas = 1000000000;
		public static int IDtgs = 1000000001;
		public static int IDv = 1000000002;
		public static int IDis = 1000000003;
		//���ְ�����
		private int IDc;
		private int ADc; //�ڲ�����ipת��
		//int IDc,ADc,IDtgs,IDs;//���ʼ��
		private String TS1,TS2,TS3,TS4,TS5,TS6;
		String password,npassword;//���ʼ��
		int Lifetime1;
		Ticket tickettgs,ticketv;
		Authenticator at,as;//���ʼ��
		int[] Keyctgs,Keycv;
		ServerSocket serverSocket;
		clientChat c;
		public boolean flag = false;
		//Socket����
		int port;
		Socket NowSocket;
		public Socket C_ASSocket,C_TGSSocket,C_VSocket,C_RegistSocket,C_Ssocket,ReC_Vsocket;
		OutputStream outstream,outstream2,outstream3;
		InputStream instream,instream2,instream3;
		Listen listen;
		public STATEC state;

		public void setIDc(int idc) {
			this.IDc = idc;
		}
		
		public int getIDc() {
			return IDc;
		}
		
		public void setPassword(String password) {
			this.password = password;
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
		
		public void setnPassword(String npassword) {
			this.npassword = npassword;
		}
		
		public void setIDtgs(int idtgs) {
			Client.IDtgs = idtgs;
		}

		public void setAuthat() {
			this.at.setID(IDc);
			this.at.setAD(ADc);
			this.at.setTS(TS3);	
		}
		
		public void setAuthas() {
			this.as.setID(IDc);
			this.as.setAD(ADc);
			this.as.setTS(TS5);
		}
		
		
		public int getLT1() {
			return Lifetime1;
		}
		
		
		public Ticket gettickettgs() {
			return tickettgs;
		}
		
		public Ticket gettickets() {
			return ticketv;
		}
		
		public int[] getKctgs() {
			return Keyctgs;
		}
		
		public int[] getKeycv() {
			return Keycv;
		}
		
		public void setKeyctgs(int[] key) {
			this.Keyctgs = key;
		}
		
		public void setKeycv(int[] key) {
			this.Keycv = key;
		}
		
		Client(STATEC statec){
			this.state = statec;
			Socket ASsocket = null;
			try {
				ASsocket = new Socket("192.168.1.103",10000);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Socket TGSsocket = null;
			try {
				TGSsocket = new Socket("192.168.1.103",20000);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Socket Vsocket = null;
			try {
				Vsocket = new Socket("192.168.1.103",30000);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Socket C_Msocket = null;
			try {
				C_Msocket = new Socket("192.168.1.103",40000);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Socket ReC_Vsocket = null;
			try {
				ReC_Vsocket = new Socket("192.168.1.103",30001);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			statec.C_RSocket = C_Msocket;
			statec.C_ASsocket = ASsocket;
			statec.C_TGSSocket = TGSsocket;
			statec.C_VSocket = Vsocket;
			this.ReC_Vsocket = ReC_Vsocket;
			C_ASSocket = ASsocket;
			C_TGSSocket = TGSsocket;
			C_VSocket = Vsocket;
			C_RegistSocket = C_Msocket;
		}

		@SuppressWarnings("resource")
		public void login(String IDc,String Psw,TextArea t) throws IOException {
			LoginMessage uobj=new LoginMessage();
			uobj.loginName=IDc;
			LocalIP LIP = new LocalIP();
			String ip = LIP.getLocalHostLANAddress().getHostAddress().toString();
			System.out.println(ip);
			state.setIDC(Integer.valueOf(IDc));
			this.IDc = Integer.valueOf(IDc);
			int idc = Integer.valueOf(IDc);
			Keys key  = new Keys();
			Hash hash = new Hash();
			BigInteger hash_Password = hash.getMD5(Psw);
			String str=key.BigIntegerToString(hash_Password);
			key.SaveKeyToFile("Keyc.txt", str);
			setIDc(idc);
			TimeStamp TS1 = new TimeStamp();
			String TimeS = TS1.getTimeString();
			setTS1(TimeS);//////////TS1
			System.out.println("��ʼ��AS��������\n");
			t.append("��ʼ��AS��������\n");
			//��������
			Pack pack = new Pack();
			C_ASSocket.getOutputStream().write(pack.Pack_0x07_Cont());
			
			C_AS c_as = new C_AS();
			c_as.setIDc(idc);
			c_as.setIDtgs(IDtgs);
			c_as.setTS(TimeS);
			InputStream inputstream = C_ASSocket.getInputStream();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputstream);
			byte[] bytes = new byte[2];
			bufferedInputStream.read(bytes,0,2);
			if(bytes[0]==(byte)0xff&&bytes[1]==(byte)0xff){
				C_ASSocket.getOutputStream().write(pack.Pack_0x07_Data(c_as));
				t.append("��ʼ��֤>>>>>>\n");
			}
			
			else {
				System.out.println("�ͻ��˲����ھ���״̬�����ܽ�����֤");
				t.append("�ͻ��˲����ھ���״̬�����ܽ�����֤");
				return;
			}
			//���ջظ�
			byte[] bytes1 = new byte[2];
			bufferedInputStream.read(bytes1, 0, 2);
			t.append("�յ�AS�Ļظ�:\n");
			state.Unpack_Head(bytes1, bufferedInputStream, C_ASSocket,ip);
			//AS_C������
			setKeyctgs(state.getKeyctgs());
			setTS2(state.getTS2());
			bufferedInputStream.close();
			inputstream.close();
			C_ASSocket.close();
			if(state.HasError) {
				t.append("��֤ʧ��");
				return;
			}	
			t.append("AS��֤�ɹ���������TGSticket");
			
			///////////////////////////////////////////////////////
			t.append("��ʼ��TGS��������\n");
			setTS3(state.getTS3());
			InputStream inputstreamTGS = C_TGSSocket.getInputStream();
			BufferedInputStream bufferedInputStreamTGS = new BufferedInputStream(inputstreamTGS);
			byte[] bytes2 = new byte[2];
			bufferedInputStreamTGS.read(bytes2, 0, 2);
			t.append("�յ�TGS�ظ���\n");
			state.Unpack_Head(bytes2, bufferedInputStreamTGS, C_TGSSocket,  ip);
			setTS4(state.getTS4());
			bufferedInputStreamTGS.close();
			inputstreamTGS.close();
			C_TGSSocket.close();
			if(state.HasError) {
				t.append("TGS��֤ʧ��");
				return;
			}
			t.append("TGS��֤�ɹ�:\n");
			///////////////////////////////////////////////
			System.out.println("�����Ѿ����ˣ�");
			setKeycv(state.getKeycv());
			t.append("���Vticket\n");
			t.append("��ʼ��V��������\n");
			setTS5(state.getTS5());
			InputStream inputstreamV = C_VSocket.getInputStream();
			BufferedInputStream bufferedInputStreamV = new BufferedInputStream(inputstreamV);
			byte[] bytes3 = new byte[2];
			bufferedInputStreamV.read(bytes3, 0, 2);
			state.Unpack_Head(bytes3, bufferedInputStreamV, C_VSocket, ip);
			setTS6(state.getTS6());
//			C_VSocket.close();
			if(state.HasError) {
				return;
			}else{
				System.out.println("Kerberos ��֤��ɣ�ʵ�ֵ�¼");
				t.append("Kerberos ��֤��ɣ�ʵ�ֵ�¼");

				
				//this.NowSocket = new Socket("192.168.1.103",30000);
				//state.C_VSocket = NowSocket;
				//System.out.println("New connection accepted "+
					//      NowSocket.getInetAddress()+":"+NowSocket.getPort());
				//this.IDc = Integer.valueOf(IDc);
			}
			///////////////////////////////////////////////////////////////
		}
			
		public void Regist(String IDc,String Psw) throws IOException {
			System.out.println("��ʼ��ע�������������֤����...\n");
			Pack pack = new Pack();
			C_RegistSocket.getOutputStream().write(pack.Pack_0x00_Cont());
			int idc = Integer.valueOf(IDc);
			Hash hash = new Hash();
			BigInteger hash_Password = hash.getMD5(Psw);
			//hash_psw�����ļ���
			Keys key  = new Keys();
			String str=key.BigIntegerToString(hash_Password);
			key.SaveKeyToFile("Keyc.txt", str);
			//Rsa_hash_psw
			Encryption EN = new Encryption();
			BigInteger rsa_hash_psw = EN.encryption(hash_Password, String.valueOf(IDis));
			Data_Regist DR = new Data_Regist();
			DR.setIDc(idc);
			DR.setRSA_HASH_PASSWORD(rsa_hash_psw);
			//��������
			InputStream inputstream =C_RegistSocket.getInputStream();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputstream);
			byte[] bytes = new byte[2];
			bufferedInputStream.read(bytes,0,2);
			if(bytes[0]==(byte)0xff&&bytes[1]==(byte)0xff){
				C_RegistSocket.getOutputStream().write(pack.Pack_0x00_Data(DR));
			}
			else {
				System.out.println("�ͻ��˲����ھ���״̬�����ܽ���ע��");
			}
			
			//���ջظ�
			byte[] bytes1 = new byte[2];
			bufferedInputStream.read(bytes1, 0, 2);
			state.Unpack_Head(bytes1, bufferedInputStream, C_RegistSocket, InetAddress.getLocalHost().toString());
			bufferedInputStream.close();
			inputstream.close();
			C_RegistSocket.close();
			if(state.HasError) {
				System.out.println("�˺��Ѵ��ڣ�");
				return;
			}
			ManagementOfCredential MOF = new ManagementOfCredential();
			MOF.Process1(IDc);
		}	
		
		public void Modify(String IDc,String Psw,String Npsw) throws IOException {
			System.out.println("��ʼ��ע�������������֤����...\n");
			int idc = Integer.valueOf(IDc);
			Hash hash = new Hash();
			BigInteger hash_psw = hash.getMD5(Psw);
			BigInteger hash_Npsw = hash.getMD5(Npsw);
			//hash_Npsw�����ļ���
			//���޸�
			Keys key  = new Keys();
			String str=key.BigIntegerToString(hash_Npsw);
			key.SaveKeyToFile("Keyc.txt", str);
			//Rsa_hash_psw
			Encryption EN = new Encryption();
			BigInteger rsa_hash_psw = EN.encryption(hash_psw, String.valueOf(IDis));
			BigInteger rsa_hash_Npsw = EN.encryption(hash_Npsw, String.valueOf(IDis));
			Data_Modify DM = new Data_Modify();
			DM.setIDc(idc);
			DM.setRSA_HASH_PASSWORD(rsa_hash_psw);
			DM.setRSA_HASH_NPASSWORD(rsa_hash_Npsw);
			System.out.println(DM.getIDc());
			System.out.println(DM.getRSA_HASH_PASSWORD().toString());
			System.out.println(DM.getRSA_HASH_NPASSWORD().toString());
			//��������
			Pack pack = new Pack();
			InputStream inputstream =C_RegistSocket.getInputStream();
			C_RegistSocket.getOutputStream().write(pack.Pack_0x01_Cont());
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputstream);
			byte[] bytes = new byte[2];
			bufferedInputStream.read(bytes,0,2);
			if(bytes[0]==(byte)0xff&&bytes[1]==(byte)0xff){
				C_RegistSocket.getOutputStream().write(pack.Pack_0x01_Data(DM));
				System.out.println("����������");
			}
			else {
				System.out.println("�ͻ��˲����ھ���״̬�����ܽ���ע��");
			}
			//��������
			byte[] bytes1 = new byte[2];
			bufferedInputStream.read(bytes1, 0, 2);
			state.Unpack_Head(bytes1, bufferedInputStream, C_RegistSocket, InetAddress.getLocalHost().toString());
			bufferedInputStream.close();
			inputstream.close();
			C_RegistSocket.close();
			if(state.HasError) {
				System.exit(1);
				return;
			}
		}
		
		public void Offline() throws  IOException {
			Pack pack = new Pack();
			Data_Offline DOf = new Data_Offline();
			DOf.setIDc(IDc);
			while(true){
				if(this.flag == true){
					continue;
				}else{
					this.flag = true;
					break;
				}
			}
			state.Send = pack.Pack_0x16_Data(DOf);
			try {
				this.C_VSocket.getOutputStream().write(pack.Pack_0x16_Cont());
				System.out.println(pack.Pack_0x16_Cont()[0]+" "+pack.Pack_0x16_Cont()[1]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while(true){
				if(this.flag == false){
					break;
				}else{
					continue;
				}
			}
		}
		
		public void Online() throws  IOException {
			Pack pack = new Pack();
			Data_Online DOn = new Data_Online();
			DOn.setIDc(IDc);
			state.Send = pack.Pack_0x10_Data(DOn);
			try {
				this.C_VSocket.getOutputStream().write(pack.Pack_0x10_Cont());
				System.out.println(pack.Pack_0x10_Cont()[0]+" "+pack.Pack_0x10_Cont()[1]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void Chat(String Message) throws IOException {
			Pack pack = new Pack();
			String idc = String.valueOf(IDc);
			//messageǩ��
			Text text = new Text();
			byte[] msgByte = null;
			try {
				msgByte = Message.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BigInteger msg = new BigInteger(msgByte);
			Decryption De = new Decryption();
			BigInteger rsa_msg = De.sign(msg, idc);
			//message Hash
			Hash hash  =new Hash();
			BigInteger hash_msg = hash.getMD5(Message);
			EK_message EK_m = new EK_message();
			EK_m.setH_MSG(hash_msg);
			EK_m.setSIGN(rsa_msg);
			Data_Chat DC = new Data_Chat();
			DC.setIDc(IDc);
			DC.setEK_message(EK_m);	
			Keys key1 = new Keys();
			int[] Key1 = key1.ReadKeysFromFile("Key1.txt");
			//������Ϣ	
			while(true){
				if(this.flag == true){
					continue;
				}else{
					this.flag = true;
					break;
				}
			}
			state.Send = pack.Pack_0x13_Data(DC, Key1);
			try {
				this.C_VSocket.getOutputStream().write(pack.Pack_0x13_Cont());
				System.out.println("����ͷ��"+pack.Pack_0x13_Cont()[0]+" "+pack.Pack_0x13_Cont()[1]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TimeStamp TS1 = new TimeStamp();
			String TimeS = TS1.getTimeString();
			String TS2 = TS1.TimeStringToTimeStamp(TimeS);
			int ts = Integer.valueOf(TS2);
			while(true){
				TimeStamp TS3 = new TimeStamp();
				String TimeS2 = TS3.getTimeString();
				String TS4 = TS3.TimeStringToTimeStamp(TimeS2);
				int ts2 = Integer.valueOf(TS4);
				if(ts2-ts == 1)
				{
					this.flag = false;
				}
				if(this.flag == false){
					break;
				}else{
					continue;
				}
			}
		}
			
		public static byte[] readFixedLengthArray(BufferedInputStream serverSocketBis,int length)
			        throws SocketTimeoutException, IOException{  //����Ӧ���ȵ�byte����
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
		 	 
		public void send(byte[] msg) {
			try {
				C_Ssocket.getOutputStream().write(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		
}

