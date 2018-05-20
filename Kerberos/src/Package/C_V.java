package Package;

public class C_V {
	private Ticket ticket;
	private Authenticator auth=new Authenticator();
	public void setTicket(Ticket ticket){
		this.ticket=ticket;
	}
	public Ticket getTicket(){
		return ticket;
	}
	public Authenticator getAuth(){
		return auth;
	}
}
