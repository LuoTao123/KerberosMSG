package DES;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import Package.AS_C;
import RSA.Decryption;
import RSA.Encryption;
import RSA.Hash;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DES des =new DES();
		Hash hash = new Hash();
		BigInteger Keyctgs = hash.getMD5("2212111111");
		String str = Keyctgs.toString();
		byte[] KeyByte = null;
		try {
			KeyByte = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(KeyByte.length);
		String nstr = null;
		try {
			nstr = new String(KeyByte,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigInteger NKey = new BigInteger(nstr);
		System.out.println(nstr);
		if(NKey.equals(Keyctgs)){
			System.out.println("ПаµИ");
		}
//		DES des =new DES();
//		des.ModeChoose();
	}
}
