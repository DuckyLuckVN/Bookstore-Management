package com.duan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.duan.helper.JDBCHelper;
import com.duan.model.Location;
import com.duan.model.User;

public class LocationDAO 
{
    public static ArrayList<Location> getAll() throws SQLException
    {
        ArrayList<Location> list = new ArrayList<>();
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM dbo.LOCATION");

        while (rs.next())
        {
        	Location e = readFromResultSet(rs);
        	list.add(e);
        }
        return list;

    }
    
    public static boolean insert(Location e) throws SQLException
    {
        String sql = "INSERT INTO dbo.LOCATION Values(?, ?, ?, ?)";

        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
									        		e.getId(),
									        		e.getLocationName(),
									        		e.getMaxStorage(),
									        		e.getDescription());
        
         int count = pre.executeUpdate();
         
         return count > 0;
    }
    
    public static boolean update(Location e , String id) throws SQLException
    {
        String sql = "UPDATE dbo.LOCATIOn SET id=?, location_name=?, max_storage=?, description=? WHERE id=?";

        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, 
								        		e.getId(),
								        		e.getLocationName(),
								        		e.getMaxStorage(),
								        		e.getDescription(),
								        		id);
        int count = pre.executeUpdate();
        return count > 0;
    }
    
    public static boolean delete(String id) throws SQLException
    {
        String sql = "DELETE FROM dbo.LOCATION Where id = ?";
        PreparedStatement pre;
        
        pre = JDBCHelper.createPreparedStatement(sql, id);
        int count = pre.executeUpdate();
        
        return count > 0;
    }
    
    public static Location findByID(String id) throws SQLException
    {
        String sql = "SELECT * FROM dbo.LOCATION WHERE id = ?";
        ResultSet rs = JDBCHelper.executeQuery(sql, id);
        
        if (rs.next())
        {
        	return readFromResultSet(rs);
        }
        return null;
    }
    
    public static Location readFromResultSet(ResultSet rs) throws SQLException
    {
    	String id = rs.getString(1);
    	String locationName = rs.getString(2);
    	int maxStorage = rs.getInt(3);
    	String description = rs.getString(4);
    	
    	return new Location(id, locationName, maxStorage, description);
    }
}
