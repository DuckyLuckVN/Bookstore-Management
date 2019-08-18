package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;

import com.duan.custom.common.BookJPanel;
import com.duan.custom.common.JTableRed;
import com.duan.dao.AdminDAO;
import com.duan.dao.AuthorDAO;
import com.duan.dao.BookDAO;
import com.duan.dao.OrderDAO;
import com.duan.dao.OrderDetailDAO;
import com.duan.dao.PublisherDAO;
import com.duan.dao.RentBookDAO;
import com.duan.dao.RentBookDetailDAO;
import com.duan.dao.UserDAO;
import com.duan.helper.DataHelper;
import com.duan.helper.DateHelper;
import com.duan.helper.SettingSave;
import com.duan.model.Book;
import com.duan.model.Order;
import com.duan.model.RentBook;
import com.duan.model.RentBookDetail;
import com.duan.model.User;
import com.duan.custom.message.MessageOptionPane;
import com.duan.helper.AccountSave;
import com.duan.helper.SettingSave;

import javax.swing.JTabbedPane;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserMainJFrame extends JFrame {
	
	private JPopupMenu popupMenu;
	private int indexSelect = -1;
	private BookDetailJDialog bookDetailJDialog = new BookDetailJDialog(this);
	private OrderDetailJDialog orderDetailJDialog = new OrderDetailJDialog(this);
	private RentBookDetailJDialog rentBookDetailJDialog = new RentBookDetailJDialog(this);
	DefaultTableModel model;
	ArrayList<Book> listBook = new ArrayList<Book>();
	ArrayList<Order> listOrder = new ArrayList<Order>();
	ArrayList<RentBook> listRentBook = new ArrayList<RentBook>();
	int index = -1;
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	private JPanel contentPane;
	private JTextField txtSearchBook;
	private JTable tblBook;
	private JTableRed tblOrder;
	private JTableRed tblRentBook;
	private JTextField txtSearchRentBook;
	private JTextField txtSearchOrder;

	private ProfileUserJDialog profileUserJDialog = new ProfileUserJDialog();
	private JLabel lblUserFullname;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMainJFrame frame = new UserMainJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public UserMainJFrame() 
	{
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) 
			{
				if (MessageOptionPane.showConfirmDialog(contentPane, "Bạn có chắc muốn thoát không?"))
				{
					dispose();
				}
			}
		});
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 881, 704);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel pnlBook = new JPanel();
		tabbedPane.addTab("Tra cứu sách", new ImageIcon(UserMainJFrame.class.getResource("/com/duan/icon/icons8_books_32px_1.png")), pnlBook, null);
		pnlBook.setLayout(null);
		
		JLabel lblSearchBook = new JLabel("Tra cứu sách:");
		lblSearchBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSearchBook.setBounds(10, 11, 85, 29);
		pnlBook.add(lblSearchBook);
		
		txtSearchBook = new JTextField();
		txtSearchBook.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				try 
				{
					searchBook();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		txtSearchBook.setBounds(105, 11, 240, 27);
		pnlBook.add(txtSearchBook);
		txtSearchBook.setColumns(10);
		
		JScrollPane scrollPaneBook = new JScrollPane();
		scrollPaneBook.setBounds(10, 51, 830, 497);
		pnlBook.add(scrollPaneBook);
		
		tblBook = new JTableRed();
		tblBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() >= 2 ) 
				{
					showBookDetail();
				}
				if (indexSelect != -1 && SwingUtilities.isRightMouseButton(e))
				{
					popupMenu.show(tblBook, e.getX(), e.getY());
				}
			}
		});
		tblBook.setRowHeight(30);
		tblBook.setModel(new DefaultTableModel(null, new String[] {"MÃ SÁCH", "TÊN SÁCH", "TÁC GIẢ", "NHÀ XUẤT BẢN", "NĂM XUẤT BẢN", "SỐ TRANG", "GIÁ BÁN"}) 
		{
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		});
		tblBook.getColumnModel().getColumn(0).setPreferredWidth(0);
		scrollPaneBook.setViewportView(tblBook);
		
		JPanel pnlRentBook = new JPanel();
		tabbedPane.addTab("Tra cứu đơn thuê", new ImageIcon(UserMainJFrame.class.getResource("/com/duan/icon/icons8_bookmark_32px.png")), pnlRentBook, null);
		pnlRentBook.setLayout(null);
		
		JLabel lblSearchRentBook = new JLabel("Tra cứu đơn:");
		lblSearchRentBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSearchRentBook.setBounds(10, 11, 81, 29);
		pnlRentBook.add(lblSearchRentBook);
		
		txtSearchRentBook = new JTextField();
		txtSearchRentBook.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				try {
					searchRentBook();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		txtSearchRentBook.setColumns(10);
		txtSearchRentBook.setBounds(101, 11, 240, 27);
		pnlRentBook.add(txtSearchRentBook);
		
		JScrollPane scrollPaneRentBook = new JScrollPane();
		scrollPaneRentBook.setBounds(10, 51, 830, 497);
		pnlRentBook.add(scrollPaneRentBook);
		
		 tblRentBook = new JTableRed();
		 tblRentBook.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) 
		 	{
		 		if (e.getClickCount() >= 0) 
		 		{
		 			showRentBookDetail();
				}
		 	}
		 });
		 tblRentBook.setRowHeight(30);
		tblRentBook.setModel(new DefaultTableModel(null, new String[] {"MÃ ĐƠN", "NGƯỜI THUÊ", "NHÂN VIÊN TRỰC", "NGÀY THUÊ", "NGÀY TRẢ", "TỔNG SÁCH", "TỔNG PHÍ"}) 
		{
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		});
		tblRentBook.getColumnModel().getColumn(0).setPreferredWidth(0);
		scrollPaneRentBook.setViewportView(tblRentBook);
		
		JPanel pnlOrder = new JPanel();
		tabbedPane.addTab("Tra cứu đơn mua sách", new ImageIcon(UserMainJFrame.class.getResource("/com/duan/icon/icons8_buy_for_change_32px.png")), pnlOrder, null);
		pnlOrder.setLayout(null);
		
		JLabel lblSearchOrder = new JLabel("Tra cứu đơn:");
		lblSearchOrder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSearchOrder.setBounds(10, 11, 81, 29);
		pnlOrder.add(lblSearchOrder);
		
		txtSearchOrder = new JTextField();
		txtSearchOrder.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				try 
				{
					searchOrder();
				} catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		txtSearchOrder.setColumns(10);
		txtSearchOrder.setBounds(101, 11, 240, 27);
		pnlOrder.add(txtSearchOrder);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 830, 497);
		pnlOrder.add(scrollPane);
		
		 tblOrder = new JTableRed();
		 tblOrder.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent evt)
		 	{
		 		if (evt.getClickCount() >= 2)
				{
					showOrderDetail();
				}
		 		
		 	}
		 	
		 });
		 tblOrder.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyReleased(KeyEvent e) 
		 	{
		 	}
		 	
		 });
		 tblOrder.setRowHeight(30);
		tblOrder.setModel(new DefaultTableModel(null, new String[] {"MÃ ĐƠN", "NGƯỜI MUA", "NHÂN VIÊN TRỰC", "NGÀY MUA", "TỔNG SÁCH", "TỔNG PHÍ"}) 
		{
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		});
		tblOrder.getColumnModel().getColumn(0).setPreferredWidth(0);
		scrollPane.setViewportView(tblOrder);
		
		JPanel pnlProfile = new JPanel();
		pnlProfile.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 2, true), "Th\u00F4ng tin c\u1EE7a b\u1EA1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlProfile.setLayout(new BorderLayout(0, 0));
		
		lblUserFullname = new JLabel("Xin chào: Nguyễn Đại Hào");
		lblUserFullname.setForeground(Color.DARK_GRAY);
		lblUserFullname.setBorder(null);
		lblUserFullname.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserFullname.setPreferredSize(new Dimension(400, 35));
		lblUserFullname.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlProfile.add(lblUserFullname, BorderLayout.WEST);
		
		JPanel pnlControllProfile = new JPanel();
		pnlProfile.add(pnlControllProfile, BorderLayout.EAST);
		pnlControllProfile.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnChangeProfile = new JButton("Đổi thông tin");
		btnChangeProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				showProfileUserJDialog();
			}
		});
		pnlControllProfile.add(btnChangeProfile);
		
		JButton btnLogout = new JButton("Đăng xuất");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (MessageOptionPane.showConfirmDialog(contentPane, "Bạn có chắc muốn đăng xuất?"))
				{
					logout();
				}
			}
		});
		pnlControllProfile.add(btnLogout);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
				.addComponent(pnlProfile, GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
					.addComponent(pnlProfile, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
		loadDataBookToList();
		loadDataRentbook();
		loadToOrderBook();
	}
	
	public void loadDataBookToList()
	{
		try 
		{
			listBook = BookDAO.getAll();
			if (listBook.size() > 0 ) 
			{
				fillToTblBook();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	public void fillToTblBook() throws SQLException
	{
		model = (DefaultTableModel) tblBook.getModel();
		model.setRowCount(0);
		for (Book b : listBook) 
		{
			String price = DataHelper.getFormatForMoney(b.getPrice()) + SettingSave.getSetting().getMoneySymbol();
			String nameAuthor = AuthorDAO.findById(b.getAuthorId()).getFullName();
			String namePublisher = PublisherDAO.findById(b.getPublisherId()).getName();
			String [] rows = {b.getId(),b.getTitle(),nameAuthor,namePublisher,format.format(b.getPublicationYear()),b.getPageNum()+"",price};
			model.addRow(rows);
		}
	}	
	
	public void searchBook() throws SQLException
	{
		model = (DefaultTableModel) tblBook.getModel();
		model.setRowCount(0);
		String search = txtSearchBook.getText();
		for (Book b : listBook) 
		{
			if (DataHelper.search(b.getSearchString(), search) == true)
			{
				String price = DataHelper.getFormatForMoney(b.getPrice()) + SettingSave.getSetting().getMoneySymbol();
				String nameAuthor = AuthorDAO.findById(b.getAuthorId()).getFullName();
				String namePublisher = PublisherDAO.findById(b.getPublisherId()).getName();
				String [] rows = {b.getId(),b.getTitle(),nameAuthor,namePublisher,format.format(b.getPublicationYear()),b.getPageNum()+"",price};
				model.addRow(rows);
			}
			
		}
	}
	
	public void loadDataRentbook()
	{
		try 
		{
			User user = AccountSave.getUser();
			listRentBook = RentBookDAO.getAllOfUser(user.getId());
			if (listRentBook.size() > 0) 
			{
				fillToTblrentbook();
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	public void fillToTblrentbook() throws SQLException
	{
		model = (DefaultTableModel) tblRentBook.getModel();
		model.setRowCount(0);
		Book b;
		for (RentBook rb : listRentBook) 
		{
			String nameUser = UserDAO.findByID(rb.getUserId()).getFullname();
			String nameAdmin = AdminDAO.findByID(rb.getAdminId()).getFullname();	
			
			int amount = RentBookDetailDAO.findById(rb.getId()).getAmount();
			double price = RentBookDetailDAO.findById(rb.getId()).getPrice();
			Object [] rows = {rb.getId(),nameUser,nameAdmin,rb.getCreatedDate(),rb.getReturnedDate(),amount,rb.getCostRent()*amount};
			model.addRow(rows);
		}
	}
	
	public void searchRentBook() throws SQLException
	{
		model = (DefaultTableModel) tblRentBook.getModel();
		model.setRowCount(0);
		String search = txtSearchRentBook.getText();
		for (RentBook rb : listRentBook) 
		{
			if (DataHelper.search(rb.getSearchString(), search) == true) 
			{
				String nameUser = UserDAO.findByID(rb.getUserId()).getFullname();
				String nameAdmin = AdminDAO.findByID(rb.getAdminId()).getFullname();	
				
				int amount = RentBookDetailDAO.findById(rb.getId()).getAmount();
				double price = RentBookDetailDAO.findById(rb.getId()).getPrice();
				Object [] rows = {rb.getId(),nameUser,nameAdmin,rb.getCreatedDate(),rb.getReturnedDate(),amount,price};
				model.addRow(rows);
			}
		}
	}
	
	public void loadToOrderBook()
	{
		try 
		{
			User user = AccountSave.getUser();
			listOrder = OrderDAO.getAllOfUser(user.getId());
			if (listOrder.size() > 0) 
			{
				fillToTblOrder();
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void fillToTblOrder() throws SQLException
	{
		model = (DefaultTableModel) tblOrder.getModel();
		model.setRowCount(0);
		for (Order od : listOrder) 
		{
			String nameUser = UserDAO.findByID(od.getUserId()).getFullname();
			String nameAdmin = AdminDAO.findByID(od.getAdminId()).getFullname();
			int amount = OrderDetailDAO.findByID(od.getId()).getAmount();
			double price = OrderDetailDAO.findByID(od.getId()).getPrice();
			Object [] rows = {od.getId(),nameUser,nameAdmin,od.getDateCreated(),amount,price*amount};
			model.addRow(rows);
		}
		showDetail();
	}

	
	public void showDetail()
	{
		lblUserFullname.setText("Xin chào: " + AccountSave.getUser().getFullname());
	}
	
	public void showProfileUserJDialog()
	{
		profileUserJDialog = new ProfileUserJDialog();
		profileUserJDialog.setMainJFrame(this);
		profileUserJDialog.setLocationRelativeTo(getContentPane());
		profileUserJDialog.setVisible(true);
	}

	public void searchOrder() throws SQLException
	{
		model = (DefaultTableModel) tblOrder.getModel();
		model.setRowCount(0);
		String search = txtSearchOrder.getText();
		for (Order od : listOrder) 
		{
			if (DataHelper.search(od.getSearchString(), search) )
				{
				String nameUser = UserDAO.findByID(od.getUserId()).getFullname();
				String nameAdmin = AdminDAO.findByID(od.getAdminId()).getFullname();
				int amount = OrderDetailDAO.findByID(od.getId()).getAmount();
				double price = OrderDetailDAO.findByID(od.getId()).getPrice();
				Object [] rows = {od.getId(),nameUser,nameAdmin,od.getDateCreated(),amount,price};
				model.addRow(rows);
				}
			
		}
	}
	public void showBookDetail()
	{
		try 
		{
			indexSelect = tblBook.getSelectedRow();
			//Lay ra id sach dang duoc chon
			String id = (String) tblBook.getValueAt(indexSelect, 0);
			//Lay ve doi tuong cua id do tren DB
			Book bookDetail = BookDAO.findByID(id);
			
			//Tao 1 jdialog BookDetailJDialog de hien thi
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
	public void showOrderDetail()
	{
		indexSelect = tblOrder.getSelectedRow();
		int order_id = DataHelper.getInt( tblOrder.getValueAt(indexSelect, 0).toString() );
		orderDetailJDialog.setLocationRelativeTo(this);
		orderDetailJDialog.setDetailModel(order_id);
		orderDetailJDialog.showDetail();
		orderDetailJDialog.fillToTable();
		orderDetailJDialog.setVisible(true);
		
	}
	public void showRentBookDetail()
	{
		indexSelect = tblRentBook.getSelectedRow();
		int rentbook_id = DataHelper.getInt(tblRentBook.getValueAt(indexSelect, 0).toString());
		rentBookDetailJDialog = new RentBookDetailJDialog();
		rentBookDetailJDialog.setLocationRelativeTo(this);
		rentBookDetailJDialog.setDetailModel(rentbook_id);
		rentBookDetailJDialog.showDetail();
		rentBookDetailJDialog.fillToTable();
		rentBookDetailJDialog.setVisible(true);
	}
	
	public void logout()
	{
		AccountSave.removeUser();
		dispose();
		new LoginJFrame().setVisible(true);
	}
}
