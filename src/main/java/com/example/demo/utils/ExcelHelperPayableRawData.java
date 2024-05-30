package com.example.demo.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Component;

import com.example.demo.user.model.File_rtgs_awb_core;
import com.example.demo.user.model.File_rtgs_nbe_ats;
import com.google.zxing.WriterException;

@Component
public class ExcelHelperPayableRawData {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Posting Date", "Value Date", "Description", "Transaction Reference", "Branch Code",
			"Debit Amount", "Credit Amount", "Balance" };

	private static void insertQrCodeToCell(Workbook workbook, int rowNum, Drawing drawing, Sheet sheet, String date)
			throws IOException, WriterException, ParseException {

//		final String DIRECTORY_logo = System.getProperty("user.dir")
//				+ "/src/main/resources/static/awash_logo/awash_logo_002.png";
//		File file_path = new File(StringUtils.join(DIRECTORY_logo));
//		if (!file_path.exists()) {
//			file_path.mkdirs();
//		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date2 = formatter.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date2);
		String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());

		String signature = "AWASH BANK RECONCCILIATION SYSTEM " + "Payable Raw Data" + monthName + " " + date2.getDate()
				+ ", " + date2.toString().split(" ")[5];
		byte[] image = new byte[0];
		image = QRCodeGenerator.getQRCodeImage(signature, 300, 300);

		int inputImagePictureID = workbook.addPicture(image, Workbook.PICTURE_TYPE_PNG);
		ClientAnchor anchor = new HSSFClientAnchor();

		anchor.setCol1(7);
		anchor.setCol2(8);
		anchor.setRow1(rowNum - 1);
		anchor.setRow2(rowNum);
		anchor.setDx1(-50);
		anchor.setDy1(0);

		final Picture pict = drawing.createPicture(anchor, inputImagePictureID);
		pict.resize();
		pict.resize(0.25, 0.22);
	}

	private static void insertLogoToCell(Workbook workbook, int rowNum, Drawing drawing) throws IOException {

		final String DIRECTORY_logo = System.getProperty("user.dir")
				+ "/src/main/resources/static/awash_logo/awash_logo_002.png";
		File file_path = new File(StringUtils.join(DIRECTORY_logo));
		if (!file_path.exists()) {
			file_path.mkdirs();
		}

		InputStream is = new FileInputStream(DIRECTORY_logo);
		byte[] inputImageBytes = IOUtils.toByteArray(is);
		int inputImagePictureID = workbook.addPicture(inputImageBytes, Workbook.PICTURE_TYPE_PNG);
		is.close();
		ClientAnchor anchor = new HSSFClientAnchor();

		anchor.setCol1(0);
		anchor.setCol2(2);
		anchor.setRow1(rowNum - 1);
		anchor.setRow2(rowNum + 1);
		drawing.createPicture(anchor, inputImagePictureID);

	}

	public static ByteArrayInputStream tutorialsToExcelPayable(String date, List<File_rtgs_awb_core> Payable_raw_data_all,
			double beginning_balance) throws ParseException, WriterException {

		try (Workbook workbook = new HSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date date2 = formatter.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date2);
			String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());
			DecimalFormat df = new DecimalFormat("#.##");


			String SHEET = "Payable Raw Data " + monthName + " " + date2.getDate() + ", "
					+ date2.toString().split(" ")[5];
			Sheet sheet = workbook.createSheet(SHEET);

			CellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			CellStyle style2 = workbook.createCellStyle();
			style2.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			Font font = workbook.createFont();
			font.setFontHeightInPoints((short) 8);
			font.setBold(true);

			style.setFont(font);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);

			Font font2 = workbook.createFont();
			font2.setFontHeightInPoints((short) 8);

			style2.setFont(font2);
			style2.setBorderBottom(BorderStyle.THIN);
			style2.setBorderTop(BorderStyle.THIN);
			style2.setBorderRight(BorderStyle.THIN);
			style2.setBorderLeft(BorderStyle.THIN);

			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 5));

			// Header
			Row titleRow = sheet.createRow(0);
			titleRow.setHeight((short) (1200));

//			=============================================
			Drawing drawing = sheet.createDrawingPatriarch();

			insertLogoToCell(workbook, 1, drawing);
