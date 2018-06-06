package Package;

import java.io.UnsupportedEncodingException;

import DES.Text;

public class Pack {
	public byte[] Server_Return(){
		byte[] NewByte=new byte[2];
		NewByte[0]=(byte)0x00;						//0为控制包
		NewByte[1]=(byte)0x00;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x00_Cont(){
		byte[] NewByte=new byte[2];
		NewByte[0]=(byte)0x01;						//1为数据包
		NewByte[1]=(byte)0x00;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x00_Data(Data_Regist DR){
		byte[] NewByte=new byte[314];				//1(控制包)+1（控制位0x00）+4（int账号）+128（1024/8的密码）
		int IDc=DR.getIDc();
		byte[] IDcByte=IntToByteArray2(IDc);
		String PSWString = DR.getRSA_HASH_PASSWORD().toString();
		byte[] PSWByte = null;
		byte[] ChangedByte = new byte[310]; 
		try {
			PSWByte = PSWString.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.arraycopy(IDcByte, 0, NewByte, 0, IDcByte.length);
		if(PSWByte.length<310){
			for(int i = 0;i<310-PSWByte.length;i++){
				ChangedByte[i] =(byte) 0x00;
			}
			System.arraycopy(PSWByte, 0, ChangedByte, 310-PSWByte.length, PSWByte.length);
		}else{
			System.arraycopy(PSWByte, 0, ChangedByte, 0, PSWByte.length);
		}
		System.arraycopy(ChangedByte, 0, NewByte, 4, ChangedByte.length);
		return NewByte;
	}
	
	public byte[] Pack_0x01_Cont(){
		byte[] NewByte=new byte[2];
		NewByte[0]=(byte)0x01;						//1为数据包
		NewByte[1]=(byte)0x01;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x01_Data(Data_Modify DM){
		byte[] NewByte=new byte[624];				//1(控制包)+1（控制位0x00）+4（int账号）+128（1024/8的密码）
		int IDc=DM.getIDc();
		byte[] IDcByte=IntToByteArray2(IDc);
		String PSWString = DM.getRSA_HASH_PASSWORD().toString();
		byte[] PSWByte = null;
		byte[] ChangedByte = new byte[310]; 
		try {
			PSWByte = PSWString.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.arraycopy(IDcByte, 0, NewByte, 0, IDcByte.length);
		if(PSWByte.length<310){
			for(int i = 0;i<310-PSWByte.length;i++){
				ChangedByte[i] =(byte) 0x00;
			}
			System.arraycopy(PSWByte, 0, ChangedByte, 310-PSWByte.length, PSWByte.length);
		}else{
			System.arraycopy(PSWByte, 0, ChangedByte, 0, PSWByte.length);
		}
		System.arraycopy(ChangedByte, 0, NewByte, 4, ChangedByte.length);
		String NPSWString = DM.getRSA_HASH_NPASSWORD().toString();
		byte[] NPSWByte = null;
		byte[] NChangedByte = new byte[310]; 
		try {
			NPSWByte = NPSWString.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(NPSWByte.length<310){
			for(int i = 0;i<310-NPSWByte.length;i++){
				NChangedByte[i] =(byte) 0x00;
			}
			System.arraycopy(NPSWByte, 0, NChangedByte, 310-NPSWByte.length, NPSWByte.length);
		}else{
			System.arraycopy(NPSWByte, 0, NChangedByte, 0, NPSWByte.length);
		}
		System.arraycopy(NChangedByte, 0, NewByte, 314, NChangedByte.length);
		return NewByte;
	}
	
	public byte[] Pack_0x02_Cont(){
		byte[] NewByte=new byte[2];
		NewByte[0]=(byte)0x00;						//1为数据包
		NewByte[1]=(byte)0x02;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x03_Cont(){
		byte[] NewByte=new byte[2];
		NewByte[0]=(byte)0x00;						//1为数据包
		NewByte[1]=(byte)0x03;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x04_Cont(){
		byte[] NewByte=new byte[2];
		NewByte[0]=(byte)0x00;						//0为控制包
		NewByte[1]=(byte)0x04;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x05_Cont(){
		byte[] NewByte=new byte[2];
		NewByte[0]=(byte)0x00;						//0为控制包
		NewByte[1]=(byte)0x05;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x06_Cont(){
		byte[] NewByte=new byte[2];
		NewByte[0]=(byte)0x00;						//0为控制包
		NewByte[1]=(byte)0x06;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x07_Cont(){
		byte[] NewByte=new byte[2];
		NewByte[0]=(byte)0x01;						//0为控制包
		NewByte[1]=(byte)0x07;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x07_Data(C_AS CA){
		byte[] NewByte=new byte[27];
		int IDc=CA.getIDc();
		int IDtgs=CA.getIDtgs();
		String TS1=CA.getTS();
		byte[] IDcByte=IntToByteArray2(IDc);
		byte[] IDtgsByte=IntToByteArray2(IDtgs);
		byte[] TS1Byte;
		try {
			TS1Byte = TS1.getBytes("UTF-8");
			System.arraycopy(TS1Byte, 0, NewByte, 8, TS1Byte.length);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.arraycopy(IDcByte, 0, NewByte, 0, IDcByte.length);
		System.arraycopy(IDtgsByte, 0, NewByte, 4, IDtgsByte.length);
		return NewByte;
	}
	
	public byte[] Pack_0x08_Cont(){
		byte[] NewByte=new byte[2];
		NewByte[0]=(byte)0x01;						//0为控制包
		NewByte[1]=(byte)0x08;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x08_Data(AS_C AC,int[] Keyc,int[] Keytgs){
		Text text = new Text();
		byte[] NewByte = new byte[102];
		byte[] TicketByte = new byte[55];
		String str = AC.getKey().toString();
		byte[] KeyByte = null;
		try {
			KeyByte = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] IDtgsByte = IntToByteArray2(AC.getID());
		byte[] TS2Byte = null;
		try {
			TS2Byte = AC.getTS().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] LTByte = IntToByteArray2(AC.getLT());
		String str1 = AC.getTicket().getKey().toString();
		byte[] KeyctgsByte = null;
		try {
			KeyctgsByte = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] IDcByte = IntToByteArray2(AC.getTicket().getID1());
		byte[] ADcByte = IntToByteArray2(AC.getTicket().getAD());
		byte[] IDtgs1Byte = IntToByteArray2(AC.getTicket().getID2());
		byte[] TS2Byte1 = null;
		try {
			TS2Byte1 = AC.getTicket().getTS().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] LT1Byte = IntToByteArray2(AC.getTicket().getLT());
		if(KeyByte.length==20){	
			System.arraycopy(KeyByte, 0, NewByte, 0, KeyByte.length);
		}else if(KeyByte.length<20){
			for(int i = 0;i<20-KeyByte.length;i++){
				KeyByte[i] = (byte)0x00;
			}
			System.arraycopy(KeyByte, 0, NewByte, 20-KeyByte.length, KeyByte.length);
		}
		System.arraycopy(IDtgsByte, 0, NewByte, 20, IDtgsByte.length);
		System.arraycopy(TS2Byte, 0, NewByte, 24, TS2Byte.length);
		System.arraycopy(LTByte, 0, NewByte, 43, LTByte.length);
		if(KeyctgsByte.length==20){	
			System.arraycopy(KeyctgsByte, 0, TicketByte, 0, KeyctgsByte.length);
		}else if(KeyctgsByte.length<20){
			for(int i = 0;i<20-KeyctgsByte.length;i++){
				KeyctgsByte[i] = (byte)0x00;
			}
			System.arraycopy(KeyctgsByte, 0, TicketByte, 20-KeyctgsByte.length, KeyctgsByte.length);
		}
		System.arraycopy(IDcByte, 0, TicketByte, 20, IDcByte.length);
		System.arraycopy(ADcByte, 0, TicketByte, 24, ADcByte.length);
		System.arraycopy(IDtgs1Byte, 0, TicketByte, 28, IDtgs1Byte.length);
		System.arraycopy(TS2Byte1, 0, TicketByte, 32, TS2Byte1.length);
		System.arraycopy(LT1Byte, 0, TicketByte, 51, LT1Byte.length);
		//Ektgs加密
		byte ChangedTicket[] = text.DESSupreier(0, TicketByte, Keytgs);
		System.arraycopy(ChangedTicket, 0, NewByte, 47, ChangedTicket.length);
		//Ekc加密
		byte[] ChangedNewByte = text.DESSupreier(0, NewByte, Keyc);
		return ChangedNewByte;
	}
	
	public byte[] Pack_0x09_Cont(){
		byte[] NewByte=new byte[2];
		NewByte[0]=(byte)0x01;						//0为控制包
		NewByte[1]=(byte)0x09;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x09_Data(C_TGS CT,int[] Keyctgs){
		Text text = new Text();
		byte[] NewByte=new byte[75];
		byte[] IDvByte = IntToByteArray2(CT.getID());
		byte[] TicketByte = CT.getChangedTicket();
		byte[] AuthenticatorByte = new byte[27];
		byte[] IDcByte = IntToByteArray2(CT.getAuthenticator().getID());
		byte[] ADcByte = IntToByteArray2(CT.getAuthenticator().getAD());
		byte[] TSByte = null;
		try {
			TSByte = CT.getAuthenticator().getTS().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.arraycopy(IDcByte, 0, AuthenticatorByte, 0, IDcByte.length);
		System.arraycopy(ADcByte, 0, AuthenticatorByte, 4, ADcByte.length);
		System.arraycopy(TSByte, 0, AuthenticatorByte, 8, TSByte.length);
		//Ekc,tgs加密
		byte[] ChangedAuthenByte =  text.DESSupreier(0, TicketByte, Keyctgs);
		System.arraycopy(IDvByte, 0, NewByte, 0, IDvByte.length);
		System.arraycopy(TicketByte, 0, NewByte, 4, TicketByte.length);
		System.arraycopy(ChangedAuthenByte, 0, NewByte, 48, ChangedAuthenByte.length);
		return NewByte;
	}
	
	public byte[] Pack_0x0a_Cont(){
		byte[] NewByte=new byte[2];
		NewByte[0]=(byte)0x01;						//0为控制包
		NewByte[1]=(byte)0x0a;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x0a_Data(TGS_C TC,int[] Keyctgs,int[] Keyv){
		Text text = new Text();
		byte[] NewByte = new byte[76];
		byte[] KeyByte = TC.getKey().toByteArray();
		byte[] IDvByte = IntToByteArray2(TC.getID());
		byte[] TSByte = null;
		try {
			TSByte = TC.getTS().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] TicketByte = new byte[44];
		byte[] KeyByte1 = TC.getTicket().getKey().toByteArray();
		byte[] IDcByte = IntToByteArray2(TC.getTicket().getID1());
		byte[] ADcByte = IntToByteArray2(TC.getTicket().getAD());
		byte[] IDvByte1 = IntToByteArray2(TC.getTicket().getID2());
		byte[] TSByte1 = null;
		try {
			TSByte1 = TC.getTicket().getTS().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] LTByte = IntToByteArray2(TC.getTicket().getLT());
		if(KeyByte.length==9){	
			System.arraycopy(KeyByte, 0, TicketByte, 0, KeyByte.length);
		}else if(KeyByte.length<9){
			for(int i = 0;i<9-KeyByte.length;i++){
				KeyByte[i] = (byte)0x00;
			}
			System.arraycopy(KeyByte, 0, TicketByte, 9-KeyByte.length, KeyByte.length);
		}
		System.arraycopy(IDcByte, 0, TicketByte, 9, IDcByte.length);
		System.arraycopy(ADcByte, 0, TicketByte, 13, ADcByte.length);
		System.arraycopy(IDvByte1, 0, TicketByte, 17, IDvByte1.length);
		System.arraycopy(TSByte1, 0, TicketByte, 21, TSByte1.length);
		System.arraycopy(LTByte, 0, TicketByte, 40, LTByte.length);
		//Ekv加密
		//////
		byte[] ChangedTicketByte =text.DESSupreier(0, TicketByte, Keyv);
		if(KeyByte1.length==9){	
			System.arraycopy(KeyByte1, 0, NewByte, 0, KeyByte1.length);
		}else if(KeyByte1.length<9){
			for(int i = 0;i<9-KeyByte1.length;i++){
				KeyByte1[i] = (byte)0x00;
			}
			System.arraycopy(KeyByte1, 0, NewByte, 9-KeyByte1.length, KeyByte1.length);
		}
		System.arraycopy(IDvByte, 0, NewByte, 9, IDvByte.length);
		System.arraycopy(TSByte, 0, NewByte, 13, TSByte.length);
		System.arraycopy(ChangedTicketByte, 0, NewByte, 32, TicketByte.length);
		//Ekc,tgs加密
		byte[] ChangedNewByte = text.DESSupreier(0, NewByte, Keyctgs);
		return ChangedNewByte;
	}
	
	public byte[] Pack_0x0b_Cont(){
		byte[] NewByte=new byte[2];
		NewByte[0]=(byte)0x01;						//0为控制包
		NewByte[1]=(byte)0x0b;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x0b_Data(C_V CV,int[] Keycv){
		Text text = new Text();
		byte[] NewByte = new byte[71];
		byte[] TicketByte = CV.getChangedTicket();
		byte[] AuthenByte = new byte[27];
		byte[] IDcByte = IntToByteArray2(CV.getAuth().getID());
		byte[] ADcByte = IntToByteArray2(CV.getAuth().getAD());
		byte[] TS4Byte = null;
		try {
			TS4Byte = CV.getAuth().getTS().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.arraycopy(IDcByte, 0, AuthenByte, 0, IDcByte.length);
		System.arraycopy(ADcByte, 0, AuthenByte, 4, ADcByte.length);
		System.arraycopy(TS4Byte, 0, AuthenByte, 8, TS4Byte.length);
		//Ekc,v加密
		byte[] ChangedAuthenByte = text.DESSupreier(0, AuthenByte, Keycv);
		System.arraycopy(TicketByte, 0, NewByte, 0, TicketByte.length);
		System.arraycopy(ChangedAuthenByte, 0, NewByte, 44, ChangedAuthenByte.length);
		return NewByte;
	}
	
	public byte[] Pack_0x0c_Cont(){
		byte[] NewByte = new byte[2];
		NewByte[0] = (byte)0x01;						//0为控制包
		NewByte[1] = (byte)0x0c;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x0c_Data(V_C VC,int[] Keycv){
		Text text =new Text();
		byte[] NewByte = new byte[19];
		byte[] TS5Byte = null;
		try {
			TS5Byte = VC.getTS().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.arraycopy(TS5Byte, 0, NewByte, 0, TS5Byte.length);
		//Ekc,v加密
		byte[] ChangedNewByte = text.DESSupreier(0, NewByte, Keycv);
		return ChangedNewByte;
	}
	
	public byte[] Pack_0x0d_Cont(){
		byte[] NewByte = new byte[2];
		NewByte[0] = (byte)0x00;						//0为控制包
		NewByte[1] = (byte)0x0d;						//控制头(位)
		return NewByte;
	}
	 
	public byte[] Pack_0x0e_Cont(){
		byte[] NewByte = new byte[2];
		NewByte[0] = (byte)0x00;						//0为控制包
		NewByte[1] = (byte)0x0e;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x0f_Cont(){
		byte[] NewByte = new byte[2];
		NewByte[0] = (byte)0x00;						//0为控制包
		NewByte[1] = (byte)0x0f;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x10_Cont(){
		byte[] NewByte=new byte[2];
		NewByte[0]=(byte)0x01;						//0为控制包
		NewByte[1]=(byte)0x10;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x10_Data(Data_Online DO){
		byte[] NewByte=new byte[4];
		int IDc=DO.getIDc();
		byte[] IDcByte=IntToByteArray2(IDc);
		System.arraycopy(IDcByte, 0, NewByte, 0, IDcByte.length);
		return NewByte;
	}
	
	public byte[] Pack_0x11_Cont(){
		byte[] NewByte=new byte[2];
		NewByte[0]=(byte)0x00;						//0为控制包
		NewByte[1]=(byte)0x11;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x12_Cont(){
		byte[] NewByte=new byte[2];
		NewByte[0]=(byte)0x00;						//0为控制包
		NewByte[1]=(byte)0x12;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x13_Cont(){
		byte[] NewByte=new byte[2];
		NewByte[0]=(byte)0x01;						//0为控制包
		NewByte[1]=(byte)0x13;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x13_Data(Data_Chat DC,int[] K1){
		Text text =new Text();
		byte[] NewByte=new byte[149];
		int IDc=DC.getIDc();
		byte[] IDcByte=IntToByteArray2(IDc);
		EK_message EKm = DC.getEKMSG();
		byte[] HMSGByte=EKm.getH_MSG().toByteArray();
		byte[] SignByte=EKm.getSIGN().toByteArray();
		System.arraycopy(IDcByte, 0, NewByte, 0, IDcByte.length);
		System.arraycopy(HMSGByte, 0, NewByte, 4, HMSGByte.length);
		System.arraycopy(SignByte, 0, NewByte, 20, SignByte.length);
		//DES加密
		byte[] ChangedNewByte = text.DESSupreier(0, NewByte, K1);
		return ChangedNewByte;
	}
	
	public byte[] Pack_0x14_Cont(){
		byte[] NewByte=new byte[2];
		NewByte[0]=(byte)0x00;						//0为控制包
		NewByte[1]=(byte)0x14;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x15_Cont(){
		byte[] NewByte=new byte[2];
		NewByte[0]=(byte)0x00;						//0为控制包
		NewByte[1]=(byte)0x15;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x16_Cont(){
		byte[] NewByte = new byte[2];
		NewByte[0] = (byte)0x01;						//0为控制包
		NewByte[1] = (byte)0x16;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x16_Data(Data_Offline DOf){
		byte[] NewByte = new byte[4];
		byte[] IDcByte = IntToByteArray2(DOf.getIDc());
		System.arraycopy(IDcByte, 0, NewByte, 0, IDcByte.length);
		return NewByte;
	}
	
	public byte[] Pack_0x17_Cont(){
		byte[] NewByte = new byte[2];
		NewByte[0] = (byte)0x00;						//0为控制包
		NewByte[1] = (byte)0x17;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x18_Cont(){
		byte[] NewByte = new byte[2];
		NewByte[0] = (byte)0x00;						//0为控制包
		NewByte[1] = (byte)0x18;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x19_Cont(){
		byte[] NewByte = new byte[2];
		NewByte[0] = (byte)0x01;						//0为控制包
		NewByte[1] = (byte)0x19;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x19_Data(Data_Update DU,int[] K1){
		Text text = new Text();
		byte[] NewByte = new byte[9];
		byte[] KeyByte = DU.getKey().toByteArray();
		if(KeyByte.length==9){	
			System.arraycopy(KeyByte, 0, NewByte, 0, KeyByte.length);
		}else if(KeyByte.length<9){
			for(int i = 0;i<9-KeyByte.length;i++){
				KeyByte[i] = (byte)0x00;
			}
			System.arraycopy(KeyByte, 0, NewByte, 9-KeyByte.length, KeyByte.length);
		}
		//DES加密
		byte[] ChangedNewByte = text.DESSupreier(0, NewByte, K1);
		return ChangedNewByte;
	}
	
	public byte[] Pack_0x1a_Cont(){
		byte[] NewByte = new byte[2];
		NewByte[0] = (byte)0x00;						//0为控制包
		NewByte[1] = (byte)0x1a;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x1b_Cont(){
		byte[] NewByte = new byte[2];
		NewByte[0] = (byte)0x00;						//0为控制包
		NewByte[1] = (byte)0x1b;						//控制头(位)
		return NewByte;
	}
	
	public byte[] Pack_0x1c_Cont() {
		byte[] NewByte = new byte[2];
		NewByte[0] = (byte)0x01;						//0为控制包
		NewByte[1] = (byte)0x1c;						//控制头(位)
		return NewByte;
	}
	/*
	public byte[] DESCHULI(byte[] Origin){
		byte[] Changed =new byte[Origin.length];
		Changed = Origin;
		////////////////////////////////////////////////////////////////
		return Changed;
	}
	*/
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
	
	public String IPByteToString(byte[] bArr){
		int[] bArrInt = new int[bArr.length];
		for(int i = 0;i<bArrInt.length;i++){
			if((int)bArr[i]<0){
				bArrInt[i] = 256+(int)bArr[i];
			}else{
				bArrInt[i] = (int)bArr[i];
			}
		}
		return bArrInt[0]+"."+bArrInt[1]+"."+bArrInt[2]+"."+bArrInt[3];
	}
	
	public byte[] IPStringToByte(String ip){
		String[] IpStrings = ip.split(".");
		int[] IpInts = new int[IpStrings.length];
		byte[] IpByte = new byte[IpStrings.length];
		for(int i = 0;i<IpInts.length;i++){
			 IpInts[i] = Integer.valueOf(IpStrings[i]);
			 IpByte[i] = (byte) IpInts[i];
		}
		return IpByte;
	}
}
