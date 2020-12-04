package org.amortizer.loancalcservice.utils;

import java.io.File;
import java.io.FileNotFoundException;

import com.itextpdf.io.IOException;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class ItextPDFHelper {

	private File destFile;
	private String docSrc;
	

	public ItextPDFHelper() {}
	
	public ItextPDFHelper(File file, String doc) {
		
		this.destFile = file;
		this.docSrc = doc;
		
	}

	//simple PDF generator
	public void generatePdf() throws IOException, FileNotFoundException{

		//Initialize PDF writer
		PdfWriter writer = new PdfWriter(this.destFile);

		//Initialize PDF document
		PdfDocument pdf = new PdfDocument(writer);

		// Initialize document
		Document document = new Document(pdf);

		//Add paragraph to the document
		document.add(new Paragraph(this.docSrc));

		//Close document
		document.close();
	}

	
	//simple PDF generator
	public void generatePdf(File file, String doc) throws IOException, java.io.IOException{
		
		//set font
		PdfFont font = PdfFontFactory.createFont(FontConstants.COURIER_BOLD, "CP1251", true);
		
		//Initialize PDF writer
		PdfWriter writer = new PdfWriter(file);

		//Initialize PDF document
		PdfDocument pdf = new PdfDocument(writer);

		// Initialize document
		Document document = new Document(pdf);
		
		// set font
		document.setFont(font);

		//Add paragraph to the document
		document.add(new Paragraph(doc));
		
		//Close document
		document.close();
	}

	
	public static void main(String[] args) throws IOException, java.io.IOException {

		System.out.println(String.format("|%20d|", 93));
		System.out.println(String.format("|%-20d|", 93));
		
		StringBuilder amortSchedule = new StringBuilder();
		amortSchedule.append("Month       Payment       Principal       Interest        Balance");
		amortSchedule.append("\n");
		for (int i = 0; i < 20; i++) {
			amortSchedule.append(String.format("%5d", i));
			amortSchedule.append(String.format("%14.2f", 1005.00));
			amortSchedule.append(String.format("%16.2f", 45345.00));
			amortSchedule.append(String.format("%15.2f", 750.00));
			amortSchedule.append(String.format("%15.2f", 1234567890.00));
			amortSchedule.append("\n");
		}
		
		System.out.println(amortSchedule.toString());

		 new ItextPDFHelper().generatePdf(
		 new File("C:\\tmp\\Schedule.pdf"), amortSchedule.toString());
//		 new AmortizationSchedule().createAmortizationSchedule(150000,1750,6.5,360,0));

	}

}
