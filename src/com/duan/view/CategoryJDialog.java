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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

	

public class CategoryJDialog extends JDialog {

	String header[]= {"MÃ THỂ LOẠI","TÊN THỂ LOẠI","GHI CHÚ"};
	DefaultTableModel model = new DefaultTableModel(header, 0);
	public void CategoryJDialog() 
	{
		LoadDataToJtable();
	}
	// ĐỔ DỮ LIỆU LÊN BẢNG CATEGORY
	public void LoadDataToJtable() {
		Connection conn = null;
    	java.sql.Statement st = null;
    	ResultSet rs = null;
    	try {
    		model.setRowCount(0);
		 	conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=BookStore", "sa", "123");
		 	st = conn.createStatement();
			String sql = "select * from CATEGORY";
			rs = st.executeQuery(sql);
			 while (rs.next()) {
				   Vector data = new Vector();
				   data.add(rs.getString(1));
				   data.add(rs.getString(2));
				   data.add(rs.getString(3));
				   model.addRow(data);
				 }
			 tblCategory.setModel(model);conn.close();
		} catch (Exception e) {
			 System.out.println(e);
		} 
	}
   
	private JPanel contentPane;
	private JTable tblCategory;
	private JTextField txtMaTheLoai;
	private JTextField txtTenTheLoai;
	private JTextArea txtGhiChu;

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

