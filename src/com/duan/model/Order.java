
package com.duan.model;

import java.util.Date;


public class Order 
{
    private int id;
    private int userId;
    private int adminId;
    private Date dateCreated;

    public Order()
    {
    	
    }
    
    public Order(int id, int userId, int adminId, Date dateCreated) {
        this.id = id;
        this.userId = userId;
        this.adminId = adminId;
        this.dateCreated = dateCreated;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    
}
