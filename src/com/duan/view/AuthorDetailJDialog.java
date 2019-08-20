package com.duan.view;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.duan.dao.AuthorDAO;
import com.duan.helper.DataHelper;
import com.duan.helper.DateHelper;
import com.duan.helper.SettingSave;
import com.duan.helper.SwingHelper;
import com.duan.model.Author;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import javax.swing.border.LineBorder;

public class AuthorDetailJDialog extends JDialog {
	private JTextField txtFullname;
	private JTextField txtDateOfBirth;
	private JTextField txtDateOfDeath;
	private JLabel lblAvatar;
	private JTextArea txtIntroduce;

	Author author;

	public static void main(String[] args) {
		try {
			AuthorDetailJDialog dialog = new AuthorDetailJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AuthorDetailJDialog() {
		setTitle("Trần Đăng Khoa | Thông tin tác giả");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 564, 278);
		getContentPane().setLayout(null);
		{
			lblAvatar = new JLabel("Không có ảnh");
			lblAvatar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
			lblAvatar.setBounds(10, 11, 199, 231);
			getContentPane().add(lblAvatar);
		}
		{
			JLabel lblHTn = new JLabel("Họ tên:");
			lblHTn.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblHTn.setBounds(219, 11, 48, 25);
			getContentPane().add(lblHTn);
		}
		{
			txtFullname = new JTextField();
			txtFullname.setEditable(false);
			txtFullname.setForeground(Color.RED);
			txtFullname.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtFullname.setText("Trần Đăng Khoa");
			txtFullname.setBorder(null);
			txtFullname.setOpaque(false);
			txtFullname.setBounds(277, 11, 267, 25);
			getContentPane().add(txtFullname);
			txtFullname.setColumns(10);
		}
		{
			JLabel lblNmSinh = new JLabel("Năm sinh:");
			lblNmSinh.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNmSinh.setBounds(219, 47, 62, 25);
			getContentPane().add(lblNmSinh);
		}
		{
			txtDateOfBirth = new JTextField();
			txtDateOfBirth.setEditable(false);
			txtDateOfBirth.setText("09-15-1980");
			txtDateOfBirth.setOpaque(false);
			txtDateOfBirth.setForeground(Color.BLUE);
			txtDateOfBirth.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtDateOfBirth.setColumns(10);
			txtDateOfBirth.setBorder(null);
			txtDateOfBirth.setBounds(287, 47, 257, 25);
			getContentPane().add(txtDateOfBirth);
		}
		{
			txtDateOfDeath = new JTextField();
			txtDateOfDeath.setEditable(false);
			txtDateOfDeath.setText("không có");
			txtDateOfDeath.setOpaque(false);
			txtDateOfDeath.setForeground(Color.BLUE);
			txtDateOfDeath.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtDateOfDeath.setColumns(10);
			txtDateOfDeath.setBorder(null);
			txtDateOfDeath.setBounds(287, 83, 257, 25);
			getContentPane().add(txtDateOfDeath);
		}
		{
			JLabel lblNmMt = new JLabel("Năm mất:");
			lblNmMt.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNmMt.setBounds(219, 83, 62, 25);
			getContentPane().add(lblNmMt);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 2, true), "Gi\u1EDBi thi\u1EC7u", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			scrollPane.setBounds(219, 119, 328, 123);
			getContentPane().add(scrollPane);
			{
				txtIntroduce = new JTextArea();
				txtIntroduce.setFont(new Font("Tahoma", Font.PLAIN, 13));
				txtIntroduce.setEditable(false);
				txtIntroduce.setWrapStyleWord(true);
				txtIntroduce.setLineWrap(true);
				txtIntroduce.setOpaque(false);
				scrollPane.setViewportView(txtIntroduce);
			}
		}
	}
	
	public void setDetailModel(Author author)
	{
		this.author = author;
		if (author != null) { showDetail(); };
	}
	
	public void setDetailModel(int authorId)
	{
		try 
		{
			this.author = AuthorDAO.findById(authorId);
			if (author != null) { showDetail(); };
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Hiển thị thông tin chi tiết của Author
	public void showDetail()
	{
		txtFullname.setText(author.getFullName());
		txtDateOfBirth.setText(DateHelper.dateToString(author.getDateOfBirth(), SettingSave.getSetting().getDateFormat()));
		
		if (author.getDateOfDeath() != null)
			txtDateOfDeath.setText(DateHelper.dateToString(author.getDateOfDeath(), SettingSave.getSetting().getDateFormat()));
		else
			txtDateOfDeath.setText("Không có");
		
		txtIntroduce.setText(author.getIntroduce());
		
		//Set hình ảnh
		setAvatar();
	}
	
	public void setAvatar()
	{
		try 
		{
			if (author.getImage() != null && author.getImage().length() > 0)
			{
				//Kiểm tra xem ảnh này có tồn tại trong thư mục image không, nếu không thì clear avatar
				if (new File("image/" + author.getImage()) != null)
				{
					lblAvatar.setIcon(new ImageIcon(new File("image/" + author.getImage()).getAbsolutePath()));
					SwingHelper.setAutoResizeIcon(lblAvatar);
					return;
				}
			}
		} 
		catch (Exception e) 
		{
			clearAvatar();
		}
		clearAvatar();
	}
	
	public void clearAvatar()
	{
		lblAvatar.setIcon(null);
		lblAvatar.setText("Không có ảnh");
	}

}
