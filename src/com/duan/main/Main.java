package com.duan.main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.duan.helper.SettingSave;
import com.duan.view.LoginJFrame;
import com.duan.view.SettingJDialog;

public class Main extends JFrame
{
	public static void main(String[] args) 
	{
		LoginJFrame loginJFrame = new LoginJFrame();
		SettingJDialog settingJDialog = new SettingJDialog();
		//Kiểm tra xem file save setting có tồn tại hay không, nếu có thì chạy thẳng loginJframe
		//Chưa có thì phải hiện settingJdialog trước.
		if (SettingSave.isFileSettingExist())
		{
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			loginJFrame.setVisible(true);
		}
		else
		{
			String msg = "Đây là lần đầu phần mềm được mở, vui lòng\n"
					+ "thiết lập các giá trị cần thiết trước khi\n"
					+ "chương trình được chạy!";
			JOptionPane.showMessageDialog(null, msg);
			settingJDialog.setVisible(true);
		}
	}
}