//			=============================================

			Cell titleCell = titleRow.createCell(2);
			CellStyle cs = titleCell.getCellStyle();
			cs.setWrapText(true);
			cs.setFont(font);
			cs.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			titleCell.setCellStyle(cs);
			CellUtil.setAlignment(titleCell, HorizontalAlignment.CENTER);
			CellUtil.setVerticalAlignment(titleCell, VerticalAlignment.CENTER);

			titleCell.setCellValue("RAW DATA OF PAYABLE ACCOUNT on " + monthName + " " + date2.getDate() + ", "
					+ date2.toString().split(" ")[5]);

			Row row_0 = sheet.createRow(1);
			Cell cellt = row_0.createCell(3);
			cellt.setCellStyle(cs);
			CellUtil.setAlignment(cellt, HorizontalAlignment.CENTER);
			CellUtil.setVerticalAlignment(cellt, VerticalAlignment.CENTER);
			cellt.setCellValue("AWASH BANK R.M.S");

//			Cell cell23 = titleRow.createCell(2);
//			cell23.setCellValue("hollaa");
//			cell23.setCellStyle(style);
//			CellUtil.setVerticalAlignment(cell23, VerticalAlignment.CENTER);
//			Cell cell_rms = titleRow.createCell(2);
//			CellStyle css = cell_rms.getCellStyle();
//			css.setWrapText(true);
//			css.setFont(font);
//			css.setFillForegroundColor(IndexedColors.WHITE.getIndex());
//			css.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//			cell_rms.setCellStyle(cs);
//			CellUtil.setAlignment(cell_rms, HorizontalAlignment.CENTER);
//			CellUtil.setVerticalAlignment(cell_rms, VerticalAlignment.CENTER);
//			cell_rms.setCellValue("here we go podcast.");
//			=============================================
			Drawing drawing2 = sheet.createDrawingPatriarch();
//			Cell cell_qr_code = titleRow.createCell(2);
			insertQrCodeToCell(workbook, 1, drawing2, sheet, date);
//			=============================================
            
			Row row_1 = sheet.createRow(2);
			row_1.setHeight((short) (row_1.getHeight()));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_1.createCell(col);
				if (col == 0) {
					cell.setCellValue("BBF:");
				} else if (col == 1) {
					cell.setCellValue(df.format(beginning_balance));
					// cell.setCellValue(String.format("%,.2f", ats_ending_balance));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
//				if (col == 3)
//					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}
			Row headerRow = sheet.createRow(3);
			headerRow.setHeight((short) (headerRow.getHeight()));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
			}
			int rowIdx = 4;
//			int counter = 1;
			System.out.println("beginning balanceeeeeeeeeeeeeee" + Payable_raw_data_all.size());

			for (File_rtgs_awb_core data_payable : Payable_raw_data_all) {
				Row row = sheet.createRow(rowIdx++);
				row.setHeight((short) (row.getHeight()));

				Cell c1 = row.createCell(0);
				c1.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c1, VerticalAlignment.CENTER);
				CellUtil.setAlignment(c1, HorizontalAlignment.CENTER);
				c1.setCellValue("" + data_payable.getPosting_date());
				System.out.println("here is posting date: " + data_payable.getPosting_date());

				Cell c2 = row.createCell(1);
				c2.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c2, VerticalAlignment.CENTER);
				c2.setCellValue(data_payable.getValue_date());

				Cell c3 = row.createCell(2);
				c3.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c3, VerticalAlignment.CENTER);
				c3.setCellValue(data_payable.getAdditional_information());

				Cell c4 = row.createCell(3);
				c4.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
				c4.setCellValue(data_payable.getTransaction_reference());

				Cell c5 = row.createCell(4);
				c5.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
				c5.setCellValue(data_payable.getBranch_code());

				Cell c6 = row.createCell(5);
				c6.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c6, VerticalAlignment.CENTER);
				if (data_payable.getDr_cr().equalsIgnoreCase("DR"))
					c6.setCellValue(data_payable.getAmount());
				else
					c6.setCellValue("00");

				Cell c7 = row.createCell(6);
				c7.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c7, VerticalAlignment.CENTER);
				if (data_payable.getDr_cr().equalsIgnoreCase("CR"))
					c7.setCellValue(data_payable.getAmount());
				else
					c7.setCellValue("00");
				
				
				Cell c8 = row.createCell(7);
				c8.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c8, VerticalAlignment.CENTER);
				c8.setCellValue(data_payable.getBalance());


