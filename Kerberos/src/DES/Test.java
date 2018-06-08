package DES;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Scanner;

import javax.xml.bind.DatatypeConverter;

import Kerberos.TimeStamp;
import Package.AS_C;
import RSA.Decryption;
import RSA.Encryption;
import RSA.Hash;

public class Test {
		// TODO Auto-generated method stub
	public static void main(String[] args)throws UnknownHostException,SocketException {
		System.out.println("输入");
		Scanner in  = new Scanner(System.in);
		
		String  Message = in.nextLine();
		int ID = 1000000003;
		String IDc  = String.valueOf(ID);
		//String Message = "wqewkehksandjkashjdkhwkjensamndkljwhkldsal ";
		byte[] msgByte = null;
		try {
			msgByte = Message.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigInteger msg = new BigInteger(msgByte);
		/////////////////////////////////////////////////////////////////对象
		Encryption En = new Encryption();
		BigInteger rsa_msg = En.encryption(msg, IDc);
		
		String newSTring = rsa_msg.toString();
		byte[] newByte = null;
		try {
			newByte = newSTring.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i= 0; i<newByte.length;i++){
			System.out.print(newByte[i]);
		}
		System.out.println();
		///////////////////////////////////////////////////////////////////////////////////////传输
		String TransmitByte = null;
		try {
			TransmitByte = new String(newByte,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/////////////////////////////////////////////////////////////////新对象
		BigInteger Nmsg = new BigInteger(TransmitByte);
		Decryption De = new Decryption();
		BigInteger De_msg = De.decryption(Nmsg, IDc);
		byte[] newbyte = De_msg.toByteArray();
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
		//13836323567808474072
		System.out.println(hash.getMD5(Message));
		System.out.println(hash.getMD5(origin));
		System.out.println(origin);
	}
}
