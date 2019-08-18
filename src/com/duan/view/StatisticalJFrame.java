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

import com.duan.dao.StaticticsDAO;
import com.duan.helper.DataHelper;
import com.duan.helper.SettingSave;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.GridLayout;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class StatisticalJFrame extends JFrame {
	
	private static final int TAB_BOOK_ORDER = 1;
	private static final int TAB_BOOK_RENT = 2;
	private static final int TAB_INCOM_TOTAL = 3;

	private JPanel contentPane;
	private JTable tblSachBan;
	private JTable tblDoanhThu;
	private JTable tblSachThue;
	private JLabel lblTotalBookOrder;
	private JLabel lblTotalBookRented;
	private JLabel lblTotalBookStorage;
	private JLabel lblTotalRegistration;
	private JLabel lblTotalIncome;
	private JLabel lblTotalBookLost;

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
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Thống Kê Chung", null, panel, null);
		
		JLabel lblThngKThng = new JLabel("THỐNG KÊ TRONG THÁNG");
		lblThngKThng.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblThngKThng.setHorizontalAlignment(SwingConstants.CENTER);
		
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
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblThngKThng, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(pnlSachDaBan, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
							.addGap(28)
							.addComponent(pnlSachChoThue, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(pnlSachMat, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
							.addGap(28)
							.addComponent(pnlLuotDK, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(pnlNhapKho, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
							.addGap(28)
							.addComponent(pnlTongDoanhThu, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)))
					.addGap(19))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addComponent(lblThngKThng, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlSachDaBan, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
						.addComponent(pnlSachChoThue, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlSachMat, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
						.addComponent(pnlLuotDK, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlNhapKho, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
						.addComponent(pnlTongDoanhThu, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
					.addGap(25))
		);
		panel.setLayout(gl_panel);
		
		JPanel pnlSachBan = new JPanel();
		tabbedPane.addTab("Sách Bán", new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_books_32px_1.png")), pnlSachBan, null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Toàn bộ", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"}));
		
		JScrollPane scrollPane = new JScrollPane();
		
		tblSachBan = new JTable();
		tblSachBan.setModel(new DefaultTableModel(null,new String[] {"MÃ SÁCH", "TÊN SÁCH", "THỂ LOẠI", "TÁC GIẢ", "NGÀY NHẬP", "TỔNG ĐÃ BÁN"}) 
		{
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblSachBan.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(tblSachBan);
		GroupLayout gl_pnlSachBan = new GroupLayout(pnlSachBan);
		gl_pnlSachBan.setHorizontalGroup(
			gl_pnlSachBan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSachBan.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(gl_pnlSachBan.createSequentialGroup()
					.addContainerGap()
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(555, Short.MAX_VALUE))
		);
		gl_pnlSachBan.setVerticalGroup(
			gl_pnlSachBan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSachBan.createSequentialGroup()
					.addContainerGap()
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
					.addGap(11))
		);
		pnlSachBan.setLayout(gl_pnlSachBan);
		
		JPanel pnlSachThue = new JPanel();
		tabbedPane.addTab("Sách Thuê", new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_bookmark_32px.png")), pnlSachThue, null);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Toàn bộ", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"}));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		tblSachThue = new JTable();
		tblSachThue.setModel(new DefaultTableModel(null,new String[] {"MÃ SÁCH", "TÊN SÁCH", "TỔNG ĐÃ THUÊ", "ĐÃ TRẢ", "CHƯA TRẢ", "QUÁ THỜI HẠN"}) 
		{
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblSachThue.getColumnModel().getColumn(0).setResizable(false);
		scrollPane_2.setViewportView(tblSachThue);
		GroupLayout gl_pnlSachThue = new GroupLayout(pnlSachThue);
		gl_pnlSachThue.setHorizontalGroup(
			gl_pnlSachThue.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSachThue.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_pnlSachThue.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE))
					.addGap(10))
		);
		gl_pnlSachThue.setVerticalGroup(
			gl_pnlSachThue.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSachThue.createSequentialGroup()
					.addGap(11)
					.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
					.addGap(11))
		);
		pnlSachThue.setLayout(gl_pnlSachThue);
		
		JPanel pnlDoanhThu = new JPanel();
		tabbedPane.addTab("Doanh Thu", new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_stack_of_money_32px.png")), pnlDoanhThu, null);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Toàn bộ", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"}));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		tblDoanhThu = new JTable();
		tblDoanhThu.setModel(new DefaultTableModel(null, new String[] {"MÃ SÁCH", "TÊN SÁCH", "TIỀN NHẬP", "TIỀN BÁN", "TIỀN THUÊ", "TIỀN PHẠT", "TỔNG TIỀN"}) 
		{
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblDoanhThu.getColumnModel().getColumn(0).setResizable(false);
		scrollPane_1.setViewportView(tblDoanhThu);
		GroupLayout gl_pnlDoanhThu = new GroupLayout(pnlDoanhThu);
		gl_pnlDoanhThu.setHorizontalGroup(
			gl_pnlDoanhThu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlDoanhThu.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_pnlDoanhThu.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE))
					.addGap(10))
		);
		gl_pnlDoanhThu.setVerticalGroup(
			gl_pnlDoanhThu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlDoanhThu.createSequentialGroup()
					.addGap(11)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
					.addGap(11))
		);
		pnlDoanhThu.setLayout(gl_pnlDoanhThu);
		showStatisticOverview();
	}
	
	public void showStatisticOverview()
	{
		try 
		{
			Object[] data = StaticticsDAO.getStatisticOverviewInMonth();
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
