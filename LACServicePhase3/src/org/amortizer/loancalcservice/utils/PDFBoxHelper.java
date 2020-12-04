package org.amortizer.loancalcservice.utils;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


public class PDFBoxHelper {

	private final short MAX_LINES_PER_PAGE=60;
	private PDDocument document;;
	private PDPage page;
	private PDPageContentStream content;
    private int lineCntr;	
	
	PDFBoxHelper() {
		
		document = new PDDocument();
		content = null;
		lineCntr=1;
		
	}
	
	// simple PDF generator
	public void generatePdf(File file, String doc) throws IOException  {

		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		 
		contentStream.setFont(PDType1Font.COURIER, 12);
		contentStream.beginText();
		contentStream.showText(doc);
		contentStream.endText();
		contentStream.close();
		 
		document.save(file.toString());
		document.close();
	}
	
	public void addLineToPDF(String line) throws IOException {
		chkForNewPage();
		content.showText(line);
		content.newLine();
		lineCntr++;
	}
	
	public void chkForNewPage() throws IOException {
		if (lineCntr==1 || lineCntr > MAX_LINES_PER_PAGE) {
			if (lineCntr>1) lineCntr=1;
			page = new PDPage();
			document.addPage(page);
			if (content!=null) content.close();
			content = new PDPageContentStream(document, page);
			content.setFont(PDType1Font.COURIER, 12);
			content.beginText();
		}
	}
	
	public void endPDFDoc(String docName) throws IOException {
	
		content.endText();
		content.close();
		document.save(docName);
		document.close();

	}
	
	public static void main(String[] args) throws IOException, java.io.IOException {

		int idx = new Random().nextInt();
				
		final String docName=String.format("c:\\tmp\\LoanSchedule-%d.pdf",idx);
		PDFBoxHelper pdfHelper = new PDFBoxHelper();
		
		StringBuilder amortSchedule = new StringBuilder();
//		amortSchedule.append("Month       Payment       Principal       Interest        Balance");
		pdfHelper.addLineToPDF("Month       Payment       Principal       Interest        Balance");
		for (int i = 0; i < 20; i++) {
			amortSchedule.append(String.format("%5d", i));
			amortSchedule.append(String.format("%14.2f", 1005.00));
			amortSchedule.append(String.format("%16.2f", 45345.00));
			amortSchedule.append(String.format("%15.2f", 750.00));
			amortSchedule.append(String.format("%15.2f", 1234567890.00));

			pdfHelper.addLineToPDF(amortSchedule.toString());
			
			amortSchedule.setLength(0);
		}
		
		pdfHelper.endPDFDoc(docName);
		
		System.out.println(amortSchedule.toString());


		

		
	}
	

}
