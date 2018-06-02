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
            System.out.println(buff.length);
            Text = new BigInteger(buff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Text;
    }
	
}
