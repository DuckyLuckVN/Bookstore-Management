package com.duan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.duan.helper.JDBCHelper;
import com.duan.model.Book;
import com.duan.model.BookProduct;
import com.duan.model.Order;
import com.duan.model.OrderDetail;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class OrderDetailDAO 
{
	//Trả về danh sách toàn bộ model OrderDetail
    public static ArrayList<OrderDetail> getAll() throws SQLException
    {
        ArrayList<OrderDetail> list = new ArrayList<>();
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM [ORDER_DETAIL]");

        while(rs.next())
        {
        	OrderDetail detail = readFromResultSet(rs);
        	list.add(detail);
        }
        return list;
    }
    
    
    public static boolean insert(OrderDetail detail) throws SQLException
    {
        String sql = "INSERT INTO dbo.ORDER_DETAIL Values(?, ?, ?, ?)";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
        										detail.getOrderId(), 
        										detail.getBookId(), 
        										detail.getAmount(),
        										detail.getPrice());
        int count = pre.executeUpdate();
        
        return count > 0;
    }
    
    
    public static boolean update(OrderDetail detail, int order_id) throws SQLException
    {
        String sql = "UPDATE ORDER_DETAIL SET order_id = ?, "
        							+ "book_id, "
        							+ "amount = ?, "
        							+ "price = ? "
        							+ "WHERE order_id = ?";

        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
        											detail.getOrderId(),
        											detail.getBookId(),
        											detail.getAmount(),
        											detail.getPrice(),
        											order_id);
        int count = pre.executeUpdate();
        return count > 0;

    }
    
    public static boolean delete(int order_id) throws SQLException
    {
        String sql = "DELETE FROM ORDER_DETAIL Where order_id = ?";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, order_id);
        int count = pre.executeUpdate();
        return count > 0;
    }
    
    //Trả về danh sách các sách và số lượng, giá dựa vào mã số hóa đơn @order_id
    public static List<BookProduct> getListProducts(int order_id) throws SQLException
    {
    	ResultSet rs = JDBCHelper.executeQuery("{call sp_getOrderDetail(?)}", order_id);
    	List<BookProduct> list = new ArrayList<>();
    	while (rs.next())
    	{
    		String book_id = rs.getString(1);
    		Book book = BookDAO.findByID(book_id);
    		int amount = rs.getInt(2);
    		double price = rs.getDouble(3);
    		
    		BookProduct bookProduct = new BookProduct(book, amount, price);
    		list.add(bookProduct);
    	}
    	
    	return list;
    }
    
    //Trả về tổng số tiền của đơn hàng có mã là @order_id
    public static double getTotalPrice(int order_id) throws SQLException
    {
    	ResultSet rs = JDBCHelper.executeQuery("{call sp_getTotalPriceInOrder(?)}", order_id);

    	if (rs.next())
    	{
    		return rs.getDouble(1);
    	}
    	return 0;
    }
    
    //Trả về tổng số lượng sách của đơn hàng có mã là @order_id
    public static int getTotalAmountBook(int order_id) throws SQLException
    {
    	ResultSet rs = JDBCHelper.executeQuery("{call sp_getCountBookInOrder(?)}", order_id);

    	if (rs.next())
    	{
    		return rs.getInt(1);
    	}
    	return 0;
    }
    
    
    public static OrderDetail findByID(int order_id, String book_id) throws SQLException
    {
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM dbo.ORDER_DETAIL Where order_id =? AND book_id=?", order_id, book_id);
        
        if (rs.next())
        {
        	return readFromResultSet(rs);
        }
        
        return null;
    }
    
    public static OrderDetail findByID(int order_id) throws SQLException
    {
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM dbo.ORDER_DETAIL Where order_id =?", order_id);
        
        if (rs.next())
        {
        	return readFromResultSet(rs);
        }
        
        return null;
    }
    
    public static OrderDetail readFromResultSet(ResultSet rs) throws SQLException
    {
    	int orderId = rs.getInt(1);
        String bookId = rs.getString(2);
        int amount = rs.getInt(3);
        Double price = rs.getDouble(4);
        
        return new OrderDetail(orderId, bookId, amount, price);
    }

}