package Package;

import java.math.BigInteger;

public class EK_message {
	private BigInteger MSG;
	private BigInteger sign;
	private BigInteger H_MSG;
	public void setMSG(BigInteger msg){
		this.MSG=msg;
	}
	public BigInteger getMSG(){
		return this.MSG ;
	}
	public void setSIGN(BigInteger sign){
		this.sign=sign;
	}
	public BigInteger getSIGN(){
		return this.sign ;
	}
	public void setH_MSG(BigInteger h_msg){
		this.H_MSG=h_msg;
	}
	public BigInteger getH_MSG(){
		return this.H_MSG ;
	}
}