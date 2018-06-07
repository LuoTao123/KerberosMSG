package RSA;

//import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Encryption {
	private BigInteger ManagePublicKey;
	private BigInteger ManageMod;
	private BigInteger PublicKey;
	private BigInteger Mod;
	private BigInteger Text;
	private String Manager="Manager";
	private String OriginPK;
	private String OriginPM;
//	private String OriginText;
//	private int Hash;
	public boolean HasCre = false;
	public boolean Truecre = false;
	public boolean IsWork = false;
	public boolean IsTrue = false;
	
	public void ReadCreKeys(String name){								//读取公钥（从证书中获取），需要管理员证书签名验证后转化为公钥
/*		System.out.print("请输入对方公钥文件名：");
		System.out.print("请输入对方的名字");
		@SuppressWarnings("resource")
		Scanner Sca = new Scanner(System.in);
		String filename = Sca.nextLine();
		String filename = "Cren_"+Sca.nextLine()+".txt";*/
		String filename = "Cren_"+name+".txt";
		String text;
		BigInteger Keys[]=new BigInteger[2];
		int turn=0;
		try {
			File file=new File(filename);
			if(!file.exists()){
				System.out.println("没有对方的证书！");
				System.exit(0);
			}
			//取得证书
			HasCre = true;
			FileInputStream readerStream = new FileInputStream(filename);
			BufferedReader br =new BufferedReader(new InputStreamReader(readerStream));
			while((text=br.readLine())!=null&&turn<4){
				if(turn<2){
					Keys[turn]=new BigInteger(text);
				}else if(turn==2){
					OriginPK=text;
				}else{
					OriginPM=text;
				}
				turn++;
			}
			br.close();
			readerStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.PublicKey=Keys[0];
		this.Mod=Keys[1];
	}
	
/*	public String ReadText(){								//读取加密或者解密的文件内容
		System.out.print("请输入需要解密或认证的文件名(格式：XXX.txt)：");
		@SuppressWarnings("resource")
		Scanner Sca = new Scanner(System.in);
		String filename = Sca.nextLine();
		String Origin = null;
		String text=null;
		try {
			File file=new File(filename);
			if(!file.exists()){
				System.out.println("该文件不存在！");
				System.exit(0);
			}
			FileInputStream readerStream = new FileInputStream(filename);
			BufferedReader br =new BufferedReader(new InputStreamReader(readerStream));
			if((text=br.readLine())!=null){
				System.out.println("内容："+text);
			}
			if((Origin=br.readLine())!=null){
				OriginText=Origin;
			}
			br.close();
			readerStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Hash=text.hashCode();
		return text;
	}*/
	
	public void ReadManagerPublicKeys(){
		
/*		System.out.print("请输入管理员公钥文件名：");
		@SuppressWarnings("resource")
		Scanner Sca = new Scanner(System.in);
		String filename = Sca.nextLine();*/
		
		String filename = "Pub_"+Manager+".txt";
		String text;
		BigInteger Keys[]=new BigInteger[2];
		int turn=0;
		try {
			File file=new File(filename);
			if(!file.exists()){
				System.out.println("没有管理员的公钥！");
				System.exit(0);
			}
			FileInputStream readerStream = new FileInputStream(filename);
			@SuppressWarnings("resource")
			BufferedReader br =new BufferedReader(new InputStreamReader(readerStream));
			while((text=br.readLine())!=null||turn<2){
				Keys[turn]=new BigInteger(text);
				turn++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.ManagePublicKey=Keys[0];
		this.ManageMod=Keys[1];
	}
	
	public void renzhen(String name){									//认证，转化为公钥
		ReadCreKeys(name);										//读证书的公钥
		ReadManagerPublicKeys();
/*		System.out.println(PublicKey.toString());
		System.out.println(Mod.toString());
		System.out.println(ManagePublicKey.toString());
		System.out.println(ManageMod.toString());*/
		Miller_Rabin MR=new Miller_Rabin();
		PublicKey=MR.Power(PublicKey, ManagePublicKey, ManageMod);
		Mod=MR.Power(Mod, ManagePublicKey, ManageMod);
		System.out.println(PublicKey.toString());
		System.out.println(OriginPK);
		System.out.println(Mod.toString());
		System.out.println(OriginPM);
		if(!OriginPK.equals(PublicKey.toString())||!OriginPM.equals(Mod.toString())){
			System.out.println("证书认证失败，可能因为该证书是伪造的");
			System.exit(0);
		}else{
			//证书为真
			Truecre = true;
			System.out.println("证书为真，继续执行操作！");
		}
//		System.out.println(PublicKey.toString());
//		System.out.println(Mod.toString());
	}
	
	
	
	public BigInteger encryption(BigInteger inputText,String name){
		System.out.println("??????????");
		renzhen(name);
		System.out.println("??????????");
//		String input=ReadText(filename);
/*		byte[] input = null;
		try {
			input = inputtext.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
//		Text=new BigInteger(input);
		Text = inputText;
		BigInteger Ciphertext=new BigInteger("1");
		Miller_Rabin MR=new Miller_Rabin();
		Ciphertext=MR.Power(Text, PublicKey, Mod);
		System.out.println("??????????");
		//加密或认真完成
		IsWork = true;
/*		String text=null;
		System.out.println("转化完成："+Ciphertext.toString());
		text=Ciphertext.toString();
		System.out.print("请输入存储转化完成内容的文件名(格式：XXX.txt)：");
		@SuppressWarnings("resource")
		Scanner Sca = new Scanner(System.in);
		String filename = Sca.nextLine();
		if(!CreateFile(filename)){
			System.out.println("文件已存在！");
		}
		addtext(filename,text);
		addtext(filename,String.valueOf(Hash));*/
		System.out.println("加密完成，存储完毕！");
		return Ciphertext;
	}
	
	public String authenticate(BigInteger inputtext,String name){
		renzhen(name);
//		String input=ReadText(filename);
/*		byte[] input = null;
		try {
			input = inputtext.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		Text=inputtext;
		BigInteger Ciphertext=new BigInteger("1");
		Miller_Rabin MR=new Miller_Rabin();
		Ciphertext=MR.Power(Text, PublicKey, Mod);
		IsWork = true;
		String text=null;
		System.out.println("验证完成："+Ciphertext.toString());
		text=Ciphertext.toString();
/*		System.out.print("请输入存储转化完成内容的文件名(格式：XXX.txt)：");
		@SuppressWarnings("resource")
		Scanner Sca = new Scanner(System.in);
		String filename = Sca.nextLine();
		if(!CreateFile(filename)){
			System.out.println("文件已存在！");
		}
		addtext(filename,text);
		System.out.println("加密完成，存储完毕！");*/
		byte[] textByte = Ciphertext.toByteArray();
		String Ciphertxt = null;
		try {
			Ciphertxt = new String(textByte,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Ciphertxt;
	}
	
	public boolean CreateFile(String filename){
		boolean flag=false;
		File file=new File(filename);
		if(!file.exists()){
			try {
				file.createNewFile();
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
}
