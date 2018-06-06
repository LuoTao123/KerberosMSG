package DataBase;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import DES.Keys;
import DES.Text;
import Package.Authenticator;
import Package.Ticket;
import RSA.Decryption;
import RSA.Encryption;
import RSA.Hash;
import Package.Pack;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Keys kkey = new Keys();
		sql a = new sql();
		Hash hash = new Hash();
		BigInteger x = hash.getMD5("11223311");
		String str = x.toString();
		System.out.println(kkey.BigIntegerToString(x).length());
		try {
			a.AddNewUsers(1211221, x);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigInteger x1 = null;
		try {
			x1 = a.HasAIDc(122211221);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str1 = x1.toString();
		System.out.println(str);
		System.out.println(str1);
	}
}
