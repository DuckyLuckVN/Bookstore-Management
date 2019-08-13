package com.duan.main;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.duan.custom.MessageOptionPane;
import com.duan.dao.DBConnection;
import com.duan.helper.SettingSave;
import com.duan.model.Setting;
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
			
			SettingSave.loadSetting();
			Setting st = SettingSave.getSetting();
			
			try 
			{
				DBConnection.setConnection(st.getHostDB(), st.getNameDB(), st.getUsernameDB(), st.getPasswordDB());
			} catch (SQLException e) 
			{
				e.printStackTrace();
				MessageOptionPane.showMessageDialog(null, "Đã có sự cố sảy ra: (ERROR CODE: " + e.getErrorCode() + ")\n" + e.getMessage(), MessageOptionPane.ICON_NAME_ERROR);
			}
			loginJFrame.setVisible(true);
		}
		else
		{
			String msg = "Đây là lần đầu phần mềm được mở, vui lòng thiết lập các giá trị cần thiết trước khi chương trình được chạy!";
			MessageOptionPane.showMessageDialog(null, msg);
			settingJDialog.setVisible(true);
		}
	}
}
