package gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentDate {
	DateFormat dateFormat = new SimpleDateFormat("d-M-y");
	Calendar cal = Calendar.getInstance();

	public String getCompleteDate() {
		String compdate = dateFormat.format(cal.getTime());
		return compdate;
	}

	public int getDate() {
		return cal.getTime().getDate();
	}
	public int getMonth() {
		return cal.getTime().getMonth()+1;
	}
	public int getYear() {
		return cal.getTime().getYear()+1900;
	}
	
	

}
