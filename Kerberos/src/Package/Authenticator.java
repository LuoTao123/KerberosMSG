package Package;

public class Authenticator {
	private int ID;
	private int AD;
	private String TS;
	public void setID(int id){
		this.ID=id;
	}
	public void setAD(int ad){
		this.AD=ad;
	}
	public void setTS(String ts){
		this.TS=ts;
	}
	public int getID(){
		return ID;
	}
	public int getAD(){
		return AD;
	}
	public String getTS(){
		return TS;
	}
}
