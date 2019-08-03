package com.duan.dao;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.duan.helper.JDBCHelper;
import com.duan.model.BookProduct;
import com.duan.model.BookLost;
import com.duan.model.BookLostDetail;
import com.duan.model.Order;
import com.duan.model.OrderDetail;

public class BookLostDAO 
{
    public static ArrayList<BookLost> getAll() throws SQLException
    {
        ArrayList<BookLost> list = new ArrayList<>();
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM BOOK_LOST");

        while(rs.next())
        {
        	BookLost lostBook = readFromResultSet(rs);
        	list.add(lostBook);
        }
        return list;
    }
    
    public static boolean insert(BookLost e) throws SQLException
    {
        String sql = "INSERT INTO BOOK_LOST Values(?, ?, ?)";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
        										e.getRentbookId(), 
        										e.getAdminId(), 
        										e.getCreatedDate());
        int count = pre.executeUpdate();
        
        return count > 0;
    }
    
    public static boolean insert(BookLost bookLost, List<BookProduct> products) throws SQLException
    {
        String sql = "INSERT INTO BOOK_LOST Values(?, ?, ?)";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
        										bookLost.getRentbookId(), 
        										bookLost.getAdminId(), 
        										bookLost.getCreatedDate());
        int count = pre.executeUpdate();
        if (count > 0)
        {
        	for (BookProduct p : products)
        	{
        		BookLostDetail detail = new BookLostDetail(bookLost.getRentbookId(), p.getBook().getId(), p.getAmount(), p.getPrice());
        		BookLostDetailDAO.insert(detail);
        	}
        	return true;
        }

        return false;
    }
    
    public static boolean update(BookLost bookLost, int rentbook_id) throws SQLException
    {
        String sql = "UPDATE BOOK_LOST SET rentbook_id = ?, "
        							+ "admin_id = ?, "
        							+ "created_date = ? "
        							+ "WHERE rentbook_id = ?";

        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
        											bookLost.getRentbookId(),
        											bookLost.getAdminId(),
        											bookLost.getCreatedDate(),
        											rentbook_id);
        int count = pre.executeUpdate();
        return count > 0;

    }
    
    public static boolean update(BookLost bookLost, List<BookProduct> products) throws SQLException
    {
        if (findByID(bookLost.getRentbookId()) != null)
        {
        	//Xóa toàn bộ chi tiết cũ đi, sau đó cập nhật lại order, và thêm lại order chi tiết
        	BookLostDetailDAO.delete(bookLost.getRentbookId());
        	update(bookLost, bookLost.getRentbookId());
        	for (BookProduct p : products)
        	{
        		BookLostDetail detail = new BookLostDetail(bookLost.getRentbookId(), p.getBook().getId(), p.getAmount(), p.getPrice());
        		BookLostDetailDAO.insert(detail);
        	}
        	return true;
        }
        
        return false;
    }
    
    public static boolean delete(int rentbook_id) throws SQLException
    {
        String sql = "DELETE FROM BOOK_LOST Where rentbook_id = ?";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, rentbook_id);
        int count = pre.executeUpdate();
        return count > 0;
    }
    
    public static BookLost findByID(int rentbook_id) throws SQLException
    {
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM BOOK_LOST Where rentbook_id =?", rentbook_id);
        
        if (rs.next())
        {
        	return readFromResultSet(rs);
        }
        
        return null;
    }
    
    public static BookLost readFromResultSet(ResultSet rs) throws SQLException
    {
    	int rentbookId = rs.getInt(1);
        int adminId = rs.getInt(2);
        Date dateCreated = rs.getDate(3);
        
        return new BookLost(rentbookId, adminId, dateCreated);
    }
    
//    public static Date convertStringTimeToDate(String stringTime) throws ParseException
//    {
//    	String StringDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
//    	SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//    	
//    	
//    	return formater.parse(StringDate + " " + stringTime);
//    	
//    }
    


}










