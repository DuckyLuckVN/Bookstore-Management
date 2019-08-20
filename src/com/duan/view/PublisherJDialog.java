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

import com.duan.custom.common.JTableBlue;
import com.duan.custom.message.MessageOptionPane;
import com.duan.dao.AdminDAO;
<<<<<<< HEAD
import com.duan.dao.CategoryDAO;
import com.duan.dao.LocationDAO;
import com.duan.dao.PublisherDAO;
import com.duan.model.Admin;
import com.duan.model.Category;
=======
import com.duan.dao.LocationDAO;
import com.duan.dao.PublisherDAO;
import com.duan.helper.DataHelper;
import com.duan.model.Admin;
>>>>>>> master
import com.duan.model.Location;
import com.duan.model.Publisher;

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
import java.util.List;
<<<<<<< HEAD
import java.util.Random;
=======
>>>>>>> master
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PublisherJDialog extends JDialog {
	String head[] = {"MÃ SỐ","TÊN NXB","SỐ ĐT","EMAIL","ĐỊA CHỈ","NGÀY TẠO"};
    DefaultTableModel tblModel = new DefaultTableModel(head, 0);
	private JPanel contentPane;
<<<<<<< HEAD
	private JTableBlue tblLocation;
	private JTextField txtTenNXB;
	private JTextField txtSDT;
=======
	private JTableBlue tblPublisher;
	private JTextField txtTenNXB;
	private JTextField txtSoDienThoai;
>>>>>>> master
	private JTextField txtEmail;
	
	PublisherDAO dao;
	ArrayList<Location> list = new ArrayList<>();
	int index = -1;
	private JTextArea txtDiaChi;
<<<<<<< HEAD
	JButton btnThm;
	JButton btnCpNht;
	JButton btnXa;
	JButton btnMi;
=======
	JButton btnThem;
	JButton btnCapNhat;
	JButton btnXoa;
	JButton btnMoi;
	private int indexSelectedLast = -1;
	private JTextArea textMoTa;
>>>>>>> master
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					PublisherJDialog frame = new PublisherJDialog();
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public PublisherJDialog() 
	{		
		setModal(true);
		setResizable(false);
		setTitle("Quản lý nhà xuất bản");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CategoryJDialog.class.getResource("/com/duan/icon/icons8_medium_priority_50px.png")));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 536, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 290, 508, 207);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 8, 381, 276);
		panel.setLayout(null);
		
		tblPublisher = new JTableBlue();
		tblPublisher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				setControllMode_edit();
				index = tblPublisher.getSelectedRow();
				if (index >= 0) {
					showDetail();
				}
			}
		});
		tblPublisher.setRowHeight(30);
		tblPublisher.setModel(new DefaultTableModel(null, new String[] {"MÃ SỐ", "TÊN NXB", "SỐ ĐT", "EMAIL", "ĐỊA CHỈ", "NGÀY TẠO"})
		{
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tblPublisher.getColumnModel().getColumn(1).setResizable(false);
		contentPane.setLayout(null);
		scrollPane.setViewportView(tblPublisher);
		contentPane.add(scrollPane);
		contentPane.add(panel);
		
		JLabel lblTenNXB = new JLabel("Tên NXB:");
		lblTenNXB.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTenNXB.setBounds(10, 16, 87, 25);
		panel.add(lblTenNXB);
		
		txtTenNXB = new JTextField();
<<<<<<< HEAD
=======
		txtTenNXB.setEnabled(false);
>>>>>>> master
		txtTenNXB.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTenNXB.setBounds(107, 16, 264, 25);
		panel.add(txtTenNXB);
		txtTenNXB.setColumns(10);
		
<<<<<<< HEAD
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSDT.setColumns(10);
		txtSDT.setBounds(107, 52, 264, 25);
		panel.add(txtSDT);
=======
		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setEnabled(false);
		txtSoDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBounds(107, 52, 264, 25);
		panel.add(txtSoDienThoai);
>>>>>>> master
		
		JLabel lblSoDienThoai = new JLabel("Số điện thoại:");
		lblSoDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSoDienThoai.setBounds(10, 52, 87, 25);
		panel.add(lblSoDienThoai);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDiaChi.setBounds(10, 124, 87, 25);
		panel.add(lblDiaChi);
		
		txtDiaChi = new JTextArea();
<<<<<<< HEAD
=======
		txtDiaChi.setEnabled(false);
>>>>>>> master
		txtDiaChi.setBorder(new LineBorder(SystemColor.controlShadow));
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDiaChi.setBounds(107, 124, 264, 62);
		panel.add(txtDiaChi);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(10, 88, 87, 25);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
<<<<<<< HEAD
=======
		txtEmail.setEnabled(false);
>>>>>>> master
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtEmail.setColumns(10);
		txtEmail.setBounds(107, 88, 264, 25);
		panel.add(txtEmail);
<<<<<<< HEAD
=======
		
		textMoTa = new JTextArea();
		textMoTa.setEnabled(false);
		textMoTa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textMoTa.setBorder(new LineBorder(SystemColor.controlShadow));
		textMoTa.setBounds(107, 197, 264, 62);
		panel.add(textMoTa);
>>>>>>> master
		
		JLabel lblMoTa = new JLabel("Mô tả:");
		lblMoTa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMoTa.setBounds(10, 197, 87, 25);
		panel.add(lblMoTa);
		
		txtMoTa = new JTextArea();
		txtMoTa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMoTa.setBorder(new LineBorder(SystemColor.controlShadow));
		txtMoTa.setBounds(107, 197, 264, 62);
		panel.add(txtMoTa);
		
		JPanel pnlControll = new JPanel();
		pnlControll.setBorder(new TitledBorder(null, "\u0110i\u1EC1u khi\u1EC3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlControll.setBounds(401, 7, 117, 275);
		contentPane.add(pnlControll);
		pnlControll.setLayout(new GridLayout(0, 1, 0, 10));
		
<<<<<<< HEAD
		btnThm = new JButton(" Lưu");
		btnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insert();
			}
		});
=======
		btnThem = new JButton(" Lưu");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateAll()) {
					insert();
				}

			}
		});
		btnThem.setEnabled(false);
