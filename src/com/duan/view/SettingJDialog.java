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

public class SettingJDialog extends JDialog {
	private JTextField txtHost;
	private JTextField txtPort;
	private JTextField txtNameDB;
	private JTextField txtUsernameDB;
	private JTextField txtPasswordDB;
	private JTextField txtMoneySymbol;
	private JComboBox cboTimeFormat;
	private JComboBox cboDateFormat;
	private MainJFrame2 mainJFrame;
	private JTextField txtUsernameMail;
	private JTextField txtPasswordEmail;
	private JTextField txtDayExpiration;


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
		setBounds(100, 100, 387, 602);
		getContentPane().setLayout(null);
		
		JPanel pnlDB = new JPanel();
		pnlDB.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "C\u01A1 s\u1EDF d\u1EEF li\u1EC7u", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlDB.setBounds(10, 11, 362, 198);
		getContentPane().add(pnlDB);
		pnlDB.setLayout(null);
		
		JLabel lblaChHost = new JLabel("Địa chỉ host:");
		lblaChHost.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblaChHost.setBounds(10, 21, 105, 24);
		pnlDB.add(lblaChHost);
		
		txtHost = new JTextField();
		txtHost.setBounds(125, 21, 124, 24);
		pnlDB.add(txtHost);
		txtHost.setColumns(10);
		
		JLabel lblTnDatabase = new JLabel("Tên database:");
		lblTnDatabase.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTnDatabase.setBounds(10, 56, 105, 24);
		pnlDB.add(lblTnDatabase);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPort.setBounds(259, 21, 42, 24);
		pnlDB.add(lblPort);
		
		txtPort = new JTextField();
		txtPort.setColumns(10);
		txtPort.setBounds(298, 21, 50, 24);
		pnlDB.add(txtPort);
		
		txtNameDB = new JTextField();
		txtNameDB.setEditable(false);
		txtNameDB.setColumns(10);
		txtNameDB.setBounds(125, 56, 223, 24);
		pnlDB.add(txtNameDB);
		
		JLabel lblTiKhongDb = new JLabel("Tài khoảng SQL:");
		lblTiKhongDb.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTiKhongDb.setBounds(10, 91, 105, 24);
		pnlDB.add(lblTiKhongDb);
		
		txtUsernameDB = new JTextField();
		txtUsernameDB.setColumns(10);
		txtUsernameDB.setBounds(125, 91, 223, 24);
		pnlDB.add(txtUsernameDB);
		
		JLabel lblMtKhuDb = new JLabel("Mật khẩu SQL:");
		lblMtKhuDb.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMtKhuDb.setBounds(10, 126, 105, 24);
		pnlDB.add(lblMtKhuDb);
		
		txtPasswordDB = new JTextField();
		txtPasswordDB.setColumns(10);
		txtPasswordDB.setBounds(125, 126, 223, 24);
		pnlDB.add(txtPasswordDB);
		
