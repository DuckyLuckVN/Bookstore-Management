package com.duan.helper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

public class SwingHelper 
{
	//Dùng để set Auto Resize icon vừa khít với size label
	public static void setAutoResizeIcon(JLabel label)
	{
		ImageIcon img = (ImageIcon) label.getIcon();
		label.setIcon(new ImageIcon(img.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH)));
	}
	
	//Dùng để set Auto Resize icon vừa khít với size label có kèm theo kiểu Scale
	public static void setAutoResizeIcon(JLabel label, int ScaleMode)
	{
		ImageIcon img = (ImageIcon) label.getIcon();
		label.setIcon(new ImageIcon(img.getImage().getScaledInstance(label.getWidth(), label.getHeight(), ScaleMode)));
	}
	
	//Dùng để set Auto Resize icon vừa khít với PreferredSize label có kèm theo kiểu Scale
	public static void setAutoResizeIcon_PreferredSize(JLabel label)
	{
		ImageIcon img = (ImageIcon) label.getIcon();
		int height = (int) label.getPreferredSize().getHeight();
		int width = (int) label.getPreferredSize().getWidth();
		//System.out.println(height + ", " + width);
		label.setIcon(new ImageIcon(img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
	}
	
	//Dùng để xóa 1 dòng nào đó trong JTable
	public static void removeRow(JTable table, int row)
	{
		((DefaultTableModel) table.getModel()).removeRow(row);
	}
	
	
	public static boolean showConfirmDialog(Component comp, String title, String content)
	{
		int status = JOptionPane.showConfirmDialog(comp, content, title, JOptionPane.YES_NO_OPTION);
		return (status == JOptionPane.YES_OPTION) ? true : false;
	}
	
	public static void changeBackground(Component comp, Color color)
	{
		comp.setBackground(color);
	}
	
	public static void setTextBelowIconButton(JButton btn)
	{
		btn.setVerticalTextPosition(SwingConstants.BOTTOM);
		btn.setHorizontalTextPosition(SwingConstants.CENTER);
	}
	
	public static boolean showConfirm(Component comp, String msg)
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int status = JOptionPane.showConfirmDialog(comp, msg, "Thông Báo", JOptionPane.YES_NO_OPTION);
		if (status == JOptionPane.YES_OPTION)
		{
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
}
