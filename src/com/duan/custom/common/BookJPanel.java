package com.duan.custom.common;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import com.duan.helper.SwingHelper;

import javax.swing.ImageIcon;

public class BookJPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public BookJPanel() {
		setBorder(new LineBorder(Color.DARK_GRAY, 2));
		setLayout(new BorderLayout(0, 0));
		setSize(208, 265);
		setPreferredSize(new Dimension(180, 240));
		
		JLabel lblTitle = new JLabel("Tôi thấy hoa vàng trên cỏ xanh");
		lblTitle.setPreferredSize(new Dimension(151, 25));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle, BorderLayout.SOUTH);
		
		JLabel lblImage = new JLabel("chưa có ảnh");
		lblImage.setIcon(new ImageIcon(BookJPanel.class.getResource("/com/duan/image/toi_thay_hoa_vang_tren_co_xanh__nguyen_nhat_anh.jpg")));
		lblImage.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblImage, BorderLayout.CENTER);
		SwingHelper.setAutoResizeIcon_PreferredSize(lblImage);
	}

}
