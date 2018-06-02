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
		int[] TextInts = new int[texts.length()];
		for(int i=0;i<64;i++){
			TextInts[i]=Integer.parseInt(texts.substring(i,i+1));
		}
		return TextInts;
	}
	
	public byte[] IntsToBytes(int[] text){
		String[] texts = new String[8];
		for(int i = 0;i<texts.length;i++){
			StringBuffer SB = new StringBuffer();
			for(int j = 0;j<texts.length;j++){
				SB.append(text[i*8+j]);
			}
			texts[i] = SB.toString();
		}
		byte[] TextByte = new byte[texts.length];  
        for (int i = 0; i < TextByte.length; i++) {  
        	//将二进制字符串转化为Byte
        	TextByte[i] = Byte.parseByte(texts[i],2);  
        }  
		return TextByte;
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
		DES des = new DES();
		if(TextInts.length%64==0){
			for(int i = 0;i<turn;i++){
				System.arraycopy(TextInts, i*64, Ints64bit, 0, Ints64bit.length);
				ChangedInts64bit = des.ModeChoose(mode, Ints64bit, Keys);
				System.arraycopy(ChangedInts64bit, 0, ChangedTextInts, i*64, ChangedInts64bit.length);
			}
		}else{
			if(mode==0){
				for(int i = 0;i<turn;i++){
					System.arraycopy(TextInts, i*64, Ints64bit, 0, Ints64bit.length);
					ChangedInts64bit = des.ModeChoose(mode, Ints64bit, Keys);
					System.arraycopy(ChangedInts64bit, 0, ChangedTextInts, i*64, ChangedInts64bit.length);
				}
				System.arraycopy(TextInts, TextInts.length-64, Ints64bit, 0, Ints64bit.length);
				ChangedInts64bit = des.ModeChoose(mode, Ints64bit, Keys);
				System.arraycopy(ChangedInts64bit, 0, ChangedTextInts, ChangedTextInts.length-64, ChangedInts64bit.length);
			}else{
				System.arraycopy(TextInts, TextInts.length-64, Ints64bit, 0, Ints64bit.length);
				ChangedInts64bit = des.ModeChoose(mode, Ints64bit, Keys);
				System.arraycopy(ChangedInts64bit, 0, ChangedTextInts, ChangedTextInts.length-64, ChangedInts64bit.length);
				for(int i = 0;i<turn;i++){
					System.arraycopy(TextInts, i*64, Ints64bit, 0, Ints64bit.length);
					ChangedInts64bit = des.ModeChoose(mode, Ints64bit, Keys);
					System.arraycopy(ChangedInts64bit, 0, ChangedTextInts, i*64, ChangedInts64bit.length);
				}
			}
		}
		byte[] ChangedByte = IntsToBytes(ChangedTextInts);
		return ChangedByte;
	}
}
