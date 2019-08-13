package com.duan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.spi.DirStateFactory.Result;

import com.duan.helper.JDBCHelper;
import com.duan.model.Publisher;

public class PublisherdDAO 
{
	public static ArrayList<Publisher> getAll() throws SQLException
	{
		ArrayList<Publisher> list = new ArrayList<Publisher>();
		ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM PUBLISHER");
		while (rs.next()) 
		{
			Publisher pls = readFromResultSet(rs);
		}
		return list;
	}
	
	public static boolean insert(Publisher pub) throws SQLException
	{
		String sql = "INSERT INTO VALUES(?,?,?,?,?,?)";
		PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, pub.getName(), pub.getPhoneNumber(),
																	pub.getEmail(), pub.getAddress(),pub.getIntroduct(),
																	pub.getCreatedDate());
		int count = pre.executeUpdate();
		return count > 0;
		
	}
	public static boolean update(Publisher pub,int id) throws SQLException
	{
		String sql = "UPDATE PUBLISHER SET name = ?, phone_number= ?, email = ?, address = ?,introduct = ? ,created_date = ? WHERE ID = ?";
		PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, pub.getName(),pub.getPhoneNumber(),
																					pub.getEmail(),pub.getAddress(),pub.getIntroduct(),
																					pub.getCreatedDate(),id);
		int count = pre.executeUpdate();
		return count > 0;
	}
	public static boolean delete(Publisher pub,int id) throws SQLException
	{
		String sql = "DELETE FROM PUBLISHER WHERE ID = ?";
		PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, id);
		int count = pre.executeUpdate();
		return count > 0;
	}
	
	public static Publisher readFromResultSet(ResultSet rs) throws SQLException
	{
		String name = rs.getString(2);
		String phoneNumber = rs.getString(3);
		String email = rs.getString(4);
		String address = rs.getString(5);
		String introduct = rs.getString(6);
		Date createdDate = rs.getDate(7);
		return new Publisher(0, name, phoneNumber, email, address, introduct, createdDate);
	}
}