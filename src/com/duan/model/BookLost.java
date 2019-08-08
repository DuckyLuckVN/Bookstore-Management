package com.duan.model;

import java.util.Date;

public class BookLost 
{
	private int rentbookId;
	private int adminId;
	private Date createdDate;
	
	public BookLost()
	{
		
	}
	
	public BookLost(int rentbookId, int adminId, Date createdDate) {
		super();
		this.rentbookId = rentbookId;
		this.adminId = adminId;
		this.createdDate = createdDate;
	}
	
	public int getRentbookId() {
		return rentbookId;
	}
	public void setRentbookId(int rentbookId) {
		this.rentbookId = rentbookId;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
