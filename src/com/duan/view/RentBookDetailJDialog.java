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
import com.duan.model.BookProduct;
import com.duan.model.Order;
import com.duan.model.RentBook;
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
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JScrollPane;

import com.duan.controller.ExportPDF;
import com.duan.custom.common.JTableBlue;

import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RentBookDetailJDialog extends JDialog {

	private JPanel contentPane;
	private JTableBlue tblOrderDetail;
	private JLabel lblUser;
	private JLabel lblNhnVinBn;
	private JLabel lblAdmin;
	private JLabel lblCreatedDate;
	private List<BookProduct> listBookProduct = new ArrayList<BookProduct>();
	private RentBook rentBook;
	private JLabel lblTngCng;
	private JLabel lblTotalCostRent;
	private JLabel lblTnhTrng;
	private JLabel lblStatus;
	private JButton btnGiMail;
	private JLabel lblReturnedDate;
	
	private SendMailJDialog sendMailJDialog;
	private JLabel lblTotalCostExpiration;
	private JLabel lblPhiPhatQuaHan;
	private JPanel pnlTotalCostExpiration;
	private JPanel pnlTotalCost;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					RentBookDetailJDialog frame = new RentBookDetailJDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RentBookDetailJDialog(Component comp)
	{
		this();
		setLocationRelativeTo(comp);
	}
	public RentBookDetailJDialog() 
	{
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RentBookDetailJDialog.class.getResource("/com/duan/icon/icons8_details_popup_50px_1.png")));
		setTitle("Chi tiết đơn thuê số: 3303");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 542, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTiKhonMua = new JLabel("Tài khoản thuê:");
		lblTiKhonMua.setForeground(Color.DARK_GRAY);
		lblTiKhonMua.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTiKhonMua.setBounds(10, 11, 105, 21);
		contentPane.add(lblTiKhonMua);
		
		JLabel lblNgyMua = new JLabel("Ngày thuê:");
		lblNgyMua.setForeground(Color.DARK_GRAY);
		lblNgyMua.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgyMua.setBounds(10, 43, 105, 21);
		contentPane.add(lblNgyMua);
		
		lblCreatedDate = new JLabel("12-05-2019");
		lblCreatedDate.setForeground(Color.DARK_GRAY);
		lblCreatedDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCreatedDate.setBounds(125, 43, 299, 21);
		contentPane.add(lblCreatedDate);
		
		lblAdmin = new JLabel("Đào Quang Tiến (quantienpoly)");
		lblAdmin.setForeground(Color.DARK_GRAY);
		lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAdmin.setBounds(125, 109, 299, 21);
		contentPane.add(lblAdmin);
		
		lblNhnVinBn = new JLabel("Nhân viên nhập:");
		lblNhnVinBn.setForeground(Color.DARK_GRAY);
		lblNhnVinBn.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNhnVinBn.setBounds(10, 109, 105, 21);
		contentPane.add(lblNhnVinBn);
		
		lblUser = new JLabel("Nguyễn Đại Hào (daihao12mc)");
		lblUser.setForeground(Color.RED);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUser.setBounds(125, 11, 299, 21);
		contentPane.add(lblUser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 173, 516, 156);
		contentPane.add(scrollPane);
		
		tblOrderDetail = new JTableBlue();
		tblOrderDetail.setRowHeight(30);
		tblOrderDetail.setModel(new DefaultTableModel(null,new String[] {"MÃ SÁCH", "TÊN SÁCH", "GIÁ BÁN", "GIÁ LÚC THUÊ", "SỐ LƯỢNG"}) 
		{
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		});
		tblOrderDetail.getColumnModel().getColumn(0).setPreferredWidth(50);;
		tblOrderDetail.getColumnModel().getColumn(1).setPreferredWidth(180);;
		scrollPane.setViewportView(tblOrderDetail);
		
		JButton btnPrint = new JButton("Xuất đơn");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if (ExportPDF.writeRentbook(rentBook))
				{
					ExportPDF.showPDFRentbook();
				}
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPrint.setIcon(new ImageIcon(RentBookDetailJDialog.class.getResource("/com/duan/icon/icons8_print_32px.png")));
		btnPrint.setBounds(434, 11, 92, 87);
		SwingHelper.setTextBelowIconButton(btnPrint);
		contentPane.add(btnPrint);
		
		lblTnhTrng = new JLabel("Tình trạng:");
		lblTnhTrng.setForeground(Color.DARK_GRAY);
		lblTnhTrng.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTnhTrng.setBounds(10, 141, 105, 21);
		contentPane.add(lblTnhTrng);
		
		lblStatus = new JLabel("Đang thuê");
		lblStatus.setForeground(Color.RED);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStatus.setBounds(125, 141, 299, 21);
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
		btnGiMail.setBounds(434, 109, 89, 23);
		contentPane.add(btnGiMail);
		
		JLabel lblNgyTr = new JLabel("Ngày trả:");
		lblNgyTr.setForeground(Color.DARK_GRAY);
		lblNgyTr.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgyTr.setBounds(10, 75, 105, 21);
		contentPane.add(lblNgyTr);
		
		lblReturnedDate = new JLabel("12-05-2019");
		lblReturnedDate.setForeground(Color.DARK_GRAY);
		lblReturnedDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblReturnedDate.setBounds(125, 75, 299, 21);
		contentPane.add(lblReturnedDate);
		
		pnlTotalCost = new JPanel();
		pnlTotalCost.setBounds(10, 340, 516, 23);
		contentPane.add(pnlTotalCost);
		pnlTotalCost.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTotalCostRent = new JPanel();
		pnlTotalCost.add(pnlTotalCostRent, BorderLayout.WEST);
		pnlTotalCostRent.setLayout(new BorderLayout(3, 0));
		
		lblTngCng = new JLabel("Tổng phí thuê:");
		pnlTotalCostRent.add(lblTngCng, BorderLayout.WEST);
		lblTngCng.setForeground(Color.DARK_GRAY);
		lblTngCng.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		lblTotalCostRent = new JLabel("50,000đ (10 quyển)");
		pnlTotalCostRent.add(lblTotalCostRent, BorderLayout.EAST);
		lblTotalCostRent.setForeground(Color.BLUE);
		lblTotalCostRent.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		pnlTotalCostExpiration = new JPanel();
