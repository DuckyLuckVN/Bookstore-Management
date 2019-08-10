package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.duan.custom.MessageOptionPane;
import com.duan.helper.AccountSave;
import com.duan.helper.ComponentResizer;
import com.duan.helper.SwingHelper;
import com.duan.model.Admin;

import diu.swe.habib.JPanelSlider.JPanelSlider;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.Customizer;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.dnd.DropTarget;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import javax.swing.JSeparator;

public class MainJFrame extends JFrame {

	private static final Color COLOR_MENU_DEFAULT = new Color(238, 239, 249);
	private static final Color COLOR_MENU_HOVER = new Color(232, 233, 247);
	private static final Color COLOR_MENU_CLICKED = new Color(24, 232, 226);
	private static final Border BORDER_HIGHLIGHT = new MatteBorder(0, 7, 0, 0, (Color) new Color(255, 24, 70));

	private JPanel contentPane;
	private SwingHelper sHelper = new SwingHelper();
	private JPanelSlider pnlContent;
	private JLabel lblAvatar;
	private JLabel lblChucVu;
	private JLabel lblHoTen;
	
	private JPanel pnl1;
	private JPanel pnl2;
	private JPanel pnl3;
	private JPanel pnl4;
	private JPanel pnl5;
	private JPanel pnl6;
	private JPanel pnl7;
	private JPanel pnl9;
	private Container containerSelected = null;
	
	private int posX;
	private int posY;
	
	
	
	private BorderLayout borderLayout = new BorderLayout(0, 0);
	private CardLayout cardLayout = new CardLayout(0, 0);
	
	private BookJFrame bookJFrame = new BookJFrame();
	private LoginJFrame loginJFrame = new LoginJFrame();
	private RentBookJFrame rentBookJFrame = new RentBookJFrame();
	private OrderJFrame sellBookJFrame = new OrderJFrame();
	private UserJFrame userJFrame = new UserJFrame();
	private AdminJFrame adminJFrame = new AdminJFrame();
	private StatisticalJFrame statisticalJFrame = new StatisticalJFrame();
	private BookLostJFrame bookLostJFrame = new BookLostJFrame();
	private StorageJFrame storageJFrame = new StorageJFrame();
	private SettingJDialog settingJDialog = new SettingJDialog();
	private ProfileAdminJDialog profileAdminJDialog = new ProfileAdminJDialog();
	
	//Khai báo container
	private Container bookContainer = bookJFrame.getContentPane();
	private Container rentbContainer = rentBookJFrame.getContentPane();
	private Container sellBookContainer = sellBookJFrame.getContentPane();
	private Container userContainer = userJFrame.getContentPane();
	private Container adminContainer = adminJFrame.getContentPane();
	private Container statisticalContainer = statisticalJFrame.getContentPane();
	private Container bookLostContainer = bookLostJFrame.getContentPane();
	private Container storageContainer = storageJFrame.getContentPane();
	private JLabel lblBackgroundMain;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					MainJFrame frame = new MainJFrame();
					frame.addContainer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	 private void createAnsShowGui() 
	 {
	        ComponentResizer cr = new ComponentResizer();
	        cr.setMinimumSize(new Dimension(10, 10));
	        cr.setMaximumSize(new Dimension(1920, 1080));
	        cr.registerComponent(this);
	        cr.setSnapSize(new Dimension(10, 10));
	    }
	 
