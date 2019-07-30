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
    public static ArrayList<Admin> getAllAdmin() throws SQLException
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
    
    public static boolean insert(Admin ad) throws SQLException
    {
        String sql = "INSERT INTO ADMIN Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, 
        									ad.getUsername(), 
        									ad.getPassword() , 
        									ad.getFullname(),
        									ad.getEmail(), 
        									ad.getPhoneNumber(), 
        									ad.getImage(),
        									ad.isSex(),
        									ad.getRole(), 
        									ad.isActive(),
        									ad.getCreatedDate());
        int count = pre.executeUpdate();
        return count > 0;
    }
    
    public static boolean update(Admin ad , int id) throws SQLException
    {
        String sql = "UPDATE ADMIN SET username=?, password=?, fullname=?, "
                + "email=?, phone_number=?, image=?, sex=? role=?, isActive=? created_date=? Where id = ?";
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, 
        										ad.getUsername(), 
        										ad.getPassword(),
        										ad.getFullname(),
        										ad.getEmail(),
        										ad.getPhoneNumber(),
        										ad.getImage(),
        										ad.isSex(),
        										ad.getRole(),
        										ad.isActive(),
        										ad.getCreatedDate(),
        										id);
        int count = pre.executeUpdate();
        return count > 0;
    }
    
    public static boolean delete(int id) throws SQLException
    {
        String sql = "DELETE FROM ADMIN Where id = ?";
        PreparedStatement  pre = JDBCHelper.createPreparedStatement(sql, id);
        int count = pre.executeUpdate();
        return count > 0;
    }
    
    public static Admin findByID(int id) throws SQLException
    {
        String sql = "SELECT * FROM ADMIN Where id = ?";
        ResultSet rs = JDBCHelper.executeQuery(sql, id);
        if (rs.next())
        {
        	return readFromResultSet(rs);
        }
        return null;
    }
    
    public static Admin readFromResultSet(ResultSet rs) throws SQLException
    {
    	int id = rs.getInt(1);
    	String username = rs.getString(2);
    	String password = rs.getString(3);
    	String fullname = rs.getString(4);
    	String email = rs.getString(5);
    	String phoneNumber = rs.getString(6);
    	String image = rs.getString(7);
    	boolean sex = rs.getBoolean(8);
    	int role = rs.getInt(9);
    	boolean isActive = rs.getBoolean(10);
    	Date createdDate = rs.getDate(11);
    	
    	return new Admin(id, username, password, fullname, email, phoneNumber, image, sex, role, isActive, createdDate);
    }
    
}
