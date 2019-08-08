package com.duan.model;

import java.util.Date;

public class Storage 
{
	private int id;
	private int adminId;
	private String description;
	private Date createdDate;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getAdminId() {
		return adminId;
	}
	
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public Storage(int id, int adminId, String description, Date createdDate) 
	{
		super();
		this.id = id;
		this.adminId = adminId;
		this.description = description;
		this.createdDate = createdDate;
	}
	
}
