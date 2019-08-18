package com.duan.model;

import java.util.Date;

public class Publisher 
{
	private int id;
	private String name;
	private String phoneNumber;
	private String email;
	private String address;
	private String introduct;
	private Date createdDate;
	
	
	public Publisher(int id, String name, String phoneNumber, String email, 
						String address, String introduct,Date createdDate ) 
	{
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.introduct = introduct;
		createdDate = createdDate;
	}
	
	public Publisher() 
	{

	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIntroduct() {
		return introduct;
	}
	public void setIntroduct(String introduct) {
		this.introduct = introduct;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		createdDate = createdDate;
	}
	
	
}