		JButton btnTestConnect = new JButton("Thử kết nối");
		btnTestConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				testConnect();
			}
		});
		btnTestConnect.setBounds(259, 161, 89, 23);
		pnlDB.add(btnTestConnect);
		
		JPanel pnlFormat = new JPanel();
		pnlFormat.setLayout(null);
		pnlFormat.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u0110\u1ECBnh d\u1EA1ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlFormat.setBounds(10, 220, 362, 126);
		getContentPane().add(pnlFormat);
		
		JLabel lblnVTin = new JLabel("Đơn vị tiền:");
		lblnVTin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblnVTin.setBounds(10, 21, 105, 24);
		pnlFormat.add(lblnVTin);
		
		txtMoneySymbol = new JTextField();
		txtMoneySymbol.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMoneySymbol.setColumns(10);
		txtMoneySymbol.setBounds(125, 21, 227, 24);
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
		cboDateFormat.setBounds(125, 56, 227, 24);
		pnlFormat.add(cboDateFormat);
		
		cboTimeFormat = new JComboBox();
		cboTimeFormat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboTimeFormat.setModel(new DefaultComboBoxModel(new String[] {"hh:mm:ss", "hh:mm", "hh,mm", "mm:ss", "hh"}));
		cboTimeFormat.setBounds(125, 91, 227, 24);
		pnlFormat.add(cboTimeFormat);
		
		JButton btnSave = new JButton("Lưu lại");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
					saveSetting();
			}
		});
		btnSave.setBounds(282, 538, 89, 25);
		getContentPane().add(btnSave);
		
		JButton btnDefault = new JButton("Mặc định");
		btnDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				SettingSave.setSettingToDefault();
				showDetail();
			}
		});
		btnDefault.setBounds(183, 538, 89, 25);
		getContentPane().add(btnDefault);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "T\u00E0i kho\u1EA3ng Email", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 357, 362, 97);
		getContentPane().add(panel);
		
		JLabel lblTiKhonMail = new JLabel("Tài khoản mail:");
		lblTiKhonMail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTiKhonMail.setBounds(10, 21, 105, 24);
		panel.add(lblTiKhonMail);
		
		txtUsernameMail = new JTextField();
		txtUsernameMail.setText("razzermkd@gmail.com");
		txtUsernameMail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtUsernameMail.setColumns(10);
		txtUsernameMail.setBounds(125, 21, 227, 24);
		panel.add(txtUsernameMail);
		
		JLabel lblMtKhuMail = new JLabel("Mật khẩu mail:");
		lblMtKhuMail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMtKhuMail.setBounds(10, 56, 105, 24);
		panel.add(lblMtKhuMail);
		
		txtPasswordEmail = new JPasswordField();
		txtPasswordEmail.setText("123456789");
		txtPasswordEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtPasswordEmail.setColumns(10);
		txtPasswordEmail.setBounds(125, 56, 227, 24);
		panel.add(txtPasswordEmail);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "C\u1EA5u h\u00ECnh chung", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 465, 362, 62);
		getContentPane().add(panel_1);
		
		JLabel lblHnThuSch = new JLabel("Hạn thuê sách:");
		lblHnThuSch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHnThuSch.setBounds(10, 21, 105, 24);
		panel_1.add(lblHnThuSch);
		
		txtDayExpiration = new JTextField();
		txtDayExpiration.setText("17");
		txtDayExpiration.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDayExpiration.setColumns(10);
		txtDayExpiration.setBounds(125, 21, 43, 24);
		panel_1.add(txtDayExpiration);
		
		JLabel lblNgay = new JLabel("ngày");
		lblNgay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgay.setBounds(178, 21, 43, 24);
		panel_1.add(lblNgay);
		
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
		
		//setting của email
		txtUsernameMail.setText(setting.getUsernameEmail());
		txtPasswordEmail.setText(setting.getPasswordEmail());
		
		//setting chung
		txtDayExpiration.setText(setting.getDayExpiration() + "");
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
				JOptionPane.showMessageDialog(getContentPane(), "Kết nối thành công!");
			}
			else
			{
				JOptionPane.showMessageDialog(getContentPane(), "Kết nối thất bại!");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(getContentPane(), "Kết nối thất bại, đã có lỗi sảy ra (code: " + e.getErrorCode() + ")\n" + e.getMessage());
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
			JOptionPane.showMessageDialog(getContentPane(), "Lưu lại thay đổi thành công!");
			String msg = "Bạn cần phải reload lại ứng dụng để các tùy chỉnh này hoạt động chính xác!";
			if (SwingHelper.showConfirm(getContentPane(), msg))
			{
				dispose();
				
				//Nếu setting này được bật từ MainJFRame thì khi save xong reload lại chỉ cần mở lại MainJFrame là dc, không cần login lại
				if (mainJFrame != null) 
				{
					mainJFrame.dispose();
					mainJFrame = new MainJFrame2();
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
			msg += "+ Ngày quá hạn không được để trống\n";
		}
		else if (DataHelper.isInteger(txtDayExpiration.getText()) == false || DataHelper.getInt(txtDayExpiration.getText()) <= 0)
		{
			isSuccess = false;
			msg += "+ Ngày quá hạn phải là số và > 0\n";
		}
		
		if (isSuccess == false)
		{
			JOptionPane.showMessageDialog(getContentPane(), "Đã có lỗi sảy ra:\n" + msg);
		}
			
		return isSuccess;
	}
	
	public void setMainJFrame(MainJFrame2 mainJFrame)
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
		
		return new Setting(host, port, nameDB, usernameDB, passwordDB, moneySymbol, dateFormat, timeFormat, usernameEmail, passwordEmail, dayExpiration);
	}
}
