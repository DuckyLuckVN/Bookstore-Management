package com.duan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duan.helper.JDBCHelper;
import com.duan.model.Book;
import com.duan.model.BookProduct;
import com.duan.model.RentBookDetail;

public class RentBookDetailDAO 
{
    public static ArrayList<RentBookDetail> getAll() throws SQLException
    {
        ArrayList<RentBookDetail> list = new ArrayList<>();
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM RENTBOOK_DETAIL");
        try {
            while (rs.next())
            {
                list.add(readFromResultSet(rs));
            }
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean insert(RentBookDetail rb) throws SQLException
    {
        String sql = "INSERT INTO RENTBOOK_DETAIL Values(?, ?, ?, ?)";

        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, 
						        		rb.getRentbookId(), 
						        		rb.getBookId(),
						        		rb.getAmount(),
						        		rb.getPrice());
        int count = pre.executeUpdate();
        return count > 0;
    }
    
    public static RentBookDetail findById(int rentbook_id, String book_id) throws SQLException
    {
    	String sql = "SELECT * FROM RENTBOOK_DETAIL WHERE rentbook_id=? AND book_id=?";
    	ResultSet rs = JDBCHelper.executeQuery(sql, rentbook_id, book_id);
    	if (rs.next())
    	{
    		return readFromResultSet(rs);
    	}
    	
    	return null;
    }
    
    //Trả về danh sách các sách và số lượng, giá dựa vào mã số thuê @rentbook_id
    public static List<BookProduct> getListProducts(int rentbook_id) throws SQLException
    {
    	ResultSet rs = JDBCHelper.executeQuery("{call sp_getRentBookDetail(?)}", rentbook_id);
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
    
    public static List<Book> getListBook(int rentbook_id) throws SQLException
    {
    	ResultSet rs = JDBCHelper.executeQuery("{call sp_getRentBookDetail(?)}", rentbook_id);
    	List<Book> list = new ArrayList<>();
    	while (rs.next())
    	{
    		String book_id = rs.getString(1);
    		Book book = BookDAO.findByID(book_id);
    		
    		list.add(book);
    	}
    	
    	return list;
    }
    
    //Trả về tổng số lượng sách đã thuê của đơn thuê có mã rentbook_id
    public static int getTotalBookRented(int rentbook_id) throws SQLException
    {
    	ResultSet rs = JDBCHelper.executeQuery("{call sp_getTotalRentbook(?)}", rentbook_id);
    	if (rs.next())
    	{
    		return rs.getInt(1);
    	}
    	
    	return -1;
    }
    
    //Xóa hết RentBookDetail có mã rentbook là rentbook_id
    public static boolean delete(int rentbook_id) throws SQLException
    {
    	return JDBCHelper.excuteUpdate("DELETE FROM RENTBOOK_DETAIL WHERE rentbook_id=?", rentbook_id) > 0;
    }
    
    public static void main(String[] args) throws SQLException {
		System.out.println(getListProducts(111).size());
	}
    
    public static RentBookDetail readFromResultSet(ResultSet rs) throws SQLException
    {
    	int rentbookId  = rs.getInt(1);
        String bookId = rs.getString(2);
        int mount = rs.getInt(3);
        double price = rs.getDouble(4);
        
        RentBookDetail rbdetail = new RentBookDetail(rentbookId, bookId, mount, price);
        return rbdetail;
    }
    
  
}
    

