package com.duan.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import com.duan.dao.AdminDAO;
import com.duan.dao.BookDAO;
import com.duan.dao.CategoryDAO;
import com.duan.dao.LocationDAO;
import com.duan.dao.UserDAO;
import com.duan.helper.DateHelper;
import com.duan.helper.SettingSave;
import com.duan.model.Admin;
import com.duan.model.Book;
import com.duan.model.Category;
import com.duan.model.User;
import com.duan.model.Location;

public class ExportExcel 
{
	public static boolean writeBook(File file, List<Book> listBook)
	{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sách");
		CellStyle styleHeader = workbook.createCellStyle();	
		CellStyle styleBody = workbook.createCellStyle();	
		Font fontHeader = workbook.createFont();
		Font fontBody = workbook.createFont();
		
		
		//Cấu hình font cho StyleHeader
		fontHeader.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
		fontHeader.setBold(true);
		fontHeader.setFontHeightInPoints((short) 10);
		
		//Cấu hình style cho header
		styleHeader.setFillForegroundColor(IndexedColors.BROWN.getIndex());
		styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styleHeader.setAlignment(HorizontalAlignment.CENTER);
		styleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
		styleHeader.setBorderLeft(BorderStyle.THIN);
		styleHeader.setBorderRight(BorderStyle.THIN);
		styleHeader.setFont(fontHeader);
		
		//Cấu hình font cho styleBody
		
		
		//Cấu hình style cho body
		styleBody.setBorderLeft(BorderStyle.THIN);
		styleBody.setBorderRight(BorderStyle.THIN);
		styleBody.setBorderBottom(BorderStyle.THIN);
		styleBody.setBorderTop(BorderStyle.THIN);
		styleBody.setFont(fontBody);
		styleBody.setAlignment(HorizontalAlignment.CENTER);
		styleBody.setVerticalAlignment(VerticalAlignment.CENTER);
		
		
		try 
		{
			int rowNum = 0;
			Cell cell;
			Row row;
			
			row = sheet.createRow(rowNum);
			//[Mã sách, Tên sách, Thể loại, Số trang, Tác giả, Số lượng, Nhà xuất bản, Năm xuất bản, giá bán, vị trí, ghi chú, ngày tạo]
			
			/////////////////////////////////////////////// TẠO TIÊU ĐỀ ////////////////////////////////////////////////////
			row.setHeightInPoints(18);
			//Mã sách
			cell = row.createCell(0);
			cell.setCellValue("Mã sách");
			cell.setCellStyle(styleHeader);
			
			//Tên sách
			cell = row.createCell(1);
			cell.setCellValue("Tên sách");
			cell.setCellStyle(styleHeader);
			
			//Thể loại
			cell = row.createCell(2);
			cell.setCellValue("Thể loại");
			cell.setCellStyle(styleHeader);
			
			//Số trang
			cell = row.createCell(3);
			cell.setCellValue("Số trang");
			cell.setCellStyle(styleHeader);
			
			//Tác giả
			cell = row.createCell(4);
			cell.setCellValue("Tác giả");
			cell.setCellStyle(styleHeader);
			
			//Số lượng
			cell = row.createCell(5);
			cell.setCellValue("Số lượng");
			cell.setCellStyle(styleHeader);
			
			//Nhà xuất bản
			cell = row.createCell(6);
			cell.setCellValue("Nhà xuất bản");
			cell.setCellStyle(styleHeader);
			
			//Năm xuất bản
			cell = row.createCell(7);
			cell.setCellValue("Năm xuất bản");
			cell.setCellStyle(styleHeader);
			
			//Giá bán
			cell = row.createCell(8);
			cell.setCellValue("Giá bán");
			cell.setCellStyle(styleHeader);
			
			//vị trí
			cell = row.createCell(9);
			cell.setCellValue("Vị trí");
			cell.setCellStyle(styleHeader);
			
			//Ghi chú
			cell = row.createCell(10);
			cell.setCellValue("Ghi chú");
			cell.setCellStyle(styleHeader);
			
			//Ngày tạo
			cell = row.createCell(11);
			cell.setCellValue("Ngày tạo");
			cell.setCellStyle(styleHeader);
			
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			//////////////////////////////////////////////////// INSERT NỘI DUNG /////////////////////////////////////////////////////////////////////
			//[Mã sách, Tên sách, Thể loại, Số trang, Tác giả, Số lượng, Nhà xuất bản, Năm xuất bản, giá bán, vị trí, ghi chú, ngày tạo]
			for (Book b : listBook)
			{
				rowNum++;
				row = sheet.createRow(rowNum);
				row.setHeightInPoints(20);
				
				Category category = CategoryDAO.findById(b.getCategoryId());
				Location location = LocationDAO.findByID(b.getLocationId());
				
				//Mã sách
				cell = row.createCell(0);
				cell.setCellValue(b.getId());
				cell.setCellStyle(styleBody);
				
				//Tên sách
				cell = row.createCell(1);
				cell.setCellValue(b.getTitle());
				cell.setCellStyle(styleBody);
				
				//Thể loại
				cell = row.createCell(2);
				cell.setCellValue(category.getCategoryTitle());
				cell.setCellStyle(styleBody);
				
				//Số trang
				cell = row.createCell(3);
				cell.setCellValue(b.getPageNum());
				cell.setCellStyle(styleBody);
				
				//Tác giả
				cell = row.createCell(4);
				cell.setCellValue(b.getAuthor());
				cell.setCellStyle(styleBody);
				
				//Số lượng
				cell = row.createCell(5);
				cell.setCellValue(b.getAmount());
				cell.setCellStyle(styleBody);
				
				//Nhà xuất bản
				cell = row.createCell(6);
				cell.setCellValue(b.getPublisher());
				cell.setCellStyle(styleBody);
				
				//Năm xuất bản
				cell = row.createCell(7);
				cell.setCellValue(b.getPublicationYear());
				cell.setCellStyle(styleBody);
				
				//Giá bán
				cell = row.createCell(8);
				cell.setCellValue(b.getPrice());
				cell.setCellStyle(styleBody);
				
				//vị trí
				cell = row.createCell(9);
				cell.setCellValue(location.getLocationName());
				cell.setCellStyle(styleBody);
				
				//Ghi chú
				cell = row.createCell(10);
				cell.setCellValue(b.getDescription());
				cell.setCellStyle(styleBody);
				
				//Ngày tạo
				cell = row.createCell(11);
				cell.setCellValue(DateHelper.dateToString(b.getCreatedDate(), SettingSave.getSetting().getDateFormat()));
				cell.setCellStyle(styleBody);
			}
			
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			sheet.autoSizeColumn(8);
			sheet.autoSizeColumn(9);
			sheet.autoSizeColumn(10);
			sheet.autoSizeColumn(11);
			
			//Tiến hành ghi file
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			workbook.close();
			fos.close();
			return true;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean writeUser(File file, List<User> listUser)
	{
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Khách hàng");
		CellStyle styleHeader = workbook.createCellStyle();	
		CellStyle styleBody = workbook.createCellStyle();	
		Font fontHeader = workbook.createFont();
		Font fontBody = workbook.createFont();
		
		
		//Cấu hình font cho StyleHeader
		fontHeader.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
		fontHeader.setBold(true);
		fontHeader.setFontHeightInPoints((short) 10);
		
		//Cấu hình style cho header
		styleHeader.setFillForegroundColor(IndexedColors.BROWN.getIndex());
		styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styleHeader.setAlignment(HorizontalAlignment.CENTER);
		styleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
		styleHeader.setBorderLeft(BorderStyle.THIN);
		styleHeader.setBorderRight(BorderStyle.THIN);
		styleHeader.setFont(fontHeader);
		
		//Cấu hình font cho styleBody
		
		
		//Cấu hình style cho body
		styleBody.setBorderLeft(BorderStyle.THIN);
		styleBody.setBorderRight(BorderStyle.THIN);
		styleBody.setBorderBottom(BorderStyle.THIN);
		styleBody.setBorderTop(BorderStyle.THIN);
		styleBody.setFont(fontBody);
		styleBody.setAlignment(HorizontalAlignment.CENTER);
		styleBody.setVerticalAlignment(VerticalAlignment.CENTER);
		
		
		try 
		{
			int rowNum = 0;
			Cell cell;
			Row row;
			
			row = sheet.createRow(rowNum);
			//[Mã user, tài khoản, họ tên, ngày sinh, email, số điện thoại, giới tính, ngày đăng ký]
			
			/////////////////////////////////////////////// TẠO TIÊU ĐỀ ////////////////////////////////////////////////////
			row.setHeightInPoints(18);
			//Mã số
			cell = row.createCell(0);
			cell.setCellValue("Mã số");
			cell.setCellStyle(styleHeader);
			
			//Tài khoản
			cell = row.createCell(1);
			cell.setCellValue("Tài khoản");
			cell.setCellStyle(styleHeader);
			
			//Họ và tên
			cell = row.createCell(2);
			cell.setCellValue("Họ và tên");
			cell.setCellStyle(styleHeader);
			
			//Ngày sinh
			cell = row.createCell(3);
			cell.setCellValue("Ngày sinh");
			cell.setCellStyle(styleHeader);
			
			//Email
			cell = row.createCell(4);
			cell.setCellValue("Email");
			cell.setCellStyle(styleHeader);
			
			//Số điện thoại
			cell = row.createCell(5);
			cell.setCellValue("Số điện thoại");
			cell.setCellStyle(styleHeader);
			
			//Giới tính
			cell = row.createCell(6);
			cell.setCellValue("Giới tính");
			cell.setCellStyle(styleHeader);
			
			//Ngày đăng ký
			cell = row.createCell(7);
			cell.setCellValue("Ngày đăng ký");
			cell.setCellStyle(styleHeader);
			
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			//////////////////////////////////////////////////// INSERT NỘI DUNG /////////////////////////////////////////////////////////////////////
			//[Mã user, tài khoảng, họ tên, ngày sinh, email, số điện thoại, giới tính, ngày đăng ký]
			for (User u : listUser)
			{
				rowNum++;
				row = sheet.createRow(rowNum);
				row.setHeightInPoints(20);

				//Mã số
				cell = row.createCell(0);
				cell.setCellValue(u.getId());
				cell.setCellStyle(styleBody);
				
				//Tài khoản
				cell = row.createCell(1);
				cell.setCellValue(u.getUsername());
				cell.setCellStyle(styleBody);
				
				//Họ và tên
				cell = row.createCell(2);
				cell.setCellValue(u.getFullname());
				cell.setCellStyle(styleBody);
				
				//Ngày sinh
				cell = row.createCell(3);
				cell.setCellValue(DateHelper.dateToString(u.getDateOfBirth(), SettingSave.getSetting().getDateFormat()));
				cell.setCellStyle(styleBody);
				
				//Email
				cell = row.createCell(4);
				cell.setCellValue(u.getEmail());
				cell.setCellStyle(styleBody);
				
				//Số điện thoại
				cell = row.createCell(5);
				cell.setCellValue(u.getPhoneNumber());
				cell.setCellStyle(styleBody);
				
				//Giới tính
				cell = row.createCell(6);
				cell.setCellValue((u.isSex() ? "Nam" : "Nữ"));
				cell.setCellStyle(styleBody);
				
				//Ngày đăng ký
				cell = row.createCell(7);
				cell.setCellValue(DateHelper.dateToString(u.getCreatedDate(), SettingSave.getSetting().getDateFormat()));
				cell.setCellStyle(styleBody);
			}
			
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			
			//Tiến hành ghi file
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			workbook.close();
			fos.close();
			return true;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		
		return false;
	}
	
	public static boolean writeAdmin(File file, List<Admin> listAdmin)
	{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Quản trị viên");
		CellStyle styleHeader = workbook.createCellStyle();	
		CellStyle styleBody = workbook.createCellStyle();	
		Font fontHeader = workbook.createFont();
		Font fontBody = workbook.createFont();
		
		
		//Cấu hình font cho StyleHeader
		fontHeader.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
		fontHeader.setBold(true);
		fontHeader.setFontHeightInPoints((short) 10);
		
		//Cấu hình style cho header
		styleHeader.setFillForegroundColor(IndexedColors.BROWN.getIndex());
		styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styleHeader.setAlignment(HorizontalAlignment.CENTER);
		styleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
		styleHeader.setBorderLeft(BorderStyle.THIN);
		styleHeader.setBorderRight(BorderStyle.THIN);
		styleHeader.setFont(fontHeader);
		
		//Cấu hình font cho styleBody
		
		
		//Cấu hình style cho body
		styleBody.setBorderLeft(BorderStyle.THIN);
		styleBody.setBorderRight(BorderStyle.THIN);
		styleBody.setBorderBottom(BorderStyle.THIN);
		styleBody.setBorderTop(BorderStyle.THIN);
		styleBody.setFont(fontBody);
		styleBody.setAlignment(HorizontalAlignment.CENTER);
		styleBody.setVerticalAlignment(VerticalAlignment.CENTER);
		
		
		try 
		{
			int rowNum = 0;
			Cell cell;
			Row row;
			
			row = sheet.createRow(rowNum);
			//[Mã admin, tài khoản, họ tên, email, số điện thoại, giới tính, chức vụ, tình trạng, ngày đăng ký]
			
			/////////////////////////////////////////////// TẠO TIÊU ĐỀ ////////////////////////////////////////////////////
			row.setHeightInPoints(18);
			//Mã số
			cell = row.createCell(0);
			cell.setCellValue("Mã số");
			cell.setCellStyle(styleHeader);
			
			//Tài khoản
			cell = row.createCell(1);
			cell.setCellValue("Tài khoản");
			cell.setCellStyle(styleHeader);
			
			//Họ và tên
			cell = row.createCell(2);
			cell.setCellValue("Họ và tên");
			cell.setCellStyle(styleHeader);
			
			//Email
			cell = row.createCell(3);
			cell.setCellValue("Email");
			cell.setCellStyle(styleHeader);
			
			//Số điện thoại
			cell = row.createCell(4);
			cell.setCellValue("Số điện thoại");
			cell.setCellStyle(styleHeader);
			
			//Giới tính
			cell = row.createCell(5);
			cell.setCellValue("Giới tính");
			cell.setCellStyle(styleHeader);
			
			//Chức vụ
			cell = row.createCell(6);
			cell.setCellValue("Chức vụ");
			cell.setCellStyle(styleHeader);
			
			//Tình trạng
			cell = row.createCell(7);
			cell.setCellValue("Tình trạng");
			cell.setCellStyle(styleHeader);
			
			//Ngày đăng ký
			cell = row.createCell(8);
			cell.setCellValue("Ngày đăng ký");
			cell.setCellStyle(styleHeader);
			
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			//////////////////////////////////////////////////// INSERT NỘI DUNG /////////////////////////////////////////////////////////////////////
			//[Mã admin, tài khoản, họ tên, email, số điện thoại, giới tính, chức vụ, tình trạng, ngày đăng ký]
			for (Admin a : listAdmin)
			{
				rowNum++;
				row = sheet.createRow(rowNum);
				row.setHeightInPoints(20);

				//Mã số
				cell = row.createCell(0);
				cell.setCellValue(a.getId());
				cell.setCellStyle(styleBody);
				
				//Tài khoản
				cell = row.createCell(1);
				cell.setCellValue(a.getUsername());
				cell.setCellStyle(styleBody);
				
				//Họ và tên
				cell = row.createCell(2);
				cell.setCellValue(a.getFullname());
				cell.setCellStyle(styleBody);
				
				//Email
				cell = row.createCell(3);
				cell.setCellValue(a.getEmail());
				cell.setCellStyle(styleBody);
				
				//Số điện thoại
				cell = row.createCell(4);
				cell.setCellValue(a.getPhoneNumber());
				cell.setCellStyle(styleBody);
				
				//Giới tính
				cell = row.createCell(5);
				cell.setCellValue((a.isSex() ? "Nam" : "Nữ"));
				cell.setCellStyle(styleBody);
				
				//Chức vụ
				cell = row.createCell(6);
				cell.setCellValue(a.getRoleTitle());
				cell.setCellStyle(styleBody);
				
				//Chức vụ
				cell = row.createCell(7);
				cell.setCellValue((a.isActive()) ? "Còn hoạt động" : "Đã khóa");
				cell.setCellStyle(styleBody);
				
				//Ngày đăng ký
				cell = row.createCell(8);
				cell.setCellValue(DateHelper.dateToString(a.getCreatedDate(), SettingSave.getSetting().getDateFormat()));
				cell.setCellStyle(styleBody);
			}
			
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			sheet.autoSizeColumn(8);
			
			//Tiến hành ghi file
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			workbook.close();
			fos.close();
			return true;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		
		return false;
	}
	
	public static boolean writeCategory(File file, List<Category> listCategory)
	{
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Thể loại");
		CellStyle styleHeader = workbook.createCellStyle();	
		CellStyle styleBody = workbook.createCellStyle();	
		Font fontHeader = workbook.createFont();
		Font fontBody = workbook.createFont();
		
		
		//Cấu hình font cho StyleHeader
		fontHeader.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
		fontHeader.setBold(true);
		fontHeader.setFontHeightInPoints((short) 10);
		
		//Cấu hình style cho header
		styleHeader.setFillForegroundColor(IndexedColors.BROWN.getIndex());
		styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styleHeader.setAlignment(HorizontalAlignment.CENTER);
		styleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
		styleHeader.setBorderLeft(BorderStyle.THIN);
		styleHeader.setBorderRight(BorderStyle.THIN);
		styleHeader.setFont(fontHeader);
		
		//Cấu hình font cho styleBody
		
		
		//Cấu hình style cho body
		styleBody.setBorderLeft(BorderStyle.THIN);
		styleBody.setBorderRight(BorderStyle.THIN);
		styleBody.setBorderBottom(BorderStyle.THIN);
		styleBody.setBorderTop(BorderStyle.THIN);
		styleBody.setFont(fontBody);
		styleBody.setAlignment(HorizontalAlignment.CENTER);
		styleBody.setVerticalAlignment(VerticalAlignment.CENTER);
		
		
		try 
		{
			int rowNum = 0;
			Cell cell;
			Row row;
			
			row = sheet.createRow(rowNum);
			
			//[Mã thể loại, tên thể loại, ghi chú]
			/////////////////////////////////////////////// TẠO TIÊU ĐỀ ////////////////////////////////////////////////////
			row.setHeightInPoints(18);
			//Mã số
			cell = row.createCell(0);
			cell.setCellValue("Mã thể loại");
			cell.setCellStyle(styleHeader);
			
			//Tài khoản
			cell = row.createCell(1);
			cell.setCellValue("Tên thể loại");
			cell.setCellStyle(styleHeader);
			
			//Họ và tên
			cell = row.createCell(2);
			cell.setCellValue("Ghi chú");
			cell.setCellStyle(styleHeader);
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			//////////////////////////////////////////////////// INSERT NỘI DUNG /////////////////////////////////////////////////////////////////////
			//[Mã thể loại, tên thể loại, ghi chú]
			for (Category c : listCategory)
			{
				rowNum++;
				row = sheet.createRow(rowNum);
				row.setHeightInPoints(20);

				//Mã thể loại
				cell = row.createCell(0);
				cell.setCellValue(c.getId());
				cell.setCellStyle(styleBody);
				
				//Tên thể loại
				cell = row.createCell(1);
				cell.setCellValue(c.getCategoryTitle());
				cell.setCellStyle(styleBody);
				
				//ghi chú
				cell = row.createCell(2);
				cell.setCellValue(c.getCategoryDescription());
				cell.setCellStyle(styleBody);
			}
			
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			
			//Tiến hành ghi file
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			workbook.close();
			return true;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		
		return false;
	}
	
	public static boolean writeLocation(File file, List<Location> listLocations)
	{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Vị trí");
		CellStyle styleHeader = workbook.createCellStyle();	
		CellStyle styleBody = workbook.createCellStyle();	
		Font fontHeader = workbook.createFont();
		Font fontBody = workbook.createFont();
		
		
		//Cấu hình font cho StyleHeader
		fontHeader.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
		fontHeader.setBold(true);
		fontHeader.setFontHeightInPoints((short) 10);
		
		//Cấu hình style cho header
		styleHeader.setFillForegroundColor(IndexedColors.BROWN.getIndex());
		styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styleHeader.setAlignment(HorizontalAlignment.CENTER);
		styleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
		styleHeader.setBorderLeft(BorderStyle.THIN);
		styleHeader.setBorderRight(BorderStyle.THIN);
		styleHeader.setFont(fontHeader);
		
		//Cấu hình font cho styleBody
		
		
		//Cấu hình style cho body
		styleBody.setBorderLeft(BorderStyle.THIN);
		styleBody.setBorderRight(BorderStyle.THIN);
		styleBody.setBorderBottom(BorderStyle.THIN);
		styleBody.setBorderTop(BorderStyle.THIN);
		styleBody.setFont(fontBody);
		styleBody.setAlignment(HorizontalAlignment.CENTER);
		styleBody.setVerticalAlignment(VerticalAlignment.CENTER);
		
		
		try 
		{
			int rowNum = 0;
			Cell cell;
			Row row;
			
			row = sheet.createRow(rowNum);
			//[Mã thể loại, tên thể loại, sức chứa, ghi chú]
			
			/////////////////////////////////////////////// TẠO TIÊU ĐỀ ////////////////////////////////////////////////////
			row.setHeightInPoints(18);
			//Mã số
			cell = row.createCell(0);
			cell.setCellValue("Mã vị trí");
			cell.setCellStyle(styleHeader);
			
			//Tài khoản
			cell = row.createCell(1);
			cell.setCellValue("Tên vị trí");
			cell.setCellStyle(styleHeader);
			
			//Sức chứa
			cell = row.createCell(2);
			cell.setCellValue("Sức chứa");
			cell.setCellStyle(styleHeader);
			
			//Họ và tên
			cell = row.createCell(3);
			cell.setCellValue("Ghi chú");
			cell.setCellStyle(styleHeader);
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			//////////////////////////////////////////////////// INSERT NỘI DUNG /////////////////////////////////////////////////////////////////////
			//[Mã thể loại, tên thể loại, sức chứa, ghi chú]
			for (Location l : listLocations)
			{
				rowNum++;
				row = sheet.createRow(rowNum);
				row.setHeightInPoints(20);

				//Mã vị trí
				cell = row.createCell(0);
				cell.setCellValue(l.getId());
				cell.setCellStyle(styleBody);
				
				//Tên vị trí
				cell = row.createCell(1);
				cell.setCellValue(l.getLocationName());
				cell.setCellStyle(styleBody);
				
				//Sức chứa
				cell = row.createCell(2);
				cell.setCellValue(l.getMaxStorage());
				cell.setCellStyle(styleBody);
				
				//ghi chú
				cell = row.createCell(3);
				cell.setCellValue(l.getDescription());
				cell.setCellStyle(styleBody);
			}
			
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			
			//Tiến hành ghi file
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			workbook.close();
			return true;
			
			} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
			
		return false;
	}
	
	public static void main(String[] args) throws SQLException {
		File file = new File("C:\\Users\\DaiHao\\Desktop\\testExcel.xls");
		ExportExcel.writeLocation(file, LocationDAO.getAll());
	}
}
