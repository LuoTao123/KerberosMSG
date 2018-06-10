package Client;
import Client.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
//**********************************该类实现更新用户资料*********************************

public class UpdateDetail extends JDialog{
	JButton  submit;
    JButton  close;
    JPasswordField   newPass;
    JPasswordField   Regain;
   // JTextField   email;
    JPasswordField oldPass;
    JLabel       Regain_Label;
   // JLabel       Email_label;
    JLabel      oldPass_Label;
    JLabel       newPass_Label;
 	JPanel panel;
    Font myfont;
    String name;
    Client client4;
	//*******************************构造更新用户资料界面********************************	       
    public UpdateDetail(String uName){
    	name = uName;
        myfont=new Font("宋体",Font.PLAIN,13);
        this.setTitle("\u4FEE\u6539\u7528\u6237\u8D44\u6599");
        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
         
        Icon image = new ImageIcon("image/bb.gif");
        oldPass_Label=new JLabel("旧密码：");
        newPass_Label=new JLabel("新密码:");
        newPass_Label.setFont(myfont);      
        Regain_Label=new JLabel("确认密码:");
        Regain_Label.setFont(myfont);        
        newPass=new JPasswordField(6);
        Regain=new JPasswordField(6);
        oldPass=new JPasswordField(6);
     //   email=new JTextField(15);     
        close=new JButton("关闭"); //关闭
        close.setPreferredSize(new Dimension(60,20));
        close.setFont(myfont);
        close.setBackground(Color.LIGHT_GRAY);      
        close.addActionListener(new
                    ActionListener()
                    {
                    	public void actionPerformed(ActionEvent evt)
                        {
                         	setVisible(false);
                        }
                     });
		submit=new JButton("\u4FEE\u6539");
        submit.setPreferredSize(new Dimension(60,20));
        submit.setFont(myfont);
        submit.setBackground(Color.LIGHT_GRAY);
        submit.addActionListener(new UpdateClient());
            
        newPass_Label.setBounds(new Rectangle(15,15,60,20));
        newPass.setBounds(new Rectangle(100,15,180,20));
        Regain_Label.setBounds(new Rectangle(15,60,60,20));
        Regain.setBounds(new Rectangle(100,60,180,20));
        oldPass_Label.setBounds(new Rectangle(15,105,60,20));
        oldPass.setBounds(new Rectangle(100, 105, 180, 20));
     	submit.setBounds(new Rectangle(90,150,90,20));
     	close.setBounds(new Rectangle(200,150,90,20));
        
     	panel.add(oldPass_Label);
        panel.add(oldPass);     
        panel.add(newPass_Label);           
        panel.add(newPass);              ///新密码            
        panel.add(Regain_Label);              ///验证密码            
        panel.add(Regain);              ///验证密码 
        panel.add(close);
        panel.add(submit);
        
        setSize(380,230);
        Dimension us = this.getSize();
   		//获取屏幕的尺寸
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2 ;
		int newY = (them.height - us.height) / 2 ;		
		this.setLocation(newX,newY);
        setVisible(true);
	}
    //******************************实现各种按钮事件*************************************   
	class UpdateClient implements ActionListener{
       		 
    	public void actionPerformed(ActionEvent e){
       		 	
       		Object obj = e.getSource();
       		 	
       		if(obj==(JButton)submit){
       		 	String password0=String.valueOf(oldPass.getPassword());
       			String password1=String.valueOf(newPass.getPassword());
            	String password2=String.valueOf(Regain.getPassword());	
			
	        	if(password1.length() == 0){
			
					JOptionPane jopName = new JOptionPane();
					jopName.showMessageDialog(null,"请输入密码!","提示",JOptionPane.CANCEL_OPTION);
					return;
				}
			
				if((password1.length() < 6) || (password1.length() > 12)){
			
					JOptionPane jopName = new JOptionPane();
					jopName.showMessageDialog(null,"密码不得少于6个字符多于12个字符!请重新输入!","提示",JOptionPane.CANCEL_OPTION);
					newPass.setText("");
					return;
				}
			
				if(password2.length() == 0){
			
					JOptionPane jopName = new JOptionPane();
					jopName.showMessageDialog(null,"请再次输入密码!","提示",JOptionPane.CANCEL_OPTION);
					return;
				}
			
				if(!password2.equals(password1)){
			
					JOptionPane jopName = new JOptionPane();
					jopName.showMessageDialog(null,"您输入的密码和确认密码不一致!","提示",JOptionPane.CANCEL_OPTION);
					Regain.setText("");
					return;
				}
            	updateUser();          //调用更新用户资料方法
            }
		}	
	}
    //**********************************更新方法******************************************
    public  void updateUser(){
       	
        UpdateUser uObj=new UpdateUser();    									       
		uObj.userName = name;
		uObj.oldPass=String.valueOf(oldPass.getPassword());
		uObj.UpdatePass = String.valueOf(newPass.getPassword());
		uObj.RePass = String.valueOf(Regain.getPassword());
		//client4=new Client();
		try {
			client4.Modify(uObj.userName, uObj.oldPass, uObj.UpdatePass);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    		
		//try{
		  // 	Socket toServer;
		    //toServer = new Socket("127.0.0.1",1001);                                                
		    
		    //ObjectOutputStream streamToServer = new ObjectOutputStream(toServer.getOutputStream());
			//streamToServer.writeObject((UpdateUser)uObj);                          
			//ObjectInputStream streamFromServer = new ObjectInputStream(toServer.getInputStream());
			//ValidateMessage msg = (ValidateMessage)streamFromServer.readObject();
		
			//String str = msg.validateMessage;	                                     
			//if(str.toString().equals("ok")){                                       
			 //	JOptionPane.showMessageDialog(null,"资料更新有效!!!");
			 	//this.dispose();
			//}
			//else{
				//JOptionPane.showMessageDialog(null,"资料更新不成功!");
				//return;
			//}
			//streamToServer.close();
           	//streamFromServer.close();
		//}
		//catch(Exception e){}
	} 
}