package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import com.duan.helper.DateHelper;
import com.duan.helper.SwingHelper;
import com.duan.model.Book;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;

public class BookEditorJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaSach;
	private JTextField txtTenSach;
	private JTextField txtSoTrang;
	private JTextField txtGia;
	private JTextField txtNhaXuatBan;
	private JComboBox cboTheLoai;
	private JTextArea txtGhiChu;
	private JComboBox cboNamXuatBan;
	
	
	CategoryJDialog categoryJDialog = new CategoryJDialog();
	
	private boolean isUpdateMode;
	private JTextField txtSoLuong;
	private JTextField txtTacGia;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookEditorJFrame frame = new BookEditorJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BookEditorJFrame() 
	{
		init();
		setTitle("Thêm Sách");
		isUpdateMode = false;
	}
	
	public BookEditorJFrame(Book book)
	{
		setTitle("Cập nhật sách");
		isUpdateMode = true;
		txtMaSach.setText(book.getId());
		txtTenSach.setText(book.getTitle());
		txtTacGia.setText(book.getAuthor());
		txtNhaXuatBan.setText(book.getPublisher());
		txtSoLuong.setText(book.getAmount() + "");
		txtSoTrang.setText(book.getPageNum() + "");
		cboNamXuatBan.setSelectedItem(book.getPublicationYear());
	
	}
	
	private void init()
	{
		setTitle("Thêm sách");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BookEditorJFrame.class.getResource("/com/duan/icon/icons8_edit_property_50px.png")));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 658, 412);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(10, 11, 398, 351);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblMaSach = new JLabel("Mã sách");
		lblMaSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMaSach.setBounds(10, 11, 75, 24);
		panel.add(lblMaSach);
		
		txtMaSach = new JTextField();
		txtMaSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMaSach.setBounds(95, 11, 293, 24);
		panel.add(txtMaSach);
		txtMaSach.setColumns(10);
		
		JLabel lblTieuDe = new JLabel("Tiêu đề");
		lblTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTieuDe.setBounds(10, 46, 75, 24);
		panel.add(lblTieuDe);
		
		txtTenSach = new JTextField();
		txtTenSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTenSach.setColumns(10);
		txtTenSach.setBounds(95, 46, 293, 24);
		panel.add(txtTenSach);
		
		JLabel lblTheLoai = new JLabel("Thể loại");
		lblTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTheLoai.setBounds(10, 81, 75, 24);
		panel.add(lblTheLoai);
		
		cboTheLoai = new JComboBox();
		cboTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboTheLoai.setBounds(95, 81, 191, 24);
		panel.add(cboTheLoai);
		
		JButton btnEditTheLoai = new JButton("Tùy chỉnh");
		btnEditTheLoai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				showCategoryJFrame();
			}
		});
		btnEditTheLoai.setBounds(296, 81, 92, 24);
		panel.add(btnEditTheLoai);
		
		JLabel lblSTrang = new JLabel("Số trang");
		lblSTrang.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSTrang.setBounds(10, 116, 75, 24);
		panel.add(lblSTrang);
		
		txtSoTrang = new JTextField();
		txtSoTrang.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSoTrang.setColumns(10);
		txtSoTrang.setBounds(95, 116, 75, 24);
		panel.add(txtSoTrang);
		
		JLabel lblSTrang_1 = new JLabel("Giá bán");
		lblSTrang_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSTrang_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSTrang_1.setBounds(180, 116, 46, 24);
		panel.add(lblSTrang_1);
		
		txtGia = new JTextField();
		txtGia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtGia.setColumns(10);
		txtGia.setBounds(236, 116, 124, 24);
		panel.add(txtGia);
		
		JLabel lblNhXutBn = new JLabel("Nhà xuất bản");
		lblNhXutBn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNhXutBn.setBounds(10, 186, 75, 24);
		panel.add(lblNhXutBn);
		
		txtNhaXuatBan = new JTextField();
		txtNhaXuatBan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNhaXuatBan.setColumns(10);
		txtNhaXuatBan.setBounds(95, 186, 293, 24);
		panel.add(txtNhaXuatBan);
		
		JLabel lblNmXutBn = new JLabel("Năm xuất bản");
		lblNmXutBn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNmXutBn.setBounds(10, 221, 81, 24);
		panel.add(lblNmXutBn);
		
		cboNamXuatBan = new JComboBox();
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int yearToday  = localDate.getYear();
		//System.out.println(yearToday);
		for (int i = 1975; i <= yearToday; i++)
		{
			cboNamXuatBan.addItem(i);
		}
		cboNamXuatBan.setBounds(95, 221, 112, 24);
		panel.add(cboNamXuatBan);
		
		JLabel lblGhiCh = new JLabel("Ghi chú");
		lblGhiCh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGhiCh.setBounds(10, 256, 75, 24);
		panel.add(lblGhiCh);
		
		txtGhiChu = new JTextArea();
		txtGhiChu.setBorder(new LineBorder(SystemColor.inactiveCaption));
		txtGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtGhiChu.setBounds(95, 256, 293, 84);
		panel.add(txtGhiChu);
		
		JLabel lblSLng = new JLabel("Số lượng");
		lblSLng.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSLng.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSLng.setBounds(217, 221, 57, 24);
		panel.add(lblSLng);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(284, 221, 104, 24);
		panel.add(txtSoLuong);
		
		JLabel lblVn = new JLabel("VNĐ");
		lblVn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVn.setBounds(360, 116, 28, 24);
		panel.add(lblVn);
		
		JLabel lblTcGi = new JLabel("Tác giả");
		lblTcGi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTcGi.setBounds(10, 151, 75, 24);
		panel.add(lblTcGi);
		
		txtTacGia = new JTextField();
		txtTacGia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTacGia.setColumns(10);
		txtTacGia.setBounds(95, 151, 293, 24);
		panel.add(txtTacGia);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBounds(418, 11, 214, 283);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 225, 214, 58);
		panel_1.add(panel_2);
		panel_2.setBackground(SystemColor.menu);
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.setLayout(null);
		
		JButton btnSelectPicture = new JButton("Chọn ảnh");
		SwingHelper.setTextBelowIconButton(btnSelectPicture);
		btnSelectPicture.setBounds(10, 11, 85, 36);
		panel_2.add(btnSelectPicture);
		
		JButton btnRemovePicture = new JButton("Xóa ảnh");
		btnRemovePicture.setBounds(115, 11, 89, 36);
		panel_2.add(btnRemovePicture);
		
		JLabel lblPicture = new JLabel("Chưa có ảnh");
		lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
		lblPicture.setBounds(10, 11, 194, 207);
		panel_1.add(lblPicture);
		
		JButton btnNewButton_2 = new JButton("Xác nhận");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setIcon(new ImageIcon(BookEditorJFrame.class.getResource("/com/duan/icon/icons8_checked_50px.png")));
		btnNewButton_2.setBounds(418, 305, 214, 58);
		contentPane.add(btnNewButton_2);
		
		setLocationRelativeTo(getOwner());
	}
	
	public void showCategoryJFrame()
	{
		categoryJDialog.setVisible(true);
	}
	
}
