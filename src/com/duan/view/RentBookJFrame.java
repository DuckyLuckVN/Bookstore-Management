package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.print.attribute.standard.SheetCollate;
import javax.security.auth.Refreshable;
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

import com.duan.custom.common.JTableRed;
import com.duan.custom.message.MessageOptionPane;
import com.duan.dao.AdminDAO;
import com.duan.dao.RentBookDAO;
import com.duan.dao.RentBookDetailDAO;
import com.duan.dao.UserDAO;
import com.duan.helper.DataHelper;
import com.duan.helper.DateHelper;
import com.duan.helper.SettingSave;
import com.duan.helper.SwingHelper;
import com.duan.model.Admin;
import com.duan.model.RentBook;
import com.duan.model.User;

import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;

public class RentBookJFrame extends JFrame {

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
	private JTableRed tblRentBook;
	private JPanel pnlSelect;
	private JButton btnMaxLeft;
	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnMaxRight;
	private JPanel pnlTime;
	private JLabel lblTmKim;
	private JTextField txtSearch;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnDetail;
	
	private RentBookEditorJDialog insertRentBookJDialog = new RentBookEditorJDialog(this);
	private RentBookEditorJDialog editorRentbookJDialog = new RentBookEditorJDialog(this);
	private RentBookDetailJDialog rentBookDetailJDialog = new RentBookDetailJDialog();
	
