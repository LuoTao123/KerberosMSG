package Package;

import java.math.BigInteger;

public class Data_Regist {
	private int IDc;
	private BigInteger rsa_hash_Password;
	public void setIDc(int idc){
		this.IDc=idc;
	}
	
	public int getIDc(){
		return this.IDc;
	}
	public void setRSA_HASH_PASSWORD(BigInteger RHP){
		this.rsa_hash_Password=RHP;
	}
	public BigInteger getRSA_HASH_PASSWORD(){
		return this.rsa_hash_Password ;
	}
}