//				Cell c8 = row.createCell(7);
//				c8.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c8, VerticalAlignment.CENTER);
//				if (data_payable.getDr_cr().equalsIgnoreCase("cr")) {
//					beginning_balance = (beginning_balance + data_payable.getAmount());
//					c8.setCellValue(df.format(beginning_balance));
//				} else {
//					beginning_balance = (beginning_balance - data_payable.getAmount());
//					c8.setCellValue(df.format(beginning_balance));
//				}

//				counter++;
//				Cell c5 = row.createCell(4);
//				c5.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
				// c5.setCellValue(data_core.getID());
//				c5.setCellValue("");
			}

//			Row row_1 = sheet.createRow(3);
//			row_1.setHeight((short) (row_1.getHeight()));
//			for (int col = 0; col < HEADERs.length; col++) {
//				Cell cell = row_1.createCell(col);
//				if (col == 1) {
//					cell.setCellValue("Balance as per Receivable Statement");
//				} else if (col == 3) {
//					cell.setCellValue(
//							" " + NumberFormat.getCurrencyInstance().format(Payable_ending_balance).replace("$", ""));
//					// cell.setCellValue(String.format("%,.2f", ats_ending_balance));
//				}
//				cell.setCellStyle(style);
//				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
//				if (col == 3)
//					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
//			}

//			Row row_1 = sheet.createRow(3);
//			row_1.setHeight((short) (row_1.getHeight()));
//			for (int col = 0; col < HEADERs.length; col++) {
//				Cell cell = row_1.createCell(col);
//				cell.setCellStyle(cs);
//				if (col == 1) {
//					cell.setCellValue("Add: Credit Entries In Payable");
//				}
//				cell.setCellStyle(style);
//				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
//			}
//
//			int rowIdx = 4;
//			int rowNo = 1;

//			for (File_rtgs_awb_core data_core :Payable_credit_data) {
//				Row row = sheet.createRow(rowIdx++);
//				row.setHeight((short) (row.getHeight()));
//				
//				Cell c1 = row.createCell(0);
//				c1.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c1, VerticalAlignment.CENTER);
//				CellUtil.setAlignment(c1, HorizontalAlignment.CENTER);
//				c1.setCellValue(""+rowNo);
//				
//				Cell c2 = row.createCell(1);
//				c2.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c2, VerticalAlignment.CENTER);
//				c2.setCellValue(data_core.getValue_date());
//				
//				Cell c3 = row.createCell(2);
//				c3.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c3, VerticalAlignment.CENTER);
//				c3.setCellValue(data_core.getAdditional_information());
//				
//				Cell c4 = row.createCell(3);
//				c4.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
//				c4.setCellValue(data_core.getAmount());
//				
//				rowNo++;
////				Cell c5 = row.createCell(4);
////				c5.setCellStyle(style2);
////				CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
////				// c5.setCellValue(data_core.getID());
////				c5.setCellValue("");
//			}

//			Row row_2 = sheet.createRow(rowIdx);
//			row_2.setHeight((short) (row_2.getHeight()));
//			for (int col = 0; col < HEADERs.length; col++) {
//				Cell cell = row_2.createCell(col);
//				if (col == 1) {
//					cell.setCellValue("Total Credit In Payable");
//				} else if (col == 3) {
//					// cell.setCellValue(NumberFormat.getCurrencyInstance().format(total_Payable_credit).replace("$",
//					// ""));
//				}
//				cell.setCellStyle(style);
//				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
//				if (col == 3)
//					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
//			}

//			rowIdx++;
//			Row row_4 = sheet.createRow(rowIdx);
//			row_4.setHeight((short) (row_4.getHeight()));
//			for (int col = 0; col < HEADERs.length; col++) {
//				Cell cell = row_4.createCell(col);
//				cell.setCellStyle(cs);
//				if (col == 0) {
//					cell.setCellValue("Total Debit Balance");
//				} else if (col == 4) {
//					cell.setCellValue(NumberFormat.getCurrencyInstance().format(ats_ending_balance + total_core_debit)
//							.replace("$", ""));
//				}
//				cell.setCellStyle(style);
//				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
//				if (col == 4)
//					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
//			}

