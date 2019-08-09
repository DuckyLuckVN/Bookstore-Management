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

import com.duan.dao.LocationDAO;
import com.duan.model.Location;

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
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LocationJDialog extends JDialog {

	private JPanel contentPane;
	private JTable tblLocation;
	private JTextField txtMaKeSach;
	private JTextField txtTenKe;
	private JTextField txtSucChua;
	
	LocationDAO dao;
	ArrayList<Location> list = new ArrayList<>();
	DefaultTableModel model;
	int index = -1;
	private JTextArea txtGhiChu;
	JButton btnThm;
	JButton btnCpNht;
	JButton btnXa;
	JButton btnMi;
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					LocationJDialog frame = new LocationJDialog();
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public LocationJDialog() 
	{
		setModal(true);
		setResizable(false);
		setTitle("Quản lý kệ sách");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CategoryJDialog.class.getResource("/com/duan/icon/icons8_medium_priority_50px.png")));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 495, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 227, 471, 189);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 8, 344, 208);
		panel.setLayout(null);
		
		tblLocation = new JTable();
		tblLocation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				showDetail();
				setControllMode_Edit();
				unlockForm();
				txtMaKeSach.setEditable(false);
			}
		});
		tblLocation.setModel(new DefaultTableModel(null, new String[] {"MÃ KỆ SÁCH", "TÊN KỆ", "SỨC CHỨA", "GHI CHÚ"})
		{
			boolean[] columnEditables = new boolean[] {
				true, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblLocation.getColumnModel().getColumn(1).setResizable(false);
		contentPane.setLayout(null);
		scrollPane.setViewportView(tblLocation);
		contentPane.add(scrollPane);
		contentPane.add(panel);
		
		JLabel lblMThLoi = new JLabel("Mã kệ sách");
		lblMThLoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMThLoi.setBounds(10, 19, 77, 25);
		panel.add(lblMThLoi);
		
		txtMaKeSach = new JTextField();
		txtMaKeSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaKeSach.setBounds(97, 19, 237, 25);
		panel.add(txtMaKeSach);
		txtMaKeSach.setColumns(10);
		
		txtTenKe = new JTextField();
		txtTenKe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTenKe.setColumns(10);
		txtTenKe.setBounds(97, 55, 237, 25);
		panel.add(txtTenKe);
		
		JLabel lblTnThLoi = new JLabel("Tên kệ");
		lblTnThLoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTnThLoi.setBounds(10, 55, 77, 25);
		panel.add(lblTnThLoi);
		
		JLabel lblGhiCh = new JLabel("Ghi chú");
		lblGhiCh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGhiCh.setBounds(10, 127, 77, 25);
		panel.add(lblGhiCh);
		
		txtGhiChu = new JTextArea();
		txtGhiChu.setBorder(new LineBorder(SystemColor.controlShadow));
		txtGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtGhiChu.setBounds(97, 130, 237, 67);
		panel.add(txtGhiChu);
		
		JLabel lblSLngLu = new JLabel("Sức chứa");
		lblSLngLu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSLngLu.setBounds(10, 91, 77, 25);
		panel.add(lblSLngLu);
		
		txtSucChua = new JTextField();
		txtSucChua.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSucChua.setColumns(10);
		txtSucChua.setBounds(97, 91, 237, 25);
		panel.add(txtSucChua);
		
		JPanel pnlControll = new JPanel();
		pnlControll.setBorder(new TitledBorder(null, "\u0110i\u1EC1u khi\u1EC3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlControll.setBounds(364, 8, 117, 208);
		contentPane.add(pnlControll);
		pnlControll.setLayout(new GridLayout(0, 1, 0, 10));
		
		btnThm = new JButton(" Lưu");
		btnThm.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (checkForm()) 
				{
					insert();
					clearForm();
					setControllMode_Nothing();
					lockForm();
				}
			}
		});
		pnlControll.add(btnThm);
		btnThm.setHorizontalAlignment(SwingConstants.LEFT);
		btnThm.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/Accept.png")));
		
		btnCpNht = new JButton("Cập nhật");
		btnCpNht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (checkForm()) 
				{
					update();
					clearForm();
					lockForm();
					setControllMode_Nothing();
				}
			}

			private void LoadDataToJTable() {
				// TODO Auto-generated method stub
				
			}
		});
		pnlControll.add(btnCpNht);
		btnCpNht.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/Notes.png")));
		btnCpNht.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int luachon = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa ? ","Thông báo",JOptionPane.YES_NO_OPTION);
				if (luachon == JOptionPane.YES_OPTION) 
				{
					delete();
					clearForm();
					lockForm();
					setControllMode_Nothing();
				}
				else 
				{
					fillToTable();
				}
			}
		});
		pnlControll.add(btnXa);
		btnXa.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/icons8_delete_32px_1.png")));
		btnXa.setHorizontalAlignment(SwingConstants.LEFT);
		
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
		setLocationRelativeTo(getOwner());
		loadLocationToList();
		lockForm();
		setControllMode_Nothing();
	}
	
	public void loadLocationToList()
	{
		dao = new LocationDAO();
		try 
		{
			list = dao.getAll();
			if (list.size() > 0) 
			{
				fillToTable();
			}
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fillToTable()
	{
		model = (DefaultTableModel) tblLocation.getModel();
		model.setRowCount(0);
		for (Location lc : list) 
		{
			Object[] rows = new Object[] {lc.getId(),lc.getLocationName(),lc.getMaxStorage(),lc.getDescription()};
			model.addRow(rows);
		}
	}
	
	public void showDetail()
	{
		index = tblLocation.getSelectedRow();
		if (index < 0) 
		{
			return;
		}
		
		Location lc = list.get(index);
		txtMaKeSach.setText(lc.getId());
		txtTenKe.setText(lc.getLocationName());
		txtSucChua.setText(lc.getMaxStorage()+"");
		txtGhiChu.setText(lc.getDescription());
	}
	
	public boolean checkForm()
	{
		String msg = "Đã có lỗi : \n";
		boolean loiRong = false;
		if (txtMaKeSach.getText().equals("")) 
		{
			msg+="Mã kệ sách không được để trống ! \n";
			loiRong = true;
		}
		if (txtTenKe.getText().equals("")) 
		{
			msg+="Tên kệ sách không được để trống !\n";
		}
		
		
			try 
			{
				int succhua = Integer.parseInt(txtSucChua.getText());
				if (txtSucChua.getText().equals("")) 
				{
					msg+="Sức chứ không được để trống !\n";
				}
				if (succhua < 0) 
				{
					msg+="Sức chứa phải lớn hơn 0";
					loiRong = true;
				}
				
			}
			catch (NumberFormatException e) 
			{
				msg+="Sức chứa không đúng dịnh dạng !";
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
		
		dao = new LocationDAO();
		String id = txtMaKeSach.getText();
		String ten  = txtTenKe.getText();
		int sucChua = Integer.parseInt(txtSucChua.getText());
		String moTa = txtGhiChu.getText();
		
		Location lc = new Location(id, ten, sucChua, moTa);
		try 
		{
			if (dao.insert(lc)) 
			{
				JOptionPane.showMessageDialog(this, "Thêm thành công mã kệ : " + txtMaKeSach.getText());
				list.add(lc);
				fillToTable();
			}
		} catch (SQLException e) 
		{
//			if (e.getErrorCode() == 2627) 
//			{
//				JOptionPane.showMessageDialog(this, "Mã kệ này đã tồn tại !");
//			}
			e.printStackTrace();
		}
	}
	
	public void update ()
	{
		dao = new LocationDAO();
		
		String id = txtMaKeSach.getText();
		String ten  = txtTenKe.getText();
		int sucChua = Integer.parseInt(txtSucChua.getText());
		String moTa = txtGhiChu.getText();
		
		Location lc = new Location(id, ten, sucChua, moTa);
		try 
		{
			if (dao.update(lc, id)) 
			{
				JOptionPane.showMessageDialog(this, "Sửa thành công kệ có mã : " + list.get(index).getId());
				list.set(index, lc);
				fillToTable();
				tblLocation.setRowSelectionInterval(index, index);
			}
		} catch (SQLException e) 
		{
			if (e.getErrorCode() == 2627) 
			{
				JOptionPane.showMessageDialog(this, "Mã kệ này đã tồn tại");
			}
			e.printStackTrace();
		}
	}
	
	public void delete()
	{
		dao = new LocationDAO();
		try 
		{
			if (dao.delete(list.get(index).getId())) 
			{
					JOptionPane.showMessageDialog(this, "Xóa thành công kệ sách có mã : " + list.get(index).getId());
					list.remove(index);
					fillToTable();
			}
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(this	, "Không thể xóa mã kệ sách này");
			
		}
	}
	
	public void lockForm()
	{
		txtMaKeSach.setEditable(false);
		txtTenKe.setEditable(false);
		txtSucChua.setEditable(false);
		txtGhiChu.setEditable(false);
	}
	public void unlockForm() 
	{
		txtMaKeSach.setEditable(true);
		txtTenKe.setEditable(true);
		txtSucChua.setEditable(true);
		txtGhiChu.setEditable(true);
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
		txtGhiChu.setText("");
		txtMaKeSach.setText("");
		txtSucChua.setText("");
		txtTenKe.setText("");
	}
	
}
