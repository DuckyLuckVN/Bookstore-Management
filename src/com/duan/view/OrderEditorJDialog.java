package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.duan.dao.BookDAO;
import com.duan.dao.OrderDAO;
import com.duan.dao.OrderDetailDAO;
import com.duan.dao.UserDAO;
import com.duan.helper.AccountSave;
import com.duan.helper.DataHelper;
import com.duan.helper.SwingHelper;
import com.duan.model.Book;
import com.duan.model.BookProduct;
import com.duan.model.Order;
import com.duan.model.OrderDetail;
import com.duan.model.RentBook;
import com.duan.model.User;

import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.Dialog.ModalExclusionType;

public class OrderEditorJDialog extends JDialog {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTable tblBook;
	private JLabel lblPriceTotal;
	
	private SelectUserJDialog selectUserJDialog = new SelectUserJDialog();
	private SelectBookJDialog selectBookJDialog = new SelectBookJDialog();
	private OrderJFrame orderJFrame;
	
	private boolean isEditMode = false;
	private Order order;
	private User userSelect;
	private List<Book> listBook = new ArrayList<Book>();
	private List<BookProduct> listBookProduct = new ArrayList<BookProduct>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderEditorJDialog frame = new OrderEditorJDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public OrderEditorJDialog() 
	{
		setModal(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(OrderEditorJDialog.class.getResource("/com/duan/icon/icons8_buy_for_change_64px_1.png")));
		setTitle("Bán Sách chi tiết");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 561, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 541, 400);
		panel.setBorder(new TitledBorder(null, "Th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblTaiKhoang = new JLabel("Tài khoảng");
		lblTaiKhoang.setBounds(14, 25, 67, 27);
		lblTaiKhoang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtUsername = new JTextField();
		txtUsername.setBounds(91, 26, 302, 27);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUsername.setEditable(false);
		txtUsername.setColumns(10);
		
		JButton btnSelectAccount = new JButton("Chọn");
		btnSelectAccount.setBounds(399, 27, 65, 27);
		btnSelectAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				showSelectUser();
			}
		});
		
		JLabel lblThueSach = new JLabel("Sách mua");
		lblThueSach.setBounds(16, 63, 66, 27);
		lblThueSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnSelectBook = new JButton("Chọn sách");
		btnSelectBook.setBounds(92, 65, 83, 27);
		btnSelectBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				showSelectBook();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 103, 512, 235);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		tblBook = new JTable();
		tblBook.setRowHeight(25);
		tblBook.setBorder(new EmptyBorder(0, 0, 0, 0));
		tblBook.setModel(new DefaultTableModel(null, new String[] {"MÃ SÁCH", "TÊN SÁCH", "GIÁ BÁN", "SỐ LƯỢNG"}) {
			
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
					return boolean.class;
				}
				return super.getColumnClass(columnIndex);
			}
		});
		
		tblBook.getModel().addTableModelListener(new TableModelListener() 
		{
			@Override
			public void tableChanged(TableModelEvent evt) 
			{
				eventModelTableChange();
				
				if (evt.getType() == TableModelEvent.UPDATE)
				{
					updateAmountBookProduct(evt.getFirstRow());
				}
			}
		});
		tblBook.getColumnModel().getColumn(0).setPreferredWidth(10);
		tblBook.getColumnModel().getColumn(1).setPreferredWidth(180);
		scrollPane.setViewportView(tblBook);
		
		JButton btnDelete = new JButton("Xóa");
		btnDelete.setBounds(459, 65, 66, 27);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				removeBookSelected();
			}
		});
		
		JLabel lblTngGi = new JLabel("Tổng Giá:");
		lblTngGi.setBounds(16, 355, 66, 27);
		lblTngGi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTngGi.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblPriceTotal = new JLabel("0 đ");
		lblPriceTotal.setBounds(88, 355, 127, 27);
		lblPriceTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblPriceTotal.setForeground(Color.RED);
		lblPriceTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.setLayout(null);
		contentPane.add(panel);
		
		JButton button = new JButton("Xóa");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				removeUserSelected();
			}
		});
		button.setBounds(459, 27, 66, 27);
		panel.setLayout(null);
		panel.add(lblTngGi);
		panel.add(lblPriceTotal);
		panel.add(lblThueSach);
		panel.add(btnSelectBook);
		panel.add(btnDelete);
		panel.add(scrollPane);
		panel.add(lblTaiKhoang);
		panel.add(txtUsername);
		panel.add(btnSelectAccount);
		panel.add(button);
		
		JButton btnSave = new JButton("Xác nhận");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (checkErrorAll() == false)
				{
					if (isEditMode == false && insertOrder())
					{
						orderJFrame.getDataTolist();
						orderJFrame.fillToTable();
						JOptionPane.showMessageDialog(contentPane, "Thêm hóa đơn mới thành công!");
					}
					else if (isEditMode == true && updateOrder())
					{
						orderJFrame.getDataTolist();
						orderJFrame.fillToTable();
						JOptionPane.showMessageDialog(contentPane, "Cập nhật lại hóa đơn số '" + order.getId() + "' thành công!");
					}
						
				}
			}
		});
		btnSave.setBounds(422, 416, 124, 33);
		contentPane.add(btnSave);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
	}
	
	//Đổ dữ liệu từ ListBook vào table
	public void fillToTable()
	{
		DefaultTableModel model = (DefaultTableModel) tblBook.getModel();
		model.setRowCount(0);
		
		for (BookProduct product : listBookProduct)
		{
			String price = DataHelper.getFormatForMoney(product.getPrice()) + " đ";
			
			Object[] rowData = {product.getBook().getId(), product.getBook().getTitle(), price, product.getAmount()};
			model.addRow(rowData);
		}
	}
	
	//Hàm này được gọi khi các row trong bảng bị thay đổi (thêm, xóa, cập nhật)
	public void eventModelTableChange()
	{
		//Cập nhật lại tổng giá tiền
		updatePriceTotal();
	}
	
	//Trả về tổng giá của các sản phẩm đã chọn, với cập nhật lại số lượng cho listBookProduct nếu giá nhập vào là đúng
	public double getPriceTotal()
	{
		int rowCount = tblBook.getRowCount();
		double priceTotal = 0;
		for (int i = 0; i < rowCount; i++)
		{
			double price = listBookProduct.get(i).getPrice();
			String amount_temp = tblBook.getValueAt(i, 3).toString();
			
			if (DataHelper.isInteger(amount_temp))
			{
				int amount = DataHelper.getInt(amount_temp);
				priceTotal += price * amount;
				
				//Cập nhật lại số lượng amount của listBookProduct luôn.
				listBookProduct.get(i).setAmount(amount);
			}
		}
		
		return priceTotal;
	}
	
	//Cập nhật lại giá từ từ danh sách ở bảng đang có
	public void updatePriceTotal()
	{
		lblPriceTotal.setText(DataHelper.getFormatForMoney(getPriceTotal()) + " đ");
	}
	
	
	//Cập nhật lại số lượng amount của listBookProcut tại vị trí index
	public void updateAmountBookProduct(int index)
	{
		String amount_temp = tblBook.getValueAt(index, 3).toString();
		
		if (DataHelper.isInteger(amount_temp))
		{
			int amount = DataHelper.getInt(amount_temp);
			
			//Cập nhật lại số lượng amount của listBookProduct luôn.
			listBookProduct.get(index).setAmount(amount);
		}
	}
	
	
	//Hiển thị JDialog chọn user
	public void showSelectUser()
	{
		selectUserJDialog.setLocationRelativeTo(this);
		selectUserJDialog.setVisible(true);
	
		if (selectUserJDialog.getStatus() == SelectUserJDialog.STATUS_SELECTED)
		{
			userSelect = selectUserJDialog.getUserSelected();
			txtUsername.setText(userSelect.getFullname());
			
		}
	}
	
	//Hiển thị jdialog chọn sách
	public void showSelectBook()
	{
		selectBookJDialog.setLocationRelativeTo(this);
		selectBookJDialog.setVisible(true);
		
		if (selectBookJDialog.getStatus() == SelectBookJDialog.STATUS_SELECTED)
		{
			listBookProduct = selectBookJDialog.getListBookProductSelected();
			fillToTable();
		}
	}
	
	//Xóa sách có trong bảng, sau đó xóa cả trong listBookProduct
	public void removeBookSelected()
	{
		int index = tblBook.getSelectedRow();
		if (index >= 0)
		{
			((DefaultTableModel) tblBook.getModel()).removeRow(index);
			listBookProduct.remove(index);
		}
	}
	
	public void removeUserSelected()
	{
		userSelect = null;
		txtUsername.setText("");
	}
	
	//Thay đổi mode của JFrame
	public void setEditMode(boolean isEditMode)
	{
		this.isEditMode = isEditMode;
	}
	
	//Set thông tin của order để hiển thị lên form
	public void setOrderModel(Order order)
	{
		this.order = order;
		try 
		{
			//Lấy thông tin và lưu lại để xử lý
			listBookProduct = OrderDetailDAO.getListProducts(order.getId());
			userSelect = UserDAO.findByID(order.getUserId());
			if (userSelect != null)
			{
				txtUsername.setText(userSelect.getFullname());
			}
			
			fillToTable();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void setOrderJFrame(OrderJFrame orderJFrame)
	{
		this.orderJFrame = orderJFrame;
	}
	
	//Kiểm tra bắt lỗi trước khi thực hiện, nếu có lỗi sẽ trả về TRUE
	public boolean checkErrorAll()
	{
		boolean isError = true;
		String msg = "";
		
		try 
		{
			//Lỗi chưa chọn sách
			if (listBookProduct.size() == 0)
			{
				isError = true;
				msg += "+ Sách chưa được chọn, vui lòng chọn sách trước khi xác nhận \n";
			}
	
			//Lặp duyệt hết các row trong bảng
			for (int i = 0; i < tblBook.getRowCount(); i++)
			{
				//Lấy ra dữ liệu từng dòng
				BookProduct product = listBookProduct.get(i);
				String amount_temp = tblBook.getValueAt(i, 3).toString();
			
				//Lỗi nhập số lượng bé hơn 0 và sai định dạng
				if (DataHelper.isInteger(amount_temp) == true && DataHelper.getInt(amount_temp ) > 0)
				{
					//Lỗi số lượng nhập vào lớn hơn số lượng sách có trong kho
					int amountAvailable;
						amountAvailable = BookDAO.getAmountAvailable(product.getBook().getId());
					int amount = DataHelper.getInt(amount_temp);
					
					//NẾU EDIT MODE == FALSE (INSERT MODE) THÌ KIỂM TRA KIỂU NÀY
					if (isEditMode == false)
					{
						if (amount <= amountAvailable)
						{
							isError = false;
						}
						else
						{
							isError = true;
							msg += "+ [" + product.getBook().getId() + "] Không đủ sách để bán (chỉ còn: " + amountAvailable + " quyển, cần bán: " + amount + " quyển) \n";
						}
					}
					//NẾU EDIT MODE == TRUE THÌ KIỂM TRA KIỂU NÀY
					else if (isEditMode == true)
					{
						int amountProduct = 0;
						
						//Nếu sách này đã có trong danh sách chi tiết thì mới set amount product
						//Amount product là số lượng cũ có trong đơn hàng chi tiết
						OrderDetail detail = OrderDetailDAO.findByID(order.getId(), product.getBook().getId());
						if (detail != null)
						{
							amountProduct = detail.getAmount();
						}
						
						int amountAvailableAfterUpdate = amountAvailable + amountProduct - product.getAmount();
						System.out.println(amountAvailableAfterUpdate);
						//Nếu số lượng sách tồn kho sau khi update (amountAvailableAfterUpdate) mà nhỏ hơn 0 thì => báo không đủ sách để đáp ứng
						if (amountAvailableAfterUpdate >= 0)
						{
							isError = false;
						}
						else
						{
							isError = true;
							msg += "+ [" + product.getBook().getId() + "] Không đủ sách để thêm vào (chỉ còn: " + amountAvailable + " quyển, cần bổ sung: " + (product.getAmount() - amountProduct) + " quyển)\n";
						}
					}
					
				}
				else
				{
					isError = true;
					msg += "+ [" + product.getBook().getId() + "] Số lượng nhập vào phải là số và lớn hơn 0\n";
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		if (isError)
		{
			JOptionPane.showMessageDialog(contentPane, "Đã có lỗi sảy ra:\n" + msg);
		}
		
		return isError;
	}
	
	//Thêm Order vào Database
	public boolean insertOrder()
	{
		
		Order order = new Order();
		boolean status = false;
		try 
		{
			if (userSelect == null)
				order.setUserId(0);
			else
				order.setUserId(userSelect.getId());
			
			order.setAdminId(AccountSave.getAdmin().getId());
			order.setDateCreated(new Date());
		
			status = OrderDAO.insert(order, listBookProduct);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	//Cập nhật Order vào Database
	public boolean updateOrder()
	{
		try 
		{
			if (userSelect != null)
				this.order.setUserId(userSelect.getId());
			else
				this.order.setUserId(0);
		
			return OrderDAO.update(order, listBookProduct);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}

}