	private List<RentBook> listRentBook = new ArrayList<RentBook>();
	private int indexSelect = -1;
	private JComboBox cboStatus;
	private JButton btnRefresh;
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					RentBookJFrame frame = new RentBookJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public RentBookJFrame() 
	{
		setTitle("Quản Lý Thuê Sách");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RentBookJFrame.class.getResource("/com/duan/icon/icons8_bookmark_64px_2.png")));
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
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				showRentBookDetail();
			}
		});
		SwingHelper.setTextBelowIconButton(btnDetail);
		btnDetail.setIcon(new ImageIcon(RentBookJFrame.class.getResource("/com/duan/icon/icons8_details_popup_50px_1.png")));
		btnDetail.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnlController.add(btnDetail);
		
		btnAdd = new JButton("Thêm mới");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showInsertRentBook();
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		SwingHelper.setTextBelowIconButton(btnAdd);
		btnAdd.setIcon(new ImageIcon(RentBookJFrame.class.getResource("/com/duan/icon/icons8_add_50px_3.png")));
		btnAdd.setSelectedIcon(new ImageIcon(RentBookJFrame.class.getResource("/com/duan/icon/icons8_add_64px.png")));
		pnlController.add(btnAdd);
		
		btnEdit = new JButton("Thay đổi");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					showEditRentbook();
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 12));
		SwingHelper.setTextBelowIconButton(btnEdit);
		btnEdit.setIcon(new ImageIcon(RentBookJFrame.class.getResource("/com/duan/icon/icons8_edit_property_50px.png")));
		pnlController.add(btnEdit);
		
		btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					if (MessageOptionPane.showConfirmDialog(contentPane, "Bạn có chắc muốn xóa không?"))
					{
						if (deleteRentBook())
						{
							getDataToList();
							fillToTable();
						}
					}
				}
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		SwingHelper.setTextBelowIconButton(btnDelete);
		btnDelete.setIcon(new ImageIcon(RentBookJFrame.class.getResource("/com/duan/icon/icons8_delete_50px.png")));
		pnlController.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "B\u1EA3ng d\u1EEF li\u1EC7u", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		pnlSelect = new JPanel();
		
		pnlTime = new JPanel();
		pnlTime.setBackground(SystemColor.menu);
		
		lblTmKim = new JLabel("Tìm kiếm:");
		lblTmKim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				try {
					search();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		txtSearch.setColumns(10);
		
		JLabel lblTnhTrng = new JLabel("Tình trạng:");
		lblTnhTrng.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		cboStatus = new JComboBox();
		cboStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try {
					search();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		cboStatus.setModel(new DefaultComboBoxModel(new String[] {"Toàn bộ", "Đang thuê", "Đã trả", "Quá hạn"}));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTnhTrng, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboStatus, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 281, Short.MAX_VALUE)
							.addComponent(lblTmKim, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
						.addComponent(pnlSelect, GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlTime, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlController, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTnhTrng, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTmKim, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(cboStatus, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
						.addComponent(pnlController, GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlTime, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlSelect, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
		);
		pnlTime.setLayout(new BorderLayout(0, 0));
		
		btnRefresh = new JButton("Tải lại");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				refresh();
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRefresh.setIcon(new ImageIcon(RentBookJFrame.class.getResource("/com/duan/icon/icons8_synchronize_24px.png")));
		pnlTime.add(btnRefresh, BorderLayout.CENTER);
		pnlSelect.setLayout(new GridLayout(1, 0, 15, 0));
		
		btnMaxLeft = new JButton("|<");
		btnMaxLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int rowCount = tblRentBook.getRowCount();
				if (rowCount > 0)
				{
					indexSelect = 0;
					tblRentBook.setRowSelectionInterval(indexSelect, indexSelect);
					setControllModeTo_Editable();
				}
			}
		});
		pnlSelect.add(btnMaxLeft);
		
		btnLeft = new JButton("<");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int rowCount = tblRentBook.getRowCount();
				if (indexSelect > 0 && rowCount > 0)
				{
					indexSelect--;
					tblRentBook.setRowSelectionInterval(indexSelect, indexSelect);
					setControllModeTo_Editable();
				}
			}
		});
		btnLeft.setEnabled(false);
		pnlSelect.add(btnLeft);
		
		btnRight = new JButton(">");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int rowCount = tblRentBook.getRowCount();
				if (indexSelect < rowCount - 1 && rowCount > 0)
				{
					indexSelect++;
					tblRentBook.setRowSelectionInterval(indexSelect, indexSelect);
					setControllModeTo_Editable();
				}
			}
		});
		btnRight.setEnabled(false);
		pnlSelect.add(btnRight);
		
		btnMaxRight = new JButton(">|");
		btnMaxRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int rowCount = tblRentBook.getRowCount();
				if (rowCount > 0)
				{
					indexSelect = rowCount - 1;
					tblRentBook.setRowSelectionInterval(indexSelect, indexSelect);
					setControllModeTo_Editable();
				}
			}
		});
		pnlSelect.add(btnMaxRight);
		
		tblRentBook = new JTableRed();
		tblRentBook.setRowHeight(30);
		tblRentBook.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				eventTableSelectRow();
			}
		});
		tblRentBook.addMouseListener(new MouseAdapter() {
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
					showRentBookDetail();
				}
			}
		});
		tblRentBook.setModel(new DefaultTableModel(null, new String[] {"MÃ SỐ", "TÀI KHOẢNG THUÊ", "QUẢN TRỊ VIÊN", "NGÀY THUÊ", "NGÀY TRẢ", "TÌNH TRẠNG"} ) 
		{
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tblRentBook.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(tblRentBook);
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(getOwner());
		
		setControllModeTo_Nothing();
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
	
	//Lấy dữ liệu từ SQL Server về listRentbook
	public void getDataToList() throws SQLException
	{
		listRentBook = RentBookDAO.getAll();
	}
	
	//Đổ dữ liệu vào tblBook dựa trên list và fillter
	public void fillToTable() throws SQLException
	{
		DefaultTableModel model = (DefaultTableModel) tblRentBook.getModel();
		
		model.setRowCount(0);
		for (RentBook rb : listRentBook)
		{
			User user = UserDAO.findByID(rb.getUserId());
			Admin admin = AdminDAO.findByID(rb.getAdminId());
			
			String createdDate = DateHelper.dateToString(rb.getCreatedDate(), SettingSave.getSetting().getDateFormat());
			String returnedDate = "Chưa có";
			String status = rb.getTitleStatus();
			if (rb.getReturnedDate() != null)
			{
				returnedDate = DateHelper.dateToString(rb.getReturnedDate(), SettingSave.getSetting().getDateFormat());
			}
			
			String[] data = {rb.getId() + "", user.getUsername(), admin.getUsername(), createdDate, returnedDate, status};
			model.addRow(data);
		}
		
		int rowCount = tblRentBook.getRowCount();
		//Nếu điều kiện hợp lý thì set select row lại y như lúc chưa fillToTable
		if (indexSelect != -1)
		{
			if (indexSelect < rowCount && rowCount > 0)
			{
				tblRentBook.setRowSelectionInterval(indexSelect, indexSelect);
			}
			else
			{
				indexSelect = rowCount - 1;
				if (indexSelect > -1)
				{
					tblRentBook.setRowSelectionInterval(indexSelect, indexSelect);
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
		DefaultTableModel model = (DefaultTableModel) tblRentBook.getModel();
		String cboStatusStr = (cboStatus.getSelectedIndex() != 0) ? cboStatus.getSelectedItem().toString() : "";
		System.out.println(cboStatusStr);
		model.setRowCount(0);
		for (RentBook rb : listRentBook)
		{
			//Nếu giá trì tìm không thấy hoặc trạng thái đơn hàng không giống như cboStatus đã chọn thì không xử lý và lặp lại tiếp
			if (DataHelper.search(rb.getSearchString(), search) == false || DataHelper.search(rb.getTitleStatus(), cboStatusStr) == false) {continue;}
			
			
			User user = UserDAO.findByID(rb.getUserId());
			Admin admin = AdminDAO.findByID(rb.getAdminId());
			
			String createdDate = DateHelper.dateToString(rb.getCreatedDate(), SettingSave.getSetting().getDateFormat());
			String returnedDate = "Chưa có";
			String status = rb.getTitleStatus();
			if (rb.getReturnedDate() != null)
			{
				returnedDate = DateHelper.dateToString(rb.getReturnedDate(), SettingSave.getSetting().getDateFormat());
			}
			
			String[] data = {rb.getId() + "", user.getUsername(), admin.getUsername(), createdDate, returnedDate, status};
			model.addRow(data);
		}
		
		
		
		//Nếu điều kiện hợp lý thì set select row lại y như lúc chưa fillToTable
		int rowCount = tblRentBook.getRowCount();
		if (indexSelect != -1)
		{
			if (indexSelect < rowCount && rowCount > 0)
			{
				tblRentBook.setRowSelectionInterval(indexSelect, indexSelect);
			}
			else
			{
				indexSelect = rowCount - 1;
				if (indexSelect > -1)
				{
					tblRentBook.setRowSelectionInterval(indexSelect, indexSelect);
				}
				else
				{
					setControllModeTo_Nothing();
				}
			}
		}
	}
	
	//Sự kiện dc gọi mỗi khi có thao tác select các row trên bảng
	public void eventTableSelectRow()
	{
		indexSelect = tblRentBook.getSelectedRow();
		
		setControllModeTo_Editable();
	}
	
	//Gọi sự kiện để xóa renbook trên dòng được chọn
	public boolean deleteRentBook() throws SQLException
	{
		int rentbook_id = DataHelper.getInt(tblRentBook.getValueAt(indexSelect, 0).toString());
		return RentBookDAO.delete(rentbook_id);
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
	
	public void showRentBookDetail()
	{
		int rentbook_id = DataHelper.getInt(tblRentBook.getValueAt(indexSelect, 0).toString());
		rentBookDetailJDialog.setLocationRelativeTo(this);
		rentBookDetailJDialog.setDetailModel(rentbook_id);
		rentBookDetailJDialog.showDetail();
		rentBookDetailJDialog.fillToTable();
		rentBookDetailJDialog.setVisible(true);
	}
	
	public void showInsertRentBook()
	{
		insertRentBookJDialog.setRentBookJFrame(this);
		insertRentBookJDialog.setVisible(true);
	}
	
	public void showEditRentbook() throws SQLException
	{
		int rentbook_id = DataHelper.getInt(tblRentBook.getValueAt(indexSelect, 0).toString());
		RentBook model = RentBookDAO.findById(rentbook_id);
		
		editorRentbookJDialog.setEditModel(model);
		editorRentbookJDialog.setEditMode(true);
		editorRentbookJDialog.setRentBookJFrame(this);
		
		editorRentbookJDialog.setVisible(true);
	}
	
	public void refresh()
	{
		try {
			getDataToList();
			fillToTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
