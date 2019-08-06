package com.duan.model;

import java.io.Serializable;

public class Setting implements Serializable
{
	private String hostDB = "localhost";
	private String portDB = "1433";
	private String nameDB = "BookStore";
	private String usernameDB = "sa";
	private String passwordDB = "123";
	private String moneySymbol = "đ";
	private String dateFormat = "dd-MM-yyyy";
	private String timeFormat = "hh:mm:ss";
	private String usernameEmail = "razzermkd@gmail.com";
	private String passwordEmail = "123456789";
	private int dayExpiration = 7;
	
	public Setting()
	{
		
	}

	
	
	public Setting(String hostDB, String portDB, String nameDB, String usernameDB, String passwordDB,
			String moneySymbol, String dateFormat, String timeFormat, String usernameEmail, String passwordEmail,
			int dayExpiration) {
		super();
		this.hostDB = hostDB;
		this.portDB = portDB;
		this.nameDB = nameDB;
		this.usernameDB = usernameDB;
		this.passwordDB = passwordDB;
		this.moneySymbol = moneySymbol;
		this.dateFormat = dateFormat;
		this.timeFormat = timeFormat;
		this.usernameEmail = usernameEmail;
		this.passwordEmail = passwordEmail;
		this.dayExpiration = dayExpiration;
	}



	public String getUsernameEmail() {
		return usernameEmail;
	}


	public void setUsernameEmail(String usernameEmail) {
		this.usernameEmail = usernameEmail;
	}


	public String getPasswordEmail() {
		return passwordEmail;
	}


	public void setPasswordEmail(String passwordEmail) {
		this.passwordEmail = passwordEmail;
	}


	public int getDayExpiration() {
		return dayExpiration;
	}


	public void setDayExpiration(int dayExpiration) {
		this.dayExpiration = dayExpiration;
	}


	public String getHostDB() {
		return hostDB;
	}
	public void setHostDB(String hostDB) {
		this.hostDB = hostDB;
	}
	public String getPortDB() {
		return portDB;
	}
	public void setPortDB(String portDB) {
		this.portDB = portDB;
	}
	public String getNameDB() {
		return nameDB;
	}
	public void setNameDB(String nameDB) {
		this.nameDB = nameDB;
	}
	public String getUsernameDB() {
		return usernameDB;
	}
	public void setUsernameDB(String usernameDB) {
		this.usernameDB = usernameDB;
	}
	public String getPasswordDB() {
		return passwordDB;
	}
	public void setPasswordDB(String passwordDB) {
		this.passwordDB = passwordDB;
	}
	public String getMoneySymbol() {
		return moneySymbol;
	}
	public void setMoneySymbol(String moneySymbol) {
		this.moneySymbol = moneySymbol;
	}
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public String getTimeFormat() {
		return timeFormat;
	}
	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}
	
	
}
