package Client;
	import java.io.IOException;
	import java.util.Scanner;

	public class Test {
		public static void main(String[] arg)  {
			System.out.println("ѡ�񷽷�");
			Client client = new Client();
			int input;
			Scanner in = new Scanner(System.in);
			String str = in.nextLine();
			input = Integer.valueOf(str);
			if(input == 1) {
				System.out.println("�������˺ź�����");
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
				System.out.println("�������˺�������µ�����");
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
				System.out.println("�������˺ź�����");
				String ID = in.nextLine();
				String password = in.nextLine();
				try {
					client.login(ID, password);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("�����뷢�͵���Ϣ");
				String	Message = in.nextLine();
				try {
					client.Online(ID);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					client.Chat(Message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
}
