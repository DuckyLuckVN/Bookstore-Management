package com.duan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.duan.helper.JDBCHelper;
import com.duan.model.BookProduct;
import com.duan.model.Order;
import com.duan.model.OrderDetail;

public class OrderDAO 
{
    public static ArrayList<Order> getAll() throws SQLException
    {
        ArrayList<Order> list = new ArrayList<>();
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM [ORDER]");

        while(rs.next())
        {
        	Order order = readFromResultSet(rs);
        	list.add(order);
        }
        return list;
    }
    
    public static boolean insert(Order order) throws SQLException
    {
        String sql = "INSERT INTO [ORDER] Values(?, ?, ?)";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
        										order.getUserId(), 
        										order.getAdminId(), 
        										order.getDateCreated());
        int count = pre.executeUpdate();
        
        return count > 0;
    }
    
    public static boolean insert(Order order, List<BookProduct> products) throws SQLException
    {
        String sql = "INSERT INTO [ORDER] Values(?, ?, ?)";
        Integer user_id = order.getUserId();
        if (user_id == 0)
        	user_id = null;
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
        										user_id, 
        										order.getAdminId(), 
        										order.getDateCreated());
        int count = pre.executeUpdate();
        ResultSet rs = pre.getGeneratedKeys();
        if (rs.next())
        {
        	int order_id = rs.getInt(1);
        	for (BookProduct p : products)
        	{
        		OrderDetail detail = new OrderDetail(order_id, p.getBook().getId(), p.getAmount(), p.getPrice());
        		OrderDetailDAO.insert(detail);
        	}
        }

        return count > 0;
    }
    
    public static boolean update(Order order, int id) throws SQLException
    {
        String sql = "UPDATE [ORDER] SET user_id = ?, "
        							+ "admin_id = ?, "
        							+ "date_created = ? "
        							+ "WHERE id = ?";
        Integer user_id = order.getUserId();
        if (user_id == 0)
        	user_id = null;

        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
        											user_id,
        											order.getAdminId(),
        											order.getDateCreated(),
        											id);
        int count = pre.executeUpdate();
        return count > 0;

    }
    
    public static boolean update(Order order, List<BookProduct> products) throws SQLException
    {
        if (findByID(order.getId()) != null)
        {
        	//Xóa toàn bộ chi tiết cũ đi, sau đó cập nhật lại order, và thêm lại order chi tiết
        	OrderDetailDAO.delete(order.getId());
        	update(order, order.getId());
        	for (BookProduct p : products)
        	{
        		OrderDetail detail = new OrderDetail(order.getId(), p.getBook().getId(), p.getAmount(), p.getPrice());
        		OrderDetailDAO.insert(detail);
        	}
        	return true;
        }
        
        return false;
    }
    
    public static boolean delete(int id) throws SQLException
    {
        String sql = "DELETE FROM [ORDER] Where id = ?";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, id);
        int count = pre.executeUpdate();
        return count > 0;
    }
    
    public static Order findByID(int id) throws SQLException
    {
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM [ORDER] Where id =?", id);
        
        if (rs.next())
        {
        	return readFromResultSet(rs);
        }
        
        return null;
    }
    
    public static Order readFromResultSet(ResultSet rs) throws SQLException
    {
    	int id = rs.getInt(1);
        int userId = rs.getInt(2);
        int adminId = rs.getInt(3);
        Date dateCreated = rs.getDate(4);
        
        return new Order(id, userId, adminId, dateCreated);
    }

}