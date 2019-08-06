package com.duan.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.duan.model.Setting;

public class SettingSave 
{
	public static final String URL_SAVE_FILE = "C:\\temp\\";
	public static final String FILE_NAME = "SettingBookStore.dat";
	public static final String PATH_SAVE_FILE = URL_SAVE_FILE + FILE_NAME;
	
	public static Setting settingSave = new Setting();
	
	static
	{
		loadSetting();
	}
	
	public static Setting getSetting()
	{
		return settingSave;
	}
	
	public static void setSetting(Setting setting)
	{
		settingSave = setting;
	}
	
	//Cập nhật lại các giá trị trong Setting thành giá trị mặc định
	public static void setSettingToDefault()
	{
		setSetting(new Setting());
	}
	
	//Thực hiện đọc file Setting lên sau đó lưu vào SettingSave
	public static boolean loadSetting()
	{
		File file = new File(URL_SAVE_FILE);
		ObjectInputStream ois = null;
		
		
		if (file.exists() && new File(PATH_SAVE_FILE).exists())
		{
			try 
			{
				ois = new ObjectInputStream(new FileInputStream(PATH_SAVE_FILE));
				Setting setting = (Setting) ois.readObject();
				
				//Ghi đè lại setting
				setSetting(setting);
			} 
			catch (IOException e) 
			{

				e.printStackTrace();
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
			finally 
			{
				try 
				{
					ois.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
			
		}
		return false;
	}
	
	//Thực hiện ghi file object settingSave theo đường dẫn URL_SAVE_FILE
	public static boolean writeSetting()
	{
		File folder = new File(URL_SAVE_FILE);
		ObjectOutputStream oos = null;
		
		//Nếu folder chứa file chưa tồn tại hoặc ko có thì tạo đường dẫn tới file đó.
		if (!folder.exists()) {folder.mkdir();}
		
		File file = new File(PATH_SAVE_FILE);
		try 
		{
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(getSetting());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				oos.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	//Trả về TRUE nếu có tồn tại file setting, FALSE nếu chưa tồn tại
	public static boolean isFileSettingExist()
	{
		return new File(PATH_SAVE_FILE).exists();
	}
	
	public static void main(String[] args) {
		writeSetting();
	}
}
