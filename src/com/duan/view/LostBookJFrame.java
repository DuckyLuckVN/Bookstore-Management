package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LostBookJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable tblBook;
	private JTable tblLostBook;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					LostBookJFrame frame = new LostBookJFrame();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}


	public LostBookJFrame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1017, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblChnSchMt = new JLabel("Chọn sách mất:");
		lblChnSchMt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton btnChn = new JButton("Chọn");
		btnChn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		
		tblBook = new JTable();
		tblBook.setModel(new DefaultTableModel(null, new String[] {"MÃ SÁCH", "TÊN SÁCH", "GIÁ", "TỔNG THUÊ", "SỐ MẤT", "TIỀN PHẠT"}) 
		{
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tblBook);
		
		JButton btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnXa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JPanel pnlForm = new JPanel();
		pnlForm.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(116, 27, 363, 24);
		
		JLabel lblNgiThu = new JLabel("Người thuê:");
		lblNgiThu.setBounds(10, 60, 93, 24);
		lblNgiThu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		textField = new JTextField();
		textField.setBounds(116, 62, 363, 24);
		textField.setEditable(false);
		textField.setColumns(10);
		
		JLabel lblTiKhong = new JLabel("Tài khoảng:");
		lblTiKhong.setBounds(10, 95, 93, 24);
		lblTiKhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		textField_1 = new JTextField();
		textField_1.setBounds(116, 97, 363, 24);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		JLabel lblTngSchThu = new JLabel("Tổng sách thuê:");
		lblTngSchThu.setBounds(10, 130, 93, 24);
		lblTngSchThu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		textField_2 = new JTextField();
		textField_2.setBounds(116, 132, 363, 24);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(116, 167, 363, 24);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		
		JLabel lblNgiChoThu = new JLabel("NV cho thuê:");
		lblNgiChoThu.setBounds(10, 165, 93, 24);
		lblNgiChoThu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlForm.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã đơn thuê:");
		lblNewLabel.setBounds(10, 25, 96, 24);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlForm.add(lblNewLabel);
		pnlForm.add(comboBox);
		pnlForm.add(lblNgiThu);
		pnlForm.add(textField);
		pnlForm.add(lblTiKhong);
		pnlForm.add(textField_1);
		pnlForm.add(lblTngSchThu);
		pnlForm.add(textField_2);
		pnlForm.add(lblNgiChoThu);
		pnlForm.add(textField_3);
		
		JLabel lblTngTinPht = new JLabel("Tổng tiền phạt:");
		lblTngTinPht.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTngTinPht.setBounds(10, 200, 106, 24);
		pnlForm.add(lblTngTinPht);
		
		JLabel label = new JLabel("0 đ");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(116, 202, 262, 24);
		pnlForm.add(label);
		
		JPanel pnlControll = new JPanel();
		pnlControll.setLayout(new GridLayout(1, 1, 15, 0));
		
		JButton btnToMi = new JButton("Tạo mới");
		btnToMi.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnToMi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnToMi.setIcon(new ImageIcon(LostBookJFrame.class.getResource("/com/duan/icon/Create.png")));
		pnlControll.add(btnToMi);
		
		JButton btnLu = new JButton("Lưu");
		btnLu.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLu.setIcon(new ImageIcon(LostBookJFrame.class.getResource("/com/duan/icon/Accept.png")));
		pnlControll.add(btnLu);
		
		JButton btnCpNht = new JButton("Cập nhật");
		btnCpNht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCpNht.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnCpNht.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCpNht.setIcon(new ImageIcon(LostBookJFrame.class.getResource("/com/duan/icon/Notes.png")));
		pnlControll.add(btnCpNht);
		
		JButton btnXa_1 = new JButton("Xóa");
		btnXa_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnXa_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnXa_1.setIcon(new ImageIcon(LostBookJFrame.class.getResource("/com/duan/icon/icons8_delete_32px_1.png")));
		pnlControll.add(btnXa_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		tblLostBook = new JTable();
		tblLostBook.setModel(new DefaultTableModel(null, new String[] {"MÃ ĐƠN", "TK THUÊ", "NV BÁO MẤT", "NGÀY BÁO MẤT", "SỐ SÁCH MẤT", "TIỀN PHẠT"}) 
		{
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(tblLostBook);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlForm, GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblChnSchMt, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(btnChn, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addGap(258)
							.addComponent(btnXa, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
						.addComponent(pnlControll, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(pnlForm, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblChnSchMt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(btnChn))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(btnXa, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(pnlControll, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
