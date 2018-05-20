package Package;

public class C_TGS {
	private int ID;
	private Ticket ticket;
	private Authenticator auth=new Authenticator();
	
	public void setID(int id){
		this.ID=id;
	}
	public void setTicket(Ticket ticket){
		this.ticket=ticket;
	}
	public int getID(){
		return ID;
	}
	public Ticket getTicket(){
		return ticket;
	}
	public Authenticator getAuthenticator(){
		return auth;
	}
}
