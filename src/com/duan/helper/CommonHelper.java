package com.duan.helper;

public class CommonHelper 
{
	public static boolean isInteger(String number)
	{
		try 
		{
			Integer.parseInt(number);
			return true;
		} 
		catch (NumberFormatException e) 
		{
			return false;
		}
	}
	
	public static boolean isDouble(String number)
	{
		try 
		{
			Double.parseDouble(number);
			return true;
		} 
		catch (NumberFormatException e) 
		{
			return false;
		}
	}
	
	public static boolean isFloat(String number)
	{
		try 
		{
			Float.parseFloat(number);
			return true;
		} 
		catch (NumberFormatException e) 
		{
			return false;
		}
	}
	
	public static int getInt(String number)
	{
		return Integer.parseInt(number);
	}
	
	public static double getDouble(String number)
	{
		return Double.parseDouble(number);
	}
	
	public static float getFloat(String number)
	{
		return Float.parseFloat(number);
	}
	
	public static String getHocLuc(double diem)
	{
		String hocLuc = "";
		if (diem >= 9)
			hocLuc = "Xuất Sắc";
		else if (diem >= 8)
			hocLuc = "Giỏi";
		else if (diem >= 6.5)
			hocLuc = "Khá";
		else if (diem >= 5)
			hocLuc = "Trung Bình";
		else if (diem >= 3)
			hocLuc = "Yếu";
		else
			hocLuc = "Kém";
		
		return hocLuc;
	}
}
