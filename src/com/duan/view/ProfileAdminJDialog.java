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

import com.duan.custom.MessageOptionPane;
import com.duan.dao.AdminDAO;
import com.duan.helper.AccountSave;
import com.duan.helper.DataHelper;
import com.duan.helper.SwingHelper;
import com.duan.model.Admin;
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

public class ProfileAdminJDialog extends JDialog {
	private JTextField txtUsername;
	private JTextField txtFullname;
	private JTextField txtPassword;
	private JTextField txtEmail;
	private JTextField txtPhoneNum;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtId;
	private JRadioButton rdoNam;
	private JRadioButton rdoNu;
	
	private Admin admin;
	private File fileImage;
	private JLabel lblAvatar;
	private JLabel lblRoleTitle;

	private MainJFrame mainJFrame;
	
	public static void main(String[] args) {
		try {
			ProfileAdminJDialog dialog = new ProfileAdminJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ProfileAdminJDialog() 
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
		setBounds(100, 100, 555, 347);
		getContentPane().setLayout(null);
		
		lblAvatar = new JLabel("Không có ảnh");
		lblAvatar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvatar.setBounds(350, 11, 188, 223);
		getContentPane().add(lblAvatar);
		
		JButton btnChnnh = new JButton("Chọn ảnh");
		btnChnnh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				selectAvatar();
			}
		});
		btnChnnh.setBounds(350, 240, 89, 27);
		getContentPane().add(btnChnnh);
		
		JButton btnXanh = new JButton("Xóa ảnh");
		btnXanh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				clearAvatar();
			}
		});
		btnXanh.setBounds(449, 240, 89, 27);
		getContentPane().add(btnXanh);
		
		JButton btnXcNhn = new JButton(" Xác nhận");
		btnXcNhn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if (validateAll())
				{
					boolean isSuccess =updateProfile();
					if (isSuccess)
					{
						MessageOptionPane.showAlertDialog(getContentPane(), "Cập nhật thông tin thành công!", MessageOptionPane.ICON_NAME_SUCCESS);
						if (mainJFrame != null)
						{
							mainJFrame.showInfoAdmin();
						}
					}
				}
			}
		});
		btnXcNhn.setBounds(350, 275, 188, 35);
		getContentPane().add(btnXcNhn);
		
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
		txtFullname.setBounds(102, 125, 238, 27);
		getContentPane().add(txtFullname);
		txtFullname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFullname.setColumns(10);
		
		JLabel lblHTn = new JLabel("Họ tên:");
		lblHTn.setBounds(10, 125, 82, 27);
		getContentPane().add(lblHTn);
		lblHTn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblMtKhu = new JLabel("Mật khẩu:");
		lblMtKhu.setBounds(10, 163, 82, 27);
		getContentPane().add(lblMtKhu);
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(102, 163, 238, 27);
		getContentPane().add(txtPassword);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPassword.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 201, 82, 27);
		getContentPane().add(lblEmail);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtEmail = new JTextField();
		txtEmail.setBounds(102, 201, 238, 27);
		getContentPane().add(txtEmail);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		
		txtPhoneNum = new JTextField();
		txtPhoneNum.setBounds(102, 239, 238, 27);
		getContentPane().add(txtPhoneNum);
		txtPhoneNum.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPhoneNum.setColumns(10);
		
		JLabel lblSinThoi = new JLabel("Số điện thoại:");
		lblSinThoi.setBounds(10, 239, 82, 27);
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
		
		JLabel lblChcV = new JLabel("Chức vụ:");
		lblChcV.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblChcV.setBounds(10, 87, 82, 27);
		getContentPane().add(lblChcV);
		
		lblRoleTitle = new JLabel("Nhân viên");
		lblRoleTitle.setForeground(new Color(0, 0, 255));
		lblRoleTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRoleTitle.setBounds(102, 87, 82, 27);
		getContentPane().add(lblRoleTitle);
		
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
		admin = AdminDAO.findByID(AccountSave.getAdmin().getId());

		setTitle("Thông tin tài khoảng | " + admin.getFullname() + " (" + admin.getUsername() + ")");
		
		txtEmail.setText(admin.getEmail());
		txtFullname.setText(admin.getFullname());
		txtId.setText(admin.getId() + "");
		txtPassword.setText(admin.getPassword());
		txtPhoneNum.setText(admin.getPhoneNumber());
		txtUsername.setText(admin.getUsername());
		lblRoleTitle.setText(admin.getRoleTitle());
		setAvatar(admin.getImage());
		
		
		if (admin.isSex())
			rdoNam.setSelected(true);
		else
			rdoNu.setSelected(false);
	}
	
	public void setAvatar(String nameImage)
	{
		if (nameImage != null && nameImage.length() > 0)
		{
			if (getClass().getResource("/com/duan/image/" + nameImage) != null)
			{
				lblAvatar.setIcon(new ImageIcon(getClass().getResource("/com/duan/image/" + nameImage)));
				SwingHelper.setAutoResizeIcon(lblAvatar);
			}
		}
	}
	
	public void setAvatar(File fileImage)
	{
		if (fileImage != null)
		{
			lblAvatar.setIcon(new ImageIcon(fileImage.getPath()));
			SwingHelper.setAutoResizeIcon(lblAvatar);
		}
	}
	
	//Xóa sạch thông tin về Avatar
	public void clearAvatar()
	{
		lblAvatar.setIcon(null);
		fileImage = null;
	}
	
	
	//Mở hợp thoại chọn ảnh set Avatar
	public void selectAvatar()
	{
		JFileChooser chooser = new JFileChooser();
		int status = chooser.showOpenDialog(getContentPane());
		
		if (status == JFileChooser.APPROVE_OPTION)
		{
			File fileSelected = chooser.getSelectedFile();
			//Kiểm tra file vừa chọn có đúng định dạng của file ảnh không
			if (DataHelper.checkFileExtension(fileSelected.getName(), DataHelper.EXTENSIONS_FILE_IMAGE))
			{
				fileImage = fileSelected;
				setAvatar(fileImage);
			}
		}
	}
	
	public boolean updateProfile()
	{
		try 
		{
			admin.setFullname(txtFullname.getText());
			admin.setEmail(txtEmail.getText());
			admin.setPassword(txtPassword.getText());
			admin.setSex(rdoNam.isSelected());
			admin.setPhoneNumber(txtPhoneNum.getText());
			
			if (fileImage != null)
			{
				admin.setImage(fileImage.getName());
				//Tiến hành ghi file ảnh
				DataHelper.writeFileToSource(DataHelper.getArrayByteFromFile(fileImage), "/com/duan/image/" + fileImage.getName());
			}
			else
			{
				if (lblAvatar.getIcon() == null)
				{
					admin.setImage(null);
				}
			}
		
			boolean isSuccess = AdminDAO.update(admin, admin.getId());
			
			if (isSuccess)
			{
				AccountSave.setAdmin(admin);
				return true;
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
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
	
	public void setMainJFrame(MainJFrame mainJFrame)
	{
		this.mainJFrame = mainJFrame;
	}
}

