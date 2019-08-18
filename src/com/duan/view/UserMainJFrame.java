package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;

import com.duan.custom.common.BookJPanel;
import com.duan.custom.common.JTableRed;
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
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UserMainJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearchBook;
	private JTable tblBook;
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
		txtSearchBook.setBounds(105, 11, 240, 27);
		pnlBook.add(txtSearchBook);
		txtSearchBook.setColumns(10);
		
		JScrollPane scrollPaneBook = new JScrollPane();
		scrollPaneBook.setBounds(10, 51, 830, 497);
		pnlBook.add(scrollPaneBook);
		
		tblBook = new JTableRed();
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
		txtSearchRentBook.setColumns(10);
		txtSearchRentBook.setBounds(101, 11, 240, 27);
		pnlRentBook.add(txtSearchRentBook);
		
		JScrollPane scrollPaneRentBook = new JScrollPane();
		scrollPaneRentBook.setBounds(10, 51, 830, 497);
		pnlRentBook.add(scrollPaneRentBook);
		
		JTableRed tblRentBook = new JTableRed();
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
		txtSearchOrder.setColumns(10);
		txtSearchOrder.setBounds(101, 11, 240, 27);
		pnlOrder.add(txtSearchOrder);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 830, 497);
		pnlOrder.add(scrollPane);
		
		JTableRed tblOrder = new JTableRed();
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
	
	public void logout()
	{
		AccountSave.removeUser();
		dispose();
		new LoginJFrame().setVisible(true);
	}
}
