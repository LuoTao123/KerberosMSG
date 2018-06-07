package Kerberos;

import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.Date;  

public class TimeStamp {
	public String TimeStringToTimeStamp(String TimeString) {  
		String TimeStamp = null;  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Date date = null;  
		try {  
			date = sdf.parse(TimeString);  
			long l = date.getTime();  
			String str = String.valueOf(l);  
			TimeStamp = str.substring(0, 10);  
		} catch (ParseException e) {  
		// TODO Auto-generated catch block  
			e.printStackTrace();  
		}  
		return TimeStamp;  
	}  
	
	public String TimeStampToTimeString(String TimeStamp) {  
		String TimeString = null;    
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lTimeStamp = Long.valueOf(TimeStamp);  
		TimeString = sdf.format(new Date(lTimeStamp * 1000L));   
		return TimeString;  
	}
	
	public String getTimeString(){
		Date date=new Date();    
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return sdf.format(date);   
	}
	
	public boolean IsOverLifeTime(String TimeStamp1,int LifeTime,String TimeStamp2){
		int TS1 = Integer.valueOf(TimeStamp1);
		int TS2 = Integer.valueOf(TimeStamp2);
		if(TS2-TS1>LifeTime){
			return false;
		}else{
			return true;
		}
	}
}  
