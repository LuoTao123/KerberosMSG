package RSA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
//import java.util.Scanner;

public class Decryption {
	private BigInteger PrivateKey;
	private BigInteger Mod;
	private BigInteger Text;
	private BigInteger PublicKey;
	private BigInteger PublicMod;
	private String OriginPK;
	private String OriginPM;
//	private String OriginText;
//	private int TextHash;
//	private int Hash;
	public boolean HasKey = false;
	public boolean IsWork = false;

/*	public String Readtext(){
		System.out.print("��������Ҫ���ܻ���֤���ļ�����");
		@SuppressWarnings("resource")
		Scanner Sca = new Scanner(System.in);
		String filename = Sca.nextLine();
		String text = "";
		String Origin="";
		try {
			File file=new File(filename);
			if(!file.exists()){
				System.out.println("û�ж�Ӧ���ļ�����");
				System.exit(0);
			}
			FileInputStream readerStream = new FileInputStream(filename);
			BufferedReader br =new BufferedReader(new InputStreamReader(readerStream));
			if((text=br.readLine())!=null){
				System.out.println("���ݣ�"+text);
				OriginText=text;									//��ȡԭ��������
			}
			if((Origin=br.readLine())!=null){
				TextHash=Integer.parseInt(Origin);
			}
			br.close();
			br.close();
			readerStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
	}*/
		
