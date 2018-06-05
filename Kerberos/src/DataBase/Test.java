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
		String I1 = "5051525154485449545257544848555652574957515549525551545052505353515554575556495448525453505256574853575349545754494853545148564857535751485049495052495156555148515253535756525452505553525451565457515753525751564953565753505251565353525356485257565052545251575148555455485652535055575448554957515454495453505649515657505350554856525051485650545354555248485749525448525250515455575757575252535651574853505651545355495655504857535656545553575257485255495455495057565652545349575156485548515156545656575751574953575456504954535356565353575248555648565250485253525048525249535651575048565250555448505254545248545549495753";
		Text text = new Text();
		BigInteger BI = text.StringToBigInteger(I1);
		Encryption EN = new Encryption();
		BigInteger rsa_hash_psw = EN.encryption(BI, String.valueOf(1000000003));
		String I = rsa_hash_psw.toString();
		byte[] newbyte = null;
		String N = null;
		byte[] ChangedByte = new byte[310]; 
		try {
			newbyte = I.getBytes("UTF-8");
			System.out.println(newbyte.length);
			System.out.println(newbyte[0]);
			if(newbyte.length<310){
				for(int i = 0;i<310-newbyte.length;i++){
					ChangedByte[i] =(byte) 0x00;
				}
				System.arraycopy(newbyte, 0, ChangedByte, 310-newbyte.length, newbyte.length);
			}else{
				System.arraycopy(newbyte, 0, ChangedByte, 0, newbyte.length);
			}
			N = new String(ChangedByte,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigInteger II = new BigInteger(N);
		Decryption DE = new Decryption();
		BigInteger HASH_PASSWORD = DE.decryption(II, String.valueOf(1000000003));
		String origin = text.BigIntegerToString(HASH_PASSWORD);
		if(origin.equals(I1)){
			System.out.println("相等");
		}
		else{
			System.out.println("这事何等的卧槽");
		}
		System.out.println(origin);
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
