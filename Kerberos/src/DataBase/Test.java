package DataBase;
import java.net.UnknownHostException;

import Package.Pack;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pack pack = new Pack();
		int ADc = 0;
		try {
			ADc = pack.ipStringToInts("127.0.0.1");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ADc);
		byte[] ADcByte = pack.IntToByteArray2(ADc);
		int NAD = pack.ByteArrayToInt2(ADcByte);
		System.out.println(ADcByte);
		String ADcString = pack.ipIntsToString(NAD);
		System.out.println(ADcString);
	}
}
