package com.hospital.base.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateTimeUtils {

	public String getCurrentTimeUsingDate() {
		Date date = new Date();
		String strDateFormat = "dd/MMM/yyyy hh:mm:ss a";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		String dateTimeUtils = dateFormat.format(date);
		return dateTimeUtils;
	}

	public String getCurrentDate() {
		Date date = new Date();
		String strDateFormat = "dd/MMM/yyyy";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		String dateTimeUtils = dateFormat.format(date);
		return dateTimeUtils;
	}
	
	public String getCurrentDateTime() {
		Date date = new Date();
		String strDateFormat = "dd/MMM/yyyy hh:mm:ss";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		String dateTimeUtils = dateFormat.format(date);
		return dateTimeUtils;
	}

	public String addDays(String oldDate, int add) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		Calendar c = Calendar.getInstance();
		try {
			// Setting the date to the given date
			c.setTime(sdf.parse(oldDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Number of Days to add
		c.add(Calendar.DAY_OF_MONTH, add);
		String newDate = sdf.format(c.getTime());
		return newDate;
	}

	public int dateCompare(String date1, String date2) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		int comp = 0;
		try {
			Date d1 = sdf.parse(date1);
			Date d2 = sdf.parse(date2);
			comp = d1.compareTo(d2); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return comp;
	}
}
