package Kerberos;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimeStamp TS = new TimeStamp();
		String TimeString = TS.getTimeString();
		String TimeStamp = TS.TimeStringToTimeStamp("2018-06-07 09:52:19");
		try{
			Thread.sleep(3000);
			}catch(Exception e){
			System.exit(0);//�˳�����
		}
		int LT = 4;
		if(TS.IsOverLifeTime(TimeStamp, LT,TimeStamp)){
			System.out.println("ʱ��������");
		}else{
			System.out.println("ʱ��������");
		}
	}

}
