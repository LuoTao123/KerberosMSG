package RSA;

import java.math.BigInteger;
import java.util.Random;  

public class Miller_Rabin {
	public int turns;
	
	public Miller_Rabin(){
		turns=20;
	}
	
	public BigInteger Power(BigInteger testnum,BigInteger test,BigInteger a){	//b^test mod a
		BigInteger Two=new BigInteger("2");
		BigInteger One=new BigInteger("1");
		BigInteger Zero=new BigInteger("0");
		BigInteger result = new BigInteger("1");
		testnum=testnum.remainder(a);											//testnum%=a;
		for(;!test.equals(Zero);test=test.divide(Two)){							//for(;test!=0;test/=2)
			if(test.remainder(Two).equals(One)){								//if(test%2==1)
				result=result.multiply(testnum).remainder(a);					//result=（result*testnum）%a;
			}
			testnum=testnum.multiply(testnum).remainder(a);						//(testnum*testnum)%a;
		}
		return result;
	}
	
	public BigInteger Multiply(BigInteger result2,BigInteger result3,BigInteger a){	//a*b mod n
		BigInteger result= new BigInteger("0");
		result2.remainder(a);										//result2%=a;
		result3.remainder(a);										//result3%=a;
		result=result2.multiply(result3);							//result=result2*result3;
		result=result.remainder(a);									//result=result%a;
		return result;
	}
	
	public boolean Is_Prime(BigInteger a,BigInteger testnum){		//b检测a是否为质数
		BigInteger Two=new BigInteger("2");
		BigInteger One=new BigInteger("1");
		BigInteger Zero=new BigInteger("0");
		BigInteger test = a.subtract(One) ;
		BigInteger result= new BigInteger("0");
		int j=0;
		while(test.remainder(Two).equals(Zero)){									//while(test%2==0)
			test=test.divide(Two);													//test/=2;
			j++;
		}
		result=Power(testnum,test,a);												//b^test mod a or b^(a-1) mod a
																					//Fermat小定理
		if(result.equals(One)||result.equals(a.subtract(One))){						//if(result==1||result==a-1)
			return true;															//是质数
		}
		j--;
		while(j>0){
			result=Multiply(result,result,a);
			if(result.equals(a.subtract(One))){
				return true;
			}
			j--;
		}
		return false;
	}
	
	public boolean MillerRabin(BigInteger a){
		BigInteger Two=new BigInteger("2");
		BigInteger Zero=new BigInteger("0");
		BigInteger testnum=new BigInteger("0");
		if(a.divideAndRemainder(Two)[1].equals(Zero)){
			return false;
		}
		if(a.compareTo(Two)==-1||a.remainder(Two).equals(Zero)){
			return false;
		}
		Random rand = new Random();
		for(int i=0;i<turns;i++){
			testnum = new BigInteger(a.bitLength(), rand);
		    while( testnum.compareTo(a) >= 0 ) {
//		    	System.out.println(testnum.toString());
		        testnum = new BigInteger(a.bitLength(), rand);
		    }
//		    System.out.println(testnum.toString());
			if(!Is_Prime(a,testnum)){
				return false;
			}
		}
		return true;
	}
}
