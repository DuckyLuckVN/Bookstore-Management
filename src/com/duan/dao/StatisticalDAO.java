package com.duan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.duan.helper.JDBCHelper;
import com.duan.model.Book;

public class StatisticalDAO {
	//Trả về model Book dựa trên ResultSet truyền vào.
	public static Book readFromResultSet(ResultSet rs) throws SQLException
    {
    	String id = rs.getString(1);
        String title = rs.getString(2);
        String categoryId = rs.getString(3);
        int  pageNum = rs.getInt(4);
        String author = rs.getString(5);
        int amount =  rs.getInt(6);
        String  publisher = rs.getString(7);
        int publicationYear = rs.getInt(8) ;
        double money = rs.getDouble(9);
        String image = rs.getString(10);
        String locationId = rs.getString(11);
        String description = rs.getString(12);
        Date createdDate = rs.getDate(13);
        
        return new Book(id, title, categoryId, pageNum, author, amount, publisher, publicationYear, money, image, locationId, description, createdDate);
    }
	
	//Tìm và trả về model Book có id = id truyền vào.
    public static Book findByID(String id) throws SQLException
    {
        String sql = "SELECT * FROM BOOK Where id = ?";
        ResultSet rs = JDBCHelper.executeQuery(sql, id);
        
        if (rs.next())
        {
        	return readFromResultSet(rs);
        }

        return null;
    }
    
   
}
