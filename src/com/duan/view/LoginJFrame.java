package com.duan.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.duan.helper.AccountSave;
import com.duan.helper.SwingHelper;
import javax.swing.JTextArea;

/*
 * 15/05/2019
 * Nguyễn Đại Hào
 * JDialog dùng để đăng nhập tài khoảng
 */
public class LoginJFrame extends JDialog 
{
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	public int posX = 0;
	public int posY = 0;
	
	private final JPanel contentPanel = new JPanel();


	public static void main(String[] args) {
		try {
			LoginJFrame dialog = new LoginJFrame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			//new MessageJDialog(null, "Đã có lỗi sảy ra!", e.getMessage(), "Đã Hiểu", MessageJDialog.TYPE_ICON_ERROR).setVisible(true);
			e.printStackTrace();
		}
	}


	public LoginJFrame() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setUndecorated(true);
		try 
		{
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) 
		{
			//new MessageJDialog(this, "Đã có lỗi sảy ra!", e1.getMessage(), "Đã Hiểu", MessageJDialog.TYPE_ICON_ERROR).setVisible(true);
			e1.printStackTrace();
		}
		setResizable(false);
		setTitle("Đăng Nhập");
		setBounds(100, 100, 755, 500);
		contentPane = new JPanel();
		
		contentPane.addMouseMotionListener(new MouseMotionAdapter() 
		{
			@Override
			public void mouseDragged(MouseEvent e) 
			{
				setLocation(e.getXOnScreen() - posX, e.getYOnScreen() - posY);
			}
		});
		contentPane.addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e) 
			{
				posX = e.getX();
				posY = e.getY();
//				
//				System.out.println("x: " + posX + ", y: " + posY);
//				System.out.println("xx: " + e.getXOnScreen() + ", yy: " + e.getYOnScreen());
//				System.out.println("localtion: " + (e.getXOnScreen() - posX) +", " + (e.getYOnScreen() - posY));
			}
		});
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblExit = new JLabel("X");
		lblExit.setOpaque(true);
		lblExit.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				lblExit.setForeground(Color.WHITE);
				lblExit.setBackground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				lblExit.setForeground(Color.black);
				lblExit.setBackground(new JLabel().getBackground());
			}
		});
		lblExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblExit.setForeground(new Color(0, 0, 0));
		lblExit.setBounds(726, 0, 29, 29);
		contentPane.add(lblExit);
		
		JPanel pnlForm = new JPanel();
		pnlForm.setBackground(new Color(255, 255, 255));
		pnlForm.setBounds(396, 246, 341, 181);
		contentPane.add(pnlForm);
		pnlForm.setLayout(null);
		
		JLabel lblUsername = new JLabel("Tên đăng nhập");
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setForeground(new Color(0, 0, 0));
		lblUsername.setBounds(10, 11, 321, 29);
		pnlForm.add(lblUsername);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtUsername = new JTextField();
		txtUsername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

			}
		});
		txtUsername.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsername.setBackground(new Color(246, 246, 246));
		txtUsername.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
		txtUsername.setBounds(10, 39, 321, 46);
		pnlForm.add(txtUsername);
		txtUsername.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Mật khẩu");
		lblPassword.setForeground(new Color(0, 0, 0));
		lblPassword.setBounds(10, 96, 321, 29);
		pnlForm.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtPassword = new JPasswordField();
		txtPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

			}
		});
		txtPassword.setEchoChar('•');
		txtPassword.setHorizontalAlignment(SwingConstants.LEFT);
		txtPassword.setBackground(new Color(246, 246, 246));
		txtPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
		txtPassword.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtPassword.setBounds(10, 124, 321, 46);
		pnlForm.add(txtPassword);
		
		JButton btnLogin = new JButton("Đăng Nhập");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

			}
		});
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				btnLogin.setBackground(new Color(40, 195, 252));
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				btnLogin.setBackground(new Color(30, 144, 255));
				
			}
		});
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(62, 144, 255));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLogin.setBounds(399, 438, 335, 51);
		contentPane.add(btnLogin);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(460, 23, 211, 213);
//		ImageIcon icon = new ImageIcon(new ImageIcon(LoginJFrame.class.getResource("/com/daihao/icon/avatar-icon.png")).getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH));
		lblLogo.setIcon(new ImageIcon(LoginJFrame.class.getResource("/com/duan/icon/avatar-icon.png")));
		SwingHelper.setAutoResizeIcon(lblLogo);
		contentPane.add(lblLogo);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(65, 71, 86));
		panel.setBounds(0, 0, 386, 500);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginJFrame.class.getResource("/com/duan/icon/icons8_book_64px_3.png")));
		lblNewLabel.setBounds(39, 36, 64, 85);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("BOOKSTORE");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(112, 35, 286, 57);
		panel.add(lblNewLabel_1);
		
		JLabel lblManagerment = new JLabel("MANAGERMENT");
		lblManagerment.setHorizontalAlignment(SwingConstants.LEFT);
		lblManagerment.setForeground(Color.WHITE);
		lblManagerment.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblManagerment.setBounds(111, 68, 286, 57);
		panel.add(lblManagerment);
		setLocationRelativeTo(getOwner());
	}
}
