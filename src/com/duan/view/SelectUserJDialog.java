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

public class SelectUserJDialog extends JDialog {

	private JPanel contentPane;
	private JTable tblUser;
	private JLabel lblTmTheoTn;
	private JTextField textField;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectUserJDialog frame = new SelectUserJDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SelectUserJDialog() 
	{
		setModal(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelectUserJDialog.class.getResource("/com/duan/icon/icons8_user_groups_64px.png")));
		setTitle("Chọn người dùng");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 377, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 48, 356, 218);
		contentPane.add(scrollPane);
		
		tblUser = new JTable();
		tblUser.setModel(new DefaultTableModel(null,new String[] {"MÃ", "TÀI KHOẢNG", "HỌ TÊN"}) 
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
		
		lblTmTheoTn = new JLabel("Tìm kiếm:");
		lblTmTheoTn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTmTheoTn.setBounds(5, 11, 59, 26);
		contentPane.add(lblTmTheoTn);
		
		textField = new JTextField();
		textField.setBounds(74, 11, 287, 26);
		contentPane.add(textField);
		textField.setColumns(10);
	}

}