//			rowIdx++;
//			Row row_5 = sheet.createRow(rowIdx);
//			row_5.setHeight((short) (row_5.getHeight()));
//			for (int col = 0; col < HEADERs.length; col++) {
//				Cell cell = row_5.createCell(col);
//				if (col == 1) {
//					cell.setCellValue("Less: Debit Entries In Payable");
//				}
//				cell.setCellStyle(style);
//				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
//
//			}

//			rowIdx++;
//			int counter = 1;
//			for (File_rtgs_awb_core data_core : Payable_raw_data) {
//				Row row = sheet.createRow(rowIdx++);
//				row.setHeight((short) (row.getHeight()));
//
//				Cell c1 = row.createCell(0);
//				c1.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c1, VerticalAlignment.CENTER);
//				CellUtil.setAlignment(c1, HorizontalAlignment.CENTER);
//				c1.setCellValue("" + counter);
//
//				Cell c2 = row.createCell(1);
//				c2.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c2, VerticalAlignment.CENTER);
//				c2.setCellValue(data_core.getValue_date());
//				Cell c3 = row.createCell(2);
//				c3.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c3, VerticalAlignment.CENTER);
//				c3.setCellValue(data_core.getAdditional_information());
//				Cell c4 = row.createCell(3);
//				c4.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
//				c4.setCellValue(data_core.getAmount());
//
//				counter++;
////				Cell c5 = row.createCell(4);
////				c5.setCellStyle(style2);
////				CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
//				// c5.setCellValue(data_core.getID());
////				c5.setCellValue("");
//			}

