package com.duan.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import com.duan.dao.AdminDAO;
import com.duan.dao.BookLostDetailDAO;
import com.duan.dao.OrderDAO;
import com.duan.dao.OrderDetailDAO;
import com.duan.dao.RentBookDAO;
import com.duan.dao.RentBookDetailDAO;
import com.duan.dao.UserDAO;
import com.duan.helper.DataHelper;
import com.duan.helper.DateHelper;
import com.duan.helper.SettingSave;
import com.duan.model.Admin;
import com.duan.model.BookLost;
import com.duan.model.BookProduct;
import com.duan.model.Order;
import com.duan.model.RentBook;
import com.duan.model.RentBookDetail;
import com.duan.model.User;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ExportPDF 
{
	public static final String PATH_PDF_ORDER = "C:\\temp\\OrderData.pdf";
	public static final String PATH_PDF_RENTBOOK = "C:\\temp\\RentBookData.pdf";
	public static final String PATH_PDF_BOOKLOST = "C:\\temp\\BookLostData.pdf";
	
	public static boolean writeOrder(Order order)
	{
		Document document = null;
		BaseFont baseFont = null;
		Font utf8Font;
		User user;
		Admin admin;
		
		try 
		{
			document = new Document();
			baseFont = BaseFont.createFont("c:\\WINDOWS\\fonts\\tahoma.ttf", BaseFont.IDENTITY_H, true);
			utf8Font = new Font(baseFont);
			utf8Font.setSize(13);
			
			user = UserDAO.findByID(order.getUserId());
			admin = AdminDAO.findByID(order.getAdminId());
			
			Font fTitle = new Font(baseFont);
			fTitle.setSize(16);
			
			PdfWriter.getInstance(document, new FileOutputStream(PATH_PDF_ORDER));
			
			document.open();
			document.addTitle("Phiếu mua hàng số " + order.getId());
			
			Paragraph prTitle = new Paragraph("Phiếu mua hàng số " + order.getId(), fTitle);
			prTitle.setAlignment(Element.ALIGN_CENTER);
			prTitle.setSpacingAfter(20);
			prTitle.setSpacingBefore(15);
			
			document.add(prTitle);
			PdfPTable tblInfo = new PdfPTable(2);
			tblInfo.getDefaultCell().setBorder(0);
			
			//USER INFO
			tblInfo.addCell(new Paragraph("Tài khoảng mua hàng:", utf8Font));
			tblInfo.addCell(new Paragraph((user != null) ? (user.getFullname() + " (" + user.getUsername() +")") : "Không có", utf8Font));
			
			//ADMIN INFO
			tblInfo.addCell(new Paragraph("Nhân viên trực:", utf8Font));
			tblInfo.addCell(new Paragraph(admin.getFullname() + " (" + admin.getUsername() + ")", utf8Font));
			
			//ORDER INFO
			tblInfo.addCell(new Paragraph("Ngày mua hàng:", utf8Font));
			tblInfo.addCell(new Paragraph(DateHelper.dateToString(order.getDateCreated(), SettingSave.getSetting().getDateFormat()), utf8Font));
			document.add(tblInfo);
			
			Paragraph listTitle = new Paragraph("Danh sách sản phẩm: " , utf8Font);
			listTitle.setAlignment(Element.ALIGN_MIDDLE);
			listTitle.setSpacingAfter(15);
			listTitle.setSpacingBefore(15);
			document.add(listTitle);
			
			Font fontListItem = new Font(baseFont);
			fontListItem.setSize(10);
			
			Font fHeaderList = new Font(baseFont);
			fHeaderList.setSize(10);
			fHeaderList.setStyle(Font.BOLD);
			fHeaderList.setColor(BaseColor.WHITE);
			
			//Danh sách sản phẩm - table [Mã sách, tên sách, giá, số lượng]
			PdfPTable tblList = new PdfPTable(4);
			tblList.setWidthPercentage(100);
			tblList.setWidths(new int[] {2, 5, 2, 2});
			tblList.getDefaultCell().setPadding(10);
			
			
			//-Header 1 - Mã Sách
			PdfPCell header1 = new PdfPCell(new Paragraph("Mã sách", fHeaderList));
			header1.setHorizontalAlignment(Element.ALIGN_CENTER);
			header1.setBackgroundColor(BaseColor.DARK_GRAY);
			header1.setPadding(8);
			tblList.addCell(header1);
			
			//-Header 2 - Tên sách
			PdfPCell header2 = new PdfPCell(new Paragraph("Tên sách", fHeaderList));
			header2.setHorizontalAlignment(Element.ALIGN_CENTER);
			header2.setBackgroundColor(BaseColor.DARK_GRAY);
			header2.setPadding(8);
			tblList.addCell(header2);
			
			//-Header 3 - Giá
			PdfPCell header3 = new PdfPCell(new Paragraph("Giá", fHeaderList));
			header3.setHorizontalAlignment(Element.ALIGN_CENTER);
			header3.setBackgroundColor(BaseColor.DARK_GRAY);
			header3.setPadding(8);
			tblList.addCell(header3);
			
			//-Header 4 - Số lượng
			PdfPCell header4 = new PdfPCell(new Paragraph("Số lượng", fHeaderList));
			header4.setHorizontalAlignment(Element.ALIGN_CENTER);
			header4.setBackgroundColor(BaseColor.DARK_GRAY);
			header4.setPadding(8);
			tblList.addCell(header4);
			
			//Lặp order lấy ra dữ liệu và thêm vào bảng
			for (BookProduct p : OrderDetailDAO.getListProducts(order.getId()))
			{
				Paragraph prBookId = new Paragraph(p.getBook().getId(), fontListItem);
				tblList.addCell(prBookId);
				
				Paragraph prBookTitle = new Paragraph(p.getBook().getTitle(), fontListItem);
				tblList.addCell(prBookTitle);
				
				Paragraph prBookPrice = new Paragraph(DataHelper.getFormatForMoney(p.getPrice()) + SettingSave.getSetting().getMoneySymbol(), fontListItem);
				tblList.addCell(prBookPrice);
				
				Paragraph prBookAmount = new Paragraph(p.getAmount() + "", fontListItem);
				tblList.addCell(prBookAmount);
			}
			
			document.add(tblList);
			
			//Thông tin tổng kết
			int totalBook = OrderDetailDAO.getTotalAmountBook(order.getId());
			double totalPrice =OrderDetailDAO.getTotalPrice(order.getId());
			Font fontTotal = new Font(baseFont, 12, Font.BOLD, BaseColor.RED);
			
			//Tổng số lượng
			Paragraph prTotalBook = new Paragraph();
			prTotalBook.setSpacingBefore(15);
			prTotalBook.setFont(utf8Font);
			prTotalBook.add(new Chunk("Tổng sách mua: "));
			Chunk chunkTotalLost = new Chunk(totalBook + " quyển");
			chunkTotalLost.setFont(fontTotal);
			prTotalBook.add(chunkTotalLost);
			document.add(prTotalBook);

			//Tổng tiền phải trả
			Paragraph prTotalPrice = new Paragraph();
			prTotalPrice.setSpacingBefore(2);
			prTotalPrice.setFont(utf8Font);
			prTotalPrice.add(new Chunk("Tổng tiền: "));
			Chunk chunkTotalCost = new Chunk(DataHelper.getFormatForMoney(totalPrice) + SettingSave.getSetting().getMoneySymbol());
			chunkTotalCost.setFont(fontTotal);
			prTotalPrice.add(chunkTotalCost);
			document.add(prTotalPrice);
			
			
			return true;
			
		} 
		catch (DocumentException | IOException e) 
		{
			e.printStackTrace();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally {
			document.close();
		}
		return false;
	}
	
	
	public static boolean writeRentbook(RentBook rentBook)
	{
		Document document = null;
		BaseFont baseFont = null;
		Font utf8Font;
		User user;
		Admin admin;
		
		try 
		{
			document = new Document();
			baseFont = BaseFont.createFont("c:\\WINDOWS\\fonts\\tahoma.ttf", BaseFont.IDENTITY_H, true);
			utf8Font = new Font(baseFont);
			utf8Font.setSize(13);
			
			user = UserDAO.findByID(rentBook.getUserId());
			admin = AdminDAO.findByID(rentBook.getAdminId());
			
			Font fTitle = new Font(baseFont);
			fTitle.setSize(16);
			
			PdfWriter.getInstance(document, new FileOutputStream(PATH_PDF_RENTBOOK));
			
			document.open();
			document.addTitle("Phiếu thuê sách số " + rentBook.getId());
			
			Paragraph prTitle = new Paragraph("Phiếu thuê sách số " + rentBook.getId(), fTitle);
			prTitle.setAlignment(Element.ALIGN_CENTER);
			prTitle.setSpacingAfter(20);
			prTitle.setSpacingBefore(15);
			
			document.add(prTitle);
			PdfPTable tblInfo = new PdfPTable(2);
			tblInfo.getDefaultCell().setBorder(0);
			
			//USER INFO
			tblInfo.addCell(new Paragraph("Tài khoảng thuê:", utf8Font));
			tblInfo.addCell(new Paragraph((user != null) ? (user.getFullname() + " (" + user.getUsername() +")") : "Không có", utf8Font));
			
			//ADMIN INFO
			tblInfo.addCell(new Paragraph("Nhân viên trực:", utf8Font));
			tblInfo.addCell(new Paragraph(admin.getFullname() + " (" + admin.getUsername() + ")", utf8Font));
			
			//ORDER INFO
			tblInfo.addCell(new Paragraph("Ngày thuê:", utf8Font));
			tblInfo.addCell(new Paragraph(DateHelper.dateToString(rentBook.getCreatedDate(), SettingSave.getSetting().getDateFormat()), utf8Font));
			tblInfo.addCell(new Paragraph("Ngày trả dự kiến:", utf8Font));
			tblInfo.addCell(new Paragraph(DateHelper.dateToString(DateHelper.addDay(rentBook.getCreatedDate(), rentBook.getExpirationDay()), SettingSave.getSetting().getDateFormat()), utf8Font));
			tblInfo.addCell(new Paragraph("Ngày trả thực tế:", utf8Font));
			tblInfo.addCell(new Paragraph((rentBook.getReturnedDate() == null) ? "" : DateHelper.dateToString(rentBook.getReturnedDate(), SettingSave.getSetting().getDateFormat()), utf8Font));
			tblInfo.addCell(new Paragraph("Tình trạng:", utf8Font));
			tblInfo.addCell(new Paragraph(rentBook.getTitleStatus(), utf8Font));
			document.add(tblInfo);
			
			Paragraph listTitle = new Paragraph("Danh sách sách thuê: " , utf8Font);
			listTitle.setAlignment(Element.ALIGN_MIDDLE);
			listTitle.setSpacingAfter(15);
			listTitle.setSpacingBefore(15);
			document.add(listTitle);
			
			Font fontListItem = new Font(baseFont);
			fontListItem.setSize(10);
			
			Font fHeaderList = new Font(baseFont);
			fHeaderList.setSize(10);
			fHeaderList.setStyle(Font.BOLD);
			fHeaderList.setColor(BaseColor.WHITE);
			
			//Danh sách sản phẩm - table [Mã sách, tên sách, giá, số lượng]
			PdfPTable tblList = new PdfPTable(4);
			tblList.setWidthPercentage(100);
			tblList.setWidths(new int[] {2, 5, 2, 2});
			tblList.getDefaultCell().setPadding(10);
			
			
			//-Header 1 - Mã Sách
			PdfPCell header1 = new PdfPCell(new Paragraph("Mã sách", fHeaderList));
			header1.setHorizontalAlignment(Element.ALIGN_CENTER);
			header1.setBackgroundColor(BaseColor.DARK_GRAY);
			header1.setPadding(8);
			tblList.addCell(header1);
			
			//-Header 2 - Tên sách
			PdfPCell header2 = new PdfPCell(new Paragraph("Tên sách", fHeaderList));
			header2.setHorizontalAlignment(Element.ALIGN_CENTER);
			header2.setBackgroundColor(BaseColor.DARK_GRAY);
			header2.setPadding(8);
			tblList.addCell(header2);
			
			//-Header 3 - Giá
			PdfPCell header3 = new PdfPCell(new Paragraph("Giá", fHeaderList));
			header3.setHorizontalAlignment(Element.ALIGN_CENTER);
			header3.setBackgroundColor(BaseColor.DARK_GRAY);
			header3.setPadding(8);
			tblList.addCell(header3);
			
			//-Header 4 - Số lượng
			PdfPCell header4 = new PdfPCell(new Paragraph("Số lượng", fHeaderList));
			header4.setHorizontalAlignment(Element.ALIGN_CENTER);
			header4.setBackgroundColor(BaseColor.DARK_GRAY);
			header4.setPadding(8);
			tblList.addCell(header4);
			
			//Lặp order lấy ra dữ liệu và thêm vào bảng
			for (BookProduct p : RentBookDetailDAO.getListProducts(rentBook.getId()))
			{
				Paragraph prBookId = new Paragraph(p.getBook().getId(), fontListItem);
				tblList.addCell(prBookId);
				
				Paragraph prBookTitle = new Paragraph(p.getBook().getTitle(), fontListItem);
				tblList.addCell(prBookTitle);
				
				Paragraph prBookPrice = new Paragraph(DataHelper.getFormatForMoney(p.getPrice()) + SettingSave.getSetting().getMoneySymbol(), fontListItem);
				tblList.addCell(prBookPrice);
				
				Paragraph prBookAmount = new Paragraph(p.getAmount() + "", fontListItem);
				tblList.addCell(prBookAmount);
			}
			
			document.add(tblList);
			
			//Thông tin tổng kết
			int totalBook = RentBookDetailDAO.getTotalBookRented(rentBook.getId());
			double totalCostRent = rentBook.getCostRent() * totalBook;
			String totalCostRentStr = DataHelper.getFormatForMoney(totalCostRent) + SettingSave.getSetting().getMoneySymbol();
			Font fontTotal = new Font(baseFont, 12, Font.BOLD, BaseColor.RED);
			
			//Tổng số lượng
			Paragraph prTotalCostRent = new Paragraph();
			prTotalCostRent.setSpacingBefore(15);
			prTotalCostRent.setFont(utf8Font);
			
			prTotalCostRent.add(new Chunk("Tổng phí thuê: "));
			Chunk chunkTotalCost = new Chunk(totalCostRentStr + " (" + totalBook + " quyển)");
			chunkTotalCost.setFont(fontTotal);
			prTotalCostRent.add(chunkTotalCost);

			document.add(prTotalCostRent);
			
			Paragraph prTotalCostExpiration = new Paragraph();
			prTotalCostExpiration.setSpacingBefore(5);
			prTotalCostExpiration.setFont(utf8Font);
			prTotalCostExpiration.add(new Chunk("Phí phạt quá hạn: "));
			
			//Xử lý hiện thông tin phí quá hạn
			if (rentBook.getStatus() == 0 && DateHelper.getDayBetweenTwoDate(rentBook.getCreatedDate(), new Date()) > rentBook.getExpirationDay())
			{
				int totalDayExpiration = DateHelper.getDayBetweenTwoDate(rentBook.getCreatedDate(), new Date()) - rentBook.getExpirationDay();
				double totalCostExpiration = totalDayExpiration * totalBook * rentBook.getCostExpiration();
				String totalCostExpirationStr = DataHelper.getFormatForMoney(totalCostExpiration) + SettingSave.getSetting().getMoneySymbol();
				
				Chunk chunkTotalCostExpiration = new Chunk(totalCostExpirationStr + " (" + (totalDayExpiration) + " ngày)");
				chunkTotalCostExpiration.setFont(fontTotal);
				prTotalCostExpiration.add(chunkTotalCostExpiration);
				document.add(prTotalCostExpiration);
			}
			else if (rentBook.getStatus() == 1 && DateHelper.getDayBetweenTwoDate(rentBook.getCreatedDate(), rentBook.getReturnedDate()) > rentBook.getExpirationDay())
			{
				int totalDayExpiration = DateHelper.getDayBetweenTwoDate(rentBook.getCreatedDate(), rentBook.getReturnedDate()) - rentBook.getExpirationDay();
				double totalCostExpiration = totalDayExpiration * totalBook * rentBook.getCostExpiration();
				String totalCostExpirationStr = DataHelper.getFormatForMoney(totalCostExpiration) + SettingSave.getSetting().getMoneySymbol();
				
				Chunk chunkTotalCostExpiration = new Chunk(totalCostExpirationStr + " (" + totalDayExpiration + " ngày)");
				chunkTotalCostExpiration.setFont(fontTotal);
				prTotalCostExpiration.add(chunkTotalCostExpiration);
				document.add(prTotalCostExpiration);
				
			}
			
			
			return true;
			
		} 
		catch (DocumentException | IOException e) 
		{
			e.printStackTrace();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally {
			document.close();
		}
		return false;
	}
	
	
	public static boolean writeBookLost(BookLost bookLost)
	{
		Document document = null;
		BaseFont baseFont = null;
		Font utf8Font;
		User user;
		Admin adminRentbook;
		Admin adminBookLost;
		RentBook rentBook;
		
		try 
		{
			document = new Document();
			baseFont = BaseFont.createFont("c:\\WINDOWS\\fonts\\tahoma.ttf", BaseFont.IDENTITY_H, true);
			utf8Font = new Font(baseFont);
			utf8Font.setSize(13);
			
			rentBook = RentBookDAO.findById(bookLost.getRentbookId());
			user = UserDAO.findByID(rentBook.getUserId());
			adminRentbook = AdminDAO.findByID(rentBook.getAdminId());
			adminBookLost = AdminDAO.findByID(bookLost.getAdminId());
			
			Font fTitle = new Font(baseFont);
			fTitle.setSize(16);
			
			PdfWriter.getInstance(document, new FileOutputStream(PATH_PDF_BOOKLOST));
			
			document.open();
			document.addTitle("Phiếu báo mất số " + rentBook.getId());
			
			Paragraph prTitle = new Paragraph("Phiếu báo mất số " + rentBook.getId(), fTitle);
			prTitle.setAlignment(Element.ALIGN_CENTER);
			prTitle.setSpacingAfter(20);
			prTitle.setSpacingBefore(15);
			
			document.add(prTitle);
			PdfPTable tblInfo = new PdfPTable(2);
			tblInfo.getDefaultCell().setBorder(0);
			
			//USER INFO
			tblInfo.addCell(new Paragraph("Tài khoảng thuê:", utf8Font));
			tblInfo.addCell(new Paragraph((user != null) ? (user.getFullname() + " (" + user.getUsername() +")") : "Không có", utf8Font));
			
			//ADMIN INFO
			tblInfo.addCell(new Paragraph("Nhân viên cho thuê:", utf8Font));
			tblInfo.addCell(new Paragraph(adminRentbook.getFullname() + " (" + adminRentbook.getUsername() + ")", utf8Font));
			
			//RENTBOOK INFO
			tblInfo.addCell(new Paragraph("Ngày thuê:", utf8Font));
			tblInfo.addCell(new Paragraph(DateHelper.dateToString(rentBook.getCreatedDate(), SettingSave.getSetting().getDateFormat()), utf8Font));
			tblInfo.addCell(new Paragraph("Ngày trả:", utf8Font));
			tblInfo.addCell(new Paragraph(DateHelper.dateToString(rentBook.getReturnedDate(), SettingSave.getSetting().getDateFormat()), utf8Font));
			
			//BOOKLOST INFO
			tblInfo.addCell(new Paragraph("Ngày báo mất:", utf8Font));
			tblInfo.addCell(new Paragraph(DateHelper.dateToString(bookLost.getCreatedDate(), SettingSave.getSetting().getDateFormat()), utf8Font));
			tblInfo.addCell(new Paragraph("Nhân viên báo mất:", utf8Font));
			tblInfo.addCell(new Paragraph(adminBookLost.getFullname() + " (" + adminBookLost.getUsername() + ")", utf8Font));
			tblInfo.addCell(new Paragraph("Tình trạng:", utf8Font));
			tblInfo.addCell(new Paragraph((rentBook.getStatus() == 0) ? "Đang thuê" : "Đã trả", utf8Font));
			
			document.add(tblInfo);
			

			//Tiêu đề trước bảng danh sách
			Paragraph listTitle = new Paragraph("Danh sách sách mất: " , utf8Font);
			listTitle.setAlignment(Element.ALIGN_MIDDLE);
			listTitle.setSpacingAfter(15);
			listTitle.setSpacingBefore(15);
			document.add(listTitle);
			
			Font fontListItem = new Font(baseFont);
			fontListItem.setSize(10);
			
			Font fHeaderList = new Font(baseFont);
			fHeaderList.setSize(9);
			fHeaderList.setStyle(Font.BOLD);
			fHeaderList.setColor(BaseColor.WHITE);
			
			//Danh sách sản phẩm - table [Mã sách, tên sách, giá, số lượng, số mất, phí phạt]
			PdfPTable tblList = new PdfPTable(6);
			tblList.setWidthPercentage(100);
			tblList.setWidths(new int[] {2, 6, 3, 2, 2, 3});
			tblList.getDefaultCell().setPadding(10);
			
			
			//-Header 1 - Mã Sách
			PdfPCell header1 = new PdfPCell(new Paragraph("Mã sách", fHeaderList));
			header1.setHorizontalAlignment(Element.ALIGN_CENTER);
			header1.setBackgroundColor(BaseColor.DARK_GRAY);
			header1.setPadding(8);
			tblList.addCell(header1);
			
			//-Header 2 - Tên sách
			PdfPCell header2 = new PdfPCell(new Paragraph("Tên sách", fHeaderList));
			header2.setHorizontalAlignment(Element.ALIGN_CENTER);
			header2.setBackgroundColor(BaseColor.DARK_GRAY);
			header2.setPadding(8);
			tblList.addCell(header2);
			
			//-Header 3 - Giá
			PdfPCell header3 = new PdfPCell(new Paragraph("Giá bán lúc thuê", fHeaderList));
			header3.setHorizontalAlignment(Element.ALIGN_CENTER);
			header3.setBackgroundColor(BaseColor.DARK_GRAY);
			header3.setPadding(8);
			tblList.addCell(header3);
			
			//-Header 4 - Số lượng
			PdfPCell header4 = new PdfPCell(new Paragraph("Số lượng", fHeaderList));
			header4.setHorizontalAlignment(Element.ALIGN_CENTER);
			header4.setBackgroundColor(BaseColor.DARK_GRAY);
			header4.setPadding(8);
			tblList.addCell(header4);
			
			//-Header 5 - Số mất
			PdfPCell header5 = new PdfPCell(new Paragraph("Số mất", fHeaderList));
			header5.setHorizontalAlignment(Element.ALIGN_CENTER);
			header5.setBackgroundColor(BaseColor.DARK_GRAY);
			header5.setPadding(8);
			tblList.addCell(header5);
			
			//-Header 6 - Số tiền phạt
			PdfPCell header6 = new PdfPCell(new Paragraph("Phí phạt", fHeaderList));
			header6.setHorizontalAlignment(Element.ALIGN_CENTER);
			header6.setBackgroundColor(BaseColor.DARK_GRAY);
			header6.setPadding(8);
			tblList.addCell(header6);
			
			//Lặp order lấy ra dữ liệu và thêm vào bảng
			for (BookProduct p : BookLostDetailDAO.getListProducts(bookLost.getRentbookId()))
			{
				RentBookDetail rentDetail = RentBookDetailDAO.findById(rentBook.getId(), p.getBook().getId());
				
				//Mã sách
				Paragraph prBookId = new Paragraph(p.getBook().getId(), fontListItem);
				tblList.addCell(prBookId);
				
				//tên sách
				Paragraph prBookTitle = new Paragraph(p.getBook().getTitle(), fontListItem);
				tblList.addCell(prBookTitle);
				
				//Giá sách lúc thuê
				Paragraph prBookPrice = new Paragraph(DataHelper.getFormatForMoney(rentDetail.getPrice()) + SettingSave.getSetting().getMoneySymbol(), fontListItem);
				tblList.addCell(prBookPrice);
				
				//Số lượng sách lúc thuê
				Paragraph prBookRentAmount = new Paragraph(rentDetail.getAmount() + "", fontListItem);
				tblList.addCell(prBookRentAmount);
				
				//Số lượng sách mất
				Paragraph prBookLostAmount = new Paragraph(p.getAmount() + "", fontListItem);
				tblList.addCell(prBookLostAmount);
				
				//Phí phạt
				Paragraph prCostBookLost = new Paragraph(DataHelper.getFormatForMoney(p.getPrice()) + SettingSave.getSetting().getMoneySymbol(), fontListItem);
				tblList.addCell(prCostBookLost);
				
			}
			document.add(tblList);
			
			//Thông tin tổng kết
			int totalLost = BookLostDetailDAO.getTotalLost(rentBook.getId());
			int totalRenk = RentBookDetailDAO.getTotalBookRented(rentBook.getId());
			double totalCost = BookLostDetailDAO.getTotalCost(rentBook.getId());
			Font fontTotal = new Font(baseFont, 12, Font.BOLD, BaseColor.RED);
			
			//Tổng sách đã mất
			Paragraph prTotalLost = new Paragraph();
			prTotalLost.setSpacingBefore(15);
			prTotalLost.setFont(utf8Font);
			prTotalLost.add(new Chunk("Tổng sách mất: "));
			Chunk chunkTotalLost = new Chunk(totalLost + "/" + totalRenk + " quyển");
			chunkTotalLost.setFont(fontTotal);
			prTotalLost.add(chunkTotalLost);
			document.add(prTotalLost);

			//Tổng tiền phí phạt
			Paragraph prTotalCost = new Paragraph();
			prTotalCost.setSpacingBefore(2);
			prTotalCost.setFont(utf8Font);
			prTotalCost.add(new Chunk("Tổng phí phạt: "));
			Chunk chunkTotalCost = new Chunk(DataHelper.getFormatForMoney(totalCost) + SettingSave.getSetting().getMoneySymbol());
			chunkTotalCost.setFont(fontTotal);
			prTotalCost.add(chunkTotalCost);
			document.add(prTotalCost);
			
			
			return true;
			
		} 
		catch (DocumentException | IOException e) 
		{
			e.printStackTrace();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally {
			document.close();
		}
		return false;
	}
	
	
	public static void showPDFOrder()
	{
		try 
		{
			Desktop.getDesktop().open(new File(PATH_PDF_ORDER));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public static void showPDFRentbook()
	{
		try 
		{
			Desktop.getDesktop().open(new File(PATH_PDF_RENTBOOK));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public static void showPDFBookLost()
	{
		try 
		{
			Desktop.getDesktop().open(new File(PATH_PDF_BOOKLOST));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
