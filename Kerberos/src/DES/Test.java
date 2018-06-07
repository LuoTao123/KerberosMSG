package DES;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import Kerberos.TimeStamp;
import Package.AS_C;
import RSA.Decryption;
import RSA.Encryption;
import RSA.Hash;

public class Test {
		// TODO Auto-generated method stub
	public static void main(String[] args)throws UnknownHostException,SocketException {
		String Message = "wqewkehksandjkashjdkhwkjensamndkljwhkldsal ";
		byte[] msgByte = null;
		try {
			msgByte = Message.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigInteger msg = new BigInteger(msgByte);
		String newSTring = msg.toString();
		byte[] newByte = null;
		try {
			newByte = newSTring.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String TransmitByte = null;
		try {
			TransmitByte = new String(newByte,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigInteger Nmsg = new BigInteger(TransmitByte);
		byte[] newbyte = Nmsg.toByteArray();
		String origin = null;
		try {
			origin = new String(newbyte,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Hash hash = new Hash();
		if(hash.getMD5(Message).equals(hash.getMD5(origin))){
			System.out.println("Yeah");
		}
		System.out.println(origin);
	}
}
