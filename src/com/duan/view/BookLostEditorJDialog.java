package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;

import com.duan.dao.AdminDAO;
import com.duan.dao.BookLostDAO;
import com.duan.dao.BookLostDetailDAO;
import com.duan.dao.RentBookDAO;
import com.duan.dao.RentBookDetailDAO;
import com.duan.dao.UserDAO;
import com.duan.helper.AccountSave;
import com.duan.helper.DataHelper;
import com.duan.model.Book;
import com.duan.model.BookLost;
import com.duan.model.BookProduct;
import com.duan.model.RentBook;
import com.duan.model.User;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class BookLostEditorJDialog extends JDialog {

	public JPanel contentPane;
	private JTextField txtNguoiThue;
	private JTextField txtTaiKhoan;
	private JTextField txtTongThue;
	private JTextField txtAdmin;
	private JTable tblBook;
	private JComboBox cboRentBookId;
	private JLabel lblTotalCost;
	
	private boolean isEditMode = false;
	
	private BookLostJFrame bookLostJFrame;
	private SelectBookJDialog selectBookJDialog = new SelectBookJDialog();
	
	private BookLost bookLostEdit;
	private RentBook rentBookSelected;
	private RentBook rentbookEdit;
	
	private List<BookProduct> listBookProduct;
	private List<RentBook> listRentbookReturned;
	
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					BookLostEditorJDialog frame = new BookLostEditorJDialog();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}


	public BookLostEditorJDialog() {
		setTitle("Báo mất sách");
		setModal(true);
		setResizable(false);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 523, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblChnSchMt = new JLabel("Chọn sách mất:");
		lblChnSchMt.setBounds(10, 268, 93, 24);
		lblChnSchMt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton btnChn = new JButton("Chọn");
		btnChn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				showSelectBook();
			}
		});
		btnChn.setBounds(116, 270, 75, 23);
		btnChn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 304, 496, 129);
		
		tblBook = new JTable();
		tblBook.setRowHeight(30);
		tblBook.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tblBook.setModel(new DefaultTableModel(null, new String[] {"MÃ SÁCH", "TÊN SÁCH", "GIÁ", "ĐÃ THUÊ", "ĐÃ MẤT", "TIỀN PHẠT"}) 
		{
			public boolean isCellEditable(int row, int column) 
			{
				switch (column) 
				{
					case 4:
					case 5:
						return true;
					default:
						return false;
				}
			}
		});
		tblBook.getModel().addTableModelListener(new TableModelListener() 
		{
			@Override
			public void tableChanged(TableModelEvent e) 
			{
				updateTotalCost();
			}
		});
		
		scrollPane.setViewportView(tblBook);
		
		JButton btnXa = new JButton("Xóa");
		btnXa.setBounds(449, 270, 57, 22);
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				deleteBookProduct();
			}
		});
		btnXa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JPanel pnlForm = new JPanel();
		pnlForm.setBounds(10, 11, 496, 246);
		pnlForm.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		cboRentBookId = new JComboBox();
		cboRentBookId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				cboSelectedRentbookId();
			}
		});
		cboRentBookId.setBounds(116, 27, 363, 24);
		
		JLabel lblNgiThu = new JLabel("Người thuê:");
		lblNgiThu.setBounds(10, 60, 93, 24);
		lblNgiThu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtNguoiThue = new JTextField();
		txtNguoiThue.setForeground(Color.RED);
		txtNguoiThue.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNguoiThue.setBounds(116, 62, 363, 24);
		txtNguoiThue.setEditable(false);
		txtNguoiThue.setColumns(10);
		
		JLabel lblTiKhong = new JLabel("Tài khoản:");
		lblTiKhong.setBounds(10, 95, 93, 24);
		lblTiKhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTaiKhoan.setBounds(116, 97, 363, 24);
		txtTaiKhoan.setEditable(false);
		txtTaiKhoan.setColumns(10);
		
		JLabel lblTngSchThu = new JLabel("Tổng sách thuê:");
		lblTngSchThu.setBounds(10, 130, 93, 24);
		lblTngSchThu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtTongThue = new JTextField();
		txtTongThue.setForeground(Color.BLUE);
		txtTongThue.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTongThue.setBounds(116, 132, 363, 24);
		txtTongThue.setEditable(false);
		txtTongThue.setColumns(10);
		
		txtAdmin = new JTextField();
		txtAdmin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtAdmin.setBounds(116, 167, 363, 24);
		txtAdmin.setEditable(false);
		txtAdmin.setColumns(10);
		
		JLabel lblNgiChoThu = new JLabel("NV cho thuê:");
		lblNgiChoThu.setBounds(10, 165, 93, 24);
		lblNgiChoThu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlForm.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã đơn thuê:");
		lblNewLabel.setBounds(10, 25, 96, 24);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlForm.add(lblNewLabel);
		pnlForm.add(cboRentBookId);
		pnlForm.add(lblNgiThu);
		pnlForm.add(txtNguoiThue);
		pnlForm.add(lblTiKhong);
		pnlForm.add(txtTaiKhoan);
		pnlForm.add(lblTngSchThu);
		pnlForm.add(txtTongThue);
		pnlForm.add(lblNgiChoThu);
		pnlForm.add(txtAdmin);
		
		JLabel lblTngTinPht = new JLabel("Tổng tiền phạt:");
		lblTngTinPht.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTngTinPht.setBounds(10, 200, 106, 24);
		pnlForm.add(lblTngTinPht);
		
		lblTotalCost = new JLabel("0 đ");
		lblTotalCost.setForeground(Color.RED);
		lblTotalCost.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotalCost.setBounds(116, 202, 262, 24);
		pnlForm.add(lblTotalCost);
		
		JPanel pnlControll = new JPanel();
		pnlControll.setBounds(10, 444, 496, 59);
		pnlControll.setLayout(new GridLayout(1, 1, 15, 0));
		
		JButton btnConfirm = new JButton("Xác nhận");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (validateAll())
				{
					try 
					{
						if (isEditMode == false && insertBookLost())
						{
							bookLostJFrame.getDataTolist();
							bookLostJFrame.fillToTable();
							JOptionPane.showMessageDialog(contentPane, "Đã báo mất sách cho đơn thuê '" + rentBookSelected.getId() + "' thành công!");
						}
						else if (isEditMode == true && updateBookLost())
						{
							bookLostJFrame.getDataTolist();
							bookLostJFrame.fillToTable();
							JOptionPane.showMessageDialog(contentPane, "Cập nhật báo mất sách cho đơn thuê '" + rentBookSelected.getId() + "' thành công!");
						}
					} 
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					}
				}
			}
		});
		btnConfirm.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlControll.add(btnConfirm);
		contentPane.setLayout(null);
		contentPane.add(pnlForm);
		contentPane.add(lblChnSchMt);
		contentPane.add(btnChn);
		contentPane.add(btnXa);
		contentPane.add(scrollPane);
		contentPane.add(pnlControll);
		getDataToList();
		fillToCboRentBookId();
	}
	
	//Lấy dữ liệu từ database bỏ vào list chứa các thông tin Rentbook đã trả sách
	public void getDataToList()
	{
		try 
		{
			listRentbookReturned = RentBookDAO.getAllReturned();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Đổ dữ liệu RentbookID trong listRentbookReturned vào cboRentbookId
	public void fillToCboRentBookId()
	{
		cboRentBookId.removeAllItems();
		cboRentBookId.removeAll();
		cboRentBookId.addItem("Chưa chọn");
		for (RentBook rb : listRentbookReturned)
		{
			cboRentBookId.addItem(rb.getId());
		}
	}
	
	//Đổ dữ liệu từ list BookProduct vào table
	public void fillToTable()
	{
		DefaultTableModel model = (DefaultTableModel) tblBook.getModel();
		model.setRowCount(0);
		
		try 
		{
			for (BookProduct bp : listBookProduct)
			{
				Book book = bp.getBook();
				System.out.println(rentBookSelected.getId());
				int amountRented = RentBookDetailDAO.findById(rentBookSelected.getId(), book.getId()).getAmount();
				String price_str = DataHelper.getFormatForMoney(bp.getPrice()) + "đ";
				
				Object[] rowData = {book.getId(), book.getTitle(), price_str, amountRented, bp.getAmount(), bp.getPrice()};
				model.addRow(rowData);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	//Hàm này được gọi khi cbo chọn mã thuê được kích hoạt
	public void cboSelectedRentbookId()
	{
		int index = cboRentBookId.getSelectedIndex();
		if (index != 0)
		{
			unLockForm();
			this.rentBookSelected = listRentbookReturned.get(index - 1);
			showInfoRentbook(rentBookSelected);
			
			//Xóa hết các phần tử có trong bảng
			((DefaultTableModel) tblBook.getModel()).setRowCount(0);
		}
		else
		{
			this.rentBookSelected = null;
			clearForm();
			lockForm();
		}
		
		
	}
	
	//Cập nhật lại tổng tiền phạt dựa vào thông tin trong bảng
	public void updateTotalCost()
	{
		//Lặp toàn bộ các hàng trong bảng
		int rowCount = tblBook.getRowCount();
		double totalCost = 0;
		
		for (int i=0; i < rowCount; i++)
		{
			String cost_str = tblBook.getValueAt(i, 5).toString();
			if (DataHelper.isDouble(cost_str))
			{
				double cost = DataHelper.getDouble(cost_str);
				totalCost += cost;
			}
		}
		lblTotalCost.setText(DataHelper.getFormatForMoney(totalCost) + "đ");
		
	}
	
	//Kiểm tra các lỗi trên toàn bộ form trả về TRUE nếu hợp lệ
	public boolean validateAll()
	{
		boolean isSuccess = true;
		String msg = "";
		try 
		{
			//CHECK CHƯA CHỌN MÃ ĐƠN THUÊ SÁCH
			if (rentBookSelected == null)
			{
				isSuccess = false;
				msg += "+ Mã đơn thuê chưa được chọn, vui lòng kiểm tra lại\n";
			}
			else
			{
				//NẾU LÀ INSERT MODE
				if (isEditMode == false)
				{
					//CHECK Mã đơn thuê này đã tồn tại trong bảng BookLost (đã khai báo mất sách rồi)
					if (BookLostDAO.findByID(rentBookSelected.getId()) != null)
					{
						isSuccess = false;
						msg += "+ Khai báo mất bị trùng, phiếu mượn '" + rentBookSelected.getId() + "' đã được báo mất sách rồi.\n";
					}
				}
				//NẾU LÀ EDIT MODE
				else 
				{
					//Nếu như đơn thuê báo mất bị thay đổi thì vào đây kiểm tra xem đơn sau thay đổi có bị trùng không
					if (rentBookSelected.getId() != rentbookEdit.getId())
					{
						//CHECK Mã đơn thuê này đã tồn tại trong bảng BookLost (đã khai báo mất sách rồi)
						if (BookLostDAO.findByID(rentBookSelected.getId()) != null)
						{
							isSuccess = false;
							msg += "+ Khai báo mất bị trùng, phiếu mượn '" + rentBookSelected.getId() + "' đã được báo mất sách rồi.\n";
						}
					}
				}
			}
			
			//Kiểm tra xem đã chọn sách chưa?
			if (listBookProduct == null || listBookProduct.size() == 0)
			{
				isSuccess = false;
				msg += "+ Sách mất chưa được khai báo, vui lòng chọn sách bị mất\n";
			}
			
			//Lặp từng dòng trong bảng và kiểm tra 'SỐ Mất' và 'Tiền Phạt'
			for (int i=0; i < tblBook.getRowCount(); i++)
			{
				String lost_str = tblBook.getValueAt(i, 4).toString();
				String cost_str = tblBook.getValueAt(i, 5).toString();
				String book_id = tblBook.getValueAt(i, 0).toString();
				int amountRented = RentBookDetailDAO.findById(rentBookSelected.getId(), book_id).getAmount();
				System.out.println(amountRented);
				
				
				//CHECK SỐ LƯỢNG MẤT
				if (DataHelper.isInteger(lost_str))
				{
					int lost = DataHelper.getInt(lost_str);
					//Số sách mất không được bé hơn hoặc = 0
					if (lost <= 0)
					{
						isSuccess = false;
						msg += "+ [" + book_id + "] Số sách mất không được bé hơn hoặc bằng 0\n";
					}
					//Nếu số sách mất lại cao hơn số sách đã thuê
					if (lost > amountRented)
					{
						isSuccess = false;
						msg += "+ [" + book_id + "] Số sách lớn hơn số sách đã thuê (số mất: " + lost + ", đã thuê: " + amountRented + ")\n";
					}
				}
				else
				{
					isSuccess = false;
					msg += "+ [" + book_id + "] Số sách mất phải là số và lớn hơn 0\n";
				}
				
				//CHECK SỐ TIỀN PHẠT
				if (DataHelper.isDouble(cost_str) && DataHelper.getDouble(cost_str) <= 0)
				{
					isSuccess = false;
					msg += "+ [" + book_id + "] Số tiền phạt phải là số và lớn hơn 0\n";
				}
			}
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (isSuccess == false)
		{
			JOptionPane.showMessageDialog(this, "Đã có lỗi sảy ra: \n" + msg);
		}
		
		return isSuccess;
	}
	
	//Trả về danh sách List BOokProduct dựa trên các dữ liệu có trong bảng
	public List<BookProduct> getListBookProduct()
	{
		List<BookProduct> list = new ArrayList<BookProduct>();
		for (int i = 0; i < tblBook.getRowCount(); i++)
		{
			int amount = DataHelper.getInt(tblBook.getValueAt(i, 4).toString());
			double cost = DataHelper.getDouble(tblBook.getValueAt(i, 5).toString());
			Book book = listBookProduct.get(i).getBook();
			
			list.add(new BookProduct(book, amount, cost));
		}
		
		return list;
	}
	
	//Thêm BookLost vào database
	public boolean insertBookLost() throws SQLException
	{
		BookLost bookLost = new BookLost(rentBookSelected.getId(), AccountSave.getAdmin().getId(), new Date());
		return BookLostDAO.insert(bookLost, getListBookProduct());
	}
	
	public boolean updateBookLost() throws SQLException
	{
		bookLostEdit.setRentbookId(rentBookSelected.getId());
		bookLostEdit.setAdminId(AccountSave.getAdmin().getId());
		return BookLostDAO.update(this.bookLostEdit, getListBookProduct());
	}
	
	
	//Xóa bookProduct đang được chọn trên mảng (hàm này dc gọi khi nhấn nút xóa)
	public void deleteBookProduct()
	{
		int index = tblBook.getSelectedRow();
		if (index != -1)
		{
			listBookProduct.remove(index);
			((DefaultTableModel) tblBook.getModel()).removeRow(index);
		}
	}
	
	//Mở bảng chọn sách
	public void showSelectBook()
	{
		try 
		{
			if (rentBookSelected != null)
			{
				selectBookJDialog.setLocationRelativeTo(this);
				
				selectBookJDialog.setListBook(RentBookDetailDAO.getListBook(rentBookSelected.getId()));
				selectBookJDialog.fillToTable();
				
				selectBookJDialog.setVisible(true);
				
				if (selectBookJDialog.getStatus() == selectBookJDialog.STATUS_SELECTED)
				{
					listBookProduct = selectBookJDialog.getListBookProductSelected();
					fillToTable();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(contentPane, "Mã đơn thuê chưa được chọn");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//
	public void setEditMode(boolean isEditMode)
	{
		this.isEditMode = isEditMode;
	}
	
	//
	public void setBookLostJFrame(BookLostJFrame bookLostJFrame)
	{
		this.bookLostJFrame = bookLostJFrame;
	}
	
	//Thực hiện thao tác truyền các giá trị liên quan đến BookLost vào, và tải lên giao diện
	public void setEditModel(BookLost bookLost)
	{
		try 
		{
			this.bookLostEdit = bookLost;
			rentbookEdit = RentBookDAO.findById(bookLost.getRentbookId());
			
			//Chọn đúng id cho combobox
			cboRentBookId.setSelectedItem(rentbookEdit.getId());

			this.listBookProduct = BookLostDetailDAO.getListProducts(rentbookEdit.getId());
			fillToTable();
			
			//Hiển thị thông tin về Rentbook này lên form
			showInfoRentbook(RentBookDAO.findById(bookLost.getRentbookId()));
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Hàm gọi hiển thị thông tin của dối tượng Rentbook vừa truyền vào
	public void showInfoRentbook(RentBook rb)
	{
		try 
		{
			User user = UserDAO.findByID(rb.getUserId());
			txtTaiKhoan.setText(user.getUsername());
			txtNguoiThue.setText(user.getFullname());
			txtAdmin.setText(AdminDAO.findByID(rb.getAdminId()).getUsername());
			txtTongThue.setText(RentBookDetailDAO.getTotalBookRented(rb.getId()) + " quyển");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Xóa sạch các giá trị có trong form
	public void clearForm()
	{
		txtAdmin.setText("");
		txtNguoiThue.setText("");
		txtTaiKhoan.setText("");
		txtTongThue.setText("");
		((DefaultTableModel)tblBook.getModel()).setRowCount(0);
	}
	
	//Disable tất cả các phần tử có trong form (textfield)
	public void lockForm()
	{
		txtAdmin.setEnabled(false);
		txtNguoiThue.setEnabled(false);
		txtTaiKhoan.setEnabled(false);
		txtTongThue.setEnabled(false);
	}
	
	//Enable tất cả các phần tử có trong form
	public void unLockForm()
	{
		txtAdmin.setEnabled(true);
		txtNguoiThue.setEnabled(true);
		txtTaiKhoan.setEnabled(true);
		txtTongThue.setEnabled(true);
	}

}
