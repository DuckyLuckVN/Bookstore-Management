package com.duan.custom.message;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class MessageJDialog extends CustomJDialog {
	
	public static final String ICON_NAME_INFORMATION = "icon_information_100px.png";
	public static final String ICON_NAME_WARNING = "icon_warning_100px.png";
	public static final String ICON_NAME_ERROR = "icon_error_100px.png";
	
	private final JPanel contentPanel = new JPanel();
	private int posX;
	private int posY;
	private JLabel lblIcon;
	private JTextArea txtContent;
	private JLabel lblTitle;
	
	public static void main(String[] args) 
	{
		try {
			MessageJDialog dialog = new MessageJDialog(null, "Đã có lỗi sảy ra rồi ................................................................");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MessageJDialog(Component component, String message)
	{
		this(component, message, ICON_NAME_INFORMATION);
	}
	
	public MessageJDialog(Component component, String message, String iconName)
	{
		this(component, message, iconName, "Thông Báo");
	}
	
	public MessageJDialog(Component component, String message, String iconName, String title)
	{
		this();
		setLocationRelativeTo(component);
		txtContent.setText(message);
		lblIcon.setIcon(new ImageIcon(MessageJDialog.class.getResource("/com/duan/icon/" + iconName)));
		lblTitle.setText(title);
	}

	public MessageJDialog() 
	{
		contentPanel.setBorder(new LineBorder(Color.GRAY, 3));
		setContentPane(contentPanel);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				if (e.getKeyCode() == 27)
					dispose();
			}
		});
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setModal(true);
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) 
			{
				setLocation(getX() + e.getX() - posX, getY() + e.getY() - posY);
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) 
			{
				posX = e.getX();
				posY = e.getY();
			}
		});
		getContentPane().setBackground(Color.WHITE);
		setUndecorated(true);
		setResizable(false);
		setSize(554, 230);
		getContentPane().setLayout(null);
		
		JLabel lblClose = new JLabel("X");
		lblClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblClose.setForeground(Color.WHITE);
				lblClose.setBackground(Color.RED);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblClose.setForeground(Color.BLACK);
				lblClose.setBackground(new JLabel().getBackground()); 
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblClose.setOpaque(true);
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setForeground(Color.BLACK);
		lblClose.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblClose.setBounds(529, 0, 25, 25);
		getContentPane().add(lblClose);
		
		lblIcon = new JLabel("");
		lblIcon.setBackground(Color.LIGHT_GRAY);
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setBounds(10, 31, 100, 100);
		getContentPane().add(lblIcon);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(129, 49, 418, 139);
		getContentPane().add(scrollPane);
		
		txtContent = new JTextArea();
		txtContent.setEnabled(false);
		txtContent.setEditable(false);
		txtContent.setWrapStyleWord(true);
		txtContent.setLineWrap(true);
		txtContent.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane.setViewportView(txtContent);
		
		lblTitle = new JLabel("Thông Báo");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 5, 547, 30);
		getContentPane().add(lblTitle);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		btnOk.setForeground(SystemColor.windowText);
		btnOk.setBackground(SystemColor.controlHighlight);
		btnOk.setBounds(458, 199, 89, 23);
		getContentPane().add(btnOk);
		
		setLocationRelativeTo(getOwner());

	}
}
