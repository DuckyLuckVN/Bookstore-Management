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
import com.duan.model.Storage;
import com.duan.model.StorageDetail;

public class StorageDAO 
{
	public static ArrayList<Storage> getAll() throws SQLException
	{
		ArrayList<Storage> list = new ArrayList<Storage>();
		ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM [STORAGE]");
		
		while(rs.next())
		{
			Storage stor = readFromResultSet(rs);
			list.add(stor);
		}
		
		return list;
	}
	//Thêm dữ liệu model Storage vào bảng Storage, trả về TRUE nếu thành công, FALSE nếu thất bại.
	public static boolean insert(Storage storage) throws SQLException
	{
		String sql = "INSERT INTO [STORAGE] Values(?, ? , ?)";
		
		 PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, 
					storage.getAdminId(), 
					storage.getDescription(),
					storage.getCreatedDate());
		 int count = pre.executeUpdate();

		 return count > 0;
	}
	
	//Thêm dữ liệu model Storage lên Database, sau đó insert StorageDetail dựa vào listProduct
	public static boolean insert(Storage storage, List<BookProduct> listProduct) throws SQLException
	{
		//Thực hiện insert dữ liệu, lấy ra mã storage vừa insert và insert detail
		String sql = "INSERT INTO [STORAGE] Values(?, ? , ?)";
	
		 PreparedStatement st = JDBCHelper.createPreparedStatement(sql, 
					storage.getAdminId(), 
					storage.getDescription(),
					storage.getCreatedDate());
		 
		 boolean isSuccess = st.executeUpdate() > 0;
		 
		if (isSuccess)
		{
			//Lấy ra id mới vừa được tạo
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next());
			{
				int storage_id = rs.getInt(1);
				//insert detail từ listProduct vào
				for (BookProduct p : listProduct)
				{
					StorageDetail detail = new StorageDetail(storage_id, p.getBook().getId(), p.getAmount(), p.getPrice());
					StorageDetailDao.insert(detail);
				}
				return true;
			}
		}
		return false;
	}
	
	//Update model Storage lên database tại row storage có id = id truyền vào, trả về TRUE nếu thành công, FALSE nếu thất bại
	public static boolean update(Storage storage) throws SQLException
    {
        String sql = " UPDATE STORAGE SET admin_id=?, description=?, created_date=? WHERE id = ?";
					        		
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
        												storage.getAdminId(),
        												storage.getDescription(),
        												storage.getCreatedDate(), 
        												storage.getId());
		int count = pre.executeUpdate();
        
        return count > 0 ;
    }
	
	//cập nhật model Storage lên Database, sau đó update lại StorageDetail dựa vào listProduct
	public static boolean update(Storage storage, List<BookProduct> listProduct) throws SQLException
	{
		if (update(storage))
		{
			
			for (BookProduct p : listProduct)
			{
				//Xóa hết các dữ liệu detail cũ, và insert cái mới từ listProduct vào
				StorageDetailDao.delete(storage.getId(), p.getBook().getId());
				StorageDetail detail = new StorageDetail(storage.getId(), p.getBook().getId(), p.getAmount(), p.getPrice());
				StorageDetailDao.insert(detail);
			}
			return true;
		}
		return false;
	}
	
	//Xóa row có id = id truyền vào, trả về TRUE nếu thành công, FALSE nếu thất bại
	public static boolean delete(int id) throws SQLException
	{
		String sql = "DELETE FROM STORAGE WHERE id=?";
		PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, id);
		int count = pre.executeUpdate();
		return count > 0;
		
	}
	//Tìm và trả về model Storage có id = id truyền vào.
	public static Storage findById(int id) throws SQLException
	{
		String sql = "SELECT * FROM STORAGE WHERE id = ?";
		ResultSet rs = JDBCHelper.executeQuery(sql, id);
		
		if (rs.next()) 
		{
			return readFromResultSet(rs);
		}
		return null;
	}
	
	 public static Storage readFromResultSet(ResultSet rs) throws SQLException
	    {
	    	int id = rs.getInt(1);
	        int adminId = rs.getInt(2);
	        String description = rs.getString(3);
	        Date createdDate = rs.getDate(4);
	        
	        return new Storage(id, adminId, description, createdDate);
	    }
}