//			Row row_6 = sheet.createRow(rowIdx);
//			row_6.setHeight((short) (row_6.getHeight()));
//			for (int col = 0; col < HEADERs.length; col++) {
//				Cell cell = row_6.createCell(col);
//				if (col == 1) {
//					cell.setCellValue("Total Debit In Payable");
//				} else if (col == 3) {
//					// cell.setCellValue(NumberFormat.getCurrencyInstance().format(total_Payable_debit).replace("$",
//					// ""));
//				}
//				cell.setCellStyle(style);
//				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
//				if (col == 3)
//					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
//			}
//
//			rowIdx++;
//			Row row_7 = sheet.createRow(rowIdx);
//			row_7.setHeight((short) (row_7.getHeight()));
//			for (int col = 0; col < HEADERs.length; col++) {
//				Cell cell = row_7.createCell(col);
//				if (col == 1) {
//					cell.setCellValue("Balance(Credit-Debit)");
//				} else if (col == 3) {
////					cell.setCellValue(NumberFormat.getCurrencyInstance()
////							.format((total_Payable_credit-total_Payable_debit)).replace("$", ""));
//				}
//				cell.setCellStyle(style);
//				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
//				if (col == 4)
//					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
//			}
//
//			rowIdx++;
//			Row row_8 = sheet.createRow(rowIdx);
//			row_8.setHeight((short) (row_8.getHeight()));
//			for (int col = 0; col < HEADERs.length; col++) {
//				Cell cell = row_8.createCell(col);
//				if (col == 1) {
//					cell.setCellValue("Balance as per Payable Statement");
//				} else if (col == 3) {
////					cell.setCellValue(
////							" " + NumberFormat.getCurrencyInstance().format(Payable_ending_balance).replace("$", ""));
//					// cell.setCellValue(String.format("%,.2f", ats_ending_balance));
//				}
//				cell.setCellStyle(style);
//				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
//				if (col == 3)
//					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
//			}
//
//			rowIdx++;
//			Row row_9 = sheet.createRow(rowIdx);
//			row_9.setHeight((short) (row_9.getHeight()));
//			for (int col = 0; col < HEADERs.length; col++) {
//				Cell cell = row_9.createCell(col);
//				if (col == 1) {
//					cell.setCellValue("diff");
//				} else if (col == 3) {
////					cell.setCellValue(NumberFormat.getCurrencyInstance()
////							.format((total_Payable_credit-total_Payable_debit) - Payable_ending_balance).replace("$", ""));
//				}
//				cell.setCellStyle(style);
//				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
//				if (col == 4)
//					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
//			}
//
//			rowIdx++;
//			Row row_8 = sheet.createRow(rowIdx);
//			row_8.setHeight((short) (row_8.getHeight()));
//			for (int col = 0; col < HEADERs.length; col++) {
//				Cell cell = row_8.createCell(col);
//				if (col == 0) {
//					cell.setCellValue("Balance as per CONVENTIONAL (Book) Statement");
//				} else if (col == 4) {
//					cell.setCellValue(
//							NumberFormat.getCurrencyInstance().format(conventional_ending_balance).replace("$", ""));
//				}
//				cell.setCellStyle(style);
//				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
//				if (col == 4)
//					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
//			}
//
//			rowIdx++;
//			Row row_9 = sheet.createRow(rowIdx);
//			row_9.setHeight((short) (row_9.getHeight()));
//			for (int col = 0; col < HEADERs.length; col++) {
//				Cell cell = row_9.createCell(col);
//				if (col == 0) {
//					cell.setCellValue("Balance as per IFB Statement");
//				} else if (col == 4) {
//					cell.setCellValue(NumberFormat.getCurrencyInstance().format(ifb_ending_balance).replace("$", ""));
//				}
//				cell.setCellStyle(style);
//				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
//				if (col == 4)
//					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
//			}
//
//			rowIdx++;
//			Row row_10 = sheet.createRow(rowIdx);
//			row_10.setHeight((short) (row_10.getHeight()));
//			for (int col = 0; col < HEADERs.length; col++) {
//				Cell cell = row_10.createCell(col);
//				if (col == 0) {
//					cell.setCellValue("Balance as per Book Statement");
//				} else if (col == 4) {
//					cell.setCellValue(NumberFormat.getCurrencyInstance()
//							.format(conventional_ending_balance + ifb_ending_balance).replace("$", ""));
//				}
//				cell.setCellStyle(style);
//				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
//				if (col == 4)
//					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
//			}
//
//			rowIdx++;
//			for (File_rtgs_nbe_ats data_ats : ats_credit_data) {
//				Row row = sheet.createRow(rowIdx++);
//				row.setHeight((short) (row.getHeight()));
//				Cell c1 = row.createCell(0);
//				c1.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c1, VerticalAlignment.CENTER);
//				CellUtil.setAlignment(c1, HorizontalAlignment.RIGHT);
//				c1.setCellValue(data_ats.getValue_date_type());
//				Cell c2 = row.createCell(1);
//				c2.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c2, VerticalAlignment.CENTER);
//				c2.setCellValue(data_ats.getReference());
//				Cell c3 = row.createCell(2);
//				c3.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c3, VerticalAlignment.CENTER);
//				c3.setCellValue(data_ats.getSender() + " " + data_ats.getReceiver());
//				Cell c4 = row.createCell(3);
//				c4.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
//				c4.setCellValue(data_ats.getAmount());
//				Cell c5 = row.createCell(4);
//				c5.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
//				// c5.setCellValue(data_core.getID());
//				c5.setCellValue("");
//			}
//
//			Row row_11 = sheet.createRow(rowIdx);
//			row_11.setHeight((short) (row_11.getHeight()));
//			for (int col = 0; col < HEADERs.length; col++) {
//				Cell cell = row_11.createCell(col);
//				if (col == 0) {
//					cell.setCellValue("Total credit Balance");
//				} else if (col == 3) {
//					cell.setCellValue(NumberFormat.getCurrencyInstance().format(total_ats_credit).replace("$", ""));
//				}
//				cell.setCellStyle(style);
//				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
//				if (col == 3)
//					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
//			}
//
//			rowIdx++;
//			Row row_12 = sheet.createRow(rowIdx);
//			row_12.setHeight((short) (row_12.getHeight()));
//			for (int col = 0; col < HEADERs.length; col++) {
//				Cell cell = row_12.createCell(col);
//				if (col == 0) {
//					cell.setCellValue("Less: Debit Entries in NBE");
//				} else if (col == 4) {
//					cell.setCellValue(NumberFormat.getCurrencyInstance()
//							.format(conventional_ending_balance + ifb_ending_balance + total_ats_credit)
//							.replace("$", ""));
//				}
//				cell.setCellStyle(style);
//				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
//				if (col == 4)
//					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
//			}
//
//			rowIdx++;
//			for (File_rtgs_nbe_ats data_ats : ats_debit_data) {
//				Row row = sheet.createRow(rowIdx++);
//				row.setHeight((short) (row.getHeight()));
//				Cell c1 = row.createCell(0);
//				c1.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c1, VerticalAlignment.CENTER);
//				CellUtil.setAlignment(c1, HorizontalAlignment.RIGHT);
//				c1.setCellValue(data_ats.getValue_date_type());
//				Cell c2 = row.createCell(1);
//				c2.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c2, VerticalAlignment.CENTER);
//				c2.setCellValue(data_ats.getReference());
//				Cell c3 = row.createCell(2);
//				c3.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c3, VerticalAlignment.CENTER);
//				c3.setCellValue(data_ats.getSender() + " " + data_ats.getReceiver());
//				Cell c4 = row.createCell(3);
//				c4.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
//				c4.setCellValue(data_ats.getAmount());
//				Cell c5 = row.createCell(4);
//				c5.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
//				// c5.setCellValue(data_core.getID());
//				c5.setCellValue("");
//			}
//
//			Row row_13 = sheet.createRow(rowIdx);
//			row_13.setHeight((short) (row_13.getHeight()));
//			for (int col = 0; col < HEADERs.length; col++) {
//				Cell cell = row_13.createCell(col);
//				if (col == 0) {
//					cell.setCellValue("Total Debit on NBE");
//				} else if (col == 3) {
//					cell.setCellValue(NumberFormat.getCurrencyInstance().format(total_ats_debit).replace("$", ""));
//				}
//				cell.setCellStyle(style);
//				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
//				if (col == 3)
//					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
//			}
//
//			rowIdx++;
//			Row row_14 = sheet.createRow(rowIdx);
//			row_14.setHeight((short) (row_14.getHeight()));
//			for (int col = 0; col < HEADERs.length; col++) {
//				Cell cell = row_14.createCell(col);
//				if (col == 0) {
//					cell.setCellValue("Total Adjustment and correct Balance of Book");
//				} else if (col == 4) {
//					cell.setCellValue(NumberFormat.getCurrencyInstance().format(
//							(conventional_ending_balance + ifb_ending_balance + total_ats_credit) - total_ats_debit)
//							.replace("$", ""));
//				}
//				cell.setCellStyle(style);
//				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
//				if (col == 4)
//					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
//			}

