package Client;
	import java.io.IOException;
	import java.util.Scanner;
	
	public class Test {
		public static void main(String[] arg)  {
			System.out.println("选择方法");
			Client client = new Client();
			int input;
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			String str = in.nextLine();
			input = Integer.valueOf(str);
			if(input == 1) {
				System.out.println("请输入账号和密码");
				String IDc = in.nextLine();
				String Psw = in.nextLine();
				try {
					client.Regist(IDc, Psw);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (input ==2) {
				System.out.println("请输入账号密码和新的密码");
				String idc = in.nextLine();
				String psw = in.nextLine();
				String npsw = in.nextLine();
				try {
					client.Modify(idc, psw, npsw);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(input == 3) {
				System.out.println("请输入账号和密码");
				String ID = in.nextLine();
				String password = in.nextLine();
				try {
					client.login(ID, password);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					client.Online();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				@SuppressWarnings("unused")
//				Listen listen = new Listen(ID,client.NowSocket);
				System.out.println("请输入发送的信息");
				String	Message = in.nextLine();
				try {
					client.Chat(Message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
}
