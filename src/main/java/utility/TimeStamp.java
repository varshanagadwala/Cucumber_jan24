package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStamp {

	public static String getTimeStamp() {
		Date date = new Date();
		System.out.println(date);

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(date);
		System.out.println(strDate);
		return date.toString();
	}

	public static void main(String[] args) {
		getTimeStamp();
	}
}