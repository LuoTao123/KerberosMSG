package RSA;

import java.math.BigInteger;
import java.util.Vector;

public class GetPAndQAndEAndD {
	private int LengthofKey;
	public BigInteger Presult;
	public BigInteger Qresult;
	public BigInteger Eresult;
	public BigInteger Dresult;
	public BigInteger Nresult;
	
	public BigInteger turn;
	public BigInteger conclu;
	public BigInteger Tempclu;
	public BigInteger Lastconclu;
	
	public GetPAndQAndEAndD(int L){
		LengthofKey=L;
		Presult=new BigInteger("0");
		Qresult=new BigInteger("0");
		Eresult=new BigInteger("65537");
		Dresult=new BigInteger("0");
		Nresult=new BigInteger("0");
		turn=new BigInteger("0");
		conclu=new BigInteger("2");
		Lastconclu=new BigInteger("1");
		Tempclu=new BigInteger("0");
	}
	
	public BigInteger GetP(){
		return Presult;
	}
	
	public BigInteger GetQ(){
		return Qresult;
	}
	
	public BigInteger GetE(){
		return Eresult;
	}
	
	public BigInteger GetD(){
		return Dresult;
	}
	
	public BigInteger GetN(){
		return Nresult;
	}
	
	public BigInteger gcd(BigInteger a,BigInteger b){
		BigInteger Two=new BigInteger("2");
		if(a.equals(b)){
			return a;
		}
		if(a.equals(BigInteger.ZERO)){
			return b;
		}
		if(b.equals(BigInteger.ZERO)){
			return a;
		}
		if(a.remainder(Two).equals(BigInteger.ZERO)&&b.remainder(Two).equals(BigInteger.ZERO)){
			return Two;
		}
		else if(a.remainder(Two).equals(BigInteger.ZERO)){
			return gcd(a.shiftRight(1),b);
		}else if(b.remainder(Two).equals(BigInteger.ZERO)){
			return gcd(a,b.shiftRight(1));
		}else{
			if(a.compareTo(b)>0){
				return gcd(a.subtract(b),b);
			}else{
				return gcd(b.subtract(a),a);
			}
		}
	}
	
	public void inverse(BigInteger a,BigInteger b){
		if(!b.mod(a).equals(BigInteger.ZERO)){
			turn=turn.add(BigInteger.ONE);
			inverse(b.mod(a),a);
			if(a.mod(b.mod(a)).equals(BigInteger.ZERO)){
				conclu=b.divide(a);
				Lastconclu=new BigInteger("1");
				return;
			}
			Tempclu=conclu;
			conclu=conclu.multiply(b.divide(a)).add(Lastconclu);
			Lastconclu=Tempclu;
		}
	}
	
	public void getPAndQAndEAndD(){
		Miller_Rabin MR=new Miller_Rabin();
		GetBigInteger GBI=new GetBigInteger();
		BigInteger BI=new BigInteger("0");
		Vector<BigInteger> VBI=new Vector<BigInteger>();
		while(true){
			BI=GBI.getBigInteger(LengthofKey);
			if(MR.MillerRabin(BI)){
				VBI.add(BI);
				break;
			}
		}
		BigInteger n=new BigInteger("0");
		BigInteger p=new BigInteger("0");
		BigInteger p1=new BigInteger("0");
		BigInteger q=new BigInteger("0");
		BigInteger q1=new BigInteger("0");
		BigInteger Two=new BigInteger("2");
		BigInteger phin=new BigInteger("0");
		BigInteger e=new BigInteger("65537");
		BigInteger d=new BigInteger("0");
		while(true){
			BI=new BigInteger("0");
			while(true){
				BI=GBI.getBigInteger(LengthofKey);
				if(MR.MillerRabin(BI)){
					break;
				}
			}
			q=BI;
			for(int i=0;i<VBI.size();i++){
				p=VBI.get(i);
				n=p.multiply(q);
				if(n.bitLength()==LengthofKey){	
					continue;
				}
				if(p.compareTo(q)>0){
					if(p.subtract(q).bitLength()<(n.bitLength()/3)||p.subtract(q).bitLength()<(n.bitLength()/2-100)){	
						continue;
					}
				}else if(p.compareTo(q)<0){									
					if(q.subtract(p).bitLength()<(n.bitLength()/3)||q.subtract(p).bitLength()<(n.bitLength()/2-100)){
						continue;
					}
				}else{
					continue;
				}
				p1=p.subtract(BigInteger.ONE).divide(Two);					//p1=(p-1)/2
				q1=q.subtract(BigInteger.ONE).divide(Two);					//q1=(q-1)/2
				if(MR.MillerRabin(p1)||MR.MillerRabin(q1)){					//检测p1和q1是否都是素数
					continue;
				}
				phin=p1.multiply(q1).multiply(new BigInteger("4"));			//φ(n)=p1*q1
				if(phin.compareTo(e)<0){									//0<e<φ(n)
					continue;
				}
				if(!gcd(phin,e).equals(BigInteger.ONE)){					//看φ与e是否互质
					continue;
				}
				turn=new BigInteger("0");
				inverse(e,phin);											//模反得到d
				if(!turn.mod(Two).equals(BigInteger.ZERO)){					//看几轮，单轮就是模减去得到的数
					d=phin.subtract(conclu);
				}
				else{
					d=conclu;
				}
				if(d.multiply(d).multiply(d).multiply(d).compareTo(n)>0){
					Presult=p;
					Qresult=q;
					Eresult=e;
					Dresult=d;
					Nresult=p.multiply(q);
					return;
				}
			}
			VBI.add(q);
		}
	}
}
