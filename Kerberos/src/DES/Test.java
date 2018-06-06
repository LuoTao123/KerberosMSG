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
		Text text = new Text();
		Keys key = new Keys();
		String str = "????111331128798178asdfhjksdhfjdnskjfh3";
		byte[] strByte = null;
		try {
			strByte = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(strByte.length);
		for(int i = 0;i<strByte.length;i++){
			System.out.print(strByte[i]+" ");
		}
		System.out.println();
		int[] Keys = key.ReadKeysFromFile("Keytgs.txt");
		byte[] Changed = text.DESSupreier(0, strByte, Keys);
		byte[] Origin = text.DESSupreier(1, Changed, Keys);
		String nstr = null;
		try {
			nstr = new String(Origin,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(nstr.equals(str)){
			System.out.println("À÷º¦ÁË");
		}else{
			System.out.println("²ÝÄâÂð");
		}
		for(int i = 0;i<Origin.length;i++){
			System.out.print(Origin[i]+" ");
		}
		System.out.println();
/*		Text text = new Text();
		byte[] Byte = new byte[2];
		Byte[0] =(byte) 0x00;
		Byte[1] =(byte) 0x12;
		int[] Int = text.BytesToInts(Byte);
		for(int i = 0;i<Int.length;i++){
			System.out.print(Int[i]);
		}*/
//		DES des =new DES();
//		des.ModeChoose();
	}
}
