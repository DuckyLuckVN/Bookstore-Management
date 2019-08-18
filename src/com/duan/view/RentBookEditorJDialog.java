package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import com.duan.custom.common.JTableBlue;
import com.duan.custom.message.MessageOptionPane;
import com.duan.dao.BookDAO;
import com.duan.dao.RentBookDAO;
import com.duan.dao.RentBookDetailDAO;
import com.duan.dao.UserDAO;
import com.duan.helper.AccountSave;
import com.duan.helper.DataHelper;
import com.duan.helper.DateHelper;
import com.duan.helper.SettingSave;
import com.duan.model.Book;
import com.duan.model.BookProduct;
import com.duan.model.RentBook;
import com.duan.model.User;
import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RentBookEditorJDialog extends JDialog {

	private JPanel contentPane;
	private JTextField txtTaiKhoang;
	private JTextField txtHoTen;
	private JTextField txtEmail;
	private JTextField txtSDT;
	private JTextField txtNgaySinh;
	private JTextField txtSoLuong;
	private JTableBlue tblBook;
	private JTextField txtMaTaiKhoang;
	private JButton btnConfirm;
	private JComboBox cboStatus;
	private JButton btnSelectBook;
	private JButton btnDeleteBook;

	private SelectUserJDialog selectUserJDialog = new SelectUserJDialog();
	private SelectBookJDialog selectBookJDialog = new SelectBookJDialog();
	private BookDetailJDialog bookDetailJFrame = new BookDetailJDialog();
	private RentBookJFrame rentBookJFrame;
	private boolean isEditMode = false;
	
	private RentBook rentBookEdit;
	private User userSelected;
	private List<BookProduct> listBookProduct = new ArrayList<BookProduct>();
	private List<BookProduct> listBookProductEdit = new ArrayList<BookProduct>();
	private JLabel lblTotalCostRent;
	private JLabel lblPhiPhatQuaHan;
	private JLabel lblTotalCostExpiration;
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					RentBookEditorJDialog frame = new RentBookEditorJDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public RentBookEditorJDialog(RentBookJFrame rentBookJFrame)
	{
		this();
		setLocationRelativeTo(rentBookJFrame);
	}

	public RentBookEditorJDialog() 
	{
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RentBookEditorJDialog.class.getResource("/com/duan/icon/icons8_edit_property_50px.png")));
		setTitle("Thêm thuê sách");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 608, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTKThue = new JLabel("Tài khoảng thuê:");
		lblTKThue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTKThue.setBounds(10, 48, 104, 26);
		contentPane.add(lblTKThue);
		
		txtTaiKhoang = new JTextField();
		txtTaiKhoang.setForeground(new Color(255, 0, 0));
		txtTaiKhoang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTaiKhoang.setEditable(false);
		txtTaiKhoang.setBounds(124, 50, 369, 26);
		contentPane.add(txtTaiKhoang);
		txtTaiKhoang.setColumns(10);
		
		JButton btnSelectUser = new JButton("Chọn User");
		btnSelectUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSelectUser();
			}
		});
		btnSelectUser.setBounds(503, 13, 89, 209);
		contentPane.add(btnSelectUser);
		
		JLabel lblHo = new JLabel("Họ tên:");
		lblHo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHo.setBounds(10, 85, 104, 26);
		contentPane.add(lblHo);
		
		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtHoTen.setEditable(false);
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(124, 85, 369, 26);
		contentPane.add(txtHoTen);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(124, 122, 369, 26);
		contentPane.add(txtEmail);
		
		JLabel label = new JLabel("Email:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 122, 104, 26);
		contentPane.add(label);
		
		JLabel lblSoDT = new JLabel("Số điện thoại:");
		lblSoDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSoDT.setBounds(10, 159, 104, 26);
		contentPane.add(lblSoDT);
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSDT.setEditable(false);
		txtSDT.setColumns(10);
		txtSDT.setBounds(124, 159, 369, 26);
		contentPane.add(txtSDT);
		
		txtNgaySinh = new JTextField();
		txtNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNgaySinh.setEditable(false);
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(124, 196, 369, 26);
		contentPane.add(txtNgaySinh);
		
		JLabel lblNgySinh = new JLabel("Ngày sinh:");
		lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgySinh.setBounds(10, 196, 104, 26);
		contentPane.add(lblNgySinh);
		
		JLabel lblSachChoThue = new JLabel("Sách cho thuê:");
		lblSachChoThue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSachChoThue.setBounds(10, 233, 104, 26);
		contentPane.add(lblSachChoThue);
		
		btnSelectBook = new JButton("Thêm sách");
		btnSelectBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSelectBook();
			}
		});
		btnSelectBook.setBounds(404, 233, 89, 26);
		contentPane.add(btnSelectBook);
		
		btnConfirm = new JButton("Xác nhận");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					if (validateAll())
					{
						//Nếu không phải là edit mode thì tiến hành insert
						if (isEditMode == false)
						{
							insertRentbook();
						}
						else
						{
							updateRentBook();
						}
					}
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		btnConfirm.setBounds(378, 456, 214, 36);
		contentPane.add(btnConfirm);
		
		JLabel lblTinhTrang = new JLabel("Tình trạng:");
		lblTinhTrang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTinhTrang.setBounds(300, 419, 68, 26);
		contentPane.add(lblTinhTrang);
		
		cboStatus = new JComboBox();
		cboStatus.setEnabled(false);
		cboStatus.setModel(new DefaultComboBoxModel(new String[] {"Đang thuê", "Đã trả sách"}));
		cboStatus.setBounds(378, 419, 214, 26);
		contentPane.add(cboStatus);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setForeground(Color.BLUE);
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSoLuong.setText("0 Quyển");
		txtSoLuong.setEditable(false);
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(124, 233, 270, 26);
		contentPane.add(txtSoLuong);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 270, 582, 138);
		contentPane.add(scrollPane);
		
		tblBook = new JTableBlue();
		tblBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (e.getClickCount() >= 2)
				{
					try 
					{
						showBookDetail();
					} 
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					}
				}
			}
		});
		tblBook.setModel(new DefaultTableModel(null, new String[] {"MÃ SÁCH", "TÊN SÁCH", "GIÁ BÁN", "SỐ LƯỢNG", "XÓA"}) {
			
			//Column = 4 -> cột "XÓA"
			public boolean isCellEditable(int row, int column) {
				if (column == 4 || column == 3)
				{
					return true;
				}
				
				return false;
			}
			
			//Trả về dạng checkbox với cột thứ 4 ("XÓA")
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 4)
				{
					return Boolean.class;
				}
				return super.getColumnClass(columnIndex);
			}
		});
		
		//Bắt sư 5kiện các giá trị trong bảng bị thay đổi
		tblBook.getModel().addTableModelListener(new TableModelListener() 
		{
			@Override
			public void tableChanged(TableModelEvent e) 
			{
				try 
				{
					onTableUpdate(e);
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
			
		});
		
		tblBook.setRowHeight(25);
		tblBook.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblBook.getColumnModel().getColumn(1).setPreferredWidth(200);;
		scrollPane.setViewportView(tblBook);
		
		btnDeleteBook = new JButton("Xóa sách");
		btnDeleteBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				deleteBook();
				updateAmountNumber();
				updateTotalCostRent();
			}
		});
		btnDeleteBook.setBounds(503, 235, 89, 26);
		contentPane.add(btnDeleteBook);
		
		JLabel lblMSTi = new JLabel("ID Tài khoảng:");
		lblMSTi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMSTi.setBounds(10, 13, 104, 26);
		contentPane.add(lblMSTi);
		
		txtMaTaiKhoang = new JTextField();
		txtMaTaiKhoang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaTaiKhoang.setEditable(false);
		txtMaTaiKhoang.setColumns(10);
		txtMaTaiKhoang.setBounds(124, 15, 369, 26);
		contentPane.add(txtMaTaiKhoang);
		
		JLabel lblTngPhThu = new JLabel("Tổng phí thuê:");
		lblTngPhThu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTngPhThu.setBounds(10, 419, 91, 26);
		contentPane.add(lblTngPhThu);
		
		lblTotalCostRent = new JLabel("0đ");
		lblTotalCostRent.setForeground(Color.BLUE);
		lblTotalCostRent.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotalCostRent.setBounds(111, 419, 191, 26);
		contentPane.add(lblTotalCostRent);
		
		lblPhiPhatQuaHan = new JLabel("Phí phạt quá hạn:");
		lblPhiPhatQuaHan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPhiPhatQuaHan.setBounds(10, 456, 109, 26);
