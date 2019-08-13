package com.duan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.duan.helper.JDBCHelper;
import com.duan.model.Author;

public class AuthorDAO
{
	public static ArrayList<Author> getAll() throws SQLException
	{
		ArrayList<Author> list = new ArrayList<Author>();
		String sqlString = "SELECT * FROM AUTHOR";
		ResultSet rs = JDBCHelper.executeQuery(sqlString);
		while(rs.next())
		{
			Author at = readFromResultSet(rs);
			list.add(at);
		}
		return list;
	}
	
	public static boolean insert (Author at) throws SQLException
	{
		String sql = "INSERT INTO AUTHOR VALUES (?,?,?,?,?,?)";
		PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,at.getFullName(), at.getDateOfBirth(),
																	at.getDateOfDeath(), at.getImage(), at.getIntroduce(),
																	at.getCreatedDate());
		int count = pre.executeUpdate();
		return count > 0;
	}
	
	public static boolean update(Author at,int id) throws SQLException
	{
		String sql = "UPDATE AUTHOR SET fullname = ?, date_of_birth = ?, date_of_death = ?, image = ?, introduce = ?, created_date = ? WHERE ID = ?";
		PreparedStatement pret = JDBCHelper.createPreparedStatement(sql, at.getFullName(), at.getDateOfBirth(),
																	at.getDateOfDeath(), at.getImage(), at.getIntroduce(),
																	at.getCreatedDate(), id);
		int count = pret.executeUpdate();
		return count > 0 ;
	}
	public static boolean delete(Author at,int id) throws SQLException
	{
		String sql = "DELETE FROM AUTHOR WHERE ID = ?";
		PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, id);
		int count = pre.executeUpdate();
		return count > 0;
		
	}
	
	public static Author readFromResultSet(ResultSet rs) throws SQLException
	{
		String fullName = rs.getString(2);
		Date dateOfBirth = rs.getDate(3);
		Date dateOfDeath = rs.getDate(4);
		String image = rs.getString(5);
		String introduce = rs.getString(6);
		Date createdDate = rs.getDate(7);
		
		return new Author(0, fullName, dateOfBirth, dateOfDeath, image, introduce, createdDate);
	}
}
