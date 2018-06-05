package Package;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
//import java.math.BigInteger;
import java.net.Socket;
import java.net.SocketTimeoutException;
//import java.util.Random;
import java.util.Random;

import Package.Pack;

public class SocketClient {
  public static void main(String args[]) throws Exception {
    // Ҫ���ӵķ����IP��ַ�Ͷ˿�
    String host = "127.0.0.1";
    int port = 40000;
    // �����˽�������
    Socket socket = new Socket(host, port);
    // �������Ӻ��������
    OutputStream outputStream = socket.getOutputStream();
    Pack pack =new Pack();
    socket.getOutputStream().write(pack.Pack_0x00_Cont());
    int IDC= 1234567890;
    Random rand = new Random();
    BigInteger PSW = new BigInteger(1024, rand);
    System.out.println(PSW);
    System.out.println(PSW.toByteArray().length);
    Data_Regist DR = new Data_Regist();
    DR.setIDc(IDC);
    DR.setRSA_HASH_PASSWORD(PSW);
    InputStream inputStream = socket.getInputStream();
	BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
	byte[] bytes =new byte[2];
	System.out.println("?????");
	System.out.println(bytes[0]);
	System.out.println(bytes[1]);
	bufferedInputStream.read(bytes, 0, 2);
	if(bytes[0]==(byte)0x00&&bytes[1]==(byte)0x00){
		socket.getOutputStream().write(pack.Pack_0x00_Data(DR));
	}else{
		System.out.println("���������س���");
	}
    //ͨ��shutdownOutput���ٷ������Ѿ����������ݣ�����ֻ�ܽ�������
    socket.shutdownOutput();
    inputStream.close();
    outputStream.close();
    socket.close();
  }
  public static byte[] readFixedLengthArray(BufferedInputStream serverSocketBis,int length)
	        throws SocketTimeoutException, IOException{  //����Ӧ���ȵ�byte����
	    byte [] result = new byte[length];  
	    int readLen = 0;  
	    int getLen = 0;  
	    while(getLen<length){  
	        readLen = serverSocketBis.read(result, getLen, length-getLen);  
	        serverSocketBis.read(result);
	        if(readLen ==-1){  
	            return null;  
	        }  
	        getLen = getLen + readLen;  
	    }  
	       return result;  
	}
}