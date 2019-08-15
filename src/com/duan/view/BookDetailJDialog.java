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

import com.duan.dao.AuthorDAO;
import com.duan.dao.BookDAO;
import com.duan.dao.CategoryDAO;
import com.duan.dao.LocationDAO;
import com.duan.dao.PublisherDAO;
import com.duan.helper.DataHelper;
import com.duan.helper.SettingSave;
import com.duan.helper.SwingHelper;
import com.duan.model.Book;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

public class BookDetailJDialog extends JDialog {

	private JPanel contentPane;
	private JLabel lblMaSach;
	private JLabel lblTenSach;
	private JLabel lblTacGia;
	private JLabel lblTheLoai;
	private JLabel lblTrang;
	private JLabel lblXuatBan;
	private JLabel lblSoLuong;
	private JLabel lblGia;
	private JLabel lblDaBan;
	private JLabel lblChoThue;
	private JLabel lblImage;
	private JLabel lblViTri;
	private JTextField txtDescription;
	private JTextArea txtIntroduce;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					BookDetailJDialog frame = new BookDetailJDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BookDetailJDialog(Component comp)
	{
		this();
		setLocationRelativeTo(comp);
	}
	public BookDetailJDialog() 
	{
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BookDetailJDialog.class.getResource("/com/duan/icon/icons8_details_popup_50px_1.png")));
		setTitle("Code Dạo Ký Sự");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 608, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblImage = new JLabel("Không có ảnh");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblImage.setBounds(10, 11, 278, 365);
		contentPane.add(lblImage);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Th\u00F4ng tin v\u1EC1 s\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(298, 11, 294, 365);
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel item0 = new JPanel();
		panel_3.add(item0);
		FlowLayout fl_item0 = (FlowLayout) item0.getLayout();
		fl_item0.setAlignment(FlowLayout.LEFT);
		
		JLabel lblMSch = new JLabel("Mã Sách:");
		lblMSch.setForeground(Color.DARK_GRAY);
		item0.add(lblMSch);
		lblMSch.setHorizontalAlignment(SwingConstants.LEFT);
		lblMSch.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblMaSach = new JLabel("10580");
		lblMaSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item0.add(lblMaSach);
		
		JPanel item1 = new JPanel();
		panel_3.add(item1);
		FlowLayout flowLayout = (FlowLayout) item1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JLabel lblTnSch_1 = new JLabel("Tên sách:");
		lblTnSch_1.setForeground(Color.DARK_GRAY);
		lblTnSch_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblTnSch_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		item1.add(lblTnSch_1);
		
		lblTenSach = new JLabel("Code Dạo Ký Sự");
		lblTenSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item1.add(lblTenSach);
		
		JPanel item2 = new JPanel();
		panel_3.add(item2);
		FlowLayout flowLayout_1 = (FlowLayout) item2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		
		JLabel lblTcGi = new JLabel("Tác giả:");
		lblTcGi.setForeground(Color.DARK_GRAY);
		item2.add(lblTcGi);
		lblTcGi.setHorizontalAlignment(SwingConstants.LEFT);
		lblTcGi.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblTacGia = new JLabel("Phạm Hoàng Huy");
		lblTacGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item2.add(lblTacGia);
		
		JPanel item3 = new JPanel();
		panel_3.add(item3);
		FlowLayout flowLayout_2 = (FlowLayout) item3.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		
		JLabel lblXutBn = new JLabel("Thể loại:");
		lblXutBn.setForeground(Color.DARK_GRAY);
		lblXutBn.setHorizontalAlignment(SwingConstants.LEFT);
		lblXutBn.setFont(new Font("Tahoma", Font.BOLD, 14));
		item3.add(lblXutBn);
		
		lblTheLoai = new JLabel("Công Nghệ Thông Tin");
		lblTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item3.add(lblTheLoai);
		
		JPanel item4 = new JPanel();
		panel_3.add(item4);
		FlowLayout fl_item4 = (FlowLayout) item4.getLayout();
		fl_item4.setAlignment(FlowLayout.LEFT);
		
		JLabel lblSTrang = new JLabel("Số trang:");
		lblSTrang.setForeground(Color.DARK_GRAY);
		lblSTrang.setHorizontalAlignment(SwingConstants.LEFT);
		lblSTrang.setFont(new Font("Tahoma", Font.BOLD, 14));
		item4.add(lblSTrang);
		
		lblTrang = new JLabel("325 trang");
		lblTrang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item4.add(lblTrang);
		
		JPanel item5 = new JPanel();
		panel_3.add(item5);
		FlowLayout fl_item5 = (FlowLayout) item5.getLayout();
		fl_item5.setAlignment(FlowLayout.LEFT);
		
		JLabel lblSLng = new JLabel("Xuất bản:");
		lblSLng.setForeground(Color.DARK_GRAY);
		lblSLng.setHorizontalAlignment(SwingConstants.LEFT);
		lblSLng.setFont(new Font("Tahoma", Font.BOLD, 14));
		item5.add(lblSLng);
		
		lblXuatBan = new JLabel("Nhà Xuất Bản Trẻ (2017)");
		lblXuatBan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item5.add(lblXuatBan);
		
		JPanel item10 = new JPanel();
		FlowLayout fl_item10 = (FlowLayout) item10.getLayout();
		fl_item10.setAlignment(FlowLayout.LEFT);
		panel_3.add(item10);
		
		JLabel lblVTr = new JLabel("Vị trí:");
		lblVTr.setHorizontalAlignment(SwingConstants.LEFT);
		lblVTr.setForeground(Color.DARK_GRAY);
		lblVTr.setFont(new Font("Tahoma", Font.BOLD, 14));
		item10.add(lblVTr);
		
		lblViTri = new JLabel("30 quyển");
		lblViTri.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item10.add(lblViTri);
		
		JPanel item6 = new JPanel();
		panel_3.add(item6);
		FlowLayout fl_item6 = (FlowLayout) item6.getLayout();
		fl_item6.setAlignment(FlowLayout.LEFT);
		
		JLabel lblGiBn = new JLabel("Số lượng:");
		lblGiBn.setForeground(Color.DARK_GRAY);
		lblGiBn.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiBn.setFont(new Font("Tahoma", Font.BOLD, 14));
		item6.add(lblGiBn);
		
		lblSoLuong = new JLabel("30 quyển");
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item6.add(lblSoLuong);
		
		JPanel item7 = new JPanel();
		FlowLayout fl_item7 = (FlowLayout) item7.getLayout();
		fl_item7.setAlignment(FlowLayout.LEFT);
		panel_3.add(item7);
		
		JLabel lblGiBn_1 = new JLabel("Giá bán:");
		lblGiBn_1.setForeground(Color.DARK_GRAY);
		lblGiBn_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiBn_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		item7.add(lblGiBn_1);
		
		lblGia = new JLabel("110.000 đ");
		lblGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item7.add(lblGia);
		
		JPanel item8 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) item8.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_3.add(item8);
		
		JLabel lblBn = new JLabel("Đã bán:");
		lblBn.setForeground(Color.RED);
		lblBn.setHorizontalAlignment(SwingConstants.LEFT);
		lblBn.setFont(new Font("Tahoma", Font.BOLD, 14));
		item8.add(lblBn);
		
		lblDaBan = new JLabel("15 quyển");
		lblDaBan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item8.add(lblDaBan);
		
		JPanel item9 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) item9.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_3.add(item9);
		
		JLabel lblangChoThu = new JLabel("Đang cho thuê:");
		lblangChoThu.setHorizontalAlignment(SwingConstants.LEFT);
		lblangChoThu.setForeground(Color.BLUE);
		lblangChoThu.setFont(new Font("Tahoma", Font.BOLD, 14));
		item9.add(lblangChoThu);
		
		lblChoThue = new JLabel("10 quyển");
		lblChoThue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item9.add(lblChoThue);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Gi\u1EDBi thi\u1EC7u", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(10, 387, 582, 97);
		contentPane.add(scrollPane);
		
		txtIntroduce = new JTextArea();
		txtIntroduce.setWrapStyleWord(true);
		txtIntroduce.setLineWrap(true);
		txtIntroduce.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtIntroduce.setOpaque(false);
		txtIntroduce.setEditable(false);
		scrollPane.setViewportView(txtIntroduce);
		
		JLabel lblGhiCh = new JLabel("Ghi chú:");
		lblGhiCh.setHorizontalAlignment(SwingConstants.LEFT);
		lblGhiCh.setForeground(Color.DARK_GRAY);
		lblGhiCh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGhiCh.setBounds(10, 494, 69, 17);
		contentPane.add(lblGhiCh);
		
		txtDescription = new JTextField();
		txtDescription.setBorder(null);
		txtDescription.setEditable(false);
		txtDescription.setBounds(89, 495, 503, 21);
		contentPane.add(txtDescription);
		txtDescription.setColumns(10);
	}
	
	public void setDetail(Book book) throws SQLException
	{
		String price = DataHelper.getFormatForMoney(book.getPrice()) + SettingSave.getSetting().getMoneySymbol();
		String publisher = PublisherDAO.findById(book.getPublisherId()).getName() + " (" + book.getPublicationYear() + ")";
		String categoryTitle = CategoryDAO.getTitleById(book.getCategoryId());
		String locationTitle = LocationDAO.findByID(book.getLocationId()).getLocationName();
		String authorName = AuthorDAO.findById(book.getAuthorId()).getFullName();
		
		lblMaSach.setText(book.getId());
		lblTenSach.setText(book.getTitle());
		lblTacGia.setText(authorName);
		lblTheLoai.setText(categoryTitle);
		lblTrang.setText(book.getPageNum() + " trang");
		lblXuatBan.setText(publisher);
		lblSoLuong.setText(book.getAmount() + " quyển");
		lblViTri.setText(locationTitle);
		lblGia.setText(price);
		lblDaBan.setText(BookDAO.getCountSold(book.getId()) + " quyển");
		lblChoThue.setText(BookDAO.getCountBeingRented(book.getId()) + " quyển");
		setTitle("Thông tin sách | " + book.getTitle());
		
		//Set image
		if (book.getImage() != null)
		{
			try 
			{
				setImage(book.getImage());
			} 
			catch (NullPointerException e) 
			{
				setImage(null);
			}
		}
		else 
		{
			setImage(null);
		}
		
	}
	
	//Cập nhật lại ảnh của sách
	public void setImage(String imageName)
	{
		if (imageName != null && imageName.length() > 0)
		{
			URL url = getClass().getResource("/com/duan/image/" + imageName);
			ImageIcon icon = new ImageIcon(url);
			lblImage.setIcon(icon);
			lblImage.setText("");
			SwingHelper.setAutoResizeIcon(lblImage);
		}
		else
		{
			lblImage.setIcon(null);
			lblImage.setText("Không có ảnh");
		}
	}
}
