package DES;

import java.math.BigInteger;

import RSA.Decryption;
import RSA.Encryption;
import RSA.Hash;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String Psw = "12345678";
		Hash hash = new Hash();
		BigInteger hash_Password = hash.getMD5(Psw);
		Encryption En = new Encryption();
		Decryption De = new Decryption();
		BigInteger BI = En.encryption(hash_Password, "1000000003");
		BigInteger BW = De.decryption(BI, "1000000003");
		System.out.println(hash_Password.toString());
		System.out.println(BW.toString());
//		DES des =new DES();
//		des.ModeChoose();
	}
}
