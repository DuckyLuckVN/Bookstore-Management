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
import javax.swing.LayoutStyle.ComponentPlacement;

public class SelectBookJDialog extends JDialog {

	private JPanel contentPane;
	private JTable tblUser;
	private JLabel lblTmTheoTn;
	private JTextField textField;
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
		setBounds(100, 100, 451, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		tblUser = new JTable();
		tblUser.setModel(new DefaultTableModel(null,new String[] {"CHỌN", "MÃ", "TIÊU ĐỀ", "GIÁ BÁN"}) 
		{
			//Ghi đè lại hàm getColumnClass để cột đầu tiên ở dạng boolean (checkbox)
			@Override
			public Class<?> getColumnClass(int columnIndex) 
			{
				//columnIndex = 0 -> cột chọn
				if (columnIndex == 0)
				{
					return boolean.class;
				}
				return super.getColumnClass(columnIndex);
			}

			public boolean isCellEditable(int row, int column) 
			{
				//Cho phép cột đầu tiên được edit
				if (column == 0)
					return true;
				return false;
			}
		});
		tblUser.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tblUser.getColumnModel().getColumn(0).setPreferredWidth(0);
		tblUser.getColumnModel().getColumn(1).setPreferredWidth(10);
		scrollPane.setViewportView(tblUser);
		
		lblTmTheoTn = new JLabel("Tìm theo tên:");
		lblTmTheoTn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblTmTheoTn)
					.addGap(10)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTmTheoTn, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
					.addGap(1))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
