package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import com.duan.dao.BookDAO;
import com.duan.dao.CategoryDAO;
import com.duan.dao.LocationDAO;
import com.duan.helper.DataHelper;
import com.duan.helper.DateHelper;
import com.duan.helper.SwingHelper;
import com.duan.model.Book;
import com.duan.model.Category;
import com.duan.model.Location;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

public class BookEditorJDialog extends JDialog {

	private JPanel contentPane;
	private JTextField txtMaSach;
	private JTextField txtTenSach;
	private JTextField txtSoTrang;
	private JTextField txtGia;
	private JTextField txtNhaXuatBan;
	private JComboBox cboTheLoai;
	private JTextArea txtGhiChu;
	private JComboBox cboNamXuatBan;
	private JLabel lblImage;
	private JComboBox cboViTri;
	
	CategoryJDialog categoryJDialog = new CategoryJDialog();
	
	
	List<Category> listCategory;
	List<Location> listLocation;
	BookJFrame bookJFrame;
	private File fileImage;
	
	private boolean isEditMode;
	private JTextField txtTacGia;
	
	private Book bookEdit;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookEditorJDialog frame = new BookEditorJDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public BookEditorJDialog() 
	{
		setResizable(false);
		init();
		setTitle("Thêm Sách");
		isEditMode = false;
	}
	
