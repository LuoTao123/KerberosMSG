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
	
	public void ReadCreKeys(String name){								//��ȡ��Կ����֤���л�ȡ������Ҫ����Ա֤��ǩ����֤��ת��Ϊ��Կ
/*		System.out.print("������Է���Կ�ļ�����");
		System.out.print("������Է�������");
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
				System.out.println("û�жԷ���֤�飡");
				System.exit(0);
			}
			//ȡ��֤��
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
	
/*	public String ReadText(){								//��ȡ���ܻ��߽��ܵ��ļ�����
		System.out.print("��������Ҫ���ܻ���֤���ļ���(��ʽ��XXX.txt)��");
		@SuppressWarnings("resource")
		Scanner Sca = new Scanner(System.in);
		String filename = Sca.nextLine();
		String Origin = null;
		String text=null;
		try {
			File file=new File(filename);
			if(!file.exists()){
				System.out.println("���ļ������ڣ�");
				System.exit(0);
			}
			FileInputStream readerStream = new FileInputStream(filename);
			BufferedReader br =new BufferedReader(new InputStreamReader(readerStream));
			if((text=br.readLine())!=null){
				System.out.println("���ݣ�"+text);
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
		
/*		System.out.print("���������Ա��Կ�ļ�����");
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
				System.out.println("û�й���Ա�Ĺ�Կ��");
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
	
	public void renzhen(String name){									//��֤��ת��Ϊ��Կ
		ReadCreKeys(name);										//��֤��Ĺ�Կ
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
			System.out.println("֤����֤ʧ�ܣ�������Ϊ��֤����α���");
			System.exit(0);
		}else{
			//֤��Ϊ��
			Truecre = true;
			System.out.println("֤��Ϊ�棬����ִ�в�����");
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
		//���ܻ��������
		IsWork = true;
/*		String text=null;
		System.out.println("ת����ɣ�"+Ciphertext.toString());
		text=Ciphertext.toString();
		System.out.print("������洢ת��������ݵ��ļ���(��ʽ��XXX.txt)��");
		@SuppressWarnings("resource")
		Scanner Sca = new Scanner(System.in);
		String filename = Sca.nextLine();
		if(!CreateFile(filename)){
			System.out.println("�ļ��Ѵ��ڣ�");
		}
		addtext(filename,text);
		addtext(filename,String.valueOf(Hash));*/
		System.out.println("������ɣ��洢��ϣ�");
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
		System.out.println("��֤��ɣ�"+Ciphertext.toString());
		text=Ciphertext.toString();
/*		System.out.print("������洢ת��������ݵ��ļ���(��ʽ��XXX.txt)��");
		@SuppressWarnings("resource")
		Scanner Sca = new Scanner(System.in);
		String filename = Sca.nextLine();
		if(!CreateFile(filename)){
			System.out.println("�ļ��Ѵ��ڣ�");
		}
		addtext(filename,text);
		System.out.println("������ɣ��洢��ϣ�");*/
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
