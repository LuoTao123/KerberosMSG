package Package;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.Socket;
import java.net.SocketTimeoutException;

import DES.Keys;
import DES.Text;

public class Unpack {
	
	public Data_Regist Unpack_0x00(byte[] NewByte){
		System.out.println("解包啦");
		Data_Regist DR = new Data_Regist();
		byte[] IDcByte = new byte[4];
		System.arraycopy(NewByte, 0, IDcByte, 0, IDcByte.length);
		byte[] PSWByte = new byte[129];
		System.arraycopy(NewByte, 4, PSWByte, 0, PSWByte.length);
		int IDc = ByteArrayToInt2(IDcByte);
		BigInteger PSW = new BigInteger(PSWByte);
		DR.setIDc(IDc);
		DR.setRSA_HASH_PASSWORD(PSW);
		return DR;
	}
	
	public Data_Modify Unpack_0x01(byte[] NewByte){
		Data_Modify DM = new Data_Modify();
		byte[] IDcByte = new byte[4];
		System.arraycopy(NewByte, 0, IDcByte, 0, IDcByte.length);
		byte[] PSWByte = new byte[129];
		System.arraycopy(NewByte, 4, PSWByte, 0, PSWByte.length);
		byte[] NPSWByte = new byte[129];
		System.arraycopy(NewByte, 133, NPSWByte, 0, NPSWByte.length);
		int IDc = ByteArrayToInt2(IDcByte);
		BigInteger PSW = new BigInteger(PSWByte);
		BigInteger NPSW = new BigInteger(NPSWByte);
		DM.setIDc(IDc);
		DM.setRSA_HASH_PASSWORD(PSW);
		DM.setRSA_HASH_NPASSWORD(NPSW);
		return DM;
	}
	
