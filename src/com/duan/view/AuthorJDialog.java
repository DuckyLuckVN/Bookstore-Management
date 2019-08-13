package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.duan.custom.common.JDateChooserCustom;
import com.duan.custom.common.JTableBlue;
import com.duan.dao.LocationDAO;
import com.duan.model.Location;
import com.toedter.calendar.JDateChooser;

import net.miginfocom.layout.LC;

import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AuthorJDialog extends JDialog {

	private JPanel contentPane;
	private JTableBlue tblLocation;
	private JTextField txtMaKeSach;
	private JDateChooserCustom txtTenKe;
	private JDateChooserCustom txtNgayMat;
	
	LocationDAO dao;
	ArrayList<Location> list = new ArrayList<>();
	DefaultTableModel model;
	int index = -1;
	private JTextArea txtGhiChu;
	JButton btnThm;
	JButton btnCpNht;
	JButton btnXa;
	JButton btnMi;
	private JLabel lblAvatar;
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					AuthorJDialog frame = new AuthorJDialog();
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public AuthorJDialog() 
	{
		setModal(true);
		setResizable(false);
		setTitle("Quản lý tác giả");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CategoryJDialog.class.getResource("/com/duan/icon/icons8_medium_priority_50px.png")));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 621, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 227, 595, 189);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 8, 468, 208);
		panel.setLayout(null);
		
		tblLocation = new JTableBlue();
		tblLocation.setRowHeight(30);
		tblLocation.setModel(new DefaultTableModel(null, new String[] {"MÃ SỐ", "HỌ TÊN", "NGÀY SINH", "NGÀY MẤT", "NGÀY TẠO"})
		{
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tblLocation.getColumnModel().getColumn(1).setResizable(false);
		contentPane.setLayout(null);
		scrollPane.setViewportView(tblLocation);
		contentPane.add(scrollPane);
		contentPane.add(panel);
		
		JLabel lblMThLoi = new JLabel("Họ tên:");
		lblMThLoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMThLoi.setBounds(140, 11, 77, 25);
		panel.add(lblMThLoi);
		
		txtMaKeSach = new JTextField();
		txtMaKeSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaKeSach.setBounds(227, 11, 231, 25);
		panel.add(txtMaKeSach);
		txtMaKeSach.setColumns(10);
		
		txtTenKe = new JDateChooserCustom();
		txtTenKe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTenKe.setBounds(227, 47, 231, 25);
		panel.add(txtTenKe);
		
		JLabel lblTnThLoi = new JLabel("Ngày sinh:");
		lblTnThLoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTnThLoi.setBounds(140, 47, 77, 25);
		panel.add(lblTnThLoi);
		
		JLabel lblGhiCh = new JLabel("Giới thiệu:");
		lblGhiCh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGhiCh.setBounds(140, 119, 77, 25);
		panel.add(lblGhiCh);
		
		txtGhiChu = new JTextArea();
		txtGhiChu.setBorder(new LineBorder(SystemColor.controlShadow));
		txtGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtGhiChu.setBounds(227, 122, 231, 67);
		panel.add(txtGhiChu);
		
		JLabel lblSLngLu = new JLabel("Ngày mất:");
		lblSLngLu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSLngLu.setBounds(140, 83, 77, 25);
		panel.add(lblSLngLu);
		
		txtNgayMat = new JDateChooserCustom();
		txtNgayMat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNgayMat.setBounds(227, 83, 231, 25);
		panel.add(txtNgayMat);
		
		lblAvatar = new JLabel("");
		lblAvatar.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblAvatar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAvatar.setBounds(10, 17, 120, 140);
		panel.add(lblAvatar);
		
		JButton btnChn = new JButton("Chọn");
		btnChn.setBounds(10, 160, 59, 33);
		panel.add(btnChn);
		
		JButton btnXa_1 = new JButton("Xóa");
		btnXa_1.setBounds(71, 160, 59, 33);
		panel.add(btnXa_1);
		
		JPanel pnlControll = new JPanel();
		pnlControll.setBorder(new TitledBorder(null, "\u0110i\u1EC1u khi\u1EC3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlControll.setBounds(488, 8, 117, 208);
		contentPane.add(pnlControll);
		pnlControll.setLayout(new GridLayout(0, 1, 0, 10));
		
		btnThm = new JButton(" Lưu");
		
		btnMi = new JButton(" Mới");
		btnMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				clearForm();
				setControllMode_Insert();
				unlockForm();
				tblLocation.setRequestFocusEnabled(false);
			}
		});
		pnlControll.add(btnMi);
		btnMi.setHorizontalAlignment(SwingConstants.LEFT);
		btnMi.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/Create.png")));
		pnlControll.add(btnThm);
		btnThm.setHorizontalAlignment(SwingConstants.LEFT);
		btnThm.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/Accept.png")));
		
		btnCpNht = new JButton("Cập nhật");
		pnlControll.add(btnCpNht);
		btnCpNht.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/Notes.png")));
		btnCpNht.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnXa = new JButton("Xóa");
		pnlControll.add(btnXa);
		btnXa.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/icons8_delete_32px_1.png")));
		btnXa.setHorizontalAlignment(SwingConstants.LEFT);
		setLocationRelativeTo(getOwner());

	}
	
	

	
	public void lockForm()
	{
		txtMaKeSach.setEnabled(false);
		txtTenKe.setEnabled(false);
		txtNgayMat.setEnabled(false);
		txtGhiChu.setEditable(false);
	}
	public void unlockForm() 
	{
		txtMaKeSach.setEditable(true);
		txtTenKe.setEnabled(true);
		txtNgayMat.setEnabled(true);
		txtGhiChu.setEditable(true);
	}
	public void setControllMode_Nothing()
	{
		btnMi.setEnabled(true);
		btnThm.setEnabled(false);
		btnXa.setEnabled(false);
		btnCpNht.setEnabled(false);
	}
	public void setControllMode_Edit()
	{
		btnMi.setEnabled(true);
		btnCpNht.setEnabled(true);
		btnXa.setEnabled(true);
		btnThm.setEnabled(false);
	}
	
	public void setControllMode_Insert()
	{
		btnMi.setEnabled(false);
		btnThm.setEnabled(true);
		btnXa.setEnabled(false);
		btnCpNht.setEnabled(false);
	}
	public void clearForm()
	{
		txtGhiChu.setText("");
		txtMaKeSach.setText("");
		txtNgayMat.setDate(null);;
		txtTenKe.setDate(null);;
	}
}
