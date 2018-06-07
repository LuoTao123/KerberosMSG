package Package;

import java.math.BigInteger;

public class TGS_C {
	private BigInteger Key;
	private int ID;
	private String TS;
	private Ticket ticket;
	private byte[] ChangedTicket;
	public void setKey(BigInteger key){
		this.Key=key;
	}
	public void setID(int id){
		this.ID=id;
	}
	public void setTS(String ts){
		this.TS=ts;
	}
	public void setTicket(Ticket ticket){
		this.ticket = ticket;
	}
	public void setChangedTicket(byte[] ChangedTicket){
		this.ChangedTicket=ChangedTicket;
	}
	public BigInteger getKey(){
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
	public byte[] getChangedTicket(){
		return ChangedTicket;
	}
}