>>>>>>> master
		
		btnMoi = new JButton(" Mới");
		btnMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				clearForm();
				setControllMode_insert();
				unLockForm();
				tblPublisher.setRequestFocusEnabled(false);
			}
		});
		pnlControll.add(btnMoi);
		btnMoi.setHorizontalAlignment(SwingConstants.LEFT);
		btnMoi.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/Create.png")));
		pnlControll.add(btnThem);
		btnThem.setHorizontalAlignment(SwingConstants.LEFT);
		btnThem.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/Accept.png")));
		
<<<<<<< HEAD
		btnCpNht = new JButton("Cập nhật");
		btnCpNht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkForm()) 
				{
					update();
					clearForm();
					lockForm();
					setControllMode_Nothing();
				}
			}
		});
		pnlControll.add(btnCpNht);
		btnCpNht.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/Notes.png")));
		btnCpNht.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					int luachon = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa ? ","Thông báo",JOptionPane.YES_NO_OPTION);
					if (luachon == JOptionPane.YES_OPTION) 
					{
						delete();
						loadTable();
					}
				}
		});
		pnlControll.add(btnXa);
		btnXa.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/icons8_delete_32px_1.png")));
		btnXa.setHorizontalAlignment(SwingConstants.LEFT);
		setLocationRelativeTo(getOwner());
	}
	
