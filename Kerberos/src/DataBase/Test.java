package DataBase;
import java.net.UnknownHostException;

import Package.Authenticator;
import Package.Pack;
import Package.Ticket;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sql a = new sql();
/*		try {
			Ticket ticket = a.SelectTicket(1276338796);
			System.out.println(ticket.getID1());
			System.out.println(ticket.getAD());
			System.out.println(ticket.getID2());
			System.out.println(ticket.getLT());
			System.out.println(ticket.getTS());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Authenticator Auth= a.SelectAuthenticator(1276338796);
			System.out.println(Auth.getID());
			System.out.println(Auth.getAD());
			System.out.println(Auth.getTS());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			a.DeleteAuthenticator(1276338796);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			a.DeleteTicket(1276338796);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
