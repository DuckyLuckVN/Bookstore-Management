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

import com.duan.controller.ExportExcel;
import com.duan.custom.common.JTableRed;
import com.duan.custom.common.JTextFieldDark;
import com.duan.custom.message.MessageOptionPane;
import com.duan.dao.AuthorDAO;
import com.duan.dao.BookDAO;
import com.duan.dao.CategoryDAO;
import com.duan.dao.PublisherDAO;
import com.duan.helper.AccountSave;
import com.duan.helper.DataHelper;
import com.duan.helper.DateHelper;
import com.duan.helper.SettingSave;
import com.duan.helper.SwingHelper;
import com.duan.model.Admin;
import com.duan.model.Book;

import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.border.TitledBorder;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Desktop;
import javax.swing.border.LineBorder;

public class BookJFrame extends JFrame{

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
	private JButton btnDetail;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JTableRed tblBook;
	private JPanel pnlSelect;
	private JButton btnMaxLeft;
	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnMaxRight;
	private JPanel pnlTime;
	
	private BookEditorJDialog inserBookJFrame = new BookEditorJDialog();
	private BookEditorJDialog editorBookJDialog = new BookEditorJDialog();
	private BookDetailJDialog bookDetailJDialog = new BookDetailJDialog(this);
	private JLabel lblTmKim;
	private JTextField txtSearch;
	
