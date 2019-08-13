package com.duan.custom.message;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.LineBorder;

public class ConfirmJDialog extends CustomJDialog {
	
	public static final String ICON_NAME_INFORMATION = "icon_information_125px.png";
	public static final String ICON_NAME_WARNING = "icon_warning_125px.png";
	public static final String ICON_NAME_ERROR = "icon_error_125px.png";
	public static final String ICON_NAME_BLOCK = "icon_block_125px.png";
	public static final String ICON_NAME_QUESTION = "icon_question_125px.png";
	
	private final JPanel contentPanel = new JPanel();
	private int posX;
	private int posY;
	
	private JLabel lblIcon;
	private JLabel lblContent;
	
	private boolean isConfirmed = false;	
	public static void main(String[] args) 
	{
		try {
			ConfirmJDialog dialog = new ConfirmJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ConfirmJDialog(Component component, String message)
	{
		this(component, message, ICON_NAME_QUESTION);
	}

	public ConfirmJDialog(Component component, String message, String iconName)
	{
		this(component, message, iconName, 14);
	}
	
	public ConfirmJDialog(Component component, String message, String iconName, int fontSize)
	{
		this();
		setLocationRelativeTo(component);
		lblContent.setText(message);
		lblIcon.setIcon(new ImageIcon(MessageJDialog.class.getResource("/com/duan/icon/" + iconName)));
		lblContent.setFont(new Font("Tahoma", Font.BOLD, fontSize));
	}
	public ConfirmJDialog() 
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				if (e.getKeyCode() == 27)
					dispose();
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) 
			{
				isConfirmed = false;
			}
		});
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
		contentPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		setContentPane(contentPanel);
		getContentPane().setBackground(Color.WHITE);
		setUndecorated(true);
		setResizable(false);
		setSize(471, 234);
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
		lblClose.setBounds(446, 0, 25, 25);
		getContentPane().add(lblClose);
		
		lblIcon = new JLabel("");
		lblIcon.setBackground(Color.LIGHT_GRAY);
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setBounds(184, 11, 100, 100);
		getContentPane().add(lblIcon);
		
		lblContent = new JLabel("Xóa tài khoảng có username 'DuckyLuckVN' thành công!");
		lblContent.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContent.setHorizontalAlignment(SwingConstants.CENTER);
		lblContent.setBounds(0, 131, 469, 45);
		getContentPane().add(lblContent);
		
		JButton btnHy = new JButton("Hủy");
		btnHy.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnHy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				isConfirmed = false;
				dispose();
			}
		});
		btnHy.setBounds(243, 197, 89, 26);
		getContentPane().add(btnHy);
		
		JButton btnConfirm = new JButton("Xác nhận");
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				isConfirmed = true;
				dispose();
			}
		});
		btnConfirm.setBounds(132, 197, 89, 26);
		getContentPane().add(btnConfirm);
		
		setLocationRelativeTo(getOwner());
	}
	
	public boolean isConfirm()
	{
		return this.isConfirmed;
	}
}
