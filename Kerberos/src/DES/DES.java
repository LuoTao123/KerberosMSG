package DES;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.Random;

public class DES {
	public int[][] AllsubKey;
	public int turn;
	public String InputPlaintxt;							//����������ַ���
	public String InputCyphertxt;
	public String AllKey;
	public int[] All64Key;
	public int[] All56Key;
	public int[] Plaintext;									//���Ķ�ά����
	public int[] Changed64txt;
	public int[] Ciphertext;
	public int[] Left32Pltxt;
	public int[] Right32Pltxt;
	public int[] Temp32Pltxt;
	public int[] Bit48Pltxt;
	public int[] Left28Key;
	public int[] Right28Key;
	public int[] Bit48Key;
	public int[] XOR48Out;
	public int[] Sbox32Out;
	public int[] Pbox32Out; 
	public int Sbox[][][]={{{14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},
							{0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},
							{4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},
							{15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}},
							
							{{15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},
								{3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},
								{0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},
								{13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}},
							
							{{10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},
								{13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},
								{13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},
								{1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}},
							
							{{7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},
								{13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},
								{10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},
								{3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}},
							
							{{2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},
								{14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},
								{4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},
								{11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}},
							
							{{12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},
								{10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},
								{9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},
								{4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}},
							
							{{4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},
								{13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},
								{1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},
								{6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}},
							
							{{13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},
								{1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},
								{7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},
								{2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}}};
							
	public int InitPlaintxt[]={58,50,42,34,26,18,10,2,
								60,52,44,36,28,20,12,4,
								62,54,46,38,30,22,14,6,
								64,56,48,40,32,24,16,8,
								57,49,41,33,25,17,9,1,
								59,51,43,35,27,19,11,3,
								61,53,45,37,29,21,13,5,
								63,55,47,39,31,23,15,7};
	
	public int InitKeys_change[]={57,49,41,33,25,17,9,1,58,50,42,34,26,18,
									10,2,59,51,43,35,27,19,11,3,60,52,44,36,
									63,55,47,39,31,23,15,7,62,54,46,38,30,22,
									14,6,61,53,45,37,29,21,13,5,28,20,12,4};
	
