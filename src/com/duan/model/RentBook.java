package com.duan.model;

import java.util.Date;

public class RentBook 
{
    private int id;
    private int userId;
    private int adminId;
    private Date createdDate;
    private int status;

    public RentBook(int id, int userId, int adminId, Date createdDate, int status) {
        this.id = id;
        this.userId = userId;
        this.adminId = adminId;
        this.createdDate = createdDate;
        this.status = status;
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
    
    
}
