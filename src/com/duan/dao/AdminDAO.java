package com.duan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import com.duan.helper.JDBCHelper;
import com.duan.model.Admin;

public class AdminDAO 
{
    public ArrayList<Admin> getAllAdmin() throws SQLException
    {
        ArrayList<Admin> list = new ArrayList<>();
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM ADMIN");
        
        while (rs.next())
        {
        	Admin admin = readFromResultSet(rs);
        	list.add(admin);
        }
        return list;
    }
    
    public boolean insert(Admin ad) throws SQLException
    {
        String sql = "INSERT INTO ADMIN Values(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, 
        									ad.getUsername(), 
        									ad.getPassword() , 
        									ad.getFullname(),
        									ad.getEmail(), 
        									ad.getPhoneNumber(), 
        									ad.getRole(), 
        									ad.getCreatedDate());
        int count = pre.executeUpdate();
        return count > 0;
    }
    
    public boolean update(Admin ad , int id) throws SQLException
    {
        String sql = "UPDATE ADMIN SET username=?, password=?, fullname=?, "
                + "email=?, phone_number=?, role=?, created_date=? Where id = ?";
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, 
        										ad.getUsername(), 
        										ad.getPassword(),
        										ad.getFullname(),
        										ad.getEmail(),
        										ad.getPhoneNumber(),
        										ad.getRole(),
        										ad.getCreatedDate(),
        										id);
        int count = pre.executeUpdate();
        return count > 0;
    }
    
    public boolean delete(int id) throws SQLException
    {
        String sql = "DELETE FROM ADMIN Where id = ?";
        PreparedStatement  pre = JDBCHelper.createPreparedStatement(sql, id);
        int count = pre.executeUpdate();
        return count > 0;
    }
    
    public Admin findByID(int id) throws SQLException
    {
        String sql = "SELECT * FROM ADMIN Where id = ?";
        ResultSet rs = JDBCHelper.executeQuery(sql);
        if (rs.next())
        {
        	return readFromResultSet(rs);
        }
        return null;
    }
    
    public Admin readFromResultSet(ResultSet rs) throws SQLException
    {
    	int id = rs.getInt(1);
    	String username = rs.getString(2);
    	String password = rs.getString(3);
    	String fullname = rs.getString(4);
    	String email = rs.getString(5);
    	String phoneNumber = rs.getString(6);
    	int role = rs.getInt(7);
    	Date createdDate = rs.getDate(8);
    	
    	return new Admin(id, username, password, fullname, email, phoneNumber, role, createdDate);
    }
    
}
