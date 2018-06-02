package Package;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Unpack {
	public void Unpack_Head(byte[] NewByte,BufferedInputStream bufferedInputStream,Socket socket) throws SocketTimeoutException, IOException{
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
				case (byte)0x00:	zhuangtaiji0(Unpack_0x00(readFixedLengthArray(bufferedInputStream,132)));break;
				case (byte)0x01:	zhuangtaiji1(Unpack_0x01(readFixedLengthArray(bufferedInputStream,260)));break;
				case (byte)0x10:	zhuangtaiji10(Unpack_0x10(readFixedLengthArray(bufferedInputStream,4)));break;
				case (byte)0x13:	zhuangtaiji13(Unpack_0x13(readFixedLengthArray(bufferedInputStream,276)));break;
				case (byte)0x07:	zhuangtaiji7(Unpack_0x07(readFixedLengthArray(bufferedInputStream,26)));break;
				case (byte)0x08:	zhuangtaiji8(Unpack_0x08(readFixedLengthArray(bufferedInputStream,76)));break;
				case (byte)0x09:	zhuangtaiji9(Unpack_0x09(readFixedLengthArray(bufferedInputStream,72)));break;
				case (byte)0x0a:	zhuangtaiji0a(Unpack_0x0a(readFixedLengthArray(bufferedInputStream,72)));break;
				case (byte)0x0b:	zhuangtaiji0b(Unpack_0x0b(readFixedLengthArray(bufferedInputStream,68)));break;
				case (byte)0x0c:	zhuangtaiji0c(Unpack_0x0c(readFixedLengthArray(bufferedInputStream,132)));break;
				case (byte)0x16:	zhuangtaiji0(Unpack_0x00(readFixedLengthArray(bufferedInputStream,132)));break;
				case (byte)0x19:	zhuangtaiji0(Unpack_0x00(readFixedLengthArray(bufferedInputStream,132)));break;
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
	
	public void zhuangtaiji7(C_AS CA){
		System.out.println("客户端"+CA.getIDc());
		System.out.println("请求访问"+CA.getIDtgs());
		System.out.println("时间戳为"+CA.getTS());
	}
	
	public void zhuangtaiji8(AS_C AC){
		System.out.println("AS->C成功！");
	}
	
	public void zhuangtaiji9(C_TGS CT){
		System.out.println("发给TGS！");
	}
	
	public void zhuangtaiji0a(TGS_C TC){
		System.out.println("TGS->C");
	}
	
	public void zhuangtaiji0b(C_V CV){
		System.out.println("C->V");
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
	
	public void zhuangtaiji1a(){
		System.out.println("更新密钥成功");
	}
	
	public void zhuangtaiji1b(){
		System.out.println("更新密钥失败");
	}
	
	public void zhuangtaiji10(Data_Online DOn){
		System.out.println(DOn.getIDc()+"上线啦！");
	}
	
	public void zhuangtaiji11(){
		System.out.println("上线成功！");
	}
	
	public void zhuangtaiji12(){
		System.out.println("上线失败！");
	}
	
	public void zhuangtaiji13(Data_Chat DC){
		EK_message EKm = DC.getEKMSG();
		System.out.println(DC.getIDc());
		System.out.println(EKm.getMSG().toString());
		System.out.println(EKm.getH_MSG().toString());
		System.out.println(EKm.getSIGN().toString());
	}
	
	public void zhuangtaiji14(){
		System.out.println("发送聊天信息成功！");
	}
	
	public void zhuangtaiji15(){
		System.out.println("发送聊天内容失败！");
	}
	
	public Data_Regist Unpack_0x00(byte[] NewByte){
		Data_Regist DR = new Data_Regist();
		byte[] IDcByte = new byte[4];
		System.arraycopy(NewByte, 0, IDcByte, 0, IDcByte.length);
		byte[] PSWByte = new byte[129];
		PSWByte[0]=(byte)0x00;
		System.arraycopy(NewByte, 4, PSWByte, 1, PSWByte.length-1);
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
		PSWByte[0]=(byte)0x00;
		System.arraycopy(NewByte, 4, PSWByte, 1, PSWByte.length-1);
		byte[] NPSWByte = new byte[129];
		NPSWByte[0]=(byte)0x00;
		System.arraycopy(NewByte, 132, NPSWByte, 1, NPSWByte.length-1);
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
		byte[] TS1Byte = new byte[18];
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
		AS_C AC = new AS_C();
		//Ekc解密
		byte[] NewByte = DESCHULI(ChangedNewByte);
		byte[] KeyByte = new byte[8];
		byte[] IDtgsByte = new byte[4];
		byte[] TS2Byte = new byte[18];
		byte[] LTByte = new byte[4];
		byte[] ChangedTicket = new byte[42];
		System.arraycopy(NewByte, 0, KeyByte, 0, KeyByte.length);
		System.arraycopy(NewByte, 8, IDtgsByte, 0, IDtgsByte.length);
		System.arraycopy(NewByte, 12, TS2Byte, 0, TS2Byte.length);
		System.arraycopy(NewByte, 30, LTByte, 0, LTByte.length);
		System.arraycopy(NewByte, 34, ChangedTicket, 0, ChangedTicket.length);
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
		C_TGS CT = new C_TGS();
		Ticket ticket = new Ticket();
		Authenticator Au = new Authenticator();
		byte[] IDvByte = new byte[4];
		byte[] ChangedTicketByte = new byte[42];
		byte[] ChangedAuthenByte = new byte[26];
		System.arraycopy(NewByte, 0, IDvByte, 0, IDvByte.length);
		System.arraycopy(NewByte, 0, ChangedTicketByte, 4, ChangedTicketByte.length);
		System.arraycopy(NewByte, 0, ChangedAuthenByte, 46, ChangedAuthenByte.length);
		int IDv = ByteArrayToInt2(IDvByte);
		//Ektgs解密
		byte[] TicketByte = DESCHULI(ChangedTicketByte);
		//Ekc,tgs解密
		byte[] AuthenByte = DESCHULI(ChangedAuthenByte);
		byte[] Key1Byte = new byte[8];
		byte[] IDcByte = new byte[4];
		byte[] ADByte = new byte[4];
		byte[] IDtgs1Byte = new byte[4];
		byte[] TS2Byte1 = new byte[18];
		byte[] LT2Byte = new byte[4];
		System.arraycopy(TicketByte, 0, Key1Byte, 0, Key1Byte.length);
		System.arraycopy(TicketByte, 8, IDcByte, 0, IDcByte.length);
		System.arraycopy(TicketByte, 12, ADByte, 0, ADByte.length);
		System.arraycopy(TicketByte, 16, IDtgs1Byte, 0, IDtgs1Byte.length);
		System.arraycopy(TicketByte, 20, TS2Byte1, 0, TS2Byte1.length);
		System.arraycopy(TicketByte, 38, LT2Byte, 0, LT2Byte.length);
		byte[] IDcByte1 = new byte[4];
		byte[] ADcByte = new byte[4];
		byte[] TSByte = new byte[18];
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
	
	public TGS_C Unpack_0x0a(byte[] ChangedNewByte){
		TGS_C TC = new TGS_C();
		//Ekc,tgs解密
		byte[] NewByte = DESCHULI(ChangedNewByte);
		byte[] KeyByte = new byte[8];
		byte[] IDvByte = new byte[4];
		byte[] TS4Byte = new byte[18];
		byte[] TicketByte = new byte[42];
		System.arraycopy(NewByte, 0, KeyByte, 0, KeyByte.length);
		System.arraycopy(NewByte, 8, IDvByte, 0, IDvByte.length);
		System.arraycopy(NewByte, 12, TS4Byte, 0, TS4Byte.length);
		System.arraycopy(NewByte, 30, TicketByte, 0, TicketByte.length);
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
	
	public C_V Unpack_0x0b(byte[] NewByte){
		C_V CV = new C_V();
		Ticket ticket = new Ticket();
		Authenticator Authen = new Authenticator();
		byte[] ChangedTicketByte = new byte[42];
		byte[] ChangedAuthenByte = new byte[26];
		System.arraycopy(NewByte, 0, ChangedTicketByte, 0, ChangedTicketByte.length);
		System.arraycopy(NewByte, 42, ChangedAuthenByte, 0, ChangedAuthenByte.length);
		//Ekv解密
		byte[] TicketByte = DESCHULI(ChangedTicketByte);
		byte[] KeyByte = new byte[8];
		byte[] IDcByte = new byte[4];
		byte[] ADcByte = new byte[4];
		byte[] IDvByte = new byte[4];
		byte[] TS4Byte = new byte[18];
		byte[] LT4Byte = new byte[4];
		System.arraycopy(TicketByte, 0, KeyByte, 0, KeyByte.length);
		System.arraycopy(TicketByte, 8, IDcByte, 0, IDcByte.length);
		System.arraycopy(TicketByte, 12, ADcByte, 0, ADcByte.length);
		System.arraycopy(TicketByte, 16, IDvByte, 0, IDvByte.length);
		System.arraycopy(TicketByte, 20, TS4Byte, 0, TS4Byte.length);
		System.arraycopy(TicketByte, 38, LT4Byte, 0, LT4Byte.length);
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
		byte[] AuthenByte = DESCHULI(ChangedAuthenByte);
		byte[] IDcByte1 = new byte[4];
		byte[] ADcByte1 = new byte[4];
		byte[] TS5Byte = new byte[18];
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
	
	public V_C Unpack_0x0c(byte[] ChangedNewByte){
		V_C VC = new V_C();
		byte[] NewByte = DESCHULI(ChangedNewByte);
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
	
	public Data_Chat Unpack_0x13(byte[] ChangedNewByte){
		//DES解密
		byte[] NewByte = DESCHULI(ChangedNewByte);
		Data_Chat DC = new Data_Chat();
		EK_message EKm = new EK_message();
		byte[] IDcByte = new byte[4];
		byte[] MSGByte = new byte[128];
		byte[] HMSGByte = new byte[16];
		byte[] SignByte = new byte[128];
		System.arraycopy(NewByte, 0, IDcByte, 0, IDcByte.length);
		System.arraycopy(NewByte, 4, MSGByte, 0, MSGByte.length);
		System.arraycopy(NewByte, 132, HMSGByte, 0, HMSGByte.length);
		System.arraycopy(NewByte, 148, SignByte, 0, SignByte.length);
		int IDc = ByteArrayToInt2(IDcByte);
		BigInteger MSG = new BigInteger(MSGByte);
		BigInteger HMSG = new BigInteger(HMSGByte);
		BigInteger Sign = new BigInteger(SignByte);
		EKm.setMSG(MSG);
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
		byte[] KeyByte = new byte[8];
		System.arraycopy(NewByte, 0, KeyByte, 0, KeyByte.length);
		BigInteger Key = new BigInteger(KeyByte);
		DU.setKey(Key);
		return DU;
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
