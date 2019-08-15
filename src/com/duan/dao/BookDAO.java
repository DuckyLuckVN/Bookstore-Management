
package com.duan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import com.duan.helper.JDBCHelper;
import com.duan.model.Book;
import com.duan.model.Location;


public class BookDAO 
{
    public static ArrayList<Book> getAll() throws SQLException
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
    public static boolean insert(Book b) throws SQLException
    {
        String sql = "INSERT INTO BOOK Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
        											b.getId(), 
        											b.getTitle(), 
        											b.getCategoryId(),
									        		b.getPageNum(), 
									        		b.getAuthorId(), 
									        		b.getAmount(), 
									        		b.getPublisherId(),
									        		b.getPublicationYear(), 
									        		b.getPrice(), 
									        		b.getImage(),
									        		b.getLocationId(),
									        		b.getDescription(),
									        		b.getIntroduce(),
									        		b.getCreatedDate());
        
        int count = pre.executeUpdate();
        return count > 0;
    }
    
    //Update model Book lên database tại row book có id = id truyền vào, trả về TRUE nếu thành công, FALSE nếu thất bại
    public static boolean update(Book b, String id) throws SQLException
    {
        String sql = " UPDATE BOOK SET id=?, "
					        		+ "title=?, "
					        		+ "category_id=?, "
					        		+ "page_num=?, "
					                + "author_id=?, "
					                + "amount=?, "
					                + "publisher_id=?, "
					                + "publication_year=?,"
					                + "price=?, "
					                + "image=?, "
					                + "location_id=?, "
					                + "description=?, "
					                + "introduce=?,"
					                + "created_date=? "
					                + "WHERE ID = ?";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, 
		        								b.getId(), 
		        								b.getTitle(), 
								        		b.getCategoryId(), 
								        		b.getPageNum(),
								        		b.getAuthorId(),
								        		b.getAmount(),
								        		b.getPublisherId(),
								        		b.getPublicationYear(),
								        		b.getPrice(), 
								        		b.getImage(), 
								        		b.getLocationId(),
								        		b.getDescription(), 
								        		b.getIntroduce(),
								        		b.getCreatedDate(),
								        		id);
		int count = pre.executeUpdate();
        
        return count > 0 ;
    }
    
    //Xóa row có id = id truyền vào, trả về TRUE nếu thành công, FALSE nếu thất bại
    public static boolean delete(String id) throws SQLException
   
     {
        String sql = "DELETE FROM BOOK Where id = ?";

        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, id);
        int count = pre.executeUpdate();
        return count > 0;

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
    
    //Trả về số lượng sách có thể thao tác
    public static int getAmountAvailable(String id) throws SQLException
    {
    	Book book = findByID(id);
    	
    	if (book != null)
    	{
    		return book.getAmount();
    	}
    	
    	return 0;
    }
    
    //Trả về số sách đã bán được dựa trên ID
    public static int getCountSold(String id) throws SQLException
    {
    	PreparedStatement st = JDBCHelper.createPreparedStatement("{call sp_getCountBookSold(?) }", id);
    	ResultSet rs = st.executeQuery();
    	if (rs.next())
    	{
    		return rs.getInt(1);
    	}
    	return 0;
    }
    
    //Trả về số sách đang được thuê
    public static int getCountBeingRented(String id) throws SQLException
    {
    	PreparedStatement st = JDBCHelper.createPreparedStatement("{call sp_getCountBookBeingRented(?) }", id);
    	ResultSet rs = st.executeQuery();
    	if (rs.next())
    	{
    		return rs.getInt(1);
    	}
    	return 0;
    }
    
    //Trả về model Book dựa trên ResultSet truyền vào.
    public static Book readFromResultSet(ResultSet rs) throws SQLException
    {
    	String id = rs.getString(1);
        String title = rs.getString(2);
        String categoryId = rs.getString(3);
        int  pageNum = rs.getInt(4);
        int authorId = rs.getInt(5);
        int amount =  rs.getInt(6);
        int  publisherId = rs.getInt(7);
        int publicationYear = rs.getInt(8) ;
        double money = rs.getDouble(9);
        String image = rs.getString(10);
        String locationId = rs.getString(11);
        String description = rs.getString(12);
        String introcuce = rs.getString(13);
        Date createdDate = rs.getDate(14);
        
        return new Book(id, title, categoryId, pageNum, authorId, amount, publisherId, publicationYear, money, image, locationId, description, introcuce, createdDate);
    }
    
    public static void main(String[] args) throws SQLException 
    {
    	System.out.println(BookDAO.findByID("JH42").getImage());
    }
}
