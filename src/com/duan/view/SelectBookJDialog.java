package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class SelectBookJDialog extends JDialog {

	private JPanel contentPane;
	private JTable tblUser;
	private JLabel lblTmTheoTn;
	private JTextField textField;
	private JButton btnTm;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectBookJDialog frame = new SelectBookJDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SelectBookJDialog() 
	{
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelectBookJDialog.class.getResource("/com/duan/icon/icons8_book_50px_1.png")));
		setTitle("Chọn sách");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 668, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		tblUser = new JTable();
		tblUser.setModel(new DefaultTableModel(null,new String[] {"MÃ", "TIÊU ĐỀ", "THỂ LOẠI", "TÁC GIẢ", "SỐ TRANG", "GIÁ BÁN", "NHÀ XUẤT BẢN", "NĂM XUẤT BẢN"}) 
		{
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblUser.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(tblUser);
		
		lblTmTheoTn = new JLabel("Tìm theo tên:");
		lblTmTheoTn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		btnTm = new JButton("Tìm");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblTmTheoTn)
					.addGap(10)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnTm, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTmTheoTn, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTm, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
					.addGap(1))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
