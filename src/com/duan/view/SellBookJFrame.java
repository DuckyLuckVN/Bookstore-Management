package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;

public class SellBookJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBnSch = new JLabel("BÁN SÁCH");
		lblBnSch.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblBnSch.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblBnSch.setHorizontalAlignment(SwingConstants.CENTER);
		lblBnSch.setBounds(10, 11, 578, 38);
		contentPane.add(lblBnSch);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 60, 323, 243);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTiKhong = new JLabel("Tài khoảng");
		lblTiKhong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTiKhong.setBounds(10, 11, 60, 27);
		panel.add(lblTiKhong);
		
		textField = new JTextField();
		textField.setBounds(80, 11, 158, 27);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnChn = new JButton("Chọn");
		btnChn.setBounds(248, 11, 65, 27);
		panel.add(btnChn);
		
		JLabel lblHTn = new JLabel("Họ tên");
		lblHTn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHTn.setBounds(10, 49, 60, 27);
		panel.add(lblHTn);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(80, 49, 158, 27);
		panel.add(textField_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(343, 60, 245, 180);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Xác Nhận");
		btnNewButton.setBounds(343, 251, 245, 52);
		contentPane.add(btnNewButton);
	}
}
