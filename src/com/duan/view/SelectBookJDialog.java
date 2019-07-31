package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import com.duan.dao.BookDAO;
import com.duan.helper.DataHelper;
import com.duan.model.Book;
import com.duan.model.BookProduct;

import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectBookJDialog extends JDialog {

	private JPanel contentPane;
	private JTable tblBook;
	private JLabel lblTmTheoTn;
	private JTextField textField;
	
	public static final int STATUS_NOT_SELECT = -1;
	public static final int STATUS_SELECTED = 1;
	private int status;
	
	private List<Book> listBookSelected = new ArrayList<Book>();
	private List<Book> listBook;
	private List<BookProduct> listBookProduct = new ArrayList<BookProduct>();
	
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectBookJDialog frame = new SelectBookJDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SelectBookJDialog() 
	{
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelectBookJDialog.class.getResource("/com/duan/icon/icons8_book_50px_1.png")));
		setTitle("Chọn sách");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 49, 425, 191);
		
		tblBook = new JTable();
		tblBook.setRowHeight(25);
		tblBook.setModel(new DefaultTableModel(null,new String[] {"MÃ", "TIÊU ĐỀ", "GIÁ BÁN", "CHỌN"}) 
		{
			//Ghi đè lại hàm getColumnClass để cột đầu tiên ở dạng boolean (checkbox)
			@Override
			public Class<?> getColumnClass(int columnIndex) 
			{
				//columnIndex = 3 -> cột chọn
				if (columnIndex == 3)
				{
					return Boolean.class;
				}
				return super.getColumnClass(columnIndex);
			}

			public boolean isCellEditable(int row, int column) 
			{
				//Cho phép cột đầu tiên được edit
				if (column == 3)
					return true;
				return false;
			}
		});
		tblBook.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblBook.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblBook.getColumnModel().getColumn(1).setPreferredWidth(300);
		scrollPane.setViewportView(tblBook);
		
		lblTmTheoTn = new JLabel("Tìm theo tên:");
		lblTmTheoTn.setBounds(5, 11, 83, 26);
		lblTmTheoTn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField = new JTextField();
		textField.setBounds(98, 11, 332, 26);
		textField.setColumns(10);
		contentPane.setLayout(null);
		contentPane.add(lblTmTheoTn);
		contentPane.add(textField);
		contentPane.add(scrollPane);
		
		JButton btnChn = new JButton("Chọn");
		btnChn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					setListBookSelected();
					dispose();
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		btnChn.setBounds(331, 244, 99, 26);
		contentPane.add(btnChn);
		
		
		getDataToList();
		fillToTable();
	}
	
	//Câu lệnh dùng để mở Jdialog lên và cài đặc giá trị mặc định lại
	public void openSelectJDialog()
	{
		status = STATUS_NOT_SELECT;
		listBookSelected.clear();;
		getDataToList();
		fillToTable();
		setVisible(true);
	}
	
	//Trả về trạng thái sách đã dc chọn hay chưa
	public int getStatus()
	{
		return this.status;
	}
	
	//Trả về danh sách các sách được chọn
	public List<Book> getListBookSelected()
	{
		return this.listBookSelected;
	}
	
	//Trả về danh sách các sách được chọn nhưng dưới dạng model BookProduct (tiện cho việc xử lý ở các jframe khác)
	public List<BookProduct> getListBookProductSelected()
	{
		return this.listBookProduct;
	}
	
	public void setListBookSelected() throws SQLException
	{
		listBookSelected.clear();

		int rowCount = tblBook.getRowCount();
		//Lặp duyệt qua từng dòng trong bảng
		for (int i = 0; i < rowCount; i++)
		{
			//Kiểm tra xem dòng này ô chọn có dc "CHECK" hay không?
			boolean isChecked = (Boolean) tblBook.getValueAt(i, 3);
			
			//Nếu được check thì lấy thông tin model của sách đó bỏ vào listBookSelected
			if (isChecked )
			{
				//lấy thông tin trên bảng
				String bookId = tblBook.getValueAt(i, 0).toString();
				Book bookSelected = BookDAO.findByID(bookId);
				
				//thêm vào listBook
				listBookSelected.add(bookSelected);
				
				//Thêm vào listBook dưới dạng BookProduct
				BookProduct product = new BookProduct(bookSelected, 1, bookSelected.getPrice());
				listBookProduct.add(product);
				
				//Cập nhật lại trạng thái
				status = STATUS_SELECTED;
			}
		}
	}
	
	
	public void getDataToList()
	{
		try 
		{
			listBook = BookDAO.getAll();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void fillToTable()
	{
		DefaultTableModel model = (DefaultTableModel) tblBook.getModel();
		model.setRowCount(0);
		
		for (Book book : listBook)
		{
			String price = DataHelper.getFormatForMoney(book.getPrice()) + " đ";
			Object[] rowData = {book.getId(), book.getTitle(), price, false};
			model.addRow(rowData);
		}
	}
}
