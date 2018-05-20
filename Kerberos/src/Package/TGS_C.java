package Package;

public class TGS_C {
	private int Key;
	private int ID;
	private String TS;
	private Ticket ticket=new Ticket();
	public void setKey(int key){
		this.Key=key;
	}
	public void setID(int id){
		this.ID=id;
	}
	public void setTS(String ts){
		this.TS=ts;
	}
	public int getKey(){
		return Key;
	}
	public int getID(){
		return ID;
	}
	public String getTS(){
		return TS;
	}
	public Ticket getTicket(){
		return ticket;
	}
}
