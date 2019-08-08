package com.duan.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class TestXuatFilePDF 
{
	public static void main(String[] args) throws DocumentException, IOException 
	{
		BaseFont baseFont = BaseFont.createFont("c:\\WINDOWS\\fonts\\times.ttf", BaseFont.IDENTITY_H, true);
		Font font_utf8 = new Font(baseFont);
		//Tạo 1 document
		Document document = new Document();
		
		//Tạo 1 PdfWriter truyền document vào
		PdfWriter.getInstance(document, new FileOutputStream("C:\\temp\\test.pdf"));
		
		//Mở file document để thực hiện viết
		document.open();
		document.addTitle("Đây là title add title thử nghiệm");
		document.add(new Paragraph("H\u2082SO\u2074"));
		
		//Khai báo 2 paragraph
		Paragraph paragraph1 = new Paragraph("This is Paragraph 1 thử nghiệm");
		Paragraph paragraph2 = new Paragraph("This is Paragraph 2 thử nghiệm", font_utf8);
		
		//Thêm 2 đoạn văn bản vào document
		document.add(paragraph1);
		document.add(paragraph2);
		
		PdfPTable table = new PdfPTable(3);
		table.setSpacingBefore(25);
		table.setSpacingAfter(25);
		table.getDefaultCell().setBorder(0);

		PdfPCell c1 = new PdfPCell(new Phrase("Header1"));
		c1.setBackgroundColor(BaseColor.GREEN);
		table.addCell(c1);
		
		PdfPCell c2 = new PdfPCell(new Phrase("Header2"));
		table.addCell(c2);
		
		PdfPCell c3 = new PdfPCell(new Phrase("Header3"));
		table.addCell(c3);
		
		table.addCell("1.1");
		table.addCell("1.2");
		table.addCell("1.4");
		

		document.add(table);
		
		document.close();
		
		Desktop.getDesktop().open(new File("C:\\temp\\test.pdf"));
			
	}
}