	public BookEditorJDialog(Book book)
	{
		this();
		try 
		{
			setBookEditor(book);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	
	}
	
	private void init()
	{
		setTitle("Thêm sách");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BookEditorJDialog.class.getResource("/com/duan/icon/icons8_edit_property_50px.png")));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 648, 403);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlForm = new JPanel();
		pnlForm.setBackground(SystemColor.menu);
		pnlForm.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlForm.setBounds(10, 11, 398, 351);
		contentPane.add(pnlForm);
		pnlForm.setLayout(null);
		
		JLabel lblMaSach = new JLabel("Mã sách");
		lblMaSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMaSach.setBounds(10, 11, 75, 24);
		pnlForm.add(lblMaSach);
		
		txtMaSach = new JTextField();
		txtMaSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMaSach.setBounds(95, 11, 293, 24);
		pnlForm.add(txtMaSach);
		txtMaSach.setColumns(10);
		
		JLabel lblTieuDe = new JLabel("Tiêu đề");
		lblTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTieuDe.setBounds(10, 46, 75, 24);
		pnlForm.add(lblTieuDe);
		
		txtTenSach = new JTextField();
		txtTenSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTenSach.setColumns(10);
		txtTenSach.setBounds(95, 46, 293, 24);
		pnlForm.add(txtTenSach);
		
		JLabel lblTheLoai = new JLabel("Thể loại");
		lblTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTheLoai.setBounds(10, 81, 75, 24);
		pnlForm.add(lblTheLoai);
		
		cboTheLoai = new JComboBox();
		cboTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboTheLoai.setBounds(95, 81, 191, 24);
		pnlForm.add(cboTheLoai);
		
		JButton btnEditTheLoai = new JButton("Tùy chỉnh");
		btnEditTheLoai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				showCategoryJFrame();
			}
		});
		btnEditTheLoai.setBounds(296, 81, 92, 24);
		pnlForm.add(btnEditTheLoai);
		
		JLabel lblSTrang = new JLabel("Số trang");
		lblSTrang.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSTrang.setBounds(10, 116, 75, 24);
		pnlForm.add(lblSTrang);
		
		txtSoTrang = new JTextField();
		txtSoTrang.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSoTrang.setColumns(10);
		txtSoTrang.setBounds(95, 116, 75, 24);
		pnlForm.add(txtSoTrang);
		
		JLabel lblSTrang_1 = new JLabel("Giá bán");
		lblSTrang_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSTrang_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSTrang_1.setBounds(180, 116, 46, 24);
		pnlForm.add(lblSTrang_1);
		
		txtGia = new JTextField();
		txtGia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtGia.setColumns(10);
		txtGia.setBounds(236, 116, 124, 24);
		pnlForm.add(txtGia);
		
		JLabel lblNhXutBn = new JLabel("Nhà xuất bản");
		lblNhXutBn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNhXutBn.setBounds(10, 186, 75, 24);
		pnlForm.add(lblNhXutBn);
		
		txtNhaXuatBan = new JTextField();
		txtNhaXuatBan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNhaXuatBan.setColumns(10);
		txtNhaXuatBan.setBounds(95, 186, 293, 24);
		pnlForm.add(txtNhaXuatBan);
		
		JLabel lblNmXutBn = new JLabel("Năm xuất bản");
		lblNmXutBn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNmXutBn.setBounds(10, 221, 81, 24);
		pnlForm.add(lblNmXutBn);
		
		cboNamXuatBan = new JComboBox();
		cboNamXuatBan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int yearToday  = localDate.getYear();
		//System.out.println(yearToday);
		for (int i = 1975; i <= yearToday; i++)
		{
			cboNamXuatBan.addItem(i);
		}
		cboNamXuatBan.setBounds(95, 221, 81, 24);
		pnlForm.add(cboNamXuatBan);
		
		JLabel lblGhiCh = new JLabel("Ghi chú");
		lblGhiCh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGhiCh.setBounds(10, 256, 75, 24);
		pnlForm.add(lblGhiCh);
		
		txtGhiChu = new JTextArea();
		txtGhiChu.setBorder(new LineBorder(SystemColor.inactiveCaption));
		txtGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtGhiChu.setBounds(95, 256, 293, 84);
		pnlForm.add(txtGhiChu);
		
		JLabel lblVn = new JLabel("VNĐ");
		lblVn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVn.setBounds(360, 116, 28, 24);
		pnlForm.add(lblVn);
		
		JLabel lblTcGi = new JLabel("Tác giả");
		lblTcGi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTcGi.setBounds(10, 151, 75, 24);
		pnlForm.add(lblTcGi);
		
		txtTacGia = new JTextField();
		txtTacGia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTacGia.setColumns(10);
		txtTacGia.setBounds(95, 151, 293, 24);
		pnlForm.add(txtTacGia);
		
		JLabel lblVTr = new JLabel("Vị trí để sách");
		lblVTr.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVTr.setBounds(194, 221, 75, 24);
		pnlForm.add(lblVTr);
		
		cboViTri = new JComboBox();
		cboViTri.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboViTri.setModel(new DefaultComboBoxModel(new String[] {"Kệ A1", "Kệ A2", "Kệ A3"}));
		cboViTri.setBounds(279, 221, 109, 24);
		pnlForm.add(cboViTri);
		
		JPanel pnlControllImage = new JPanel();
		pnlControllImage.setBounds(418, 244, 214, 61);
		contentPane.add(pnlControllImage);
		pnlControllImage.setBackground(SystemColor.menu);
		pnlControllImage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlControllImage.setLayout(null);
		
		JButton btnSelectPicture = new JButton("Chọn ảnh");
		btnSelectPicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				chooseImage();
			}
		});
		SwingHelper.setTextBelowIconButton(btnSelectPicture);
		btnSelectPicture.setBounds(10, 11, 85, 39);
		pnlControllImage.add(btnSelectPicture);
		
		JButton btnRemovePicture = new JButton("Xóa ảnh");
		btnRemovePicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				removeImage();
			}
		});
		btnRemovePicture.setBounds(115, 11, 89, 39);
		pnlControllImage.add(btnRemovePicture);
		
		JPanel pnlImage = new JPanel();
		pnlImage.setBackground(SystemColor.menu);
		pnlImage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlImage.setBounds(418, 11, 214, 235);
		contentPane.add(pnlImage);
		pnlImage.setLayout(null);
		
		lblImage = new JLabel("Chưa có ảnh");
		lblImage.setBounds(2, 2, 210, 231);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		pnlImage.add(lblImage);
		
		JButton btnConfirm = new JButton("Xác nhận");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					if(isEditMode == true)
					{
						if (updateBook())
						{
							//Tiến hành ghi file ảnh vào folder image sau khi insert Book thành công
							if (fileImage != null)
							{
								try 
								{
									writeImage(fileImage);
								} 
								catch (IOException e1) 
								{
									e1.printStackTrace();
								}
							}
							
							bookJFrame.getDataToList();
							bookJFrame.fillToTable();
							JOptionPane.showMessageDialog(getContentPane(), "UPDATE thành công!");
						}
						else 
						{
							JOptionPane.showMessageDialog(getContentPane(), "UPDATE thất bại");
						}
					}
					else if (isEditMode == false)
					{
						if (insertBook())
						{
							//Tiến hành ghi file ảnh vào folder image sau khi insert Book thành công
							if (fileImage != null)
							{
								try 
								{
									writeImage(fileImage);
								} 
								catch (IOException e1) 
								{
									e1.printStackTrace();
								}
							}
							
							//Sau đó đổ lại dữ liệu vào JFrame Book và fill ngược vào table
							bookJFrame.getDataToList();
							bookJFrame.fillToTable();
							JOptionPane.showMessageDialog(getContentPane(), "INSERT thành công!");
						}
						else 
						{
							JOptionPane.showMessageDialog(getContentPane(), "INSERT thất bại");
						}
					}
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConfirm.setIcon(new ImageIcon(BookEditorJDialog.class.getResource("/com/duan/icon/icons8_checked_50px.png")));
		btnConfirm.setBounds(418, 316, 214, 47);
		contentPane.add(btnConfirm);
		
		setLocationRelativeTo(getOwner());
		getDataToList();
		fillToCboCategory();
	}
	
	//Lấy dữ liệu từ DB về listCategory
	public void getDataToList()
	{
		try 
		{
			listCategory = CategoryDAO.getAll();
			listLocation = LocationDAO.getAll();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Đổ dữ liệu từ cboCategory vào cboTheLoai
	public void fillToCboCategory()
	{
		for (Category e : listCategory)
		{
			cboTheLoai.addItem(e.getCategoryTitle());
		}
		
		for (Location e : listLocation)
		{
			cboViTri.addItem(e.getLocationName());
		}
	}

	
	//Trả về một model Book từ các dữ liệu trên form
	public Book getBookFromForm()
	{
		String categoryId = listCategory.get(cboTheLoai.getSelectedIndex()).getId();
		int publicationYear = DataHelper.getInt(cboNamXuatBan.getItemAt(cboNamXuatBan.getSelectedIndex()).toString());
		String locationId = listLocation.get(cboViTri.getSelectedIndex()).getId();
		String imageName = null;
		Date createdDate = new Date();
		
		if (fileImage != null)
		{
			imageName = fileImage.getName();
		}
		else if (isEditMode) 
		{
			imageName = bookEdit.getImage();
			createdDate = bookEdit.getCreatedDate();
		}

		
		this.bookEdit.setId(txtMaSach.getText());
		this.bookEdit.setTitle(txtTenSach.getText());
		this.bookEdit.setCategoryId(categoryId);
		this.bookEdit.setPageNum(DataHelper.getInt(txtSoTrang.getText()));
		this.bookEdit.setPrice(DataHelper.getDouble(txtGia.getText()));
		this.bookEdit.setAuthor(txtTacGia.getText());
		this.bookEdit.setPublisher(txtNhaXuatBan.getText());
		this.bookEdit.setPublicationYear(publicationYear);
		this.bookEdit.setLocationId(locationId);
		this.bookEdit.setDescription(txtGhiChu.getText());
		this.bookEdit.setCreatedDate(createdDate);
		this.bookEdit.setImage(imageName);
		
		return this.bookEdit;
		
		
	}
	
	//Thực hiện ghi file ảnh vào source
	public void writeImage(File fileImage) throws IOException
	{
		//Chuyển file ảnh đang được chọn sang byte array
		byte[] imageArray = DataHelper.getArrayByteFromFile(fileImage);
		
		//sau đó tiến hành ghi byte array này ra file theo dường dẫn tới folder image
		DataHelper.writeFileToSource(imageArray, "/com/duan/image/" + fileImage.getName());
	}
	
	//Cập nhật lại book editor thành book, chuyển chế độ editMode = true
	public void setBookEditor(Book book) throws SQLException
	{
		setTitle("Cập nhật sách");
		isEditMode = true;
		this.bookEdit = book;
		
		txtMaSach.setEnabled(false);
		
		if (book.getImage() != null)
		{
			try 
			{
				setImage(book.getImage());
			} 
			catch (NullPointerException e) 
			{
				removeImage();
			}
		}
	}
	
	//Set BookJFrame để sau khi update xong thì có thể gọi hàm fillToTable bên bookJframe bênt kia
	public void setBookJFrame(BookJFrame bookJFrame)
	{
		this.bookJFrame = bookJFrame;
	}
	
	//Thực hiện thêm model book vào database
	public boolean insertBook() throws SQLException
	{
		Book book = getBookFromForm();
		return BookDAO.insert(book);
	}
	
	//Thựch hiện update lại model book vào database
	public boolean updateBook() throws SQLException
	{
		Book book = getBookFromForm();
		return BookDAO.update(book, book.getId());
	}
	
	//Hiển thị các thông tin của model Book lên form
	public void showDataToForm(Book book) throws SQLException
	{
		String categoryTitle = CategoryDAO.getTitleById(book.getCategoryId());
		String locationName = LocationDAO.findByID(book.getLocationId()).getLocationName();
		txtMaSach.setText(book.getId());
		txtTenSach.setText(book.getTitle());
		txtTacGia.setText(book.getAuthor());
		txtNhaXuatBan.setText(book.getPublisher());
		//txtSoLuong.setText(book.getAmount() + "");
		txtSoTrang.setText(book.getPageNum() + "");
		txtGia.setText(book.getPrice() + "");
		txtGhiChu.setText(book.getDescription());
		cboNamXuatBan.setSelectedItem(book.getPublicationYear());
		cboTheLoai.setSelectedItem(categoryTitle);
		cboViTri.setSelectedItem(locationName);
		
		//Xử lý ảnh
		if (book.getImage() != null)
			setImage(book.getImage());
		else 
			removeImage();
	}
	
	
	public void chooseImage()
	{
		JFileChooser chooser = new JFileChooser();
		int status = chooser.showOpenDialog(this);
		
		//Đã chọn 1 file nào đó
		if (status == JFileChooser.APPROVE_OPTION)
		{
			File fileImage = chooser.getSelectedFile();
			//Kiểm tra xem nó có phải là file ảnh không
			if (DataHelper.checkFileExtension(fileImage.getName(), DataHelper.EXTENSIONS_FILE_IMAGE))
			{
				this.fileImage = fileImage;
				setImage(fileImage);
				lblImage.setText("");
			}
			else
			{
				//Xử lý không đúng file ảnh!
			}
		}
	}
	
	//Cập nhật lại icon cho lblImage = file vừa truyền vào
	public void setImage(File file)
	{
		ImageIcon icon = new ImageIcon(file.getAbsolutePath());
		lblImage.setIcon(icon);
		SwingHelper.setAutoResizeIcon(lblImage);
	}
	
	//Cập nhật lại icon cho lblImage lấy ảnh từ folder image có nameFile.
	public void setImage(String imageName)
	{
		if (imageName != null && imageName.length() > 0)
		{
			ImageIcon icon = new ImageIcon(DataHelper.getFileFromSource("/com/duan/image/" + imageName).getAbsolutePath());
			lblImage.setIcon(icon);
			SwingHelper.setAutoResizeIcon(lblImage);
		}
		else
		{
			removeImage();
		}

	}
	
	//Xóa icon lblImage và set fileImage = null
	public void removeImage()
	{
		lblImage.setText("Chưa có ảnh");
		lblImage.setIcon(null);
		this.fileImage = null;
	}
	
	
	public void showCategoryJFrame()
	{
		categoryJDialog.setVisible(true);
	}
}
