package Client;
	import java.io.IOException;
	import java.util.Scanner;
	public class Test {
		public static void main(String[] arg)  {
			System.out.println("选择方法");
			STATEC statec = new STATEC();
			Client client = new Client(statec);
			statec.client = client;
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
				client.setIDc(Integer.valueOf(ID));
				try {
					client.login(ID, password);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Listen listen = new Listen(Integer.valueOf(ID),client.state.C_VSocket,client.state);
				STATEC stateR = new STATEC();
				stateR.client = client;
				Listen listenR = new Listen(Integer.valueOf(ID),client.ReC_Vsocket,stateR);
				try {
					client.Online();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				///////////////////////未收到回复直接进入循环
//				@SuppressWarnings("unused")
//				Listen listen = new Listen(ID,client.NowSocket);
				int i = 0;
				while(i<4) {
					i++;
					System.out.println("请输入发送的信息");
					String	Message = in.nextLine();
					try {
						client.Chat(Message);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					client.Offline();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
}
