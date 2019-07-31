package com.duan.dao;

import java.security.interfaces.RSAKey;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.duan.helper.JDBCHelper;
import com.duan.model.Book;
import com.duan.model.BookProduct;
import com.duan.model.RentBook;
import com.duan.model.RentBookDetail;


public class RentBookDAO 
{
    public static ArrayList<RentBook> getAll() throws SQLException
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
    
    public static boolean insert(RentBook rb) throws SQLException
    {
        String sql = "INSERT INTO RENTBOOK Values(?, ?, ?, ?, ?)";

        PreparedStatement st = JDBCHelper.createPreparedStatement(sql, 
        											rb.getUserId(), 
        											rb.getAdminId(),
        											rb.getCreatedDate(), 
        											rb.getReturnedDate(),
        											rb.getStatus());
        int count = st.executeUpdate();
        return  count > 0;

    }
    
    public static boolean insert(RentBook rb, List<BookProduct> bookProducts) throws SQLException
    {
        String sql = "INSERT INTO RENTBOOK Values(?, ?, ?, ?, ?)";

        PreparedStatement st = JDBCHelper.createPreparedStatement(sql, 
        											rb.getUserId(), 
        											rb.getAdminId(),
        											rb.getCreatedDate(), 
        											rb.getReturnedDate(),
        											rb.getStatus());
        int count = st.executeUpdate();
        if (count > 0 && st.getGeneratedKeys().next())
        {
        	
        	int rentbook_id = st.getGeneratedKeys().getInt(1);
        	for (BookProduct bProduct : bookProducts)
        	{
        		String book_id = bProduct.getBook().getId();
        		System.out.println(book_id);
        		RentBookDetail rentBookDetail = new RentBookDetail(rentbook_id, book_id, bProduct.getAmount(), bProduct.getPrice());
        		RentBookDetailDAO.insert(rentBookDetail);
        	}
        	return true;
        }
        
        return false;
    }
    
    //Thực hiện xóa các dữ liệu chi tiết cũ, sau đó insert lại dữ liệu mới
    public static boolean update(RentBook rb, List<BookProduct> list) throws SQLException
    {
    	if (RentBookDAO.findById(rb.getId()) != null)
    	{
    		//Xóa các dữ liệu cũ, insert dữ liệu mới
    		RentBookDetailDAO.delete(rb.getId());
    		update(rb, rb.getId());
    		for (BookProduct bp : list)
    		{
    			RentBookDetail rentBookDetail = new RentBookDetail(rb.getId(), bp.getBook().getId(), bp.getAmount(), bp.getPrice());
    			boolean status = RentBookDetailDAO.insert(rentBookDetail);
    		}
    		return true;
    	}
    	return false;
    }
    
    public static boolean update(RentBook rb, int id) throws SQLException
    {
        String sql = "UPDATE RENTBOOK Set user_id = ?, "
        								+ "admin_id = ?, "
        								+ "created_date = ?, "
        								+ "returned_date=?, "
        								+ "status = ? "
        			+ "WHERE id = ?";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, 
        										rb.getUserId(), 
        										rb.getAdminId(), 
        										rb.getCreatedDate(), 
        										rb.getReturnedDate(),
        										rb.getStatus(),
        										id);
        int count = pre.executeUpdate();
        
        return count > 0;
    }
    
    public static boolean delete(int id) throws SQLException
    {
        String sql = "DELETE FROM RENTBOOK Where id = ?";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, id);
        int count = pre.executeUpdate();
        return count > 0;
    }
    
    public static RentBook findById(int id) throws SQLException
    {
        String sql = "SELECT * FROM RENTBOOK Where id = ? ";
        ResultSet rs = JDBCHelper.executeQuery(sql,id);
        
        if (rs.next())
        {
        	return readFromResultSet(rs);
        }
        
        return null;
    }
    
    public static RentBook readFromResultSet(ResultSet rs) throws SQLException
    {
    	int id = rs.getInt(1);
    	int userId = rs.getInt(2);
    	int adminId = rs.getInt(3);
    	Date createdDate = rs.getDate(4);
    	Date returnedDate = rs.getDate(5);
    	int status = rs.getInt(6);
    	
    	return new RentBook(id, userId, adminId, createdDate, returnedDate, status);
    }
    
    public static void main(String[] args) throws SQLException {
    	RentBook rentBook = new RentBook(30, 100, 101, new Date(), null, 0);
    	List<BookProduct> list = new ArrayList<>();
    	
    	Book book1 = new Book();
    	book1.setId("GH12");
    	
    	Book book2 = new Book();
    	book2.setId("JH42");
    	
    	list.add(new BookProduct(book1, 50, 500000));
    	list.add(new BookProduct(book2, 30, 300000));
    	
		RentBookDAO.insert(rentBook, list);
	}
}






