package com.duan.custom.message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.Timer;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CustomJDialog extends JDialog
{
	private Timer timerOpen = new Timer(18, new ActionListener() 
	{
		float value = 0.0f;
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			value += 0.1f;
			setOpacity(Math.min(value, 1));
			
			if (value > 1.0)
				timerOpen.stop();
		}
	});
	
	private Timer timerClose = new Timer(15, new ActionListener() 
	{
		float value = 1.0f;
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			value -= 0.1f;
			setOpacity(Math.max(value, 0));
			
			if (value < 0)
			{
				close();	
				timerClose.stop();
			}
		}
	});
	
	public CustomJDialog() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) 
			{
				setOpacity(0);
				timerOpen.start();
			}
		});
	}
	
	@Override
	public void dispose() 
	{
		timerClose.start();
	}
	
	public void close()
	{
		super.dispose();
	}
	
}
