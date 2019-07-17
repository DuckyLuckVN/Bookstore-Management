package com.duan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.duan.helper.JDBCHelper;
import com.duan.model.RentBook;


public class RentBookDAO 
{
    public ArrayList<RentBook> getAlllRentBook() throws SQLException
    {
        ArrayList<RentBook> list = new ArrayList<>();
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM RENTBOOK");

        while (rs.next())
        {
        	RentBook e = readFromResultSet(rs);
        	list.add(e);
        }
        return list;
    }
    
    public boolean insert(RentBook rb) throws SQLException
    {
        String sql = "INSERT INTO RENTBOOK Values(?, ?, ?, ?)";

        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, 
        											rb.getUserId(), 
        											rb.getAdminId(),
        											rb.getCreatedDate(), 
        											rb.getStatus());
        int count = pre.executeUpdate();
        return  count > 0;

    }
    
    public boolean update(RentBook rb, int id) throws SQLException
    {
        String sql = "UPDATE RENTBOOK Set user_id = ?, "
        								+ "admin_id = ?, "
        								+ "created_date = ?, "
        								+ "status = ? "
        			+ "WHERE id = ?";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, 
        										rb.getUserId(), 
        										rb.getAdminId(), 
        										rb.getCreatedDate(), 
        										rb.getStatus(),
        										id);
        int count = pre.executeUpdate();
        
        return count > 0;
    }
    
    public boolean delete(int id) throws SQLException
    {
        String sql = "DELETE FROM RENTBOOK Where id = ?";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, id);
        int count = pre.executeUpdate();
        return count > 0;
    }
    
    public RentBook findById(int id) throws SQLException
    {
        String sql = "SELECT * FROM RENTBOOK Where id = ? ";
        ResultSet rs = JDBCHelper.executeQuery(sql,id);
        
        if (rs.next())
        {
        	return readFromResultSet(rs);
        }
        
        return null;
    }
    
    public RentBook readFromResultSet(ResultSet rs) throws SQLException
    {
    	int id = rs.getInt(1);
    	int userId = rs.getInt(2);
    	int adminId = rs.getInt(3);
    	Date createdDate = rs.getDate(4);
    	int status = rs.getInt(5);
    	
    	return new RentBook(id, userId, adminId, createdDate, status);
    }
}
