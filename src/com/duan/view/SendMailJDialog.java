package com.duan.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class SendMailJDialog extends JDialog {
	private JTextField txtTaiKhoang;
	private JPasswordField txtMatKhau;
	private JTextField txtDiaChi;
	private JTextField txtTieuDe;
	private JTextArea txtNoiDung ;


	public static void main(String[] args) {
		try {
			SendMailJDialog dialog = new SendMailJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public SendMailJDialog(String emailAddress) 
	{
		this();
		txtDiaChi.setText(emailAddress);
	}

	public SendMailJDialog() {
		setResizable(false);
		setTitle("Gửi mail");
		setBounds(100, 100, 400, 386);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getContentPane().setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Tài khoảng:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel.setBounds(10, 11, 88, 24);
			getContentPane().add(lblNewLabel);
		}
		{
			txtTaiKhoang = new JTextField();
			txtTaiKhoang.setText("tiendqps08547@fpt.edu.vn");
			txtTaiKhoang.setBounds(108, 11, 266, 24);
			getContentPane().add(txtTaiKhoang);
			txtTaiKhoang.setColumns(10);
		}
		{
			txtMatKhau = new JPasswordField();
			txtMatKhau.setText("quangtien");
			txtMatKhau.setColumns(10);
			txtMatKhau.setBounds(108, 46, 266, 24);
			getContentPane().add(txtMatKhau);
		}
		{
			JLabel lblMtKhu = new JLabel("Mật khẩu");
			lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblMtKhu.setBounds(10, 46, 88, 24);
			getContentPane().add(lblMtKhu);
		}
		{
			txtDiaChi = new JTextField();
			txtDiaChi.setColumns(10);
			txtDiaChi.setBounds(108, 81, 266, 24);
			getContentPane().add(txtDiaChi);
		}
		{
			JLabel lblaChGi = new JLabel("Gửi mail đến:");
			lblaChGi.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblaChGi.setBounds(10, 81, 88, 24);
			getContentPane().add(lblaChGi);
		}
		{
			JLabel lblNiDung = new JLabel("Tiêu đề");
			lblNiDung.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNiDung.setBounds(10, 116, 88, 24);
			getContentPane().add(lblNiDung);
		}
		{
			txtTieuDe = new JTextField();
			txtTieuDe.setColumns(10);
			txtTieuDe.setBounds(108, 116, 266, 24);
			getContentPane().add(txtTieuDe);
		}
		{
			JLabel lblNiDung_1 = new JLabel("Nội dung:");
			lblNiDung_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNiDung_1.setBounds(10, 151, 88, 24);
			getContentPane().add(lblNiDung_1);
		}
		
		txtNoiDung = new JTextArea();
		txtNoiDung.setBorder(new LineBorder(SystemColor.controlShadow));
		txtNoiDung.setLineWrap(true);
		txtNoiDung.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNoiDung.setBounds(108, 151, 266, 151);
		getContentPane().add(txtNoiDung);
		{
			JButton btnGiMail = new JButton("Gửi mail");
			btnGiMail.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					if (checkFrom()) {
					
					
					Properties p = new Properties();
					p.put("mail.smtp.auth", "true");
					p.put("mail.smtp.starttls.enable", "true");
					p.put("mail.smtp.host", "smtp.gmail.com");
//					p.put("mail.smtp.port", 587);
					p.put("mail.smtp.port", "587");

					Session s = Session.getInstance(p, new javax.mail.Authenticator() 
					{
						protected PasswordAuthentication getPasswordAuthentication() 
						{
							return new PasswordAuthentication(txtTaiKhoang.getText(), txtMatKhau.getText());
//							return new PasswordAuthentication("tiendqps08547@fpt.edu.vn", "quangtien");
						}
					});
					
					Message msg = new MimeMessage(s);
					
					try 
					{
						msg.setFrom(new InternetAddress(txtDiaChi.getText()));
						msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(txtDiaChi.getText()));
						msg.setSubject(txtTieuDe.getText());
						msg.setText(txtNoiDung.getText());
						Transport.send(msg);
					} 
					catch (MessagingException ex) 
					{
						ex.printStackTrace();
						System.out.println(ex.getMessage());
					}
				}
				}
			});
			btnGiMail.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnGiMail.setBounds(108, 313, 266, 33);
			getContentPane().add(btnGiMail);
		}
	}
	
	public boolean checkFrom() 
	{
		String msgString = "Đã có lỗi xảy ra : \n";
		boolean loiRong = false;
		if (txtTaiKhoang.getText().equals(""))
		{	
			msgString+="Tài khoản không được để trống !\n ";
			loiRong = true;
		}
		if (txtMatKhau.getText().equals(""))
		{	
			msgString+="Mật khẩu không được để trống !\n";
			loiRong = true;
		}
		if (txtDiaChi.getText().equals(""))
		{	
			msgString+="Địa chỉ không được để trống !\n";
			loiRong = true;
		}
		else
        {
            if (!txtDiaChi.getText().matches("\\w+@\\w+(\\.\\w+){1,2}")) 
            {
                loiRong = true;
                msgString+="Email không đúng định dạng ! \n";
            }
        }
		if (txtTieuDe.getText().equals(""))
		{	
			msgString+="Tiêu đề không được để trống !\n";
			loiRong = true;
		}
		if (txtNoiDung.getText().equals(""))
		{	
			msgString+="Nội dung không được để trống !";
			loiRong = true;
		}
		if (loiRong == true) 
		{
			JOptionPane.showMessageDialog(this, msgString);
			return false;
		}
		return true;
	}
}

