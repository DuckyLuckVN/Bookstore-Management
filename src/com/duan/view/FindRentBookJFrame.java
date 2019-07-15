package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.Cursor;

public class FindRentBookJFrame extends JDialog {

	private JPanel contentPane;
	private JTextField txtCode;
	private JTextField txtTitle;
	private JTextField txtToNumPage;
	private JCheckBox chkPrice;
	private JTextField txtFromPrice;
	private JLabel label;
	private JTextField txtToPrice;
	private JCheckBox chkAuthor;

	private RentBookJFrame rentBookJFrame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindRentBookJFrame frame = new FindRentBookJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FindRentBookJFrame(RentBookJFrame rentBookJFrame)
	{
		this();
		this.rentBookJFrame = rentBookJFrame;
		setLocationRelativeTo(rentBookJFrame);
	}
	public FindRentBookJFrame() {
		setModal(true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FindRentBookJFrame.class.getResource("/com/duan/icon/icons8_view_file_50px_1.png")));
		setTitle("Tìm kiếm sách");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 372, 281);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox chkCode = new JCheckBox("Mã thuê");
		chkCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkCode.setBounds(6, 7, 89, 26);
		contentPane.add(chkCode);
		
		txtCode = new JTextField();
		txtCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCode.setBounds(101, 7, 259, 26);
		contentPane.add(txtCode);
		txtCode.setColumns(10);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTitle.setColumns(10);
		txtTitle.setBounds(101, 40, 259, 26);
		contentPane.add(txtTitle);
		
		JCheckBox chkTitle = new JCheckBox("Tài khoảng");
		chkTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkTitle.setBounds(6, 40, 89, 26);
		contentPane.add(chkTitle);
		
		JCheckBox chkPage = new JCheckBox("Quản lý");
		chkPage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkPage.setBounds(6, 73, 89, 26);
		contentPane.add(chkPage);
		
		txtToNumPage = new JTextField();
		txtToNumPage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtToNumPage.setColumns(10);
		txtToNumPage.setBounds(101, 73, 259, 26);
		contentPane.add(txtToNumPage);
		
		chkPrice = new JCheckBox("Ngày thuê");
		chkPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkPrice.setBounds(6, 110, 89, 26);
		contentPane.add(chkPrice);
		
		txtFromPrice = new JTextField();
		txtFromPrice.setText("12/07/2019");
		txtFromPrice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtFromPrice.setEditable(false);
		txtFromPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFromPrice.setColumns(10);
		txtFromPrice.setBounds(101, 110, 106, 26);
		contentPane.add(txtFromPrice);
		
		label = new JLabel("Đến");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(217, 111, 27, 26);
		contentPane.add(label);
		
		txtToPrice = new JTextField();
		txtToPrice.setText("14/07/2019");
		txtToPrice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtToPrice.setEditable(false);
		txtToPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtToPrice.setColumns(10);
		txtToPrice.setBounds(254, 110, 106, 26);
		contentPane.add(txtToPrice);
		
		chkAuthor = new JCheckBox("Tình trạng");
		chkAuthor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkAuthor.setBounds(6, 143, 89, 26);
		contentPane.add(chkAuthor);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 200, 354, 2);
		contentPane.add(separator);
		
		JButton btnHuy = new JButton("Toàn bộ");
		btnHuy.setBounds(5, 209, 146, 36);
		contentPane.add(btnHuy);
		
		JButton btnTm = new JButton("Tìm");
		btnTm.setBounds(214, 209, 146, 36);
		contentPane.add(btnTm);
		
		JLabel lblNewLabel = new JLabel("Không tìm thấy kết quả trùng khớp");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(8, 182, 348, 16);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(101, 143, 259, 26);
		contentPane.add(comboBox);
	}
}
