
package com.duan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import com.duan.helper.JDBCHelper;
import com.duan.model.Book;


public class BookDAO 
{
    public ArrayList<Book> getAllbook() throws SQLException
    {
        ArrayList<Book> list = new ArrayList<>();
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM dbo.BOOK");

        while (rs.next())
        {
        	Book e = readFromResultSet(rs);
        	list.add(e);
        }
        return list;


    }
    
    //Thêm dữ liệu model Book vào bảng Book, trả về TRUE nếu thành công, FALSE nếu thất bại.
    public boolean insert(Book b) throws SQLException
    {
        String sql = "INSERT INTO BOOK Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
        											b.getId(), 
        											b.getTitle(), 
        											b.getCategoryId(),
									        		b.getPageNum(), 
									        		b.getAuthor(), 
									        		b.getAmount(), 
									        		b.getPublisher(),
									        		b.getPublicationYear(), 
									        		b.getMoney(), 
									        		b.getImage(),
									        		b.getDescription(),
									        		b.getCreatedDate());
        
        int count = pre.executeUpdate();
        return count > 0;
    }
    
    //Update model Book lên database tại row book có id = id truyền vào, trả về TRUE nếu thành công, FALSE nếu thất bại
    public  boolean update(Book b, String id) throws SQLException
    {
        String sql = " UPDATE BOOK SET id=?, "
					        		+ "title=?, "
					        		+ "category_id=?, "
					        		+ "page_num=?,"
					                + "author=?, "
					                + "amount=?, "
					                + "publisher=?, "
					                + "publication_year=?,"
					                + "price=?, "
					                + "image=?, "
					                + "desciption=?, "
					                + "created_date=? "
					                + "WHERE ID = ?";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, 
		        								b.getId(), 
		        								b.getTitle(), 
								        		b.getCategoryId(), 
								        		b.getPageNum(),
								        		b.getAuthor(),
								        		b.getAmount(),
								        		b.getPublisher(),
								        		b.getPublicationYear(),
								        		b.getMoney(), 
								        		b.getImage(), 
								        		b.getDescription(), 
								        		b.getCreatedDate(),
								        		id);
		int count = pre.executeUpdate();
        
        return count > 0 ;
    }
    
    //Xóa row có id = id truyền vào, trả về TRUE nếu thành công, FALSE nếu thất bại
    public boolean delete(String id)
   
     {
        String sql = "DELETE FROM BOOK Where id = ?";
        try 
        {
            PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, id);
            int count = pre.executeUpdate();
            return count > 0;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    //Tìm và trả về model Book có id = id truyền vào.
    public Book findByID(String id) throws SQLException
    {
        String sql = "SELECT * FROM BOOK Where id = ?";
        ResultSet rs = JDBCHelper.executeQuery(sql, id);
        
        if (rs.next())
        {
        	return readFromResultSet(rs);
        }

        return null;
    }
    
    //Trả về model Book dựa trên ResultSet truyền vào.
    public Book readFromResultSet(ResultSet rs) throws SQLException
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
        String description = rs.getString(11);
        Date createdDate = rs.getDate(12);
        
        return new Book(id, title, categoryId, pageNum, author, amount, publisher, publicationYear, money, image, description, createdDate);
    }
}
