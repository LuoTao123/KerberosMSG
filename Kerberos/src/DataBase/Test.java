package DataBase;
import Package.Authenticator;
import Package.Ticket;
import Package.Pack;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			sql a=new sql();
			Pack pack = new Pack();
			try {
				a.DeleteAuthenticator(121421);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
