package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.duan.custom.common.JPanelFlat;
import com.duan.custom.common.JScrollPaneFlat;
import com.duan.custom.common.JTableRed;
import com.duan.custom.common.JTextFieldDark;
import com.duan.custom.message.MessageOptionPane;
import com.duan.dao.AdminDAO;
import com.duan.helper.DataHelper;
import com.duan.helper.SwingHelper;
import com.duan.model.Admin;
import com.toedter.calendar.JDateChooser;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class AdminJFrame extends JFrame {

	private JPanel contentPane;
	private JTableRed tblUser;
	private JTextFieldDark txtFind;
	private JTextFieldDark txtUsername;
	private JTextFieldDark txtPassword;
	private JTextFieldDark txtFullname;
	private JTextFieldDark txtEmail;
	private JTextFieldDark txtPhoneNum;
	private JLabel lblAnh ;
	private JComboBox cboChucVu;
	private final ButtonGroup bgrSex = new ButtonGroup();
	private JButton btnNew;
	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JRadioButton rdoNam;
	private JRadioButton rdoNu;
	private JButton btnXoa;
	private JButton btnChonanh;
	
	private File fileImageSelected;
	private int index = -1;
	private int indexSelectedLast = -1;
	
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
		
		JPanel pnlController = new JPanelFlat();
		pnlController.setBackground(SystemColor.menu);
		pnlController.setBorder(new TitledBorder(null, "\u0110i\u1EC1u khi\u1EC3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblTmKim = new JLabel("Tìm kiếm");
		lblTmKim.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("rdoNam isSelected: " + rdoNam.isSelected());
			}
		});
		lblTmKim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtFind = new JTextFieldDark();
		txtFind.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				//su kien nha nut
			}
		});
		txtFind.setColumns(10);
		
		JLabel lblTiKhong = new JLabel("Tài khoản");
		lblTiKhong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtUsername = new JTextFieldDark();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUsername.setColumns(10);
		
		txtPassword = new JTextFieldDark();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPassword.setColumns(10);
		
		JLabel lblMtKhu = new JLabel("Mật khẩu");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtFullname = new JTextFieldDark();
		txtFullname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFullname.setColumns(10);
		
		JLabel lblHTn = new JLabel("Họ tên");
		lblHTn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtEmail = new JTextFieldDark();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		
		txtPhoneNum = new JTextFieldDark();
		txtPhoneNum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPhoneNum.setColumns(10);
		
		JLabel lblSinThoi = new JLabel("Số điện thoại");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblChcV = new JLabel("Chức vụ");
		lblChcV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		cboChucVu = new JComboBox();
		cboChucVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboChucVu.setModel(new DefaultComboBoxModel(new String[] {"Nhân Viên", "Quản Lý", "Giám Đốc"}));
		pnlController.setLayout(new GridLayout(0, 1, 0, 5));
		
		btnNew = new JButton("Tạo mới");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setControllMode_insert();
				unLockForm();
				clearForm();
			}
		});
		btnNew.setHorizontalAlignment(SwingConstants.LEFT);
		btnNew.setIcon(new ImageIcon(AdminJFrame.class.getResource("/com/duan/icon/Create.png")));
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlController.add(btnNew);
		
		btnSave = new JButton(" Thêm");
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if (validateAll()) {
					insert();
				}
			}
		});
		btnSave.setHorizontalAlignment(SwingConstants.LEFT);
		btnSave.setIcon(new ImageIcon(AdminJFrame.class.getResource("/com/duan/icon/Accept.png")));
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlController.add(btnSave);
		
		btnUpdate = new JButton(" Cập nhật");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (validateAll()) {
					update();
				}
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		btnUpdate.setIcon(new ImageIcon(AdminJFrame.class.getResource("/com/duan/icon/Notes.png")));
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlController.add(btnUpdate);
		
		btnDelete = new JButton(" Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(contentPane, "Bạn thực sự muốn xóa tài khoản này?", 
						"Quản lý người dùng", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE)== JOptionPane.YES_NO_OPTION) 
				{
					delete();
					loadTable();
				}
				
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
		btnDelete.setIcon(new ImageIcon(AdminJFrame.class.getResource("/com/duan/icon/Delete.png")));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlController.add(btnDelete);
		
		tblUser = new JTableRed();
		tblUser.setRowHeight(30);
		tblUser.setModel(new DefaultTableModel(null, new String[] {"MÃ SỐ", "TÀI KHOẢN", "HỌ TÊN", "EMAIL", "SỐ ĐIỆN THOẠI", "CHỨC VỤ"}) 
		{
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tblUser.getColumnModel().getColumn(0).setResizable(false);
		tblUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				
			}
		});
		tblUser.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) 
			{
				setControllMode_edit();
				index = tblUser.getSelectedRow();
				if (index >= 0) {
					showDetail();
				}
			}
		});
		scrollPane.setViewportView(tblUser);
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
					.addContainerGap(386, Short.MAX_VALUE)
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
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTmKim, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFind, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
		);
		
		JPanel panel = new JPanel();
		
		lblAnh = new JLabel("Không có ảnh");
		lblAnh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		lblAnh.setBounds(0, 0, 181, 235);
		SwingHelper.setAutoResizeIcon(lblAnh);
		
		
		
		
		lblAnh.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblAnh.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnChonanh = new JButton("Chọn ảnh");
		btnChonanh.setEnabled(false);
		btnChonanh.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser chooser = new JFileChooser();
				int status = chooser.showOpenDialog(contentPane);
				
				if (status == JFileChooser.APPROVE_OPTION)
				{
					
					File file = chooser.getSelectedFile();
					boolean hopLe = DataHelper.checkFileExtension(file.getName(), DataHelper.EXTENSIONS_FILE_IMAGE);
					if (hopLe)
					{
						fileImageSelected = file;
						setAvatar(fileImageSelected);
					}
					else
					{
						//File anh khong hop le, vui long chon lai
					}
				}
			}
		});
		btnChonanh.setBounds(0, 246, 85, 37);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setEnabled(false);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				clearAvatar();
				fileImageSelected = null;
			}
		});
		btnXoa.setBounds(96, 246, 85, 37);
		
		JLabel lblGiiTnh = new JLabel("Giới tính");
		lblGiiTnh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		rdoNam = new JRadioButton("Nam");
		rdoNam.setBackground(SystemColor.menu);
		bgrSex.add(rdoNam);
		rdoNam.setSelected(true);
		
		rdoNu = new JRadioButton("Nữ");
		rdoNu.setBackground(SystemColor.menu);
		bgrSex.add(rdoNu);
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
		loadTable();
		lockForm();
		panel.setLayout(null);
		panel.add(lblAnh);
		panel.add(btnChonanh);
		panel.add(btnXoa);
		pnlForm.setLayout(gl_pnlForm);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void setAvatar(File file)
	{
		if (file != null)
		{
			ImageIcon icon = new ImageIcon(file.getAbsolutePath());
			lblAnh.setText("");
			lblAnh.setIcon(icon);
			SwingHelper.setAutoResizeIcon(lblAnh);
		}
	}

	public void setAvatar(String imgName)
	{
		URL urlImage = getClass().getResource("/com/duan/image/" + imgName);
		if (urlImage != null)
		{
			lblAnh.setText("");
			ImageIcon icon = new ImageIcon(urlImage);
			lblAnh.setIcon(icon);
			SwingHelper.setAutoResizeIcon(lblAnh);
		}
		else
		{
			clearAvatar();
		}
	}
	
	public void clearAvatar()
	{
		lblAnh.setIcon(null);
		lblAnh.setText("Không có ảnh");
	}
	
	
	
	public void lockForm()
	{
		txtEmail.setEnabled(false);
		txtFullname.setEnabled(false);
		txtPassword.setEnabled(false);
		txtPhoneNum.setEnabled(false);
		txtUsername.setEnabled(false);
		cboChucVu.setEnabled(false);
		rdoNam.setEnabled(false);
		rdoNu.setEnabled(false);
	}
	
	public void unLockForm()
	{
		txtEmail.setEnabled(true);
		txtFullname.setEnabled(true);
		txtPassword.setEnabled(true);
		txtPhoneNum.setEnabled(true);
		txtUsername.setEnabled(true);
		cboChucVu.setEnabled(true);
		rdoNam.setEnabled(true);
		rdoNu.setEnabled(true);
	}
	
	public void clearForm()
	{
		txtEmail.setText("");
		txtFullname.setText("");
		txtPassword.setText("");
		txtPhoneNum.setText("");
		txtUsername.setText("");
		rdoNam.setSelected(true);
		cboChucVu.setSelectedIndex(0);
	}
	
	public void setControllMode_Nothing()
	{
		btnNew.setEnabled(true);
		btnDelete.setEnabled(false);
		btnSave.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnChonanh.setEnabled(false);
		btnXoa.setEnabled(false);
	}
	
	public void setControllMode_edit() 
	{
		btnNew.setEnabled(true);
		btnDelete.setEnabled(true);
		btnSave.setEnabled(false);
		btnUpdate.setEnabled(true);
		btnChonanh.setEnabled(true);
		btnXoa.setEnabled(true);
	}
	
	public void setControllMode_insert()
	{
		btnNew.setEnabled(true);
		btnDelete.setEnabled(false);
		btnSave.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnChonanh.setEnabled(true);
		btnXoa.setEnabled(true);
	}
	AdminDAO admimDao = new AdminDAO();
	private void loadTable() 
	{
		DefaultTableModel model = (DefaultTableModel) tblUser.getModel();
		model.setRowCount(0);
		try {
			List<Admin> list = admimDao.getAll();
			for (Admin admin : list) {
				Object[] rowObjects = 
					{
							admin.getId(),
							admin.getUsername(),
							admin.getFullname(),
							admin.getEmail(),
							admin.getPhoneNumber(),
							getRoleTitle(admin.getRole())
					};
				model.addRow(rowObjects);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	Admin getModel()
	{
		Admin admin = new Admin();
		
		try 
		{
			if (index != -1)
			{
				int id = Integer.parseInt(tblUser.getValueAt(tblUser.getSelectedRow(), 0).toString());
				admin = AdminDAO.findByID(id);
			}
			
			admin.setUsername(txtUsername.getText());
			admin.setPassword(txtPassword.getText());
			admin.setFullname(txtFullname.getText());
			admin.setEmail(txtEmail.getText());
			admin.setPhoneNumber(txtPhoneNum.getText());
			
			if (fileImageSelected != null)
			{
				admin.setImage(fileImageSelected.getName());
			}
			
	
			admin.setSex(rdoNam.isSelected());

			
			admin.setRole(Math.abs(cboChucVu.getSelectedIndex() - 2));
			return admin;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	private void insert() 
	{
		Admin admin = getModel();
		try {
			boolean isSuccess = admimDao.insert(admin);
			if (isSuccess)
			{
				clearForm();
				loadTable();
				setControllMode_Nothing();
				lockForm();
				
				//Neu file anh da duoc chon thi bat dau ghi file vao source project
				if (fileImageSelected != null)
				{
					//Lay ra mang byte tu file da chon
					byte[] byteArrFileImage = DataHelper.getArrayByteFromFile(fileImageSelected);
					
					//Ghi file tu mang byte cua anh
					DataHelper.writeFileToSource(byteArrFileImage, "/com/duan/image/" + fileImageSelected.getName());
					fileImageSelected = null;
				}
				
				MessageOptionPane.showAlertDialog(contentPane, "Thêm tài khoản thành công!", MessageOptionPane.ICON_NAME_SUCCESS);
			}
		} 
		catch (Exception e) 
		{
			MessageOptionPane.showAlertDialog(contentPane, "Thêm tài khoản thất bại!", MessageOptionPane.ICON_NAME_ERROR);
			e.printStackTrace();
		}
	}
	
	public String getRoleTitle(int role) 
	{
		switch (role) 
		{
		case 0: return "Giám Đốc";
		case 1: return "Quản Lý";
		case 2: return "Nhân Viên";
		}
		return "Không xác định";
	}
	
	private void delete() {
		Admin admin = getModel();
		int id = Integer.parseInt(String.valueOf(tblUser.getValueAt(this.index, 0)));
		try {
			admimDao.delete(id);
			clearForm();
			loadTable();
			setControllMode_Nothing();
			lockForm();
			MessageOptionPane.showAlertDialog(contentPane, "Xóa tài khoản thành công!", MessageOptionPane.ICON_NAME_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			MessageOptionPane.showAlertDialog(contentPane, "Xóa tài khoản thất bại!", MessageOptionPane.ICON_NAME_ERROR);
		}
	}

	private void update() 
	{
		Admin admin = getModel();
		int id = Integer.parseInt(String.valueOf(tblUser.getValueAt(this.index, 0)));
		try 
		{
			if (lblAnh.getIcon() == null)
			{
				admin.setImage(null);
			}
			else
			{
				if (fileImageSelected != null)
				{
					admin.setImage(fileImageSelected.getName());
					
					//Lay ra mang byte tu file da chon
					byte[] byteArrFileImage = DataHelper.getArrayByteFromFile(fileImageSelected);
					
					//Ghi file tu mang byte cua anh
					DataHelper.writeFileToSource(byteArrFileImage, "/com/duan/image/" + fileImageSelected.getName());
					fileImageSelected = null;
				}
			}
			
			boolean isSuccess = admimDao.update(admin, id);
			if (isSuccess)
			{
				clearForm();
				loadTable();
				setControllMode_edit();
				
				MessageOptionPane.showAlertDialog(contentPane, "Cập nhật tài khoản thành công!", MessageOptionPane.ICON_NAME_SUCCESS);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			MessageOptionPane.showAlertDialog(contentPane, "Cập nhật tài khoản thất bại!", MessageOptionPane.ICON_NAME_ERROR);
		}
	}
	
	private void showDetail() 
	{
		unLockForm();
		indexSelectedLast = tblUser.getSelectedRow();
		try {
            int id = Integer.parseInt(String.valueOf(tblUser.getValueAt(this.index, 0)));
            Admin model = admimDao.findByID(id);            
            if (model != null) 
            {
                txtUsername.setText(model.getUsername());
                txtPassword.setText(model.getPassword()); 
                txtFullname.setText(model.getFullname());
                txtEmail.setText(model.getEmail());
                txtPhoneNum.setText(model.getPhoneNumber());
                
                if (model.isSex() == true) {
					rdoNam.setSelected(true);
				} else {
					rdoNu.setSelected(true);
				}
                
                if (model.getImage() != null && model.getImage().length() > 0)
                	setAvatar(model.getImage());
                else
                	clearAvatar();
                
                
                cboChucVu.setSelectedItem(getRoleTitle(model.getRole()));
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	private boolean validateAll() {
		if (txtUsername.getText().length() == 0) 
		{
			MessageOptionPane.showAlertDialog(contentPane, "Tài khoản không đươc để trống", MessageOptionPane.ICON_NAME_WARNING);
			txtUsername.requestFocus();
			return false;
		}
		else if (!txtUsername.getText().matches("^[0-9a-zA-Z\\p{L}]{1,}$")) {
			MessageOptionPane.showAlertDialog(contentPane, "Tài khoản chỉ được nhập chữ hoặc số", MessageOptionPane.ICON_NAME_WARNING);
			txtUsername.requestFocus();
			return false;
		}
		else if (txtPassword.getText().length() == 0)
		{
			MessageOptionPane.showAlertDialog(contentPane, "Mật khẩu không đươc để trống", MessageOptionPane.ICON_NAME_WARNING);
			txtPassword.requestFocus();
			return false;
		}
		else if (!txtPassword.getText().matches("^[0-9a-zA-Z\\p{L}]{1,}$")) {
			MessageOptionPane.showAlertDialog(contentPane, "Mật khẩu chỉ được nhập chữ hoặc số", MessageOptionPane.ICON_NAME_WARNING);
			txtPassword.requestFocus();
			return false;
		}
		else if (txtPassword.getText().length() < 6 || txtPassword.getText().length() > 14) 
		{
			MessageOptionPane.showAlertDialog(contentPane, "Mật khẩu từ 6 - 14 ký tự", MessageOptionPane.ICON_NAME_WARNING);
			txtPassword.requestFocus();
			return false;
		}
		else if (txtUsername.getText().length() == 0) 
		{
			MessageOptionPane.showAlertDialog(contentPane, "Họ tên không đươc để trống", MessageOptionPane.ICON_NAME_WARNING);
			txtFullname.requestFocus();
			return false;
		}
		else if (!txtFullname.getText().matches("^[a-zA-Z\\p{L} ]{1,}$")) {
			MessageOptionPane.showAlertDialog(contentPane, "Họ tên chỉ được nhập chữ", MessageOptionPane.ICON_NAME_WARNING);
			txtFullname.requestFocus();
			return false;
		}
		else if (txtEmail.getText().length() == 0) 
		{
			MessageOptionPane.showAlertDialog(contentPane, "Email không đươc để trống", MessageOptionPane.ICON_NAME_WARNING);
			txtEmail.requestFocus();
			return false;
		}
		else if (!txtEmail.getText().matches("\\w+@\\w+(\\.\\w+){1,2}")) 
		{
			MessageOptionPane.showAlertDialog(contentPane, "Email không đươc đúng định dạng", MessageOptionPane.ICON_NAME_WARNING);
			txtEmail.requestFocus();
			return false;
		}
		else if (txtPhoneNum.getText().length() == 0) 
		{
			MessageOptionPane.showAlertDialog(contentPane, "Số điện thoại không đươc để trống", MessageOptionPane.ICON_NAME_WARNING);
			txtPhoneNum.requestFocus();
			return false;
		}
		else if (!txtPhoneNum.getText().matches("[0-9]{10,}")) {
			MessageOptionPane.showAlertDialog(contentPane, "Số điện thoại 10 số ", MessageOptionPane.ICON_NAME_WARNING);
			txtPhoneNum.requestFocus();
			return false;
		}
		return true;
	}
}
