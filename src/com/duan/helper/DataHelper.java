package com.duan.helper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

public class DataHelper 
{
	//Kiểm tra độ dài của chuỗi đang truyền vào
	public static boolean lengthRange(String str, int min, int max)
	{
		if (str.length() >= min && str.length() <= max)
		{
			return true;
		}
		return false;
	}
	
	//Kiểm tra chuỗi data có chứa chuỗi find trong đó hay không
	public static boolean contain(String data, String find)
	{
		data = data.toLowerCase();
		find = find.toLowerCase();
		String regex = "^(?i)[\\w\\p{L} ]*" + find + "[\\w\\p{L} ]*$";
		System.out.println(regex);
		if (find.length() != 0)
		{
			if (data.matches(regex))
			{
				return true;
			}
		}
		return false;
	}
	
	//Kiểm tra chuỗi truyền vào có phải là số nguyên không
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
	
	//Kiểm tra chuỗi truyền vào có phải là số thực không
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
	
	//Kiểm tra chuỗi truyền vào có phải là số thực kiểu float không
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
	
	//Trả về kiểu số nguyên từ chuỗi number truyền vào
	public static int getInt(String number)
	{
		return Integer.parseInt(number);
	}
	
	//Trả về kiểu double từ chuỗi truyền vào
	public static double getDouble(String number)
	{
		return Double.parseDouble(number);
	}
	
	//Trả về kiểu float từ chuỗi truyền vào
	public static float getFloat(String number)
	{
		return Float.parseFloat(number);
	}
	
	//Hàm trả về học lực dựa vào điểm truyền vào
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
	
	public static void main(String[] args) 
	{
		System.out.println(contain("Nguyễn Đại Hào", "á"));
	}
	
	
}