PublisherDAO publisherDao = new PublisherDAO();
private JTextArea txtMoTa;
	//ĐỔ DỮ LIỆU VÀO PUBLISHER VÀO JTABLE
	private void loadTable() {
		DefaultTableModel model = (DefaultTableModel) tblLocation.getModel();
		model.setRowCount(0);
		try {
			List<Publisher> list = publisherDao.getAll();
			for (Publisher publisher : list) {
				Object[] rowObjects = 
					{
						publisher.getId(),
						publisher.getName(),
						publisher.getPhoneNumber(),
						publisher.getEmail(),
						publisher.getAddress(),
					    publisher.getIntroduct(),
					    publisher.getCreatedDate()
					};
				model.addRow(rowObjects);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	Publisher getModel() {
		Publisher publisher = new Publisher();
		try {
			if(index != -1) {
				int id = Integer.parseInt(tblLocation.getValueAt(tblLocation.getSelectedRow(), 0).toString());
				publisher = PublisherDAO.findByID(id);
			}
			publisher.setName(txtTenNXB.getText());
			publisher.setPhoneNumber(txtSDT.getText());
			publisher.setEmail(txtEmail.getText());
			publisher.setAddress(txtDiaChi.getText());
			publisher.setAddress(txtMoTa.getText());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void delete() {
	     Publisher pub = getModel();
		int id = Integer.parseInt(String.valueOf(tblLocation.getValueAt(this.index, 0)));
		try {
			PublisherDAO.delete(pub ,id);
			clearForm();
			loadTable();
			setControllMode_Nothing();
			lockForm();
			MessageOptionPane.showAlertDialog(contentPane, "Xóa dữ liệu thành công!", MessageOptionPane.ICON_NAME_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			MessageOptionPane.showAlertDialog(contentPane, "Xóa dữ liệu thất bại!", MessageOptionPane.ICON_NAME_ERROR);
		}
	}

	private void update() {
		Publisher pub = getModel();
		int id = Integer.parseInt(String.valueOf(tblLocation.getValueAt(this.index, 0)));
        try {
			boolean isSuccess = PublisherDAO.update(pub, id);
           if(isSuccess) {
        	    clearForm();
				loadTable();
				setControllMode_Edit();
				
				MessageOptionPane.showAlertDialog(contentPane, "Cập nhật dữ liệu thành công!", MessageOptionPane.ICON_NAME_SUCCESS);

           }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			MessageOptionPane.showAlertDialog(contentPane, "Cập nhật dữ liệu thất bại!", MessageOptionPane.ICON_NAME_ERROR);
		}
		
			}

	public void insert() {
	     Publisher pub = getModel();
		try {
			boolean isSuccess = PublisherDAO.insert(pub);
			if(isSuccess) {
				clearForm();
				loadTable();
				setControllMode_Nothing();
				lockForm();
				MessageOptionPane.showAlertDialog(contentPane, "Thêm dữ liệu thành công!", MessageOptionPane.ICON_NAME_SUCCESS);
			}
		} catch (Exception e) {
			MessageOptionPane.showAlertDialog(contentPane, "Thêm dữ liệu thất bại!", MessageOptionPane.ICON_NAME_ERROR);
			e.printStackTrace();
		}
	}
	public void lockForm()
	{
		txtTenNXB.setEditable(false);
		txtSDT.setEditable(false);
		txtEmail.setEditable(false);
		txtDiaChi.setEditable(false);
=======
		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (validateAll()) {
					update();
				}
			}
		});
		btnCapNhat.setEnabled(false);
		pnlControll.add(btnCapNhat);
		btnCapNhat.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/Notes.png")));
		btnCapNhat.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (JOptionPane.showConfirmDialog(contentPane, "Bạn thực sự muốn xóa nhà xuất bản này?", 
						"Quản lý nhà xuất bản", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE)== JOptionPane.YES_NO_OPTION) 
				{
					delete();
					loadTable();
				}
			}
		});
		btnXoa.setEnabled(false);
		pnlControll.add(btnXoa);
		btnXoa.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/icons8_delete_32px_1.png")));
		btnXoa.setHorizontalAlignment(SwingConstants.LEFT);
		setLocationRelativeTo(getOwner());
		
		loadTable();
	}
	public void lockForm()
	{
		txtTenNXB.setEnabled(false);
		txtSoDienThoai.setEnabled(false);
		txtEmail.setEnabled(false);
		txtDiaChi.setEnabled(false);
		textMoTa.setEnabled(false);
	}
	
	public void unLockForm()
	{
		txtTenNXB.setEnabled(true);
		txtSoDienThoai.setEnabled(true);
		txtEmail.setEnabled(true);
		txtDiaChi.setEnabled(true);
		textMoTa.setEnabled(true);
	}
	
	public void clearForm()
	{
		txtTenNXB.setText("");
		txtSoDienThoai.setText("");
		txtEmail.setText("");
		txtDiaChi.setText("");
		textMoTa.setText("");
	}
	
	public void setControllMode_Nothing()
	{
		btnMoi.setEnabled(true);
		btnCapNhat.setEnabled(false);
		btnThem.setEnabled(false);
		btnXoa.setEnabled(false);
>>>>>>> master
	}
	
	public void setControllMode_edit() 
	{
<<<<<<< HEAD
		txtTenNXB.setEditable(true);
		txtSDT.setEditable(true);
		txtEmail.setEditable(true);
		txtDiaChi.setEditable(true);
=======
		btnMoi.setEnabled(true);
		btnXoa.setEnabled(true);
		btnThem.setEnabled(false);
		btnCapNhat.setEnabled(true);
>>>>>>> master
	}
	
	public void setControllMode_insert()
	{
		btnMoi.setEnabled(true);
		btnXoa.setEnabled(false);
		btnThem.setEnabled(true);
		btnCapNhat.setEnabled(false);
	}
	
	PublisherDAO publisherDAO = new PublisherDAO();
	
	private void loadTable() 
	{
		DefaultTableModel model = (DefaultTableModel) tblPublisher.getModel();
		model.setRowCount(0);
		try {
			List<Publisher> list = publisherDAO.getAll();
			for (Publisher publisher : list) {
				Object[] rowObjects = 
					{
							publisher.getId(),
							publisher.getName(),
							publisher.getPhoneNumber(),
							publisher.getEmail(),
							publisher.getAddress(),
							publisher.getCreatedDate()
					};
				model.addRow(rowObjects);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	Publisher getModel()
	{
		Publisher publisher = new Publisher();
		
		try 
		{
			if (index != -1)
			{
				int id = Integer.parseInt(tblPublisher.getValueAt(tblPublisher.getSelectedRow(), 0).toString());
				publisher = PublisherDAO.findById(id);
			}
			
			publisher.setName(txtTenNXB.getText());
			publisher.setPhoneNumber(txtSoDienThoai.getText());
			publisher.setEmail(txtEmail.getText());
			publisher.setAddress(txtDiaChi.getText());
			publisher.setIntroduct(textMoTa.getText());
						
			return publisher;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	private void showDetail() 
	{
		unLockForm();
		indexSelectedLast = tblPublisher.getSelectedRow();
		try {
            int id = Integer.parseInt(String.valueOf(tblPublisher.getValueAt(this.index, 0)));
            Publisher model = publisherDAO.findById(id);           
            if (model != null) 
            {
                txtTenNXB.setText(model.getName());
                txtSoDienThoai.setText(model.getPhoneNumber()); 
                txtEmail.setText(model.getEmail());
                txtDiaChi.setText(model.getAddress());
                textMoTa.setText(model.getIntroduct());
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	private void insert() 
	{
		Publisher publisher = getModel();
		try {
			boolean isSuccess = publisherDAO.insert(publisher);
			if (isSuccess)
			{
				clearForm();
				loadTable();
				setControllMode_Nothing();
				lockForm();

				MessageOptionPane.showAlertDialog(contentPane, "Thêm nhà xuất bản thành công!", MessageOptionPane.ICON_NAME_SUCCESS);
			}
		} 
		catch (Exception e) 
		{
			MessageOptionPane.showAlertDialog(contentPane, "Thêm nhà xuất bản thất bại!", MessageOptionPane.ICON_NAME_ERROR);
			e.printStackTrace();
		}
	}
<<<<<<< HEAD
	public void clearForm()
	{   
		txtDiaChi.setText("");
		txtTenNXB.setText("");
		txtEmail.setText("");
		txtSDT.setText("");
		txtMoTa.setText("");
=======
	
	private void delete() 
	{
		Publisher publisher = getModel();
		int id = Integer.parseInt(String.valueOf(tblPublisher.getValueAt(this.index, 0)));
		try {
			publisherDAO.delete(id);
			clearForm();
			loadTable();
			setControllMode_Nothing();
			lockForm();
			MessageOptionPane.showAlertDialog(contentPane, "Xóa nhà xuất bản thành công!", MessageOptionPane.ICON_NAME_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			MessageOptionPane.showAlertDialog(contentPane, "Xóa nhà xuất bản thất bại!", MessageOptionPane.ICON_NAME_ERROR);
		}
	}

	private void update() 
	{
		Publisher publisher = getModel();
		int id = Integer.parseInt(String.valueOf(tblPublisher.getValueAt(this.index, 0)));
		try 
		{
			boolean isSuccess = publisherDAO.update(publisher, id);
			if (isSuccess)
			{
				clearForm();
				loadTable();
				lockForm();
				setControllMode_Nothing();
				
				MessageOptionPane.showAlertDialog(contentPane, "Cập nhật tài khoản thành công!", MessageOptionPane.ICON_NAME_SUCCESS);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			MessageOptionPane.showAlertDialog(contentPane, "Cập nhật tài khoản thất bại!", MessageOptionPane.ICON_NAME_ERROR);
		}
	}
	
	private boolean validateAll() {
		if (txtTenNXB.getText().length() == 0) 
		{
			MessageOptionPane.showAlertDialog(contentPane, "Tên nhà xuất bản không đươc để trống", MessageOptionPane.ICON_NAME_WARNING);
			txtTenNXB.requestFocus();
			return false;
		}
		else if (txtSoDienThoai.getText().length() == 0) 
		{
			MessageOptionPane.showAlertDialog(contentPane, "Số điện thoại không đươc để trống", MessageOptionPane.ICON_NAME_WARNING);
			txtSoDienThoai.requestFocus();
			return false;
		}
		else if (!txtSoDienThoai.getText().matches("[0-9]{10,}")) {
			MessageOptionPane.showAlertDialog(contentPane, "Số điện thoại 10 số ", MessageOptionPane.ICON_NAME_WARNING);
			txtSoDienThoai.requestFocus();
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
		else if (txtDiaChi.getText().length() == 0) 
		{
			MessageOptionPane.showAlertDialog(contentPane, "Địa chỉ không đươc để trống", MessageOptionPane.ICON_NAME_WARNING);
			txtDiaChi.requestFocus();
			return false;
		}
		else if (textMoTa.getText().length() == 0) 
		{
			MessageOptionPane.showAlertDialog(contentPane, "Mô tả không đươc để trống", MessageOptionPane.ICON_NAME_WARNING);
			textMoTa.requestFocus();
			return false;
		}
		return true;
>>>>>>> master
	}
	// KIỂM TRA CÁC ĐIỀU KIỆN CỦA FROM
	public boolean checkForm() {
		String msg = "Đã có lỗi : \n";
		boolean loiRong = false;
		String pattern = "0[0-9]{10}";
		String Email = "\\w+@\\w+\\.w+";
		if (txtTenNXB.getText().equals("")) 
		{
			msg+="Tên nhà xuất bản không được để trống ! \n";
			loiRong = true;
		}
		if (txtEmail.getText().equals("")) 
		{
			msg+="Email không được để trống !\n";
			loiRong=true;
		}
		try {
			if (txtSDT.getText().equals("")) 
			{
				msg+="Số điện thoại không được để trống !\n";
			}
			else if (txtSDT.getText().matches(pattern)) 
			{
				msg+="Số điện thoại phải đủ 10 số ";
				loiRong = true;
			}
		} catch (NumberFormatException e) {
			msg+="Số điện thoại không đúng dịnh dạng !";
			loiRong = true;
		}
		if (loiRong == true) 
		{
			JOptionPane.showMessageDialog(this, msg);
			return false;
		}	
		return true;
	}

}
