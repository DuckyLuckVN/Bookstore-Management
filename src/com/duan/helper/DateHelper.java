package com.duan.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.itextpdf.text.xml.simpleparser.NewLineHandler;

public class DateHelper 
{
	static String TEST = "AAA";
	
	//Chuyển từ Date sang String					//dd-MM-yyy
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
	
	public static int getDayBetweenTwoDate(Date date1, Date date2)
	{
		long miliSecBetween = date2.getTime() - date1.getTime();
		return (int) TimeUnit.DAYS.convert(miliSecBetween, TimeUnit.MILLISECONDS);
	}
//	
//	public static int getDay(Date date)
//	{
//		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//		localDate.getday
//	}
//	
	//Lấy ra thời gian hiện tại
	public static Date getTimeNow()
	{
		return new Date();
	}
	
	public static void main(String[] args) throws ParseException 
	{
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
