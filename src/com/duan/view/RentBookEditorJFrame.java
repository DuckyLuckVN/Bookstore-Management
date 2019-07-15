package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RentBookEditorJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtTaiKhoang;
	private JTextField txtHo;
	private JTextField txtTen;
	private JTextField txtEmail;
	private JTextField txtSDT;
	private JTextField txtNgaySinh;
	private JTextField txtTenSach;
	private JTextField txtSoLuong;

	private SelectUserJDialog selectUserJDialog = new SelectUserJDialog();
	private SelectBookJDialog selectBookJDialog = new SelectBookJDialog();

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					RentBookEditorJFrame frame = new RentBookEditorJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public RentBookEditorJFrame(RentBookJFrame rentBookJFrame)
	{
		this();
		setLocationRelativeTo(rentBookJFrame);
	}

	public RentBookEditorJFrame() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RentBookEditorJFrame.class.getResource("/com/duan/icon/icons8_edit_property_50px.png")));
		setTitle("Thêm thuê sách");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 447, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNgiThu = new JLabel("Tài khoảng thuê:");
		lblNgiThu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgiThu.setBounds(10, 11, 104, 26);
		contentPane.add(lblNgiThu);
		
		txtTaiKhoang = new JTextField();
		txtTaiKhoang.setEditable(false);
		txtTaiKhoang.setBounds(124, 13, 214, 26);
		contentPane.add(txtTaiKhoang);
		txtTaiKhoang.setColumns(10);
		
		JButton btnSelectUser = new JButton("Chọn User");
		btnSelectUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSelectUser();
			}
		});
		btnSelectUser.setBounds(348, 13, 89, 209);
		contentPane.add(btnSelectUser);
		
		JLabel lblNewLabel = new JLabel("Họ:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 48, 104, 26);
		contentPane.add(lblNewLabel);
		
		txtHo = new JTextField();
		txtHo.setEditable(false);
		txtHo.setColumns(10);
		txtHo.setBounds(124, 48, 214, 26);
		contentPane.add(txtHo);
		
		JLabel lblEmail = new JLabel("Tên:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(10, 85, 104, 26);
		contentPane.add(lblEmail);
		
		txtTen = new JTextField();
		txtTen.setEditable(false);
		txtTen.setColumns(10);
		txtTen.setBounds(124, 85, 214, 26);
		contentPane.add(txtTen);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(124, 122, 214, 26);
		contentPane.add(txtEmail);
		
		JLabel label = new JLabel("Email:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 122, 104, 26);
		contentPane.add(label);
		
		JLabel lblSinThoi = new JLabel("Số điện thoại:");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSinThoi.setBounds(10, 159, 104, 26);
		contentPane.add(lblSinThoi);
		
		txtSDT = new JTextField();
		txtSDT.setEditable(false);
		txtSDT.setColumns(10);
		txtSDT.setBounds(124, 159, 214, 26);
		contentPane.add(txtSDT);
		
		txtNgaySinh = new JTextField();
		txtNgaySinh.setEditable(false);
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(124, 196, 214, 26);
		contentPane.add(txtNgaySinh);
		
		JLabel lblNgySinh = new JLabel("Ngày sinh:");
		lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgySinh.setBounds(10, 196, 104, 26);
		contentPane.add(lblNgySinh);
		
		JLabel lblSchChoThu = new JLabel("Sách cho thuê:");
		lblSchChoThu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSchChoThu.setBounds(10, 233, 104, 26);
		contentPane.add(lblSchChoThu);
		
		txtTenSach = new JTextField();
		txtTenSach.setEditable(false);
		txtTenSach.setColumns(10);
		txtTenSach.setBounds(124, 233, 214, 26);
		contentPane.add(txtTenSach);
		
		JButton btnSelectBook = new JButton("Chọn sách");
		btnSelectBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSelectBook();
			}
		});
		btnSelectBook.setBounds(348, 233, 87, 63);
		contentPane.add(btnSelectBook);
		
		JLabel lblSLng = new JLabel("Số lượng:");
		lblSLng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSLng.setBounds(10, 270, 104, 26);
		contentPane.add(lblSLng);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setEditable(false);
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(124, 270, 214, 26);
		contentPane.add(txtSoLuong);
		
		JButton btnConfirm = new JButton("Xác nhận");
		btnConfirm.setBounds(124, 350, 214, 36);
		contentPane.add(btnConfirm);
		
		JLabel lblTnhTrng = new JLabel("Tình trạng:");
		lblTnhTrng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTnhTrng.setBounds(10, 307, 104, 26);
		contentPane.add(lblTnhTrng);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Đang thuê", "Đã trả sách", "Mất sách"}));
		comboBox.setBounds(124, 307, 214, 26);
		contentPane.add(comboBox);
	}
	
	public void showSelectUser()
	{
		selectUserJDialog.setLocationRelativeTo(this);
		selectUserJDialog.setVisible(true);
	}
	
	public void showSelectBook()
	{
		selectBookJDialog.setLocationRelativeTo(this);
		selectBookJDialog.setVisible(true);
	}
}
