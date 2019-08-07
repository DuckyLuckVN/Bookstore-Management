
package com.duan.model;

import java.util.Date;


public class Admin 
{
    private int id;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phoneNumber;
    private String image;
    private boolean sex;
    private int role;
    private boolean isActive;
    private Date createdDate;
    
    
    public Admin()
    {
    	
    }
    

	public Admin(int id, String username, String password, String fullname, String email, String phoneNumber,
			String image, boolean sex, int role, boolean isActive, Date createdDate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.image = image;
		this.sex = sex;
		this.role = role;
		this.isActive = isActive;
		this.createdDate = createdDate;
	}



	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFullname() {
		return fullname;
	}
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public boolean isSex() {
		return sex;
	}
	
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	
	public int getRole() {
		return role;
	}
	
	public void setRole(int role) {
		this.role = role;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



	public boolean isActive() {
		return isActive;
	}



	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	public String getRoleTitle()
	{
		switch (role) 
		{
		case 0: return "Giám đốc";
		case 1: return "Quản lý";
		case 2: return "Nhân viên";
		}
		return "Không xác định";
	}
    
}
