package RSA;

import java.math.BigInteger;
import java.util.Random;

public class GetBigInteger {
	public BigInteger bigInt;
	public Random rand;
	public GetBigInteger(){
		bigInt=new BigInteger("0");
	}
	
	public BigInteger getBigInteger(int length){
		Random rand = new Random();
		bigInt=new BigInteger(length/2+1, rand);
		return bigInt;
	}
}
