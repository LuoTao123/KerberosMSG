package Client;
import java.util.*;
import java.io.*;
import java.net.*;

//*******************************�����û�������*******************************************
class LoginMessage extends Object implements Serializable{      //�ͻ���½��Ϣ���л�

	String loginName;
	String loginPassword;
}

class RegisterMessage implements Serializable{					  //�ͻ�ע����Ϣ���л�
   
    String RegisterName;
    String RegisterPassword;
    String AffirmPassword;
}

class ChatMessage implements Serializable{                       //�ͻ�������Ϣ���л�

	String chatUser;
	String chatMessage;
	String chatToUser;
	String chatFace;
	String chattime;
}
 
class ClientAdvice implements Serializable{                     //�ͻ��������л� 
	
	String FromUser;
	String advice;
	String ToUser;
	String date;
} 

class ValidateMessage extends Object implements Serializable{  //�û���ȷ����Ϣ���л�

	String validateName;
	String validateMessage;
}
  
class ServerMessage implements Serializable{                  //������������������߿ͻ�����Ϣ
  
  	Vector userOnLine;
  	Vector messageGained;
  	Vector userAdvice;
} 

class FileObject implements java.io.Serializable{             //�����ļ�

	byte[] fileContent;
	String from;
	String to;
	String format;
}

class ClientName implements java.io.Serializable{            //�����û���

	String clientName;
}

class UpdateUser implements java.io.Serializable{            //�����û�����
	
	String userName;
	String UpdatePass;
	String RePass;
    String oldPass;	
}

