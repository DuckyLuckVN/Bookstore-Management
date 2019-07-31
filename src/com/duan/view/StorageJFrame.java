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

import com.duan.dao.BookDAO;
import com.duan.dao.CategoryDAO;
import com.duan.helper.DataHelper;
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
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;

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
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JTable tblStorage;
	private JButton btnDetail;
	
	
	private List<Book> listBook = new ArrayList<Book>();
	private Book book;
	private int indexSelect = -1;
	private JTable tblBook;

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
		setBounds(100, 100, 805, 672);
		
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
		pnlController.setBounds(624, 5, 159, 313);
		pnlController.setLayout(new GridLayout(0, 1, 0, 5));
		pnlController.setPreferredSize(new Dimension(150, 5));
		
		btnDetail = new JButton(" Đơn mới");
		btnDetail.setHorizontalAlignment(SwingConstants.LEFT);
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnDetail.setIcon(new ImageIcon(StorageJFrame.class.getResource("/com/duan/icon/Create.png")));
		btnDetail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlController.add(btnDetail);
		
		btnAdd = new JButton(" Lưu lại");
		btnAdd.setHorizontalAlignment(SwingConstants.LEFT);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnAdd.setIcon(new ImageIcon(StorageJFrame.class.getResource("/com/duan/icon/Accept.png")));
		btnAdd.setSelectedIcon(new ImageIcon(StorageJFrame.class.getResource("/com/duan/icon/icons8_add_64px.png")));
		pnlController.add(btnAdd);
		
		btnEdit = new JButton(" Cập nhật");
		btnEdit.setHorizontalAlignment(SwingConstants.LEFT);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnEdit.setIcon(new ImageIcon(StorageJFrame.class.getResource("/com/duan/icon/Notes.png")));
		pnlController.add(btnEdit);
		
		btnDelete = new JButton("Xóa");
		btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnDelete.setIcon(new ImageIcon(StorageJFrame.class.getResource("/com/duan/icon/icons8_delete_32px_1.png")));
		pnlController.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(7, 329, 778, 272);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_1.setBounds(7, 46, 611, 272);
		
		tblBook = new JTable();
		tblBook.setModel(new DefaultTableModel(null, new String[] {"MÃ SÁCH", "TÊN SÁCH", "GIÁ BÁN", "GIÁ NHẬP", "SỐ LƯỢNG"}) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(tblBook);
		
		tblStorage = new JTable();
		tblStorage.setBorder(new EmptyBorder(0, 0, 0, 0));
		tblStorage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				eventTableSelectRow();
			}
		});
		tblStorage.addMouseListener(new MouseAdapter() {
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
					//showBookDetail();
				}
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
		contentPane.setLayout(null);
		contentPane.add(scrollPane_1);
		contentPane.add(pnlController);
		contentPane.add(scrollPane);
		
		JLabel lblChnSch = new JLabel("Chọn sách:");
		lblChnSch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChnSch.setBounds(7, 5, 70, 30);
		contentPane.add(lblChnSch);
		
		JButton btnChnSch = new JButton("Chọn sách");
		btnChnSch.setBounds(87, 5, 89, 30);
		contentPane.add(btnChnSch);
		
		JButton btnDeleteBook = new JButton("Xóa");
		btnDeleteBook.setBounds(539, 6, 79, 30);
		contentPane.add(btnDeleteBook);
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
		DefaultTableModel model = (DefaultTableModel) tblStorage.getModel();
		model.setRowCount(0);
		
		for (Book e : listBook)
		{
			String price = DataHelper.getFormatForMoney(e.getPrice()) + " đ";
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
				}
			}
		}
	}
	
	
	
	
	public void eventTableSelectRow()
	{
		indexSelect = tblStorage.getSelectedRow();
		setControllModeTo_Editable();
	}
	
	
	
	//Set các nút nhấn controll chỉ enable nút "Thêm Mới"
	//Chỉ gọi khi không có dòng nào trong bảng tblBook được chọn
	public void setControllModeTo_Nothing()
	{
		btnAdd.setEnabled(true);
		
		btnDelete.setEnabled(false);
		btnDetail.setEnabled(false);
		btnEdit.setEnabled(false);
		
	}
	
	//Set các nút nhấn controll chỉ enable nút "Thêm Mới"
	//Chỉ gọi khi không có dòng nào trong bảng tblBook được chọn
	public void setControllModeTo_Editable()
	{
		btnDelete.setEnabled(true);
		btnDetail.setEnabled(true);
		btnEdit.setEnabled(true);
		btnAdd.setEnabled(true);
		
	}
}
