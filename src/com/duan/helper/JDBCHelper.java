package com.duan.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.duan.dao.DBConnection;

public class JDBCHelper 
{
	
	/*
	 * Input: Nhận vào câu lệnh SQL và các đối số của câu SQL nếu có.
	 * Output: Trả về PreparedStatement hoàn chỉnh với các đối số truyền vào
	 */														
	public static PreparedStatement createPreparedStatement(String sql, Object...obj) throws SQLException
	{
		PreparedStatement st = null;
		//Kiểm tra nếu có "{} tức là muốn sử dụng Store để truy vấn, thì PreparedStatement sẽ thành PrepareCall.
		if (sql.contains("{"))
			st = DBConnection.getConnection().prepareCall(sql);
		else
			st = DBConnection.getConnection().prepareStatement(sql);
		
		if (obj != null && obj.length > 0)
			for (int i = 0; i < obj.length; i++)
			{
				st.setObject(i+1, obj[i]);
			}
		
		return st;
	}
	
	/*
	 * Input: Nhận vào câu lệnh SQL và các đối số của câu SQL nếu có.
	 * Output: Số dòng bị tác động sau khi thực hiện excuteUpdate (row effected)
	 */
	public static int excuteUpdate(String sql, Object...obj) throws SQLException
	{
		int count = 0;
		PreparedStatement st = createPreparedStatement(sql, obj);
		count = st.executeUpdate();
		st.close();
		return count;
	}
	
	/*
	 * Input: Nhận vào câu lệnh SQL và các đối số của câu SQL nếu có.
	 * Output: Trả về đối tượng ResultSet chứa thông tin về dữ liệu vừa select
	 */
	public static ResultSet excuteQuery(String sql, Object...obj) throws SQLException
	{
		PreparedStatement st = createPreparedStatement(sql, obj);
		ResultSet rs = st.executeQuery();
		return rs;
	}
	
	public static void main(String[] args) throws SQLException 
	{
		Connection conn = DBConnection.getConnection();
		PreparedStatement st = conn.prepareCall("{call sp_ThongKeDiem}");
		ResultSet rs = st.executeQuery();
		
		System.out.println(rs.next());
		System.out.println(rs.getString("ChuyenDe"));
	}
}
