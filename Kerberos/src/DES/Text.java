package DES;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Text {
	//RSA
	public BigInteger StringToBigInteger(String text){
		byte[] TextByte = null;
		try {
			TextByte = text.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigInteger BigText = new BigInteger(TextByte);
		return BigText;
	}
	
	public String BigIntegerToString(BigInteger BI){
		byte[] TextByte = BI.toByteArray();
		String Text = null;
		try {
			Text = new String(TextByte,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Text;
	}
	
	public int[] BytesToInts(byte[] text){
		String texts = ByteToString(text);
		String tString = null;
		StringBuffer SB = new StringBuffer();
		for(int i =0;i<text.length;i++){
			SB.append(Integer.toBinaryString((text[i] & 0xFF) + 0x100).substring(1)); 
		}
		tString = SB.toString();
		System.out.println(tString);
		int[] TextInts = new int[tString.length()];
		for(int i=0;i<tString.length();i++){
			TextInts[i]=Integer.parseInt(tString.substring(i,i+1));
		}
		return TextInts;
	}
	
	public byte[] IntsToBytes(int[] text){
		byte[] newByte = new byte[text.length/8];
		int n = 0;
		for(int i = 0;i<text.length/8;i++){
			for(int j = 0,k = 256;j<8;j++){
				k = k/2;
				n = n+k*text[i*8+j];
			}
			newByte[i] = (byte)n;
			n = 0;
		}
		return newByte;
	}
	
	public String ByteToString(byte [] b){  
        StringBuffer result = new StringBuffer();  
        for(int i = 0;i<b.length;i++){  
            result.append(Long.toString(b[i] & 0xff, 2));  
        }  
        return result.toString().substring(0, result.length()-1);  
    }
	
	public byte[] DESSupreier(int mode,byte[] Text,int[] Keys){
		int[] TextInts = BytesToInts(Text);
		int[] ChangedTextInts = new int[TextInts.length];
		int[] Ints64bit = new int[64];
		int[] ChangedInts64bit = new int[64];
		int turn = TextInts.length/64;
		if(TextInts.length%64==0){
			for(int i = 0;i<turn;i++){
				System.arraycopy(TextInts, i*64, Ints64bit, 0, Ints64bit.length);
				DES des = new DES();
				ChangedInts64bit = des.ModeChoose(mode, Ints64bit, Keys);
				System.arraycopy(ChangedInts64bit, 0, ChangedTextInts, i*64, ChangedInts64bit.length);
			}
		}else{
			for(int i = 0;i<turn;i++){
				System.arraycopy(TextInts, i*64, Ints64bit, 0, Ints64bit.length);
				DES des = new DES();
				ChangedInts64bit = des.ModeChoose(mode, Ints64bit, Keys);
				System.arraycopy(ChangedInts64bit, 0, ChangedTextInts, i*64, ChangedInts64bit.length);
			}
			System.out.println(turn);
			System.out.println(ChangedTextInts.length-64*turn);
			System.arraycopy(TextInts, turn*64, ChangedTextInts, turn*64, ChangedTextInts.length-64*turn);
		}
		byte[] ChangedByte = IntsToBytes(ChangedTextInts);
		return ChangedByte;
	}
}
