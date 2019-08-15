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

import com.duan.dao.AdminDAO;
import com.duan.dao.AuthorDAO;
import com.duan.custom.common.JDateChooserCustom;
import com.duan.custom.common.JTableBlue;
import com.duan.custom.message.MessageOptionPane;
import com.duan.dao.LocationDAO;
import com.duan.helper.DataHelper;
import com.duan.helper.DateHelper;
import com.duan.helper.SwingHelper;
import com.duan.model.Admin;
import com.duan.model.Author;
import com.duan.model.Location;
import com.toedter.calendar.JDateChooser;

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
import javax.swing.JFileChooser;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class AuthorJDialog extends JDialog {

	private static final Date Date = null;
	private JPanel contentPane;
	private JTableBlue tblAuthor;
	private JTextField txtName;
	private JDateChooserCustom txtBirthDay;
	private JDateChooserCustom txtDateOfDeath;
	private JTableBlue tblLocation;
	private JTextField txtMaKeSach;
	private JDateChooserCustom txtTenKe;
	private JDateChooserCustom txtNgayMat;
	
	private File fileImageSelected;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
	AuthorDAO dao;
	ArrayList<Author> list = new ArrayList<>();
	DefaultTableModel model;
	int index = -1;
	private JTextArea txtIntroduction;
	JButton btnInsert;
	JButton btnUpdate;
	JButton btnDelete;
	JButton btnNew;
	private JLabel lblAvatar;
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					AuthorJDialog frame = new AuthorJDialog();
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public AuthorJDialog() 
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
		setBounds(100, 100, 621, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 227, 595, 189);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 8, 468, 208);
		panel.setLayout(null);
		
		tblAuthor = new JTableBlue();
		tblAuthor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				showDetail();
				unlockForm();
				setControllMode_Edit();
			}
		});
		tblAuthor.setRowHeight(30);
		tblAuthor.setModel(new DefaultTableModel(null, new String[] {"MÃ SỐ", "HỌ TÊN", "NGÀY SINH", "NGÀY MẤT", "NGÀY TẠO"})
		{
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tblAuthor.getColumnModel().getColumn(1).setResizable(false);
		contentPane.setLayout(null);
		scrollPane.setViewportView(tblAuthor);
		contentPane.add(scrollPane);
		contentPane.add(panel);
		
		JLabel lblMThLoi = new JLabel("Họ tên:");
		lblMThLoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMThLoi.setBounds(140, 11, 77, 25);
		panel.add(lblMThLoi);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtName.setBounds(227, 11, 231, 25);
		panel.add(txtName);
		txtName.setColumns(10);
		
		txtBirthDay = new JDateChooserCustom();
		txtBirthDay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtBirthDay.setBounds(227, 47, 231, 25);
		panel.add(txtBirthDay);
		txtTenKe = new JDateChooserCustom();
		txtTenKe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTenKe.setBounds(227, 47, 231, 25);
		panel.add(txtTenKe);
		
		JLabel lblTnThLoi = new JLabel("Ngày sinh:");
		lblTnThLoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTnThLoi.setBounds(140, 47, 77, 25);
		panel.add(lblTnThLoi);
		
		JLabel lblGhiCh = new JLabel("Giới thiệu:");
		lblGhiCh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGhiCh.setBounds(140, 119, 77, 25);
		panel.add(lblGhiCh);
		
		txtIntroduction = new JTextArea();
		txtIntroduction.setBorder(new LineBorder(SystemColor.controlShadow));
		txtIntroduction.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtIntroduction.setBounds(227, 122, 231, 67);
		panel.add(txtIntroduction);
		
		JLabel lblSLngLu = new JLabel("Ngày mất:");
		lblSLngLu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSLngLu.setBounds(140, 83, 77, 25);
		panel.add(lblSLngLu);
		
		txtDateOfDeath = new JDateChooserCustom();
		txtDateOfDeath.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDateOfDeath.setBounds(227, 83, 231, 25);
		panel.add(txtDateOfDeath);
		txtNgayMat = new JDateChooserCustom();
		txtNgayMat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNgayMat.setBounds(227, 83, 231, 25);
		panel.add(txtNgayMat);
		
		lblAvatar = new JLabel("");
		lblAvatar.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblAvatar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAvatar.setBounds(10, 17, 120, 140);
		panel.add(lblAvatar);
		
		JButton btnChn = new JButton("Chọn");
		btnChn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
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
		btnChn.setBounds(10, 160, 59, 33);
		panel.add(btnChn);
		
		JButton btnXa_1 = new JButton("Xóa");
		btnXa_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				clearAvatar();
			}
		});
		btnXa_1.setBounds(71, 160, 59, 33);
		panel.add(btnXa_1);
		
		JPanel pnlControll = new JPanel();
		pnlControll.setBorder(new TitledBorder(null, "\u0110i\u1EC1u khi\u1EC3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlControll.setBounds(488, 8, 117, 208);
		contentPane.add(pnlControll);
		pnlControll.setLayout(new GridLayout(0, 1, 0, 10));
		
		btnInsert = new JButton(" Lưu");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					if(checkForm())
					{
						insert();
					}
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				} 
			}
		});
		
		btnNew = new JButton(" Mới");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				index = -1;
				clearForm();
				setControllMode_Insert();
				unlockForm();
				tblAuthor.setRequestFocusEnabled(false);
				System.out.println(lblAvatar.getText());
				System.out.println(index);
			}
		});
		pnlControll.add(btnNew);
		btnNew.setHorizontalAlignment(SwingConstants.LEFT);
		btnNew.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/Create.png")));
		pnlControll.add(btnInsert);
		btnInsert.setHorizontalAlignment(SwingConstants.LEFT);
		btnInsert.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/Accept.png")));
		
		btnUpdate = new JButton("Cập nhật");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if (checkForm()) 
				{
					update();
					clearAvatar();
				}

			}
		});
		pnlControll.add(btnUpdate);
		btnUpdate.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/Notes.png")));
		btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				int count = JOptionPane.showConfirmDialog(null, "Bạn thực sự muốn xóa ? ","Thông báo",JOptionPane.YES_NO_OPTION);
				if (count == JOptionPane.YES_OPTION) 
				{
					delete();
					clearAvatar();
					clearForm();
					lockForm();
					setControllMode_Nothing();
				}
				
			}
		});
		pnlControll.add(btnDelete);
		btnDelete.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/icons8_delete_32px_1.png")));
		btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
		setLocationRelativeTo(getOwner());
		loadAuthorToList();
		setControllMode_Nothing();

	}
	
	public void loadAuthorToList()
	{
		try 
		{
			list = dao.getAll();
			if (list.size() > 0) 
			{
				fillToTable();
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkForm()
	{
		String message = "Đã có lỗi xảy ra : \n";
		boolean loiRong = false;
		if (txtName.getText().equals("")) 
		{
			message+="   +Tên của tác giả không được để trống \n";
			loiRong = true;
		}
		else if (!txtName.getText().matches("^[a-zA-Z\\p{L} ]{1,}$"))
		{
			message +="   +Họ tên chỉ được nhập chữ \n";
			loiRong = true;
		}
		if (txtBirthDay.getDate() == null) 
		{
			message +="   +Ngày sinh không được để trống -- Hoặc sai dịnh dạng \n";
			loiRong = true;
		}
		if (txtIntroduction.getText().equals(""))
		{
			message +="   +Thông tin về tác giả không được để trống \n" ;
			loiRong = true;
		}
		if (loiRong == true) 
		{
			MessageOptionPane.showMessageDialog(this, message);
			return false;
		}
		return true;
	}
	
	public void fillToTable()
	{
		 model = (DefaultTableModel) tblAuthor.getModel();
		 model.setRowCount(0);
		 String death = "";
		 for (Author at : list) 
		 {
			 if (at.getDateOfDeath() == null) 
			 {
				 death = "Chưa có";
			 }
			 if(at.getDateOfDeath() != null)
			 {
				 death = format.format(at.getDateOfDeath());
			 }
			 Object[] rows = new Object[] {at.getId(), at.getFullName(), at.getDateOfBirth(), death, at.getCreatedDate()};
			 model.addRow(rows);
		 }
	}
	
	public void showDetail()
	{
		index = tblAuthor.getSelectedRow();
		if (index < 0) 
		{
			MessageOptionPane.showMessageDialog(this, "Bạn chưa chọn hàng !");
		}
		
		try 
		{
			int id = Integer.parseInt(String.valueOf(tblAuthor.getValueAt(this.index, 0)));
			Author author  = dao.findByID(id);
			if (author != null) 
			{
				
				txtName.setText(author.getFullName());
				txtBirthDay.setDate(author.getDateOfBirth());
				txtDateOfDeath.setDate(author.getDateOfDeath());
				txtIntroduction.setText(author.getIntroduce());
				 if (author.getImage() != null && author.getImage().length() > 0)
		         	setAvatar(author.getImage());
		         else
		         	clearAvatar();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	Author getModel()
	{
		Author at = new Author();
		
			try 
			{
				if (index != -1 ) 
				{
					int id = Integer.parseInt(tblAuthor.getValueAt(tblAuthor.getSelectedRow(), 0).toString());
					at = dao.findByID(id);
				}
				at.setFullName(txtName.getText());
				at.setDateOfBirth(txtBirthDay.getDate());
				at.setDateOfDeath(txtDateOfDeath.getDate());
				if (fileImageSelected != null)
				{
					at.setImage(fileImageSelected.getName());
				}
				at.setIntroduce(txtIntroduction.getText());
				
				at.setCreatedDate(new Date());
				return at;
				
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			return null;
		
	}
	public void insert() throws  IOException 
	{
		Author at = getModel();
		try 
		{
			boolean issucces = dao.insert(at);
			if (issucces) 
			{
				clearForm();
				clearAvatar();
				lockForm();
				setControllMode_Nothing();
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
				list.add(at);
				loadAuthorToList();
				fillToTable();
				
			}
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void delete()
	{
		int id = Integer.parseInt(String.valueOf(tblAuthor.getValueAt(this.index, 0)));
		try 
		{
			if (dao.delete(id))
			{
				MessageOptionPane.showAlertDialog(this, "Xóa thành công tác giả có tên : " +txtName.getText());
				list.remove(index);
				fillToTable();
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void update()
	{

		Author at = getModel();
		int id = Integer.parseInt(String.valueOf(tblAuthor.getValueAt(this.index, 0)));
		try 
		{
			if (lblAvatar.getIcon() == null) 
			{
				at.setImage(null);
			}
			else
			{
				if (fileImageSelected != null)
				{
					at.setImage(fileImageSelected.getName());
					
					//Lay ra mang byte tu file da chon
					byte[] byteArrFileImage = DataHelper.getArrayByteFromFile(fileImageSelected);
					
					//Ghi file tu mang byte cua anh
					DataHelper.writeFileToSource(byteArrFileImage, "/com/duan/image/" + fileImageSelected.getName());
					fileImageSelected = null;
				}
			}
			boolean issucess = dao.update(at, id);
			if (issucess) 
			{
				
				clearForm();
				loadAuthorToList();
				setControllMode_Nothing();
			}
			MessageOptionPane.showMessageDialog(this, "Sửa thành công tác giả có tên " +list.get(index).getFullName());
//			tblAuthor.setRowSelectionInterval(index, index);
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setAvatar(File file)
	{
		if (file != null)
		{
			ImageIcon icon = new ImageIcon(file.getAbsolutePath());
			lblAvatar.setText("");
			lblAvatar.setIcon(icon);
			SwingHelper.setAutoResizeIcon(lblAvatar);
		}
	}
	
	public void setAvatar(String imgName)
	{
		URL urlImage = getClass().getResource("/com/duan/image/" + imgName);
		if (urlImage != null)
		{
			lblAvatar.setText("");
			ImageIcon icon = new ImageIcon(urlImage);
			lblAvatar.setIcon(icon);
			SwingHelper.setAutoResizeIcon(lblAvatar);
		}
	}
	
	public void clearAvatar()
	{
		lblAvatar.setIcon(null);
		lblAvatar.setText("Không có ảnh");
	}
	
	public void lockForm()
	{
		txtName.setEditable(true);
		txtIntroduction.setEditable(true);
	}
	public void unlockForm() 
	{
		txtNgayMat.setEnabled(true);
		txtIntroduction.setEditable(true);
	}
	public void setControllMode_Nothing()
	{
		btnNew.setEnabled(true);
		btnInsert.setEnabled(false);
		btnDelete.setEnabled(false);
		btnUpdate.setEnabled(false);
	}
	public void setControllMode_Edit()
	{
		btnNew.setEnabled(true);
		btnUpdate.setEnabled(true);
		btnDelete.setEnabled(true);
		btnInsert.setEnabled(false);
	}
	
	public void setControllMode_Insert()
	{
		btnNew.setEnabled(false);
		btnInsert.setEnabled(true);
		btnDelete.setEnabled(false);
		btnUpdate.setEnabled(false);
	}
	public void clearForm()
	{
		txtIntroduction.setText("");
		txtName.setText("");
		txtDateOfDeath.setDate(null);;
		txtBirthDay.setDate(null);
		clearAvatar();

	}
}
