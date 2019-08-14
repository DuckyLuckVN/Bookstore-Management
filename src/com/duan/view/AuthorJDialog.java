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

import com.duan.custom.JTableBlue;
import com.duan.custom.MessageOptionPane;
import com.duan.dao.AuthorDAO;
import com.duan.dao.LocationDAO;
import com.duan.helper.DataHelper;
import com.duan.helper.DateHelper;
import com.duan.helper.SwingHelper;
import com.duan.model.Author;
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

	private JPanel contentPane;
	private JTableBlue tblAuthor;
	private JTextField txtName;
	private JTextField txtBirthDay;
	private JTextField txtDateOfDeath;
	
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
		
		txtBirthDay = new JTextField();
		txtBirthDay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtBirthDay.setColumns(10);
		txtBirthDay.setBounds(227, 47, 231, 25);
		panel.add(txtBirthDay);
		
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
		
		txtDateOfDeath = new JTextField();
		txtDateOfDeath.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDateOfDeath.setColumns(10);
		txtDateOfDeath.setBounds(227, 83, 231, 25);
		panel.add(txtDateOfDeath);
		
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
					insert();
				} catch (ParseException e)
				{
					MessageOptionPane.showAlertDialog(null, "Lỗi NGÀY THÁNG" +"\n Bạn phải thêm đúng định dạng yyyy-MM-dd");
				
				} catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btnNew = new JButton(" Mới");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				clearForm();
				setControllMode_Insert();
				unlockForm();
				tblAuthor.setRequestFocusEnabled(false);
			}
		});
		pnlControll.add(btnNew);
		btnNew.setHorizontalAlignment(SwingConstants.LEFT);
		btnNew.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/Create.png")));
		pnlControll.add(btnInsert);
		btnInsert.setHorizontalAlignment(SwingConstants.LEFT);
		btnInsert.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/Accept.png")));
		
		btnUpdate = new JButton("Cập nhật");
		pnlControll.add(btnUpdate);
		btnUpdate.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/Notes.png")));
		btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnDelete = new JButton("Xóa");
		pnlControll.add(btnDelete);
		btnDelete.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/icons8_delete_32px_1.png")));
		btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
		setLocationRelativeTo(getOwner());
		loadAuthorToList();

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
	
	public void fillToTable()
	{
		 model = (DefaultTableModel) tblAuthor.getModel();
		 model.setRowCount(0);
		
		 for (Author at : list) 
		 {
			 if (at.getDateOfDeath() == null) 
			 {
				 at.setDateOfDeath(null);
			 }
			 Object[] rows = new Object[] {at.getId(), at.getFullName(), at.getDateOfBirth(), at.getDateOfDeath(), at.getCreatedDate()};
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
		Author author = list.get(index);
		txtName.setText(author.getFullName());
		txtBirthDay.setText(format.format(author.getDateOfBirth()));
		if (author.getDateOfDeath() == null) 
			{
				txtDateOfDeath.setText("Chưa có");
			}
		else {
			txtDateOfDeath.setText(format.format(author.getDateOfDeath()));
		}
		txtIntroduction.setText(author.getIntroduce());
		
	}
	
	public void insert() throws ParseException, IOException 
	{
		
		String fullName = txtName.getText();
		Date dateOfBirth = format.parse(txtBirthDay.getText());
		Date dateOfDeath = null ; 
		
		if (txtDateOfDeath.getText().equals("")) 
		{
			dateOfDeath = null;
		}
		else 
		{
			dateOfDeath = format.parse(txtDateOfDeath.getText());
		}
		
		String image  = null;
		if (image != null) 
		{
			image = fileImageSelected.getName();
		}
		else {
			lblAvatar.setText("chưa có ảnh");
		}
		
		String introduce = txtIntroduction.getText();
		Date createdDate = new java.sql.Date(new Date().getTime());
		
		Author at = new Author(0, fullName, dateOfBirth, dateOfDeath, image, introduce, createdDate);
		try 
		{
			if (dao.insert(at)) 
			{
				//Neu file anh da duoc chon thi bat dau ghi file vao source project
				if (fileImageSelected != null)
				{
					//Lay ra mang byte tu file da chon
					byte[] byteArrFileImage = DataHelper.getArrayByteFromFile(fileImageSelected);
					
					//Ghi file tu mang byte cua anh
					DataHelper.writeFileToSource(byteArrFileImage, "/com/duan/image/" + fileImageSelected.getName());
					fileImageSelected = null;
					
				}
					MessageOptionPane.showMessageDialog(this, "thêm thành công Tác giả " + txtName.getText());
				
			}
		} 
		catch (SQLException e) 
		{
			if (e.getErrorCode() == 2627) 
			{
				MessageOptionPane.showMessageDialog(this, "Tài khoản này đã tồn tại");
			}
			MessageOptionPane.showAlertDialog(contentPane, "Thêm tài khoản thất bại!", MessageOptionPane.ICON_NAME_ERROR);
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
	
	public void lockForm()
	{
		txtName.setEditable(false);
		txtBirthDay.setEditable(false);
		txtDateOfDeath.setEditable(false);
		txtIntroduction.setEditable(false);
	}
	public void unlockForm() 
	{
		txtName.setEditable(true);
		txtBirthDay.setEditable(true);
		txtDateOfDeath.setEditable(true);
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
		txtDateOfDeath.setText("");
		txtBirthDay.setText("");
	}
}
