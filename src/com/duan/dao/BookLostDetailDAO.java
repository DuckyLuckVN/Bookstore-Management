package com.duan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duan.helper.JDBCHelper;
import com.duan.model.Book;
import com.duan.model.BookLostDetail;
import com.duan.model.BookProduct;
import com.duan.model.OrderDetail;

public class BookLostDetailDAO 
{
	//Trả về danh sách toàn bộ model BookLostDetail
    public static ArrayList<BookLostDetail> getAll() throws SQLException
    {
        ArrayList<BookLostDetail> list = new ArrayList<>();
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM [ORDER_DETAIL]");

        while(rs.next())
        {
        	BookLostDetail detail = readFromResultSet(rs);
        	list.add(detail);
        }
        return list;
    }
    
    
    public static boolean insert(BookLostDetail detail) throws SQLException
    {
        String sql = "INSERT INTO dbo.BOOK_LOST_DETAIL Values(?, ?, ?, ?)";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
        										detail.getRentbookId(), 
        										detail.getBookId(), 
        										detail.getAmount(),
        										detail.getCost());
        int count = pre.executeUpdate();
        
        return count > 0;
    }
    
    
    public static boolean update(OrderDetail detail, int rentbook_id, int book_id) throws SQLException
    {
        String sql = "UPDATE BOOK_LOST_DETAIL SET rentbook_id=?, "
        							+ "book_id=?, "
        							+ "amount=?, "
        							+ "cost=? "
        							+ "WHERE rentbook_id=? AND book_id=?";

        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
        											detail.getOrderId(),
        											detail.getBookId(),
        											detail.getAmount(),
        											detail.getPrice(),
        											rentbook_id,
        											book_id);
        int count = pre.executeUpdate();
        return count > 0;

    }
    
    public static boolean delete(int order_id) throws SQLException
    {
        String sql = "DELETE FROM BOOK_LOST_DETAIL Where rentbook_id = ?";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, order_id);
        int count = pre.executeUpdate();
        return count > 0;
    }
    
    //Trả về danh sách các sách và số lượng, phí dựa vào mã số hóa đơn @rentbook_id
    public static List<BookProduct> getListProducts(int rentbook_id) throws SQLException
    {
    	ResultSet rs = JDBCHelper.executeQuery("{call sp_getLostBookDetail(?)}", rentbook_id);
    	List<BookProduct> list = new ArrayList<>();
    	while (rs.next())
    	{
    		String book_id = rs.getString(1);
    		Book book = BookDAO.findByID(book_id);
    		int amount = rs.getInt(2);
    		double cost = rs.getDouble(3);
    		
    		BookProduct bookProduct = new BookProduct(book, amount, cost);
    		list.add(bookProduct);
    	}
    	
    	return list;
    }
    
    public static BookLostDetail findByID(int order_id, String book_id) throws SQLException
    {
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM BOOK_LOST_DETAIL Where rentbook_id =? AND book_id=?", order_id, book_id);
        
        if (rs.next())
        {
        	return readFromResultSet(rs);
        }
        
        return null;
    }
    
    public static BookLostDetail readFromResultSet(ResultSet rs) throws SQLException
    {
    	int rentbookId = rs.getInt(1);
        String bookId = rs.getString(2);
        int amount = rs.getInt(3);
        Double cost = rs.getDouble(4);
        
        return new BookLostDetail(rentbookId, bookId, amount, cost);
    }

}