	private List<Book> listBook = new ArrayList<Book>();
	private Book book;
	private int indexSelect = -1;
	private JPopupMenu popupMenu;
	private JMenuItem mntmXemChiTit;
	private JMenuItem mntmSa;
	private JMenuItem mntmXa;
	private JButton btnExportExcel;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					BookJFrame frame = new BookJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public BookJFrame() 
	{
		setTitle("Quản lý kho sách");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BookJFrame.class.getResource("/com/duan/icon/icons8_books_64px_4.png")));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 897, 600);
		
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
		contentPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				System.out.println(e.getKeyCode());
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		pnlController = new JPanel();
		pnlController.setBorder(new TitledBorder(null, "\u0110i\u1EC1u khi\u1EC3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlController.setLayout(new GridLayout(0, 1, 0, 5));
		pnlController.setPreferredSize(new Dimension(150, 5));
		
		btnDetail = new JButton("Xem chi tiết");
		btnDetail.setEnabled(false);
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showBookDetail();
			}
		});
		SwingHelper.setTextBelowIconButton(btnDetail);
		btnDetail.setIcon(new ImageIcon(BookJFrame.class.getResource("/com/duan/icon/icons8_details_popup_50px_1.png")));
		btnDetail.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnlController.add(btnDetail);
		
		btnAdd = new JButton("Thêm mới");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (AccountSave.getAdmin().getRole() == Admin.ROLE_QUANLY || AccountSave.getAdmin().getRole() == Admin.ROLE_GIAMDOC)
				{
					showInsertBook();
				}
				else
				{
					MessageOptionPane.showAlertDialog(contentPane, "Chức năng này chỉ dành cho chức vụ Quản Lý!", MessageOptionPane.ICON_NAME_BLOCK);
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		SwingHelper.setTextBelowIconButton(btnAdd);
		btnAdd.setIcon(new ImageIcon(BookJFrame.class.getResource("/com/duan/icon/icons8_add_50px_3.png")));
		btnAdd.setSelectedIcon(new ImageIcon(BookJFrame.class.getResource("/com/duan/icon/icons8_add_64px.png")));
		pnlController.add(btnAdd);
		
		btnEdit = new JButton("Thay đổi");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					if (AccountSave.getAdmin().getRole() == Admin.ROLE_QUANLY || AccountSave.getAdmin().getRole() == Admin.ROLE_GIAMDOC)
					{
						System.out.println(getBookSelected().getImage());
						showEditorBook(getBookSelected());
					}
					else
					{
						MessageOptionPane.showAlertDialog(contentPane, "Chức năng này chỉ dành cho chức vụ Quản Lý!", MessageOptionPane.ICON_NAME_BLOCK);
					}
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 12));
		SwingHelper.setTextBelowIconButton(btnEdit);
		btnEdit.setIcon(new ImageIcon(BookJFrame.class.getResource("/com/duan/icon/icons8_edit_property_50px.png")));
		pnlController.add(btnEdit);
		
		btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					if (AccountSave.getAdmin().getRole() == Admin.ROLE_QUANLY || AccountSave.getAdmin().getRole() == Admin.ROLE_GIAMDOC)
					{
						if (MessageOptionPane.showConfirmDialog(contentPane, "Bạn có chắc muốn xóa sách này không?"))
						{
							if (deleteBook())
							{
								getDataToList();
								fillToTable();
								MessageOptionPane.showAlertDialog(getContentPane(), "Đã xóa sách thành công!", MessageOptionPane.ICON_NAME_SUCCESS);
							}
						}
					}
					else
					{
						MessageOptionPane.showAlertDialog(contentPane, "Chức năng này chỉ dành cho chức vụ Quản Lý!", MessageOptionPane.ICON_NAME_BLOCK);
					}
				} 
				catch (HeadlessException | SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		SwingHelper.setTextBelowIconButton(btnDelete);
		btnDelete.setIcon(new ImageIcon(BookJFrame.class.getResource("/com/duan/icon/icons8_delete_50px.png")));
		pnlController.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "B\u1EA3ng d\u1EEF li\u1EC7u", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		pnlSelect = new JPanel();
		
		pnlTime = new JPanel();
		pnlTime.setBackground(SystemColor.menu);
		
		lblTmKim = new JLabel("Tìm kiếm:");
		lblTmKim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtSearch = new JTextFieldDark();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				try 
				{
					search();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		});
		txtSearch.setColumns(10);
		
		popupMenu = new JPopupMenu();
		
		mntmXemChiTit = new JMenuItem("Xem chi tiết");
		popupMenu.add(mntmXemChiTit);
		
		mntmSa = new JMenuItem("Sửa");
		popupMenu.add(mntmSa);
		
		mntmXa = new JMenuItem("Xóa");
		popupMenu.add(mntmXa);
		pnlTime.setLayout(new BorderLayout(0, 0));
		pnlSelect.setLayout(new GridLayout(1, 0, 15, 0));
		
		btnMaxLeft = new JButton("|<");
		btnMaxLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int rowCount = tblBook.getRowCount();
				if (rowCount > 0)
				{
					indexSelect = 0;
					tblBook.setRowSelectionInterval(indexSelect, indexSelect);
					setControllModeTo_Editable();
					eventTableSelectRow();
				}
			}
		});
		pnlSelect.add(btnMaxLeft);
		
		btnLeft = new JButton("<");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int rowCount = tblBook.getRowCount();
				if (indexSelect > 0 && rowCount > 0)
				{
					indexSelect--;
					tblBook.setRowSelectionInterval(indexSelect, indexSelect);
					setControllModeTo_Editable();
					eventTableSelectRow();
				}
			}
		});
		btnLeft.setEnabled(false);
		pnlSelect.add(btnLeft);
		
		btnRight = new JButton(">");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int rowCount = tblBook.getRowCount();
				if (indexSelect < rowCount - 1 && rowCount > 0)
				{
					indexSelect++;
					tblBook.setRowSelectionInterval(indexSelect, indexSelect);
					setControllModeTo_Editable();
					eventTableSelectRow();
				}
			}
		});
		btnRight.setEnabled(false);
		pnlSelect.add(btnRight);
		
		btnMaxRight = new JButton(">|");
		btnMaxRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int rowCount = tblBook.getRowCount();
				if (rowCount > 0)
				{
					indexSelect = rowCount - 1;
					tblBook.setRowSelectionInterval(indexSelect, indexSelect);
					setControllModeTo_Editable();
					eventTableSelectRow();
				}
			}
		});
		pnlSelect.add(btnMaxRight);
		
		tblBook = new JTableRed();
		tblBook.setShowHorizontalLines(false);
		tblBook.setShowVerticalLines(true);
		tblBook.setDragEnabled(true);
		tblBook.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				eventTableSelectRow();
			}
		});
		tblBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				eventTableSelectRow();
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (e.getClickCount() >= 2)
				{
					showBookDetail();
				}
				if (indexSelect != -1 && SwingUtilities.isRightMouseButton(e))
				{
					popupMenu.show(tblBook, e.getX(), e.getY());
				}
			}
		});
		tblBook.setRowHeight(35);
		tblBook.setModel(new DefaultTableModel(null, new String[] {"MÃ SÁCH", "TÊN SÁCH", "THỂ LOẠI", "TÁC GIẢ", "SỐ LƯỢNG", "GIÁ", "GHI CHÚ"} ) 
		{
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		});
		tblBook.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblBook.getColumnModel().getColumn(1).setPreferredWidth(200);
		scrollPane.setViewportView(tblBook);
		
		btnExportExcel = new JButton("Xuất Excel");
		btnExportExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try {
				JFileChooser chooser = new JFileChooser();
				int status = chooser.showOpenDialog(contentPane);
				
				if (status == chooser.APPROVE_OPTION)
				{
					String path = chooser.getSelectedFile().getAbsoluteFile().toString();
					File file = new File(path + ".xls");
					if (ExportExcel.writeBook(file, BookDAO.getAll()))
					{
						Desktop.getDesktop().open(file);
					}
				}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnExportExcel.setIcon(new ImageIcon(BookJFrame.class.getResource("/com/duan/icon/icons8_microsoft_excel_2019_16px.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(lblTmKim, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
							.addComponent(btnExportExcel))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE))
					.addGap(10)
					.addComponent(pnlController, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
					.addGap(3))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(pnlSelect, GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(pnlTime, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
					.addGap(3))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTmKim, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(1)
									.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnExportExcel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
							.addGap(6))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(pnlController, GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlSelect, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlTime, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
		);
		
		JButton btnRefresh = new JButton("Tải lại");
		pnlTime.add(btnRefresh, BorderLayout.CENTER);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				refresh();
			}
		});
		btnRefresh.setIcon(new ImageIcon(BookJFrame.class.getResource("/com/duan/icon/icons8_synchronize_24px.png")));
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.setLayout(gl_contentPane);
		//setLocationRelativeTo(getOwner());
		try 
		{
			getDataToList();
			fillToTable();
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
	}
	
	//Lấy dữ liệu BOOK từ database đổ vào list
	public void getDataToList()
	{
		try 
		{
			listBook.clear();
			listBook = BookDAO.getAll();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Đổ dữ liệu từ list vào table
	public void fillToTable() throws SQLException
	{
		DefaultTableModel model = (DefaultTableModel) tblBook.getModel();
		model.setRowCount(0);
		
		for (Book e : listBook)
		{
			String price = DataHelper.getFormatForMoney(e.getPrice()) + SettingSave.getSetting().getMoneySymbol();
			String categoryTitle = CategoryDAO.getTitleById(e.getCategoryId());
			String authorFullname = AuthorDAO.findById(e.getAuthorId()).getFullName();
			String[] rowData = 
				{
					e.getId(), 
					e.getTitle(), 
					categoryTitle, 
					authorFullname, 
					e.getAmount() + "", 
					price, 
					e.getDescription(), 
				};
			
			model.addRow(rowData);
		}
		
		int rowCount = tblBook.getRowCount();
		
		//Nếu điều kiện hợp lý thì set select row lại y như lúc chưa fillToTable
		if (indexSelect != -1)
		{
			if (indexSelect < rowCount && rowCount > 0)
			{
				tblBook.setRowSelectionInterval(indexSelect, indexSelect);
			}
			else
			{
				indexSelect = rowCount - 1;
				if (indexSelect > -1)
				{
					tblBook.setRowSelectionInterval(indexSelect, indexSelect);
				}
				else
				{
					setControllModeTo_Nothing();
				}
			}
		}
	}
	
	public void search() throws SQLException
	{
		String search = txtSearch.getText();
		
		DefaultTableModel model = (DefaultTableModel) tblBook.getModel();
		model.setRowCount(0);
		
		for (Book e : listBook)
		{
			if (DataHelper.search(e.getSearchString(), search) == false)
				continue;
			String price = DataHelper.getFormatForMoney(e.getPrice()) + SettingSave.getSetting().getMoneySymbol();
			String categoryTitle = CategoryDAO.getTitleById(e.getCategoryId());
			String authorFullname = AuthorDAO.findById(e.getAuthorId()).getFullName();
			
			String[] rowData = 
				{
					e.getId(), 
					e.getTitle(), 
					categoryTitle, 
					authorFullname, 
					e.getAmount() + "", 
					price, 
					e.getDescription(), 
				};
			
			model.addRow(rowData);
		}
		
		//Nếu điều kiện hợp lý thì set select row lại y như lúc chưa fillToTable
		int rowCount = tblBook.getRowCount();
		if (indexSelect != -1)
		{
			if (indexSelect < rowCount && rowCount > 0)
			{
				tblBook.setRowSelectionInterval(indexSelect, indexSelect);
			}
			else
			{
				indexSelect = rowCount - 1;
				if (indexSelect > -1)
				{
					tblBook.setRowSelectionInterval(indexSelect, indexSelect);
				}
				else
				{
					setControllModeTo_Nothing();
				}
			}
		}
	}
	
	public void showBookDetail()
	{
		try 
		{
			String id = (String) tblBook.getValueAt(indexSelect, 0);
			Book bookDetail;
			bookDetail = BookDAO.findByID(id);
			
			bookDetailJDialog = new BookDetailJDialog();
			bookDetailJDialog.setLocationRelativeTo(this);
			bookDetailJDialog.setDetail(bookDetail);
			bookDetailJDialog.setVisible(true);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Hiện lên JFrame để insertBook
	public void showInsertBook()
	{
		inserBookJFrame.setBookJFrame(this);
		inserBookJFrame.setVisible(true);
	}
	
	//Hiện lên JFrame để edit book
	public void showEditorBook(Book book) throws SQLException
	{
		editorBookJDialog.setBookEditor(book);
		editorBookJDialog.setBookJFrame(this);
		editorBookJDialog.showDataToForm(book);
		editorBookJDialog.setVisible(true);
	}
	
	//Tiến hành xóa sách đang được chọn
	public boolean deleteBook() throws SQLException 
	{
		String bookId = getBookSelected().getId();
		return BookDAO.delete(bookId);
	}
	
	//Trả về model Book đang được chọn trên tblBook
	public Book getBookSelected() throws SQLException
	{
		if (indexSelect != -1)
		{
			String bookId = tblBook.getValueAt(indexSelect, 0).toString();
			return BookDAO.findByID(bookId);
		}
		return null;
	}
	
	
	public void eventTableSelectRow()
	{
		indexSelect = tblBook.getSelectedRow();
		setControllModeTo_Editable();
		Rectangle cellRect = tblBook.getCellRect(indexSelect, 0, true);
		tblBook.scrollRectToVisible(cellRect);
	}
	
	
	
	//Set các nút nhấn controll chỉ enable nút "Thêm Mới"
	//Chỉ gọi khi không có dòng nào trong bảng tblBook được chọn
	public void setControllModeTo_Nothing()
	{
		btnAdd.setEnabled(true);
		
		btnDelete.setEnabled(false);
		btnDetail.setEnabled(false);
		btnEdit.setEnabled(false);
		
		//Các nút di chuyển select
		btnLeft.setEnabled(false);
		btnRight.setEnabled(false);
	}
	
	//Set các nút nhấn controll chỉ enable nút "Thêm Mới"
	//Chỉ gọi khi không có dòng nào trong bảng tblBook được chọn
	public void setControllModeTo_Editable()
	{
		btnDelete.setEnabled(true);
		btnDetail.setEnabled(true);
		btnEdit.setEnabled(true);
		btnAdd.setEnabled(true);
		
		//Các nút di chuyển select
		btnLeft.setEnabled(true);
		btnRight.setEnabled(true);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	//Tải lại tất cả
	public void refresh()
	{
		getDataToList();
		try {
			fillToTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
