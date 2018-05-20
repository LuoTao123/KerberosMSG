
package Package;

public class Ticket{
	private int Key;
	private int ID1;
	private int AD;
	private int ID2;
	private String TS;
	private String LT;
	public void setKey(int key){
		this.Key=key;
	}
	public void setID1(int id1){
		this.ID1=id1;
	}
	public void setAD(int ad){
		this.AD=ad;
	}
	public void setID2(int id2){
		this.ID2=id2;
	}
	public void setTS(String ts){
		this.TS=ts;
	}
	public void setLT(String lt){
		this.LT=lt;
	}
	public int getKey(){
		return Key;
	}
	public int getID1(){
		return ID1;
	}
	public int getAD(){
		return AD;
	}
	public int getID2(){
		return ID2;
	}
	public String getTS(){
		return TS;
	}
	public String getLT(){
		return LT;
	}
}