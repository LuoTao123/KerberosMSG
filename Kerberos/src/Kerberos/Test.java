package Kerberos;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimeStamp TS = new TimeStamp();
		String TimeString = TS.getTimeString();
		String TimeStamp = TS.TimeStringToTimeStamp(TimeString);
		try{
			Thread.sleep(3000);
			}catch(Exception e){
			System.exit(0);//退出程序
		}
		int LT = 4;
		if(TS.IsOverLifeTime(TimeStamp, LT)){
			System.out.println("时间周期内");
		}else{
			System.out.println("时间周期外");
		}
	}

}
