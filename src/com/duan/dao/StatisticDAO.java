package com.duan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionListener;

import com.duan.helper.DataHelper;
import com.duan.helper.DateHelper;
import com.duan.helper.JDBCHelper;
import com.duan.helper.SettingSave;
import com.itextpdf.text.io.TempFileCache.ObjectPosition;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class StatisticDAO 
{
	public static Object[] getOverviewInMonth() throws SQLException
	{
		ResultSet rs = JDBCHelper.executeQuery("{call sp_getStatisticOverviewInMonth}");
		if (rs.next())
		{
			return new Object[] {rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getDouble(6)};
		}
		return null;
	}
	
	public static List<Object[]> getOrderInMonth(int month) throws SQLException
	{
		ResultSet rs = JDBCHelper.executeQuery("{call sp_getStatisticOrderInMonth(?)}", month);
		List<Object[]> list = new ArrayList<Object[]>();
		
		while (rs.next())
		{
			Object[] data = {
					rs.getString(1), 
					rs.getString(2), 
					rs.getString(3), 
					rs.getString(4),
					DateHelper.dateToString(rs.getDate(5), SettingSave.getSetting().getDateFormat()),
					rs.getInt(6),
					rs.getInt(7),
					DataHelper.getFormatForMoney(rs.getDouble(8)) + SettingSave.getSetting().getMoneySymbol()
					};
			list.add(data);
		}
		
		return list;
	}
	
	public static List<Object[]> getRentBookInMonth(int month) throws SQLException
	{
		ResultSet rs = JDBCHelper.executeQuery("{call sp_getStatisticRentbookInMonth(?)}", month);
		List<Object[]> list = new ArrayList<Object[]>();
		
		while (rs.next())
		{
			Object[] data = {
					rs.getString(1), 
					rs.getString(2), 
					rs.getInt(3), 
					rs.getInt(4),
					rs.getInt(5),
					rs.getInt(6)
					};
			list.add(data);
		}
		
		return list;
	}
	
	public static List<Object[]> getBookLostInMonth(int month) throws SQLException
	{
		ResultSet rs = JDBCHelper.executeQuery("{call sp_getStatisticBookLostInMonth(?)}", month);
		List<Object[]> list = new ArrayList<Object[]>();
		
		while (rs.next())
		{
			Object[] data = {
					rs.getString(1), 
					rs.getString(2), 
					rs.getInt(3), 
					rs.getInt(4),
					DataHelper.getFormatForMoney(rs.getDouble(5)) + SettingSave.getSetting().getMoneySymbol()
					};
			list.add(data);
		}
		
		return list;
	}
	
	public static List<Object[]> getUserInMonth(int month) throws SQLException
	{
		ResultSet rs = JDBCHelper.executeQuery("{call sp_getStatisticUserInMonth(?)}", month);
		List<Object[]> list = new ArrayList<Object[]>();
		
		while (rs.next())
		{
			Object[] data = {
					rs.getInt(1), 
					rs.getString(2), 
					rs.getString(3), 
					rs.getString(4),
					DateHelper.dateToString(rs.getDate(5), SettingSave.getSetting().getDateFormat()),
					DateHelper.dateToString(rs.getDate(6), SettingSave.getSetting().getDateFormat())
					};
			list.add(data);
		}
		
		return list;
	}
	
	public static List<Object[]> getStorageInMonth(int month) throws SQLException
	{
		ResultSet rs = JDBCHelper.executeQuery("{call sp_getStatisticStorageInMonth(?)}", month);
		List<Object[]> list = new ArrayList<Object[]>();
		
		while (rs.next())
		{
			Object[] data = {
					rs.getString(1), 
					rs.getString(2), 
					rs.getInt(3), 
					DataHelper.getFormatForMoney(rs.getDouble(4)) + SettingSave.getSetting().getMoneySymbol()
					};
			list.add(data);
		}
		
		return list;
	}
	
	public static List<Object[]> getIncomeInMonth(int month) throws SQLException
	{
		ResultSet rs = JDBCHelper.executeQuery("{call sp_getStatisticIncome(?)}", month);
		List<Object[]> list = new ArrayList<Object[]>();
		
		while (rs.next())
		{
			Object[] data = {
					rs.getString(1), 
					rs.getString(2), 
					DataHelper.getFormatForMoney(rs.getDouble(3)) + SettingSave.getSetting().getMoneySymbol(),
					DataHelper.getFormatForMoney(rs.getDouble(4)) + SettingSave.getSetting().getMoneySymbol(),
					DataHelper.getFormatForMoney(rs.getDouble(5)) + SettingSave.getSetting().getMoneySymbol(),
					DataHelper.getFormatForMoney(rs.getDouble(6)) + SettingSave.getSetting().getMoneySymbol(),
					DataHelper.getFormatForMoney(rs.getDouble(7)) + SettingSave.getSetting().getMoneySymbol(),
					};
			list.add(data);
		}
		
		return list;
	}
	
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getOrderInMonth(8).size());
	}
}
