package com.example.demo.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.user.model.File_rtgs_awb_core;
import com.example.demo.user.model.File_rtgs_nbe_ats;
import com.google.zxing.WriterException;

public class ExcelHelperIssue {

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Date", "Description", "Code", "Amount", "Setteled Date/Balance" };
	static String SHEET = "P&S report Jan 31, 2023";
	
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
		
		String signature = "AWASH BANK RECONCCILIATION SYSTEM " + "PAS report " + monthName + " " + date2.getDate() + ", " + date2.toString().split(" ")[5];
		byte[] image = new byte[0];
		image = QRCodeGenerator.getQRCodeImage(signature, 300, 300);

		int inputImagePictureID = workbook.addPicture(image, Workbook.PICTURE_TYPE_PNG);
		ClientAnchor anchor = new HSSFClientAnchor();

		anchor.setCol1(4);
		anchor.setCol2(5);
		anchor.setRow1(rowNum - 1);
		anchor.setRow2(rowNum);
		anchor.setDx1(500); 
		anchor.setDy1(0); 

		final Picture pict = drawing.createPicture(anchor, inputImagePictureID);
		pict.resize();
		pict.resize(0.12, 0.25);
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
		anchor.setCol2(1);
		anchor.setRow1(rowNum - 1);
		anchor.setRow2(rowNum);
		drawing.createPicture(anchor, inputImagePictureID);

	}

	public static ByteArrayInputStream tutorialsToExcel(String date, List<File_rtgs_awb_core> core_debit_data, List<File_rtgs_awb_core> core_debit_data_setteled, Double ats_ending_balance,
			Double total_core_debit, Double total_core_debit_setteled,  List<File_rtgs_awb_core> core_credit_data, List<File_rtgs_awb_core> core_credit_data_setteled, Double total_core_credit,
			Double total_core_credit_setteled, Double ifb_ending_balance, List<File_rtgs_nbe_ats> ats_credit_data,List<File_rtgs_nbe_ats> ats_credit_data_setteled,
			Double total_ats_credit, Double total_ats_credit_setteled, List<File_rtgs_nbe_ats> ats_debit_data, List<File_rtgs_nbe_ats> ats_debit_data_setteled, Double total_ats_debit, Double total_ats_debit_setteled) throws ParseException, WriterException {

		try (Workbook workbook = new HSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET);

			CellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			CellStyle style2 = workbook.createCellStyle();
			style2.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			Font font = workbook.createFont();
			font.setFontHeightInPoints((short) 7);
			font.setBold(true);

			style.setFont(font);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);

			Font font2 = workbook.createFont();
			font2.setFontHeightInPoints((short) 7);

			style2.setFont(font2);
			style2.setBorderBottom(BorderStyle.THIN);
			style2.setBorderTop(BorderStyle.THIN);
			style2.setBorderRight(BorderStyle.THIN);
			style2.setBorderLeft(BorderStyle.THIN);

			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
			// Header
			Row titleRow = sheet.createRow(0);
			titleRow.setHeight((short) (1200));
			
//			=============================================
			Drawing drawing = sheet.createDrawingPatriarch();

			insertLogoToCell(workbook, 1, drawing);
//			=============================================
			
			Cell titleCell = titleRow.createCell(0);
			CellStyle cs = titleCell.getCellStyle();
			cs.setWrapText(true);
			cs.setFont(font);
			cs.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			titleCell.setCellStyle(cs);
			CellUtil.setAlignment(titleCell, HorizontalAlignment.CENTER);
			CellUtil.setVerticalAlignment(titleCell, VerticalAlignment.CENTER);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date date2 = formatter.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date2);
			String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());

			titleCell.setCellValue(
					"RECONCILIATION OF ISSUE ACCOUNT\nAs of " + monthName + " " + date2.getDate() + ", "
							+ date2.toString().split(" ")[5]);
			Row row_0 = sheet.createRow(1);
			Cell cellt = row_0.createCell(4);
			cellt.setCellStyle(cs);
			CellUtil.setAlignment(cellt, HorizontalAlignment.CENTER);
			CellUtil.setVerticalAlignment(cellt, VerticalAlignment.CENTER);
			cellt.setCellValue("AWASH BANK R.M.S");
