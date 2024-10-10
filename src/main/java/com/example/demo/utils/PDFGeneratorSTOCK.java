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

import com.example.demo.Stock.Model.StockReport;
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

public class PDFGeneratorSTOCK {
	// List to hold all Students
//		private List<Student> studentList;

	public String generate(String date, String type, List<StockReport> data_stock_,
			List<StockReport> data_stock_mms, Double total_stock_, Double total_stock_mms)
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
				+ date.substring(0, 7) + "/" + "STATIONARY STOCK REPORT As of " + monthName + " " + date2.getDate()
				+ ", " + date2.toString().split(" ")[5] + ".pdf";
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

		Paragraph pt = null;

		if (type.equalsIgnoreCase("121_stationary_pdf")) {
			pt = new Paragraph("RECONCILIATION OF STATIONARY STOCK \n As of " + monthName + " " + date2.getDate() + ", "
					+ date2.toString().split(" ")[5], fo1);
		} else if (type.equalsIgnoreCase("111_tools_pdf")) {
			pt = new Paragraph("RECONCILIATION OF TOOLS STOCK \n As of " + monthName + " " + date2.getDate() + ", "
					+ date2.toString().split(" ")[5], fo1);
		} else if (type.equalsIgnoreCase("113_spares_pdf")) {
			pt = new Paragraph("RECONCILIATION OF SPARES STOCK \n As of " + monthName + " " + date2.getDate() + ", "
					+ date2.toString().split(" ")[5], fo1);
		} else if (type.equalsIgnoreCase("105_uniform_pdf")) {
			pt = new Paragraph("RECONCILIATION OF UNIFORM STOCK \n As of " + monthName + " " + date2.getDate() + ", "
					+ date2.toString().split(" ")[5], fo1);
		} else if (type.equalsIgnoreCase("119_accessory_pdf")) {
			pt = new Paragraph("RECONCILIATION OF ACCESSORY STOCK \n As of " + monthName + " " + date2.getDate() + ", "
					+ date2.toString().split(" ")[5], fo1);
		} else if (type.equalsIgnoreCase("120_check_pdf")) {
			pt = new Paragraph("RECONCILIATION OF CHECK STOCK \n As of " + monthName + " " + date2.getDate() + ", "
					+ date2.toString().split(" ")[5], fo1);
		} else if (type.equalsIgnoreCase("112_sanitory_pdf")) {
			pt = new Paragraph("RECONCILIATION OF SANITORY STOCK \n As of " + monthName + " " + date2.getDate() + ", "
					+ date2.toString().split(" ")[5], fo1);
		} else if (type.equalsIgnoreCase("106_computer_pdf")) {
			pt = new Paragraph("RECONCILIATION OF COMPUTER STOCK \n As of " + monthName + " " + date2.getDate() + ", "
					+ date2.toString().split(" ")[5], fo1);
		} else if (type.equalsIgnoreCase("107_furniture_pdf")) {
			pt = new Paragraph("RECONCILIATION OF FURNITURE STOCK \n As of " + monthName + " " + date2.getDate() + ", "
					+ date2.toString().split(" ")[5], fo1);
		} else if (type.equalsIgnoreCase("104_office_equipment_pdf")) {
			pt = new Paragraph("RECONCILIATION OF OFFICE EQUIPMENT STOCK \n As of " + monthName + " " + date2.getDate() + ", "
					+ date2.toString().split(" ")[5], fo1);
		}

		cellt2.setPhrase(pt);
		tablet.addCell(cellt2);

		String signature = " RECONCCILIATION SYSTEM " + "STOCK report " + monthName + " " + date2.getDate()
				+ ", " + date2.toString().split(" ")[5];
		PdfPCell celly = new PdfPCell();
		celly.setFixedHeight(60.5f);
		celly.setPaddingLeft(50f);
		celly.setPaddingTop(0f);
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
		document.add(tablet);

