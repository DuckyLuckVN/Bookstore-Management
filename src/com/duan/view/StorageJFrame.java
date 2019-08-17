package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.print.attribute.standard.SheetCollate;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.duan.custom.common.JTableBlue;
import com.duan.custom.common.JTableRed;
import com.duan.custom.message.MessageOptionPane;
import com.duan.dao.AdminDAO;
import com.duan.dao.BookDAO;
import com.duan.dao.CategoryDAO;
import com.duan.dao.StorageDAO;
import com.duan.dao.StorageDetailDao;
import com.duan.helper.AccountSave;
import com.duan.helper.DataHelper;
import com.duan.helper.DateHelper;
import com.duan.helper.SettingSave;
import com.duan.helper.SwingHelper;
import com.duan.model.Admin;
import com.duan.model.Book;
import com.duan.model.BookProduct;
import com.duan.model.Storage;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextArea;

public class StorageJFrame extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnHelp;
	private JMenuItem mntmCheckVersion;
	private JMenuItem mntmAboutUs;
	private JMenuItem mntmExportToText;
	private JMenuItem mntmExportToExcel;
	private JMenu mnExportTo;
	private JMenuItem mntmCreateBackupFile;
	private JPanel pnlController;
	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnSelectBook;
	private JButton btnDeleteBook; 
	private JTableRed tblStorage;
	private JTableBlue tblBook;
	private JButton btnNew;
	
	private SelectBookJDialog selectBookJDialog = new SelectBookJDialog();
	
	private List<Storage> listStorage = new ArrayList<Storage>();
	private List<BookProduct> listBookProduct = new ArrayList<BookProduct>();
	private int indexSelect = -1;
	private JLabel lblGhiCh;
	private JTextField txtDescription;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					StorageJFrame frame = new StorageJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public StorageJFrame() 
	{
		setTitle("Nhập kho");
		setIconImage(Toolkit.getDefaultToolkit().getImage(StorageJFrame.class.getResource("/com/duan/icon/icons8_books_64px_4.png")));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 805, 709);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmCreateBackupFile = new JMenuItem("Tạo bản sao lưu (.bak)");
		mnFile.add(mntmCreateBackupFile);
		
		mnExportTo = new JMenu("Xuất File ..");
		mnFile.add(mnExportTo);
		
		mntmExportToText = new JMenuItem("Xuất File Text (.txt)");
		mnExportTo.add(mntmExportToText);
		
		mntmExportToExcel = new JMenuItem("Xuất file Excel (.xls)");
		mnExportTo.add(mntmExportToExcel);
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		mntmCheckVersion = new JMenuItem("Phiên bản");
		mnHelp.add(mntmCheckVersion);
		
		mntmAboutUs = new JMenuItem("Thông tin chung");
		mnHelp.add(mntmAboutUs);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		pnlController = new JPanel();
		pnlController.setBorder(new TitledBorder(null, "\u0110i\u1EC1u khi\u1EC3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlController.setLayout(new GridLayout(0, 1, 0, 5));
		pnlController.setPreferredSize(new Dimension(150, 5));
		
		btnNew = new JButton(" Đơn mới");
		btnNew.setHorizontalAlignment(SwingConstants.LEFT);
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setControllModeTo_Insert();
				unlockForm();
				clearForm();
			}
		});
		
		btnNew.setIcon(new ImageIcon(StorageJFrame.class.getResource("/com/duan/icon/Create.png")));
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlController.add(btnNew);
		
		btnSave = new JButton(" Lưu lại");
		btnSave.setEnabled(false);
		btnSave.setHorizontalAlignment(SwingConstants.LEFT);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					if (validateAll() && insertStorage())
					{
						getDataToList();
						fillToTableStorage();
						
						//Gán row đang chọn vào row mới nhất, và hiển thị thông tin lên form lại
						{
							indexSelect = tblStorage.getRowCount() - 1;
							tblStorage.setRowSelectionInterval(indexSelect, indexSelect);
							showDetail();
						}
						
						MessageOptionPane.showAlertDialog(contentPane, "Đã thêm mới đơn nhập kho thành công!", MessageOptionPane.ICON_NAME_SUCCESS);
					}
				}
				catch (HeadlessException | SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnSave.setIcon(new ImageIcon(StorageJFrame.class.getResource("/com/duan/icon/Accept.png")));
		btnSave.setSelectedIcon(new ImageIcon(StorageJFrame.class.getResource("/com/duan/icon/icons8_add_64px.png")));
		pnlController.add(btnSave);
		
		btnUpdate = new JButton(" Cập nhật");
		btnUpdate.setEnabled(false);
		btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					if (validateAll() && updateStorage())
					{
						getDataToList();
						fillToTableStorage();
						
						MessageOptionPane.showAlertDialog(contentPane, "Cập nhật đơn nhập kho thành công!", MessageOptionPane.ICON_NAME_SUCCESS);
					}
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnUpdate.setIcon(new ImageIcon(StorageJFrame.class.getResource("/com/duan/icon/Notes.png")));
		pnlController.add(btnUpdate);
		
		btnDelete = new JButton("Xóa");
		btnDelete.setEnabled(false);
		btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					if (MessageOptionPane.showConfirmDialog(contentPane, "Bạn có chắc muốn xóa đơn nhập kho này không?"))
					{
						if(deleteStorage())
						{
							MessageOptionPane.showAlertDialog(contentPane, "Xóa đơn nhập kho số '" + listStorage.get(indexSelect).getId() + "' thành công!", MessageOptionPane.ICON_NAME_SUCCESS);
							getDataToList();
							fillToTableStorage();
							showDetail();
						}
					}
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnDelete.setIcon(new ImageIcon(StorageJFrame.class.getResource("/com/duan/icon/icons8_delete_32px_1.png")));
		pnlController.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "B\u1EA3ng nh\u1EADp kho", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "B\u1EA3ng s\u00E1ch nh\u1EADp v\u00E0o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		tblBook = new JTableBlue();
		tblBook.setRowHeight(30);
		tblBook.setModel(new DefaultTableModel(null, new String[] {"MÃ SÁCH", "TÊN SÁCH", "GIÁ BÁN", "GIÁ NHẬP", "SỐ LƯỢNG"}) {
			public boolean isCellEditable(int row, int column) 
			{
				switch (column) 
				{
				case 3:
				case 4:
					return true;
				}
				return false;
			}
		});
		tblBook.getColumnModel().getColumn(0).setPreferredWidth(40);
		tblBook.getColumnModel().getColumn(1).setPreferredWidth(200);
		tblBook.getColumnModel().getColumn(4).setPreferredWidth(40);
		scrollPane_1.setViewportView(tblBook);
		
		tblStorage = new JTableRed();
		tblStorage.setShowHorizontalLines(true);
		tblStorage.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				eventTableStorageSelectRow();
			}
		});
		tblStorage.setRowHeight(35);
		tblStorage.setModel(new DefaultTableModel(null, new String[] {"MÃ NHẬP KHO", "NGƯỜI NHẬP", "TỔNG SỐ", "GHI CHÚ", "NGÀY NHẬP"} ) 
		{
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		});
		tblStorage.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblStorage.getColumnModel().getColumn(1).setPreferredWidth(200);
		scrollPane.setViewportView(tblStorage);
		
		JLabel lblChnSch = new JLabel("Chọn sách:");
		lblChnSch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		btnSelectBook = new JButton("Chọn sách");
		btnSelectBook.setEnabled(false);
		btnSelectBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				showSelectBook();
			}
		});
		
		btnDeleteBook = new JButton("Xóa");
		btnDeleteBook.setEnabled(false);
		btnDeleteBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				 deleteBook();
			}
		});
		
		lblGhiCh = new JLabel("Ghi chú:");
		lblGhiCh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtDescription = new JTextField();
		txtDescription.setEnabled(false);
		txtDescription.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(2)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(8)
									.addComponent(lblGhiCh)
									.addGap(25)
									.addComponent(txtDescription, GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
									.addGap(2))
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addGap(8)
									.addComponent(lblChnSch)
									.addGap(10)
									.addComponent(btnSelectBook, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 377, Short.MAX_VALUE)
									.addComponent(btnDeleteBook, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
									.addGap(1))
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE))
							.addGap(6)
							.addComponent(pnlController, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(2))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblGhiCh, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDescription, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblChnSch, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(1)
									.addComponent(btnSelectBook, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(1)
									.addComponent(btnDeleteBook, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
							.addGap(11)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
						.addComponent(pnlController, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
					.addGap(6))
		);
		contentPane.setLayout(gl_contentPane);
		//setLocationRelativeTo(getOwner());
		try 
		{
			getDataToList();
			fillToTableStorage();
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
	}
	
	//Hiển thị JDialog chọn các sách
	public void showSelectBook()
	{
		selectBookJDialog.setLocationRelativeTo(this);
		selectBookJDialog.setVisible(true);
		if (selectBookJDialog.getStatus() == SelectBookJDialog.STATUS_SELECTED)
		{
			listBookProduct = selectBookJDialog.getListBookProductSelected();
			fillToTableBook();
		}
	}
	
	//Sự kiện được gọi mỗi khi row trong bảng Storage được chọn
	public void eventTableStorageSelectRow()
	{
		indexSelect = tblStorage.getSelectedRow();
		setControllModeTo_Edit();
		showDetail();
		unlockForm();
	}
	
	//Lấy dữ liệu BOOK từ database đổ vào list
	public void getDataToList()
	{
		try 
		{
			listStorage.clear();
			listStorage = StorageDAO.getAll();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Lấy thông tin đã chọn đưới table Storage hiển thị lên form
	public void showDetail()
	{
		if (indexSelect == -1)
		{
			clearForm();
			lockForm();
			setControllModeTo_Nothing();
			return;
		}
		
		Storage storage;
		try 
		{
			storage = listStorage.get(indexSelect);
			txtDescription.setText(storage.getDescription());
			this.listBookProduct = StorageDetailDao.getListProduct(storage.getId());
			fillToTableBook();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Đổ dữ liệu từ ListBookProduct vào tblBook
	public void fillToTableBook()
	{
		DefaultTableModel model = (DefaultTableModel) tblBook.getModel();
		model.setRowCount(0);
		for (BookProduct bp : listBookProduct)
		{
			Object[] rowData = {
					bp.getBook().getId(),
					bp.getBook().getTitle(),
					DataHelper.getFormatForMoney(bp.getBook().getPrice()) + SettingSave.getSetting().getMoneySymbol(),
					bp.getPrice(),
					bp.getAmount()};
			model.addRow(rowData);
		}
	}
	
	//Đổ dữ liệu từ list vào table storage
	public void fillToTableStorage() throws SQLException
	{
		DefaultTableModel model = (DefaultTableModel) tblStorage.getModel();
		model.setRowCount(0);
		
		for (Storage storage : listStorage)
		{
			Admin admin = AdminDAO.findByID(storage.getAdminId());
			Object[] rowData = 
				{
					storage.getId(), 
					admin.getFullname() + " (" + admin.getUsername() + ")", 
					StorageDetailDao.getTotalBook(storage.getId()),
					storage.getDescription(),
					DateHelper.dateToString(storage.getCreatedDate(), SettingSave.getSetting().getDateFormat())
				};
			
			model.addRow(rowData);
		}
		
		int rowCount = tblStorage.getRowCount();
		
		//Nếu điều kiện hợp lý thì set select row lại y như lúc chưa fillToTable
		if (indexSelect != -1)
		{
			if (indexSelect < rowCount && rowCount > 0)
			{
				tblStorage.setRowSelectionInterval(indexSelect, indexSelect);
			}
			else
			{
				indexSelect = rowCount - 1;
				if (indexSelect > -1)
				{
					tblStorage.setRowSelectionInterval(indexSelect, indexSelect);
				}
				else
				{
					setControllModeTo_Nothing();
					clearForm();
					lockForm();
				}
			}
		}
	}
	
	
	//Trả về danh sách BookProduct vừa nhập trên Table Book (hàm này chỉ nên gọi khi đã gọi hàm validateAll() rồi)
	public List<BookProduct> getListBookProduct()
	{
		List<BookProduct> list = new ArrayList<BookProduct>();
		
		//Lặp hết bảng lấy ra dữ liệu cần thiết
		for (int i = 0; i < tblBook.getRowCount(); i++)
		{
			BookProduct product = new BookProduct();
			product = listBookProduct.get(i);
			product.setAmount(DataHelper.getInt(tblBook.getValueAt(i, 4).toString()));
			product.setPrice(DataHelper.getDouble(tblBook.getValueAt(i, 3).toString()));
			list.add(product);
		}
		
		return list;
	}
	
	//Tiến hành insert dữ liệu từ form về Database, trả về TRUE nếu thành công, ngược lại là FALSE
	public boolean insertStorage() throws SQLException
	{
		return StorageDAO.insert(new Storage(0, AccountSave.getAdmin().getId(), txtDescription.getText(), new Date()), getListBookProduct());
	}
	
	//Tiến hành update dữ liệu từ form về Database, trả về TRUE nếu thành công, ngược lại là FALSE
	public boolean updateStorage() throws SQLException
	{
		Storage storage = listStorage.get(indexSelect);
		storage.setDescription(txtDescription.getText());
		storage.setAdminId(AccountSave.getAdmin().getId());
		return StorageDAO.update(storage, getListBookProduct());
	}
	
	//Tiến hành xóa Storage trên bảng tblStorage đã chọn
	public boolean deleteStorage() throws SQLException
	{
		return StorageDAO.delete(listStorage.get(indexSelect).getId());
	}

	//Kiểm tra bắt lỗi từ form, trả về TRUE nếu hợp lệ, FALSE nếu không hợp lệ
	public boolean validateAll()
	{
		boolean isSuccess = true;
		String msg = "";
		
		//KIỂM TRA ĐÃ CHỌN SÁCH HAY CHƯA
		if (listBookProduct == null || listBookProduct.size() == 0)
		{
			isSuccess = false;
			msg += "+ Sách nhập kho chưa được chọn, vui lòng chọn sách\n";
		}
		else
		{
			//Lặp toàn bộ bảng Book và kiểm tra
			for (int i=0; i < tblBook.getRowCount(); i++)
			{
				String price_str = tblBook.getValueAt(i, 3).toString();
				String amount_str = tblBook.getValueAt(i, 4).toString();
				String book_id = tblBook.getValueAt(i, 0).toString();
				
				//KIỂM TRA GIÁ NHẬP
				if (DataHelper.isDouble(price_str))
				{
					if (DataHelper.getDouble(price_str) < 0)
					{
						isSuccess = false;
						msg += "+ [" + book_id + "] Giá nhập vào phải lớn hơn hoặc bằng 0\n";
					}
				}
				else
				{
					isSuccess = false;
					msg += "+ [" + book_id + "] Giá nhập vào sai định dạng (phải là số)\n";
				}
				
				//KIỂM TRA SỐ LƯỢNG
				if (DataHelper.isDouble(amount_str))
				{
					if (DataHelper.getDouble(amount_str) < 0)
					{
						isSuccess = false;
						msg += "+ [" + book_id + "] Số lượng nhập vào phải lớn hơn hoặc bằng 0\n";
					}
				}
				else
				{
					isSuccess = false;
					msg += "+ [" + book_id + "] Số lượng nhập vào sai định dạng (phải là số)\n";
				}
			}
		}
		
		if (isSuccess == false) { MessageOptionPane.showMessageDialog(this, "Đã có lỗi sảy ra: \n" + msg, MessageOptionPane.ICON_NAME_WARNING);}
		
		return isSuccess;
	}
	
	//Tiến hành xóa hàng trên bảng sách đang được chọn
	public void deleteBook()
	{
		int index = tblBook.getSelectedRow();
		if (index != -1)
		{
			((DefaultTableModel) tblBook.getModel()).removeRow(index);
			listBookProduct.remove(index);
		}
	}
	
	//Tiến hành xóa các dữ liệu có trong bảng và textfield trên form, và các giá trị đã lưu (dùng khi nhấn nút NEW)
	public void clearForm()
	{
		((DefaultTableModel) tblBook.getModel()).setRowCount(0);
		listBookProduct.clear();
		txtDescription.setText("");
	}
	
	public void lockForm()
	{
		txtDescription.setEnabled(false);
		btnDeleteBook.setEnabled(false);
		btnSelectBook.setEnabled(false);
	}
	
	public void unlockForm()
	{
		txtDescription.setEnabled(true);
		btnDeleteBook.setEnabled(true);
		btnSelectBook.setEnabled(true);
	}
	
	//Set các nút nhấn controll chỉ enable nút "Thêm Mới"
	//Chỉ gọi khi không có dòng nào trong bảng tblBook được chọn
	public void setControllModeTo_Nothing()
	{
		btnSave.setEnabled(false);
		
		btnDelete.setEnabled(false);
		btnNew.setEnabled(true);
		btnUpdate.setEnabled(false);
		
	}
	
	//Set các nút nhấn controll chỉ enable nút "Thêm Mới"
	//Chỉ gọi khi không có dòng nào trong bảng tblBook được chọn
	public void setControllModeTo_Edit()
	{
		btnDelete.setEnabled(true);
		btnUpdate.setEnabled(true);
		btnSave.setEnabled(false);
		btnNew.setEnabled(true);
	}
	
	//Set các nút nhấn controll chỉ enable nút "Thêm Mới"
	//Chỉ gọi khi không có dòng nào trong bảng tblBook được chọn
	public void setControllModeTo_Insert()
	{
		btnDelete.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnSave.setEnabled(true);
		btnNew.setEnabled(false);
	}
}