//			=============================================
			Drawing drawing2 = sheet.createDrawingPatriarch();
//			Cell cell_qr_code = titleRow.createCell(2);
			insertQrCodeToCell(workbook, 1, drawing2, sheet, date);
//			=============================================

			Row headerRow = sheet.createRow(2);
			headerRow.setHeight((short) (headerRow.getHeight() - 100));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
			}

			Row row_1 = sheet.createRow(3);
			row_1.setHeight((short) (row_1.getHeight() - 100));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_1.createCell(col);
				if (col == 0) {
					cell.setCellValue("Balance as per NBE Statement");
				} else if (col == 4) {
					cell.setCellValue(
							" " + NumberFormat.getCurrencyInstance().format(ats_ending_balance).replace("$", ""));
					// cell.setCellValue(String.format("%,.2f", ats_ending_balance));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				if (col == 4)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}

			Row row_2 = sheet.createRow(4);
			row_2.setHeight((short) (row_2.getHeight() - 100));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_2.createCell(col);
				cell.setCellStyle(cs);
				if (col == 0) {
					cell.setCellValue("Add: Debit entries in Book");
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
			}

			int rowIdx = 5;
			for (File_rtgs_awb_core data_core : core_debit_data) {
				Row row = sheet.createRow(rowIdx++);
				row.setHeight((short) (row.getHeight() - 100));
				Cell c1 = row.createCell(0);
				c1.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c1, VerticalAlignment.CENTER);
				CellUtil.setAlignment(c1, HorizontalAlignment.RIGHT);
				c1.setCellValue(data_core.getValue_date());
				Cell c2 = row.createCell(1);
				c2.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c2, VerticalAlignment.CENTER);
				c2.setCellValue(data_core.getAdditional_information());
				Cell c3 = row.createCell(2);
				c3.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c3, VerticalAlignment.CENTER);
				c3.setCellValue(data_core.getBranch_code());
				Cell c4 = row.createCell(3);
				c4.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
				c4.setCellValue(data_core.getAmount());
				Cell c5 = row.createCell(4);
				c5.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
				// c5.setCellValue(data_core.getID());
				c5.setCellValue("");
			}
			
			for (File_rtgs_awb_core data_core : core_debit_data_setteled) {
				Row row = sheet.createRow(rowIdx++);
				row.setHeight((short) (row.getHeight() - 100));
				Cell c1 = row.createCell(0);
				c1.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c1, VerticalAlignment.CENTER);
				CellUtil.setAlignment(c1, HorizontalAlignment.RIGHT);
				c1.setCellValue(data_core.getValue_date());
				Cell c2 = row.createCell(1);
				c2.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c2, VerticalAlignment.CENTER);
				c2.setCellValue(data_core.getAdditional_information());
				Cell c3 = row.createCell(2);
				c3.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c3, VerticalAlignment.CENTER);
				c3.setCellValue(data_core.getBranch_code());
				Cell c4 = row.createCell(3);
				c4.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
				c4.setCellValue(data_core.getAmount());
				Cell c5 = row.createCell(4);
				c5.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
				c5.setCellValue(data_core.getSetteled_date());
				//c5.setCellValue("");
			}

			Row row_3 = sheet.createRow(rowIdx);
			row_3.setHeight((short) (row_3.getHeight() - 100));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_3.createCell(col);
				if (col == 0) {
					cell.setCellValue("Total Debit in Book");
				} else if (col == 3) {
					cell.setCellValue(NumberFormat.getCurrencyInstance().format(total_core_debit + total_core_debit_setteled).replace("$", ""));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				if (col == 3)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}

			rowIdx++;
			Row row_4 = sheet.createRow(rowIdx);
			row_4.setHeight((short) (row_4.getHeight() - 100));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_4.createCell(col);
				cell.setCellStyle(cs);
				if (col == 0) {
					cell.setCellValue("Total Debit Balance");
				} else if (col == 4) {
					cell.setCellValue(NumberFormat.getCurrencyInstance().format(ats_ending_balance + total_core_debit + total_core_debit_setteled)
							.replace("$", ""));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				if (col == 4)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}

			rowIdx++;
			Row row_5 = sheet.createRow(rowIdx);
			row_5.setHeight((short) (row_5.getHeight() - 100));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_5.createCell(col);
				if (col == 0) {
					cell.setCellValue("Less: Credit Entries in Book");
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);

			}

			rowIdx++;
			for (File_rtgs_awb_core data_core : core_credit_data) {
				Row row = sheet.createRow(rowIdx++);
				row.setHeight((short) (row.getHeight() - 100));
				Cell c1 = row.createCell(0);
				c1.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c1, VerticalAlignment.CENTER);
				CellUtil.setAlignment(c1, HorizontalAlignment.RIGHT);
				c1.setCellValue(data_core.getValue_date());
				Cell c2 = row.createCell(1);
				c2.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c2, VerticalAlignment.CENTER);
				c2.setCellValue(data_core.getAdditional_information());
				Cell c3 = row.createCell(2);
				c3.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c3, VerticalAlignment.CENTER);
				c3.setCellValue(data_core.getBranch_code());
				Cell c4 = row.createCell(3);
				c4.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
				c4.setCellValue(data_core.getAmount());
				Cell c5 = row.createCell(4);
				c5.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
				// c5.setCellValue(data_core.getID());
				c5.setCellValue("");
			}
			
			for (File_rtgs_awb_core data_core : core_credit_data_setteled) {
				Row row = sheet.createRow(rowIdx++);
				row.setHeight((short) (row.getHeight() - 100));
				Cell c1 = row.createCell(0);
				c1.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c1, VerticalAlignment.CENTER);
				CellUtil.setAlignment(c1, HorizontalAlignment.RIGHT);
				c1.setCellValue(data_core.getValue_date());
				Cell c2 = row.createCell(1);
				c2.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c2, VerticalAlignment.CENTER);
				c2.setCellValue(data_core.getAdditional_information());
				Cell c3 = row.createCell(2);
				c3.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c3, VerticalAlignment.CENTER);
				c3.setCellValue(data_core.getBranch_code());
				Cell c4 = row.createCell(3);
				c4.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
				c4.setCellValue(data_core.getAmount());
				Cell c5 = row.createCell(4);
				c5.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
			    c5.setCellValue(data_core.getSetteled_date());
				//c5.setCellValue("");
			}


			Row row_6 = sheet.createRow(rowIdx);
			row_6.setHeight((short) (row_6.getHeight() - 100));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_6.createCell(col);
				if (col == 0) {
					cell.setCellValue("Total credit in bank");
				} else if (col == 3) {
					cell.setCellValue(NumberFormat.getCurrencyInstance().format(total_core_credit + total_core_credit_setteled).replace("$", ""));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				if (col == 3)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}

			rowIdx++;
			Row row_7 = sheet.createRow(rowIdx);
			row_7.setHeight((short) (row_7.getHeight() - 100));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_7.createCell(col);
				if (col == 0) {
					cell.setCellValue("Total Adjustment and correct Balance of NBE");
				} else if (col == 4) {
					cell.setCellValue(NumberFormat.getCurrencyInstance()
							.format((ats_ending_balance + total_core_debit + total_core_debit_setteled) - (total_core_credit + total_core_credit_setteled)).replace("$", ""));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				if (col == 4)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}