//			rowIdx++;
//			Row row_15 = sheet.createRow(rowIdx);
//			row_15.setHeight((short) (row_15.getHeight()));
//			for (int col = 0; col < HEADERs.length; col++) {
//				Cell cell = row_15.createCell(col);
//				cell.setCellValue("");
//				cell.setCellStyle(style);
//			}
//
//			rowIdx++;
//			Row row_17 = sheet.createRow(rowIdx);
//			row_17.setHeight((short) (row_17.getHeight()));
//			for (int col = 0; col < HEADERs.length; col++) {
//				Cell cell = row_17.createCell(col);
//				cell.setCellValue("");
//				cell.setCellStyle(cs);
//			}
//
//			rowIdx++;
//			Row row_16 = sheet.createRow(rowIdx);
//			row_16.setHeight((short) (row_16.getHeight() + 400));
//			for (int col = 0; col < HEADERs.length; col++) {
//				if (col == 0) {
//					Cell cell = row_16.createCell(col);
//					cell.setCellValue("Prepared By ________");
//					cell.setCellStyle(cs);
//				} else if (col == 1) {
//					Cell cell = row_16.createCell(col);
//					cell.setCellValue("Checked By _________");
//					cell.setCellStyle(cs);
//				} else if (col == 2) {
//					Cell cell = row_16.createCell(col);
//					cell.setCellValue("Follow Up By _________");
//					cell.setCellStyle(cs);
//				} else if (col == 3) {
//					Cell cell = row_16.createCell(col);
//					cell.setCellValue("Approved By _________");
//					cell.setCellStyle(cs);
//				}
//
//			}

			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
//			sheet.setColumnWidth(0, 10 * 256);
//			sheet.setColumnWidth(1, 10 * 256);
//			sheet.setColumnWidth(2, 25 * 256);
//			sheet.setColumnWidth(3, 25 * 256);
//			sheet.setColumnWidth(4, 10 * 256);

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}

}
