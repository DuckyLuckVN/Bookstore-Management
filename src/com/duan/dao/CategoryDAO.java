package com.duan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.duan.helper.JDBCHelper;
import com.duan.model.Category;

public class CategoryDAO 
{
    public static ArrayList<Category> getAll() throws SQLException
    {
        ArrayList<Category> list = new ArrayList<>();
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM CATEGORY");

        while (rs.next())
        {
        	Category e = readFromResultSet(rs);
        	list.add(e);
        }
        return list;
    }
    
    public static boolean insert(Category cg) throws SQLException
    {
        String sql = "INSERT INTO CATEGORY Values (?,?,?)";

        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, cg.getId(), cg.getCategoryTitle(), cg.getCategoryDescription());
        int count = pre.executeUpdate();
        
        return count > 0;
    }
    
    public boolean update(Category cg , String id) throws SQLException
    {
        String sql = "UPDATE CATEGORY Set id = ? , category_title= ?, category_description = ? where id = ?";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,cg.getId(), cg.getCategoryTitle(),cg.getCategoryDescription(), id);
        int count = pre.executeUpdate();

        return count > 0;
        

    }
    
    public static boolean delete(String id) throws SQLException
    {
        String sql = "DELETE FROM CATEGORY Where id = ?";
        PreparedStatement pre;
        
        pre = JDBCHelper.createPreparedStatement(sql, id);
        int count = pre.executeUpdate();
        return count >0;
    }
    
    public static Category findById(String id) throws SQLException
    {
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM CATEGORY Where id = ?", id);
        
        if (rs.next())
        {
        	return readFromResultSet(rs);
        }
        
        return null;
    }
    
    public static String getTitleById(String id) throws SQLException
    {
    	ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM CATEGORY Where id = ?", id);
    	if (rs.next())
    	{
    		return readFromResultSet(rs).getCategoryTitle();
    	}
    	return null;
    	
    }
    
    public static Category readFromResultSet(ResultSet rs) throws SQLException
    {
    	String id = rs.getString(1);
        String categoryTitle = rs.getString(2);
        String categoryDescription = rs.getString(3);
        
        return new Category(id, categoryTitle, categoryDescription);
    }
}
