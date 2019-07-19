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
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.duan.dao.BookDAO;
import com.duan.helper.DataHelper;
import com.duan.helper.DateHelper;
import com.duan.model.Book;
import com.duan.model.RentBook;
import com.duan.model.User;
import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RentBookEditorJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtTaiKhoang;
	private JTextField txtHoTen;
	private JTextField txtEmail;
	private JTextField txtSDT;
	private JTextField txtNgaySinh;
	private JTextField txtSoLuong;
	private JTable tblBook;
	private JTextField txtMaTaiKhoang;

	private SelectUserJDialog selectUserJDialog = new SelectUserJDialog();
	private SelectBookJDialog selectBookJDialog = new SelectBookJDialog();
	private BookDetailJFrame bookDetailJFrame = new BookDetailJFrame();
	private RentBookJFrame rentBookJFrame;
	private boolean isEditMode = false;
	
	private RentBook rentBook;
	private User userSelect;
	private List<Book> listBook = new ArrayList<Book>();
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					RentBookEditorJFrame frame = new RentBookEditorJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public RentBookEditorJFrame(RentBookJFrame rentBookJFrame)
	{
		this();
		setLocationRelativeTo(rentBookJFrame);
	}

	public RentBookEditorJFrame() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RentBookEditorJFrame.class.getResource("/com/duan/icon/icons8_edit_property_50px.png")));
		setTitle("Thêm thuê sách");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 447, 538);
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
		txtTaiKhoang.setBounds(124, 50, 214, 26);
		contentPane.add(txtTaiKhoang);
		txtTaiKhoang.setColumns(10);
		
		JButton btnSelectUser = new JButton("Chọn User");
		btnSelectUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSelectUser();
			}
		});
		btnSelectUser.setBounds(348, 13, 89, 209);
		contentPane.add(btnSelectUser);
		
		JLabel lblHo = new JLabel("Họ tên:");
		lblHo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHo.setBounds(10, 85, 104, 26);
		contentPane.add(lblHo);
		
		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtHoTen.setEditable(false);
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(124, 85, 214, 26);
		contentPane.add(txtHoTen);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(124, 122, 214, 26);
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
		txtSDT.setBounds(124, 159, 214, 26);
		contentPane.add(txtSDT);
		
		txtNgaySinh = new JTextField();
		txtNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNgaySinh.setEditable(false);
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(124, 196, 214, 26);
		contentPane.add(txtNgaySinh);
		
		JLabel lblNgySinh = new JLabel("Ngày sinh:");
		lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgySinh.setBounds(10, 196, 104, 26);
		contentPane.add(lblNgySinh);
		
		JLabel lblSachChoThue = new JLabel("Sách cho thuê:");
		lblSachChoThue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSachChoThue.setBounds(10, 233, 104, 26);
		contentPane.add(lblSachChoThue);
		
		JButton btnSelectBook = new JButton("Chọn sách");
		btnSelectBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSelectBook();
			}
		});
		btnSelectBook.setBounds(249, 233, 89, 26);
		contentPane.add(btnSelectBook);
		
		JButton btnConfirm = new JButton("Xác nhận");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					validateTable();
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		btnConfirm.setBounds(10, 462, 427, 36);
		contentPane.add(btnConfirm);
		
		JLabel lblTinhTrang = new JLabel("Tình trạng:");
		lblTinhTrang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTinhTrang.setBounds(10, 419, 68, 26);
		contentPane.add(lblTinhTrang);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Đang thuê", "Đã trả sách", "Mất sách"}));
		comboBox.setBounds(88, 419, 214, 26);
		contentPane.add(comboBox);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setForeground(Color.BLUE);
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSoLuong.setText("0 Quyển");
		txtSoLuong.setEditable(false);
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(124, 233, 115, 26);
		contentPane.add(txtSoLuong);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 270, 421, 138);
		contentPane.add(scrollPane);
		
		tblBook = new JTable();
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
		
		JButton btnDeleteBook = new JButton("Xóa sách");
		btnDeleteBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				deleteBook();
				updateQualityNumber();
			}
		});
		btnDeleteBook.setBounds(348, 233, 89, 26);
		contentPane.add(btnDeleteBook);
		
		JLabel lblMSTi = new JLabel("Mã số:");
		lblMSTi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMSTi.setBounds(10, 13, 104, 26);
		contentPane.add(lblMSTi);
		
		txtMaTaiKhoang = new JTextField();
		txtMaTaiKhoang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaTaiKhoang.setEditable(false);
		txtMaTaiKhoang.setColumns(10);
		txtMaTaiKhoang.setBounds(124, 15, 214, 26);
		contentPane.add(txtMaTaiKhoang);
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
				
				updateQualityNumber();
			}
		}
	}
	
	public void showSelectUser()
	{
		selectUserJDialog.setLocationRelativeTo(this);
		selectUserJDialog.openSelectDialog();
		
		//Kiểm tra nếu như đã có user được chọn thì lấy user đó về lưu vào userSelect
		if (selectUserJDialog.getStatus() == selectUserJDialog.STATUS_SELECTED)
		{
			userSelect = selectUserJDialog.getUserSelected();
			showUserDetail(userSelect);
		}
	}
	
	public void showSelectBook()
	{
		selectBookJDialog.setLocationRelativeTo(this);
		selectBookJDialog.openSelectJDialog();
		
		//Kiểm tra sách đã được chọn
		if (selectBookJDialog.getStatus() == selectBookJDialog.STATUS_SELECTED)
		{
			listBook = selectBookJDialog.getListBookSelected();
			fillToTable();
			updateQualityNumber();
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
	
	public void fillToTable()
	{
		DefaultTableModel model = (DefaultTableModel) tblBook.getModel();
		model.setRowCount(0);
		
		for (Book book : listBook)
		{
			Object[] rowData = {book.getId(), book.getTitle(), book.getPrice(), 1, false};
			model.addRow(rowData);
		}
		
	}
	
	//Cập nhật lại tổng số lượng sách
	public void updateQualityNumber()
	{
		
		txtSoLuong.setText(getQualityNumber() + " quyển");
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
				listBook.remove(i);
			}
		}
	}
	
	//Tiến hành kiểm tra tất cả giá trị trong bảng sách, trả về TRUE nếu hợp lệ, FALSE nếu không hợp lệ.
	public boolean validateTable() throws SQLException
	{
		boolean isSuccess = true;
		String msg = "";
		int rowCount = tblBook.getRowCount();
		
		//CHECK - USER
		if (userSelect != null)
		{
			if (rowCount > 0)
			{
				//Duyệt từng dòng trong bảng và kiểm tra
				for (int i = 0; i < rowCount; i++)
				{
					String bookId = tblBook.getValueAt(i, 0).toString();
					int amountAvailable = BookDAO.getAmountAvailable(bookId);
					String amountInput_temp = tblBook.getValueAt(i, 3).toString();
					int amountInput;
					
					//CHECK - SỐ LƯỢNG SÁCH
					if (DataHelper.isInteger(amountInput_temp))
					{
						//Ép kiểu về integer cho số lượng
						amountInput = DataHelper.getInt(amountInput_temp);
						
						//Số sách nhập vào lớn hơn số sách đang có
						if (amountInput > amountAvailable)
						{
							msg += "[" + bookId + "] Số sách còn lại không đủ đáp ứng cho " + amountInput + " quyển (còn " + amountAvailable + " quyển)\n";
							isSuccess = false;
						}
					}
					else
					{
						msg += "[" + bookId + "] Số lượng sách nhập vào phải là số\n";
						isSuccess = false;
					}
				}
			}
			else
			{
				msg += "+ Bạn chưa chọn sách thuê\n";
				isSuccess = false;
			}
		}
		else
		{
			msg += "+ Bạn chưa chọn tài khoảng thuê\n";
			isSuccess = false;
		}
		
		if (isSuccess == false)
		{
			JOptionPane.showMessageDialog(this, "Đã có lỗi sảy ra:\n" + msg);
		}
		
		return isSuccess;
	}

	
	//Hiển thị thông tin user truyền vào lên form
	public void showUserDetail(User user)
	{
		String ngaySinh = DateHelper.dateToString(user.getDateOfBirth(), "dd/MM/yyyy");
		txtMaTaiKhoang.setText(user.getId() + "");
		txtTaiKhoang.setText(user.getUsername());
		txtHoTen.setText(user.getFullname());
		txtNgaySinh.setText(ngaySinh);
		txtSDT.setText(user.getPhoneNumber());
		txtEmail.setText(user.getEmail());
	}
	
	public void setRentBookJFrame(RentBookJFrame rentBookJFrame)
	{
		this.rentBookJFrame = rentBookJFrame;
	}
}
