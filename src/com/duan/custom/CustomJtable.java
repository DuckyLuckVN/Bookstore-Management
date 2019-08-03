package com.duan.custom;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class CustomJtable extends JTable
{
	public CustomJtable() {
		setSelectionBackground(Color.RED);
		setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
	}
	
}
