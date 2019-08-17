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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FindBookJDialog extends JDialog {

	private JPanel contentPane;
	private JTextField txtCode;
	private JTextField txtTitle;
	private JTextField txtFromNumPage;
	private JLabel lbln;
	private JTextField txtToNumPage;
	private JCheckBox chkPrice;
	private JTextField txtFromPrice;
	private JLabel label;
	private JTextField txtToPrice;
	private JCheckBox chkAuthor;
	private JTextField txtAuthor;

	private BookJFrame bookJFrame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindBookJDialog frame = new FindBookJDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FindBookJDialog(BookJFrame bookJFrame)
	{
		this();
		this.bookJFrame = bookJFrame;
		setLocationRelativeTo(bookJFrame);
	}
	public FindBookJDialog() {
		setModal(true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FindBookJDialog.class.getResource("/com/duan/icon/icons8_view_file_50px_1.png")));
		setTitle("Tìm kiếm sách");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 336, 273);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox chkCode = new JCheckBox("Mã sách");
		chkCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkCode.setBounds(6, 7, 84, 26);
		contentPane.add(chkCode);
		
		txtCode = new JTextField();
		txtCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCode.setBounds(96, 7, 225, 26);
		contentPane.add(txtCode);
		txtCode.setColumns(10);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTitle.setColumns(10);
		txtTitle.setBounds(96, 40, 225, 26);
		contentPane.add(txtTitle);
		
		JCheckBox chkTitle = new JCheckBox("Tên sách");
		chkTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkTitle.setBounds(6, 40, 84, 26);
		contentPane.add(chkTitle);
		
		txtFromNumPage = new JTextField();
		txtFromNumPage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFromNumPage.setColumns(10);
		txtFromNumPage.setBounds(96, 73, 84, 26);
		contentPane.add(txtFromNumPage);
		
		JCheckBox chkPage = new JCheckBox("Số trang");
		chkPage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkPage.setBounds(6, 73, 84, 26);
		contentPane.add(chkPage);
		
		lbln = new JLabel("Đến");
		lbln.setHorizontalAlignment(SwingConstants.CENTER);
		lbln.setBounds(190, 73, 27, 26);
		contentPane.add(lbln);
		
		txtToNumPage = new JTextField();
		txtToNumPage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtToNumPage.setColumns(10);
		txtToNumPage.setBounds(227, 73, 94, 26);
		contentPane.add(txtToNumPage);
		
		chkPrice = new JCheckBox("Giá tiền");
		chkPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkPrice.setBounds(6, 110, 84, 26);
		contentPane.add(chkPrice);
		
		txtFromPrice = new JTextField();
		txtFromPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFromPrice.setColumns(10);
		txtFromPrice.setBounds(96, 110, 84, 26);
		contentPane.add(txtFromPrice);
		
		label = new JLabel("Đến");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(190, 110, 27, 26);
		contentPane.add(label);
		
		txtToPrice = new JTextField();
		txtToPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtToPrice.setColumns(10);
		txtToPrice.setBounds(227, 110, 94, 26);
		contentPane.add(txtToPrice);
		
		chkAuthor = new JCheckBox("Tác giả");
		chkAuthor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkAuthor.setBounds(6, 143, 84, 26);
		contentPane.add(chkAuthor);
		
		txtAuthor = new JTextField();
		txtAuthor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(96, 143, 225, 26);
		contentPane.add(txtAuthor);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(4, 191, 314, 2);
		contentPane.add(separator);
		
		JButton btnHuy = new JButton("Toàn bộ");
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnHuy.setBounds(5, 201, 146, 36);
		contentPane.add(btnHuy);
		
		JButton btnTm = new JButton("Tìm");
		btnTm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTm.setBounds(174, 201, 146, 36);
		contentPane.add(btnTm);
		
		JLabel lblNewLabel = new JLabel("Không tìm thấy kết quả trùng khớp");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(8, 174, 310, 16);
		contentPane.add(lblNewLabel);
	}
}
