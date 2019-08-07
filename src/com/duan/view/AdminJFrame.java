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

import com.duan.custom.CustomJTableRed;
import com.duan.helper.DataHelper;
import com.duan.helper.SwingHelper;
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
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ButtonGroup;

public class AdminJFrame extends JFrame {

	private JPanel contentPane;
	private CustomJTableRed tblUser;
	private JTextField txtFind;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtFullname;
	private JTextField txtEmail;
	private JTextField txtPhoneNum;
	private JLabel lblAnh ;
	private JComboBox cboChucVu;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private File fileAnhDaChon = null;

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
		setBounds(100, 100, 699, 623);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel pnlForm = new JPanel();
		pnlForm.setBorder(new TitledBorder(null, "Th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel pnlController = new JPanel();
		pnlController.setBorder(new TitledBorder(null, "\u0110i\u1EC1u khi\u1EC3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblTmKim = new JLabel("Tìm kiếm");
		lblTmKim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtFind = new JTextField();
		txtFind.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				//su kien nha nut
			}
		});
		txtFind.setColumns(10);
		
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
		
		cboChucVu = new JComboBox();
		cboChucVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboChucVu.setModel(new DefaultComboBoxModel(new String[] {"Nhân viên", "Quản Lý ", "Giám Đốc"}));
		pnlController.setLayout(new GridLayout(0, 1, 0, 5));
		
		JButton btnToMi = new JButton("Tạo mới");
		btnToMi.setHorizontalAlignment(SwingConstants.LEFT);
		btnToMi.setIcon(new ImageIcon(AdminJFrame.class.getResource("/com/duan/icon/Create.png")));
		btnToMi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlController.add(btnToMi);
		
		JButton btnThm = new JButton(" Thêm");
		btnThm.setHorizontalAlignment(SwingConstants.LEFT);
		btnThm.setIcon(new ImageIcon(AdminJFrame.class.getResource("/com/duan/icon/Accept.png")));
		btnThm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlController.add(btnThm);
		
		JButton btnCpNht = new JButton(" Cập nhật");
		btnCpNht.setHorizontalAlignment(SwingConstants.LEFT);
		btnCpNht.setIcon(new ImageIcon(AdminJFrame.class.getResource("/com/duan/icon/Notes.png")));
		btnCpNht.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlController.add(btnCpNht);
		
		JButton btnXa = new JButton(" Xóa");
		btnXa.setHorizontalAlignment(SwingConstants.LEFT);
		btnXa.setIcon(new ImageIcon(AdminJFrame.class.getResource("/com/duan/icon/Delete.png")));
		btnXa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlController.add(btnXa);
		
		tblUser = new CustomJTableRed();
		tblUser.setModel(new DefaultTableModel(null, new String[] {"MÃ SỐ", "TÀI KHOẢNG", "HỌ TÊN", "EMAIL", "SỐ ĐIỆN THOẠI", "CHỨC VỤ"}) 
		{
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tblUser.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(tblUser);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				int index_cbo_selected = cboChucVu.getSelectedIndex();
				int role;
				switch (index_cbo_selected) {
				case 0:
					//Nhan Vien
					role = 3;
					break;
				case 1:
					//Quan ly
					role = 2;
					break;
				case 2:
					//Giam doc
					role = 1;
					break;
				}
				System.out.println();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(pnlForm, GroupLayout.PREFERRED_SIZE, 505, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnlController, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addGap(1))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 287, Short.MAX_VALUE)
					.addComponent(lblTmKim)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtFind, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pnlController, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pnlForm, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTmKim, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFind, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
		);
		
		JPanel panel = new JPanel();
		
		lblAnh = new JLabel("Không có ảnh");
		lblAnh.setBounds(0, 0, 181, 235);
		
		
		
		//Set icon xong moi goi setAutoResizeIcon
		lblAnh.setIcon(new ImageIcon(AdminJFrame.class.getResource("/com/duan/image/S1e18_Waddles_stare.png")));
		SwingHelper.setAutoResizeIcon(lblAnh);
		
		
		
		
		lblAnh.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblAnh.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnChnnh = new JButton("Chọn ảnh");
		btnChnnh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser chooser = new JFileChooser();
				int status = chooser.showOpenDialog(contentPane);
				
				if (status == JFileChooser.APPROVE_OPTION)
				{
					
					System.out.println("Da94 chon file");
					fileAnhDaChon = chooser.getSelectedFile();
					System.out.println(fileAnhDaChon.getName());
					String duongdanString = DataHelper.getRootSource() + "com/duan/image/";
					
				}
				
				
			}
		});
		btnChnnh.setBounds(0, 246, 85, 37);
		
		JButton btnXa_1 = new JButton("Xóa");
		btnXa_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				lblAnh.setIcon(null);
			}
		});
		btnXa_1.setBounds(96, 246, 85, 37);
		
		JLabel lblGiiTnh = new JLabel("Giới tính");
		lblGiiTnh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JRadioButton rdoNam = new JRadioButton("Nam");
		buttonGroup.add(rdoNam);
		rdoNam.setSelected(true);
		
		JRadioButton rdoNu = new JRadioButton("Nữ");
		buttonGroup.add(rdoNu);
		GroupLayout gl_pnlForm = new GroupLayout(pnlForm);
		gl_pnlForm.setHorizontalGroup(
			gl_pnlForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlForm.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_pnlForm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTiKhong, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMtKhu, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHTn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addGap(2)
							.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addGap(2)
							.addComponent(lblSinThoi, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblGiiTnh, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblChcV, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(gl_pnlForm.createParallelGroup(Alignment.LEADING)
						.addComponent(txtUsername, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
						.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
						.addComponent(txtFullname, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
						.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
						.addComponent(txtPhoneNum, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addComponent(rdoNam, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(rdoNu, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
						.addComponent(cboChucVu, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
					.addGap(6))
		);
		gl_pnlForm.setVerticalGroup(
			gl_pnlForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlForm.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_pnlForm.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addComponent(lblTiKhong, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(lblMtKhu, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(lblHTn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(16)
							.addComponent(lblSinThoi, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(lblGiiTnh, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(lblChcV, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(txtFullname, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(txtPhoneNum, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addGroup(gl_pnlForm.createParallelGroup(Alignment.LEADING)
								.addComponent(rdoNam, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(rdoNu, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
							.addGap(9)
							.addComponent(cboChucVu, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		);
		panel.setLayout(null);
		panel.add(lblAnh);
		panel.add(btnChnnh);
		panel.add(btnXa_1);
		pnlForm.setLayout(gl_pnlForm);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void test()
	{
		//lblAnh
	}
}
