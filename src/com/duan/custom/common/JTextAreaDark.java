package com.duan.custom.common;

import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;

public class JTextAreaDark extends JTextArea
{
	public JTextAreaDark()
	{
		setWrapStyleWord(true);
		setLineWrap(true);
		setFont(new Font("Tahoma", Font.PLAIN, 13));
		setBorder(new LineBorder(Color.DARK_GRAY, 2));
		
	}
}
