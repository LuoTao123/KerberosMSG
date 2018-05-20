package Package;

public class AS_C {
	private int Key;
	private int ID;
	private String TS;
	private String LT;
	private Ticket ticket=new Ticket();
	public int getKey(){
		return Key;
	}
	public int getID(){
		return ID;
	}
	public void setKey(int key){
		this.Key=key;
	}
	public void setID(int id){
		this.ID=id;
	}
	public void setTS(String ts){
		this.TS=ts;
	}
	public void setLT(String lt){
		this.LT=lt;
	}
	public String getTS(){
		return TS;
	}
	public String getLT(){
		return LT;
	}
	public Ticket getTicket(){
		return ticket;
	}
}