	public CategoryJDialog() 
	{		
		setModal(true);
		setResizable(false);
		setTitle("Quản lý thể loại");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LocationJDialog.class.getResource("/com/duan/icon/icons8_medium_priority_50px.png")));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 486, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		scrollPane.setBounds(10, 204, 459, 147);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 8, 344, 185);
		panel.setLayout(null);
		
		tblCategory = new JTable();
		tblCategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblCategory.getSelectedRow();
				txtMaTheLoai.setText(tblCategory.getValueAt(row, 0).toString());
				txtTenTheLoai.setText(tblCategory.getValueAt(row, 1).toString());
				txtGhiChu.setText(tblCategory.getValueAt(row, 2).toString());
			}
		});
		tblCategory.setModel(new DefaultTableModel(null, new String[] {"MÃ THỂ LOẠI", "TÊN THỂ LOẠI", "GHI CHÚ"})
		{
			boolean[] columnEditables = new boolean[] {
				true, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblCategory.getColumnModel().getColumn(1).setResizable(false);
		contentPane.setLayout(null);
		scrollPane.setViewportView(tblCategory);
		contentPane.add(scrollPane);
		contentPane.add(panel);
		
		JLabel lblMThLoi = new JLabel("Mã thể loại");
		lblMThLoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMThLoi.setBounds(10, 11, 77, 33);
		panel.add(lblMThLoi);
		
		txtMaTheLoai = new JTextField();
		txtMaTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaTheLoai.setBounds(97, 11, 237, 33);
		panel.add(txtMaTheLoai);
		txtMaTheLoai.setColumns(10);
		
		txtTenTheLoai = new JTextField();
		txtTenTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTenTheLoai.setColumns(10);
		txtTenTheLoai.setBounds(97, 55, 237, 33);
		panel.add(txtTenTheLoai);
		
		JLabel lblTnThLoi = new JLabel("Tên thể loại");
		lblTnThLoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTnThLoi.setBounds(10, 55, 77, 33);
		panel.add(lblTnThLoi);
		
		JLabel lblGhiCh = new JLabel("Ghi chú");
		lblGhiCh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGhiCh.setBounds(10, 99, 77, 25);
		panel.add(lblGhiCh);
		
		txtGhiChu = new JTextArea();
		txtGhiChu.setBorder(new LineBorder(SystemColor.controlShadow));
		txtGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtGhiChu.setBounds(97, 99, 237, 65);
		panel.add(txtGhiChu);
		
		JButton btnThm = new JButton(" Lưu");
		btnThm.setHorizontalAlignment(SwingConstants.LEFT);
		btnThm.setIcon(new ImageIcon(LocationJDialog.class.getResource("/com/duan/icon/Accept.png")));
		btnThm.setBounds(364, 57, 105, 38);
		contentPane.add(btnThm);
		
		JButton btnCpNht = new JButton("Cập nhật");
		
		btnCpNht.setIcon(new ImageIcon(LocationJDialog.class.getResource("/com/duan/icon/Notes.png")));
		btnCpNht.setHorizontalAlignment(SwingConstants.LEFT);
		btnCpNht.setBounds(364, 106, 105, 38);
		contentPane.add(btnCpNht);
		
		JButton btnXa = new JButton("Xóa");
		btnXa.setIcon(new ImageIcon(LocationJDialog.class.getResource("/com/duan/icon/icons8_delete_32px_1.png")));
		btnXa.setHorizontalAlignment(SwingConstants.LEFT);
		btnXa.setBounds(364, 155, 105, 38);
		contentPane.add(btnXa);
		
		JButton btn = new JButton(" Mới");
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		btn.setIcon(new ImageIcon(LocationJDialog.class.getResource("/com/duan/icon/Create.png")));
		btn.setBounds(364, 8, 105, 38);
		contentPane.add(btn);
		setLocationRelativeTo(getOwner());
		
		//ĐỊNH NGHĨA NÚT CẬP NHẬT(update)
		btnCpNht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String user ="sa";
					String pass ="123";
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					String url="jdbc:sqlserver://localhost:1433;databaseName=BookStore";
					Connection con = DriverManager.getConnection(url, user, pass);
					String sql = "update CATEGORY set category_title=?, category_description=? where Id=?" ;
					PreparedStatement st = con.prepareStatement(sql);
					st.setString(1, txtTenTheLoai.getText());
					st.setString(2, txtGhiChu.getText());
					st.setString(3, txtMaTheLoai.getText());
					st.executeUpdate();
					LoadDataToJtable();
				} catch (Exception e) {
					System.out.print(e);
				}
			}
		});
		
		//ĐỊNH NGHĨA NÚT MỚI
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				txtMaTheLoai.setText("");
				txtTenTheLoai.setText("");
				txtGhiChu.setText("");
				txtMaTheLoai.requestFocus();
			}
		});
		
		//ĐỊNH NGHĨA NÚT LƯU
		btnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Nếu không nhập tên thể loại
				if (txtTenTheLoai.getText().isEmpty()) {
				 JOptionPane.showMessageDialog(rootPane, "Không được bỏ trống tên thể loại");
				 txtTenTheLoai.requestFocus();
				 return;
				}
				// Nếu không nhập mã thể loại
				if (txtMaTheLoai.getText().isEmpty()) {
				 JOptionPane.showMessageDialog(rootPane, "Không được bỏ trống mã thể loại");
				 txtMaTheLoai.requestFocus();
				 return;
				}
				int ret = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn lưu dữ liệu ?", "Confirm", JOptionPane.YES_NO_OPTION);
				// Trường hợp không lưu
				 if (ret != JOptionPane.YES_OPTION) {
				 return;
				 }
				// Câu lệnh insert
				 String insert = "insert into CATEGORY values(?, ?, ?)";
				 System.out.println(insert);
				 Connection conn = null;
				 PreparedStatement ps = null;
				 try {
					  conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=BookStore", "sa", "123");
					  ps = conn.prepareStatement(insert);
					  ps.setString(1, txtMaTheLoai.getText());
					  ps.setString(2, txtTenTheLoai.getText());
					  ps.setString(3, txtGhiChu.getText());
					  ret = ps.executeUpdate();
					  if (ret != -1) {
				              JOptionPane.showMessageDialog(rootPane, "Dữ liệu lưu thành công");
						  }
					  LoadDataToJtable();
				} catch (Exception e2) {
					 e2.printStackTrace();
				}finally {
					try {
					    if (conn != null) {
					     conn.close();
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
		
		//ĐỊNH NGHĨA NÚT XÓA 
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ret = JOptionPane.showConfirmDialog(rootPane, "bạn có muốn xóa không ?", "Chọn", JOptionPane.YES_NO_OPTION);
				if(ret != JOptionPane.YES_OPTION) {
					return;
				}
				Connection c = null;
				PreparedStatement ps = null;
               try {
            	   c = DriverManager.getConnection("jdbc:sqlserver://localhost;DatabaseName=BookStore", "sa", "123");
            	   ps = c.prepareStatement("delete from CATEGORY where id= ?");
            	   ps.setString(1, txtMaTheLoai.getText());
            	   ret = ps.executeUpdate();
            	   if (ret != -1) {
            		   JOptionPane.showMessageDialog(rootPane, "Thể loại đã được xóa"); 
            	   }
            	   LoadDataToJtable();
			} catch (Exception ex) {
				 System.out.println(ex);
			}
			}
		});
		LoadDataToJtable();
	}
}
