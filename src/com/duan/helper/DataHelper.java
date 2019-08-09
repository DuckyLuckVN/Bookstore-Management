package com.duan.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.DecimalFormat;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class DataHelper 
{
	public static final String[] EXTENSIONS_FILE_IMAGE = {".JPEG", ".TIFF", ".PNG", ".JPG", ".RAW"};
	
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
	
	//Kiểm tra tên fileName truyền vào có phải thuộc một trong các định dạng trong array extensions hay không?
	public static boolean checkFileExtension(String fileName, String[] extensions)
	{
		//Nếu có . đuôi thì mới bắt đầu kiểm tra
		if (fileName.contains("."))
		{
			for (String extension : extensions)
			{
				fileName = fileName.toUpperCase();
				if (fileName.endsWith(extension))
				{
					return true;
				}
			}
			return false;
		}
		return false;
	}
	
	//Kiểm tra xem chuỗi truyền vào có phải là email hay không
		public static boolean isEmail(String email)
		{
			String regex = "\\w+@\\w+(\\.\\w+){1,3}";
			return email.matches(regex);
		}
		
		//Kiểm tra xem chuỗi truyền vào có phải là sdt hay không
		public static boolean isPhoneNumber(String email)
		{
			String regex = "[0-9]{9,11}";
			return email.matches(regex);
		}
	
	//Lấy ra đường dẫn URL đến source project
	public static File getFileFromSource(String path)
	{
		return new File(DateHelper.class.getResource(path).getPath());
	}
	
	//Tra ve duong dan file source goc cua ung dung
		public static String getRootSource()
		{
			return DataHelper.class.getResource("/").toString();
		}
	
	//Ghi file vào source theo đường dẫn của file
	public static void writeFileToSource(byte[] arrayData, String path) throws IOException
	{
		File file = new File(getFileFromSource("/").getAbsolutePath() + path);
		System.out.println("FileHelper:" + file);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(arrayData);
		fos.close();
	}
	
	public static byte[] getArrayByteFromFile(File file) throws IOException
	{
		byte[] array = Files.readAllBytes(file.toPath());
		return array;
	}
	
	
	
	//Trả về chuỗi đã được định dạng lại theo kiểu của đồng tiền
	public static String getFormatForMoney(double input)
	{
		DecimalFormat decimalFormat = new DecimalFormat("###,###.##");
		return decimalFormat.format(input);
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
		System.out.println(isInteger("a"));;
	}
	
	
}
