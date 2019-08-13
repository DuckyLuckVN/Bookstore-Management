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
import com.duan.dao.CategoryDAO;
import com.duan.model.Category;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryJDialog extends JDialog {
	String head[]= {"Mã thể loại","Tên thể loại","Ghi chú"};
	DefaultTableModel model = new DefaultTableModel(head, 0);
	
	private JPanel contentPane;
	private JTableBlue tblCategory;
	private JTextField txtMaTheLoai;
	private JTextField txtTenTheLoai;
	private JTextArea txtGhiChu;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnSave;
	private JButton btnNew;
	
	ArrayList<Category> list = new ArrayList<>();
	CategoryDAO dao;
	int index = -1;
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryJDialog frame = new CategoryJDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CategoryJDialog() 
	{
		setModal(true);
		setResizable(false);
		setTitle("Quản lý thể loại");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LocationJDialog.class.getResource("/com/duan/icon/icons8_medium_priority_50px.png")));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 486, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		scrollPane.setBounds(10, 204, 459, 147);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 8, 344, 185);
		panel.setLayout(null);
		
		tblCategory = new JTableBlue();
		tblCategory.setRowHeight(30);
		tblCategory.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				showDetail();
				setControllMode_Edit();
				unLockForm();
				txtMaTheLoai.setEnabled(false);
			}
		});
		tblCategory.setModel(new DefaultTableModel(null, new String[] {"MÃ THỂ LOẠI", "TÊN THỂ LOẠI", "GHI CHÚ"})
		{
			boolean[] columnEditables = new boolean[] {
				true, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblCategory.getColumnModel().getColumn(1).setResizable(false);
		contentPane.setLayout(null);
		scrollPane.setViewportView(tblCategory);
		contentPane.add(scrollPane);
		contentPane.add(panel);
		
		JLabel lblMThLoi = new JLabel("Mã thể loại");
		lblMThLoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMThLoi.setBounds(10, 11, 77, 33);
		panel.add(lblMThLoi);
		
		txtMaTheLoai = new JTextField();
		txtMaTheLoai.setEnabled(false);
		txtMaTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaTheLoai.setBounds(97, 11, 237, 33);
		panel.add(txtMaTheLoai);
		txtMaTheLoai.setColumns(10);
		
		txtTenTheLoai = new JTextField();
		txtTenTheLoai.setEnabled(false);
		txtTenTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTenTheLoai.setColumns(10);
		txtTenTheLoai.setBounds(97, 55, 237, 33);
		panel.add(txtTenTheLoai);
		
		JLabel lblTnThLoi = new JLabel("Tên thể loại");
		lblTnThLoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTnThLoi.setBounds(10, 55, 77, 33);
		panel.add(lblTnThLoi);
		
		JLabel lblGhiCh = new JLabel("Ghi chú");
		lblGhiCh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGhiCh.setBounds(10, 99, 77, 25);
		panel.add(lblGhiCh);
		
		txtGhiChu = new JTextArea();
		txtGhiChu.setEnabled(false);
		txtGhiChu.setBorder(new LineBorder(SystemColor.controlShadow));
		txtGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtGhiChu.setBounds(97, 99, 237, 65);
		panel.add(txtGhiChu);
		
		btnSave = new JButton(" Lưu");
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (checkForm()) 
				{
					insert();
					setControllMode_Nothing();
					clearForm();
					lockForm();
				}
				
			}
		});
		btnSave.setHorizontalAlignment(SwingConstants.LEFT);
		btnSave.setIcon(new ImageIcon(LocationJDialog.class.getResource("/com/duan/icon/Accept.png")));
		btnSave.setBounds(364, 57, 105, 38);
		contentPane.add(btnSave);
		
		btnUpdate = new JButton("Cập nhật");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if (checkForm()) 
				{
					update();
					clearForm();
					lockForm();
					setControllMode_Nothing();
					
				}
			}

		});
		btnUpdate.setIcon(new ImageIcon(LocationJDialog.class.getResource("/com/duan/icon/Notes.png")));
		btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		btnUpdate.setBounds(364, 106, 105, 38);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Xóa");
		btnDelete.setEnabled(false);
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				
			}

		});
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int luachon = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa thể loại này không ?","Thông báo ",JOptionPane.YES_NO_OPTION);
				if (luachon == JOptionPane.YES_OPTION) 
				{
						delete();
						lockForm();	
						clearForm();
						setControllMode_Nothing();
				}
			}
		});
		btnDelete.setIcon(new ImageIcon(LocationJDialog.class.getResource("/com/duan/icon/icons8_delete_32px_1.png")));
		btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
		btnDelete.setBounds(364, 155, 105, 38);
		contentPane.add(btnDelete);
		
		btnNew = new JButton(" Mới");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				
				unLockForm();
				setControllMode_Insert();
				txtMaTheLoai.requestFocus();
				clearForm();
				
				
			}
		});
		btnNew.setHorizontalAlignment(SwingConstants.LEFT);
		btnNew.setIcon(new ImageIcon(LocationJDialog.class.getResource("/com/duan/icon/Create.png")));
		btnNew.setBounds(364, 8, 105, 38);
		contentPane.add(btnNew);
		setLocationRelativeTo(getOwner());
		loadCategoryToList();
	}
	
	public void test()
	{
		txtGhiChu.getText();
	}
	
	public void loadCategoryToList() 
	{
		dao = new CategoryDAO();
		try 
		{
			list = dao.getAll();
			if (list.size() > 0) 
			{
				fillToTable();
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void fillToTable() 
	{
		model = (DefaultTableModel) tblCategory.getModel();
		model.setRowCount(0);
		for (Category category : list) 
		{
			Object[] rows = new Object[] {category.getId(),category.getCategoryTitle(),category.getCategoryDescription()};
			model.addRow(rows);
		}
	}
	
	public void showDetail()
	{
		index = tblCategory.getSelectedRow();
		if (index < 0 )
		{
			return;
		}
		Category category = list.get(index);
		txtMaTheLoai.setText(category.getId());
		txtTenTheLoai.setText(category.getCategoryTitle());
		txtGhiChu.setText(category.getCategoryDescription());
	}
	
	public boolean checkForm()
	{
		String msg="Đã có lỗi : \n";
		boolean loiRong = false;
		if (txtMaTheLoai.getText().equals(""))
		{
			msg+="\n Không được để trống mã thể loại !\n";
			loiRong = true;
		}
		if (txtTenTheLoai.getText().equals("")) 
		{
			msg+="\n Không được để trống tên thể loại!\n";
			loiRong = true;
		}
		if (loiRong == true) 
		{
			JOptionPane.showMessageDialog(this, msg);
			return false;
		}
		
		
		return true;
	}
	public void insert()
	{
		dao = new CategoryDAO();
		String id = txtMaTheLoai.getText();
		String title = txtTenTheLoai.getText();
		String decription = txtGhiChu.getText();
		
		Category category = new Category(id, title, decription);
		try 
		{
			if (dao.insert(category)) 
			{
				JOptionPane.showMessageDialog(this, "Thêm thành công thể loại : " + txtTenTheLoai.getText());
				list.add(category);
				fillToTable();
			}
		} catch (SQLException e) 
		{
			if (e.getErrorCode() == 2627) 
			{
				JOptionPane.showMessageDialog(this, "Mã thể loại này  đã tồn tại!");
			}
			
		}	
	}
	
	public void update()
	{
		dao = new CategoryDAO();
		String id = txtMaTheLoai.getText();
		String title = txtTenTheLoai.getText();
		String decription = txtGhiChu.getText();
		
		Category category = new Category(id, title, decription);
		
		try 
		{
			if (dao.update(category, id)) 
			{
				JOptionPane.showMessageDialog(this, "Sửa thành công thể loại có mã : " + list.get(index).getId());
				list.set(index, category);
				fillToTable();
			}
		} catch (SQLException e) 
		{
			if (e.getErrorCode() == 2627) 
			{
				JOptionPane.showMessageDialog(this, "Thể loại đã tồn tại!");
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete() 
	{
		dao = new CategoryDAO();
		String id = txtMaTheLoai.getText();
		try 
		{
			if (dao.delete(id)) 
			{
				JOptionPane.showMessageDialog(this, "Xóa thành công thể loại có mã : " + list.get(index).getId());
				list.remove(index);
				fillToTable();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void setControllMode_Nothing()
	{
		btnNew.setEnabled(true);
		btnSave.setEnabled(false);
		btnDelete.setEnabled(false);
		btnUpdate.setEnabled(false);
	}
	
	public void setControllMode_Edit()
	{
		btnNew.setEnabled(true);
		btnSave.setEnabled(false);
		btnDelete.setEnabled(true);
		btnUpdate.setEnabled(true);
	}
	
	public void setControllMode_Insert()
	{
		btnNew.setEnabled(false);
		btnSave.setEnabled(true);
		btnDelete.setEnabled(false);
		btnUpdate.setEnabled(false);
	}
	
	public void clearForm()
	{
		txtGhiChu.setText("");
		txtMaTheLoai.setText("");
		txtTenTheLoai.setText("");
	}
	
	public void lockForm()
	{
		txtGhiChu.setEnabled(false);
		txtMaTheLoai.setEnabled(false);
		txtTenTheLoai.setEnabled(false);
	}
	
	public void unLockForm()
	{
		txtGhiChu.setEnabled(true);
		txtMaTheLoai.setEnabled(true);
		txtTenTheLoai.setEnabled(true);
	}
}
