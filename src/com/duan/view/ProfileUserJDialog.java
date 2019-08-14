package com.duan.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import com.duan.custom.common.JDateChooserCustom;
import com.duan.custom.message.MessageOptionPane;
import com.duan.dao.AdminDAO;
import com.duan.dao.UserDAO;
import com.duan.helper.AccountSave;
import com.duan.helper.DataHelper;
import com.duan.helper.SwingHelper;
import com.duan.model.Admin;
import com.duan.model.User;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class ProfileUserJDialog extends JDialog {
	private JTextField txtUsername;
	private JTextField txtFullname;
	private JTextField txtPassword;
	private JTextField txtEmail;
	private JTextField txtPhoneNum;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtId;
	private JRadioButton rdoNam;
	private JRadioButton rdoNu;
	private JLabel lblNgySinh;
	private JDateChooserCustom txtDateOfBirth;
	private JButton btnXcNhn;
	
	private User user;

	private UserMainJFrame mainJFrame;
	
	public static void main(String[] args) {
		try {
			ProfileUserJDialog dialog = new ProfileUserJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ProfileUserJDialog() 
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 370, 393);
		getContentPane().setLayout(null);
		
		JLabel lblTiKhong = new JLabel("Tài khoảng:");
		lblTiKhong.setBounds(10, 49, 82, 27);
		getContentPane().add(lblTiKhong);
		lblTiKhong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtUsername = new JTextField();
		txtUsername.setBounds(102, 49, 238, 27);
		getContentPane().add(txtUsername);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtUsername.setEditable(false);
		txtUsername.setColumns(10);
		
		txtFullname = new JTextField();
		txtFullname.setBounds(102, 87, 238, 27);
		getContentPane().add(txtFullname);
		txtFullname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFullname.setColumns(10);
		
		JLabel lblHTn = new JLabel("Họ tên:");
		lblHTn.setBounds(10, 87, 82, 27);
		getContentPane().add(lblHTn);
		lblHTn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblMtKhu = new JLabel("Mật khẩu:");
		lblMtKhu.setBounds(10, 159, 82, 27);
		getContentPane().add(lblMtKhu);
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(102, 159, 238, 27);
		getContentPane().add(txtPassword);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPassword.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 197, 82, 27);
		getContentPane().add(lblEmail);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtEmail = new JTextField();
		txtEmail.setBounds(102, 197, 238, 27);
		getContentPane().add(txtEmail);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		
		txtPhoneNum = new JTextField();
		txtPhoneNum.setBounds(102, 235, 238, 27);
		getContentPane().add(txtPhoneNum);
		txtPhoneNum.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPhoneNum.setColumns(10);
		
		JLabel lblSinThoi = new JLabel("Số điện thoại:");
		lblSinThoi.setBounds(10, 235, 82, 27);
		getContentPane().add(lblSinThoi);
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblGiiTnh = new JLabel("Giới tính:");
		lblGiiTnh.setBounds(10, 273, 82, 27);
		getContentPane().add(lblGiiTnh);
		lblGiiTnh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		rdoNam = new JRadioButton("Nam");
		rdoNam.setBounds(102, 274, 56, 27);
		getContentPane().add(rdoNam);
		rdoNam.setSelected(true);
		rdoNam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonGroup.add(rdoNam);
		
		rdoNu = new JRadioButton("Nữ");
		rdoNu.setBounds(175, 274, 47, 27);
		getContentPane().add(rdoNu);
		rdoNu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonGroup.add(rdoNu);
		
		txtId = new JTextField();
		txtId.setBounds(102, 11, 238, 27);
		getContentPane().add(txtId);
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtId.setEditable(false);
		txtId.setColumns(10);
		
		JLabel lblMS = new JLabel("Mã số:");
		lblMS.setBounds(10, 11, 82, 27);
		getContentPane().add(lblMS);
		lblMS.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblNgySinh = new JLabel("Ngày sinh:");
		lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNgySinh.setBounds(10, 125, 82, 27);
		getContentPane().add(lblNgySinh);
		
		txtDateOfBirth = new JDateChooserCustom();
		txtDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDateOfBirth.setBounds(102, 125, 238, 27);
		getContentPane().add(txtDateOfBirth);
		
		btnXcNhn = new JButton("Xác nhận");
		btnXcNhn.setBounds(10, 311, 344, 42);
		getContentPane().add(btnXcNhn);
		
		try 
		{
			showDetail();
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
	}
	
	public void showDetail() throws SQLException
	{
		user = UserDAO.findByID(AccountSave.getUser().getId());

		setTitle("Thông tin tài khoảng | " + user.getFullname() + " (" + user.getUsername() + ")");
		
		txtEmail.setText(user.getEmail());
		txtFullname.setText(user.getFullname());
		txtId.setText(user.getId() + "");
		txtPassword.setText(user.getPassword());
		txtPhoneNum.setText(user.getPhoneNumber());
		txtUsername.setText(user.getUsername());
		
		
		if (user.isSex())
			rdoNam.setSelected(true);
		else
			rdoNu.setSelected(false);
	}
	

	
	public boolean updateProfile()
	{
		try 
		{
			user.setFullname(txtFullname.getText());
			user.setEmail(txtEmail.getText());
			user.setPassword(txtPassword.getText());
			user.setSex(rdoNam.isSelected());
			user.setPhoneNumber(txtPhoneNum.getText());
			
		
			boolean isSuccess = UserDAO.update(user, user.getId());
			
			if (isSuccess)
			{
				AccountSave.setUser(user);
				return true;
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
		return false;
	}
	
	//Kiểm tra lỗi, trả về TRUE nếu hợp lệ
	public boolean validateAll()
	{
		boolean isSuccess = true;
		String msg = "";
		
		//Kiểm tra Họ Tên
		if (txtFullname.getText().length() == 0)
		{
			isSuccess = false;
			msg += "- Họ tên không được phép để trống\n";
		}
		
		//Kiểm tra ngày sinh
		if (txtDateOfBirth.getDate() == null)
		{
			isSuccess = false;
			msg += "- Ngày sinh không hợp lệ!";
		} 
		
		//Kiểm tra Mật Khẩu
		if (txtPassword.getText().length() == 0)
		{
			isSuccess = false;
			msg += "- Mật khẩu không được phép để trống\n";
		}
		
		//Kiểm tra Email
		if (txtEmail.getText().length() == 0)
		{
			isSuccess = false;
			msg += "- Email không được phép để trống\n";
		}
		else
		{
			if (DataHelper.isEmail(txtEmail.getText()) == false)
			{
				isSuccess = false;
				msg += "- Email nhập vào không hợp lệ\n";
			}
		}
		
		//Kiểm tra SDT
		if (txtPhoneNum.getText().length() == 0)
		{
			isSuccess = false;
			msg += "- Số điện thoại không được phép để trống\n";
		}
		else
		{
			if (DataHelper.isPhoneNumber(txtPhoneNum.getText()) == false)
			{
				isSuccess = false;
				msg += "- Số điện thoại nhập vào không hợp lệ\n";
			}
		}
		
		if (isSuccess == false)
		{
			MessageOptionPane.showMessageDialog(getContentPane(), "Đã có lỗi sảy ra:\n" + msg);
		}
		
		return isSuccess;
	}
	
	public void setMainJFrame(UserMainJFrame mainJFrame)
	{
		this.mainJFrame = mainJFrame;
	}
}

