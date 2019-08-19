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
import com.duan.dao.CategoryDAO;
import com.duan.dao.LocationDAO;
import com.duan.dao.PublisherDAO;
import com.duan.model.Admin;
import com.duan.model.Category;
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
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PublisherJDialog extends JDialog {
	String head[] = {"MÃ SỐ","TÊN NXB","SỐ ĐT","EMAIL","ĐỊA CHỈ","NGÀY TẠO"};
    DefaultTableModel tblModel = new DefaultTableModel(head, 0);
	private JPanel contentPane;
	private JTableBlue tblLocation;
	private JTextField txtTenNXB;
	private JTextField txtSDT;
	private JTextField txtEmail;
	
	PublisherDAO dao;
	ArrayList<Location> list = new ArrayList<>();
	int index = -1;
	private JTextArea txtDiaChi;
	JButton btnThm;
	JButton btnCpNht;
	JButton btnXa;
	JButton btnMi;
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
		
		tblLocation = new JTableBlue();
		tblLocation.setRowHeight(30);
		tblLocation.setModel(new DefaultTableModel(null, new String[] {"MÃ SỐ", "TÊN NXB", "SỐ ĐT", "EMAIL", "ĐỊA CHỈ", "NGÀY TẠO"})
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
		
		JLabel lblMThLoi = new JLabel("Tên NXB:");
		lblMThLoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMThLoi.setBounds(10, 16, 87, 25);
		panel.add(lblMThLoi);
		
		txtTenNXB = new JTextField();
		txtTenNXB.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTenNXB.setBounds(107, 16, 264, 25);
		panel.add(txtTenNXB);
		txtTenNXB.setColumns(10);
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSDT.setColumns(10);
		txtSDT.setBounds(107, 52, 264, 25);
		panel.add(txtSDT);
		
		JLabel lblTnThLoi = new JLabel("Số điện thoại:");
		lblTnThLoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTnThLoi.setBounds(10, 52, 87, 25);
		panel.add(lblTnThLoi);
		
		JLabel lblGhiCh = new JLabel("Địa chỉ:");
		lblGhiCh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGhiCh.setBounds(10, 124, 87, 25);
		panel.add(lblGhiCh);
		
		txtDiaChi = new JTextArea();
		txtDiaChi.setBorder(new LineBorder(SystemColor.controlShadow));
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDiaChi.setBounds(107, 124, 264, 62);
		panel.add(txtDiaChi);
		
		JLabel lblSLngLu = new JLabel("Email:");
		lblSLngLu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSLngLu.setBounds(10, 88, 87, 25);
		panel.add(lblSLngLu);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtEmail.setColumns(10);
		txtEmail.setBounds(107, 88, 264, 25);
		panel.add(txtEmail);
		
		JLabel lblMT = new JLabel("Mô tả:");
		lblMT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMT.setBounds(10, 197, 87, 25);
		panel.add(lblMT);
		
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
		
		btnThm = new JButton(" Lưu");
		btnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insert();
			}
		});
		
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
	}
	public void unlockForm() 
	{
		txtTenNXB.setEditable(true);
		txtSDT.setEditable(true);
		txtEmail.setEditable(true);
		txtDiaChi.setEditable(true);
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
		txtDiaChi.setText("");
		txtTenNXB.setText("");
		txtEmail.setText("");
		txtSDT.setText("");
		txtMoTa.setText("");
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
