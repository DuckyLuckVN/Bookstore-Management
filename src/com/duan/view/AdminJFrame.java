package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AdminJFrame extends JFrame {

	private JPanel contentPane;
	private JTable tblUser;
	private JTextField txtFind;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtFullname;
	private JTextField txtEmail;
	private JTextField txtPhoneNum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminJFrame frame = new AdminJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminJFrame() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminJFrame.class.getResource("/com/duan/icon/icons8_user_groups_64px.png")));
		setTitle("Quản Lý Người Dùng");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 699, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblTitle = new JLabel("QUẢN LÝ NGƯỜI DÙNG");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel pnlForm = new JPanel();
		pnlForm.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel pnlController = new JPanel();
		pnlController.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JLabel lblTmKim = new JLabel("Tìm kiếm");
		lblTmKim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtFind = new JTextField();
		txtFind.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTmKim)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtFind, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(pnlForm, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnlController, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)))
					.addGap(1))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pnlForm, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlController, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFind, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTmKim, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
		);
		
		JLabel lblTiKhong = new JLabel("Tài khoảng");
		lblTiKhong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPassword.setColumns(10);
		
		JLabel lblMtKhu = new JLabel("Mật khẩu");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtFullname = new JTextField();
		txtFullname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFullname.setColumns(10);
		
		JLabel lblHTn = new JLabel("Họ tên");
		lblHTn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		
		txtPhoneNum = new JTextField();
		txtPhoneNum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPhoneNum.setColumns(10);
		
		JLabel lblSinThoi = new JLabel("Số điện thoại");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblChcV = new JLabel("Chức vụ");
		lblChcV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nhân viên", "Quản Lý", "Giám Đốc"}));
		GroupLayout gl_pnlForm = new GroupLayout(pnlForm);
		gl_pnlForm.setHorizontalGroup(
			gl_pnlForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlForm.createSequentialGroup()
					.addGap(8)
					.addComponent(lblTiKhong, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(txtUsername, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
					.addGap(8))
				.addGroup(gl_pnlForm.createSequentialGroup()
					.addGap(8)
					.addComponent(lblMtKhu, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
					.addGap(8))
				.addGroup(gl_pnlForm.createSequentialGroup()
					.addGap(8)
					.addComponent(lblHTn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(txtFullname, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
					.addGap(8))
				.addGroup(gl_pnlForm.createSequentialGroup()
					.addGap(10)
					.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
					.addGap(8))
				.addGroup(gl_pnlForm.createSequentialGroup()
					.addGap(10)
					.addComponent(lblSinThoi, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(txtPhoneNum, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
					.addGap(8))
				.addGroup(gl_pnlForm.createSequentialGroup()
					.addGap(8)
					.addComponent(lblChcV, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
		);
		gl_pnlForm.setVerticalGroup(
			gl_pnlForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlForm.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_pnlForm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTiKhong, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_pnlForm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMtKhu, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_pnlForm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHTn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFullname, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_pnlForm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addGap(5)
							.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
					.addGap(11)
					.addGroup(gl_pnlForm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSinThoi, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPhoneNum, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_pnlForm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblChcV, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
		);
		pnlForm.setLayout(gl_pnlForm);
		pnlController.setLayout(new GridLayout(0, 1, 0, 5));
		
		JButton btnToMi = new JButton("Tạo mới");
		btnToMi.setHorizontalAlignment(SwingConstants.LEFT);
		btnToMi.setIcon(new ImageIcon(AdminJFrame.class.getResource("/com/duan/icon/Create.png")));
		btnToMi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlController.add(btnToMi);
		
		JButton btnThm = new JButton(" Thêm");
		btnThm.setHorizontalAlignment(SwingConstants.LEFT);
		btnThm.setIcon(new ImageIcon(AdminJFrame.class.getResource("/com/duan/icon/Accept.png")));
		btnThm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlController.add(btnThm);
		
		JButton btnCpNht = new JButton(" Cập nhật");
		btnCpNht.setHorizontalAlignment(SwingConstants.LEFT);
		btnCpNht.setIcon(new ImageIcon(AdminJFrame.class.getResource("/com/duan/icon/Notes.png")));
		btnCpNht.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlController.add(btnCpNht);
		
		JButton btnXa = new JButton(" Xóa");
		btnXa.setHorizontalAlignment(SwingConstants.LEFT);
		btnXa.setIcon(new ImageIcon(AdminJFrame.class.getResource("/com/duan/icon/Delete.png")));
		btnXa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlController.add(btnXa);
		
		tblUser = new JTable();
		tblUser.setModel(new DefaultTableModel(null, new String[] {"MÃ SỐ", "TÀI KHOẢNG", "HỌ TÊN", "EMAIL", "SỐ ĐIỆN THOẠI", "CHỨC VỤ"}) 
		{
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tblUser.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(tblUser);
		contentPane.setLayout(gl_contentPane);
	}
}
