package com.duan.custom.common;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;

public class JTextFieldDark extends JTextField
{
	public JTextFieldDark() {
		setBorder(new LineBorder(Color.DARK_GRAY, 2));
	}

}
