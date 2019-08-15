package com.duan.model;

import java.io.Serializable;

public class Setting implements Serializable
{
	public static final String SYMBOL_USER_FULLNAME = "%user_fullname%";
	public static final String SYMBOL_USER_USERNAME = "%user_username%";
	public static final String SYMBOL_ADMIN_FULLNAME = "%admin_fullname%";
	public static final String SYMBOL_ADMIN_USERNAME = "%admin_username%";
	public static final String SYMBOL_RENT_ID = "%rent_id%";
	public static final String SYMBOL_RENT_TOTALBOOK = "%rent_totalbook%";
	public static final String SYMBOL_RENT_EXPIRATION_DAY = "%rent_expiration_day%";
	public static final String SYMBOL_RENT_CREATED_DATE = "%rent_createdDate%";
	
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
	private double costRentBook = 5000;
	private double costRentExpiration = 1000; //cost * số sach * ngày quá hạn
	private double costBookLost = 2;
	private String messageReportExpiration = "Xin chào " + SYMBOL_USER_FULLNAME + ", đon thuê số (" + SYMBOL_RENT_ID + ") "
											+ "của bạn đã quá hạn trả sách " + SYMBOL_RENT_EXPIRATION_DAY + " ngày rồi. "
											+ "Đừng để lâu quá phí phạt quá hạn trả sẽ tăng đó!.";
	
	public Setting()
	{
		
	}

	

	public Setting(String hostDB, String portDB, String nameDB, String usernameDB, String passwordDB,
			String moneySymbol, String dateFormat, String timeFormat, String usernameEmail, String passwordEmail,
			int dayExpiration, double costRentBook, double costRentExpiration, double costBookLost,
			String messageReportExpiration) {
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
		this.costRentBook = costRentBook;
		this.costRentExpiration = costRentExpiration;
		this.costBookLost = costBookLost;
		this.messageReportExpiration = messageReportExpiration;
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

	public double getCostRentBook() {
		return costRentBook;
	}

	public void setCostRentBook(double costRentBook) {
		this.costRentBook = costRentBook;
	}

	public double getCostRentExpiration() {
		return costRentExpiration;
	}

	public void setCostRentExpiration(double costRentExpiration) {
		this.costRentExpiration = costRentExpiration;
	}

	public double getCostBookLost() {
		return costBookLost;
	}

	public void setCostBookLost(double costBookLost) {
		this.costBookLost = costBookLost;
	}

	public String getMessageReportExpiration() {
		return messageReportExpiration;
	}

	public void setMessageReportExpiration(String messageReportExpiration) {
		this.messageReportExpiration = messageReportExpiration;
	}
	
	public static void main(String[] args) {
		System.out.println("Xin chào " + SYMBOL_USER_FULLNAME + ", đon thuê số (" + SYMBOL_RENT_ID + ") "
											+ "của bạn đã quá hạn trả sách " + SYMBOL_RENT_EXPIRATION_DAY + " ngày rồi. "
											+ "Đừng để lâu quá phí phạt trả muộn sẽ tăng đó!.");
	}
	
}
