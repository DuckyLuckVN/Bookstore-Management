package com.duan.main;

import com.duan.helper.SettingSave;
import com.duan.view.LoginJFrame;
import com.duan.view.SettingJDialog;

public class Main 
{
	public static void main(String[] args) 
	{
		//Kiểm tra xem file save setting có tồn tại hay không
		if (SettingSave.isFileSettingExist())
		{
			new LoginJFrame().setVisible(true);
		}
		else
		{
			new SettingJDialog().setVisible(true);
		}
	}
}
