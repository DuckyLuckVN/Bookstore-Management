package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
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

import com.duan.helper.SwingHelper;

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
import java.awt.event.ActionEvent;

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
	private JTable tblBook;
	private JPanel pnlSelect;
	private JButton btnMaxLeft;
	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnMaxRight;
	private JPanel pnlTime;
	
	private JButton btnDetail;
	private FindRentBookJFrame findRentBookJFrame = new FindRentBookJFrame(this);
	private RentBookEditorJFrame insertRentBookJFrame = new RentBookEditorJFrame(this);
	private JLabel lblTmKim;
	private JTextField textField;
	//private RentBookEditorJFrame insertRentBookJFrame = new RentBookEditorJFrame();

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
		pnlController.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlController.setLayout(new GridLayout(0, 1, 0, 5));
		pnlController.setPreferredSize(new Dimension(150, 5));
		
		btnDetail = new JButton("Xem chi tiết");
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 12));
		SwingHelper.setTextBelowIconButton(btnEdit);
		btnEdit.setIcon(new ImageIcon(RentBookJFrame.class.getResource("/com/duan/icon/icons8_edit_property_50px.png")));
		pnlController.add(btnEdit);
		
		btnDelete = new JButton("Xóa");
		btnDelete.setEnabled(false);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		SwingHelper.setTextBelowIconButton(btnDelete);
		btnDelete.setIcon(new ImageIcon(RentBookJFrame.class.getResource("/com/duan/icon/icons8_delete_50px.png")));
		pnlController.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		
		pnlSelect = new JPanel();
		
		pnlTime = new JPanel();
		pnlTime.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlTime.setBackground(SystemColor.menu);
		
		lblTmKim = new JLabel("Tìm kiếm:");
		lblTmKim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTmKim, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
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
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTmKim, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
						.addComponent(pnlController, GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlTime, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlSelect, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
		);
		pnlTime.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTime = new JLabel("23:15");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setIcon(new ImageIcon(RentBookJFrame.class.getResource("/com/duan/icon/icons8_alarm_clock_24px_1.png")));
		lblTime.setForeground(Color.RED);
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlTime.add(lblTime);
		pnlSelect.setLayout(new GridLayout(1, 0, 15, 0));
		
		btnMaxLeft = new JButton("|<");
		pnlSelect.add(btnMaxLeft);
		
		btnLeft = new JButton("<");
		btnLeft.setEnabled(false);
		pnlSelect.add(btnLeft);
		
		btnRight = new JButton(">");
		btnRight.setEnabled(false);
		pnlSelect.add(btnRight);
		
		btnMaxRight = new JButton(">|");
		pnlSelect.add(btnMaxRight);
		
		tblBook = new JTable();
		tblBook.setModel(new DefaultTableModel(null, new String[] {"MÃ SỐ", "TÀI KHOẢNG THUÊ", "QUẢN TRỊ VIÊN", "NGÀY THUÊ", "TÌNH TRẠNG"} ) 
		{
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblBook.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(tblBook);
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(getOwner());
	}
	
	public void showInsertRentBook()
	{
		insertRentBookJFrame.setVisible(true);
	}
	
}
