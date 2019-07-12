package com.duan.helper;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
		System.out.println(height + ", " + width);
		label.setIcon(new ImageIcon(img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
	}
	
	
	public static boolean showConfirmDialog(Component comp, String title, String content)
	{
		int status = JOptionPane.showConfirmDialog(comp, content, title, JOptionPane.YES_NO_OPTION);
		return (status == JOptionPane.YES_OPTION) ? true : false;
	}
	
	
	
	
	
	
	
}
