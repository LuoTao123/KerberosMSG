package Package;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
	public static void main(String args[]) throws Exception {
    // ����ָ���Ķ˿�
		int port = 55535;
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(port);
		// server��һֱ�ȴ����ӵĵ���
		System.out.println("server��һֱ�ȴ����ӵĵ���"); 
		//���ʹ�ö��̣߳��Ǿ���Ҫ�̳߳أ���ֹ��������ʱ���������̺߳ľ���Դ
		ExecutorService threadPool = Executors.newFixedThreadPool(100);
		while (true) {
			Socket socket = server.accept();
			Runnable runnable=()->{
				try {
					// ���������Ӻ󣬴�socket�л�ȡ�����������������������ж�ȡ
					InputStream inputStream = socket.getInputStream();
					BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
					byte[] bytes =new byte[2];
					bufferedInputStream.read(bytes, 0, 2);
					STATE state = new STATE();
					state.Unpack_Head(bytes,bufferedInputStream,socket,"127.0.0.1");
					bufferedInputStream.close();
					inputStream.close();
					socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
			threadPool.submit(runnable);
		}
	}
	public static byte[] readFixedLengthArray(BufferedInputStream serverSocketBis,int length)
	        throws SocketTimeoutException, IOException{  //����Ӧ���ȵ�byte����
	    byte [] result = new byte[length];  
	    int readLen = 0;  
	    int getLen = 0; 
	    while(getLen<length){
	        readLen = serverSocketBis.read(result, getLen, length-getLen);
	        System.out.println(readLen);
	        System.out.println(result[0]+" "+result[1]);
	        serverSocketBis.read(result); 
	        if(readLen ==-1){  
	            return null;  
	        }
	        System.out.println("fuck");
	        getLen = getLen + readLen;  
	    }  
	       return result;  
	}
}