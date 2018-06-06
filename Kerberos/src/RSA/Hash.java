package RSA;

import java.security.MessageDigest;
import java.math.BigInteger;
public class Hash {
	
	public BigInteger getMD5(String text) {
		BigInteger Text = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] input = text.getBytes();
            byte[] buff = md.digest(input);
            byte[] bytes8 = new byte[8];
            System.arraycopy(buff, 0, bytes8, 0, 8);
            bytes8[0] = (byte)0x40;
            Text = new BigInteger(bytes8);
            BigInteger Textmin = new BigInteger("9223372036854776028");
            Text = Text.add(Textmin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Text;
    }
	
}

