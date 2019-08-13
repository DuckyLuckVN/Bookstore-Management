package com.duan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.duan.helper.JDBCHelper;

public class StaticticsDAO 
{
	public static Object[] getStatisticOverviewInMonth() throws SQLException
	{
		ResultSet rs = JDBCHelper.executeQuery("{call sp_getStatisticOverviewInMonth}");
		if (rs.next())
		{
			return new Object[] {rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getDouble(6)};
		}
		return null;
	}
}
