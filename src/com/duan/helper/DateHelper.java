package com.duan.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper 
{
	static String TEST = "AAA";
	
	//Chuyển từ Date sang String
	public static String dateToString(Date date, String format)
	{
		SimpleDateFormat formater = new SimpleDateFormat(format);
		String stringDate = formater.format(date);
		return stringDate;
	}
	
	//chuyển từ String sang Date
	public static Date stringToDate(String time, String format) throws ParseException
	{
		SimpleDateFormat formater = new SimpleDateFormat(format);
		Date date = formater.parse(time);
		
		return date;
	}
	
	//Kiểm tra xem chuỗi vừa truyền vào có hợp lệ theo kiểu Date format hay không, nếu không trả về FALSE, hợp lệ sẽ trả về TRUE
	public static boolean checkFormatDate(String dateText, String format)
	{
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try 
		{
			Date date = formater.parse(dateText);
			return true;
		} 
		catch (ParseException e) 
		{
			return false;
		}
	}
	
	
	//Lấy ra thời gian hiện tại
	public static Date getTimeNow()
	{
		return new Date();
	}
	
	public static void main(String[] args) throws ParseException {
		System.out.println(stringToDate("1", "YYYY"));
		System.out.println(stringToDate("2019", "yyyy"));;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
