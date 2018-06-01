package Package;

import java.math.BigInteger;

public class AS_C {
	private BigInteger Key;
	private int ID;
	private String TS;
	private int LT;
	private Ticket ticket;
	private byte[] Changedticket;
	public BigInteger getKey(){
		return Key;
	}
	public int getID(){
		return ID;
	}
	public void setKey(BigInteger key){
		this.Key=key;
	}
	public void setID(int id){
		this.ID=id;
	}
	public void setTS(String ts){
		this.TS=ts;
	}
	public void setLT(int lt){
		this.LT=lt;
	}
	public String getTS(){
		return TS;
	}
	public int getLT(){
		return LT;
	}
	public void setTicket(Ticket ticket){
		this.ticket=ticket;
	}
	public Ticket getTicket(){
		return ticket;
	}
	public void setChangedTicket(byte[] ticket){
		this.Changedticket=ticket;
	}
	public byte[] getChangedTicket(){
		return Changedticket;
	}
}
