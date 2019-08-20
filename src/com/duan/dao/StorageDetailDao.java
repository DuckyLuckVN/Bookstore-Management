package com.duan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.duan.helper.JDBCHelper;
import com.duan.model.BookProduct;
import com.duan.model.Storage;
import com.duan.model.StorageDetail;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class StorageDetailDao
{
	public static ArrayList<StorageDetail> getAll() throws SQLException
	{
		ArrayList<StorageDetail> list = new ArrayList<StorageDetail>();
		ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM [STORAGE_DETAIL]");
		
		while(rs.next())
		{
			StorageDetail stor = readFromResultSet(rs);
			list.add(stor);
		}
		return list;
	}
	
	//Thêm dữ liệu model StorageDetail vào bảng Storage_Detail, trả về TRUE nếu thành công, FALSE nếu thất bại.
	public static boolean insert(StorageDetail stor) throws SQLException
	{
		String sql = "INSERT INTO [STORAGE_DETAIL] Values(?, ?, ? , ?)";
		
		 PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
					stor.getStorageId(), 
					stor.getBookId(), 
					stor.getAmount(),
					stor.getPrice());
		 int count = pre.executeUpdate();

		 return count > 0;
	}
	
	//Update model Storage lên database tại row storage có id = id truyền vào, trả về TRUE nếu thành công, FALSE nếu thất bại
	public static boolean update(StorageDetail detail, int storageId) throws SQLException
    {
        String sql = " UPDATE STORAGE_DETAIL SET storage_id=?, book_id=?, description=?, price=? WHERE storage_id = ?";
					        		
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,detail.getStorageId(),detail.getBookId(),
        														detail.getAmount(),detail.getPrice(), storageId);
		int count = pre.executeUpdate();
        
        return count > 0 ;
    }
	
	//Trả về danh sách BookProduct của đơn nhập hàng storage_id
	public static List<BookProduct> getListProduct(int storage_id) throws SQLException
	{
		String sql = "SELECT * FROM [STORAGE_DETAIL] WHERE storage_id=?";
		ResultSet rs = JDBCHelper.executeQuery(sql, storage_id);
		List<BookProduct> list = new ArrayList<BookProduct>();
		
		while (rs.next())
		{
			StorageDetail detail = readFromResultSet(rs);
			BookProduct product = new BookProduct(BookDAO.findByID(detail.getBookId()), detail.getAmount(), detail.getPrice());
			list.add(product);
		}
		return list;
	}
	
	//Trả về tổng số lượng sách đã nhập vào kho của mã nhập storage_id
	public static int getTotalBook(int storage_id) throws SQLException
	{
		ResultSet rs = JDBCHelper.executeQuery("{call sp_getTotalCountBookOfStorage(?)}", storage_id);
		if(rs.next())
		{
			return rs.getInt(1);
		}
		return 0;
	}
	
	//Xóa row có id = id truyền vào, trả về TRUE nếu thành công, FALSE nếu thất bại
	public static boolean delete(int StorageId) throws SQLException
	{
		String sql = "DELETE FROM STORAGE_DETAIL WHERE storage_id=?";
		PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, StorageId);
		
		int count = pre.executeUpdate();
		return count > 0;
		
	}
	
	//Xóa row có id = id truyền vào, trả về TRUE nếu thành công, FALSE nếu thất bại
	public static boolean delete(int StorageId, String book_id) throws SQLException
	{
		String sql = "DELETE FROM STORAGE_DETAIL WHERE storage_id=? AND book_id=?";
		PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, StorageId, book_id);
		
		int count = pre.executeUpdate();
		return count > 0;
		
	}
	
	//Tìm và trả về model StorageDetail có id = id truyền vào.
	public static StorageDetail findById(int storageId) throws SQLException
	{
		String sql = "SELECT * FROM STORAGE_DETAIL WHERE id = ?";
		ResultSet rs = JDBCHelper.executeQuery(sql, storageId);
		
		if (rs.next()) 
		{
			return readFromResultSet(rs);
		}
		return null;
	}
	
	//Trả về giá tiền nhập sách của sách có id truyền vào tại thời điểm gần nhất
	public static double getClosestPriceStorageWithBook(String book_id) throws SQLException
	{
		String sql = "{call sp_getClosestPriceStorageWithBook(?)}";
		ResultSet rs = JDBCHelper.executeQuery(sql, book_id);
		if (rs.next())
		{
			return rs.getDouble(1);
		}
		return 0;
	}
	
	 public static StorageDetail readFromResultSet(ResultSet rs) throws SQLException
	    {
	    	int storageId = rs.getInt(1);
	        String bookId = rs.getString(2);
	        int amount = rs.getInt(3);
	        double price = rs.getDouble(4);
	        
	        return new StorageDetail(storageId, bookId, amount, price);
	    }

}
