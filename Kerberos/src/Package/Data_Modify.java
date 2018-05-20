package Package;

import java.math.BigInteger;

public class Data_Modify {
	private int IDc;
	private BigInteger rsa_hash_Password;
	private BigInteger rsa_hash_NPassword;
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
	public void setRSA_HASH_NPASSWORD(BigInteger RHNP){
		this.rsa_hash_NPassword=RHNP;
	}
	public BigInteger getRSA_HASH_NPASSWORD(){
		return this.rsa_hash_NPassword ;
	}
	
	
}
