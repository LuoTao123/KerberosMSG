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
    // 监听指定的端口
		int port = 55534;
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(port);
		// server将一直等待连接的到来
		System.out.println("server将一直等待连接的到来"); 
		//如果使用多线程，那就需要线程池，防止并发过高时创建过多线程耗尽资源
		ExecutorService threadPool = Executors.newFixedThreadPool(100);
		while (true) {
			Socket socket = server.accept();
			Runnable runnable=()->{
				try {
					// 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
					InputStream inputStream = socket.getInputStream();
					BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
					byte[] bytes =new byte[2];
					bufferedInputStream.read(bytes, 0, 2);
//					Unpack unpack = new Unpack();
//					unpack.Unpack_Head(bytes,bufferedInputStream,socket);
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
	        throws SocketTimeoutException, IOException{  //读对应长度的byte数组
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