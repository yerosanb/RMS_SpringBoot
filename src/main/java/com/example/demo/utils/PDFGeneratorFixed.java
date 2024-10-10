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

import com.example.demo.abebayehu.entity.Fixed__report;
import com.example.demo.abebayehu.entity.Fixed_mms_report;
import com.example.demo.abebayehu.entity.Raw_fixed_;
import com.example.demo.abebayehu.entity.Raw_fixed_mms;
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

public class PDFGeneratorFixed {
	public String generateFixedPDF(String date, String type, List<Fixed__report> _debit_data,
			List<Fixed__report> _credit_data, Double total_, List<Fixed_mms_report> mms_data,
			 Double total_mms, Double conv, Double ifb, Double waiting, Double disposed, Double removed, Double mms_balance)
			throws DocumentException, IOException, ParseException, WriterException {

//		DecimalFormat df = new DecimalFormat("#.##");

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
		
		if(type.equalsIgnoreCase("fur_fixed_pdf")) { 
		Paragraph pt = new Paragraph("RECONCILIATION OF FIXED ASSET OF FURNITURE \n As At " + monthName + " "
				+ date2.getDate() + ", " + date2.toString().split(" ")[5], fo1);
		
		cellt2.setPhrase(pt);
		tablet.addCell(cellt2);
		}
		
		else if(type.equalsIgnoreCase("comp_fixed_pdf")) {
			Paragraph pt = new Paragraph("RECONCILIATION OF FIXED ASSET OF COMPUTER \n As At " + monthName + " "
					+ date2.getDate() + ", " + date2.toString().split(" ")[5], fo1);
			
			cellt2.setPhrase(pt);
			tablet.addCell(cellt2);
		}
		
		else if(type.equalsIgnoreCase("vehi_fixed_pdf")) {
			Paragraph pt = new Paragraph("RECONCILIATION OF FIXED ASSET OF Motor-Vehicle \n As At " + monthName + " "
					+ date2.getDate() + ", " + date2.toString().split(" ")[5], fo1);
			
			cellt2.setPhrase(pt);
			tablet.addCell(cellt2);
		}
		
		else if(type.equalsIgnoreCase("equp_fixed_pdf")) {
			Paragraph pt = new Paragraph("RECONCILIATION OF FIXED ASSET OF Office-Equipment \n As At " + monthName + " "
					+ date2.getDate() + ", " + date2.toString().split(" ")[5], fo1);
			
			cellt2.setPhrase(pt);
			tablet.addCell(cellt2);
		}
		
		String signature = " RECONCCILIATION SYSTEM " + "Fixed Asset Report " + monthName + " "
				+ date2.getDate() + ", " + date2.toString().split(" ")[5];
		PdfPCell celly = new PdfPCell();
		celly.setFixedHeight(60.5f);
		celly.setPaddingLeft(50f);
		celly.setPaddingTop(0f);
//        celly.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//        celly.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        celly.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
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

//        tablet.set

//		cellt.addElement(image2);
//		cellt.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
//		tablet.addCell(cellt);
//        tablet.setBorder(0);
		document.add(tablet);

//		Paragraph paragraphj = new Paragraph("RECONCILIATION OF PAPYMENT & SETTLEMENT ACCOUNT", fo);
//		Paragraph paragraph = new Paragraph("As of " + monthName + " " + date2.getDate() + ", "
//		+ date2.toString().split(" ")[5], fo);

//		paragraphj.setAlignment(Paragraph.ALIGN_CENTER);
//		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
//
//		document.add(paragraphj);
//		document.add(paragraph);

		PdfPTable table = new PdfPTable(6);

		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 15, 27, 25, 30, 17, 22 });
		table.setSpacingBefore(8);

		PdfPCell cell = new PdfPCell();
		PdfPCell cell2 = new PdfPCell();

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

		cell.setPhrase(new Phrase("Date", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Asset Description", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("GIV/GRV", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Department/Branch", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Orginal Cost", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Balance", fo));
		table.addCell(cell);

		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase(
				"Balance as per BFUB  At" + monthName + " " + date2.getDate() + ", " + date2.toString().split(" ")[5],
				fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(conv).replace("$", ""), fo));
		table.addCell(cell2);

		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		if(type.equalsIgnoreCase("fur_fixed_pdf"))
			cell.setPhrase(new Phrase("Add: IFB 01A95 Account", fo));
		else if(type.equalsIgnoreCase("comp_fixed_pdf"))
			cell.setPhrase(new Phrase("Add: IFB 01A97 Account", fo));
		else if(type.equalsIgnoreCase("vehi_fixed_pdf"))
			cell.setPhrase(new Phrase("", fo));
		else if(type.equalsIgnoreCase("equp_fixed_pdf"))
			cell.setPhrase(new Phrase("Add: IFB 01A96 Account", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(ifb).replace("$", ""), fo));
		table.addCell(cell2);

		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Total Con. and IFB", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(conv + ifb).replace("$", ""), fo));
		table.addCell(cell2);

		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("List of  Autstanding", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		for (Fixed__report data_ : _debit_data) {
			cell2.setPhrase(new Phrase(data_.getTransaction_date(), fo2));
			table.addCell(cell2);
			cell.setPhrase(new Phrase(data_.getAccount_description(), fo2));
			table.addCell(cell);
			cell.setPhrase(new Phrase(data_.getNaration(), fo2));
			table.addCell(cell);
			cell.setPhrase(new Phrase("" + data_.getAccount_name(), fo2));
			table.addCell(cell);
			cell2.setPhrase(new Phrase("" + data_.getAmount(), fo2));
			table.addCell(cell2);
			cell.setPhrase(new Phrase("", fo2));
			table.addCell(cell);
		}

		for (Fixed__report data_ : _credit_data) {
			cell2.setPhrase(new Phrase(data_.getTransaction_date(), fo2));
			table.addCell(cell2);
			cell.setPhrase(new Phrase(data_.getAccount_description(), fo2));
			table.addCell(cell);
			cell.setPhrase(new Phrase(data_.getNaration(), fo2));
			table.addCell(cell);
			cell.setPhrase(new Phrase("" + data_.getAccount_name(), fo2));
			table.addCell(cell);
			cell2.setPhrase(new Phrase("" + data_.getAmount(), fo2));
			table.addCell(cell2);
			cell.setPhrase(new Phrase("", fo2));
			table.addCell(cell);
		}

		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell2.setPhrase(new Phrase("", fo));
		table.addCell(cell2);
		cell2.setPhrase(new Phrase("", fo));
		table.addCell(cell2);
		cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(total_).replace("$", ""), fo));
		table.addCell(cell2);

		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Minus MMS Outstanding", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);

		for (Fixed_mms_report data_ : mms_data) {
			cell2.setPhrase(new Phrase(data_.getCreated_date(), fo2));
			table.addCell(cell2);
			cell.setPhrase(new Phrase(data_.getAsset_description(), fo2));
			table.addCell(cell);
			cell.setPhrase(new Phrase(data_.getTag_number(), fo2));
			table.addCell(cell);
			cell.setPhrase(new Phrase("" + data_.getBranch_name(), fo2));
			table.addCell(cell);
			cell2.setPhrase(new Phrase("" + data_.getAmount(), fo2));
			table.addCell(cell2);
			cell.setPhrase(new Phrase("", fo2));
			table.addCell(cell);
		}

