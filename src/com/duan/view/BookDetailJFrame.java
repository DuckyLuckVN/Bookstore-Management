package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.SoftBevelBorder;
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

public class BookDetailJFrame extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookDetailJFrame frame = new BookDetailJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BookDetailJFrame(Component comp)
	{
		this();
		setLocationRelativeTo(comp);
	}
	public BookDetailJFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BookDetailJFrame.class.getResource("/com/duan/icon/icons8_details_popup_50px_1.png")));
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
		setBounds(100, 100, 552, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Không có ảnh");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblNewLabel.setBounds(10, 11, 232, 305);
		contentPane.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(252, 11, 244, 305);
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel item0 = new JPanel();
		panel_3.add(item0);
		FlowLayout fl_item0 = (FlowLayout) item0.getLayout();
		fl_item0.setAlignment(FlowLayout.LEFT);
		
		JLabel lblMSch = new JLabel("Mã Sách:");
		item0.add(lblMSch);
		lblMSch.setHorizontalAlignment(SwingConstants.LEFT);
		lblMSch.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblMaSach = new JLabel("10580");
		lblMaSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item0.add(lblMaSach);
		
		JPanel item1 = new JPanel();
		panel_3.add(item1);
		FlowLayout flowLayout = (FlowLayout) item1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JLabel lblTnSch_1 = new JLabel("Tên sách:");
		lblTnSch_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblTnSch_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		item1.add(lblTnSch_1);
		
		JLabel lblTenSach = new JLabel("Code Dạo Ký Sự");
		lblTenSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item1.add(lblTenSach);
		
		JPanel item2 = new JPanel();
		panel_3.add(item2);
		FlowLayout flowLayout_1 = (FlowLayout) item2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		
		JLabel lblTcGi = new JLabel("Tác giả:");
		item2.add(lblTcGi);
		lblTcGi.setHorizontalAlignment(SwingConstants.LEFT);
		lblTcGi.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblTacGia = new JLabel("Phạm Hoàng Huy");
		lblTacGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item2.add(lblTacGia);
		
		JPanel item3 = new JPanel();
		panel_3.add(item3);
		FlowLayout flowLayout_2 = (FlowLayout) item3.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		
		JLabel lblXutBn = new JLabel("Thể loại:");
		lblXutBn.setHorizontalAlignment(SwingConstants.LEFT);
		lblXutBn.setFont(new Font("Tahoma", Font.BOLD, 14));
		item3.add(lblXutBn);
		
		JLabel lblTheLoai = new JLabel("Công Nghệ Thông Tin");
		lblTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item3.add(lblTheLoai);
		
		JPanel item4 = new JPanel();
		panel_3.add(item4);
		FlowLayout fl_item4 = (FlowLayout) item4.getLayout();
		fl_item4.setAlignment(FlowLayout.LEFT);
		
		JLabel lblSTrang = new JLabel("Số trang:");
		lblSTrang.setHorizontalAlignment(SwingConstants.LEFT);
		lblSTrang.setFont(new Font("Tahoma", Font.BOLD, 14));
		item4.add(lblSTrang);
		
		JLabel lblTrang = new JLabel("325 trang");
		lblTrang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item4.add(lblTrang);
		
		JPanel item5 = new JPanel();
		panel_3.add(item5);
		FlowLayout fl_item5 = (FlowLayout) item5.getLayout();
		fl_item5.setAlignment(FlowLayout.LEFT);
		
		JLabel lblSLng = new JLabel("Xuất bản:");
		lblSLng.setHorizontalAlignment(SwingConstants.LEFT);
		lblSLng.setFont(new Font("Tahoma", Font.BOLD, 14));
		item5.add(lblSLng);
		
		JLabel lblXuatBan = new JLabel("Nhà Xuất Bản Trẻ (2017)");
		lblXuatBan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item5.add(lblXuatBan);
		
		JPanel item6 = new JPanel();
		panel_3.add(item6);
		FlowLayout fl_item6 = (FlowLayout) item6.getLayout();
		fl_item6.setAlignment(FlowLayout.LEFT);
		
		JLabel lblGiBn = new JLabel("Số lượng:");
		lblGiBn.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiBn.setFont(new Font("Tahoma", Font.BOLD, 14));
		item6.add(lblGiBn);
		
		JLabel lblSoLuong = new JLabel("30 quyển");
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item6.add(lblSoLuong);
		
		JPanel item7 = new JPanel();
		FlowLayout fl_item7 = (FlowLayout) item7.getLayout();
		fl_item7.setAlignment(FlowLayout.LEFT);
		panel_3.add(item7);
		
		JLabel lblGiBn_1 = new JLabel("Giá bán:");
		lblGiBn_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiBn_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		item7.add(lblGiBn_1);
		
		JLabel lblGia = new JLabel("110.000 đ");
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
		
		JLabel lblDaBan = new JLabel("15 quyển");
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
		
		JLabel lblChoThue = new JLabel("10 quyển");
		lblChoThue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item9.add(lblChoThue);
	}
}
