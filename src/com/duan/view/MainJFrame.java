package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.duan.helper.SwingHelper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.Customizer;
import java.beans.PropertyChangeListener;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Toolkit;

public class MainJFrame extends JFrame {

	private JPanel contentPane;
	private int posX = 0;
	private int posY = 0;
	private SwingHelper sHelper = new SwingHelper();
	
	BookJFrame bookJFrame = new BookJFrame();
	LoginJFrame loginJFrame = new LoginJFrame();
	RentBookJFrame rentBookJFrame = new RentBookJFrame();
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					MainJFrame frame = new MainJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public MainJFrame() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainJFrame.class.getResource("/com/duan/icon/icons8_book_64px_3.png")));
		setTitle("Bookstore Managerment");
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 561);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentPane.addMouseMotionListener(new MouseMotionAdapter() 
		{
			@Override
			public void mouseDragged(MouseEvent e) 
			{
				setLocation(e.getXOnScreen() - posX, e.getYOnScreen() - posY);
			}
		});
		contentPane.addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e) 
			{
				posX = e.getX();
				posY = e.getY();
			}
		});
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(new Color(30, 144, 255));
		pnlHeader.setBounds(0, 0, 744, 178);
		contentPane.add(pnlHeader);
		pnlHeader.setLayout(null);
		
		JLabel lblTitle = new JLabel("BOOKSTORE MANAGERMENT");
		lblTitle.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblTitle.setBounds(98, 26, 482, 76);
		pnlHeader.add(lblTitle);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/icons8_book_64px_3.png")));
		lblNewLabel.setBounds(10, 37, 81, 78);
		pnlHeader.add(lblNewLabel);
		
		JLabel lblVersion = new JLabel("version: 1.0.1");
		lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersion.setForeground(new Color(255, 255, 255));
		lblVersion.setBounds(656, 156, 88, 22);
		pnlHeader.add(lblVersion);
		
		JLabel lblX = new JLabel("X");
		lblX.setOpaque(true);
		lblX.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblX.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblX.setBackground(new Color(30, 144, 255));
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (sHelper.showConfirm(getContentPane(), "Mọi cửa sổ bạn đang thao tác đều sẽ bị xóa."
														+ "\nBạn có chắc chắc muốn đóng ứng dụng này lại?"))
					System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				lblX.setBackground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				lblX.setBackground(new Color(30, 144, 255));
			}
		});
		lblX.setForeground(new Color(255, 255, 255));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(707, 0, 37, 36);
		pnlHeader.add(lblX);
		
		JPanel pnl1 = new JPanel();
		pnl1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sHelper.changeBackground(pnl1, SystemColor.controlHighlight);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sHelper.changeBackground(pnl1, SystemColor.menu);
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				openBookJFrame();
			}
		});
		pnl1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl1.setBackground(SystemColor.menu);
		pnl1.setBounds(55, 215, 130, 130);
		contentPane.add(pnl1);
		pnl1.setLayout(null);
		
		JLabel lblIcon1 = new JLabel("");
		lblIcon1.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon1.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/icons8_books_64px_4.png")));
		lblIcon1.setBounds(10, 11, 110, 81);
		pnl1.add(lblIcon1);
		
		JLabel lblTItle1 = new JLabel("Kho sách");
		lblTItle1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTItle1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTItle1.setBounds(10, 92, 110, 27);
		pnl1.add(lblTItle1);
		
		JPanel pnl2 = new JPanel();
		pnl2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sHelper.changeBackground(pnl2, SystemColor.controlHighlight);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sHelper.changeBackground(pnl2, SystemColor.menu);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				showRentBook();
			}
		});
		pnl2.setBackground(SystemColor.menu);
		pnl2.setBounds(230, 215, 130, 130);
		contentPane.add(pnl2);
		pnl2.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/icons8_bookmark_64px_2.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 11, 110, 81);
		pnl2.add(label);
		
		JLabel lblKhchHng = new JLabel("Thuê sách");
		lblKhchHng.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhchHng.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKhchHng.setBounds(10, 92, 110, 27);
		pnl2.add(lblKhchHng);
		
		JPanel pnl3 = new JPanel();
		pnl3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sHelper.changeBackground(pnl3, SystemColor.controlHighlight);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sHelper.changeBackground(pnl3, SystemColor.menu);
			}
		});
		pnl3.setBackground(SystemColor.menu);
		pnl3.setBounds(401, 215, 130, 130);
		contentPane.add(pnl3);
		pnl3.setLayout(null);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/icons8_buy_for_change_64px_1.png")));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(10, 11, 110, 81);
		pnl3.add(label_2);
		
		JLabel lblNhnVin = new JLabel("Bán sách");
		lblNhnVin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhnVin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNhnVin.setBounds(10, 92, 110, 27);
		pnl3.add(lblNhnVin);
		
		JPanel pnl4 = new JPanel();
		pnl4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sHelper.changeBackground(pnl4, SystemColor.controlHighlight);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sHelper.changeBackground(pnl4, SystemColor.menu);
			}
		});
		pnl4.setBackground(SystemColor.menu);
		pnl4.setBounds(55, 383, 130, 130);
		contentPane.add(pnl4);
		pnl4.setLayout(null);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/icons8_list_64px.png")));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(10, 11, 110, 81);
		pnl4.add(label_4);
		
		JLabel lblHan = new JLabel("Hóa đơn");
		lblHan.setHorizontalAlignment(SwingConstants.CENTER);
		lblHan.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHan.setBounds(10, 92, 110, 27);
		pnl4.add(lblHan);
		
		JPanel pnl5 = new JPanel();
		pnl5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sHelper.changeBackground(pnl5, SystemColor.controlHighlight);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sHelper.changeBackground(pnl5, SystemColor.menu);
			}
		});
		pnl5.setBackground(SystemColor.menu);
		pnl5.setBounds(230, 383, 130, 130);
		contentPane.add(pnl5);
		pnl5.setLayout(null);
		
		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/icons8_statistics_64px.png")));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(10, 11, 110, 81);
		pnl5.add(label_6);
		
		JLabel lblThu = new JLabel("Thống kê");
		lblThu.setHorizontalAlignment(SwingConstants.CENTER);
		lblThu.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblThu.setBounds(10, 92, 110, 27);
		pnl5.add(lblThu);
		
		JPanel pnl6 = new JPanel();
		pnl6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sHelper.changeBackground(pnl6, SystemColor.controlHighlight);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sHelper.changeBackground(pnl6, SystemColor.menu);
			}
		});
		pnl6.setBackground(SystemColor.menu);
		pnl6.setBounds(401, 383, 130, 130);
		contentPane.add(pnl6);
		pnl6.setLayout(null);
		
		JLabel label_8 = new JLabel("");
		label_8.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/icons8_user_credentials_64px.png")));
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(10, 11, 110, 81);
		pnl6.add(label_8);
		
		JLabel lblNhnVin_1 = new JLabel("Quản trị viên");
		lblNhnVin_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhnVin_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNhnVin_1.setBounds(10, 92, 110, 27);
		pnl6.add(lblNhnVin_1);
		
		JPanel pnl7 = new JPanel();
		pnl7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sHelper.changeBackground(pnl7, SystemColor.controlHighlight);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sHelper.changeBackground(pnl7, SystemColor.menu);
			}
		});
		pnl7.setLayout(null);
		pnl7.setBackground(SystemColor.menu);
		pnl7.setBounds(566, 215, 130, 130);
		contentPane.add(pnl7);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/icons8_user_groups_64px.png")));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 11, 110, 81);
		pnl7.add(label_1);
		
		JLabel lblCuHnh = new JLabel("Khách hàng");
		lblCuHnh.setHorizontalAlignment(SwingConstants.CENTER);
		lblCuHnh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCuHnh.setBounds(10, 92, 110, 27);
		pnl7.add(lblCuHnh);
		
		JPanel pnl8 = new JPanel();
		pnl8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sHelper.changeBackground(pnl8, SystemColor.controlHighlight);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sHelper.changeBackground(pnl8, SystemColor.menu);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if (sHelper.showConfirm(getContentPane(), "Bạn có chắc muốn đăng xuất không?"))
					logout();
			}
		});
		pnl8.setLayout(null);
		pnl8.setBackground(SystemColor.menu);
		pnl8.setBounds(566, 383, 130, 130);
		contentPane.add(pnl8);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/icons8_export_64px_2.png")));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(10, 11, 110, 81);
		pnl8.add(label_5);
		
		JLabel lblngXut = new JLabel("Đăng xuất");
		lblngXut.setHorizontalAlignment(SwingConstants.CENTER);
		lblngXut.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblngXut.setBounds(10, 92, 110, 27);
		pnl8.add(lblngXut);
		
		JLabel lblNhnVini = new JLabel("Nhân viên: Đại Hào");
		lblNhnVini.setBounds(0, 531, 744, 22);
		contentPane.add(lblNhnVini);
		lblNhnVini.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNhnVini.setHorizontalAlignment(SwingConstants.CENTER);
		setLocationRelativeTo(getOwner());
	}
	
	public void openBookJFrame()
	{
		bookJFrame.setVisible(true);
	}
	
	public void logout()
	{
		dispose();
		loginJFrame.setVisible(true);
	}
	public void showRentBook()
	{
		rentBookJFrame.setVisible(true);
	}
}
