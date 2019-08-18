package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
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

import com.duan.custom.common.JTableRed;
import com.duan.custom.message.MessageOptionPane;
import com.duan.dao.AdminDAO;
import com.duan.dao.BookLostDAO;
import com.duan.dao.BookLostDetailDAO;
import com.duan.dao.OrderDAO;
import com.duan.dao.RentBookDAO;
import com.duan.dao.UserDAO;
import com.duan.helper.DataHelper;
import com.duan.helper.DateHelper;
import com.duan.helper.SettingSave;
import com.duan.helper.SwingHelper;
import com.duan.model.BookLost;
import com.duan.model.Order;

import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Dimension;

public class BookLostJFrame extends JFrame {

	private JPanel contentPane;
	private JTableRed tblBookLost;
	private JButton btnMaxLeft;
	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnMaxRight;
	private JButton btnDetail;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	
	private BookLostEditorJDialog insertBookLostJDialog = new BookLostEditorJDialog();
	private BookLostEditorJDialog editBookLostEditorJDialog = new BookLostEditorJDialog();
	private BookLostDetailJDialog bookLostDetailJDialog = new BookLostDetailJDialog();
	
	private List<BookLost> listBookLost;
	private int indexSelected = -1;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookLostJFrame frame = new BookLostJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BookLostJFrame() 
	{
		insertBookLostJDialog.setLocationRelativeTo(this);
		editBookLostEditorJDialog.setLocationRelativeTo(this);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(BookLostJFrame.class.getResource("/com/duan/icon/icons8_buy_for_change_64px_1.png")));
		setTitle("Báo mất sách");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 833, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "B\u1EA3ng d\u1EEF li\u1EC7u", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel pnlController = new JPanel();
		pnlController.setBorder(new TitledBorder(null, "\u0110i\u1EC1u khi\u1EC3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlController.setLayout(new GridLayout(0, 1, 10, 5));
		
		btnDetail = new JButton("Xem chi tiết");
		btnDetail.setEnabled(false);
		btnDetail.setFont(new Font("Tahoma", Font.BOLD, 12));
		SwingHelper.setTextBelowIconButton(btnDetail);
		btnDetail.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				showBookLostDetail();
			}
		});
		btnDetail.setIcon(new ImageIcon(BookLostJFrame.class.getResource("/com/duan/icon/icons8_details_popup_50px.png")));
		pnlController.add(btnDetail);
		
		btnAdd = new JButton("Thêm mới");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				showInsertBookLost();
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		SwingHelper.setTextBelowIconButton(btnAdd);
		btnAdd.setIcon(new ImageIcon(BookLostJFrame.class.getResource("/com/duan/icon/icons8_add_50px_3.png")));
		pnlController.add(btnAdd);
		
		btnEdit = new JButton("Cập nhật");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				showEditBookLost();
			}
		});
		btnEdit.setEnabled(false);
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 12));
		SwingHelper.setTextBelowIconButton(btnEdit);
		btnEdit.setIcon(new ImageIcon(BookLostJFrame.class.getResource("/com/duan/icon/icons8_edit_property_50px.png")));
		pnlController.add(btnEdit);
		
		btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(MessageOptionPane.showConfirmDialog(contentPane, "Bạn có muốn xóa đơn báo mất này?"))
				{
					try 
					{
						BookLostDAO.delete(listBookLost.get(indexSelected).getRentbookId());
						listBookLost.remove(indexSelected);
						fillToTable();
					} 
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					}
				}
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		SwingHelper.setTextBelowIconButton(btnDelete);
		btnDelete.setIcon(new ImageIcon(BookLostJFrame.class.getResource("/com/duan/icon/icons8_delete_50px.png")));
		pnlController.add(btnDelete);
		
		tblBookLost = new JTableRed();
		tblBookLost.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				eventTableSelectRow();
			}
		});
		tblBookLost.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) 
			{
				eventTableSelectRow();
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (e.getClickCount() >= 2)
				{
					showBookLostDetail();
				}
			}
		});
		tblBookLost.setRowHeight(35);
		tblBookLost.setModel(new DefaultTableModel(null, new String[] {"MÃ ĐƠN THUÊ", "TK THUÊ", "NV BÁO MẤT", "NGÀY BÁO MẤT", "TỔNG SÁCH MẤT", "TỔNG TIỀN PHẠT"}) 
		{
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		scrollPane_1.setViewportView(tblBookLost);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 0, 15, 0));
		
		btnMaxLeft = new JButton("|<");
		btnMaxLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int rowCount = tblBookLost.getRowCount();
				if (rowCount > 0)
				{
					indexSelected = 0;
					tblBookLost.setRowSelectionInterval(indexSelected, indexSelected);
					setControllModeTo_Editable();
				}
			}
		});
		panel.add(btnMaxLeft);
		
		btnLeft = new JButton("<");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int rowCount = tblBookLost.getRowCount();
				if (indexSelected > 0 && rowCount > 0)
				{
					indexSelected--;
					tblBookLost.setRowSelectionInterval(indexSelected, indexSelected);
					setControllModeTo_Editable();
				}
			}
		});
		btnLeft.setEnabled(false);
		panel.add(btnLeft);
		
		btnRight = new JButton(">");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				int rowCount = tblBookLost.getRowCount();
				if (indexSelected < rowCount - 1 && rowCount > 0)
				{
					indexSelected++;
					tblBookLost.setRowSelectionInterval(indexSelected, indexSelected);
					setControllModeTo_Editable();
				}
			}
		});
		btnRight.setEnabled(false);
		panel.add(btnRight);
		
		JButton btnMaxRight = new JButton(">|");
		btnMaxRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int rowCount = tblBookLost.getRowCount();
				if (rowCount > 0)
				{
					indexSelected = rowCount - 1;
					tblBookLost.setRowSelectionInterval(indexSelected, indexSelected);
					setControllModeTo_Editable();
				}
			}
		});
		panel.add(btnMaxRight);
		
		JButton label = new JButton("Tải lại");
		label.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				refresh();
			}
		});
		label.setIcon(new ImageIcon(BookLostJFrame.class.getResource("/com/duan/icon/icons8_synchronize_24px.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlController, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))
					.addGap(5))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlController, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		getDataTolist();
		fillToTable();
	}
	
	public void getDataTolist()
	{
		try 
		{
			listBookLost = BookLostDAO.getAll();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void fillToTable()
	{
		DefaultTableModel model = (DefaultTableModel) tblBookLost.getModel();
		model.setRowCount(0);
		
		try 
		{
			for (BookLost bookLost : listBookLost)
			{
				String userUsername = UserDAO.findByID(RentBookDAO.findById( bookLost.getRentbookId() ).getUserId() ).getUsername();
				String adminUsername = AdminDAO.findByID(bookLost.getAdminId()).getUsername();
				String createdDate = DateHelper.dateToString(bookLost.getCreatedDate(), SettingSave.getSetting().getDateFormat());
				
				double totalCost = BookLostDetailDAO.getTotalCost(bookLost.getRentbookId());
				int totalLost = BookLostDetailDAO.getTotalLost(bookLost.getRentbookId());
				
				String totalLost_str = DataHelper.getFormatForMoney(totalCost) + SettingSave.getSetting().getMoneySymbol();
				
				Object[] rowData = {bookLost.getRentbookId(), userUsername, adminUsername, createdDate, totalLost, totalLost_str};
				model.addRow(rowData);
			}
			
			
			//Nếu điều kiện hợp lý thì set select row lại y như lúc chưa fillToTable
			int rowCount = tblBookLost.getRowCount();
			if (indexSelected != -1)
			{
				if (indexSelected < rowCount && rowCount > 0)
				{
					tblBookLost.setRowSelectionInterval(indexSelected, indexSelected);
				}
				else
				{
					indexSelected = rowCount - 1;
					if (indexSelected > -1)
					{
						tblBookLost.setRowSelectionInterval(indexSelected, indexSelected);
					}
					else
					{
						setControllModeTo_Nothing();
					}
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Hiển thị jdialog thông tin chi tiết về hóa đơn mất sách
	private void showBookLostDetail()
	{
		int rentbook_id = DataHelper.getInt(tblBookLost.getValueAt(indexSelected, 0).toString());
		bookLostDetailJDialog.setLocationRelativeTo(this);
		bookLostDetailJDialog.setDetailModel(rentbook_id);
		bookLostDetailJDialog.showDetail();
		bookLostDetailJDialog.fillToTable();
		bookLostDetailJDialog.setVisible(true);
	}
	
	//Sự kiện được gọi khi nhấn nút 'Thêm mới' nó sẽ mở ra một cửa sổ để nhập thông tin order vào
	private void showInsertBookLost()
	{
		insertBookLostJDialog = new BookLostEditorJDialog();
		insertBookLostJDialog.setBookLostJFrame(this);
		insertBookLostJDialog.setLocationRelativeTo(this);
		insertBookLostJDialog.setEditMode(false);
		
		insertBookLostJDialog.setVisible(true);
	}
	
	private void showEditBookLost()
	{
		editBookLostEditorJDialog = new BookLostEditorJDialog();
		editBookLostEditorJDialog.setLocationRelativeTo(this);
		editBookLostEditorJDialog.setEditMode(true);
		editBookLostEditorJDialog.setEditModel(listBookLost.get(indexSelected));
		editBookLostEditorJDialog.setBookLostJFrame(this);
		
		editBookLostEditorJDialog.setVisible(true);
	}
	
	//Hàm này sẽ dc gọi khi có 1 dòng trong bảng dc chọn vào
	public void eventTableSelectRow()
	{
		indexSelected = tblBookLost.getSelectedRow();
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
	
	public void refresh()
	{
		getDataTolist();
		fillToTable();
	}
}
