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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RentBookEditorJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtTaiKhoang;
	private JTextField txtHo;
	private JTextField txtTen;
	private JTextField txtEmail;
	private JTextField txtSDT;
	private JTextField txtNgaySinh;

	private SelectUserJDialog selectUserJDialog = new SelectUserJDialog();
	private SelectBookJDialog selectBookJDialog = new SelectBookJDialog();
	private JTextField txtQuyn;
	private JTable tblBook;

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
		setBounds(100, 100, 447, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTKThue = new JLabel("Tài khoảng thuê:");
		lblTKThue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTKThue.setBounds(10, 11, 104, 26);
		contentPane.add(lblTKThue);
		
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
		
		JLabel lblHo = new JLabel("Họ:");
		lblHo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHo.setBounds(10, 48, 104, 26);
		contentPane.add(lblHo);
		
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
		
		JLabel lblSoDT = new JLabel("Số điện thoại:");
		lblSoDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSoDT.setBounds(10, 159, 104, 26);
		contentPane.add(lblSoDT);
		
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
		
		JLabel lblSachChoThue = new JLabel("Sách cho thuê:");
		lblSachChoThue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSachChoThue.setBounds(10, 233, 104, 26);
		contentPane.add(lblSachChoThue);
		
		JButton btnSelectBook = new JButton("Chọn sách");
		btnSelectBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSelectBook();
			}
		});
		btnSelectBook.setBounds(249, 233, 89, 26);
		contentPane.add(btnSelectBook);
		
		JButton btnConfirm = new JButton("Xác nhận");
		btnConfirm.setBounds(10, 462, 427, 36);
		contentPane.add(btnConfirm);
		
		JLabel lblTinhTrang = new JLabel("Tình trạng:");
		lblTinhTrang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTinhTrang.setBounds(10, 419, 68, 26);
		contentPane.add(lblTinhTrang);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Đang thuê", "Đã trả sách", "Mất sách"}));
		comboBox.setBounds(88, 419, 214, 26);
		contentPane.add(comboBox);
		
		txtQuyn = new JTextField();
		txtQuyn.setText("0 Quyển");
		txtQuyn.setEditable(false);
		txtQuyn.setColumns(10);
		txtQuyn.setBounds(124, 233, 115, 26);
		contentPane.add(txtQuyn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 270, 421, 138);
		contentPane.add(scrollPane);
		
		tblBook = new JTable();
		tblBook.setModel(new DefaultTableModel(null, new String[] {"MÃ SÁCH", "TÊN SÁCH", "GIÁ BÁN", "SỐ LƯỢNG", "XÓA"}) {
			
			//Column = 4 -> cột "XÓA"
			public boolean isCellEditable(int row, int column) {
				if (column == 4 || column == 3)
				{
					return true;
				}
				
				return false;
			}
			
			//Trả về dạng checkbox với cột thứ 4 ("XÓA")
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 4)
				{
					return boolean.class;
				}
				return super.getColumnClass(columnIndex);
			}
		});
		tblBook.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(tblBook);
		
		JButton btnDeleteBook = new JButton("Xóa sách");
		btnDeleteBook.setBounds(348, 233, 89, 26);
		contentPane.add(btnDeleteBook);
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
