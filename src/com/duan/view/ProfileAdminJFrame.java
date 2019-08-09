package com.duan.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ProfileAdminJFrame extends JDialog {

	public static void main(String[] args) {
		try {
			ProfileAdminJFrame dialog = new ProfileAdminJFrame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ProfileAdminJFrame() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblKhngCnh = new JLabel("Không có ảnh");
		lblKhngCnh.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblKhngCnh.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhngCnh.setBounds(10, 11, 160, 179);
		getContentPane().add(lblKhngCnh);
	}
}
