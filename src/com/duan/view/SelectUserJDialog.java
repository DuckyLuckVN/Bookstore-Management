package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import com.duan.custom.JTableBlue;
import com.duan.dao.UserDAO;
import com.duan.helper.DataHelper;
import com.duan.model.User;

import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SelectUserJDialog extends JDialog {

	public static final int STATUS_NOT_SELECT = -1;
	public static final int STATUS_SELECTED = 1;
	
	private JPanel contentPane;
	private JTableBlue tblUser;
	private JLabel lblTmTheoTn;
	private JTextField txtSearch;
	
	public int status;
	private User userSelect = null;
	private List<User> listUser = new ArrayList<User>();
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectUserJDialog frame = new SelectUserJDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SelectUserJDialog() 
	{
		setModal(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelectUserJDialog.class.getResource("/com/duan/icon/icons8_user_groups_64px.png")));
		setTitle("Chọn người dùng");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 377, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 48, 356, 218);
		contentPane.add(scrollPane);
		
		tblUser = new JTableBlue();
		tblUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (e.getClickCount() >= 2)
				{
					try 
					{
						saveDataSelected();
					} 
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					}
				}
			}
		});
		tblUser.setRowHeight(30);
		tblUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblUser.setModel(new DefaultTableModel(null,new String[] {"MÃ", "TÀI KHOẢNG", "HỌ TÊN"}) 
		{
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		});
		tblUser.getColumnModel().getColumn(0).setPreferredWidth(0);
		tblUser.getColumnModel().getColumn(1).setPreferredWidth(100);
		scrollPane.setViewportView(tblUser);
		
		lblTmTheoTn = new JLabel("Tìm kiếm:");
		lblTmTheoTn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTmTheoTn.setBounds(5, 11, 59, 26);
		contentPane.add(lblTmTheoTn);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				search();
			}
		});
		txtSearch.setBounds(74, 11, 287, 26);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		getDataToList();
		fillToTable();
	}
	
	//Trả về trạng thái của hành động chọn user đã được chọn hay chưa
	public int getStatus()
	{
		return this.status;
	}
	
	//Lấy dữ liệu user về đổ vào listUser
	public void getDataToList()
	{
		listUser.clear();
		try 
		{
			listUser = UserDAO.getAll();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Đổ dữ liệu từ listUser vào table tblUser
	public void fillToTable()
	{
		DefaultTableModel model = (DefaultTableModel) tblUser.getModel();
		model.setRowCount(0);
		
		for (User e : listUser)
		{
			String[] rowData = {e.getId() + "", e.getUsername(), e.getFullname()};
			model.addRow(rowData);
		}
	}
	
	//Thực hiện lưu lại model user vừa chọn trong bảng, sau đó đóng cửa sổ lại.
	public void saveDataSelected() throws SQLException
	{
		int index = tblUser.getSelectedRow();
		int userId = DataHelper.getInt(tblUser.getValueAt(index, 0).toString());
		
		userSelect = UserDAO.findByID(userId);
		this.status = 1;
		dispose();
	}
	
	//Trả về đối tượng user đã được chọn
	public User getUserSelected()
	{
		return userSelect;
	}

	public void openSelectDialog()
	{
		userSelect = null;
		getDataToList();
		fillToTable();
		status = STATUS_NOT_SELECT;
		setVisible(true);
	}
	
	public void search()
	{
		DefaultTableModel model = (DefaultTableModel) tblUser.getModel();
		model.setRowCount(0);
		
		for (User u : listUser)
		{
			if (DataHelper.search(u.getSearchString(), txtSearch.getText()))
			{
				String[] rowData = {u.getId() + "", u.getUsername(), u.getFullname()};
				model.addRow(rowData);
			}
		}
	}
	

}
