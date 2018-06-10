package Client;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import Client.*;
//************************************����ʵ���û���½***********************************
public class clientLogin extends JFrame implements ActionListener{

	//��������
	JLabel labelUserName;
	JLabel labelUserPwd;
	JPasswordField txtUserPwd;
	JTextField txtUserName;
	JButton buttonLogin;
	JButton buttonCancel;
	JButton buttonRegister;
	JLabel label;
	JLabel imagePos;
	Login login1;
	Client client1;
//************************************���õ�½���ڷ���***********************************	
	public clientLogin(){    
		
		this.setTitle("������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	    Font myfont=new Font("����",Font.PLAIN,12);
	    
		//���ñ�ǩ
		labelUserName=new JLabel("�û�����");
		labelUserName.setFont(myfont);
		labelUserPwd=new JLabel("�� �룺");
		labelUserPwd.setFont(myfont);
		
		Icon image = new ImageIcon("image/login.png");
		imagePos = new JLabel(image);
		
		//���ð�ť���
		buttonLogin=new JButton("��¼");
		buttonLogin.setFont(myfont);
		buttonLogin.setBackground(new Color(0, 153, 255));
		buttonLogin.setToolTipText("�����������  ");
		buttonLogin.addActionListener(this);
		
		buttonRegister=new JButton("ע��");
		buttonRegister.setFont(myfont);
		buttonRegister.setBackground(new Color(0, 153, 255));
		buttonRegister.setToolTipText(" ����ע����� ");
		buttonRegister.addActionListener(this);
		
		buttonCancel=new JButton("����");
		buttonCancel.setFont(myfont);
		buttonCancel.setBackground(new Color(0, 153, 255));
		buttonCancel.setToolTipText(" ����������� ") ;
		buttonCancel.addActionListener(this);
		
		//�����ı���
		txtUserName=new JTextField();
		txtUserName.setToolTipText(" ������������ ") ;
	
		txtUserPwd=new JPasswordField();
		txtUserPwd.setToolTipText(" �������� ") ;
		
		//���ÿ�ܴ��ڲ��ֹ���Ϊ�ղ��ֹ���
		JPanel panelObj=new JPanel();
		getContentPane().add(panelObj);
		panelObj.setLayout(null);
	
		//�ֹ����ø��ؼ��Ĵ�С��λ��
		labelUserName.setBounds(new Rectangle(40, 20, 60, 20));
		txtUserName.setBounds(new Rectangle(100, 20, 180, 20));
	    labelUserPwd.setBounds(new Rectangle(40, 60, 60, 20));
		txtUserPwd.setBounds(new Rectangle(100, 60, 180, 20));
	
		buttonLogin.setBounds(new Rectangle(40,100,60,20));
		buttonRegister.setBounds(new Rectangle(130,100,60,20));
		buttonCancel.setBounds(new Rectangle(220,100,60,20));
		
		imagePos.setBounds(new Rectangle(0, 0, 316, 133));

		//�Ѹ�����ӵ���ܴ�����
		panelObj.add(labelUserName);
		panelObj.add(labelUserPwd);
		panelObj.add(txtUserName);
		panelObj.add(txtUserPwd);
		panelObj.add(buttonLogin);
		panelObj.add(buttonRegister);
		panelObj.add(buttonCancel);
		panelObj.add(imagePos);
	
		setResizable(false);
		setSize(320,160);
		Dimension us = this.getSize();
   		//��ȡ��Ļ�ĳߴ�
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2 ;
		int newY = (them.height - us.height) / 2 ;		
		this.setLocation(newX,newY);
		setVisible(true);
	} 
	  
  	//***********************************��ť�¼�****************************************
	public void actionPerformed(ActionEvent even){
	
		JButton obj=(JButton)even.getSource();
	  	
	  	if(obj==buttonCancel){               //ȡ��
	    
	    	txtUserName.setText(null);
	        txtUserPwd.setText(null);
	    }
	    else if (obj==buttonLogin){          //�ύ
        
        	String n=txtUserName.getText();
            String p=String.valueOf(txtUserPwd.getPassword());
          
            if(n.length()==0&&p.length()!=0){
               	JOptionPane.showMessageDialog(this,"��û����������","��ʾ!",JOptionPane.CANCEL_OPTION);
            	return;
            }
            else if (n.length()!=0&&p.length()==0){
                JOptionPane.showMessageDialog(this,"��û����������","��ʾ!",JOptionPane.CANCEL_OPTION);
            	return;
            }
            else if (n.length()==0&&p.length()==0){
               	JOptionPane.showMessageDialog(this,"��û����������������","��ʾ!",JOptionPane.CANCEL_OPTION);
            	return;
            }
            
            enterChatRoom();     //�����û���½����
        }                      
	  	else if (obj==buttonRegister){       //ע��
	  	
	  		setVisible(false);
	  		new clientRegister();
	  	} 
	}
    //*********************************main()���� ****************************************
	public static void main(String args[]){
	 
	 	 clientLogin f=new clientLogin();
   	}
   	
   	//*********************************�û���½******************************************
   	public void enterChatRoom(){
   		
		LoginMessage uObj=new LoginMessage();     //�����û���½����ϸ����
		uObj.loginName = new String(txtUserName.getText());
		uObj.loginPassword = new String(txtUserPwd.getPassword());
		setVisible(false);
  		login1= new Login(uObj);
  		//client1=new Client();
    	//try {
			//client1.Online(uObj.loginName);
		//} catch (IOException e) {
			//// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
    	//login1 = new Login(uObj);
   	}
}
