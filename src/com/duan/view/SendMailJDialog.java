package com.duan.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

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
			txtTaiKhoang.setBounds(108, 11, 266, 24);
			getContentPane().add(txtTaiKhoang);
			txtTaiKhoang.setColumns(10);
		}
		{
			txtMatKhau = new JPasswordField();
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
			btnGiMail.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnGiMail.setBounds(108, 313, 266, 33);
			getContentPane().add(btnGiMail);
		}
	}
}