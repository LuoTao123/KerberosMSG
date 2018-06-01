package Package;

public class C_V {
	private Ticket ticket;
	private byte[] ChangedTicket;
	private Authenticator auth;
	public void setTicket(Ticket ticket){
		this.ticket=ticket;
	}
	public void setChangedTicket(byte[] ticket){
		this.ChangedTicket= ticket;
	}
	public void setAuthenticator(Authenticator auth){
		this.auth=auth;
	}
	public Ticket getTicket(){
		return ticket;
	}
	public Authenticator getAuth(){
		return auth;
	}
	public byte[] getChangedTicket(){
		return ChangedTicket;
	}
}
