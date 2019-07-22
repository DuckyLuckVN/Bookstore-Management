package com.duan.model;

import java.util.Date;

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
	    switch (status) {
		case 0:
			return "Đang Thuê";
		case 1:
			return "Đã trả";
		default:
			return "Không rõ";
		}
    
    }
    
    
}
