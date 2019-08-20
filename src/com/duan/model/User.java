
package com.duan.model;

import java.util.Date;

import com.duan.helper.DataHelper;
import com.duan.helper.DateHelper;
import com.duan.helper.SettingSave;


public class User 
{
    private int id;
    private String username;
    private String password;
    private String fullname;
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;
    private boolean sex;
    private boolean isActive;
    private Date createdDate;

    public User()
    {
    	
    }
    
	public User(int id, String username, String password, String fullname, Date dateOfBirth, String email,
			String phoneNumber, boolean sex, boolean isActive, Date createdDate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.sex = sex;
		this.isActive = isActive;
		this.createdDate = createdDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
    
    public String getSearchString()
    {
    	String dateOfBirthStr = "";
    	String createdDateSrt = "";
    	if (dateOfBirthStr != null)
    		dateOfBirthStr = DateHelper.dateToString(dateOfBirth, SettingSave.getSetting().getDateFormat());
    	if (createdDate != null)
    		createdDateSrt = DateHelper.dateToString(createdDate, SettingSave.getSetting().getDateFormat());
    	
    	return id + " " + username  + " " + password  + " " + fullname  + " " + dateOfBirthStr + " " + email + " " + phoneNumber + " " + createdDateSrt;
    }
    
    
}
