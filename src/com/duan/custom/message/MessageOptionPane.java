package com.duan.custom.message;

import java.awt.Component;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class MessageOptionPane 
{
	public static final String ICON_NAME_INFORMATION = "icon_information_125px.png";
	public static final String ICON_NAME_WARNING = "icon_warning_125px.png";
	public static final String ICON_NAME_ERROR = "icon_error_125px.png";
	public static final String ICON_NAME_BLOCK = "icon_block_125px.png";
	public static final String ICON_NAME_SUCCESS = "icon_success_125px.png";
	public static final String ICON_NAME_QUESTION = "icon_question_125px.png";
	
	private static MessageJDialog messageJDialog;
	private static ConfirmJDialog confirmJDialog;
	private static AlertJDialog alertJDialog;
	
	
	//				SHOW MESSAGE JDIALOG 				//
	public static void showMessageDialog(Component component, String message)
	{
		messageJDialog = new MessageJDialog(component, message);
		messageJDialog.setVisible(true);
	}
	
	public static void showMessageDialog(Component component, String message, String iconName)
	{
		messageJDialog = new MessageJDialog(component, message, iconName);
		messageJDialog.setVisible(true);
	}
	
	public static void showMessageDialog(Component component, String message, String iconName, String title)
	{
		messageJDialog = new MessageJDialog(component, message, iconName, title);
		messageJDialog.setVisible(true);
	}
	
	
	
	//				SHOW ALERT JDIALOG 					//
	public static void showAlertDialog(Component component, String message)
	{
		alertJDialog = new AlertJDialog(component, message);
		alertJDialog.setVisible(true);
	}
	
	public static void showAlertDialog(Component component, String message, String iconName)
	{
		alertJDialog = new AlertJDialog(component, message, iconName);
		alertJDialog.setVisible(true);
	}
	
	public static void showAlertDialog(Component component, String message, String iconName, int fontSize)
	{
		alertJDialog = new AlertJDialog(component, message, iconName, fontSize);
		alertJDialog.setVisible(true);
	}
	
	//				SHOW CONFIRM JDIALOG				//
	public static boolean showConfirmDialog(Component component, String message)
	{
		confirmJDialog = new ConfirmJDialog(component, message);
		confirmJDialog.setVisible(true);
		return confirmJDialog.isConfirm();
	}
	
	public static boolean showConfirmDialog(Component component, String message, String iconName)
	{
		confirmJDialog = new ConfirmJDialog(component, message, iconName);
		confirmJDialog.setVisible(true);
		return confirmJDialog.isConfirm();
	}
	
	public static boolean showConfirmDialog(Component component, String message, String iconName, int fontSize)
	{
		confirmJDialog = new ConfirmJDialog(component, message, iconName, fontSize);
		confirmJDialog.setVisible(true);
		return confirmJDialog.isConfirm();
	}
	
	
	
}