//		for (Fixed_mms_report data_ : mms_debit_data) {
//			cell2.setPhrase(new Phrase(data_.getTransaction_date(), fo2));
//			table.addCell(cell2);
//			cell.setPhrase(new Phrase(data_.getCategory_description(), fo2));
//			table.addCell(cell);
//			cell.setPhrase(new Phrase(data_.getReference(), fo2));
//			table.addCell(cell);
//			cell.setPhrase(new Phrase("" + data_.getAdditional_information(), fo2));
//			table.addCell(cell);
//			cell2.setPhrase(new Phrase("" + data_.getAmount(), fo2));
//			table.addCell(cell2);
//			cell.setPhrase(new Phrase("", fo2));
//			table.addCell(cell);
//		}

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
		cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(total_mms).replace("$", ""), fo));
		table.addCell(cell2);

		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Adjusted BFUB Balance", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format((conv + ifb + total_mms)-total_).replace("$", ""), fo));
		table.addCell(cell2);

		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase(
				"Balance as per MMS as at" + monthName + " " + date2.getDate() + ", " + date2.toString().split(" ")[5],
				fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(mms_balance).replace("$", ""), fo));
		table.addCell(cell2);

		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase(" ADD: on waiting list", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(waiting).replace("$", ""), fo));
		table.addCell(cell2);
		
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase(" ADD: on disposed list", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(disposed).replace("$", ""), fo));
		table.addCell(cell2);
		
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase(" ADD: on removed list", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(removed).replace("$", ""), fo));
		table.addCell(cell2);

		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Adjusted MMS Balance", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(mms_balance+waiting+disposed+removed).replace("$", ""), fo));
		table.addCell(cell2);

		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Existing Diff Between MMS and BFUB", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(((conv+ifb+total_mms)-total_)-(mms_balance+waiting+disposed+removed)).replace("$", ""), fo));
		table.addCell(cell2);

		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
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
