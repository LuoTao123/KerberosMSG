package Package;

public class Data_Chat {
	private int IDc;
	private EK_message EKMSG=new EK_message();
	
	public void setIDc(int idc){
		this.IDc=idc;
	}
	
	public int getIDc(){
		return this.IDc;
	}
	public void setEK_message(EK_message ekmsg){
		this.EKMSG=ekmsg;
	}
	public EK_message getEKMSG(){
		return this.EKMSG ;
	}
}
