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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CategoryJDialog extends JDialog {
	String head[]= {"Mã thể loại","Tên thể loại","Ghi chú"};
	DefaultTableModel model = new DefaultTableModel(head, 0);

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
		btnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

			}
		});
		btnThm.setHorizontalAlignment(SwingConstants.LEFT);
		btnThm.setIcon(new ImageIcon(LocationJDialog.class.getResource("/com/duan/icon/Accept.png")));
		btnThm.setBounds(364, 57, 105, 38);
		contentPane.add(btnThm);
		
		JButton btnCpNht = new JButton("Cập nhật");
		btnCpNht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}

		});
		btnCpNht.setIcon(new ImageIcon(LocationJDialog.class.getResource("/com/duan/icon/Notes.png")));
		btnCpNht.setHorizontalAlignment(SwingConstants.LEFT);
		btnCpNht.setBounds(364, 106, 105, 38);
		contentPane.add(btnCpNht);
		
		JButton btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


			}
		});
		btnXa.setIcon(new ImageIcon(LocationJDialog.class.getResource("/com/duan/icon/icons8_delete_32px_1.png")));
		btnXa.setHorizontalAlignment(SwingConstants.LEFT);
		btnXa.setBounds(364, 155, 105, 38);
		contentPane.add(btnXa);
		
		JButton btn = new JButton(" Mới");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				txtMaTheLoai.setText("");
				txtTenTheLoai.setText("");
				txtGhiChu.setText("");
				txtMaTheLoai.requestFocus();
			}
		});
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		btn.setIcon(new ImageIcon(LocationJDialog.class.getResource("/com/duan/icon/Create.png")));
		btn.setBounds(364, 8, 105, 38);
		contentPane.add(btn);
		setLocationRelativeTo(getOwner());
	}
	
	public void test()
	{
		txtGhiChu.getText();
	}
}
