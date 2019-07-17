package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class StatisticalJFrame extends JFrame {

	private JPanel contentPane;
	private JTable tblSachBan;
	private JTable tblDoanhThu;
	private JTable tblSachThue;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatisticalJFrame frame = new StatisticalJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StatisticalJFrame() 
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setTitle("Thống Kê");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 764, 584);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel pnlSachBan = new JPanel();
		tabbedPane.addTab("Sách Bán", new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_books_32px_1.png")), pnlSachBan, null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"}));
		
		JScrollPane scrollPane = new JScrollPane();
		
		tblSachBan = new JTable();
		tblSachBan.setModel(new DefaultTableModel(null,new String[] {"MÃ SÁCH", "TÊN SÁCH", "THỂ LOẠI", "TÁC GIẢ", "NGÀY NHẬP", "TỔNG ĐÃ BÁN"}) 
		{
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblSachBan.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(tblSachBan);
		GroupLayout gl_pnlSachBan = new GroupLayout(pnlSachBan);
		gl_pnlSachBan.setHorizontalGroup(
			gl_pnlSachBan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSachBan.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(gl_pnlSachBan.createSequentialGroup()
					.addContainerGap()
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(555, Short.MAX_VALUE))
		);
		gl_pnlSachBan.setVerticalGroup(
			gl_pnlSachBan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSachBan.createSequentialGroup()
					.addContainerGap()
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
					.addGap(11))
		);
		pnlSachBan.setLayout(gl_pnlSachBan);
		
		JPanel pnlSachThue = new JPanel();
		tabbedPane.addTab("Sách Thuê", new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_bookmark_32px.png")), pnlSachThue, null);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"}));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		tblSachThue = new JTable();
		tblSachThue.setModel(new DefaultTableModel(null,new String[] {"MÃ SÁCH", "TÊN SÁCH", "SỐ LƯỢNG", "ĐÃ TRẢ", "CHƯA TRẢ", "QUÁ THỜI HẠN"}) 
		{
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblSachThue.getColumnModel().getColumn(0).setResizable(false);
		scrollPane_2.setViewportView(tblSachThue);
		GroupLayout gl_pnlSachThue = new GroupLayout(pnlSachThue);
		gl_pnlSachThue.setHorizontalGroup(
			gl_pnlSachThue.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSachThue.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_pnlSachThue.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE))
					.addGap(10))
		);
		gl_pnlSachThue.setVerticalGroup(
			gl_pnlSachThue.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSachThue.createSequentialGroup()
					.addGap(11)
					.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
					.addGap(11))
		);
		pnlSachThue.setLayout(gl_pnlSachThue);
		
		JPanel pnlDoanhThu = new JPanel();
		tabbedPane.addTab("Doanh Thu", new ImageIcon(StatisticalJFrame.class.getResource("/com/duan/icon/icons8_stack_of_money_32px.png")), pnlDoanhThu, null);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"}));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		tblDoanhThu = new JTable();
		tblDoanhThu.setModel(new DefaultTableModel(null, new String[] {"MÃ SÁCH", "TÊN SÁCH", "SỐ LƯỢNG", "TỔNG TIỀN"}) 
		{
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblDoanhThu.getColumnModel().getColumn(0).setResizable(false);
		scrollPane_1.setViewportView(tblDoanhThu);
		GroupLayout gl_pnlDoanhThu = new GroupLayout(pnlDoanhThu);
		gl_pnlDoanhThu.setHorizontalGroup(
			gl_pnlDoanhThu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlDoanhThu.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_pnlDoanhThu.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE))
					.addGap(10))
		);
		gl_pnlDoanhThu.setVerticalGroup(
			gl_pnlDoanhThu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlDoanhThu.createSequentialGroup()
					.addGap(11)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
					.addGap(11))
		);
		pnlDoanhThu.setLayout(gl_pnlDoanhThu);
	}
}