	public C_AS Unpack_0x07(byte[] NewByte){
		C_AS CA = new C_AS();
		byte[] IDcByte = new byte[4];
		byte[] IDtgsByte = new byte[4];
		byte[] TS1Byte = new byte[19];
		System.arraycopy(NewByte, 0, IDcByte, 0, IDcByte.length);
		System.arraycopy(NewByte, 4, IDtgsByte, 0, IDtgsByte.length);
		System.arraycopy(NewByte, 8, TS1Byte, 0, TS1Byte.length);
		int IDc = ByteArrayToInt2(IDcByte);
		int IDtgs = ByteArrayToInt2(IDtgsByte);
		String TS1 = null;
		try {
			TS1 = new String(TS1Byte,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CA.setIDc(IDc);
		CA.setIDtgs(IDtgs);
		CA.setTS(TS1);
		return CA;
	}
	
	public AS_C Unpack_0x08(byte[] ChangedNewByte){
		Text text = new Text();
		AS_C AC = new AS_C();
		Keys kkey = new Keys();
		int[] Keyc = kkey.ReadKeysFromFile("Keyc.txt");
		//Ekc解密
		byte[] NewByte = text.DESSupreier(1, ChangedNewByte, Keyc);
		byte[] KeyByte = new byte[9];
		byte[] IDtgsByte = new byte[4];
		byte[] TS2Byte = new byte[19];
		byte[] LTByte = new byte[4];
		byte[] ChangedTicket = new byte[44];
		System.arraycopy(NewByte, 0, KeyByte, 0, KeyByte.length);
		System.arraycopy(NewByte, 9, IDtgsByte, 0, IDtgsByte.length);
		System.arraycopy(NewByte, 13, TS2Byte, 0, TS2Byte.length);
		System.arraycopy(NewByte, 32, LTByte, 0, LTByte.length);
		System.arraycopy(NewByte, 36, ChangedTicket, 0, ChangedTicket.length);
		BigInteger Key = new BigInteger(KeyByte);
		int IDtgs = ByteArrayToInt2(IDtgsByte);
		String TS2 = null;
		try {
			TS2 = new String(TS2Byte,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int LT = ByteArrayToInt2(LTByte);
		AC.setKey(Key);
		AC.setID(IDtgs);
		AC.setTS(TS2);
		AC.setLT(LT);
		AC.setChangedTicket(ChangedTicket);
		return AC;
	}
	
	public C_TGS Unpack_0x09(byte[] NewByte){
		Text text = new Text();
		Keys kkey = new Keys();
		int[] Keytgs = kkey.ReadKeysFromFile("Keytgs.txt");
		C_TGS CT = new C_TGS();
		Ticket ticket = new Ticket();
		Authenticator Au = new Authenticator();
		byte[] IDvByte = new byte[4];
		byte[] ChangedTicketByte = new byte[44];
		byte[] ChangedAuthenByte = new byte[27];
		System.arraycopy(NewByte, 0, IDvByte, 0, IDvByte.length);
		System.arraycopy(NewByte, 4, ChangedTicketByte, 0, ChangedTicketByte.length);
		System.arraycopy(NewByte, 48, ChangedAuthenByte, 0, ChangedAuthenByte.length);
		int IDv = ByteArrayToInt2(IDvByte);
		//Ektgs解密
		byte[] TicketByte = text.DESSupreier(1, ChangedTicketByte ,Keytgs);
		BigInteger Keyctgs=ticket.getKey();
		int[] Keyc_tgs = kkey.StringToInts(kkey.BigIntegerToString(Keyctgs));
		//Ekc,tgs解密
		byte[] AuthenByte = text.DESSupreier(1, ChangedAuthenByte, Keyc_tgs);
		byte[] Key1Byte = new byte[9];
		byte[] IDcByte = new byte[4];
		byte[] ADByte = new byte[4];
		byte[] IDtgs1Byte = new byte[4];
		byte[] TS2Byte1 = new byte[19];
		byte[] LT2Byte = new byte[4];
		System.arraycopy(NewByte, 0, Key1Byte, 0, Key1Byte.length);
		System.arraycopy(TicketByte, 9, IDcByte, 0, IDcByte.length);
		System.arraycopy(TicketByte, 13, ADByte, 0, ADByte.length);
		System.arraycopy(TicketByte, 17, IDtgs1Byte, 0, IDtgs1Byte.length);
		System.arraycopy(TicketByte, 21, TS2Byte1, 0, TS2Byte1.length);
		System.arraycopy(TicketByte, 40, LT2Byte, 0, LT2Byte.length);
		byte[] IDcByte1 = new byte[4];
		byte[] ADcByte = new byte[4];
		byte[] TSByte = new byte[19];
		System.arraycopy(AuthenByte, 0, IDcByte1, 0, IDcByte1.length);
		System.arraycopy(AuthenByte, 4, ADcByte, 0, ADcByte.length);
		System.arraycopy(AuthenByte, 8, TSByte, 0, TSByte.length);
		BigInteger Key = new BigInteger(Key1Byte);
		int IDc = ByteArrayToInt2(Key1Byte);
		int ADc = ByteArrayToInt2(ADByte);
		int IDtgs = ByteArrayToInt2(IDtgs1Byte);
		int LT2 = ByteArrayToInt2(LT2Byte);
		String TS2 = null;
		try {
			TS2 = new String(TS2Byte1,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int IDc1 = ByteArrayToInt2(IDcByte1);
		int ADc1 = ByteArrayToInt2(ADcByte);
		String TS = null;
		try {
			TS = new String(TSByte,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ticket.setKey(Key);
		ticket.setID1(IDc);
		ticket.setAD(ADc);
		ticket.setID2(IDtgs);
		ticket.setTS(TS2);
		ticket.setLT(LT2);
		Au.setID(IDc1);
		Au.setAD(ADc1);
		Au.setTS(TS);
		CT.setID(IDv);
		CT.setTicket(ticket);
		CT.setAuthenticator(Au);
		return CT;
	}
///////////////////////////////	
	public TGS_C Unpack_0x0a(byte[] ChangedNewByte,int[] Keyctgs){
		Text text = new Text();
		TGS_C TC = new TGS_C();
		//Ekc,tgs解密
		
		byte[] NewByte = text.DESSupreier(1, ChangedNewByte, Keyctgs);
		byte[] KeyByte = new byte[9];
		byte[] IDvByte = new byte[4];
		byte[] TS4Byte = new byte[19];
		byte[] TicketByte = new byte[44];
		System.arraycopy(NewByte, 0, KeyByte, 0, KeyByte.length);
		System.arraycopy(NewByte, 9, IDvByte, 0, IDvByte.length);
		System.arraycopy(NewByte, 13, TS4Byte, 0, TS4Byte.length);
		System.arraycopy(NewByte, 31, TicketByte, 0, TicketByte.length);
		BigInteger Key = new BigInteger(KeyByte);
		int IDv = ByteArrayToInt2(IDvByte);
		String TS4 = null;
		try {
			TS4 = new String(TS4Byte,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TC.setID(IDv);
		TC.setKey(Key);
		TC.setTS(TS4);
		TC.setChangedTicket(TicketByte);
		return TC;
	}
	
	public C_V Unpack_0x0b(byte[] NewByte,int[] Keycv){
		Text text = new Text();
		C_V CV = new C_V();
		Ticket ticket = new Ticket();
		Authenticator Authen = new Authenticator();
		byte[] ChangedTicketByte = new byte[44];
		byte[] ChangedAuthenByte = new byte[27];
		System.arraycopy(NewByte, 0, ChangedTicketByte, 0, ChangedTicketByte.length);
		System.arraycopy(NewByte, 44, ChangedAuthenByte, 0, ChangedAuthenByte.length);
		//Ekv解密
		Keys kkey = new Keys();
		int[] Keyv = kkey.ReadKeysFromFile("Keyv.txt"); 
		byte[] TicketByte = text.DESSupreier(1, ChangedTicketByte, Keyv);
		byte[] KeyByte = new byte[9];
		byte[] IDcByte = new byte[4];
		byte[] ADcByte = new byte[4];
		byte[] IDvByte = new byte[4];
		byte[] TS4Byte = new byte[19];
		byte[] LT4Byte = new byte[4];
		System.arraycopy(NewByte, 0, KeyByte, 0, KeyByte.length);
		System.arraycopy(TicketByte, 9, IDcByte, 0, IDcByte.length);
		System.arraycopy(TicketByte, 13, ADcByte, 0, ADcByte.length);
		System.arraycopy(TicketByte, 17, IDvByte, 0, IDvByte.length);
		System.arraycopy(TicketByte, 21, TS4Byte, 0, TS4Byte.length);
		System.arraycopy(TicketByte, 40, LT4Byte, 0, LT4Byte.length);
		BigInteger Key = new BigInteger(KeyByte);
		int IDc = ByteArrayToInt2(IDcByte);
		int ADc = ByteArrayToInt2(ADcByte);
		int IDv = ByteArrayToInt2(IDvByte);
		String TS4 = null;
		try {
			TS4 = new String(TS4Byte,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int LT4 = ByteArrayToInt2(LT4Byte);
		ticket.setID1(IDc);
		ticket.setKey(Key);
		ticket.setID2(IDv);
		ticket.setAD(ADc);
		ticket.setLT(LT4);
		ticket.setTS(TS4);
		//Ekc,v解密
		byte[] AuthenByte = text.DESSupreier(1, ChangedAuthenByte, Keycv);
		byte[] IDcByte1 = new byte[4];
		byte[] ADcByte1 = new byte[4];
		byte[] TS5Byte = new byte[19];
		System.arraycopy(AuthenByte, 0, IDcByte1, 0, IDcByte1.length);
		System.arraycopy(AuthenByte, 0, ADcByte1, 4, ADcByte1.length);
		System.arraycopy(AuthenByte, 0, TS5Byte, 8, TS5Byte.length);
		int IDc1 = ByteArrayToInt2(IDcByte1);
		int ADc1 = ByteArrayToInt2(ADcByte1);
		String TS5 = null;
		try {
			TS5 = new String(TS5Byte,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Authen.setID(IDc1);
		Authen.setAD(ADc1);
		Authen.setTS(TS5);
		CV.setTicket(ticket);
		CV.setAuthenticator(Authen);
		return CV;
	}
	
	public V_C Unpack_0x0c(byte[] ChangedNewByte,int[] Keycv){
		Text text = new Text();
		//Ekc,tgs解密
		
		byte[] NewByte = text.DESSupreier(1, ChangedNewByte, Keycv);
		V_C VC = new V_C();
		String TS5 = null;
		try {
			TS5 = new String(NewByte,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VC.setTS(TS5);
		return VC;
	}
	
	public Data_Online Unpack_0x10(byte[] NewByte){
		Data_Online DOn = new Data_Online();
		byte[] IDcByte = new byte[4];
		System.arraycopy(NewByte, 0, IDcByte, 0, IDcByte.length);
		int IDc = ByteArrayToInt2(IDcByte);
		DOn.setIDc(IDc);
		return DOn;
	}
	
	public Data_Chat Unpack_0x1c(byte[] ChangedNewByte){
		Text text  = new Text();
		Keys kkey = new Keys();
		int[] Keyc = kkey.ReadKeysFromFile("Keyc.txt");
		//DES解密
		byte[] NewByte = text.DESSupreier(1, ChangedNewByte, Keyc);
		Data_Chat DC = new Data_Chat();
		EK_message EKm = new EK_message();
		byte[] IDcByte = new byte[4];
		byte[] HMSGByte = new byte[16];
		byte[] SignByte = new byte[129];
		System.arraycopy(NewByte, 0, IDcByte, 0, IDcByte.length);
		System.arraycopy(NewByte, 4, HMSGByte, 0, HMSGByte.length);
		System.arraycopy(NewByte, 20, SignByte, 0, SignByte.length);
		int IDc = ByteArrayToInt2(IDcByte);
		BigInteger HMSG = new BigInteger(HMSGByte);
		BigInteger Sign = new BigInteger(SignByte);
		EKm.setH_MSG(HMSG);
		EKm.setSIGN(Sign);
		DC.setIDc(IDc);
		DC.setEK_message(EKm);
		return DC;
	}
	
	public Data_Offline Unpack_0x16(byte[] NewByte){
		Data_Offline DOf = new Data_Offline();
		byte[] IDcByte = new byte[4];
		System.arraycopy(NewByte, 0, IDcByte, 0, IDcByte.length);
		int IDc = ByteArrayToInt2(IDcByte);
		DOf.setIDc(IDc);
		return DOf;
	}
	
	public Data_Update Unpack_0x19(byte[] ChangedNewByte){
		//DES解密
		byte[] NewByte = DESCHULI(ChangedNewByte);
		Data_Update DU = new Data_Update();
		byte[] KeyByte = new byte[9];
		System.arraycopy(NewByte, 0, KeyByte, 0, KeyByte.length);
		BigInteger Key = new BigInteger(KeyByte);
		DU.setKey(Key);
		return DU;
	}
	
	public byte[] DESCHULI(byte[] Origin){
		byte[] Changed =new byte[Origin.length];
		Changed = Origin;
		////////////////////////////////////////////////////////////////
		return Changed;
	}
	
	public byte[] IntToByteArray2(int value)     
	{     
	    byte[] src = new byte[4];    
	    src[0] = (byte) ((value>>24) & 0xFF);    
	    src[1] = (byte) ((value>>16)& 0xFF);    
	    src[2] = (byte) ((value>>8)&0xFF);      
	    src[3] = (byte) (value & 0xFF);         
	    return src;    
	}
	
	public int ByteArrayToInt2(byte[] bArr) {    
	         if(bArr.length!=4){    
	             return -1;    
	         }    
	         return (int) ((((bArr[0] & 0xff) << 24)      
	                    | ((bArr[1] & 0xff) << 16)      
	                    | ((bArr[2] & 0xff) << 8)  
	                    | ((bArr[3] & 0xff) << 0)));     
	}  
}
