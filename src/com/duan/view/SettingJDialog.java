package com.duan.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import com.duan.custom.message.MessageOptionPane;
import com.duan.dao.DBConnection;
import com.duan.helper.DataHelper;
import com.duan.helper.SettingSave;
import com.duan.helper.SwingHelper;
import com.duan.main.Main;
import com.duan.model.Setting;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class SettingJDialog extends JDialog {
	private JTextField txtHost;
	private JTextField txtPort;
	private JTextField txtNameDB;
	private JTextField txtUsernameDB;
	private JTextField txtPasswordDB;
	private JTextField txtMoneySymbol;
	private JComboBox cboTimeFormat;
	private JComboBox cboDateFormat;
	private MainJFrame mainJFrame;
	private JTextField txtUsernameMail;
	private JTextField txtPasswordEmail;
	private JTextField txtDayExpiration;
	private JTextField txtCostRentExpiration;
	private JTextField txtCostBookLost;
	private JTextField txtCostRent;
	private JTextArea txtMessageReportExpiration;


	public static void main(String[] args) 
	{
		try 
		{
			SettingJDialog dialog = new SettingJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public SettingJDialog() 
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("Cài đặt");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 829, 602);
		getContentPane().setLayout(null);
		
		JPanel pnlDB = new JPanel();
		pnlDB.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 2, true), "C\u01A1 s\u1EDF d\u1EEF li\u1EC7u", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlDB.setBounds(10, 11, 411, 198);
		getContentPane().add(pnlDB);
		pnlDB.setLayout(null);
		
		JLabel lblaChHost = new JLabel("Địa chỉ host:");
		lblaChHost.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblaChHost.setBounds(10, 21, 105, 24);
		pnlDB.add(lblaChHost);
		
		txtHost = new JTextField();
		txtHost.setBounds(125, 21, 177, 24);
		pnlDB.add(txtHost);
		txtHost.setColumns(10);
		
		JLabel lblTnDatabase = new JLabel("Tên database:");
		lblTnDatabase.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTnDatabase.setBounds(10, 56, 105, 24);
		pnlDB.add(lblTnDatabase);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPort.setBounds(312, 21, 42, 24);
		pnlDB.add(lblPort);
		
		txtPort = new JTextField();
		txtPort.setColumns(10);
		txtPort.setBounds(351, 21, 50, 24);
		pnlDB.add(txtPort);
		
		txtNameDB = new JTextField();
		txtNameDB.setEditable(false);
		txtNameDB.setColumns(10);
		txtNameDB.setBounds(125, 56, 276, 24);
		pnlDB.add(txtNameDB);
		
		JLabel lblTiKhongDb = new JLabel("Tài khoảng SQL:");
		lblTiKhongDb.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTiKhongDb.setBounds(10, 91, 105, 24);
		pnlDB.add(lblTiKhongDb);
		
		txtUsernameDB = new JTextField();
		txtUsernameDB.setColumns(10);
		txtUsernameDB.setBounds(125, 91, 276, 24);
		pnlDB.add(txtUsernameDB);
		
		JLabel lblMtKhuDb = new JLabel("Mật khẩu SQL:");
		lblMtKhuDb.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMtKhuDb.setBounds(10, 126, 105, 24);
		pnlDB.add(lblMtKhuDb);
		
		txtPasswordDB = new JPasswordField();
		txtPasswordDB.setColumns(10);
		txtPasswordDB.setBounds(125, 126, 276, 24);
		pnlDB.add(txtPasswordDB);
		
		JButton btnTestConnect = new JButton("Kiểm tra kết nối");
		btnTestConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				testConnect();
			}
		});
		btnTestConnect.setBounds(275, 161, 126, 23);
		pnlDB.add(btnTestConnect);
		
		JPanel pnlFormat = new JPanel();
		pnlFormat.setLayout(null);
		pnlFormat.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 2, true), "\u0110\u1ECBnh d\u1EA1ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlFormat.setBounds(10, 220, 411, 126);
		getContentPane().add(pnlFormat);
		
		JLabel lblnVTin = new JLabel("Đơn vị tiền:");
		lblnVTin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblnVTin.setBounds(10, 21, 105, 24);
		pnlFormat.add(lblnVTin);
		
		txtMoneySymbol = new JTextField();
		txtMoneySymbol.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMoneySymbol.setColumns(10);
		txtMoneySymbol.setBounds(125, 21, 276, 24);
		pnlFormat.add(txtMoneySymbol);
		
		JLabel lblnhDngNgy = new JLabel("Định dạng ngày:");
		lblnhDngNgy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblnhDngNgy.setBounds(10, 56, 105, 24);
		pnlFormat.add(lblnhDngNgy);
		
		JLabel lblnhDngGi = new JLabel("Định dạng giờ");
		lblnhDngGi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblnhDngGi.setBounds(10, 91, 105, 24);
		pnlFormat.add(lblnhDngGi);
		
		cboDateFormat = new JComboBox();
		cboDateFormat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboDateFormat.setModel(new DefaultComboBoxModel(new String[] {"dd-MM-yyyy", "dd/MM/yyyy", "yyyy-MM-dd", "yyyy/MM/dd", "MM-dd-yyyy", "MM/dd/yyyy"}));
		cboDateFormat.setBounds(125, 56, 276, 24);
		pnlFormat.add(cboDateFormat);
		
		cboTimeFormat = new JComboBox();
		cboTimeFormat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboTimeFormat.setModel(new DefaultComboBoxModel(new String[] {"hh:mm:ss", "hh:mm", "hh,mm", "mm:ss", "hh"}));
		cboTimeFormat.setBounds(125, 91, 276, 24);
		pnlFormat.add(cboTimeFormat);
		
		JButton btnSave = new JButton("Lưu lại");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
					saveSetting();
			}
		});
		btnSave.setBounds(724, 538, 89, 25);
		getContentPane().add(btnSave);
		
		JButton btnDefault = new JButton("Phục hồi mặc định");
		btnDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				SettingSave.setSettingToDefault();
				showDetail();
			}
		});
		btnDefault.setBounds(590, 538, 124, 25);
		getContentPane().add(btnDefault);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 2, true), "Th\u01B0 Email", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(431, 11, 382, 516);
		getContentPane().add(panel);
		
		JLabel lblTiKhonMail = new JLabel("Tài khoản mail:");
		lblTiKhonMail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTiKhonMail.setBounds(20, 21, 217, 24);
		panel.add(lblTiKhonMail);
		
		txtUsernameMail = new JTextField();
		txtUsernameMail.setText("razzermkd@gmail.com");
		txtUsernameMail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtUsernameMail.setColumns(10);
		txtUsernameMail.setBounds(125, 21, 247, 24);
		panel.add(txtUsernameMail);
		
		JLabel lblMtKhuMail = new JLabel("Mật khẩu mail:");
		lblMtKhuMail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMtKhuMail.setBounds(20, 56, 217, 24);
		panel.add(lblMtKhuMail);
		
		txtPasswordEmail = new JPasswordField();
		txtPasswordEmail.setText("123456789");
		txtPasswordEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtPasswordEmail.setColumns(10);
		txtPasswordEmail.setBounds(125, 56, 247, 24);
		panel.add(txtPasswordEmail);
		
		JLabel lblNiDungBo = new JLabel("Nội dung mail báo quá hạn trả sách:");
		lblNiDungBo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNiDungBo.setBounds(20, 91, 217, 24);
		panel.add(lblNiDungBo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 126, 352, 154);
		panel.add(scrollPane);
		
		txtMessageReportExpiration = new JTextArea();
		txtMessageReportExpiration.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMessageReportExpiration.setWrapStyleWord(true);
		txtMessageReportExpiration.setLineWrap(true);
		scrollPane.setViewportView(txtMessageReportExpiration);
		
		JTextArea txtruserfullnameH = new JTextArea();
		txtruserfullnameH.setText("%user_fullname% = họ tên khách hàng\r\n%user_username% = tài khoản của khách hàng\r\n%admin_fullname% = họ tên nhân viên trực\r\n%admin_username% = tài khoảng nhân viên trực\r\n%rent_id% = mã số đơn thuê\r\n%rent_totalbook% = tổng số sách đã thuê\r\n%rent_expiration_day% = số ngày quá hạn\r\n%rent_createdDate% = ngày thuê");
		txtruserfullnameH.setOpaque(false);
		txtruserfullnameH.setWrapStyleWord(true);
		txtruserfullnameH.setLineWrap(true);
		txtruserfullnameH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtruserfullnameH.setBounds(20, 317, 352, 154);
		panel.add(txtruserfullnameH);
		
		JLabel lblCcKHiu = new JLabel("Các ký hiệu thay thế");
		lblCcKHiu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCcKHiu.setBounds(20, 291, 352, 24);
		panel.add(lblCcKHiu);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 2, true), "C\u1EA5u h\u00ECnh chung", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 357, 411, 170);
		getContentPane().add(panel_1);
		
		JLabel lblHnThuSch = new JLabel("Hạn thuê sách:");
		lblHnThuSch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHnThuSch.setBounds(10, 21, 105, 24);
		panel_1.add(lblHnThuSch);
		
		txtDayExpiration = new JTextField();
		txtDayExpiration.setText("17");
		txtDayExpiration.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDayExpiration.setColumns(10);
		txtDayExpiration.setBounds(125, 21, 53, 24);
		panel_1.add(txtDayExpiration);
		
		JLabel lblNgay = new JLabel("ngày");
		lblNgay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgay.setBounds(188, 21, 213, 24);
		panel_1.add(lblNgay);
		
		JLabel lblPhQuHn = new JLabel("Phí phạt quá hạn:");
		lblPhQuHn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPhQuHn.setBounds(10, 91, 105, 24);
		panel_1.add(lblPhQuHn);
		
		txtCostRentExpiration = new JTextField();
		txtCostRentExpiration.setText("17");
		txtCostRentExpiration.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCostRentExpiration.setColumns(10);
		txtCostRentExpiration.setBounds(125, 91, 53, 24);
		panel_1.add(txtCostRentExpiration);
		
		JLabel lblXX = new JLabel("* <ngày quá hạn> * <số sách>");
		lblXX.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblXX.setBounds(188, 91, 213, 24);
		panel_1.add(lblXX);
		
		JLabel lblPhPhtMt = new JLabel("Phí mất sách:");
		lblPhPhtMt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPhPhtMt.setBounds(10, 126, 105, 24);
		panel_1.add(lblPhPhtMt);
		
		txtCostBookLost = new JTextField();
		txtCostBookLost.setText("17");
		txtCostBookLost.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCostBookLost.setColumns(10);
		txtCostBookLost.setBounds(125, 126, 53, 24);
		panel_1.add(txtCostBookLost);
		
		JLabel label_1 = new JLabel("* <giá bán lúc thuê> * <số sách>");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setBounds(188, 126, 213, 24);
		panel_1.add(label_1);
		
		JLabel lblPhThuSch = new JLabel("Phí thuê sách:");
		lblPhThuSch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPhThuSch.setBounds(10, 56, 105, 24);
		panel_1.add(lblPhThuSch);
		
		txtCostRent = new JTextField();
		txtCostRent.setText("17");
		txtCostRent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCostRent.setColumns(10);
		txtCostRent.setBounds(125, 56, 53, 24);
		panel_1.add(txtCostRent);
		
		JLabel lblQuyn = new JLabel("* <số sách>");
		lblQuyn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQuyn.setBounds(188, 56, 213, 24);
		panel_1.add(lblQuyn);
		setLocationRelativeTo(getOwner());
		showDetail();
	}
	
	//Hiển thị thông tin của SettingSave
	public void showDetail()
	{
		//Nạp setting từ file vào
		Setting setting = SettingSave.getSetting();
		
		//setting của database
		txtHost.setText(setting.getHostDB());
		txtNameDB.setText(setting.getNameDB());
		txtPasswordDB.setText(setting.getPasswordDB());
		txtPort.setText(setting.getPortDB());
		txtUsernameDB.setText(setting.getUsernameDB());
		
		//setting của format
		txtMoneySymbol.setText(setting.getMoneySymbol());
		cboDateFormat.setSelectedItem(setting.getDateFormat());
		cboTimeFormat.setSelectedItem(setting.getTimeFormat());
		
		//setting chung
		txtDayExpiration.setText(setting.getDayExpiration() + "");
		txtCostRent.setText(setting.getCostRentBook() + "");
		txtCostRentExpiration.setText(setting.getCostRentExpiration() + "");
		txtCostBookLost.setText(setting.getCostBookLost() + "");
		
		//setting của email
		txtUsernameMail.setText(setting.getUsernameEmail());
		txtPasswordEmail.setText(setting.getPasswordEmail());
		txtMessageReportExpiration.setText(setting.getMessageReportExpiration());
		
	}
	
	//Thự hiện test connect tới máy chủ dc nhập từ form
	public void testConnect()
	{
		boolean isSuccess = false;
		try 
		{
			isSuccess = DBConnection.checkConnectionSQL(txtHost.getText(), txtNameDB.getText(), txtUsernameDB.getText(), txtPasswordDB.getText());
			if (isSuccess)
			{
				MessageOptionPane.showAlertDialog(getContentPane(), "Kết nối thành công!", MessageOptionPane.ICON_NAME_SUCCESS);
			}
			else
			{
				MessageOptionPane.showAlertDialog(getContentPane(), "Kết nối thất bại!", MessageOptionPane.ICON_NAME_ERROR);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			MessageOptionPane.showMessageDialog(getContentPane(), "Kết nối thất bại, đã có lỗi sảy ra (code: " + e.getErrorCode() + ")\n" + e.getMessage(), MessageOptionPane.ICON_NAME_ERROR);
		}
	}
	
	//Thực hiện lưu các Setting có trên form vào file
	public void saveSetting()
	{
		
		if (validateAll())
		{
			//Tiến hành set Setting vào SettingSave và ghi ra file lưu trữ
			SettingSave.setSetting(getSettingFromForm());
			SettingSave.writeSetting();
			MessageOptionPane.showAlertDialog(getContentPane(), "Lưu lại thay đổi thành công!", MessageOptionPane.ICON_NAME_SUCCESS);
			String msg = "Bạn cần phải reload lại ứng dụng để các tùy chỉnh này hoạt động chính xác!";
			if (MessageOptionPane.showConfirmDialog(getContentPane(), msg, MessageOptionPane.ICON_NAME_QUESTION, 12))
			{
				dispose();
				
				//Nếu setting này được bật từ MainJFRame thì khi save xong reload lại chỉ cần mở lại MainJFrame là dc, không cần login lại
				if (mainJFrame != null) 
				{
					mainJFrame.dispose();
					mainJFrame = new MainJFrame();
					mainJFrame.addContainer();
					mainJFrame.setVisible(true);
				}
				else
				{
					Main.main(null);
				}
				
			}
			else
			{
				dispose();
			}
		}
	}
	
	
	//Kiểm tra dữ liệu trên form có hợp lệ không
	public boolean validateAll()
	{
		boolean isSuccess = true;
		String msg = "";
		
		//CHECK kết nối Database
		try {
			isSuccess = DBConnection.checkConnectionSQL(txtHost.getText(), txtNameDB.getText(), txtUsernameDB.getText(), txtPasswordDB.getText());
		} catch (SQLException e) {
			isSuccess = false;
			msg += "+ Không thể lưu thay đổi với cấu hình database sai, vui lòng kiểm tra lại";
		}
		
		//BẮT LỖI ĐỂ TRỐNG!
		if (txtUsernameMail.getText().isEmpty())
		{
			isSuccess = false;
			msg += "+ Tài khoảng email không được để trống.\n";
		}
		else
		{
			//Kiểm tra tài khoảng email
			if (DataHelper.isEmail(txtUsernameMail.getText()) == false)
			{
				isSuccess = false;
				msg += "+ Tài khoảng email không hợp lệ.\n";
			}
		}
		
		//CHECK để trống mật khẩu email
		if (txtPasswordEmail.getText().isEmpty())
		{
			isSuccess = false;
			msg += "+ Mật khẩu email không được để trống\n";
		}
		
		//CHECK ngày hết hạn
		if (txtDayExpiration.getText().isEmpty())
		{
			isSuccess = false;
			msg += "+ Hạn thuê sách không được để trống\n";
		}
		else if (DataHelper.isInteger(txtDayExpiration.getText()) == false || DataHelper.getInt(txtDayExpiration.getText()) <= 0)
		{
			isSuccess = false;
			msg += "+ Hạn thuê sách phải là số nguyên và lớn hơn 0\n";
		}
		
		
		//CHECK phí thuê sách
		if (txtCostRent.getText().isEmpty())
		{
			isSuccess = false;
			msg += "+ Phí thuê sách không được để trống\n";
		}
		else if (DataHelper.isDouble(txtCostRent.getText()) == false || DataHelper.getDouble(txtCostRent.getText()) <= 0)
		{
			isSuccess = false;
			msg += "+ Phí thuê sách phải là số và lớn hơn 0\n";
		}
		
		//CHECK phí phát quá hạn
		if (txtCostRentExpiration.getText().isEmpty())
		{
			isSuccess = false;
			msg += "+ Phí phạt quá hạn không được để trống\n";
		}
		else if (DataHelper.isDouble(txtCostRentExpiration.getText()) == false || DataHelper.getDouble(txtCostRentExpiration.getText()) <= 0)
		{
			isSuccess = false;
			msg += "+  Phí phạt quá hạn phải là số và lớn hơn 0\n";
		}
		
		//CHECK phí mất sách
		if (txtCostBookLost.getText().isEmpty())
		{
			isSuccess = false;
			msg += "+ Phí phạt mất sách không được để trống\n";
		}
		else if (DataHelper.isDouble(txtCostBookLost.getText()) == false || DataHelper.getDouble(txtCostBookLost.getText()) <= 0)
		{
			isSuccess = false;
			msg += "+  Phí phạt mất sách phải là số và lớn hơn 0\n";
		}
		
		if (isSuccess == false)
		{
			MessageOptionPane.showMessageDialog(getContentPane(), "Đã có lỗi sảy ra:\n" + msg, MessageOptionPane.ICON_NAME_WARNING);
		}
			
		return isSuccess;
	}
	
	public void setMainJFrame(MainJFrame mainJFrame)
	{
		this.mainJFrame = mainJFrame;
	}
	
	//Trả về đối tượng Setting lấy từ trên form
	public Setting getSettingFromForm()
	{
		String host = txtHost.getText();
		String port = txtPort.getText();
		String nameDB = txtNameDB.getText();
		String usernameDB = txtUsernameDB.getText();
		String passwordDB = txtPasswordDB.getText();
		String moneySymbol = txtMoneySymbol.getText();
		String dateFormat = cboDateFormat.getSelectedItem().toString();
		String timeFormat = cboTimeFormat.getSelectedItem().toString();
		String usernameEmail = txtUsernameMail.getText();
		String passwordEmail = txtPasswordEmail.getText();
		int dayExpiration = DataHelper.getInt(txtDayExpiration.getText());
		double costRent = DataHelper.getDouble(txtCostRent.getText());
		double costRentExpiration = DataHelper.getDouble(txtCostRentExpiration.getText());
		double costBookLost = DataHelper.getDouble(txtCostBookLost.getText());
		String msgReportExpiration = txtMessageReportExpiration.getText();
		
		return new Setting(host, port, nameDB, usernameDB, passwordDB, moneySymbol, dateFormat, timeFormat, usernameEmail, passwordEmail, dayExpiration, costRent, costRentExpiration, costBookLost, msgReportExpiration);
	}
}
