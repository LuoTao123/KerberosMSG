package DataBase;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

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
/*			sql a=new sql();
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
			}*/
/*		sql a = new sql();
		try {
			System.out.println(a.HasAIDc(12));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Text text = new Text();
		String str = "我到底是怎么一回事？？";
		BigInteger BI = text.StringToBigInteger(str);
		String xx = text.BigIntegerToString(BI);
		System.out.println(xx);
/*		byte[] bytes = BI.toByteArray();
		BigInteger BI1 = new BigInteger(bytes);
		Hash hash = new Hash();
		BigInteger BI = hash.getMD5("1222222222");
		Encryption EN = new Encryption();
		BigInteger rsa_hash_psw = EN.encryption(BI, String.valueOf(1000000003));
		byte[] BIbyte = rsa_hash_psw.toByteArray();
		System.out.println(BIbyte.length);
		for(int i = 0;i<BIbyte.length;i++){
			System.out.print(BIbyte[i]);
		}
		System.out.println();
		BigInteger BI1 = new BigInteger(BIbyte);
		Decryption DE = new Decryption();
		BigInteger HASH_PASSWORD = DE.decryption(BI1, String.valueOf(1000000003));
		if(BI.equals(BI1)){
			System.out.println("相等");
		}
		else{
			System.out.println("这事何等的卧槽");
		}*/
	}
}