	public void ReadPrivateKeys(String name){
//		System.out.print("��������Կ�ļ�����");
//		System.out.print("�������Լ�������");
//		@SuppressWarnings("resource")
//		Scanner Sca = new Scanner(System.in);
//		String filename = Sca.nextLine();
//		String filename = "Pri_"+Sca.nextLine()+".txt";
		String filename = "Pri_"+name+".txt";
		String text;
		BigInteger Keys[]=new BigInteger[2];
		int turn=0;
		try {
			File file=new File(filename);
			if(!file.exists()){
				System.out.println("û���Լ���˽Կ��");
				System.exit(0);
			}
			FileInputStream readerStream = new FileInputStream(filename);
			BufferedReader br =new BufferedReader(new InputStreamReader(readerStream));
			while((text=br.readLine())!=null||turn<2){
				Keys[turn]=new BigInteger(text);
				turn++;
			}
			br.close();
			readerStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HasKey = true;
		this.PrivateKey=Keys[0];
		this.Mod=Keys[1];
	}
	
	public void ReadPublicKeys(String User){
		String filename = "Pub_"+User+".txt";
		String text;
		BigInteger Keys[]=new BigInteger[2];
		int turn=0;
		try {
			File file=new File(filename);
			if(!file.exists()){
				System.out.println("û��"+User+"�Ĺ�Կ��");
				System.exit(0);
			}
			FileInputStream readerStream = new FileInputStream(filename);
			BufferedReader br =new BufferedReader(new InputStreamReader(readerStream));
			while((text=br.readLine())!=null||turn<2){
				Keys[turn]=new BigInteger(text);
				turn++;
			}
			br.close();
			readerStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.PrivateKey=Keys[0];
		this.Mod=Keys[1];
	}
	
	public void ReadManagerKeys(){
		String filename = "Pri_Manager.txt";
		String text;
		BigInteger Keys[]=new BigInteger[2];
		int turn=0;
		try {
			File file=new File(filename);
			if(!file.exists()){
				System.out.println("û�й���Ա˽Կ��");
				System.exit(0);
			}
			FileInputStream readerStream = new FileInputStream(filename);
			BufferedReader br =new BufferedReader(new InputStreamReader(readerStream));
			while((text=br.readLine())!=null||turn<2){
				Keys[turn]=new BigInteger(text);
				turn++;
			}
			br.close();
			readerStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.PrivateKey=Keys[0];
		this.Mod=Keys[1];
	}
	
	public void decryCre(String User){
		ReadPublicKeys(User);
		this.PublicKey=this.PrivateKey;
		this.PublicMod=this.Mod;
		OriginPK=PublicKey.toString();
		OriginPM=PublicMod.toString();
		ReadManagerKeys();
	
/*		System.out.println(this.PublicKey.toString());
		System.out.println(this.PublicMod.toString());
		System.out.println(this.PrivateKey.toString());
		System.out.println(this.Mod.toString());*/
		
		Miller_Rabin MR=new Miller_Rabin();
		this.PublicKey=MR.Power(PublicKey, PrivateKey, Mod);
		this.PublicMod=MR.Power(PublicMod, PrivateKey, Mod);
		
		System.out.println("ת����ɣ�");
/*		System.out.println("ת�����PublicKey��"+PublicKey.toString());
		System.out.println("ת�����PublicMod��"+PublicMod.toString());
		System.out.print("������洢֤����ļ���(��ʽ��XXX.txt)��");
		@SuppressWarnings("resource")
		Scanner Sca = new Scanner(System.in);*/
		String filename = "Cren_"+User+".txt";
		if(!CreateFile(filename)){
			System.out.println("�ļ��Ѵ��ڣ�");
			System.exit(0);
		}
		addtext(filename,PublicKey.toString());
		addtext(filename,PublicMod.toString());
		addtext(filename,OriginPK);
		addtext(filename,OriginPM);
		addtext(filename,User);
		System.out.println("֤����ɣ��洢��ϣ�");
	}
	
	public BigInteger decryption(BigInteger text,String name){
//		String input=Readtext();
//		this.Text=new BigInteger(input);
		this.Text = text;
		ReadPrivateKeys(name);
		BigInteger Plaintext=new BigInteger("1");
		Miller_Rabin MR=new Miller_Rabin();
/*		System.out.println(this.Text.toString());
		System.out.println(this.PrivateKey.toString());
		System.out.println(this.Mod.toString());*/
		Plaintext=MR.Power(this.Text,this.PrivateKey, this.Mod);
		IsWork = true;
/*		String text=null;
		System.out.println("ת����"+Plaintext.toString());
		text=Plaintext.toString();
		Hash=text.hashCode();
		if(Hash!=TextHash){
			System.out.println("����Hash���������ļ�����������ֹͣ�������Ĳ���");
			System.exit(0);
		}else{
			System.out.println("����Hash����,���������ԣ�");
		}
		System.out.print("������ת�����ļ����ļ�����");
		@SuppressWarnings("resource")
		Scanner Sca = new Scanner(System.in);
		String filename = Sca.nextLine();
		if(!CreateFile(filename)){
			System.out.println("�ļ��Ѵ��ڣ�");
			System.exit(0);
		}
		addtext(filename,text);*/
		System.out.println("������ɣ��洢��ϣ�");
		return Plaintext;
	}
	
	public BigInteger sign(BigInteger text,String name){
//		String input=Readtext();
//		this.Text=new BigInteger(input);
		this.Text = text;
		ReadPrivateKeys(name);
		BigInteger Plaintext=new BigInteger("1");
		Miller_Rabin MR=new Miller_Rabin();
/*		System.out.println(this.Text.toString());
		System.out.println(this.PrivateKey.toString());
		System.out.println(this.Mod.toString());*/
		Plaintext=MR.Power(this.Text,this.PrivateKey, this.Mod);
		IsWork = true;
/*		String text=null;
		System.out.println("ǩ����"+Plaintext.toString());
		text=Plaintext.toString();
		System.out.print("������ǩ�����ļ����ļ�����");
		@SuppressWarnings("resource")
		Scanner Sca = new Scanner(System.in);
		String filename = Sca.nextLine();
		if(!CreateFile(filename)){
			System.out.println("�ļ��Ѵ��ڣ�");
			System.exit(0);
		}
		addtext(filename,text);
		addtext(filename,OriginText);*/
		System.out.println("ǩ����ɣ��洢��ϣ�");
		return Plaintext;
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