//		pnlTotalCost.add(pnlTotalCostExpiration, BorderLayout.EAST);
		pnlTotalCostExpiration.setLayout(new BorderLayout(4, 0));
		
		lblPhiPhatQuaHan = new JLabel("Phí phạt quá hạn:");
		pnlTotalCostExpiration.add(lblPhiPhatQuaHan, BorderLayout.WEST);
		lblPhiPhatQuaHan.setForeground(Color.DARK_GRAY);
		lblPhiPhatQuaHan.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		lblTotalCostExpiration = new JLabel("35,000đ (3 ngày)");
		pnlTotalCostExpiration.add(lblTotalCostExpiration, BorderLayout.EAST);
		lblTotalCostExpiration.setForeground(Color.RED);
		lblTotalCostExpiration.setFont(new Font("Tahoma", Font.BOLD, 12));
	}
	
	public void setDetailModel(int rentbook_id)
	{
		try 
		{
			this.rentBook = RentBookDAO.findById(rentbook_id);
			listBookProduct = RentBookDetailDAO.getListProducts(this.rentBook.getId());
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void setDetailModel(RentBook rentBook)
	{
		try 
		{
			this.rentBook = rentBook;
			listBookProduct = RentBookDetailDAO.getListProducts(this.rentBook.getId());
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Hiển thị thông tin chi tiết lên form
	public void showDetail()
	{
		User user;
		String createdDate;
		String returnedDate;
		try 
		{
			user = UserDAO.findByID(rentBook.getUserId());
			Admin admin = AdminDAO.findByID(rentBook.getAdminId());
			createdDate = DateHelper.dateToString(rentBook.getCreatedDate(), SettingSave.getSetting().getDateFormat());
			int totalBook = RentBookDetailDAO.getTotalBookRented(rentBook.getId());
			double totalCostRent = rentBook.getCostRent() * totalBook;
			String totalCostRentStr = DataHelper.getFormatForMoney(totalCostRent) + SettingSave.getSetting().getMoneySymbol();
			
			setTitle("Chi tiết đơn thuê số: " + rentBook.getId());
			
			if (user != null)
				lblUser.setText(user.getFullname() + " (" + user.getUsername() + ")");
			else
				lblUser.setText("Không có");
			
			if (rentBook.getReturnedDate() != null)
				returnedDate = DateHelper.dateToString(rentBook.getReturnedDate(), SettingSave.getSetting().getDateFormat());
			else
				returnedDate = "Chưa có";
			
			lblCreatedDate.setText(createdDate);
			lblAdmin.setText(admin.getFullname() + " (" + admin.getUsername() + ")");
			lblTotalCostRent.setText(totalCostRentStr + " (" + totalBook + " quyển)");
			lblReturnedDate.setText(returnedDate);
			lblStatus.setText(rentBook.getTitleStatus());
			
			if(rentBook.getStatus() == 0 && DateHelper.getDayBetweenTwoDate(rentBook.getCreatedDate(), new Date()) > rentBook.getExpirationDay())
			{
				pnlTotalCost.add(pnlTotalCostExpiration, BorderLayout.EAST);
				
				//Lấy ra tổng số ngày quá hạn
				int totalDayExpiration = DateHelper.getDayBetweenTwoDate(rentBook.getCreatedDate(), new Date()) - rentBook.getExpirationDay();
				
				double totalCostExpiration = totalDayExpiration * rentBook.getCostExpiration() * RentBookDetailDAO.getTotalBookRented(rentBook.getId());
				lblTotalCostExpiration.setText(DataHelper.getFormatForMoney(totalCostExpiration) + SettingSave.getSetting().getMoneySymbol() + " (" + totalDayExpiration + " ngày)");
			}
			else if (rentBook.getStatus() == 1 && DateHelper.getDayBetweenTwoDate(rentBook.getCreatedDate(), rentBook.getReturnedDate()) > rentBook.getExpirationDay())
			{
				pnlTotalCost.add(pnlTotalCostExpiration, BorderLayout.EAST);
				
				//Lấy ra tổng số ngày quá hạn
				int totalDayExpiration = DateHelper.getDayBetweenTwoDate(rentBook.getCreatedDate(), rentBook.getReturnedDate()) - rentBook.getExpirationDay();
				
				double totalCostExpiration = totalDayExpiration * rentBook.getCostExpiration() * RentBookDetailDAO.getTotalBookRented(rentBook.getId());
				lblTotalCostExpiration.setText(DataHelper.getFormatForMoney(totalCostExpiration) + SettingSave.getSetting().getMoneySymbol() + " (" + totalDayExpiration + " ngày)");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
	public void fillToTable()
	{
		DefaultTableModel model = (DefaultTableModel) tblOrderDetail.getModel();
		model.setRowCount(0);
		
		for (BookProduct p : listBookProduct)
		{
			Book book = p.getBook();
			String giaHienTai = DataHelper.getFormatForMoney(book.getPrice()) + SettingSave.getSetting().getMoneySymbol();
			String giaLucMua =  DataHelper.getFormatForMoney(p.getPrice()) + SettingSave.getSetting().getMoneySymbol();
			
			Object[] rowData = {book.getId(), book.getTitle(), giaHienTai, giaLucMua, p.getAmount()};
			model.addRow(rowData);
		}
	}
}
