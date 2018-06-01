package Package;

public class C_TGS {
	private int ID;
	private byte[] Changedticket;
	private Ticket ticket;
	private Authenticator auth;
	
	public void setID(int id){
		this.ID=id;
	}
	public void setChangedTicket(byte[] ticket){
		this.Changedticket=ticket;
	}
	public void setTicket(Ticket ticket){
		this.ticket=ticket;
	}
	public void setAuthenticator(Authenticator auth){
		this.auth=auth;
	}
	public int getID(){
		return ID;
	}
	public byte[] getChangedTicket(){
		return Changedticket;
	}
	public Ticket getTicket(){
		return ticket;
	}
	public Authenticator getAuthenticator(){
		return auth;
	}
}
