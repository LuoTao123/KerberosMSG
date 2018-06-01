package Package;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		 String ts = "2018-5-31 17:58:31";
		 byte[] tsByte = ts.getBytes("UTF-8");
		 System.out.println(tsByte.length);
		 String isoString = new String(tsByte,"UTF-8");
		 System.out.println(isoString);
	}
}
