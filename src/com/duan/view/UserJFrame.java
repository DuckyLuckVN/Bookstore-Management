package com.duan.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.collections4.functors.TruePredicate;

import com.duan.custom.common.JDateChooserCustom;
import com.duan.custom.common.JTableRed;
import com.duan.custom.common.JTextFieldDark;
import com.duan.custom.message.MessageOptionPane;
import com.duan.dao.UserDAO;
import com.duan.helper.DataHelper;
import com.duan.helper.DateHelper;
import com.duan.helper.SettingSave;
import com.duan.model.User;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class UserJFrame extends JFrame {

	private JPanel contentPane;
	private JTableRed tblUser;
	private JTextField txtsearch;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtFullname;
	private JDateChooser txtBirthDay;
	private JTextField txtEmail;
	private JTextField txtPhoneNum;
	private JRadioButton rdoNam;
	private JRadioButton rdoNu;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	UserDAO dao;
	ArrayList<User> list = new ArrayList<>();
	DefaultTableModel model = null ;
	int index = -1;
	User user;
	JButton btnCpNht;
	JButton btnThem;
	JButton btnXa;
	private JLabel lblTrngThi;
	private JComboBox cboActive;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("OK");
					UserJFrame frame = new UserJFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public UserJFrame() 
	{
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserJFrame.class.getResource("/com/duan/icon/icons8_user_groups_64px.png")));
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
		
		JPanel pnlForm = new JPanel();
		pnlForm.setBorder(new TitledBorder(null, "Th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel pnlController = new JPanel();
		pnlController.setBorder(new TitledBorder(null, "\u0110i\u1EC1u khi\u1EC3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblTmKim = new JLabel("Tìm kiếm:");
		lblTmKim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtsearch = new JTextFieldDark();
		txtsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				loadListToUser();
				fillToTable();
			}
		});
		txtsearch.setColumns(10);
		
		JLabel lblTiKhong = new JLabel("Tài khoản");
		lblTiKhong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPassword.setColumns(10);
		
		JLabel lblMtKhu = new JLabel("Mật khẩu");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtFullname = new JTextField();
		txtFullname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFullname.setColumns(10);
		
		JLabel lblHTn = new JLabel("Họ tên");
		lblHTn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtBirthDay = new JDateChooserCustom();
		
		JLabel lblNgySinh = new JLabel("Ngày sinh");
		lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
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
		
		JLabel lblGiiTnh = new JLabel("Giới tính");
		lblGiiTnh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		rdoNam = new JRadioButton("Nam");
		buttonGroup.add(rdoNam);
		
		rdoNu = new JRadioButton("Nữ");
		buttonGroup.add(rdoNu);
		pnlController.setLayout(new GridLayout(0, 1, 0, 5));
		
		JButton btnTaoMoi = new JButton("Tạo mới");
		
		btnTaoMoi.setHorizontalAlignment(SwingConstants.LEFT);
		btnTaoMoi.setIcon(new ImageIcon(UserJFrame.class.getResource("/com/duan/icon/Create.png")));
		btnTaoMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlController.add(btnTaoMoi);
		
		btnThem = new JButton(" Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if (checkForm()) 
				{
					insert();
				}
			}
		});
		btnThem.setHorizontalAlignment(SwingConstants.LEFT);
		btnThem.setIcon(new ImageIcon(UserJFrame.class.getResource("/com/duan/icon/Accept.png")));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlController.add(btnThem);
		
		btnCpNht = new JButton(" Cập nhật");
		btnCpNht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				
				if (checkForm()) 
				{
					if (index < 0) 
					{
						MessageOptionPane.showAlertDialog(null, "Chưa chọn user để cập nhật", MessageOptionPane.ICON_NAME_WARNING);
						return;
					}
					update();
					btnCpNht.setEnabled(false);
					btnXa.setEnabled(false);
					btnThem.setEnabled(false);
					
				}
			}
		});
		btnCpNht.setHorizontalAlignment(SwingConstants.LEFT);
		btnCpNht.setIcon(new ImageIcon(UserJFrame.class.getResource("/com/duan/icon/Notes.png")));
		btnCpNht.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlController.add(btnCpNht);
		
		btnXa = new JButton(" Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				boolean isConfirm = MessageOptionPane.showConfirmDialog(contentPane, "Bạn có chắc muốn xóa?", MessageOptionPane.ICON_NAME_QUESTION);
				if (isConfirm) 
				{
					try {
						delete();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnXa.setHorizontalAlignment(SwingConstants.LEFT);
		btnXa.setIcon(new ImageIcon(UserJFrame.class.getResource("/com/duan/icon/Delete.png")));
		btnXa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlController.add(btnXa);
		
		tblUser = new JTableRed();
		tblUser.setRowHeight(30);
		tblUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		tblUser.setModel(new DefaultTableModel(null, new String[] {"MÃ SỐ", "TÀI KHOẢNG", "MẬT KHẨU", "HỌ TÊN", "NGÀY SINH", "EMAIL", "SỐ ĐIỆN THOẠI"}) 
		{
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tblUser.getSelectionModel().addListSelectionListener(new ListSelectionListener() 
		{
			@Override
			public void valueChanged(ListSelectionEvent arg0) 
			{
				showdetail();
				btnTaoMoi.setEnabled(true);
				btnCpNht.setEnabled(true);
				btnThem.setEnabled(true);
				btnXa.setEnabled(true);
			}
		});
		tblUser.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(tblUser);
		loadListToUser();
		btnCpNht.setEnabled(false);
		btnThem.setEnabled(false);
		btnXa.setEnabled(false);
		
		lblTrngThi = new JLabel("Trạng thái:");
		lblTrngThi.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		cboActive = new JComboBox();
		cboActive.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboActive.setModel(new DefaultComboBoxModel(new String[] {"Kích hoạt", "Khóa tài khoản"}));
		GroupLayout gl_pnlForm = new GroupLayout(pnlForm);
		gl_pnlForm.setHorizontalGroup(
			gl_pnlForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlForm.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_pnlForm.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addComponent(lblTiKhong, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtUsername, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addComponent(lblMtKhu, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addComponent(lblHTn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtFullname, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addComponent(lblNgySinh, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtBirthDay, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addComponent(lblSinThoi, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtPhoneNum, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
							.addGap(4))
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addComponent(lblGiiTnh, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(rdoNam, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(rdoNu, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
							.addComponent(lblTrngThi, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(cboActive, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
					.addGap(4))
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
					.addGap(11)
					.addGroup(gl_pnlForm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNgySinh, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtBirthDay, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_pnlForm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_pnlForm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSinThoi, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPhoneNum, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_pnlForm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGiiTnh, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdoNam, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdoNu, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTrngThi, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addGap(2)
							.addComponent(cboActive, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))))
		);
		pnlForm.setLayout(gl_pnlForm);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(pnlForm, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
					.addGap(6)
					.addComponent(pnlController, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblTmKim)
					.addGap(10)
					.addComponent(txtsearch, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlForm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlController, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTmKim, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtsearch, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		btnTaoMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//goi ham can xy ly
				txtUsername.setText("");
				txtPassword.setText("");
				txtFullname.setText("");
				txtBirthDay.setDate(null);
				txtEmail.setText("");
				txtPhoneNum.setText("");
				rdoNam.isSelected();
				btnTaoMoi.setEnabled(false);
				btnCpNht.setEnabled(false);
				btnThem.setEnabled(true);
				btnXa.setEnabled(false);
			}
		});
		
	}
	
	public void loadListToUser()
	{
		try 
		{
			dao = new UserDAO();
			list = dao.search(txtsearch.getText());
			if (list.size() > 0) 
			{
				fillToTable();
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void fillToTable()
	{
		model = (DefaultTableModel) tblUser.getModel();
		model.setRowCount(0);
		for (User user : list) 
		{
			String dateOfBirth = DateHelper.dateToString(user.getDateOfBirth(), SettingSave.getSetting().getDateFormat());
			Object[] rows = new Object[]{user.getId() , user.getUsername() , user.getPassword() , user.getFullname() , dateOfBirth, user.getEmail() , user.getPhoneNumber()};
			model.addRow(rows);
		}
	}
	
	public void showdetail()
	{
		index = tblUser.getSelectedRow();
		if (index < 0) 
		{
			return;
		}
		User user = list.get(index);
		txtUsername.setText(user.getUsername());
		txtPassword.setText(user.getPassword());
		txtFullname.setText(user.getFullname());
		txtBirthDay.setDate(user.getDateOfBirth());
		txtEmail.setText(user.getEmail());
		txtPhoneNum.setText(user.getPhoneNumber());
		boolean gt = user.isSex();
		if (gt == true) 
		{
			rdoNam.setSelected(true);
		}else 
		{
			rdoNu.setSelected(true);
		}
		
		cboActive.setSelectedIndex((user.isActive()) ? 0 : 1);
		
	}

	
	public boolean checkForm()
	{
		boolean rong = false;
		String thongbao = "Đã có lỗi xảy ra : \n";
		if (txtUsername.getText().equals("")) 
		{
			rong = true;
			thongbao+="+ Tài khoản không được để trống\n";
		}
		if (txtPassword.getText().equals("")) 
		{
			rong = true;
			thongbao+="+ Mật khẩu không được để trống \n";		
		}
		if (txtFullname.getText().equals(""))
		{
			rong = true;
			thongbao+="+ Họ tên không được để trống\n";
		}
		if(txtBirthDay.getDate() == null)
		{
			rong = true;
			thongbao+="+ Ngày sinh sai định dạng hoặc không được để trống\n";
		}
		if (txtEmail.getText().equals(""))
		{
			rong = true;
			thongbao +="+ Email không được để trống!\n";
		}
		else
        {
            if (!txtEmail.getText().matches("\\w+@\\w+(\\.\\w+){1,2}")) 
            {
                rong = true;
                thongbao+="+ Email không đúng định dạng\n";
            }
        }
		if (txtPhoneNum.getText().equals(""))
		{
			rong = true;
			thongbao +="+ Số điện thoại không được để trống!\n";
		}
		else
        {
            if (!txtPhoneNum.getText().matches("\\d{10,14}")) 
            {
                thongbao+="+ Số ĐT không đúng dịnh dạng\n";
                rong = true;
            }
        }
		
		if (rong == true) 
		{
			MessageOptionPane.showMessageDialog(this, thongbao, MessageOptionPane.ICON_NAME_WARNING);
			return false;
		}
		return true;
	}
	
	public void insert()
	{
		dao = new UserDAO();
		
		String taikhoan = txtUsername.getText();
		String matkhau = txtPassword.getText();
		String hoten = txtFullname.getText();
		Date ngaysinh = txtBirthDay.getDate();
		String email = txtEmail.getText();
		String sodt = txtPhoneNum.getText();
		boolean gioitinh = true;
		boolean isActive = (cboActive.getSelectedIndex()==0) ? true : false;
		if (gioitinh == true) 
		{
			rdoNam.setSelected(true);
		}
		else 
		{
			rdoNu.setSelected(true);
		}
		user = new User(0,taikhoan, matkhau, hoten,ngaysinh, email, sodt, gioitinh, isActive, new Date());
		try 
		{
			if (dao.insert(user)) 
			{
				list.add(user);
				MessageOptionPane.showAlertDialog(this, "Thêm thành công User có tên : " + user.getFullname(), MessageOptionPane.ICON_NAME_SUCCESS);
				loadListToUser();
				
			}
		} 
		catch (SQLException e) {
			if (e.getErrorCode() == 2627) 
			{
				MessageOptionPane.showMessageDialog(this, "ID này đã tồn tại!\n" + " Bạn cần Nhấn 'THÊM MỚI' để thêm USER mới", MessageOptionPane.ICON_NAME_WARNING);
			}
			// TODO Auto-generated catch block
		}
	}
	
	public void update()
	{
//		index = tblUser.getSelectedRow();
//		if (index < 0) 
//		{
//			return;
//		}
//		user = list.get(index);
		dao = new UserDAO();
		String taikhoan = txtUsername.getText();
		String matkhau = txtPassword.getText();
		String hoten = txtFullname.getText();
		Date ngaysinh = txtBirthDay.getDate();
		String email = txtEmail.getText();
		String sodt = txtPhoneNum.getText();
		boolean gioitinh = false;
		boolean isActive = (cboActive.getSelectedIndex()==0) ? true : false;
		
		if (rdoNam.isSelected()) 
		{
			gioitinh = true;
		}
		if (rdoNu.isSelected()) 
		{
			gioitinh = false;
		}
		
		user = new User(list.get(index).getId(), taikhoan, matkhau, hoten,ngaysinh, email, sodt, gioitinh, isActive,list.get(index).getCreatedDate() );
		try 
		{
			if (dao.update(user, list.get(index).getId())) 
			{
				list.set(index, user);
				MessageOptionPane.showAlertDialog(this, "Cập nhật thành công USER có mã : "+ list.get(index).getId(), MessageOptionPane.ICON_NAME_SUCCESS);
				fillToTable();
				txtUsername.setText("");
				txtPassword.setText("");
				txtBirthDay.setDate(null);
				txtEmail.setText("");
				txtFullname.setText("");
				txtPhoneNum.setText("");
				
			}
		} catch (SQLException e) 
		{
			
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void delete() throws SQLException
	{
		dao = new UserDAO();
		list = dao.search(txtsearch.getText());
		try 
		{
			if (dao.delete(list.get(index).getId())) 
			{
				MessageOptionPane.showAlertDialog(this, "Xóa thành công USER có mã : " + list.get(index).getId(), MessageOptionPane.ICON_NAME_SUCCESS);
				list.remove(index);
				fillToTable();
			}
		} 
		catch (SQLException e) 
		{
			switch (e.getErrorCode()) 
			{
			case 547:
				MessageOptionPane.showAlertDialog(contentPane, "Tài khoảng này không thể xóa, hãy khóa tài khoản này lại nếu cần!", MessageOptionPane.ICON_NAME_ERROR);
				break;

			default:
				e.printStackTrace();
				MessageOptionPane.showAlertDialog(contentPane, "Xóa tài khoản thất bại! [ERROR CODE: " + e.getErrorCode() + "]", MessageOptionPane.ICON_NAME_ERROR);
				break;
			}
		}
	}
}
