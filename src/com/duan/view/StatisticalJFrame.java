package com.duan.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.duan.custom.common.JTableBlue;
import com.duan.custom.common.JTableRed;
import com.duan.custom.message.MessageOptionPane;
import com.duan.dao.StatisticDAO;
import com.duan.helper.DataHelper;
import com.duan.helper.DateHelper;
import com.duan.helper.SettingSave;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.GridLayout;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;

public class StatisticalJFrame extends JFrame {
	
	private static final int TAB_BOOK_ORDER = 1;
	private static final int TAB_BOOK_RENT = 2;
	private static final int TAB_BOOK_LOST = 3;
	private static final int TAB_USER = 4;
	private static final int TAB_BOOK_STORAGE = 5;
	private static final int TAB_INCOM_TOTAL = 6;

	private JPanel contentPane;
	private JTable tblOrder;
	private JTable tblIncome;
	private JTable tblRentBook;
	private JLabel lblTotalBookOrder;
	private JLabel lblTotalBookRented;
	private JLabel lblTotalBookStorage;
	private JLabel lblTotalRegistration;
	private JLabel lblTotalIncome;
	private JLabel lblTotalBookLost;
	private JComboBox cboMonthBookLost;
	private JTable tblBookLost;
	private JTable tblUser;
	private JTable tblStorage;
	private JLabel lblStatisticOverViewTitle;
	private JComboBox cboMonthRentbook;
	private JComboBox cboMonthOrder;
	private JComboBox cboMonthUser;
	private JComboBox cboMonthStorage;
	private JComboBox cboMonthImcome;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatisticalJFrame frame = new StatisticalJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StatisticalJFrame() 
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setTitle("Thống Kê");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 814, 582);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel pnlChung = new JPanel();

		tabbedPane.addTab("Thống Kê Chung", new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_online_store_32px.png")), pnlChung, null);
		
		lblStatisticOverViewTitle = new JLabel("THỐNG KÊ TỔNG QUAN THÁNG " + DateHelper.getMonth(new Date()));
		lblStatisticOverViewTitle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblStatisticOverViewTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				showStatisticOverview();
			}
		});
		lblStatisticOverViewTitle.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblStatisticOverViewTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel pnlSachDaBan = new JPanel();
		pnlSachDaBan.setBorder(new LineBorder(Color.BLACK, 3));
		pnlSachDaBan.setBackground(new Color(255, 193, 6));
		pnlSachDaBan.setLayout(new BorderLayout(0, 5));
		
		JLabel lblXemThm = new JLabel("Xem thêm");
		lblXemThm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				tabbedPane.setSelectedIndex(TAB_BOOK_ORDER);
				cboMonthOrder.setSelectedIndex(DateHelper.getMonth(new Date()));
			}
		});
		lblXemThm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblXemThm.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblXemThm.setForeground(Color.WHITE);
		lblXemThm.setPreferredSize(new Dimension(0, 25));
		lblXemThm.setBackground(new Color(234, 171, 5));
		lblXemThm.setOpaque(true);
		lblXemThm.setHorizontalAlignment(SwingConstants.CENTER);
		pnlSachDaBan.add(lblXemThm, BorderLayout.SOUTH);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setPreferredSize(new Dimension(80, 0));
		lblIcon.setIcon(new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_buy_80px.png")));
		pnlSachDaBan.add(lblIcon, BorderLayout.EAST);
		
		JLabel lblHanXut = new JLabel("   Sách đã bán:");
		lblHanXut.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHanXut.setForeground(Color.WHITE);
		pnlSachDaBan.add(lblHanXut, BorderLayout.WEST);
		
		lblTotalBookOrder = new JLabel("10");
		lblTotalBookOrder.setForeground(Color.WHITE);
		lblTotalBookOrder.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblTotalBookOrder.setHorizontalAlignment(SwingConstants.CENTER);
		pnlSachDaBan.add(lblTotalBookOrder, BorderLayout.CENTER);
		
		JPanel pnlSachChoThue = new JPanel();
		pnlSachChoThue.setBorder(new LineBorder(Color.BLACK, 3));
		pnlSachChoThue.setBackground(new Color(128, 9, 124));
		pnlSachChoThue.setLayout(new BorderLayout(0, 5));
		
		JLabel lblHaonThu = new JLabel("   Sách cho thuê:");
		lblHaonThu.setForeground(Color.WHITE);
		lblHaonThu.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnlSachChoThue.add(lblHaonThu, BorderLayout.WEST);
		
		lblTotalBookRented = new JLabel("10");
		lblTotalBookRented.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalBookRented.setForeground(Color.WHITE);
		lblTotalBookRented.setFont(new Font("Tahoma", Font.BOLD, 40));
		pnlSachChoThue.add(lblTotalBookRented, BorderLayout.CENTER);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_bookmark_80px_1.png")));
		label_3.setPreferredSize(new Dimension(80, 0));
		pnlSachChoThue.add(label_3, BorderLayout.EAST);
		
		JLabel label_4 = new JLabel("Xem thêm");
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				tabbedPane.setSelectedIndex(TAB_BOOK_RENT);
				cboMonthRentbook.setSelectedIndex(DateHelper.getMonth(new Date()));
			}
		});
		label_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_4.setPreferredSize(new Dimension(0, 25));
		label_4.setOpaque(true);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_4.setBackground(new Color(91, 6, 89));
		pnlSachChoThue.add(label_4, BorderLayout.SOUTH);
		
		JPanel pnlSachMat = new JPanel();
		pnlSachMat.setBorder(new LineBorder(Color.BLACK, 3));
		pnlSachMat.setBackground(new Color(221, 52, 69));
		pnlSachMat.setLayout(new BorderLayout(0, 5));
		
		JLabel lblnBoMt = new JLabel("   Sách bị mất:");
		lblnBoMt.setForeground(Color.WHITE);
		lblnBoMt.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnlSachMat.add(lblnBoMt, BorderLayout.WEST);
		
		lblTotalBookLost = new JLabel("10");
		lblTotalBookLost.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalBookLost.setForeground(Color.WHITE);
		lblTotalBookLost.setFont(new Font("Tahoma", Font.BOLD, 40));
		pnlSachMat.add(lblTotalBookLost, BorderLayout.CENTER);
		
		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_books_80px.png")));
		label_6.setPreferredSize(new Dimension(80, 0));
		pnlSachMat.add(label_6, BorderLayout.EAST);
		
		JLabel label_7 = new JLabel("Xem thêm");
		label_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				tabbedPane.setSelectedIndex(TAB_BOOK_LOST);
				cboMonthBookLost.setSelectedIndex(DateHelper.getMonth(new Date()));
			}
		});
		label_7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_7.setPreferredSize(new Dimension(0, 25));
		label_7.setOpaque(true);
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_7.setBackground(new Color(197, 47, 58));
		pnlSachMat.add(label_7, BorderLayout.SOUTH);
		
		JPanel pnlLuotDK = new JPanel();
		pnlLuotDK.setBorder(new LineBorder(Color.BLACK, 3));
		pnlLuotDK.setBackground(new Color(38, 167, 69));
		pnlLuotDK.setLayout(new BorderLayout(0, 5));
		
		JLabel lblTiKhongNgi = new JLabel("   Lượt đăng ký:");
		lblTiKhongNgi.setForeground(Color.WHITE);
		lblTiKhongNgi.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnlLuotDK.add(lblTiKhongNgi, BorderLayout.WEST);
		
		lblTotalRegistration = new JLabel("10");
		lblTotalRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalRegistration.setForeground(Color.WHITE);
		lblTotalRegistration.setFont(new Font("Tahoma", Font.BOLD, 40));
		pnlLuotDK.add(lblTotalRegistration, BorderLayout.CENTER);
		
		JLabel label_10 = new JLabel("");
		label_10.setIcon(new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_user_80px.png")));
		label_10.setPreferredSize(new Dimension(80, 0));
		pnlLuotDK.add(label_10, BorderLayout.EAST);
		
		JLabel label_11 = new JLabel("Xem thêm");
		label_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				tabbedPane.setSelectedIndex(TAB_USER);
				cboMonthUser.setSelectedIndex(DateHelper.getMonth(new Date()));
			}
		});
		label_11.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_11.setPreferredSize(new Dimension(0, 25));
		label_11.setOpaque(true);
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setForeground(Color.WHITE);
		label_11.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_11.setBackground(new Color(35, 151, 59));
		pnlLuotDK.add(label_11, BorderLayout.SOUTH);
		
		JPanel pnlNhapKho = new JPanel();
		pnlNhapKho.setBorder(new LineBorder(Color.BLACK, 3));
		pnlNhapKho.setBackground(new Color(255, 107, 36));
		pnlNhapKho.setLayout(new BorderLayout(0, 5));
		
		lblTotalBookStorage = new JLabel("10");
		lblTotalBookStorage.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalBookStorage.setForeground(Color.WHITE);
		lblTotalBookStorage.setFont(new Font("Tahoma", Font.BOLD, 40));
		pnlNhapKho.add(lblTotalBookStorage, BorderLayout.CENTER);
		
		JLabel lblnNhpKho = new JLabel("   Sách nhập kho:");
		lblnNhpKho.setForeground(Color.WHITE);
		lblnNhpKho.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnlNhapKho.add(lblnNhpKho, BorderLayout.WEST);
		
		JLabel label_14 = new JLabel("Xem thêm");
		label_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				tabbedPane.setSelectedIndex(TAB_BOOK_STORAGE);
				cboMonthStorage.setSelectedIndex(DateHelper.getMonth(new Date()));
			}
		});
		label_14.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_14.setPreferredSize(new Dimension(0, 25));
		label_14.setOpaque(true);
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setForeground(Color.WHITE);
		label_14.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_14.setBackground(new Color(234, 77, 0));
		pnlNhapKho.add(label_14, BorderLayout.SOUTH);
		
		JLabel label_15 = new JLabel("");
		label_15.setIcon(new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_box_important_3_80px.png")));
		label_15.setPreferredSize(new Dimension(80, 0));
		pnlNhapKho.add(label_15, BorderLayout.EAST);
		
		JPanel pnlTongDoanhThu = new JPanel();
		pnlTongDoanhThu.setBorder(new LineBorder(Color.BLACK, 3));
		pnlTongDoanhThu.setBackground(new Color(22, 199, 164));
		pnlTongDoanhThu.setLayout(new BorderLayout(0, 5));
		
		lblTotalIncome = new JLabel("10");
		lblTotalIncome.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalIncome.setForeground(Color.WHITE);
		lblTotalIncome.setFont(new Font("Tahoma", Font.BOLD, 40));
		pnlTongDoanhThu.add(lblTotalIncome, BorderLayout.CENTER);
		
		JLabel lblTngDoanhThu = new JLabel("   Tổng doanh thu:");
		lblTngDoanhThu.setForeground(Color.WHITE);
		lblTngDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnlTongDoanhThu.add(lblTngDoanhThu, BorderLayout.WEST);
		
		JLabel label_13 = new JLabel("Xem thêm");
		label_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				tabbedPane.setSelectedIndex(TAB_INCOM_TOTAL);
				cboMonthImcome.setSelectedIndex(DateHelper.getMonth(new Date()));
			}
		});
		label_13.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_13.setPreferredSize(new Dimension(0, 25));
		label_13.setOpaque(true);
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setForeground(Color.WHITE);
		label_13.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_13.setBackground(new Color(19, 168, 138));
		pnlTongDoanhThu.add(label_13, BorderLayout.SOUTH);
		
		JLabel label_16 = new JLabel("");
		label_16.setIcon(new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_stack_of_money_80px.png")));
		label_16.setPreferredSize(new Dimension(80, 0));
		pnlTongDoanhThu.add(label_16, BorderLayout.EAST);
		GroupLayout gl_pnlChung = new GroupLayout(pnlChung);
		gl_pnlChung.setHorizontalGroup(
			gl_pnlChung.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlChung.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_pnlChung.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStatisticOverViewTitle, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
						.addGroup(gl_pnlChung.createSequentialGroup()
							.addComponent(pnlSachDaBan, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
							.addGap(28)
							.addComponent(pnlSachChoThue, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))
						.addGroup(gl_pnlChung.createSequentialGroup()
							.addComponent(pnlSachMat, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
							.addGap(28)
							.addComponent(pnlLuotDK, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))
						.addGroup(gl_pnlChung.createSequentialGroup()
							.addComponent(pnlNhapKho, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
							.addGap(28)
							.addComponent(pnlTongDoanhThu, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)))
					.addGap(19))
		);
		gl_pnlChung.setVerticalGroup(
			gl_pnlChung.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlChung.createSequentialGroup()
					.addGap(11)
					.addComponent(lblStatisticOverViewTitle, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_pnlChung.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlSachDaBan, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
						.addComponent(pnlSachChoThue, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
					.addGap(26)
					.addGroup(gl_pnlChung.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlSachMat, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
						.addComponent(pnlLuotDK, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
					.addGap(26)
					.addGroup(gl_pnlChung.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlNhapKho, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
						.addComponent(pnlTongDoanhThu, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
					.addGap(25))
		);
		pnlChung.setLayout(gl_pnlChung);
		
		JPanel pnlSachBan = new JPanel();
		tabbedPane.addTab("Sách Bán", new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_books_32px_1.png")), pnlSachBan, null);
		
		cboMonthOrder = new JComboBox();
		cboMonthOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					fillToTableOrder();
				} catch (SQLException e) 
				{
					MessageOptionPane.showMessageDialog(contentPane, "Đã có lỗi sảy ra, mã lỗi: " + e.getErrorCode(), MessageOptionPane.ICON_NAME_ERROR);
					e.printStackTrace();
				}
			}
		});
		cboMonthOrder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboMonthOrder.setModel(new DefaultComboBoxModel(new String[] {"Toàn bộ", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"}));
		
		JScrollPane scrollPane = new JScrollPane();
		
		tblOrder = new JTableBlue();
		tblOrder.setRowHeight(30);
		tblOrder.setModel(new DefaultTableModel(null,new String[] {"MÃ SÁCH", "TÊN SÁCH", "THỂ LOẠI", "TÁC GIẢ", "NGÀY NHẬP", "TỔNG ĐƠN" ,"SÁCH ĐÃ BÁN", "TIỀN THU ĐƯỢC"}) 
		{
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		});
		tblOrder.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(tblOrder);
		
		JButton btlReloadOrder = new JButton("");
		btlReloadOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					fillToTableOrder();
				} catch (SQLException e1) 
				{
					MessageOptionPane.showMessageDialog(contentPane, "Đã có lỗi sảy ra, mã lỗi: " + e1.getErrorCode(), MessageOptionPane.ICON_NAME_ERROR);
					e1.printStackTrace();
				}
			}
		});
		btlReloadOrder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btlReloadOrder.setIcon(new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_synchronize_24px.png")));
		GroupLayout gl_pnlSachBan = new GroupLayout(pnlSachBan);
		gl_pnlSachBan.setHorizontalGroup(
			gl_pnlSachBan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSachBan.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_pnlSachBan.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_pnlSachBan.createSequentialGroup()
							.addComponent(cboMonthOrder, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 552, Short.MAX_VALUE)
							.addComponent(btlReloadOrder, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE))
					.addGap(10))
		);
		gl_pnlSachBan.setVerticalGroup(
			gl_pnlSachBan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSachBan.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_pnlSachBan.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlSachBan.createSequentialGroup()
							.addGap(6)
							.addComponent(cboMonthOrder, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(btlReloadOrder, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
					.addGap(11))
		);
		pnlSachBan.setLayout(gl_pnlSachBan);
		
		JPanel pnlSachThue = new JPanel();
		tabbedPane.addTab("Sách Thuê", new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_bookmark_32px.png")), pnlSachThue, null);
		
		cboMonthRentbook = new JComboBox();
		cboMonthRentbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					fillToTableRentbook();
				} 
				catch (SQLException e) 
				{
					MessageOptionPane.showMessageDialog(contentPane, "Đã có lỗi sảy ra, mã lỗi: " + e.getErrorCode(), MessageOptionPane.ICON_NAME_ERROR);
					e.printStackTrace();
				}
			}
		});
		cboMonthRentbook.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboMonthRentbook.setModel(new DefaultComboBoxModel(new String[] {"Toàn bộ", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"}));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		tblRentBook = new JTableBlue();
		tblRentBook.setRowHeight(30);
		tblRentBook.setModel(new DefaultTableModel(null,new String[] {"MÃ SÁCH", "TÊN SÁCH", "TỔNG ĐÃ THUÊ", "ĐÃ TRẢ", "CHƯA TRẢ", "QUÁ THỜI HẠN"}) 
		{
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		});
		tblRentBook.getColumnModel().getColumn(0).setResizable(false);
		scrollPane_2.setViewportView(tblRentBook);
		
		JButton btnReloadRentbook = new JButton("");
		btnReloadRentbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					fillToTableRentbook();
				} 
				catch (SQLException e1) 
				{
					MessageOptionPane.showMessageDialog(contentPane, "Đã có lỗi sảy ra, mã lỗi: " + e1.getErrorCode(), MessageOptionPane.ICON_NAME_ERROR);
					e1.printStackTrace();
				}
			}
		});
		btnReloadRentbook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReloadRentbook.setIcon(new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_synchronize_24px.png")));
		GroupLayout gl_pnlSachThue = new GroupLayout(pnlSachThue);
		gl_pnlSachThue.setHorizontalGroup(
			gl_pnlSachThue.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSachThue.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_pnlSachThue.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlSachThue.createSequentialGroup()
							.addComponent(cboMonthRentbook, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 558, Short.MAX_VALUE)
							.addComponent(btnReloadRentbook, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE))
					.addGap(10))
		);
		gl_pnlSachThue.setVerticalGroup(
			gl_pnlSachThue.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSachThue.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_pnlSachThue.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlSachThue.createSequentialGroup()
							.addGap(5)
							.addComponent(cboMonthRentbook, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnReloadRentbook, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
					.addGap(11))
		);
		pnlSachThue.setLayout(gl_pnlSachThue);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Sách mất", new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_generic_book_file_type_32px_4.png")), panel, null);
		
		cboMonthBookLost = new JComboBox();
		cboMonthBookLost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					fillToTableBookLost();
				} 
				catch (SQLException e1) 
				{
					MessageOptionPane.showMessageDialog(contentPane, "Đã có lỗi sảy ra, mã lỗi: " + e1.getErrorCode(), MessageOptionPane.ICON_NAME_ERROR);
					e1.printStackTrace();
				}
			}
		});
		cboMonthBookLost.setModel(new DefaultComboBoxModel(new String[] {"Toàn bộ", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"}));
		cboMonthBookLost.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		
		tblBookLost = new JTableBlue();
		tblBookLost.setRowHeight(30);
		tblBookLost.setModel(new DefaultTableModel(null, new Object[] {"MÃ SÁCH", "TÊN SÁCH", "SỐ ĐƠN", "TỔNG SÁCH MẤT", "TỔNG PHẠT"}));
		scrollPane_4.setViewportView(tblBookLost);
		
		JButton btnReloadBookLost = new JButton("");
		btnReloadBookLost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					fillToTableBookLost();
				} 
				catch (SQLException e1) 
				{
					MessageOptionPane.showMessageDialog(contentPane, "Đã có lỗi sảy ra, mã lỗi: " + e1.getErrorCode(), MessageOptionPane.ICON_NAME_ERROR);
					e1.printStackTrace();
				}
			}
		});
		btnReloadBookLost.setIcon(new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_synchronize_24px.png")));
		btnReloadBookLost.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(cboMonthBookLost, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 559, Short.MAX_VALUE)
							.addComponent(btnReloadBookLost, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE))
					.addGap(10))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(5)
							.addComponent(cboMonthBookLost, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnReloadBookLost, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
					.addGap(11))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Thành viên", new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_user_32px.png")), panel_2, null);
		
		cboMonthUser = new JComboBox();
		cboMonthUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					fillToTableUser();
				} 
				catch (SQLException e1) 
				{
					MessageOptionPane.showMessageDialog(contentPane, "Đã có lỗi sảy ra, mã lỗi: " + e1.getErrorCode(), MessageOptionPane.ICON_NAME_ERROR);
					e1.printStackTrace();
				}
			}
		});
		cboMonthUser.setModel(new DefaultComboBoxModel(new String[] {"Toàn bộ", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"}));
		cboMonthUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JScrollPane scrollPane_5 = new JScrollPane();
		
		tblUser = new JTableBlue();
		tblUser.setRowHeight(30);
		tblUser.setModel(new DefaultTableModel(null, new Object[] {"USER ID", "TÀI KHOẢN", "HỌ TÊN", "ĐỊA CHỈ EMAIL", "NGÀY SINH", "NGÀY ĐĂNG KÝ"}));
		scrollPane_5.setViewportView(tblUser);
		
		JButton btnReloadUser = new JButton("");
		btnReloadUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReloadUser.setIcon(new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_synchronize_24px.png")));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(cboMonthUser, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 559, Short.MAX_VALUE)
							.addComponent(btnReloadUser, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_5, GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE))
					.addGap(10))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(5)
							.addComponent(cboMonthUser, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnReloadUser, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addComponent(scrollPane_5, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
					.addGap(11))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Sách Nhập", new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_move_by_trolley_32px.png")), panel_1, null);
		
		cboMonthStorage = new JComboBox();
		cboMonthStorage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					fillToTableStorage();
				} 
				catch (SQLException e1) 
				{
					MessageOptionPane.showMessageDialog(contentPane, "Đã có lỗi sảy ra, mã lỗi: " + e1.getErrorCode(), MessageOptionPane.ICON_NAME_ERROR);
					e1.printStackTrace();
				}
			}
		});
		cboMonthStorage.setModel(new DefaultComboBoxModel(new String[] {"Toàn bộ", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"}));
		cboMonthStorage.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		
		tblStorage = new JTableBlue();
		tblStorage.setRowHeight(30);
		tblStorage.setModel(new DefaultTableModel(null, new Object[] {"MÃ SÁCH", "TÊN SÁCH", "TỔNG SÁCH NHẬP", "CHI PHÍ"}));
		scrollPane_3.setViewportView(tblStorage);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					fillToTableStorage();
				} 
				catch (SQLException e1) 
				{
					MessageOptionPane.showMessageDialog(contentPane, "Đã có lỗi sảy ra, mã lỗi: " + e1.getErrorCode(), MessageOptionPane.ICON_NAME_ERROR);
					e1.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_synchronize_24px.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(cboMonthStorage, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 559, Short.MAX_VALUE)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE))
					.addGap(10))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(cboMonthStorage, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
					.addGap(11))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel pnlDoanhThu = new JPanel();
		tabbedPane.addTab("Doanh Thu", new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_stack_of_money_32px.png")), pnlDoanhThu, null);
		
		cboMonthImcome = new JComboBox();
		cboMonthImcome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					fillToTableIncome();
				} 
				catch (SQLException e1) 
				{
					MessageOptionPane.showMessageDialog(contentPane, "Đã có lỗi sảy ra, mã lỗi: " + e1.getErrorCode(), MessageOptionPane.ICON_NAME_ERROR);
					e1.printStackTrace();
				}
			}
		});
		cboMonthImcome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboMonthImcome.setModel(new DefaultComboBoxModel(new String[] {"Toàn bộ", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"}));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		tblIncome = new JTableBlue();
		tblIncome.setRowHeight(30);
		tblIncome.setModel(new DefaultTableModel(null, new String[] {"MÃ SÁCH", "TÊN SÁCH", "TIỀN NHẬP", "TIỀN BÁN", "TIỀN THUÊ", "TIỀN PHẠT", "TỔNG TIỀN"}) 
		{
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tblIncome.getColumnModel().getColumn(0).setResizable(false);
		scrollPane_1.setViewportView(tblIncome);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					fillToTableIncome();
				} 
				catch (SQLException e1) 
				{
					MessageOptionPane.showMessageDialog(contentPane, "Đã có lỗi sảy ra, mã lỗi: " + e1.getErrorCode(), MessageOptionPane.ICON_NAME_ERROR);
					e1.printStackTrace();
				}
			}
		});
		button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_1.setIcon(new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_synchronize_24px.png")));
		GroupLayout gl_pnlDoanhThu = new GroupLayout(pnlDoanhThu);
		gl_pnlDoanhThu.setHorizontalGroup(
			gl_pnlDoanhThu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlDoanhThu.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_pnlDoanhThu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlDoanhThu.createSequentialGroup()
							.addComponent(cboMonthImcome, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 559, Short.MAX_VALUE)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE))
					.addGap(10))
		);
		gl_pnlDoanhThu.setVerticalGroup(
			gl_pnlDoanhThu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlDoanhThu.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_pnlDoanhThu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlDoanhThu.createSequentialGroup()
							.addGap(5)
							.addComponent(cboMonthImcome, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
					.addGap(11))
		);
		pnlDoanhThu.setLayout(gl_pnlDoanhThu);
		showStatisticOverview();
		try 
		{
			fillToTableBookLost();
			fillToTableIncome();
			fillToTableOrder();
			fillToTableRentbook();
			fillToTableStorage();
			fillToTableUser();
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
	}
	
	public void showStatisticOverview()
	{
		try 
		{
			lblStatisticOverViewTitle.setText("THỐNG KÊ TỔNG QUAN THÁNG " + DateHelper.getMonth(new Date()));
			Object[] data = StatisticDAO.getOverviewInMonth();
			lblTotalBookOrder.setText(data[0] + "");
			lblTotalBookRented.setText(data[1] + "");
			lblTotalBookLost.setText(data[2] + "");
			lblTotalRegistration.setText(data[3] + "");
			lblTotalBookStorage.setText(data[4] + "");
			lblTotalIncome.setText(DataHelper.getFormatForMoney(Double.parseDouble(data[5].toString())) + SettingSave.getSetting().getMoneySymbol());
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void fillToTableOrder() throws SQLException
	{
		DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
		int month = cboMonthOrder.getSelectedIndex();

		List<Object[]> list = StatisticDAO.getOrderInMonth(month);
		
		model.setRowCount(0);
		for (Object[] data : list)
		{
			model.addRow(data);
		}
	}
	
	public void fillToTableRentbook() throws SQLException
	{
		DefaultTableModel model = (DefaultTableModel) tblRentBook.getModel();
		int month = cboMonthRentbook.getSelectedIndex();

		List<Object[]> list = StatisticDAO.getRentBookInMonth(month);
		
		model.setRowCount(0);
		for (Object[] data : list)
		{
			model.addRow(data);
		}
	}
	
	public void fillToTableBookLost() throws SQLException
	{
		DefaultTableModel model = (DefaultTableModel) tblBookLost.getModel();
		int month = cboMonthBookLost.getSelectedIndex();

		List<Object[]> list = StatisticDAO.getBookLostInMonth(month);
		
		model.setRowCount(0);
		for (Object[] data : list)
		{
			model.addRow(data);
		}
	}
	
	public void fillToTableUser() throws SQLException
	{
		DefaultTableModel model = (DefaultTableModel) tblUser.getModel();
		int month = cboMonthUser.getSelectedIndex();

		List<Object[]> list = StatisticDAO.getUserInMonth(month);
		
		model.setRowCount(0);
		for (Object[] data : list)
		{
			model.addRow(data);
		}
	}
	
	public void fillToTableStorage() throws SQLException
	{
		DefaultTableModel model = (DefaultTableModel) tblStorage.getModel();
		int month = cboMonthStorage.getSelectedIndex();

		List<Object[]> list = StatisticDAO.getStorageInMonth(month);
		
		model.setRowCount(0);
		for (Object[] data : list)
		{
			model.addRow(data);
		}
	}
	
	public void fillToTableIncome() throws SQLException
	{
		DefaultTableModel model = (DefaultTableModel) tblIncome.getModel();
		int month = cboMonthImcome.getSelectedIndex();

		List<Object[]> list = StatisticDAO.getIncomeInMonth(month);
		
		model.setRowCount(0);
		for (Object[] data : list)
		{
			model.addRow(data);
		}
	}
	
	
	public String getRoleTitle(int role) 
	{
		switch (role) 
		{
		case 0: return "Toàn bộ";
		case 1: return "Tháng 1";
		case 2: return "Tháng 2";
		case 3: return "Tháng 3";
		case 4: return "Tháng 4";
		case 5: return "Tháng 5";
		case 6: return "Tháng 6";
		case 7: return "Tháng 7";
		case 8: return "Tháng 8";
		case 9: return "Tháng 9";
		case 10: return "Tháng 10";
		case 11: return "Tháng 11";
		case 12: return "Tháng 12";
		}
		return "Không xác định";
	}
}
