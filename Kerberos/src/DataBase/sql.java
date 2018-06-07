package DataBase;
import java.math.BigInteger;
import java.sql.Connection;
import Package.Authenticator;
import Package.Ticket;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;
public class sql {
	private String url="jdbc:mysql://192.168.25.1:3306/LiaoTianShi";
	
	public void AddNewAuthenticator(Authenticator Au)throws Exception{
		int ID = Au.getID();
		int AD = Au.getAD();
		String TS = Au.getTS();
		Connection connection=null;
		Statement statement =null;	    
		String user="root";
		String password = "85119320luo";
		Class.forName("com.mysql.jdbc.Driver");//����������
		connection= DriverManager.getConnection(url, user, password);//�������ݿ�
		System.out.println("�ɹ�����mysql���ݿ�"+connection);
		statement=connection.createStatement();
		String t="";
		t="insert into Authenticator values('"+ID+"','"+AD+"','"+TS+"')";
		System.out.println(t);
		statement.executeUpdate(t);
		statement.close();
		connection.close();
		return;
	}
	
	public void AddNewTicket(Ticket ticket)throws Exception{
		int IDc = ticket.getID1();
		int ADc = ticket.getAD();
		int IDv = ticket.getID2();
		String TS = ticket.getTS();
		int LT = ticket.getLT();
		Connection connection=null;
		Statement statement =null;	    
		String user="root";
		String password = "85119320luo";
		Class.forName("com.mysql.jdbc.Driver");//����������
		connection= DriverManager.getConnection(url, user, password);//�������ݿ�
		System.out.println("�ɹ�����mysql���ݿ�"+connection);
		statement=connection.createStatement();
		String t="";
		t="insert into Ticket values('"+IDc+"','"+ADc+"','"+IDv+"','"+TS+"','"+LT+"')";
		System.out.println(t);
		statement.executeUpdate(t);
		statement.close();
		connection.close();
		return;
	}
	
	public Authenticator SelectAuthenticator(int ID) throws Exception{
		Authenticator Au = new Authenticator();
		Connection connection=null;
	 	Statement statement =null;
	 	ResultSet rs=null;	    
	 	String user="root";
	 	String password = "85119320luo";
	 	Class.forName("com.mysql.jdbc.Driver");		//����������
	 	connection= DriverManager.getConnection(url, user, password);//�������ݿ�
	 	System.out.println("�ɹ�����mysql���ݿ�"+connection);
	 	statement=connection.createStatement();
	 	String t = "Select * From Authenticator where ID="+"'"+ID+"'";
	 	System.out.println(t);
	 	rs=statement.executeQuery(t);
	 	if(rs.next()){
	 		Au.setID(Integer.parseInt(rs.getString("ID")));
	 		Au.setAD(Integer.parseInt(rs.getString("AD")));
	 		Au.setTS(rs.getString("TS"));
	 	}else{
	 		Au = null;
	 	}
	 	statement.close();
	 	connection.close();
	 	System.out.println(Au.getID());
	 	System.out.println(Au.getAD());
	 	System.out.println(Au.getTS());
		return Au;
	}
	
	public Ticket SelectTicket(int IDc) throws Exception{
		Ticket ticket = new Ticket();
		Connection connection=null;
	 	Statement statement =null;
	 	ResultSet rs=null;	    
	 	String user="root";
	 	String password = "85119320luo";
	 	Class.forName("com.mysql.jdbc.Driver");		//����������
	 	connection= DriverManager.getConnection(url, user, password);//�������ݿ�
	 	System.out.println("�ɹ�����mysql���ݿ�"+connection);
	 	statement=connection.createStatement();
	 	String t = "Select * From Ticket where IDc="+"'"+IDc+"'";
	 	System.out.println(t);
	 	rs=statement.executeQuery(t);
	 	if(rs.next()){
	 		ticket.setID1(Integer.parseInt(rs.getString("IDc")));
	 		ticket.setAD(Integer.parseInt(rs.getString("ADc")));
	 		ticket.setID2(Integer.parseInt(rs.getString("IDv")));
	 		ticket.setTS(rs.getString("TS"));
	 		ticket.setLT(Integer.parseInt(rs.getString("LT")));
	 	}else{
	 		ticket = null;
	 	}
	 	statement.close();
	 	connection.close();
	 	System.out.println(ticket.getID1());
	 	System.out.println(ticket.getAD());
	 	System.out.println(ticket.getID2());
	 	System.out.println(ticket.getTS());
	 	System.out.println(ticket.getLT());
		return ticket;
	}
	
	public void DeleteAuthenticator(int ID) throws Exception{
		Connection connection=null;
		Statement statement =null;	    
		String user="root";
		String password = "85119320luo";
		Class.forName("com.mysql.jdbc.Driver");//����������
		connection= DriverManager.getConnection(url, user, password);//�������ݿ�
		System.out.println("�ɹ�����mysql���ݿ�"+connection);
		statement=connection.createStatement();
		String t="";
		t="delete from Authenticator where ID ='"+ID+"'";
		statement.executeUpdate(t);
		statement.close();
		connection.close();
		return;
	}
	
