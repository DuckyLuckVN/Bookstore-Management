package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import diu.swe.habib.JPanelSlider.JPanelSlider;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestSlide extends JFrame {

	private JPanel contentPane;
	private JPanelSlider card;
	private CardLayout cardLayout = new CardLayout(0, 0);
	private Container containerIsShowing = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestSlide frame = new TestSlide();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestSlide() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		card = new JPanelSlider();
		card.setBounds(10, 11, 611, 322);
		contentPane.add(card);
		card.setLayout(cardLayout);
		
		JPanel pnl1 = new JPanel();
		card.add(pnl1, "name_93393783682739");
		pnl1.setLayout(null);
		
		JLabel label = new JLabel("1");
		label.setIcon(new ImageIcon(TestSlide.class.getResource("/com/duan/image/S1e18_Waddles_stare.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 11, 591, 300);
		pnl1.add(label);
		
		JPanel pnl2 = new JPanel();
		card.add(pnl2, "name_93401036342948");
		pnl2.setLayout(null);
		
		JLabel label_1 = new JLabel("2");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 99));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 11, 591, 300);
		pnl2.add(label_1);
		
		JPanel pnl3 = new JPanel();
		card.add(pnl3, "name_93402531909902");
		pnl3.setLayout(null);
		
		JLabel label_2 = new JLabel("3");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 99));
		label_2.setBounds(0, 0, 591, 300);
		pnl3.add(label_2);
		
		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if (containerIsShowing != pnl1)
				{
					containerIsShowing = pnl1;
					card.nextPanel(10, 15, pnl1, false);
					//card.refresh();
				}
			}
		});
		btn1.setBounds(10, 357, 89, 23);
		contentPane.add(btn1);
		
		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if (containerIsShowing != pnl2)
				{
					containerIsShowing = pnl2;
					card.nextPanel(10, 15, pnl2, false);
					//card.refresh();
				}
			}
		});
		btn2.setBounds(109, 357, 89, 23);
		contentPane.add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (containerIsShowing != pnl3)
				{
					containerIsShowing = pnl3;
					card.nextPanel(10, 15, pnl3, false);
					//card.refresh();
				}
			}
		});
		btn3.setBounds(208, 357, 89, 23);
		contentPane.add(btn3);
	}
}
