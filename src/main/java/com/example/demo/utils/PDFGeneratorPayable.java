package com.example.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.IOUtils;

import com.example.demo.user.model.File_rtgs__;
import com.example.demo.user.model.File_rtgs__ats;
import com.google.zxing.WriterException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PDFGeneratorPayable {
	// List to hold all Students
//	private List<Student> studentList;

public String generate(String date, List<File_rtgs__> data_payable_debit, Double payable_ending_balance,
		Double total_payable_debit, List<File_rtgs__> data_payable_credit, Double total_payable_credit)
		throws DocumentException, IOException, ParseException, WriterException {

//	DecimalFormat df = new DecimalFormat("#.##");

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	Date date2 = formatter.parse(date);
	Calendar cal = Calendar.getInstance();
	cal.setTime(date2);
	String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());
	
	final String DIRECTORY_logo = System.getProperty("user.dir")
			+ "/src/main/resources/static//_002.png";

	final String DIRECTORY2 = System.getProperty("user.dir") + "/src/main/resources/static/generated_reports/" 
			+ date.substring(0, 7) + "/";

	final String DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/generated_reports/" 
			+ date.substring(0, 7) + "/" + "REPORT As of " + monthName + " " + date2.getDate() + ", "
			+ date2.toString().split(" ")[5] + ".pdf";
	File file_path = new File(StringUtils.join(DIRECTORY2));
	if (!file_path.exists()) {
		file_path.mkdirs();
	}

	FileOutputStream pdfOutputFile = new FileOutputStream(DIRECTORY);

	Document document = new Document(PageSize.A4);
	PdfWriter.getInstance(document, pdfOutputFile);
	document.open();

	Font fo = new Font(Font.HELVETICA, 6.5f, Font.BOLD);
	Font fo7 = new Font(Font.HELVETICA, 7.5f, Font.BOLD);
	Font fo1 = new Font(Font.HELVETICA, 10f, Font.BOLD);
	Font fo2 = new Font(Font.HELVETICA, 6.5f, Font.NORMAL);
    
	PdfPTable tablet = new PdfPTable(3);
	tablet.setWidthPercentage(100f);
	tablet.setWidths(new int[] { 20, 56, 20 });
	tablet.setSpacingBefore(8);
	
	
	PdfPCell cellt1 = new PdfPCell();
	cellt1.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
	cellt1.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
	cellt1.setFixedHeight(60.5f);
    Image image = Image.getInstance(IOUtils.toByteArray(new FileInputStream(DIRECTORY_logo)));
    image.scaleToFit(100, 100);
	cellt1.addElement(image);
	cellt1.setBorder(0);
	tablet.addCell(cellt1);
	
	PdfPCell cellt2 = new PdfPCell();
	cellt2.setPaddingLeft(1f);
	cellt2.setPaddingRight(1f);
	cellt2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
	cellt2.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
	cellt2.setNoWrap(false);
	cellt2.setFixedHeight(60.5f);
	cellt2.setPaddingTop(18f);
	cellt2.setBorder(0);
	Paragraph pt = new Paragraph("Detail of P&Settlement Payable as of\n As of " + monthName + " " + date2.getDate() + ", "
			+ date2.toString().split(" ")[5], fo1);
	cellt2.setPhrase(pt);
	tablet.addCell(cellt2);
	
	String signature = " RECONCCILIATION SYSTEM " + "Payable report " + monthName + " " + date2.getDate() + ", " + date2.toString().split(" ")[5];
    PdfPCell celly = new PdfPCell();
    celly.setFixedHeight(60.5f);
    celly.setPaddingLeft(50f);
    celly.setPaddingTop(0f);
//    celly.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//    celly.setHorizontalAlignment(Element.ALIGN_RIGHT);
//    celly.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
    celly.addElement(Image.getInstance(QRCodeGenerator.getQRCodeImage(signature, 100, 100)));
    
    celly.setBorder(0);
    tablet.addCell(celly);
    
    
	PdfPCell cell12 = new PdfPCell();
	cell12.setPhrase(new Phrase("", fo));
	cell12.setBorder(0);
	tablet.addCell(cell12);
	PdfPCell cell13 = new PdfPCell();
	cell13.setPhrase(new Phrase("", fo));
	cell13.setBorder(0);
	tablet.addCell(cell13);
	PdfPCell cell14 = new PdfPCell();
	cell14.setPhrase(new Phrase("AWAH  R.M.S", fo7));
	cell14.setBorder(0);
	tablet.addCell(cell14);
    
    
    
//    tablet.set
    
//	cellt.addElement(image2);
//	cellt.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
//	tablet.addCell(cellt);
//    tablet.setBorder(0);
	document.add(tablet);
	

//	Paragraph paragraphj = new Paragraph("RECONCILIATION OF PAPYMENT & SETTLEMENT ACCOUNT", fo);
//	Paragraph paragraph = new Paragraph("As of " + monthName + " " + date2.getDate() + ", "
//	+ date2.toString().split(" ")[5], fo);

//	paragraphj.setAlignment(Paragraph.ALIGN_CENTER);
//	paragraph.setAlignment(Paragraph.ALIGN_CENTER);
//
//	document.add(paragraphj);
//	document.add(paragraph);
	


	PdfPTable table = new PdfPTable(4);

	table.setWidthPercentage(100f);
	table.setWidths(new int[] { 5, 15, 26, 20});
	table.setSpacingBefore(8);

	PdfPCell cell = new PdfPCell();
	PdfPCell cell2 = new PdfPCell();
	PdfPCell cell3 = new PdfPCell();

	cell.setPaddingLeft(1f);
	cell.setPaddingRight(1f);
	cell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
	cell.setNoWrap(false);
	cell.setFixedHeight(10.5f);
	cell2.setPaddingLeft(1f);
	cell2.setPaddingRight(1f);
	cell2.setVerticalAlignment(Element.ALIGN_CENTER);
	cell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	cell2.setNoWrap(false);
	cell2.setFixedHeight(10.5f);
	
	cell3.setPaddingLeft(1f);
	cell3.setPaddingRight(1f);
	cell3.setVerticalAlignment(Element.ALIGN_CENTER);
	cell3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
	cell3.setNoWrap(false);
	cell3.setFixedHeight(10.5f);

	cell.setPhrase(new Phrase("NO.", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Date", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Description", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Amount", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("", fo));
	
	table.addCell(cell);
	cell.setPhrase(new Phrase("Credit Entries in Payable", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
int credit_counter=1;
	for (File_rtgs__ data_ : data_payable_credit) {
		cell3.setPhrase(new Phrase("" + credit_counter, fo2));
		table.addCell(cell3);
		cell2.setPhrase(new Phrase(data_.getValue_date(), fo2));
		table.addCell(cell2);
		cell.setPhrase(new Phrase(data_.getAdditional_information(), fo2));
		table.addCell(cell);
		cell2.setPhrase(new Phrase("" + data_.getAmount(), fo2));
		table.addCell(cell2);
		credit_counter++;
	}


	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Credit Entries Total", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(total_payable_credit).replace("$", ""), fo));
	table.addCell(cell2);
	
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Debit Entries in Payable", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
int i_thec = 1;
	for (File_rtgs__ data_payable : data_payable_debit) {
		
		cell3.setPhrase(new Phrase("" + i_thec, fo2));
		table.addCell(cell3);
		cell2.setPhrase(new Phrase(data_payable.getValue_date(), fo2));
		table.addCell(cell2);
		cell.setPhrase(new Phrase(data_payable.getAdditional_information(), fo2));
		table.addCell(cell);
		cell2.setPhrase(new Phrase("" + data_payable.getAmount(), fo2));
		table.addCell(cell2);
		i_thec++;
	}


	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Debit Entries Total ", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(total_payable_debit).replace("$", ""), fo));
	table.addCell(cell2);

	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);


	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Balance (Credit-Debit)", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance()
			.format(total_payable_credit-total_payable_debit).replace("$", ""), fo));
	table.addCell(cell2);

	
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Balance " + date2.getDate() + ", "+ date2.toString().split(" ")[5], fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
//	payable_ending_balance = 123123123d;
	cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(payable_ending_balance).replace("$", ""), fo));
	table.addCell(cell2);
	
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Diff", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance()
			.format((total_payable_credit-total_payable_debit)-payable_ending_balance).replace("$", ""), fo));
	table.addCell(cell2);

	
	
