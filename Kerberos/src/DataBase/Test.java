package DataBase;
import java.math.BigInteger;

import Package.Authenticator;
import Package.Ticket;
import RSA.Hash;
import Package.Pack;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			sql a=new sql();
			Pack pack = new Pack();
			try {
				Hash hash = new Hash();
				BigInteger BI = hash.getMD5("1111");
				int IDc = 1224;
				a.AddNewUsers(IDc, BI);
				BigInteger BI1 = a.HasAIDc(IDc);
				System.out.println(BI1.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
