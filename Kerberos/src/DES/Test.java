package DES;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

import Kerberos.TimeStamp;
import Package.AS_C;
import RSA.Decryption;
import RSA.Encryption;
import RSA.Hash;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InetAddress addr = (InetAddress)InetAddress.getLocalHost();
			String ip = addr.getHostAddress().toString();
			System.out.println(ip);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
