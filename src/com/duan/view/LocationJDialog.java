package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class LocationJDialog extends JDialog {

	private JPanel contentPane;
	private JTable tblLocation;
	private JTextField txtMaKeSach;
	private JTextField txtTenKe;
	private JTextField txtSucChua;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryJDialog frame = new CategoryJDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LocationJDialog() 
	{
		setModal(true);
		setResizable(false);
		setTitle("Quản lý kệ sách");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CategoryJDialog.class.getResource("/com/duan/icon/icons8_medium_priority_50px.png")));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 495, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 227, 471, 189);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 8, 344, 208);
		panel.setLayout(null);
		
		tblLocation = new JTable();
		tblLocation.setModel(new DefaultTableModel(null, new String[] {"MÃ KỆ SÁCH", "TÊN KỆ", "SỨC CHỨA", "GHI CHÚ"})
		{
			boolean[] columnEditables = new boolean[] {
				true, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblLocation.getColumnModel().getColumn(1).setResizable(false);
		contentPane.setLayout(null);
		scrollPane.setViewportView(tblLocation);
		contentPane.add(scrollPane);
		contentPane.add(panel);
		
		JLabel lblMThLoi = new JLabel("Mã kệ sách");
		lblMThLoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMThLoi.setBounds(10, 19, 77, 25);
		panel.add(lblMThLoi);
		
		txtMaKeSach = new JTextField();
		txtMaKeSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaKeSach.setBounds(97, 19, 237, 25);
		panel.add(txtMaKeSach);
		txtMaKeSach.setColumns(10);
		
		txtTenKe = new JTextField();
		txtTenKe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTenKe.setColumns(10);
		txtTenKe.setBounds(97, 55, 237, 25);
		panel.add(txtTenKe);
		
		JLabel lblTnThLoi = new JLabel("Tên kệ");
		lblTnThLoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTnThLoi.setBounds(10, 55, 77, 25);
		panel.add(lblTnThLoi);
		
		JLabel lblGhiCh = new JLabel("Ghi chú");
		lblGhiCh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGhiCh.setBounds(10, 127, 77, 25);
		panel.add(lblGhiCh);
		
		JTextArea txtGhiChu = new JTextArea();
		txtGhiChu.setBorder(new LineBorder(SystemColor.controlShadow));
		txtGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtGhiChu.setBounds(97, 130, 237, 67);
		panel.add(txtGhiChu);
		
		JLabel lblSLngLu = new JLabel("Sức chứa");
		lblSLngLu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSLngLu.setBounds(10, 91, 77, 25);
		panel.add(lblSLngLu);
		
		txtSucChua = new JTextField();
		txtSucChua.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSucChua.setColumns(10);
		txtSucChua.setBounds(97, 91, 237, 25);
		panel.add(txtSucChua);
		
		JPanel pnlControll = new JPanel();
		pnlControll.setBorder(new TitledBorder(null, "\u0110i\u1EC1u khi\u1EC3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlControll.setBounds(364, 8, 117, 208);
		contentPane.add(pnlControll);
		pnlControll.setLayout(new GridLayout(0, 1, 0, 10));
		
		JButton btnThm = new JButton(" Lưu");
		btnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ret = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn lưu dữ liệu mới ?", "Confirm", JOptionPane.YES_NO_OPTION);
				if (ret != JOptionPane.YES_OPTION) {
					return;
				}
				try {
					String user="sa";
					String pass = "123";
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					String url = "jdbc:sqlserver://localhost:1433;databaseName=BookStore";
					Connection con = DriverManager.getConnection(url, user, pass);
					String sql="insert into tblCategory scrollPane value(?,?,?,?)";
					PreparedStatement st = con.prepareStatement(sql);
					st.setString(1,txtMaKeSach.getText());
					st.setString(2, txtTenKe.getText());
					st.setString(3, txtSucChua.getText());
					st.setString(3, txtGhiChu.getText());
					st.executeUpdate();
				} catch (Exception e2) {
					System.out.print(e2);
				}
			}
		});
		pnlControll.add(btnThm);
		btnThm.setHorizontalAlignment(SwingConstants.LEFT);
		btnThm.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/Accept.png")));
		
		JButton btnCpNht = new JButton("Cập nhật");
		btnCpNht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn cập nhật dữ liệu mới ?", "Confirm", JOptionPane.YES_NO_OPTION);
				if (ret != JOptionPane.YES_OPTION) {
					return;
				}
				try {
					String user ="sa";
					String pass ="123";
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					String url="jdbc:sqlserver://localhost:1433;databaseName=BookStore2";
					Connection con = DriverManager.getConnection(url, user, pass);
					String sql = "update tblLocation set uMaKeSach=?, uTenKe=?, uSucChua=?, uGhiChu=?";
					PreparedStatement st = con.prepareStatement(sql);
					st.setString(1, txtMaKeSach.getText());
					st.setString(2, txtTenKe.getText());
					st.setString(3, txtSucChua.getText());
					st.setString(4, txtGhiChu.getText());
					st.executeUpdate();
					con.close();
					LoadDataToJTable();
				} catch (Exception ex) {
					System.out.print(ex);
				}
			}

			private void LoadDataToJTable() {
				// TODO Auto-generated method stub
				
			}
		});
		pnlControll.add(btnCpNht);
		btnCpNht.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/Notes.png")));
		btnCpNht.setHorizontalAlignment(SwingConstants.LEFT);
		
		JButton btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(rootPane, "bạn có muốn xóa không ?", "Chọn", JOptionPane.YES_NO_OPTION);
				if(ret != JOptionPane.YES_OPTION) {
					return;
				}
				Connection c = null;
				PreparedStatement ps = null;
               try {
            	   c = DriverManager.getConnection("jdbc:sqlserver://localhost;DatabaseName=BookStore", "sa", "123");
            	   ps = c.prepareStatement("Delete From tblLocation where id = ?");
            	   ps.setString(1, txtMaKeSach.getText());
            	   ret = ps.executeUpdate();
            	   if (ret != -1) {
            		   JOptionPane.showMessageDialog(rootPane, "Thông tin đã được xóa");  
            	   }
			} catch (Exception ex) {
				 ex.printStackTrace();
			}finally {
				try {
					if (c != null) {
					     c.close();
					   }
					if (ps != null) {
					     ps.close();
					   }
				} catch (Exception ex2) {
					   ex2.printStackTrace();
				}
			}
			}
		});
		pnlControll.add(btnXa);
		btnXa.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/icons8_delete_32px_1.png")));
		btnXa.setHorizontalAlignment(SwingConstants.LEFT);
		
		JButton btnMi = new JButton(" Mới");
		btnMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMaKeSach.setText("");
				txtTenKe.setText("");
				txtGhiChu.setText("");
				txtSucChua.setText("");
				txtMaKeSach.requestFocus();
			}
		});
		pnlControll.add(btnMi);
		btnMi.setHorizontalAlignment(SwingConstants.LEFT);
		btnMi.setIcon(new ImageIcon(CategoryJDialog.class.getResource("/com/duan/icon/Create.png")));
		setLocationRelativeTo(getOwner());
	}
}
