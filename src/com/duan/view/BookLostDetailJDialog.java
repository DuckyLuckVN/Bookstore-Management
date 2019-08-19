package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.SoftBevelBorder;

import com.duan.dao.AdminDAO;
import com.duan.dao.BookDAO;
import com.duan.dao.BookLostDAO;
import com.duan.dao.BookLostDetailDAO;
import com.duan.dao.CategoryDAO;
import com.duan.dao.LocationDAO;
import com.duan.dao.OrderDAO;
import com.duan.dao.OrderDetailDAO;
import com.duan.dao.RentBookDAO;
import com.duan.dao.RentBookDetailDAO;
import com.duan.dao.UserDAO;
import com.duan.helper.DataHelper;
import com.duan.helper.DateHelper;
import com.duan.helper.SettingSave;
import com.duan.helper.SwingHelper;
import com.duan.model.Admin;
import com.duan.model.Book;
import com.duan.model.BookLost;
import com.duan.model.BookProduct;
import com.duan.model.Order;
import com.duan.model.RentBook;
import com.duan.model.RentBookDetail;
import com.duan.model.User;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JScrollPane;

import com.duan.controller.ExportPDF;
import com.duan.custom.common.JTableBlue;

import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookLostDetailJDialog extends JDialog {

	private JPanel contentPane;
	private JTableBlue tblDetail;
	private JLabel lblUser;
	private JLabel lblNhnVinBn;
	private JLabel lblRentbookAdmin;
	private JLabel lblCreatedDate;
	private JLabel lblTngCng;
	private JLabel lblTotalBook;
	private JLabel lblTnhTrng;
	private JLabel lblStatus;
	private JButton btnGiMail;
	private JLabel lblNvBoMt;
	private JLabel lblLostAdmin;
	private JLabel lblNgyBoMt;
	private JLabel lblReturnedDate;
	private JLabel label;
	private JLabel lblCreatedDate_Lost;
	private JLabel lblTotalCost;
	private JLabel lblTngPhPht;

	
	private List<BookProduct> listBookProduct = new ArrayList<BookProduct>();
	private SendMailJDialog sendMailJDialog;
	private BookLost bookLost;
	private RentBook rentBook;
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					BookLostDetailJDialog frame = new BookLostDetailJDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BookLostDetailJDialog(Component comp)
	{
		this();
		setLocationRelativeTo(comp);
	}
	public BookLostDetailJDialog() 
	{
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BookLostDetailJDialog.class.getResource("/com/duan/icon/icons8_details_popup_50px_1.png")));
		setTitle("Chi tiết đơn báo mất số: 3303");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 695, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTiKhonMua = new JLabel("Tài khoản thuê:");
		lblTiKhonMua.setForeground(Color.DARK_GRAY);
		lblTiKhonMua.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTiKhonMua.setBounds(10, 11, 126, 21);
		contentPane.add(lblTiKhonMua);
		
		JLabel lblNgyMua = new JLabel("Ngày thuê:");
		lblNgyMua.setForeground(Color.DARK_GRAY);
		lblNgyMua.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgyMua.setBounds(10, 43, 126, 21);
		contentPane.add(lblNgyMua);
		
		lblCreatedDate = new JLabel("12-05-2019");
		lblCreatedDate.setForeground(Color.DARK_GRAY);
		lblCreatedDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCreatedDate.setBounds(146, 43, 278, 21);
		contentPane.add(lblCreatedDate);
		
		lblRentbookAdmin = new JLabel("Đào Quang Tiến (quantienpoly)");
		lblRentbookAdmin.setForeground(Color.DARK_GRAY);
		lblRentbookAdmin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRentbookAdmin.setBounds(146, 141, 278, 21);
		contentPane.add(lblRentbookAdmin);
		
		lblNhnVinBn = new JLabel("Nhân viên nhập:");
		lblNhnVinBn.setForeground(Color.DARK_GRAY);
		lblNhnVinBn.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNhnVinBn.setBounds(10, 141, 126, 21);
		contentPane.add(lblNhnVinBn);
		
		lblUser = new JLabel("Nguyễn Đại Hào (daihao12mc)");
		lblUser.setForeground(Color.RED);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUser.setBounds(146, 11, 278, 21);
		contentPane.add(lblUser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 237, 669, 180);
		contentPane.add(scrollPane);
		
		tblDetail = new JTableBlue();
		tblDetail.setRowHeight(30);
		tblDetail.setModel(new DefaultTableModel(null,new String[] {"MÃ SÁCH", "TÊN SÁCH", "GIÁ BÁN", "GIÁ LÚC THUÊ", "SỐ THUÊ", "SỐ MẤT", "PHÍ PHẠT"}) 
		{
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		});
		tblDetail.getColumnModel().getColumn(0).setPreferredWidth(50);;
		tblDetail.getColumnModel().getColumn(1).setPreferredWidth(180);;
		tblDetail.getColumnModel().getColumn(3).setPreferredWidth(100);;
		scrollPane.setViewportView(tblDetail);
		
		JButton btnPrint = new JButton("Xuất đơn");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(ExportPDF.writeBookLost(bookLost))
				{
					ExportPDF.showPDFBookLost();
				}
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPrint.setIcon(new ImageIcon(BookLostDetailJDialog.class.getResource("/com/duan/icon/icons8_print_32px.png")));
		btnPrint.setBounds(587, 11, 92, 87);
		SwingHelper.setTextBelowIconButton(btnPrint);
		contentPane.add(btnPrint);
		
		lblTngCng = new JLabel("Tổng mất:");
		lblTngCng.setForeground(Color.DARK_GRAY);
		lblTngCng.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTngCng.setBounds(508, 428, 69, 21);
		contentPane.add(lblTngCng);
		
		lblTotalBook = new JLabel("35/50 quyển");
		lblTotalBook.setForeground(Color.BLUE);
		lblTotalBook.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotalBook.setBounds(587, 428, 92, 21);
		contentPane.add(lblTotalBook);
		
		lblTnhTrng = new JLabel("Tình trạng:");
		lblTnhTrng.setForeground(Color.DARK_GRAY);
		lblTnhTrng.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTnhTrng.setBounds(10, 205, 105, 21);
		contentPane.add(lblTnhTrng);
		
		lblStatus = new JLabel("Đang thuê");
		lblStatus.setForeground(Color.RED);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStatus.setBounds(146, 205, 278, 21);
		contentPane.add(lblStatus);
		
		btnGiMail = new JButton("Gửi mail");
		btnGiMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					User user = UserDAO.findByID(rentBook.getUserId());
					sendMailJDialog = new SendMailJDialog(user.getEmail());
					sendMailJDialog.setLocationRelativeTo(contentPane);
					sendMailJDialog.setVisible(true);
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		});
		btnGiMail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGiMail.setBounds(587, 109, 89, 23);
		contentPane.add(btnGiMail);
		
		lblNvBoMt = new JLabel("Nhân viên báo mất:");
		lblNvBoMt.setForeground(Color.DARK_GRAY);
		lblNvBoMt.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNvBoMt.setBounds(10, 173, 126, 21);
		contentPane.add(lblNvBoMt);
		
		lblLostAdmin = new JLabel("Đào Quang Tiến (quantienpoly)");
		lblLostAdmin.setForeground(Color.DARK_GRAY);
		lblLostAdmin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLostAdmin.setBounds(146, 173, 278, 21);
		contentPane.add(lblLostAdmin);
		
		lblNgyBoMt = new JLabel("Ngày trả:");
		lblNgyBoMt.setForeground(Color.DARK_GRAY);
		lblNgyBoMt.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgyBoMt.setBounds(10, 75, 126, 21);
		contentPane.add(lblNgyBoMt);
		
		lblReturnedDate = new JLabel("12-05-2019");
		lblReturnedDate.setForeground(Color.DARK_GRAY);
		lblReturnedDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblReturnedDate.setBounds(146, 75, 278, 21);
		contentPane.add(lblReturnedDate);
		
		label = new JLabel("Ngày báo mất:");
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(10, 109, 126, 21);
		contentPane.add(label);
		
		lblCreatedDate_Lost = new JLabel("12-05-2019");
		lblCreatedDate_Lost.setForeground(Color.DARK_GRAY);
		lblCreatedDate_Lost.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCreatedDate_Lost.setBounds(146, 109, 278, 21);
		contentPane.add(lblCreatedDate_Lost);
		
		lblTotalCost = new JLabel("300.000đ");
		lblTotalCost.setForeground(Color.RED);
		lblTotalCost.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotalCost.setBounds(112, 428, 92, 21);
		contentPane.add(lblTotalCost);
		
		lblTngPhPht = new JLabel("Tổng phí phạt:");
		lblTngPhPht.setHorizontalAlignment(SwingConstants.LEFT);
		lblTngPhPht.setForeground(Color.DARK_GRAY);
		lblTngPhPht.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTngPhPht.setBounds(10, 428, 92, 21);
		contentPane.add(lblTngPhPht);
	}
	
	public void setDetailModel(int rentbook_id)
	{
		try 
		{
			this.bookLost = BookLostDAO.findByID(rentbook_id);
			rentBook = RentBookDAO.findById(rentbook_id);
			listBookProduct = BookLostDetailDAO.getListProducts(this.bookLost.getRentbookId());
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void setDetailModel(BookLost bookLost)
	{
		try 
		{
			this.bookLost = bookLost;
			rentBook = RentBookDAO.findById(bookLost.getRentbookId());
			listBookProduct = BookLostDetailDAO.getListProducts(this.bookLost.getRentbookId());
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Hiển thị thông tin chi tiết lên form
	public void showDetail()
	{
		setTitle("Chi tiết đơn báo mất số: " + rentBook.getId());
		User user;
		String createdDate;
		String returnedDate;
		String lostCreatedDate;
		String totalCost;
		String bookLostNumber;
		try 
		{
			user = UserDAO.findByID(rentBook.getUserId());
			
			Admin rentBookAdmin = AdminDAO.findByID(rentBook.getAdminId());
			Admin bookLostAdmin =  AdminDAO.findByID(bookLost.getAdminId());
			
			createdDate = DateHelper.dateToString(rentBook.getCreatedDate(), SettingSave.getSetting().getDateFormat());
			
			//Lưu giá trị số sách mất / số sách đã thuê
			bookLostNumber = BookLostDetailDAO.getTotalLost(rentBook.getId()) + "/" + RentBookDetailDAO.getTotalBookRented(rentBook.getId()) + " quyển";
			
			//Lưu tổng số tiền phạt
			totalCost = DataHelper.getFormatForMoney(BookLostDetailDAO.getTotalCost(rentBook.getId())) + SettingSave.getSetting().getMoneySymbol();
			
			//Ngày Trả
			if (rentBook.getReturnedDate() != null)
				returnedDate = DateHelper.dateToString(rentBook.getReturnedDate(), SettingSave.getSetting().getDateFormat());
			else
				returnedDate = "Chưa có";
			
			if (bookLost.getCreatedDate() != null)
				lostCreatedDate = DateHelper.dateToString(bookLost.getCreatedDate(), SettingSave.getSetting().getDateFormat());
			else
				lostCreatedDate = "Chưa có";
			
			//USER
			if (user != null)
				lblUser.setText(user.getFullname() + " (" + user.getUsername() + ")");
			else
				lblUser.setText("Không có");
			
			lblCreatedDate.setText(createdDate);
			lblRentbookAdmin.setText(rentBookAdmin.getFullname() + " (" + rentBookAdmin.getUsername() + ")");
			lblLostAdmin.setText(bookLostAdmin.getFullname() + " (" + bookLostAdmin.getUsername() + ")");
			lblTotalBook.setText(RentBookDetailDAO.getTotalBookRented(rentBook.getId()) + " quyển");
			lblTotalCost.setText(totalCost);
			lblTotalBook.setText(bookLostNumber);
			
			if(rentBook.getStatus() == 0)
			{
				lblStatus.setText("Đang thuê");
			}
			else
			{
				lblStatus.setText("Đã trả");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
	public void fillToTable()
	{
		DefaultTableModel model = (DefaultTableModel) tblDetail.getModel();
		model.setRowCount(0);
		
		try 
		{
			for (BookProduct p : listBookProduct)
			{
				Book book = p.getBook();
				RentBookDetail rentDetail = RentBookDetailDAO.findById(rentBook.getId(), book.getId());
				
				String giaHienTai = DataHelper.getFormatForMoney(book.getPrice()) + SettingSave.getSetting().getMoneySymbol();
				String giaLucThue =  DataHelper.getFormatForMoney(rentDetail.getPrice()) + SettingSave.getSetting().getMoneySymbol();
				String phiMatSach = DataHelper.getFormatForMoney(p.getPrice()) + SettingSave.getSetting().getMoneySymbol();;
				
				Object[] rowData = {book.getId(), book.getTitle(), giaHienTai, giaLucThue, rentDetail.getAmount(), p.getAmount(), phiMatSach};
				model.addRow(rowData);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Layout: BorderLayout
	}
}
