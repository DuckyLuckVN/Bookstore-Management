package com.duan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.duan.helper.JDBCHelper;
import com.duan.model.Book;
import com.duan.model.Order;
import com.duan.model.Storage;

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
		String sql = "INSERT INTO [STORAGE] Values(?, ?, ? , ?)";
		
		 PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
					storage.getId(), 
					storage.getAdminId(), 
					storage.getDescription(),
					storage.getCreatedDate());
		 int count = pre.executeUpdate();

		 return count > 0;
	}
	//Update model Storage lên database tại row storage có id = id truyền vào, trả về TRUE nếu thành công, FALSE nếu thất bại
	public static boolean update(Storage storage, int id) throws SQLException
    {
        String sql = " UPDATE STORAGE SET id=? adminId=? description=? createdDate=? WHERE id = ?";
					        		
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,storage.getId(),storage.getAdminId(),
        															storage.getDescription(),storage.getCreatedDate(), id);
		int count = pre.executeUpdate();
        
        return count > 0 ;
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
