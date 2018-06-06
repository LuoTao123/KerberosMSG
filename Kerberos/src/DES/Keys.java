package DES;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Keys {
	public int[] ReadKeysFromFile(String filename){		//从文件里读取DES密钥，返回int数组
		String AllKey = null;
		int[] All64Key = new int[64];
		FileReader fr;
		try {
			fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			if((AllKey=br.readLine())==null){
				System.out.println("Key.txt没有钥匙，请加入后重试");
				System.exit(0);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(AllKey.length()<64){
			System.out.println("输入二进制位数少于64位，请重新输入");
			System.exit(0);
		}
		for(int i=0;i<64;i++){
			All64Key[i]=Integer.parseInt(AllKey.substring(i,i+1));
			if(All64Key[i]!=0&&All64Key[i]!=1){
				System.out.println("输入的"+i+"位不为0或1，请重新输入");
				System.exit(0);
			}
		}
		return All64Key;
	}
	
	public void SaveKeyToFile(String filename,String Keys){	//写DES的Keys到文件里去
		try {  
			FileWriter fw = new FileWriter(filename,false);  
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(Keys);  
			bw.close();  
			fw.close();  
		} catch (Exception e) {  
          // TODO Auto-generated catch block  
			e.printStackTrace();  
		}
		System.out.println("保存密码成功！");
	}
	
	public void SaveKeyToFile(String filename,BigInteger Keys){
		String Key = BigIntegerToString(Keys);
		SaveKeyToFile(filename,Key);
	}
	
	public String BigIntegerToString(BigInteger BI){				//直接的BigInteger转String
		String str = "";
		BigInteger BI1 = null;
		BigInteger TWO = new BigInteger("2");
		while(true){
			BI1 = BI.divideAndRemainder(TWO)[0];
			str = BI1.toString()+str;
			if(BI1.equals(BigInteger.ZERO)){
				break;
			}
		}
		return str;
	}
	
	public int[] StringToInts(String str) {
		int[] All64Key = new int[64];
		for(int i=0;i<64;i++){
			All64Key[i]=Integer.parseInt(str.substring(i,i+1));
			if(All64Key[i]!=0&&All64Key[i]!=1){
				System.out.println("输入的"+i+"位不为0或1，请重新输入");
				System.exit(0);
			}
		}
		return All64Key;
	}
	
	public String ByteToString(byte [] b)  
    {  
        StringBuffer result = new StringBuffer();  
        for(int i = 0;i<b.length;i++)  
        {  
            result.append(Long.toString(b[i] & 0xff, 2));  
        }  
        return result.toString().substring(0, result.length()-1);  
    }  
}
