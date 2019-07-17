package com.duan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.duan.helper.JDBCHelper;
import com.duan.model.Order;

public class OrderDAO 
{
    public ArrayList<Order> getAll() throws SQLException
    {
        ArrayList<Order> list = new ArrayList<>();
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM ORDER");

        while(rs.next())
        {
        	Order order = readFromResultSet(rs);
        	list.add(order);
        }
        return list;
    }
    
    public boolean insert(Order order) throws SQLException
    {
        String sql = "INSERT INTO ORDER Values(?, ?, ?, ?)";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
        										order.getUserId(), 
        										order.getAdminId(), 
        										order.getFullname(),
        										order.getDateCreated());
        int count = pre.executeUpdate();
        
        return count > 0;
    }
    
    public  boolean update(Order order, int id) throws SQLException
    {
        String sql = "UPDATE ORDER SET user_id = ?, "
        							+ "admin_id, "
        							+ "fullname = ?,"
        							+ "date_created = ? "
        			+ "WHERE id = ?";

        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
        											order.getUserId(),
        											order.getAdminId(),
        											order.getFullname(),
        											order.getDateCreated(),
        											id);
        int count = pre.executeUpdate();
        return count > 0;

    }
    
    public boolean delete(int id) throws SQLException
    {
        String sql = "DELETE FROM ORDER Where id = ?";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, id);
        int count = pre.executeUpdate();
        return count > 0;
    }
    
    public Order findByID(int id) throws SQLException
    {
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM ORDER Where id =?", id);
        
        if (rs.next())
        {
        	return readFromResultSet(rs);
        }
        
        return null;
    }
    
    public Order readFromResultSet(ResultSet rs) throws SQLException
    {
    	int id = rs.getInt(1);
        int userId = rs.getInt(2);
        int adminId = rs.getInt(3);
        String fullname = rs.getString(4);
        Date dateCreated = rs.getDate(5);
        
        return new Order(id, userId, adminId, fullname, dateCreated);
    }

}