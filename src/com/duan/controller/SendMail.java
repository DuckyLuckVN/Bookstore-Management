package com.duan.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.duan.custom.message.MessageOptionPane;
import com.duan.dao.AdminDAO;
import com.duan.dao.RentBookDAO;
import com.duan.dao.RentBookDetailDAO;
import com.duan.dao.UserDAO;
import com.duan.helper.DataHelper;
import com.duan.helper.DateHelper;
import com.duan.helper.SettingSave;
import com.duan.model.Admin;
import com.duan.model.RentBook;
import com.duan.model.Setting;
import com.duan.model.User;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class SendMail 
{
	private static final String USERNAME = SettingSave.getSetting().getUsernameEmail();
	private static final String PASSWORD = SettingSave.getSetting().getPasswordEmail();
	
	public static boolean sendMail(String to, String title, String messageSend) throws AddressException, MessagingException
	{
		Properties p = new Properties();
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.host", "smtp.gmail.com");
//		p.put("mail.smtp.port", 587);
		p.put("mail.smtp.port", 587);

		Session s = Session.getInstance(p, new javax.mail.Authenticator() 
		{
			protected PasswordAuthentication getPasswordAuthentication() 
			{
				return new PasswordAuthentication(USERNAME, PASSWORD);
//				return new PasswordAuthentication("tiendqps08547@fpt.edu.vn", "quangtien");
			}
		});
		
		Message msg = new MimeMessage(s);
	
		msg.setFrom(new InternetAddress(to));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		msg.setSubject(title);
		msg.setText(messageSend);
		Transport.send(msg);
		return true;
	}
	
	//Trả về số mail đã được gử!
	public static int sendReportExpirationMail() throws SQLException, AddressException, MessagingException
	{
		int total = 0;
		for (RentBook rb : RentBookDAO.getAllExpiration())
		{
			User user = UserDAO.findByID(rb.getUserId());
			String title = "Phiếu mượn số '" + rb.getId() + "' của bạn đang quá hạn trả";
			
			//Lấy về chuỗi đã được set các thông tin của rb truyền vào
			String msg = getReportExpirationString(rb);
			
			//Tiến hành gửi mail
			sendMail(user.getEmail(), title, msg);
			total++;
		}
		return total;
	}
	
	//Trả về chuỗi với nội dung hoàn chỉnh sau khi đã thay thế các ký tự
	private static String getReportExpirationString(RentBook rentBook)
	{
		try 
		{
			String msg = SettingSave.getSetting().getMessageReportExpiration();
			User user = UserDAO.findByID(rentBook.getUserId());
			Admin admin = AdminDAO.findByID(rentBook.getAdminId());
			int ExpirationMilisec = (1000*60*60*24* SettingSave.getSetting().getDayExpiration());
			int dayExpiration = DateHelper.getDayBetweenTwoDate(new Date(rentBook.getCreatedDate().getTime() + ExpirationMilisec), new Date());
			int totalBookRented = RentBookDetailDAO.getTotalBookRented(rentBook.getId());
			
			//Tiến hành thay thế các ký tự đặc biệt thành dữ liệu của rentbook truyền vào
			
			// - Thông tin admin
			msg = msg.replaceAll(Setting.SYMBOL_ADMIN_FULLNAME, admin.getFullname());
			msg = msg.replaceAll(Setting.SYMBOL_ADMIN_USERNAME, admin.getUsername());

			// - Thông tin user
			msg = msg.replaceAll(Setting.SYMBOL_USER_FULLNAME, user.getFullname());
			msg = msg.replaceAll(Setting.SYMBOL_USER_USERNAME, user.getUsername());
			
			// - Thông tin đơn thuê
			msg = msg.replaceAll(Setting.SYMBOL_RENT_CREATED_DATE, DateHelper.dateToString(rentBook.getCreatedDate(), SettingSave.getSetting().getDateFormat()));
			msg = msg.replaceAll(Setting.SYMBOL_RENT_EXPIRATION_DAY, dayExpiration + "");
			msg = msg.replaceAll(Setting.SYMBOL_RENT_ID, rentBook.getId() + "");
			msg = msg.replaceAll(Setting.SYMBOL_RENT_TOTALBOOK, totalBookRented + "");
			
			return msg;
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return "";
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getReportExpirationString(RentBookDAO.findById(100)));
	}
}