		PdfPTable table = new PdfPTable(6);

		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 18, 25, 25, 18, 18, 18 });
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
		cell.setPhrase(new Phrase("Description", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("GRV & GIV", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Debit", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Credit", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Balance", fo));
		table.addCell(cell);

		PdfPCell cellk = new PdfPCell();
		cellk.setPaddingLeft(1f);
		cellk.setPaddingRight(1f);
		cellk.setVerticalAlignment(Element.ALIGN_CENTER);
		cellk.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellk.setNoWrap(false);
		cellk.setFixedHeight(10.5f);

		cellk.setPhrase(new Phrase(
				"BFUB BALANCE As of " + monthName + " " + date2.getDate() + ", " + date2.toString().split(" ")[5], fo));
		cellk.setColspan(5);
		table.addCell(cellk);
		cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(total_stock_).replace("$", ""), fo));
		table.addCell(cell2);

		double total__credit = 0d;
		double total__debit = 0d;
		for (StockReport data_ : data_stock_) {

			cell2.setPhrase(new Phrase(data_.getPosting_date(), fo2));
			table.addCell(cell2);
			cell2.setPhrase(new Phrase("-", fo2));
			table.addCell(cell2);
			cell.setPhrase(new Phrase(data_.getReference(), fo2));
			table.addCell(cell);
			if (data_.getDr_cr().equalsIgnoreCase("dr")) {
				total__debit = total__debit + data_.getAmount();
				cell2.setPhrase(new Phrase("" + data_.getAmount(), fo2));
				table.addCell(cell2);
				cell2.setPhrase(new Phrase("0.00", fo2));
				table.addCell(cell2);
			} else {
				total__credit = total__credit + data_.getAmount();
				cell2.setPhrase(new Phrase("0.00", fo2));
				table.addCell(cell2);
				cell2.setPhrase(new Phrase("" + data_.getAmount(), fo2));
				table.addCell(cell2);
			}
			cell.setPhrase(new Phrase(
					NumberFormat.getCurrencyInstance().format(data_.getBalance()).replace("$", ""), fo2));
			table.addCell(cell);
		}

		cell.setPhrase(new Phrase("Total Debit and Credit Balance", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(total__debit).replace("$", ""), fo));
		table.addCell(cell2);
		cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(total__credit).replace("$", ""), fo));
		table.addCell(cell2);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Adjusted Balance ", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance()
				.format(total_stock_ - total__debit + total__credit).replace("$", ""), fo));
		table.addCell(cell2);

		double total_mms_credit = 0d;
		double total_mms_debit = 0d;
		for (StockReport data_mms : data_stock_mms) {

			cell2.setPhrase(new Phrase(data_mms.getDate().substring(0, 10), fo2));
			table.addCell(cell2);
			cell.setPhrase(new Phrase(data_mms.getDescription(), fo2));
			table.addCell(cell);
			cell.setPhrase(new Phrase(data_mms.getReference(), fo2));
			table.addCell(cell);
			if (data_mms.getDr_cr().equalsIgnoreCase("dr")) {
				total_mms_debit = total_mms_debit + data_mms.getAmount();
				cell2.setPhrase(new Phrase("" + data_mms.getAmount(), fo2));
				table.addCell(cell2);
				cell2.setPhrase(new Phrase("0.00", fo2));
				table.addCell(cell2);
			} else {
				total_mms_credit = total_mms_credit + data_mms.getAmount();
				cell2.setPhrase(new Phrase("0.00", fo2));
				table.addCell(cell2);
				cell2.setPhrase(new Phrase("" + data_mms.getAmount(), fo2));
				table.addCell(cell2);
			}
			cell.setPhrase(new Phrase("-", fo2));
			table.addCell(cell);
		}

		cell.setPhrase(new Phrase("Total Debit and Credit Balance", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(total_mms_debit).replace("$", ""), fo));
		table.addCell(cell2);
		cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(total_mms_credit).replace("$", ""), fo));
		table.addCell(cell2);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);

		cell.setPhrase(new Phrase("ADJUSTED BALANCE ", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance()
				.format((total_stock_ - total__debit + total__credit) + total_mms_debit - total_mms_credit)
				.replace("$", ""), fo));
		table.addCell(cell2);

		cell.setPhrase(new Phrase("MMS LEDGER BALANCE ", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fo));
		table.addCell(cell);
		cell2.setPhrase(new Phrase(NumberFormat.getCurrencyInstance().format(total_stock_mms).replace("$", ""), fo));
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
		cell2.setPhrase(new Phrase(
				NumberFormat.getCurrencyInstance().format((total_stock_ - total__debit + total__credit)
						+ total_mms_debit - total_mms_credit - total_stock_mms).replace("$", ""),
				fo));
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
