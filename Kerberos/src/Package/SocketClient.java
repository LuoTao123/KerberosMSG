package Package;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Random;

import Package.Pack;

public class SocketClient {
  public static void main(String args[]) throws Exception {
    // Ҫ���ӵķ����IP��ַ�Ͷ˿�
    String host = "127.0.0.1";
    int port = 55534;
    // �����˽�������
    Socket socket = new Socket(host, port);
    // �������Ӻ��������
    OutputStream outputStream = socket.getOutputStream();
    Pack pack =new Pack();
    socket.getOutputStream().write(pack.Pack_0x12_Cont());
 /*   int IDC= 1234567890;
    Random rand = new Random();
    BigInteger PSW = new BigInteger(1024, rand);
    System.out.println(PSW);
    System.out.println(PSW.toByteArray().length);
    Data_Online DOn = new Data_Online();
    DOn.setIDc(IDC);
    DM.setRSA_HASH_PASSWORD(PSW);
    DM.setRSA_HASH_NPASSWORD(PSW);
    InputStream inputStream = socket.getInputStream();
	BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
	byte[] bytes =new byte[2];
	bufferedInputStream.read(bytes, 0, 2);
	if(bytes[0]==(byte)0x00&&bytes[1]==(byte)0x00){
		socket.getOutputStream().write(pack.Pack_0x10_Data(DOn));
	}else{
		System.out.println("���������س���");
	}
    //ͨ��shutdownOutput���ٷ������Ѿ����������ݣ�����ֻ�ܽ�������
    socket.shutdownOutput();
    inputStream.close();*/
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