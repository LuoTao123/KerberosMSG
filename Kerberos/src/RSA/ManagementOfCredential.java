package RSA;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class ManagementOfCredential {
	private BigInteger PublicKey;
	private BigInteger PrivateKey;
	private BigInteger Mod;
	private String Owner;
	private String PublicKeyFilename;
	private String PrivateKeyFilename;
	private String CredentialFilename;
	private boolean flag;
	public void CreateKeys(int Length){
		if(Length==2048){
			flag=true;
		}else{
			flag=false;
		}
		GetPAndQAndEAndD PQED=new GetPAndQAndEAndD(Length);
		PQED.getPAndQAndEAndD();
		this.PublicKey=PQED.Eresult;
		this.PrivateKey=PQED.Dresult;
		this.Mod=PQED.Nresult;
/*		System.out.println(PublicKey.toString());
		System.out.println(PrivateKey.toString());
		System.out.println(Mod.toString());*/
	}
	
	public boolean CreateKeyFile(String filename){
		boolean flag=false;
		File PublicKeyFile=new File(filename);
		if(!PublicKeyFile.exists()){
			try {
				PublicKeyFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flag=true;
		}
		return flag;
	}
	
	public void addtext(String filename,String filetext){
		try {
			FileOutputStream writerStream = new FileOutputStream(filename,true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(writerStream));
			bw.write(filetext);
			bw.newLine();
			bw.close(); 
			writerStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	public void Process(int Length){
		CreateKeys(Length);
		CreatePubAndPriKey();
		CreateCredential();
	}
	
	public void CreateCredential(){
		this.CredentialFilename="Cre_"+Owner+".txt";
		if(!CreateKeyFile(CredentialFilename)){
			System.out.println("创建证书文件失败！可能已存在该名称！");
			System.exit(0);
		}
		addtext(this.CredentialFilename,this.PublicKey.toString());
		addtext(this.CredentialFilename,this.Mod.toString());
		addtext(this.CredentialFilename,this.Owner.toString());
		Decryption de=new Decryption();
		de.decryCre(Owner);
	}
	
	public void CreatePubAndPriKey(){
		if(flag==false){
			System.out.print("请输入要创建密钥的用户名：");
			@SuppressWarnings("resource")
			Scanner Sca = new Scanner(System.in);
			this.Owner = Sca.nextLine();
		}else{
			this.Owner="Manager";
		}
		this.PublicKeyFilename="Pub_"+Owner+".txt";
		this.PrivateKeyFilename="Pri_"+Owner+".txt";
		if(!CreateKeyFile(PublicKeyFilename)){
			System.out.println("创建公钥文件失败！可能已存在该名称！");
			System.exit(0);
		}
		if(!CreateKeyFile(PrivateKeyFilename)){
			System.out.println("创建私钥文件失败！可能已存在该名称！");
			System.exit(0);
		}
		addtext(this.PublicKeyFilename,this.PublicKey.toString());
		addtext(this.PublicKeyFilename,this.Mod.toString());
		addtext(this.PrivateKeyFilename,this.PrivateKey.toString());
		addtext(this.PrivateKeyFilename,this.Mod.toString());
	}
}
