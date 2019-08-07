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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminJFrame extends JFrame {

	private JPanel contentPane;
	private JTable tblUser;
	private JTextField txtFind;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtFullname;
	private JTextField txtEmail;
	private JTextField txtPhoneNum;
	private JLabel lblAnh ;
	private JComboBox cboChucVu;
	private final ButtonGroup bgrSex = new ButtonGroup();
	private File fileAnhDaChon = null;
	private JButton btnNew;
	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JRadioButton rdoNam;
	private JRadioButton rdoNu;

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
	int row = 0;

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
		
		JLabel lblTiKhong = new JLabel("Tài khoản");
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
		
		btnNew = new JButton("Tạo mới");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setControllMode_insert();
				unLockForm();
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
				insert();
			}
		});
		btnSave.setHorizontalAlignment(SwingConstants.LEFT);
		btnSave.setIcon(new ImageIcon(AdminJFrame.class.getResource("/com/duan/icon/Accept.png")));
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlController.add(btnSave);
		
		btnUpdate = new JButton(" Cập nhật");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();	
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
				delete();
				loadTable();
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
		btnDelete.setIcon(new ImageIcon(AdminJFrame.class.getResource("/com/duan/icon/Delete.png")));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlController.add(btnDelete);
		
		tblUser = new JTable();
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
				setControllMode_edit();
				if (arg0.getClickCount() == 2) {
		            row = tblUser.getSelectedRow();
		            if (row >= 0) {
		                edit();
		            }
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
		
		rdoNam = new JRadioButton("Nam");
		bgrSex.add(rdoNam);
		rdoNam.setSelected(true);
		
		rdoNu = new JRadioButton("Nữ");
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
		panel.add(btnChnnh);
		panel.add(btnXa_1);
		pnlForm.setLayout(gl_pnlForm);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void test()
	{
		//lblAnh
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
	}
	
	public void setControllMode_edit() 
	{
		btnNew.setEnabled(true);
		btnDelete.setEnabled(true);
		btnSave.setEnabled(false);
		btnUpdate.setEnabled(true);
	}
	
	public void setControllMode_insert()
	{
		btnNew.setEnabled(false);
		btnDelete.setEnabled(false);
		btnSave.setEnabled(true);
		btnUpdate.setEnabled(false);
	}
	AdminDAO admimDao = new AdminDAO();
	private void loadTable() {
		DefaultTableModel model = (DefaultTableModel) tblUser.getModel();
		model.setRowCount(0);
		try {
			List<Admin> list = admimDao.getAllAdmin();
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
		admin.setUsername(txtUsername.getText());
		admin.setPassword(txtPassword.getText());
		admin.setFullname(txtFullname.getText());
		admin.setEmail(txtEmail.getText());
		admin.setPhoneNumber(txtPhoneNum.getText());
		boolean sex;
		if (rdoNam.isSelected()) 
		{
			sex = true;
			admin.setSex(sex);
		} else if (rdoNu.isSelected()) {
			sex = false;
			admin.setSex(sex);
		}
		admin.setRole(cboChucVu.getSelectedIndex());
		return admin;
	}
	
	private void insert() 
	{
		Admin admin = getModel();
		try {
			admimDao.insert(admin);
			clearForm();
			loadTable();
			setControllMode_Nothing();
			lockForm();
			JOptionPane.showMessageDialog(contentPane, "Thêm tài khoản thành công!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "Thêm tài khoản thất bại!");
			e.printStackTrace();
		}
	}
	
	public String getRoleTitle(int role) 
	{
		switch (role) 
		{
		case 0: return "Nhân viên";
		case 1: return "Quản lý";
		case 2: return "Giám đốc";
		}
		return "Không xác định";
	}
	
	private void delete() {
		Admin admin = getModel();
		int id = Integer.parseInt(String.valueOf(tblUser.getValueAt(this.row, 0)));
		try {
			admimDao.delete(id);
			clearForm();
			loadTable();
			setControllMode_Nothing();
			lockForm();
			JOptionPane.showMessageDialog(contentPane, "Xóa tài khoản thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(contentPane, "Xóa tài khoản thất bại!");
		}
	}

	private void update() {
		Admin admin = getModel();
		int id = Integer.parseInt(String.valueOf(tblUser.getValueAt(this.row, 0)));
		try {
			admimDao.update(admin, id);
			clearForm();
			loadTable();
			setControllMode_Nothing();
			JOptionPane.showMessageDialog(contentPane, "Cập nhật tài khoản thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(contentPane, "Cập nhật tài khoản thất bại!");
		}
	}
	
	private void edit() 
	{
		try {
            int id = Integer.parseInt(String.valueOf(tblUser.getValueAt(this.row, 0)));
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
                cboChucVu.setSelectedItem(getRoleTitle(model.getRole()));
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	
}