	public void DeleteTicket(int IDc) throws Exception{
		Connection connection=null;
		Statement statement =null;	    
		String user="root";
		String password = "85119320luo";
		Class.forName("com.mysql.jdbc.Driver");//����������
		connection= DriverManager.getConnection(url, user, password);//�������ݿ�
		System.out.println("�ɹ�����mysql���ݿ�"+connection);
		statement=connection.createStatement();
		String t="";
		t="delete from Ticket where IDc ='"+IDc+"'";
		statement.executeUpdate(t);
		statement.close();
		connection.close();
		return;
	}
	
	public void AddNewUsers(int IDc,BigInteger BI)throws Exception{
		Connection connection=null;
		Statement statement =null;	    
		String user="root";
		String password = "85119320luo";
		Class.forName("com.mysql.jdbc.Driver");//����������
		connection= DriverManager.getConnection(url, user, password);//�������ݿ�
		System.out.println("�ɹ�����mysql���ݿ�"+connection);
		statement=connection.createStatement();
		String t="";
		t="insert into IDtoPass values('"+IDc+"','"+BI+"')";
		System.out.println(t);
		statement.executeUpdate(t);
		statement.close();
		connection.close();
		return;
	}
	
	public void Online (int IDc,String ip) throws Exception{
		Connection connection=null;
		Statement statement =null;	    
		String user="root";
		String password = "85119320luo";
		Class.forName("com.mysql.jdbc.Driver");//����������
		connection= DriverManager.getConnection(url, user, password);//�������ݿ�
		System.out.println("�ɹ�����mysql���ݿ�"+connection);
		statement=connection.createStatement();
		String t="";
		t="insert into IDtoIp values('"+IDc+"','"+ip+"')";
		System.out.println(t);
		statement.executeUpdate(t);
		statement.close();
		connection.close();
		return;
	}
	
	public String selectAllOnline() throws Exception{
		Connection connection=null;
		Statement statement =null;
		ResultSet rs=null;
		String user="root";
		String password = "85119320luo";
		Class.forName("com.mysql.jdbc.Driver");//����������
		connection= DriverManager.getConnection(url, user, password);//�������ݿ�
		System.out.println("�ɹ�����mysql���ݿ�"+connection);
		statement=connection.createStatement();
		String t="";
		t="Select * From IDtoIp";
		rs=statement.executeQuery(t);
		System.out.println(t);
		String html="";
		while (rs.next())
		{
			html+=rs.getString("IDc")+" "+rs.getString("IP")+" ";
		}
		System.out.println(html);
		statement.close();
		connection.close();
		return html;
	}
	
	public void Offline (int IDc) throws Exception{
		Connection connection=null;
		Statement statement =null;	    
		String user="root";
		String password = "85119320luo";
		Class.forName("com.mysql.jdbc.Driver");//����������
		connection= DriverManager.getConnection(url, user, password);//�������ݿ�
		System.out.println("�ɹ�����mysql���ݿ�"+connection);
		statement=connection.createStatement();
		String t="";
		t="delete from IDtoIp where IDc ='"+IDc+"'";
		statement.executeUpdate(t);
		statement.close();
		connection.close();
		return;
	}
	
	public BigInteger HasAIDc (int idc) throws Exception	//����и��˺��򷵻���
	 {
	 	Connection connection=null;
	 	Statement statement =null;
	 	ResultSet rs=null;	    
	 	String user="root";
	 	String password = "85119320luo";
	 	Class.forName("com.mysql.jdbc.Driver");		//����������
	 	connection= DriverManager.getConnection(url, user, password);//�������ݿ�
	 	System.out.println("�ɹ�����mysql���ݿ�"+connection);
	 	statement=connection.createStatement();
	 	BigInteger need = null;
	 	String t = "Select * From IDToPass where IDc="+"'"+idc+"'";
	 	System.out.println(t);
	 	rs=statement.executeQuery(t);
	 	if(rs.next()){
	 		need = new BigInteger(rs.getString("Hash_Password"));
	 	}
	 	statement.close();
	 	connection.close();
	 	return need;
	 }
	
	public void UpdatePassword(int idc,BigInteger newPassword) throws Exception{
		Connection connection=null;
		Statement statement =null;
		String user="root";
		String password = "85119320luo";
		Class.forName("com.mysql.jdbc.Driver");//����������
		connection= DriverManager.getConnection(url, user, password);//�������ݿ�
		System.out.println("�ɹ�����mysql���ݿ�"+connection);
		statement=connection.createStatement();
		String t="";
		t="update IDToPass set Hash_Password="+newPassword+" where IDc ='"+idc+"'";
		statement.executeUpdate(t);
		System.out.println(t);
		statement.close();
		connection.close();
	}
	
} 
