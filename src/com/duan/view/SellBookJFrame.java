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
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class SellBookJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField lblUsername;
	private JTextField txtFullname;
	private JTable tblBook;
	
	private SelectUserJDialog selectUserJDialog = new SelectUserJDialog();
	private SelectBookJDialog selectBookJDialog = new SelectBookJDialog();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SellBookJFrame frame = new SellBookJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SellBookJFrame() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(SellBookJFrame.class.getResource("/com/duan/icon/icons8_buy_for_change_64px_1.png")));
		setTitle("Bán Sách");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 699, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblTitle = new JLabel("BÁN SÁCH");
		lblTitle.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JLabel lblTaiKhoang = new JLabel("Tài khoảng");
		lblTaiKhoang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblUsername = new JTextField();
		lblUsername.setEditable(false);
		lblUsername.setColumns(10);
		
		JButton btnSelectAccount = new JButton("Chọn");
		btnSelectAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				showSelectUser();
			}
		});
		
		JLabel lblTen = new JLabel("Họ tên");
		lblTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtFullname = new JTextField();
		txtFullname.setColumns(10);
		
		JLabel lblThueSach = new JLabel("Sách thuê");
		lblThueSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnSelectBook = new JButton("Chọn sách");
		btnSelectBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				showSelectBook();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		tblBook = new JTable();
		tblBook.setModel(new DefaultTableModel(null, new String[] {"MÃ SÁCH", "TÊN SÁCH", "GIÁ BÁN", "SỐ LƯỢNG", "XÓA"}) {
			
			//Column = 4 -> cột "XÓA"
			public boolean isCellEditable(int row, int column) {
				if (column == 4 || column == 3)
				{
					return true;
				}
				
				return false;
			}
			
			//Trả về dạng checkbox với cột thứ 4 ("XÓA")
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 4)
				{
					return boolean.class;
				}
				return super.getColumnClass(columnIndex);
			}
		});
		tblBook.getColumnModel().getColumn(0).setResizable(false);
		tblBook.getColumnModel().getColumn(1).setResizable(false);
		scrollPane.setViewportView(tblBook);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setLayout(null);
		
		JLabel lblSLng = new JLabel("Số lượng:");
		lblSLng.setBounds(10, 11, 60, 27);
		panel_1.add(lblSLng);
		lblSLng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblQuyn = new JLabel("10 quyển");
		lblQuyn.setBounds(80, 11, 89, 27);
		panel_1.add(lblQuyn);
		lblQuyn.setForeground(Color.BLACK);
		lblQuyn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel label = new JLabel("350,000.00 đ");
		label.setBounds(80, 49, 246, 27);
		panel_1.add(label);
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblTngGi = new JLabel("Tổng Giá:");
		lblTngGi.setBounds(10, 49, 66, 27);
		panel_1.add(lblTngGi);
		lblTngGi.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnConfirm = new JButton("Xác Nhận");
		btnConfirm.setBounds(10, 87, 255, 43);
		panel_1.add(btnConfirm);
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
							.addGap(6)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)))
					.addGap(2))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
					.addGap(4))
		);
		
		JButton btnXaSch = new JButton("Xóa sách");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblTaiKhoang)
							.addGap(10)
							.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(btnSelectAccount, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblTen, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtFullname, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblThueSach, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSelectBook)
							.addPreferredGap(ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
							.addComponent(btnXaSch, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE))
					.addGap(9))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTaiKhoang, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnSelectAccount, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTen, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFullname, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblThueSach, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSelectBook, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnXaSch, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
					.addGap(9))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void showSelectUser()
	{
		selectUserJDialog.setLocationRelativeTo(this);
		selectUserJDialog.setVisible(true);
	}
	
	public void showSelectBook()
	{
		selectBookJDialog.setLocationRelativeTo(this);
		selectBookJDialog.setVisible(true);
	}
}
