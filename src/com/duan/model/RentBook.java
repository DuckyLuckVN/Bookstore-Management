package com.duan.model;

import java.sql.SQLException;
import java.util.Date;

import com.duan.dao.AdminDAO;
import com.duan.dao.UserDAO;
import com.duan.helper.DateHelper;
import com.duan.helper.SettingSave;

public class RentBook 
{
    private int id;
    private int userId;
    private int adminId;
    private Date createdDate;
    private Date returnedDate;
    private int status;

    public RentBook()
    {
    	
    }

    public RentBook(int id, int userId, int adminId, Date createdDate, Date returnedDate, int status) {
		super();
		this.id = id;
		this.userId = userId;
		this.adminId = adminId;
		this.createdDate = createdDate;
		this.returnedDate = returnedDate;
		this.status = status;
	}

	public Date getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(Date returnedDate) {
		this.returnedDate = returnedDate;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getTitleStatus()
    {
    	int DayExpiration = SettingSave.getSetting().getDayExpiration();
	    switch (status) {
		case 0:
			if (returnedDate == null && DateHelper.getDayBetweenTwoDate(createdDate, new Date()) > DayExpiration)
				return "Quá hạn";
			else
				return "Đang Thuê";
		case 1:
			return "Đã trả";
		default:
			return "Không rõ";
		}
    }
    
    public String getSearchString()
    {
    	String usernameUser = "";
    	String usernameAdmin = "";
    	String createdDateStr = "";
    	
    	try 
    	{
			usernameUser = UserDAO.findByID(userId).getUsername();
			usernameAdmin = AdminDAO.findByID(adminId).getUsername();
			createdDateStr = DateHelper.dateToString(createdDate, SettingSave.getSetting().getDateFormat());
		} 
    	catch (SQLException e) 
		{
			e.printStackTrace();
		}
    	
    	return id + " " + usernameUser + " " + usernameAdmin + " " + createdDateStr + " " + getTitleStatus();
    }
    
    public static void main(String[] args) {
		System.out.println(new Date().getDay());
	}
    
    
}
