package Client;
import Client.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.net.*;
//**********************************����ʵ���û�ע��***************************************
public class clientRegister extends JFrame implements ActionListener{
	
	JPanel panelObj;
	JLabel labelName,labelName1;
    JLabel logoimagePosition1;
	JLabel logoimagePosition2;
	JLabel labelPassword1,labelPassword11;
	JLabel labelPassword2,labelPassword22;
	JLabel labelEmail,labelEmail1;
	
	JTextField textName;

	ButtonGroup bg;
	JPasswordField textPassword1;
	JPasswordField textPassword2;
	
	JButton b1;
	JButton b2;
	JButton b3;
	
	JLabel imagePos;
STATEC statec = new STATEC();
Client client = new Client(statec);
	RegisterMessage uobj;	
	//******************************** �����û�ע�ᴰ��************************************
	public clientRegister()
	{
		this.setTitle(" �û�ע��");
     	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     	setSize(530,300);
     	Dimension us = this.getSize();
   		//��ȡ��Ļ�ĳߴ�
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2 ;
		int newY = (them.height - us.height) / 2 ;		
		this.setLocation(newX,newY);
		
		panelObj = new JPanel();
	    getContentPane().add(panelObj);
		panelObj.setLayout(null);    //���ÿ�ܴ��ڵĲ��ֹ�����Ϊnull���ֹ�����
		
		//��ʼ���������
		bg = new ButtonGroup();
		Font f = new Font("����",Font.PLAIN,12);
		labelName = new JLabel("�û�����");
		labelName.setFont(f);
		labelName1 = new JLabel("(�û�����������Ϊ2��15�ַ�)");
		labelName1.setFont(f);

		
	   
		
		labelPassword1 = new JLabel("\u5BC6 \u7801 \uFF1A");
		labelPassword1.setFont(f);
		labelPassword11 = new JLabel("(���벻������6���ַ�����12���ַ�)");
		labelPassword11.setFont(f);
		labelPassword2 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		labelPassword2.setFont(f);
		labelPassword22 = new JLabel("(������һ��ȷ��)");
		labelPassword22.setFont(f);



		
		textName = new JTextField(10);
		textName.setToolTipText("����������Ҫע����û�����");
		textPassword1 = new JPasswordField(10);
		textPassword1.setToolTipText("�����������������");
		textPassword2 = new JPasswordField(10);
		textPassword2.setToolTipText("�����ٴ���������ȷ�ϡ�");

		
		b1 = new JButton("��   ��");
		b1.setBackground(Color.WHITE);
		b1.addActionListener(this);
		b2 = new JButton("��   ��");
		b2.setBackground(Color.WHITE);
		b2.addActionListener(this);
		b3 = new JButton("��   ��");
		b3.setBackground(Color.WHITE);
		b3.addActionListener(this);
		
		Icon image = new ImageIcon("image/register.png");
		imagePos = new JLabel(image);
		
		labelName.setBounds(new Rectangle(40, 50, 60, 30));
		textName.setBounds(new Rectangle(100, 50, 180, 30));
		labelName1.setBounds(new Rectangle(300, 50, 200, 30));


		labelPassword1.setBounds(new Rectangle(40, 90, 60, 30));
		textPassword1.setBounds(new Rectangle(100, 90, 180, 30));
		labelPassword11.setBounds(new Rectangle(300, 90, 220, 30));
		labelPassword2.setBounds(new Rectangle(28, 130, 60, 30));
		textPassword2.setBounds(new Rectangle(100, 130, 180, 30));
		labelPassword22.setBounds(new Rectangle(300, 130, 220, 30));

		b1.setBounds(new Rectangle(60, 195, 80, 30));
		b2.setBounds(new Rectangle(170,195, 80, 30));
		b3.setBounds(new Rectangle(280, 195, 80, 30));
		
		imagePos.setBounds(new Rectangle(0, 0, 525, 273));
		//�����������
		panelObj.add(labelName);
		panelObj.add(textName);
		panelObj.add(labelName1);

		panelObj.add(labelPassword1);
		panelObj.add(textPassword1);
		panelObj.add(labelPassword11);
		panelObj.add(labelPassword2);
		panelObj.add(textPassword2);
		panelObj.add(labelPassword22);
		panelObj.add(b1);
		panelObj.add(b2);
		panelObj.add(b3);
		
		panelObj.add(imagePos);
		
		this.setResizable(false);
	    this.setVisible(true);
		
	}
	//***************************************�������ఴť�Ķ���*********************************************
	public void actionPerformed(ActionEvent evt){          
		
		Object obj = evt.getSource();
		
		if(obj == b1){                       //ע��
     	    uobj = new RegisterMessage();       //�����û�����ϸ����
			uobj.RegisterName = textName.getText();
			uobj.RegisterPassword = new String(textPassword1.getPassword());
			uobj.AffirmPassword = new String(textPassword2.getPassword());
		

			
			String userPassword2 = new String(textPassword2.getPassword()); 

			//�жϸ��������Ƿ���Ϲ���
			if(uobj.RegisterName.length() == 0){
				
				JOptionPane jopNamePass = new JOptionPane();
				jopNamePass.showMessageDialog(null,"�������û���!","��ʾ",JOptionPane.CANCEL_OPTION);
				return;
			}
			
			if((uobj.RegisterName.length()<2) || (uobj.RegisterName.length()>15)){
			
				JOptionPane jopNamePass = new JOptionPane();
				jopNamePass.showMessageDialog(null,"�û�����������Ϊ2��15�ַ�!","��ʾ",JOptionPane.CANCEL_OPTION);
				textName.setText("");
				return;
			}
			
	        if(uobj.RegisterPassword.length() == 0){
			
				JOptionPane jopName = new JOptionPane();
				jopName.showMessageDialog(null,"����������!","��ʾ",JOptionPane.CANCEL_OPTION);
				return;
			}
			
			if((uobj.RegisterPassword.length() < 6) || (uobj.RegisterPassword.length() > 12)){
			
				JOptionPane jopName = new JOptionPane();
				jopName.showMessageDialog(null,"���벻������6���ַ�����12���ַ�!����������!","��ʾ",JOptionPane.CANCEL_OPTION);
				textPassword1.setText("");
				return;
			}
			
			if(userPassword2.length() == 0){
			
				JOptionPane jopName = new JOptionPane();
				jopName.showMessageDialog(null,"���ٴ���������!","��ʾ",JOptionPane.CANCEL_OPTION);
				return;
			}
			
			if(!uobj.RegisterPassword.equals(userPassword2)){
			
				JOptionPane jopName = new JOptionPane();
				jopName.showMessageDialog(null,"��������������벻һ��!","��ʾ",JOptionPane.CANCEL_OPTION);
				textPassword2.setText("");
				return;
			}
			

		

			
			//����������Ӳ��ӷ���Ϣ						
			try{
		    	client=new Client(statec);
		    	client.Regist(uobj.RegisterName,uobj.RegisterPassword);
		    	//Socket toServer;
		    	//toServer = new Socket("127.0.0.1",1001);     //���ӵ�������
		    	
		    	//д�ͻ���ϸ���ϵ�������socket
		    	//ObjectOutputStream streamToServer=new ObjectOutputStream(toServer.getOutputStream());
			    //streamToServer.writeObject((RegisterMessage)uobj);
				//�����Է�����socket�ĵ�½״̬
             	//ObjectInputStream streamFromServer= new ObjectInputStream(toServer.getInputStream());
             	//ValidateMessage msg =(ValidateMessage)streamFromServer.readObject();
				//String str = msg.validateMessage;
				//if(str.toString().equals("ok")){        //ע��ɹ�
					
					//JOptionPane jopPassword = new JOptionPane();
					//jopPassword.showMessageDialog(null,"ע��ɹ���");
					//this.dispose();
					
					//��ȡ��½��Ϣ
					//clientLogin login=new clientLogin();
					//login.txtUserName.setText(uobj.RegisterName);
					//login.txtUserPwd.setText(uobj.RegisterPassword);
	
				//}
				//else if(str.toString().equals("no")){   //ע��ʧ��
			
					//JOptionPane jopPassword = new JOptionPane();
					//jopPassword.showMessageDialog(null,"���û���������ʹ�ã�","��ʾ",JOptionPane.CANCEL_OPTION);
					//textName.setText("");
					//return;
				//}
				//streamToServer.close();
            	//streamFromServer.close();
			}			
			catch(Exception e){
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(null,"�������ӷ�������","��ʾ",JOptionPane.CANCEL_OPTION);
			}	
			new clientLogin();
		}
		if(obj == b2){             //���		
			
			textName.setText("");
			textPassword1.setText("");
			textPassword2.setText("");
			
		}	
		if(obj==b3){               //����
			
			new clientLogin();		
			this.setVisible(false);
		}
	}
	
}