//		contentPane.add(lblPhiPhatQuaHan);
		
		lblTotalCostExpiration = new JLabel("0đ");
		lblTotalCostExpiration.setForeground(Color.RED);
		lblTotalCostExpiration.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotalCostExpiration.setBounds(129, 456, 191, 26);
//		contentPane.add(lblTotalCostExpiration);
	}
	
	//Khi sự kiện tableChanged dc gọi, thì nó sẽ chạy vào hàm này
	public void onTableUpdate(TableModelEvent e) throws SQLException
	{
		//Nếu sự kiện là update thì mới bắt
		if (e.getType() == TableModelEvent.UPDATE)
		{
			//Nếu cột được update là Số Lượng thì cập nhật lại thông tin số lượng
			if (e.getColumn() == 3)
			{
				if (checkAmountAt(e.getFirstRow()))
				{
					updateAmountNumber();		
					updateTotalCostRent();
				}
			}
		}
	}
	
	public void fillToTable()
	{
		DefaultTableModel model = (DefaultTableModel) tblBook.getModel();
		model.setRowCount(0);
		
		for (BookProduct product : listBookProduct)
		{
			Object[] rowData = {product.getBook().getId(), product.getBook().getTitle(), product.getPrice(), product.getAmount(), false};
			model.addRow(rowData);
		}
	}
	
	//Kiểm tra dữ liệu số lượng tại row có hợp lệ hay không
	public boolean checkAmountAt(int row)
	{
		String amount_temp = tblBook.getValueAt(row, 3).toString();
		
		//Kiểm tra xem có phải là số hay không?
		if (DataHelper.isInteger(amount_temp))
		{
			int amount = DataHelper.getInt(amount_temp);
			if (amount > 0)
			{
				return true;
			}
			else
			{
				MessageOptionPane.showAlertDialog(contentPane, "Số lượng nhập vào phải lớn hơn 0!", MessageOptionPane.ICON_NAME_WARNING);
				return false;
			}
		}
		else
		{
			MessageOptionPane.showAlertDialog(contentPane, "Số lượng nhập vào phải là số!", MessageOptionPane.ICON_NAME_WARNING);
			return false;
		}
	}
	
	public void showSelectUser()
	{
		selectUserJDialog.setLocationRelativeTo(this);
		selectUserJDialog.openSelectDialog();
		User userTemp;
		//Kiểm tra nếu như đã có user được chọn thì lấy user đó về lưu vào userSelect
		if (selectUserJDialog.getStatus() == selectUserJDialog.STATUS_SELECTED)
		{
			userTemp = selectUserJDialog.getUserSelected();
			if (userTemp.isActive() == true)
			{
				userSelected = userTemp;
				showUserDetail(userSelected);
			}
			else
			{
				MessageOptionPane.showAlertDialog(this, "Tài khoảng '" + userTemp.getUsername() + "' đang bị khóa, không thể giao dịch!");
			}
		}
	}
	
	public void showSelectBook()
	{
		selectBookJDialog.setLocationRelativeTo(this);
		selectBookJDialog.openSelectJDialog();
		
		//Kiểm tra sách đã được chọn
		if (selectBookJDialog.getStatus() == selectBookJDialog.STATUS_SELECTED)
		{
			listBookProduct = selectBookJDialog.getListBookProductSelected();
			fillToTable();
			updateAmountNumber();
			updateTotalCostRent();
		}
	}
	
	public void showBookDetail() throws SQLException
	{
		bookDetailJFrame.setLocationRelativeTo(this);
		String bookId = tblBook.getValueAt(tblBook.getSelectedRow(), 0).toString();
		Book book = BookDAO.findByID(bookId);
				
		bookDetailJFrame.setDetail(book);
		bookDetailJFrame.setVisible(true);
	}
	
	
	//Thực hiện insert dữ liệu vào bảng Rentbook
	public void insertRentbook() throws SQLException
	{
		double costRent = SettingSave.getSetting().getCostRentBook();
		double costExpiration = SettingSave.getSetting().getCostRentExpiration();
		int expirationDay = SettingSave.getSetting().getDayExpiration();
		
		RentBook rb = new RentBook(0, userSelected.getId(), AccountSave.getAdmin().getId(), costRent, costExpiration, expirationDay, new Date(), null, 0);
		boolean isSuccess = RentBookDAO.insert(rb, getListBookProduct());
		
		if (isSuccess)
		{
			rentBookJFrame.getDataToList();
			rentBookJFrame.fillToTable();
			dispose();
			MessageOptionPane.showAlertDialog(this, "Thêm phiếu thuê sách thành công!", MessageOptionPane.ICON_NAME_SUCCESS);
		}
	}
	
	//Thực hiện update dữ liệu vào bảng Rentbook
	public void updateRentBook() throws SQLException
	{
		//status: 0 - Đang Thuê, 1 - Đã trả sách
		int status = cboStatus.getSelectedIndex();
		
		//Status = 1 -> đã trả sách, cập nhật lại ngày trả sách
		if (status == 1)
		{
			rentBookEdit.setReturnedDate(new Date());
		}
		rentBookEdit.setUserId(userSelected.getId());
		rentBookEdit.setStatus(status);
		
		boolean isSuccess = RentBookDAO.update(rentBookEdit, getListBookProduct());
		if (isSuccess)
		{
			rentBookJFrame.getDataToList();
			rentBookJFrame.fillToTable();
			dispose();
			MessageOptionPane.showAlertDialog(this, "Đã cập nhật lại phiếu thuê sách '" + rentBookEdit.getId() + "' thành công!", MessageOptionPane.ICON_NAME_SUCCESS);
		}
	}
	
	//Cập nhật lại tổng số lượng sách
	public void updateAmountNumber()
	{
		txtSoLuong.setText(getQualityNumber() + " quyển");
	}
	
	//Cập nhật lại giá thuê
	public void updateTotalCostRent()
	{
		double costRent = (rentBookEdit != null) ? rentBookEdit.getCostRent() : SettingSave.getSetting().getCostRentBook();
		String totalCostRentStr = DataHelper.getFormatForMoney(costRent * getQualityNumber());
		lblTotalCostRent.setText(totalCostRentStr + SettingSave.getSetting().getMoneySymbol());
	}
	
	//Cập nhật lại phí quá hạn (nếu có)
	public void updateTotalCostExpiration()
	{
		//Kiểm tra không null và tình trạng phải khác = 1 (status = 1 là đã trả sách)
		if (rentBookEdit != null && rentBookEdit.getStatus() != 1)
		{
			//Kiểm tra nếu ngày tạo đơn thuê đến bây giờ mà lớn hơn số ngày quá hạn quy định thì mới update
			if (DateHelper.getDayBetweenTwoDate(rentBookEdit.getCreatedDate(), new Date()) > rentBookEdit.getExpirationDay())
			{
				int totalDayExpiration = DateHelper.getDayBetweenTwoDate(rentBookEdit.getCreatedDate(), new Date()) - rentBookEdit.getExpirationDay()
						;
				//Tổng phí phạt quá hạn = <số ngày quá hạn> * <phí phạt quá hạn> * <tổng số sách>
				double totalCostExpiration = totalDayExpiration * rentBookEdit.getCostExpiration() * getQualityNumber();
				lblTotalCostExpiration.setText(DataHelper.getFormatForMoney(totalCostExpiration) + SettingSave.getSetting().getMoneySymbol());
			}
		}
	}
	
	//Trả về tổng số lượng sách đang có trong bảng
	public int getQualityNumber()
	{
		int rowCount = tblBook.getRowCount();
		int sum = 0;
		//Duyệt hết phần tử trong bảng, lấy ra số lượng và + lại -> ra được tổng sách
		for (int i = 0; i < rowCount; i++)
		{
			String amount_temp = tblBook.getValueAt(i, 3).toString();
			if (DataHelper.isInteger(amount_temp))
			{
				int amount = DataHelper.getInt(amount_temp);
				sum += amount;
			}
		}
		return sum;
	}
	
	//Thực hiện kiểm tra từng dòng trong bảng và xóa các book bị đánh dấu xóa
	public void deleteBook()
	{
		int rowCount = tblBook.getRowCount();
		DefaultTableModel model = (DefaultTableModel) tblBook.getModel();

		for (int i = rowCount - 1; i >= 0; i--)
		{
			boolean isChecked = (boolean) tblBook.getValueAt(i, 4);
			if (isChecked)
			{
				model.removeRow(i);
				listBookProduct.remove(i);
			}
		}
	}
	
	//Tiến hành kiểm tra tất cả giá trị trong JDialog, trả về TRUE nếu hợp lệ, FALSE nếu không hợp lệ.
	public boolean validateAll() throws SQLException
	{
		boolean isSuccess = true;
		String msg = "";
		int rowCount = tblBook.getRowCount();
		
		//CHECK - USER
		if (userSelected == null)
		{
			msg += "+ Bạn chưa chọn tài khoảng thuê\n";
			isSuccess = false;
		}
		
		//CHECK - BOOK SELECT COUNT
		if (rowCount == 0)
		{
			msg += "+ Bạn chưa chọn sách thuê\n";
			isSuccess = false;
		}
		
		//Duyệt từng dòng trong bảng và kiểm tra
		for (int i = 0; i < rowCount; i++)
		{
			String bookId = tblBook.getValueAt(i, 0).toString();
			int amountAvailable = BookDAO.getAmountAvailable(bookId);
			
			//Nôi dung của cột số lượng trong bảng
			String amount_temp_str = tblBook.getValueAt(i, 3).toString();
			
			//CHECK - SỐ LƯỢNG SÁCH
			if (DataHelper.isInteger(amount_temp_str) && DataHelper.getInt(amount_temp_str) > 0)
			{
				//Nếu đây không phải là chế độ cập nhật thì kiểm tra kiểu này
				if (isEditMode == false)
				{
					//Ép kiểu về integer cho số lượng
					int amount_temp = DataHelper.getInt(amount_temp_str);
					
					//Số sách nhập vào lớn hơn số sách đang có
					if (amount_temp > amountAvailable)
					{
						msg += "[" + bookId + "] Số sách còn lại không đủ đáp ứng cho " + amount_temp + " quyển (còn " + amountAvailable + " quyển)\n";
						isSuccess = false;
					}
				}
				else //Nếu đây là chế độ cập nhật thì phải kiểm tra kiểu này
				{
					//Ép kiểu về integer cho số lượng
					int amount_temp = DataHelper.getInt(amount_temp_str);
					List<BookProduct> list_temp = getListBookProduct();

					for (BookProduct product : RentBookDetailDAO.getListProducts(rentBookEdit.getId()))
					{
						//Nếu mã sách trên table trùng với 1 mã sách trong rentbookDetail thì kiểm tra số lượng hợp lệ
						if (product.getBook().getId().equals(bookId))
						{
							//Kiểm tra nếu như số lượng sách trong kho sau khi đơn thuê cập nhật số lượng mà nhỏ hơn 0 thì không cho phép
							/*
							 * VD: 
							 * - Đơn thuê sách có id = Sach1 đang thuê 10 quyển
							 * - Kho sách có id = Sach1 đang có 10 quyển
							 * - Đơn thuê sau khi cập nhật có sách id = Sach1 sẽ thuê thành 25 quyển
							 * vậy => tức là sẽ phải cần thêm 15 quyển nữa trong kho để đưa cho khách thuê
							 * Nhưng trong kho chỉ còn 10 quyển nên ko cho phép
							 * 
							 * CT tính: amountAfterUpdate = (số_sách_đang_có + số_sách_đang_thuê) - số_sách_sau_khi_update_số_lượng 
							 * 
							 * Nếu: amountAfterUpdate lớn hơn hoặc = 0 thì OK, nhỏ hơn 0 thì không cho phép
							 */
							int amountAfterUpdate = (amountAvailable + product.getAmount()) - amount_temp;
							if (amountAfterUpdate < 0)
							{
								msg += "[" + bookId + "] Số lượng sách hiện có không đủ đáp ứng thêm +" + (amount_temp - product.getAmount()) + " quyển (còn " + amountAvailable + " quyển)\n";
								isSuccess = false;
							}
							break;
						}
						else
						{
							//Số sách nhập vào lớn hơn số sách đang có
							if (amount_temp > amountAvailable)
							{
								msg += "[" + bookId + "] Số sách còn lại không đủ đáp ứng cho " + amount_temp + " quyển (còn " + amountAvailable + " quyển)\n";
								isSuccess = false;
							}
						}
					}
				}
			}
			else
			{
				msg += "[" + bookId + "] Số lượng sách nhập vào phải là số và lớn hơn 0\n";
				isSuccess = false;
			}
		}
		
		if (isSuccess == false)
		{
			MessageOptionPane.showMessageDialog(this, "Đã có lỗi sảy ra:\n" + msg, MessageOptionPane.ICON_NAME_WARNING);
		}
		
		return isSuccess;
	}

	
	//Hiển thị thông tin user truyền vào lên form
	public void showUserDetail(User user)
	{
		userSelected = user;
		String ngaySinh = DateHelper.dateToString(user.getDateOfBirth(), "dd/MM/yyyy");
		txtMaTaiKhoang.setText(user.getId() + "");
		txtTaiKhoang.setText(user.getUsername());
		txtHoTen.setText(user.getFullname());
		txtNgaySinh.setText(ngaySinh);
		txtSDT.setText(user.getPhoneNumber());
		txtEmail.setText(user.getEmail());
	}
	
	//Thực hiện set editMode
	public void setEditMode(boolean status)
	{
		isEditMode = status;
		if (isEditMode == false)
		{
			cboStatus.setEnabled(false);
		}
		else
		{
			setTitle("Chỉnh Sửa Đơn Thuê | Mã Số: " + this.rentBookEdit.getId());
			btnSelectBook.setEnabled(false);
			btnDeleteBook.setEnabled(false);
		}
	}
	
	//Trả về danh sách Book Product trên bảng đang có
	public List<BookProduct> getListBookProduct()
	{
		List<BookProduct> list = new ArrayList<BookProduct>();
		for (int i = 0; i < listBookProduct.size(); i++)
		{
			Book book = listBookProduct.get(i).getBook();
			int amount = DataHelper.getInt(tblBook.getValueAt(i, 3).toString());
			BookProduct bookProduct = new BookProduct(book, amount, listBookProduct.get(i).getPrice());
			list.add(bookProduct);
		}
		System.out.println(list.size());
		return list;
	}
	
	public void setEditModel(RentBook rentBook)
	{
		this.rentBookEdit = rentBook;
		DefaultTableModel model = (DefaultTableModel) tblBook.getModel();
		model.setRowCount(0);
		
		try 
		{
			//Hiển thị thông tin người thuê
			this.userSelected = UserDAO.findByID(rentBook.getUserId());
			showUserDetail(userSelected);
			
			//Hiển thị lại trạng thái cho cboStatus
			cboStatus.setSelectedIndex(rentBook.getStatus());
			
			//Nếu đã trả rồi thì khóa cboStatus lại
			if (rentBook.getStatus() == 1)
			{
				cboStatus.setEnabled(false);
				if (DateHelper.getDayBetweenTwoDate(rentBook.getCreatedDate(), rentBook.getReturnedDate()) > rentBook.getExpirationDay())
				{
					contentPane.add(lblPhiPhatQuaHan);
					contentPane.add(lblTotalCostExpiration);
				}
			}
			else
			{
				if (DateHelper.getDayBetweenTwoDate(rentBook.getCreatedDate(), new Date()) > rentBook.getExpirationDay())
				{
					contentPane.add(lblPhiPhatQuaHan);
					contentPane.add(lblTotalCostExpiration);
				}
				cboStatus.setEnabled(true);
			}
			
			//Nếu hóa đơn này đã quá hạn thì hiển thị phí phạt lên
			
			//Lấy về danh sách các sách chi tiết dựa vào mã số thuê @rentbook_id
			this.listBookProduct = RentBookDetailDAO.getListProducts(rentBook.getId());
			
			fillToTable();
			updateAmountNumber();
			updateTotalCostRent();
			updateTotalCostExpiration();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void setRentBookJFrame(RentBookJFrame jframe)
	{
		this.rentBookJFrame = jframe;
	}
}
