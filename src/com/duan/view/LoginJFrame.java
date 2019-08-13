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

import org.omg.CORBA.PRIVATE_MEMBER;

import com.duan.custom.message.MessageOptionPane;
import com.duan.dao.AdminDAO;
import com.duan.dao.UserDAO;
import com.duan.helper.AccountSave;
import com.duan.helper.SwingHelper;
import com.duan.model.Admin;
import com.duan.model.User;

import javax.swing.JTextArea;
import javax.swing.JProgressBar;

/*
 * 15/05/2019
 * Nguyễn Đại Hào
 * JDialog dùng để đăng nhập tài khoảng
 */
public class LoginJFrame extends JDialog 
{
	private JPanel contentPane;
	private JLabel lblLogo;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JPanel pnlWelcome;
	
	public int posX = 0;
	public int posY = 0;
	
	private final JPanel contentPanel = new JPanel();
	private JButton btnLogin;
	private JProgressBar proLoading;
	private Admin admin;
	private JLabel lblWelcomeName;


	public static void main(String[] args) 
	{
		try 
		{
			LoginJFrame dialog = new LoginJFrame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} 
		catch (Exception e) 
		{
			//new MessageJDialog(null, "Đã có lỗi sảy ra!", e.getMessage(), "Đã Hiểu", MessageJDialog.TYPE_ICON_ERROR).setVisible(true);
			e.printStackTrace();
		}
	}
	
	boolean checkNullForm()
	{
		boolean isSuccess = true;
		String msg = "";
		
		if (txtUsername.getText().length()== 0) 
		{
			isSuccess = false;
			msg += "+ Tài khoản không được để trống!\n";
		}
		if (txtPassword.getText().length() == 0) 
		{
			isSuccess = false;
			msg += "+ Mật khẩu không được để trống!\n";
		}
		
		if (isSuccess == false)
		{
			MessageOptionPane.showMessageDialog(contentPane, "Đã có lỗi xảy ra: \n" + msg, MessageOptionPane.ICON_NAME_WARNING);
		}
		return isSuccess;
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
		
		btnLogin = new JButton("Đăng Nhập");
		AdminDAO dao = new AdminDAO();
		
		btnLogin = new JButton("Đăng Nhập");
		btnLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (checkNullForm() == true) 
				{
					//xu ly
					String username = txtUsername.getText();
					String password = txtPassword.getText();
					try {	
						admin = dao.findByUsername(username); 
						if (admin != null) 
						{
							if (password.equals(admin.getPassword())) 
							{
								AccountSave.setAdmin(admin);
								MessageOptionPane.showAlertDialog(contentPane, "Đăng nhập thành công!", MessageOptionPane.ICON_NAME_SUCCESS);
								active();
							}
							else 
							{
								MessageOptionPane.showAlertDialog(contentPane, "Mật khẩu không chính xác", MessageOptionPane.ICON_NAME_WARNING);
								txtPassword.requestFocus();
							}
						}
						else 
						{
							MessageOptionPane.showAlertDialog(contentPane, "Tài khoản này không tồn tại!", MessageOptionPane.ICON_NAME_WARNING);
							txtUsername.requestFocus();
						}
					} catch (Exception e2) 
					{
						e2.printStackTrace();
					}
				}
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
		
		lblLogo = new JLabel("");
		lblLogo.setBounds(396, 23, 341, 258);
//		ImageIcon icon = new ImageIcon(new ImageIcon(LoginJFrame.class.getResource("/com/daihao/icon/avatar-icon.png")).getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH));
		lblLogo.setIcon(new ImageIcon(LoginJFrame.class.getResource("/com/duan/icon/giphy (1).gif")));
		SwingHelper.setAutoResizeIcon(lblLogo, Image.SCALE_DEFAULT);
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
		
		proLoading = new JProgressBar();
		proLoading.setBorder(null);
		proLoading.setForeground(new Color(255, 69, 0));
		proLoading.setBounds(0, 492, 386, 8);
		panel.add(proLoading);
		
		pnlWelcome = new JPanel();
		pnlWelcome.setOpaque(false);
		pnlWelcome.setBackground(new Color(0, 0, 0));
		pnlWelcome.setBounds(12, 151, 366, 14);
		panel.add(pnlWelcome);
		pnlWelcome.setLayout(null);
		
		JLabel lblWelcome = new JLabel("");
		lblWelcome.setIcon(new ImageIcon(LoginJFrame.class.getResource("/com/duan/icon/KindlyWickedHoki-max-1mb.gif")));
		lblWelcome.setBounds(12, 32, 342, 108);
		SwingHelper.setAutoResizeIcon(lblWelcome, Image.SCALE_DEFAULT);
		pnlWelcome.add(lblWelcome);
		
		lblWelcomeName = new JLabel("Đào Quang Tiến");
		lblWelcomeName.setForeground(new Color(255, 255, 255));
		lblWelcomeName.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblWelcomeName.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeName.setBounds(0, 132, 354, 58);
		pnlWelcome.add(lblWelcomeName);
		
		JLabel lblLoading = new JLabel("Đang tải dữ liệu...");
		lblLoading.setForeground(Color.WHITE);
		lblLoading.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoading.setBounds(0, 295, 366, 28);
		pnlWelcome.add(lblLoading);
		
		JLabel lblPhnMmQun = new JLabel("Phần mềm quản lý nhà sách chuyên nghiệp");
		lblPhnMmQun.setBounds(40, 110, 342, 29);
		panel.add(lblPhnMmQun);
		lblPhnMmQun.setForeground(new Color(255, 255, 255));
		lblPhnMmQun.setFont(new Font("Tahoma", Font.BOLD, 15));
		setLocationRelativeTo(getOwner());
	}
	
	public void animationWelcome()
	{
		int maxHeight = 332;
		lblLogo.setIcon(new ImageIcon(LoginJFrame.class.getResource("/com/duan/icon/sunrise-400.gif")));
		getContentPane().remove(btnLogin);
		
		
		txtUsername.setEnabled(false);
		txtPassword.setEnabled(false);
		
		SwingHelper.setAutoResizeIcon(lblLogo, Image.SCALE_DEFAULT);
		new Thread(new Runnable() 
		{
			@Override
			public void run() 
			{
				while (true)
				{
					int pnlHeight = pnlWelcome.getHeight();
					pnlWelcome.setSize(pnlWelcome.getWidth(), pnlHeight+1);
					if (pnlHeight + 1 >= maxHeight)
						break;
					try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}}
			}
		}).start();
	}
	
	//Chạy processbar để kích hoạt
	public void runProcessBar()
	{
		new Thread(new Runnable() 
		{
			int maxValue = proLoading.getMaximum();
			@Override
			public void run() 
			{
				try 
				{
					while (true)
					{
						int value = proLoading.getValue();
						if (value < maxValue)
						{
							proLoading.setValue(++value);
							Thread.sleep(45);
						}
						else
						{
							dispose();
							showMainJFrame();
							break;
						}
					}
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public void showMainJFrame()
	{
		MainJFrame mainJFrame = new MainJFrame();
		mainJFrame.addContainer();
		mainJFrame.setVisible(true);
	}
	
	//Goi ham nay khi login thanh cong
	public void active()
	{
		lblWelcomeName.setText(admin.getFullname());
		AccountSave.setAdmin(admin);
		animationWelcome();
		runProcessBar();
	}
}