//			rowIdx++;
//			Row row_8 = sheet.createRow(rowIdx);
//			row_8.setHeight((short) (row_8.getHeight() - 100));
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
//			row_9.setHeight((short) (row_9.getHeight() - 100));
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

			rowIdx++;
			Row row_10 = sheet.createRow(rowIdx);
			row_10.setHeight((short) (row_10.getHeight() - 100));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_10.createCell(col);
				if (col == 0) {
					cell.setCellValue("Balance as per Book Statement");
				} else if (col == 4) {
					cell.setCellValue(NumberFormat.getCurrencyInstance()
							.format( ifb_ending_balance).replace("$", ""));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				if (col == 4)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}

			rowIdx++;
			for (File_rtgs_nbe_ats data_ats : ats_credit_data) {
				Row row = sheet.createRow(rowIdx++);
				row.setHeight((short) (row.getHeight() - 100));
				Cell c1 = row.createCell(0);
				c1.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c1, VerticalAlignment.CENTER);
				CellUtil.setAlignment(c1, HorizontalAlignment.RIGHT);
				c1.setCellValue(data_ats.getValue_date_type());
				Cell c2 = row.createCell(1);
				c2.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c2, VerticalAlignment.CENTER);
				c2.setCellValue(data_ats.getReference());
				Cell c3 = row.createCell(2);
				c3.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c3, VerticalAlignment.CENTER);
				c3.setCellValue(data_ats.getSender());
				//+ " " + data_ats.getReceiver());
				Cell c4 = row.createCell(3);
				c4.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
				c4.setCellValue(data_ats.getAmount());
				Cell c5 = row.createCell(4);
				c5.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
				// c5.setCellValue(data_core.getID());
				c5.setCellValue("");
			}
			
			for (File_rtgs_nbe_ats data_ats : ats_credit_data_setteled) {
				Row row = sheet.createRow(rowIdx++);
				row.setHeight((short) (row.getHeight() - 100));
				Cell c1 = row.createCell(0);
				c1.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c1, VerticalAlignment.CENTER);
				CellUtil.setAlignment(c1, HorizontalAlignment.RIGHT);
				c1.setCellValue(data_ats.getValue_date_type());
				Cell c2 = row.createCell(1);
				c2.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c2, VerticalAlignment.CENTER);
				c2.setCellValue(data_ats.getReference());
				Cell c3 = row.createCell(2);
				c3.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c3, VerticalAlignment.CENTER);
				c3.setCellValue(data_ats.getSender());
				//+ " " + data_ats.getReceiver());
				Cell c4 = row.createCell(3);
				c4.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
				c4.setCellValue(data_ats.getAmount());
				Cell c5 = row.createCell(4);
				c5.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
				c5.setCellValue(data_ats.getSetteled_date());
				//c5.setCellValue("");
			}

			Row row_11 = sheet.createRow(rowIdx);
			row_11.setHeight((short) (row_11.getHeight() - 100));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_11.createCell(col);
				if (col == 0) {
					cell.setCellValue("Total credit Balance");
				} else if (col == 3) {
					cell.setCellValue(NumberFormat.getCurrencyInstance().format(total_ats_credit + total_ats_credit_setteled).replace("$", ""));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				if (col == 3)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}

			rowIdx++;
			Row row_12 = sheet.createRow(rowIdx);
			row_12.setHeight((short) (row_12.getHeight() - 100));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_12.createCell(col);
				if (col == 0) {
					cell.setCellValue("Less: Debit Entries in NBE");
				} else if (col == 4) {
					cell.setCellValue(NumberFormat.getCurrencyInstance()
							.format(ifb_ending_balance + total_ats_credit)
							.replace("$", ""));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				if (col == 4)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}

			rowIdx++;
			for (File_rtgs_nbe_ats data_ats : ats_debit_data) {
				Row row = sheet.createRow(rowIdx++);
				row.setHeight((short) (row.getHeight() - 100));
				Cell c1 = row.createCell(0);
				c1.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c1, VerticalAlignment.CENTER);
				CellUtil.setAlignment(c1, HorizontalAlignment.RIGHT);
				c1.setCellValue(data_ats.getValue_date_type());
				Cell c2 = row.createCell(1);
				c2.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c2, VerticalAlignment.CENTER);
				c2.setCellValue(data_ats.getReference());
				Cell c3 = row.createCell(2);
				c3.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c3, VerticalAlignment.CENTER);
				c3.setCellValue(data_ats.getSender());
						//+ " " + data_ats.getReceiver());
				Cell c4 = row.createCell(3);
				c4.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
				c4.setCellValue(data_ats.getAmount());
				Cell c5 = row.createCell(4);
				c5.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
				// c5.setCellValue(data_core.getID());
				c5.setCellValue("");
			}
			
			for (File_rtgs_nbe_ats data_ats : ats_debit_data_setteled) {
				Row row = sheet.createRow(rowIdx++);
				row.setHeight((short) (row.getHeight() - 100));
				Cell c1 = row.createCell(0);
				c1.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c1, VerticalAlignment.CENTER);
				CellUtil.setAlignment(c1, HorizontalAlignment.RIGHT);
				c1.setCellValue(data_ats.getValue_date_type());
				Cell c2 = row.createCell(1);
				c2.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c2, VerticalAlignment.CENTER);
				c2.setCellValue(data_ats.getReference());
				Cell c3 = row.createCell(2);
				c3.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c3, VerticalAlignment.CENTER);
				c3.setCellValue(data_ats.getSender());
						//+ " " + data_ats.getReceiver());
				Cell c4 = row.createCell(3);
				c4.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
				c4.setCellValue(data_ats.getAmount());
				Cell c5 = row.createCell(4);
				c5.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
				c5.setCellValue(data_ats.getSetteled_date());
				//c5.setCellValue("");
			}

			Row row_13 = sheet.createRow(rowIdx);
			row_13.setHeight((short) (row_13.getHeight() - 100));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_13.createCell(col);
				if (col == 0) {
					cell.setCellValue("Total Debit on NBE");
				} else if (col == 3) {
					cell.setCellValue(NumberFormat.getCurrencyInstance().format(total_ats_debit + total_ats_debit_setteled).replace("$", ""));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				if (col == 3)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}

			rowIdx++;
			Row row_14 = sheet.createRow(rowIdx);
			row_14.setHeight((short) (row_14.getHeight() - 100));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_14.createCell(col);
				if (col == 0) {
					cell.setCellValue("Total Adjustment and correct Balance of Book");
				} else if (col == 4) {
					cell.setCellValue(NumberFormat.getCurrencyInstance().format(
							(ifb_ending_balance + total_ats_credit + total_ats_credit_setteled) - (total_ats_debit + total_ats_debit_setteled))
							.replace("$", ""));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				if (col == 4)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}

			rowIdx++;
			Row row_15 = sheet.createRow(rowIdx);
			row_15.setHeight((short) (row_15.getHeight() - 100));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_15.createCell(col);
				cell.setCellValue("");
				cell.setCellStyle(style);
			}

			rowIdx++;
			Row row_17 = sheet.createRow(rowIdx);
			row_17.setHeight((short) (row_17.getHeight() - 100));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_17.createCell(col);
				cell.setCellValue("");
				cell.setCellStyle(cs);
			}

			rowIdx++;
			Row row_16 = sheet.createRow(rowIdx);
			row_16.setHeight((short) (row_16.getHeight() + 400));
			for (int col = 0; col < HEADERs.length; col++) {
				if (col == 0) {
					Cell cell = row_16.createCell(col);
					cell.setCellValue("Prepared By ________");
					cell.setCellStyle(cs);
				} else if (col == 1) {
					Cell cell = row_16.createCell(col);
					cell.setCellValue("Checked By _________");
					cell.setCellStyle(cs);
				} else if (col == 2) {
					Cell cell = row_16.createCell(col);
					cell.setCellValue("Follow Up By _________");
					cell.setCellStyle(cs);
				} else if (col == 4) {
					Cell cell = row_16.createCell(col);
					cell.setCellValue("Approved By _________");
					cell.setCellStyle(cs);
				}

			}

			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.setColumnWidth(0, 20 * 256);
			sheet.setColumnWidth(1, 22 * 256);
			sheet.setColumnWidth(2, 9 * 256);
			sheet.setColumnWidth(3, 13 * 256);
			sheet.setColumnWidth(4, 22 * 256);

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}
}