	public int Left_move[]={1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
	
	public int ChangeCompress[]={14,17,11,24,1,5,3,28,15,6,21,10,
								23,19,12,4,26,8,16,7,27,20,13,2,
								41,52,31,37,47,55,30,40,51,45,33,48,
								44,49,39,56,34,53,46,42,50,36,29,32};
	
	public int Expansion[]={32,1,2,3,4,5,
							4,5,6,7,8,9,
							8,9,10,11,12,13,
							12,13,14,15,16,17,
							16,17,18,19,20,21,
							20,21,22,23,24,25,
							24,25,26,27,28,29,
							28,29,30,31,32,1};
	
	public int Pbox[]={16,7,20,21,29,12,28,17,
						1,15,23,26,5,18,31,10,
						2,8,24,14,32,27,3,9,
						19,13,30,6,22,11,4,25};
	
	public int NiInitPlaintxt[]={40,8,48,16,56,24,64,32,
								39,7,47,15,55,23,63,31,
								38,6,46,14,54,22,62,30,
								37,5,45,13,53,21,61,29,
								36,4,44,12,52,20,60,28,
								35,3,43,11,51,19,59,27,
								34,2,42,10,50,18,58,26,
								33,1,41,9,49,17,57,25};
	
	public DES(){
		AllsubKey=new int[16][48];
		turn=0;
		Plaintext=new int[64];
		Changed64txt=new int[64];
		Ciphertext=new int[64];
		All64Key=new int[64];
		All56Key=new int[56];
		Left32Pltxt=new int[32];
		Right32Pltxt=new int[32];
		Temp32Pltxt=new int[32];
		Bit48Pltxt=new int[48];
		Left28Key=new int[28];
		Right28Key=new int[28];
		Bit48Key=new int[48];
		XOR48Out=new int[48];
		Sbox32Out=new int[32];
		Pbox32Out=new int[32];
	}
	
	public void inputPltxt(int[] InputPlaintext){
/*		System.out.print("���������ģ�64λ2���ƣ���");
		Scanner Sca = new Scanner(System.in);
		InputPlaintxt = Sca.nextLine();*/
		if(InputPlaintext.length!=64){
			System.out.println("���������λ������64λ������������");
			System.exit(0);
		}
		for(int i=0;i<64;i++){
			Plaintext[i]=InputPlaintext[i];
			if(Plaintext[i]!=0&&Plaintext[i]!=1){
				System.out.println("�����"+i+"λ��Ϊ0��1������������");
				System.exit(0);
			}
		}
	}
	
	public void inputKey(int[] AllKeys){
/*		FileReader fr;
		try {
			fr = new FileReader("Key.txt");
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);
			if((AllKey=br.readLine())==null){
				System.out.println("Key.txtû��Կ�ף�����������");
				System.exit(0);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		if(AllKeys.length!=64){
			System.out.println("���������λ��������64λ������������");
			System.exit(0);
		}
		for(int i=0;i<64;i++){
			All64Key[i]=AllKeys[i];
			if(All64Key[i]!=0&&All64Key[i]!=1){
				System.out.println("�����"+i+"λ��Ϊ0��1������������");
				System.exit(0);
			}
		}
	}

	public void initPltxt(){
		for(int i=0;i<64;i++){										//�û�
			Changed64txt[i]=Plaintext[InitPlaintxt[i]-1];
		}
		for(int i=0;i<32;i++){										//�ֿ�
			Left32Pltxt[i]=Changed64txt[i];
			Right32Pltxt[i]=Changed64txt[i+32];
		}
	}
	
	public void initKey(){
		for(int i=0;i<56;i++){
			All56Key[i]=All64Key[InitKeys_change[i]-1];
		}
		for(int i=0;i<28;i++){
			Left28Key[i]=All56Key[i];
			Right28Key[i]=All56Key[i+28];
		}
	}

	public void Leftmoving(){
		int mov_pos=Left_move[turn];							//�ж�������1�λ���2��
		int temp;
		for(int i=0;i<mov_pos;i++){
			temp=Left28Key[0];
			for(int j=0;j<27;j++){
				Left28Key[j]=Left28Key[j+1];
			}
			Left28Key[27]=temp;
			temp=Right28Key[0];
			for(int j=0;j<27;j++){
				Right28Key[j]=Right28Key[j+1];
			}
			Right28Key[27]=temp;
		}
	}
	
	public void ChangeAndCompress(){
		int temp[]=new int[56];
		for(int i=0;i<28;i++){									//�ϲ�
			temp[i]=Left28Key[i];
			temp[i+28]=Right28Key[i];
		}
		for(int i=0;i<48;i++){									//�û�ѹ��
			Bit48Key[i]=temp[ChangeCompress[i]-1];
		}
	}
	
	public void ExpansionAndChange(){
		copy(Right32Pltxt,Temp32Pltxt);							//���ݸ��м���
		for(int i=0;i<48;i++){
			Bit48Pltxt[i]=Right32Pltxt[Expansion[i]-1];
		}
	}
	
	public void copy(int[] a,int[] b){
		for(int i=0;i<a.length;i++){
			b[i]=a[i];
		}
	}
	
	public void XOR48(){
		for(int i=0;i<48;i++){
			if(Bit48Key[i]==Bit48Pltxt[i]){
				XOR48Out[i]=0;
			}
			else{
				XOR48Out[i]=1;
			}
		}
	}
	
	public void ChooseAndChange(){
		int[][] Matrix=new int[8][6];
		int row,column,bit;
		for(int i=0;i<8;i++){
			for(int j=0;j<6;j++){
				Matrix[i][j]=XOR48Out[i*6+j];
			}
		}
		for(int i=0;i<8;i++){
			row=Matrix[i][0]*2+Matrix[i][5];
			column=Matrix[i][1]*8+Matrix[i][2]*4+Matrix[i][3]*2+Matrix[i][4];
			bit=Sbox[i][row][column];
			for(int j=3;j>=0;j--){
				if(bit>0){
					Sbox32Out[i*4+j]=bit%2;
					bit=bit/2;
				}else{
					Sbox32Out[i*4+j]=0;
				}
			}
		}
	}
	
	public void Change(){
		for(int i=0;i<32;i++){
			Pbox32Out[i]=Sbox32Out[Pbox[i]-1];
		}
	}
	
	public void XOR32(){
		for(int i=0;i<32;i++){
			if(Pbox32Out[i]==Left32Pltxt[i]){
				Right32Pltxt[i]=0;
			}
			else{
				Right32Pltxt[i]=1;
			}
		}
		copy(Temp32Pltxt,Left32Pltxt);
	}
	
	public void getCiphertxt(){
		for(int i=0;i<32;i++){
			Ciphertext[i+32]=Left32Pltxt[i];
			Ciphertext[i]=Right32Pltxt[i];
		}
	}
	
	public void translate(int[] InputPlaintext,int[] AllKeys){
		inputPltxt(InputPlaintext);
		inputKey(AllKeys);
		initPltxt();
		initKey();
		while(turn<16){
			Leftmoving();
			ChangeAndCompress();
			ExpansionAndChange();
			XOR48();
			ChooseAndChange();
			Change();
			XOR32();
			turn++;
		}
		getCiphertxt();
	}
	
	public void getsubKey(){
		copy(Bit48Key,AllsubKey[turn]);
	}
	
	public void inputCytxt(int[] InputCyphertext){
/*		FileReader fr;
		try {
			fr = new FileReader("Ciphertext.txt");
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);
			if((InputCyphertxt=br.readLine())==null){
				System.out.println("Ciphertext.txtû�����ģ�����������");
				System.exit(0);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		if(InputCyphertext.length!=64){
			System.out.println("���������λ��������64λ������������");
			System.exit(0);
		}
		System.out.print("�����ǣ�");
		for(int i=0;i<64;i++){
			Ciphertext[i]=InputCyphertext[i];
			if(Ciphertext[i]!=0&&Ciphertext[i]!=1){
				System.out.println("�����"+i+"λ��Ϊ0��1����������");
				System.exit(0);
			}else{
				System.out.print(Ciphertext[i]);
			}
		}
		System.out.println("");
	}
	
	public void initCytxt(){
		for(int i=0;i<32;i++){										//�ֿ�
			Left32Pltxt[i]=Ciphertext[i];
			Right32Pltxt[i]=Ciphertext[i+32];
		}
	}
	
	public void getPlaintxt(){
		for(int i=0;i<32;i++){
			Changed64txt[i+32]=Left32Pltxt[i];						//���һ�ν���
			Changed64txt[i]=Right32Pltxt[i];
		}
		for(int i=0;i<64;i++){										//�û�
			Plaintext[i]=Changed64txt[NiInitPlaintxt[i]-1];
		}
	}
	
	public void translate1(int[] InputPlaintext,int[] AllKeys){
		inputKey(AllKeys);
		initKey();
		while(turn<16){
			Leftmoving();
			ChangeAndCompress();
			getsubKey();
			turn++;
		}
		turn--;
		inputCytxt(InputPlaintext);
		initCytxt();
		while(turn>=0){
			Bit48Key=AllsubKey[turn];
			ExpansionAndChange();
			XOR48();
			ChooseAndChange();
			Change();
			XOR32();
			turn--;
		}
		getPlaintxt();
	}
	
	public void output(){
		System.out.print("�����ǣ�");
		for(int i=0;i<64;i++){
			System.out.print(Ciphertext[i]);
		}
		System.out.println("");
	}
	
	public void writeCiphertxt(){
		InputCyphertxt="";
		for(int i=0;i<64;i++){
			InputCyphertxt+=Ciphertext[i];
		}
		try {  
			FileWriter fw = new FileWriter("Ciphertext.txt");  
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(InputCyphertxt);  
			bw.close();  
			fw.close();  
		} catch (Exception e) {  
          // TODO Auto-generated catch block  
			e.printStackTrace();  
		}   
	}
	
	public void print(int[] a){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]);
		}
		System.out.println("");
	}
	
	public int[] ModeChoose(int mode,int[] InputPlaintext,int[] AllKeys){
/*		System.out.print("������ģʽ��0Ϊ���ܣ�1Ϊ���ܣ���");
		@SuppressWarnings("resource")
		Scanner Sca = new Scanner(System.in);
		InputPlaintxt = Sca.nextLine();
		mode=Integer.parseInt(InputPlaintxt);*/
		if(mode==0){
			translate(InputPlaintext,AllKeys);
//			output();
//			writeCiphertxt();
			return Ciphertext;
		}
		else{
			translate1(InputPlaintext,AllKeys);
//			output1();
			return Plaintext;
		}
	}
	
	public void output1(){
		System.out.print("�����ǣ�");
		for(int i=0;i<64;i++){
			System.out.print(Plaintext[i]);
		}
		System.out.println("");
	}
	
	public BigInteger CreateDESKey(){
		Random rand = new Random();
		BigInteger bigInt=new BigInteger(64, rand);
		return bigInt;
	}
}
