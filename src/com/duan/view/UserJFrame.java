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

import com.duan.custom.CustomJTableRed;
import com.duan.dao.UserDAO;
import com.duan.helper.DateHelper;
import com.duan.model.User;
import com.toedter.calendar.JDateChooser;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserJFrame extends JFrame {

	private JPanel contentPane;
	private JTable tblUser;
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("OK");
					UserJFrame frame = new UserJFrame();
					frame.setVisible(true);
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
		
		JLabel lblTmKim = new JLabel("Tìm kiếm");
		lblTmKim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtsearch = new JTextField();
		txtsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				fillToTableSearch();
			}
		});
		txtsearch.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(pnlForm, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnlController, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addGap(1))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblTmKim)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtsearch, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(pnlForm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtsearch, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTmKim, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(pnlController, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(36)))
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
		);
		
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
		
		txtBirthDay = new JDateChooser();
		txtBirthDay.setLocale(Locale.US);
		txtBirthDay.getCalendarButton().setIcon(new ImageIcon(UserJFrame.class.getResource("/com/toedter/calendar/demo/images/DemoTableColor16.gif")));
		txtBirthDay.getCalendarButton().setText("Chọn ");
		txtBirthDay.setDateFormatString("dd/MM/yyyy");
		//textField_4.setColumns(10);
		
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
		GroupLayout gl_pnlForm = new GroupLayout(pnlForm);
		gl_pnlForm.setHorizontalGroup(
			gl_pnlForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlForm.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_pnlForm.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addComponent(lblTiKhong, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtUsername, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE))
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addComponent(lblMtKhu, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE))
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addComponent(lblHTn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtFullname, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE))
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addComponent(lblNgySinh, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtBirthDay, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE))
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE))
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addComponent(lblSinThoi, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtPhoneNum, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE))
						.addGroup(gl_pnlForm.createSequentialGroup()
							.addComponent(lblGiiTnh, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(rdoNam, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(rdoNu, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)))
					.addGap(8))
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
						.addComponent(rdoNu, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
		);
		pnlForm.setLayout(gl_pnlForm);
		pnlController.setLayout(new GridLayout(0, 1, 0, 5));
		
		JButton btnTaoMoi = new JButton("Tạo mới");
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
			}
		});
		btnTaoMoi.setHorizontalAlignment(SwingConstants.LEFT);
		btnTaoMoi.setIcon(new ImageIcon(UserJFrame.class.getResource("/com/duan/icon/Create.png")));
		btnTaoMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlController.add(btnTaoMoi);
		
		JButton btnThem = new JButton(" Thêm");
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
		
		JButton btnCpNht = new JButton(" Cập nhật");
		btnCpNht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				
				if (checkForm()) 
				{
					update();
				}
			}
		});
		btnCpNht.setHorizontalAlignment(SwingConstants.LEFT);
		btnCpNht.setIcon(new ImageIcon(UserJFrame.class.getResource("/com/duan/icon/Notes.png")));
		btnCpNht.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlController.add(btnCpNht);
		
		JButton btnXa = new JButton(" Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int luachon = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
				if (luachon == JOptionPane.YES_OPTION) 
				{
					delete();
				}
				
			}
		});
		btnXa.setHorizontalAlignment(SwingConstants.LEFT);
		btnXa.setIcon(new ImageIcon(UserJFrame.class.getResource("/com/duan/icon/Delete.png")));
		btnXa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlController.add(btnXa);
		
		tblUser = new CustomJTableRed();
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
			}
		});
		tblUser.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(tblUser);
		contentPane.setLayout(gl_contentPane);
		loadListToUser();
	}
	
	public void loadListToUser()
	{
		try 
		{
			dao = new UserDAO();
			list = dao.getAll();
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
			Object[] rows = new Object[]{user.getId() , user.getUsername() , user.getPassword() , user.getFullname() , user.getDateOfBirth(), user.getEmail() , user.getPhoneNumber()};
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
		if (user.isSex() == true) 
		{
			rdoNam.setSelected(true);
		}else 
		{
			rdoNu.setSelected(true);
		}
	}

	
	public boolean checkForm()
	{
		boolean rong = false;
		String thongbao = " Đã có lỗi xảy ra : \n";
		if (txtUsername.getText().equals("")) 
		{
			rong = true;
			thongbao+="Tài khoản không được để trống\n";
		}
		if (txtPassword.getText().equals("")) 
		{
			rong = true;
			thongbao+="Mật khẩu không được để trống \n";		
		}
		if (txtFullname.getText().equals(""))
		{
			rong = true;
			thongbao+="Họ tên không được để trống\n";
		}
		if(txtBirthDay.getDate() == null)
		{
			rong = true;
			thongbao+="Ngày sinh sai định dạng hoặc không được để trống\n";
		}
		if (txtEmail.getText().equals(""))
		{
			rong = true;
			thongbao +="Email không được để trống!\n";
		}
		else
        {
            if (!txtEmail.getText().matches("\\w+@\\w+(\\.\\w+){1,2}")) 
            {
                rong = true;
                thongbao+="Email không đúng định dạng\n";
            }
        }
		if (txtPhoneNum.getText().equals(""))
		{
			rong = true;
			thongbao +="Số điện thoại không được để trống!\n";
		}
		else
        {
            if (!txtPhoneNum.getText().matches("\\d{10,14}")) 
            {
                thongbao+="Số ĐT không đúng dịnh dạng\n";
                rong = true;
            }
        }
		
		if (rong == true) 
		{
			JOptionPane.showMessageDialog(this, thongbao);
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
		boolean gioitinh = false;
		if (gioitinh == true) 
		{
			rdoNam.isSelected();
		}
		else 
		{
			rdoNu.isSelected();
		}
		user = new User(0, taikhoan, matkhau, hoten,ngaysinh, email, sodt, gioitinh, new Date());
		try 
		{
			if (dao.insert(user)) 
			{
				list.add(user);
				JOptionPane.showMessageDialog(this, "Thêm thành công User có mã : " + user.getId());
				fillToTable();
				
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update()
	{
		dao = new UserDAO();
		String taikhoan = txtUsername.getText();
		String matkhau = txtPassword.getText();
		String hoten = txtFullname.getText();
		Date ngaysinh = txtBirthDay.getDate();
		String email = txtEmail.getText();
		String sodt = txtPhoneNum.getText();
		boolean gioitinh = false;
		if (gioitinh == true) 
		{
			rdoNam.isSelected();
		}
		else 
		{
			rdoNu.isSelected();
		}
		user = new User(0, taikhoan, matkhau, hoten,ngaysinh, email, sodt, gioitinh, new Date());
		try 
		{
			if (dao.update(user, user.getId())) 
			{
				list.set(index, user);
				JOptionPane.showMessageDialog(this, "Cập nhật thành công USER có mã : "+ user.getId());
				fillToTable();
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void delete()
	{
		dao = new UserDAO();
		
		try 
		{
			if (dao.delete(list.get(index).getId())) 
			{
				JOptionPane.showMessageDialog(this, "Xóa thành công USER có mã : "+list.get(index).getId());
				list.remove(index);
				fillToTable();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void fillToTableSearch()
    {
        
        model = (DefaultTableModel) tblUser.getModel();
        model.setRowCount(0);
        
        for (User user : list) 
        {
            boolean isFound = Pattern.compile("^(?i)[([\\w\\s][\\p{L}\\s])]*" + txtsearch.getText() + "[([\\w\\s][\\p{L}\\s])]*$", 
					Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(user.getFullname()).matches();
            
            if (isFound) 
            {
                Object []rows = new Object[]{user.getId(),user.getUsername(),user.getPassword(),user.getFullname(),user.getDateOfBirth(),user.getEmail(),user.getPhoneNumber()};
                
                model.addRow(rows);
            }

        }
        
    }

}