	public MainJFrame() 
	{
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
		createAnsShowGui() ;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) 
			{
				if (MessageOptionPane.showConfirmDialog(getContentPane(), "Bạn có chắc muốn tắt ứng dụng này không?"))
				{
					System.exit(0);
				}
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainJFrame.class.getResource("/com/duan/icon/icons8_book_64px_3.png")));
		setTitle("Bookstore Managerment");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1093, 764);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		
		JPanel pnlMenu = new JPanel();
		pnlMenu.setPreferredSize(new Dimension(280, 1));
		pnlMenu.setBackground(COLOR_MENU_DEFAULT);
		
		JPanel pnlMenuList = new JPanel();
		pnlMenuList.setBackground(COLOR_MENU_DEFAULT);
		pnlMenuList.setLayout(new GridLayout(0, 1, 0, 0));
		
		pnl1 = new JPanel();
		pnlMenuList.add(pnl1);
		pnl1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				if (pnl1.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl1, COLOR_MENU_HOVER);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				if (pnl1.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl1, COLOR_MENU_DEFAULT);
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				setBackgroundMenuClicked(pnl1);
				showBookJFrame();
				setHighlightMenu(pnl1);
			}
		});
        
		pnl1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl1.setBackground(COLOR_MENU_DEFAULT);
		pnl1.setLayout(null);
		
		JLabel lblTItle1 = new JLabel("Sách");
		lblTItle1.setBounds(21, 0, 259, 50);
		lblTItle1.setForeground(new Color(0, 0, 0));
		lblTItle1.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/icons8_books_32px_1.png")));
		lblTItle1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTItle1.setHorizontalAlignment(SwingConstants.LEFT);
		pnl1.add(lblTItle1);
		
		pnl3 = new JPanel();
		pnlMenuList.add(pnl3);
		pnl3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				if (pnl3.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl3, COLOR_MENU_HOVER);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				if (pnl3.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl3, COLOR_MENU_DEFAULT);
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				setBackgroundMenuClicked(pnl3);
				showSellBookJFrame();
				setHighlightMenu(pnl3);
			}
		});
		pnl3.setBackground(COLOR_MENU_DEFAULT);
		pnl3.setLayout(null);
		
		JLabel lblNhnVin = new JLabel("Bán sách");
		lblNhnVin.setBounds(21, 0, 259, 50);
		lblNhnVin.setForeground(new Color(0, 0, 0));
		lblNhnVin.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/icons8_buy_for_change_32px.png")));
		lblNhnVin.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhnVin.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnl3.add(lblNhnVin);
		
		pnl2 = new JPanel();
		pnlMenuList.add(pnl2);
		pnl2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				if (pnl2.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl2, COLOR_MENU_HOVER);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				if (pnl2.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl2, COLOR_MENU_DEFAULT);
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				setBackgroundMenuClicked(pnl2);
				showRentBookJFrame();
				setHighlightMenu(pnl2);
			}
		});
		pnl2.setBackground(COLOR_MENU_DEFAULT);
		pnl2.setLayout(null);
		
		JLabel lblKhchHng = new JLabel("Thuê sách");
		lblKhchHng.setBounds(21, 0, 259, 50);
		lblKhchHng.setForeground(new Color(0, 0, 0));
		lblKhchHng.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/icons8_bookmark_32px.png")));
		lblKhchHng.setHorizontalAlignment(SwingConstants.LEFT);
		lblKhchHng.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnl2.add(lblKhchHng);
		
		pnl4 = new JPanel();
		pnlMenuList.add(pnl4);
		pnl4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				if (pnl4.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl4, COLOR_MENU_HOVER);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				if (pnl4.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl4, COLOR_MENU_DEFAULT);
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				setBackgroundMenuClicked(pnl4);
				showLostBookJFrame();
				setHighlightMenu(pnl4);
			}
		});
		pnl4.setBackground(COLOR_MENU_DEFAULT);
		pnl4.setLayout(null);
		
		JLabel lblHan = new JLabel("Mất sách");
		lblHan.setBounds(21, 0, 259, 50);
		lblHan.setForeground(new Color(0, 0, 0));
		lblHan.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/icons8-health-book-32.png")));
		lblHan.setHorizontalAlignment(SwingConstants.LEFT);
		lblHan.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnl4.add(lblHan);
		
		pnl7 = new JPanel();
		pnlMenuList.add(pnl7);
		pnl7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				if (pnl7.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl7, COLOR_MENU_HOVER);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				if (pnl7.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl7, COLOR_MENU_DEFAULT);
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				setBackgroundMenuClicked(pnl7);
				showUserJFrame();
				setHighlightMenu(pnl7);
			}
		});
		pnl7.setBackground(COLOR_MENU_DEFAULT);
		pnl7.setLayout(null);
		
		JLabel lblCuHnh = new JLabel("Khách hàng");
		lblCuHnh.setBounds(21, 0, 259, 50);
		lblCuHnh.setForeground(new Color(0, 0, 0));
		lblCuHnh.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/icons8_user_group_man_woman_32px.png")));
		lblCuHnh.setHorizontalAlignment(SwingConstants.LEFT);
		lblCuHnh.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnl7.add(lblCuHnh);
		
		pnl5 = new JPanel();
		pnlMenuList.add(pnl5);
		pnl5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl5.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				if (pnl5.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl5, COLOR_MENU_HOVER);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) 
			{
				if (pnl5.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl5, COLOR_MENU_DEFAULT);
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (AccountSave.getAdmin().getRole() == Admin.ROLE_GIAMDOC)
				{
					setBackgroundMenuClicked(pnl5);
					showStatisticalJFrame();
					setHighlightMenu(pnl5);
				}
				else
				{
					MessageOptionPane.showAlertDialog(pnlContent, "Chức năng này chỉ dành cho chức vụ Giám Đốc!", MessageOptionPane.ICON_NAME_BLOCK);
				}
			}
		});
		pnl5.setBackground(COLOR_MENU_DEFAULT);
		pnl5.setLayout(null);
		
		JLabel lblThu = new JLabel("Thống kê");
		lblThu.setBounds(21, 0, 259, 50);
		lblThu.setForeground(new Color(0, 0, 0));
		lblThu.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/icons8_statistics_32px.png")));
		lblThu.setHorizontalAlignment(SwingConstants.LEFT);
		lblThu.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnl5.add(lblThu);
		
		pnl6 = new JPanel();
		pnlMenuList.add(pnl6);
		pnl6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				if (pnl6.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl6, COLOR_MENU_HOVER);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) 
			{
				if (pnl6.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl6, COLOR_MENU_DEFAULT);
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (AccountSave.getAdmin().getRole() == Admin.ROLE_QUANLY)
				{
					setBackgroundMenuClicked(pnl6);
					showAdminJFrame();
					setHighlightMenu(pnl6);
				}
				else
				{
					MessageOptionPane.showAlertDialog(pnlContent, "Chức năng này chỉ dành cho chức vụ Quản Lý!", MessageOptionPane.ICON_NAME_BLOCK);
				}
			}
		});
		pnl6.setBackground(COLOR_MENU_DEFAULT);
		pnl6.setLayout(null);
		
		JLabel lblNhnVin_1 = new JLabel("Quản trị viên");
		lblNhnVin_1.setBounds(21, 0, 259, 50);
		lblNhnVin_1.setForeground(new Color(0, 0, 0));
		lblNhnVin_1.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/icons8_user_credentials_32px.png")));
		lblNhnVin_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhnVin_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnl6.add(lblNhnVin_1);
		
		JPanel pnl8 = new JPanel();
		pnl8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sHelper.changeBackground(pnl8, COLOR_MENU_HOVER);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sHelper.changeBackground(pnl8, COLOR_MENU_DEFAULT);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if (MessageOptionPane.showConfirmDialog(getContentPane(), "Bạn có chắc muốn đăng xuất không?"))
				{
					logout();
				}
			}
		});
		pnl8.setBackground(COLOR_MENU_DEFAULT);
		pnl8.setLayout(null);
		
		JLabel lblngXut = new JLabel("Đăng xuất");
		lblngXut.setForeground(new Color(0, 0, 0));
		lblngXut.setBounds(22, 0, 178, 51);
		lblngXut.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/icons8_exit_32px.png")));
		lblngXut.setHorizontalAlignment(SwingConstants.LEFT);
		lblngXut.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnl8.add(lblngXut);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(pnlMenu, BorderLayout.WEST);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/icons8_book_64px_3.png")));
		
		JLabel lblBookstore = new JLabel("BOOKSTORE");
		lblBookstore.setForeground(Color.DARK_GRAY);
		lblBookstore.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblBookstore.setHorizontalAlignment(SwingConstants.CENTER);
		
		pnl9 = new JPanel();
		pnl9.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnl9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				if (pnl9.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl9, COLOR_MENU_HOVER);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) 
			{
				if (pnl9.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl9, COLOR_MENU_DEFAULT);
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (AccountSave.getAdmin().getRole() == Admin.ROLE_QUANLY)
				{
					setBackgroundMenuClicked(pnl9);
					showStorageJFrame();
					setHighlightMenu(pnl9);
				}
				else
				{
					MessageOptionPane.showAlertDialog(pnlContent, "Chức năng này chỉ dành cho chức vụ Quản Lý!", MessageOptionPane.ICON_NAME_BLOCK);
				}
			}
			
		});
		pnl9.setBackground(COLOR_MENU_DEFAULT);
		pnlMenuList.add(pnl9);
		pnl9.setLayout(null);
		
		JLabel lblNhpKho = new JLabel(" Nhập kho");
		lblNhpKho.setBounds(21, 0, 259, 50);
		lblNhpKho.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/icons8_move_by_trolley_32px.png")));
		lblNhpKho.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhpKho.setForeground(Color.BLACK);
		lblNhpKho.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnl9.add(lblNhpKho);
		
		JLabel lblManagerment = new JLabel("MANAGERMENT");
		lblManagerment.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagerment.setForeground(Color.DARK_GRAY);
		lblManagerment.setFont(new Font("Tahoma", Font.BOLD, 21));
		
		JLabel lblVersion = new JLabel("Version: 1.0.1");
		lblVersion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVersion.setForeground(Color.DARK_GRAY);
		
		JPanel pnlAvatar = new JPanel();
		pnlAvatar.setBackground(COLOR_MENU_DEFAULT);
		pnlAvatar.setLayout(null);
		
		JLabel lblCicleAvatar = new JLabel("");
		lblCicleAvatar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				showProfileAdmin();
			}
		});
		lblCicleAvatar.setToolTipText("Thông tin chi tiết");
		lblCicleAvatar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCicleAvatar.setBounds(10, 11, 73, 75);
		pnlAvatar.add(lblCicleAvatar);
		lblCicleAvatar.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/cicleAvatar.png")));
		SwingHelper.setAutoResizeIcon(lblCicleAvatar);
		
		lblAvatar = new JLabel("");
		lblAvatar.setBounds(10, 11, 73, 75);
		pnlAvatar.add(lblAvatar);
		lblAvatar.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/image/43087105_2147322358815729_8587652113234919424_n.jpg")));
		SwingHelper.setAutoResizeIcon(lblAvatar);
		
		lblChucVu = new JLabel("Nhân viên");
		lblChucVu.setForeground(Color.RED);
		lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChucVu.setHorizontalAlignment(SwingConstants.CENTER);
		lblChucVu.setBounds(103, 23, 115, 20);
		pnlAvatar.add(lblChucVu);
		
		lblHoTen = new JLabel("Đào Quang Tiến");
		lblHoTen.setBounds(93, 35, 189, 43);
		pnlAvatar.add(lblHoTen);
		lblHoTen.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoTen.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblBGFrameAvatar = new JLabel("");
		lblBGFrameAvatar.setBounds(81, 22, 189, 56);
		pnlAvatar.add(lblBGFrameAvatar);
		
		lblBGFrameAvatar.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/frame2.png")));
		SwingHelper.setAutoResizeIcon(lblBGFrameAvatar);
		
		JPanel pnl10 = new JPanel();
		pnl10.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnl10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				sHelper.changeBackground(pnl10, COLOR_MENU_HOVER);
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				sHelper.changeBackground(pnl10, COLOR_MENU_DEFAULT);
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (AccountSave.getAdmin().getRole() == Admin.ROLE_QUANLY)
				{
					showSettingJDialog();
				}
				else
				{
					MessageOptionPane.showAlertDialog(pnlContent, "Chức năng này chỉ dành cho chức vụ Quản Lý!", MessageOptionPane.ICON_NAME_BLOCK);
				}
			}
		});
		pnl10.setLayout(null);
		pnl10.setBackground(COLOR_MENU_DEFAULT);
		
		JLabel lblCic = new JLabel("Cài đặt");
		lblCic.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/icons8_settings_32px_1.png")));
		lblCic.setHorizontalAlignment(SwingConstants.LEFT);
		lblCic.setForeground(Color.BLACK);
		lblCic.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCic.setBounds(25, 0, 175, 51);
		pnl10.add(lblCic);
		GroupLayout gl_pnlMenu = new GroupLayout(pnlMenu);
		gl_pnlMenu.setHorizontalGroup(
			gl_pnlMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlMenu.createSequentialGroup()
					.addGroup(gl_pnlMenu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlMenu.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_pnlMenu.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlMenu.createSequentialGroup()
									.addGap(51)
									.addComponent(lblBookstore, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblLogo)
								.addGroup(gl_pnlMenu.createSequentialGroup()
									.addGap(10)
									.addComponent(lblVersion, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnlMenu.createSequentialGroup()
									.addGap(50)
									.addComponent(lblManagerment, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))))
						.addComponent(pnlAvatar, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlMenuList, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnl8, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnl10, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_pnlMenu.setVerticalGroup(
			gl_pnlMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlMenu.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_pnlMenu.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBookstore, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlMenu.createSequentialGroup()
							.addGap(11)
							.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlMenu.createSequentialGroup()
							.addGap(69)
							.addComponent(lblVersion))
						.addGroup(gl_pnlMenu.createSequentialGroup()
							.addGap(25)
							.addComponent(lblManagerment, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)))
					.addGap(1)
					.addComponent(pnlAvatar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addComponent(pnlMenuList, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, Short.MAX_VALUE)
					.addComponent(pnl10, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnl8, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(2))
		);
		pnlMenu.setLayout(gl_pnlMenu);
		
		JPanel pnlCenter = new JPanel();
		pnlCenter.setBorder(new MatteBorder(0, 2, 0, 0, (Color) Color.DARK_GRAY));
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BorderLayout(0, 0));
		
		pnlContent = new JPanelSlider();
		pnlContent.setBackground(new Color(251, 251, 250));
		pnlCenter.add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(cardLayout);
		
		lblBackgroundMain = new JLabel("");
		lblBackgroundMain.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/image/Wingman-simple-wallpaper-backgrounds.jpg")));
		lblBackgroundMain.setHorizontalAlignment(SwingConstants.CENTER);
		pnlContent.add(lblBackgroundMain, "name_99788257003228");
		setLocationRelativeTo(getOwner());
		
		showInfoAdmin();
	}
	
	
	public void showInfoAdmin()
	{
		Admin admin = AccountSave.getAdmin();
		lblChucVu.setText(admin.getRoleTitle());
		lblHoTen.setText(admin.getFullname());
		
		//Set Avatar
		if (admin.getImage() != null && admin.getImage().length() > 0 && MainJFrame.class.getResource("/com/duan/image/" + admin.getImage()) != null)
		{
			lblAvatar.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/image/" + admin.getImage())));
			SwingHelper.setAutoResizeIcon(lblAvatar);
		}
		else
		{ //set avatar mặc định nếu admin không có image
			lblAvatar.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/duan/icon/avatar-default.png")));
			SwingHelper.setAutoResizeIcon(lblAvatar);
		}
	}
	
	public void addContainer()
	{
		pnlContent.removeAll();
		pnlContent.add(lblBackgroundMain);
		pnlContent.add(adminContainer, "1");
		pnlContent.add(bookContainer, "2");
		pnlContent.add(rentbContainer, "3");
		pnlContent.add(sellBookContainer, "4");
		pnlContent.add(statisticalContainer, "5");
		pnlContent.add(userContainer, "6");
		pnlContent.add(bookLostContainer, "7");
		pnlContent.add(storageContainer, "8");
	}
	
	//Hàm này sẽ set border các panel menu lại thành null và set border cho jpanel truyền vào là BORDER_HIGHLIGHT
	public void setHighlightMenu(JPanel pnl)
	{
		pnl1.setBorder(null);
		pnl2.setBorder(null);
		pnl3.setBorder(null);
		pnl4.setBorder(null);
		pnl5.setBorder(null);
		pnl6.setBorder(null);
		pnl7.setBorder(null);
		pnl9.setBorder(null);
		pnl.setBorder(BORDER_HIGHLIGHT);
	}
	
	public void setBackgroundMenuClicked(JPanel pnl)
	{
		pnl1.setBackground(COLOR_MENU_DEFAULT);
		pnl2.setBackground(COLOR_MENU_DEFAULT);
		pnl3.setBackground(COLOR_MENU_DEFAULT);
		pnl4.setBackground(COLOR_MENU_DEFAULT);
		pnl5.setBackground(COLOR_MENU_DEFAULT);
		pnl6.setBackground(COLOR_MENU_DEFAULT);
		pnl7.setBackground(COLOR_MENU_DEFAULT);
		pnl9.setBackground(COLOR_MENU_DEFAULT);
		pnl.setBackground(COLOR_MENU_CLICKED);
	}
	
	//Hiển thị nội dung chính panel ở giữa, dựa vào Container truyền vào
	public void setContainerShow(Container container)
	{
		if (containerSelected != container)
		{
			containerSelected = container;
			pnlContent.nextPanel(15, 15, container, true);
		}
	}
	
	public void showBookJFrame()
	{
		//bookJFrame.setVisible(true);
		setContainerShow(bookContainer);
	}
	
	public void logout()
	{
		dispose();
		AccountSave.setAdmin(null);
		loginJFrame.setVisible(true);
	}
	
	public void showLostBookJFrame()
	{
		bookLostJFrame.getDataTolist();
		bookLostJFrame.fillToTable();
		setContainerShow(bookLostContainer);
	}
	
	public void showRentBookJFrame()
	{
		//rentBookJFrame.setVisible(true);
		setContainerShow(rentbContainer);
	}
	
	public void showSellBookJFrame()
	{
//		sellBookJFrame.setLocationRelativeTo(this);
//		sellBookJFrame.setVisible(true);
		setContainerShow(sellBookContainer);
	}
	
	public void showUserJFrame()
	{
//		userJFrame.setLocationRelativeTo(this);
//		userJFrame.setVisible(true);
		setContainerShow(userContainer);
	}
	
	public void showStorageJFrame()
	{
		setContainerShow(storageContainer);
	}
	
	public void showAdminJFrame()
	{
		setContainerShow(adminContainer);
	}
	
	public void showStatisticalJFrame()
	{
		setContainerShow(statisticalContainer);
	}
	
	public void showSettingJDialog()
	{
		settingJDialog.setMainJFrame(this);
		settingJDialog.setLocationRelativeTo(this);
		settingJDialog.showDetail();
		settingJDialog.setVisible(true);
	}
	
	public void showProfileAdmin()
	{
		profileAdminJDialog = new ProfileAdminJDialog();
		profileAdminJDialog.setLocationRelativeTo(contentPane);
		profileAdminJDialog.setMainJFrame(this);
		profileAdminJDialog.setVisible(true);
	}
}
