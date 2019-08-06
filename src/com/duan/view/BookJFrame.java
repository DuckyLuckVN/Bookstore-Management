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

import com.duan.custom.CustomJTableRed;
import com.duan.dao.BookDAO;
import com.duan.dao.CategoryDAO;
import com.duan.helper.DataHelper;
import com.duan.helper.SettingSave;
import com.duan.helper.SwingHelper;
import com.duan.model.Book;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JPopupMenu;
import java.awt.Component;

public class BookJFrame extends JFrame {

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
	private CustomJTableRed tblBook;
	private JPanel pnlSelect;
	private JButton btnMaxLeft;
	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnMaxRight;
	private JPanel pnlTime;
	
	private BookEditorJDialog inserBookJFrame = new BookEditorJDialog();
	private BookEditorJDialog editorBookJDialog = new BookEditorJDialog();
	private FindBookJDialog findBookJDialog = new FindBookJDialog(this);
	private BookDetailJDialog bookDetailJFrame = new BookDetailJDialog(this);
	private JLabel lblTmKim;
	private JTextField textField;
	
	private List<Book> listBook = new ArrayList<Book>();
	private Book book;
	private int indexSelect = -1;
	private JButton btnNhpKho;
	private JPopupMenu popupMenu;
	private JMenuItem mntmXemChiTit;
	private JMenuItem mntmSa;
	private JMenuItem mntmXa;

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
		setBounds(100, 100, 1000, 600);
		
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
				showInsertBook();
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
					System.out.println(getBookSelected().getImage());
					showEditorBook(getBookSelected());
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
					if (SwingHelper.showConfirm(contentPane, "Bạn có chắc muốn xóa sách này không?"))
					{
						if (deleteBook())
						{
							getDataToList();
							fillToTable();
							JOptionPane.showMessageDialog(getContentPane(), "Đã xóa sách thành công!");
						}
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
		scrollPane.setBorder(new TitledBorder(null, "B\u1EA3ng d\u1EEF li\u1EC7u", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		pnlSelect = new JPanel();
		
		pnlTime = new JPanel();
		pnlTime.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlTime.setBackground(SystemColor.menu);
		
		lblTmKim = new JLabel("Tìm kiếm:");
		lblTmKim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		textField = new JTextField();
		textField.setBorder(null);
		textField.setColumns(10);
		
		btnNhpKho = new JButton("Nhập kho");
		
		popupMenu = new JPopupMenu();
		
		mntmXemChiTit = new JMenuItem("Xem chi tiết");
		popupMenu.add(mntmXemChiTit);
		
		mntmSa = new JMenuItem("Sửa");
		popupMenu.add(mntmSa);
		
		mntmXa = new JMenuItem("Xóa");
		popupMenu.add(mntmXa);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
						.addComponent(pnlSelect, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTmKim, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 360, Short.MAX_VALUE)
							.addComponent(btnNhpKho, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlTime, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlController, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTmKim, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNhpKho, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE))
						.addComponent(pnlController, GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlTime, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlSelect, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
		);
		pnlTime.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTime = new JLabel("23:15");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setIcon(new ImageIcon(BookJFrame.class.getResource("/com/duan/icon/icons8_alarm_clock_24px_1.png")));
		lblTime.setForeground(Color.RED);
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlTime.add(lblTime);
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
		
		tblBook = new CustomJTableRed();
		tblBook.setShowHorizontalLines(false);
		tblBook.setShowVerticalLines(true);
		tblBook.setDragEnabled(true);
		tblBook.setBorder(new EmptyBorder(0, 0, 0, 0));
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
			String[] rowData = 
				{
					e.getId(), 
					e.getTitle(), 
					categoryTitle, 
					e.getAuthor(), 
					e.getAmount() + "", 
					price, 
					e.getDescription(), 
				};
			
			model.addRow(rowData);
			model.addRow(rowData);
			model.addRow(rowData);
			model.addRow(rowData);
			model.addRow(rowData);
			model.addRow(rowData);
			model.addRow(rowData);
			model.addRow(rowData);
			model.addRow(rowData);
			model.addRow(rowData);
			model.addRow(rowData);
			model.addRow(rowData);
			model.addRow(rowData);
			model.addRow(rowData);
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
	
	public void showBookDetail()
	{
		try 
		{
			String id = (String) tblBook.getValueAt(indexSelect, 0);
			Book bookDetail;
			bookDetail = BookDAO.findByID(id);
			bookDetailJFrame.setDetail(bookDetail);
			bookDetailJFrame.setVisible(true);
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
}
