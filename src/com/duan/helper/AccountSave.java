package com.duan.helper;

import java.util.Date;

import com.duan.model.Admin;

public class AccountSave 
{
	private static Admin admin = new Admin(101, "quanly", "123", "Lý Tiểu Long", "lytieulong@gmail.com", "01682439314", null, true, 2, new Date());
	
	public static void setAdmin(Admin admin) 
	{
		AccountSave.admin = admin;
	}
	
	public static Admin getAdmin()
	{
		return admin;
	}
	
	public static void removeAdmin()
	{
		admin = null;
	}
	
}
