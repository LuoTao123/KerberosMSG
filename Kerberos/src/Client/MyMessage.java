package Client;
import java.util.*;
import java.io.*;
import java.net.*;

//*******************************保存用户的资料*******************************************
class LoginMessage extends Object implements Serializable{      //客户登陆信息序列化

	String loginName;
	String loginPassword;
}

class RegisterMessage implements Serializable{					  //客户注册信息序列化
   
    String RegisterName;
    String RegisterPassword;
    String AffirmPassword;
}

class ChatMessage implements Serializable{                       //客户聊天信息序列化

	String chatUser;
	String chatMessage;
	String chatToUser;
	String chatFace;
	String chattime;
}
 
class ClientAdvice implements Serializable{                     //客户留言序列化 
	
	String FromUser;
	String advice;
	String ToUser;
	String date;
} 

class ValidateMessage extends Object implements Serializable{  //用户需确认信息序列化

	String validateName;
	String validateMessage;
}
  
class ServerMessage implements Serializable{                  //服务器发送聊天和在线客户的信息
  
  	Vector userOnLine;
  	Vector messageGained;
  	Vector userAdvice;
} 

class FileObject implements java.io.Serializable{             //传送文件

	byte[] fileContent;
	String from;
	String to;
	String format;
}

class ClientName implements java.io.Serializable{            //传送用户名

	String clientName;
}

class UpdateUser implements java.io.Serializable{            //更新用户资料
	
	String userName;
	String UpdatePass;
	String RePass;
    String oldPass;	
}