//	System.out.println("ats_ending_balance: " + ats_ending_balance);
//	System.out.println("total__debit: " + total__debit);
//	System.out.println("total__credit: " + total__credit);
//	System.out.println("adjustment at : " + ((ats_ending_balance + total__debit) - total__credit));

//	cell.setPhrase(new Phrase("Balance as per CON Statement", fo));
//	table.addCell(cell);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//	cell2.setPhrase(new Phrase(
//			NumberFormat.getCurrencyInstance().format(conventional_ending_balance).replace("$", ""), fo));
//	table.addCell(cell2);
//
//	cell.setPhrase(new Phrase("Balance as per IFB Statement", fo));
//	table.addCell(cell);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//	cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(ifb_ending_balance).replace("$", ""), fo));
//	table.addCell(cell2);
//
//	cell.setPhrase(new Phrase("Balance as per Book Statement", fo));
//	table.addCell(cell);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//	cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance()
//			.format(conventional_ending_balance + ifb_ending_balance).replace("$", ""), fo));
//	table.addCell(cell2);
//
//	for (File_rtgs__ats data_ats : ats_credit_data) {
//		// System.out.println("sender and receiver: " + data_ats.getSender() + " " + data_ats.getReceiver());
//		cell2.setPhrase(new Phrase(data_ats.getValue_date_type(), fo2));
//		table.addCell(cell2);
//		cell.setPhrase(new Phrase(data_ats.getReference(), fo2));
//		table.addCell(cell);
//		cell.setPhrase(new Phrase(data_ats.getSender() + ":" + data_ats.getReceiver(), fo2));
//		table.addCell(cell);
//		cell2.setPhrase(new Phrase("" + data_ats.getAmount(), fo2));
//		table.addCell(cell2);
//		cell.setPhrase(new Phrase("", fo2));
//		table.addCell(cell);
//	}
//
//	cell.setPhrase(new Phrase("Total credit Balance", fo));
//	table.addCell(cell);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//	cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(total_ats_credit).replace("$", ""), fo));
//	table.addCell(cell2);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//
//	cell.setPhrase(new Phrase("Less: Debit Entries in ", fo));
//	table.addCell(cell);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//	cell2.setPhrase(new Phrase(
//			NumberFormat.getCurrencyInstance()
//					.format(conventional_ending_balance + ifb_ending_balance + total_ats_credit).replace("$", ""),
//			fo));
//	table.addCell(cell2);
//
//	for (File_rtgs__ats data_ats : ats_debit_data) {
//		cell2.setPhrase(new Phrase(data_ats.getValue_date_type(), fo2));
//		table.addCell(cell2);
//		cell.setPhrase(new Phrase(data_ats.getReference(), fo2));
//		table.addCell(cell);
//		cell.setPhrase(new Phrase(data_ats.getSender() + ":" + data_ats.getReceiver(), fo2));
//		table.addCell(cell);
//		cell2.setPhrase(new Phrase("" + data_ats.getAmount(), fo2));
//		table.addCell(cell2);
//		cell.setPhrase(new Phrase("", fo2));
//		table.addCell(cell);
//	}
//
//	cell.setPhrase(new Phrase("Total Debit on ", fo));
//	table.addCell(cell);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//	cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(total_ats_debit).replace("$", ""), fo));
//	table.addCell(cell2);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//
//	cell.setPhrase(new Phrase("Total Adjustment Balance of Book", fo));
//	table.addCell(cell);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//	cell.setPhrase(new Phrase("", fo));
//	table.addCell(cell);
//	cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance()
//			.format((conventional_ending_balance + ifb_ending_balance + total_ats_credit) - total_ats_debit)
//			.replace("$", ""), fo));
//	table.addCell(cell2);

	table.addCell(cell);
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("", fo));
	table.addCell(cell);
	cell.setPhrase(new Phrase("", fo));

	document.add(table);

	Font fo3 = new Font(Font.HELVETICA, 10f, Font.NORMAL);
	Paragraph paragraph2 = new Paragraph(
			"Prepared By ________    Checked By _________    Follow Up By _________    Approved By _________", fo3);
	Paragraph paragraph21 = new Paragraph(" ", fo3);
	paragraph2.setAlignment(Paragraph.ALIGN_CENTER);
	document.add(paragraph21);
	document.add(paragraph21);
	document.add(paragraph21);
	document.add(paragraph2);

	document.close();

	return DIRECTORY;
}

}
