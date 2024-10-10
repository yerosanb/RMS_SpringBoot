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

import com.example.demo.Stock.Model.StockReport;
import com.example.demo.abebayehu.entity.Fixed__report;
import com.example.demo.abebayehu.entity.Fixed_mms_report;
import com.example.demo.user.model.File_rtgs__;
import com.example.demo.user.model.File_rtgs__ats;
import com.google.zxing.WriterException;
import com.lowagie.text.Phrase;

public class ExcelHelperSTOCK {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Date", "Description", "GIV/GRV", "Debit", "Credit", "Balance" };

	private static void insertQrCodeToCell(Workbook workbook, int rowNum, Drawing drawing, Sheet sheet, String date)
			throws IOException, WriterException, ParseException {

//		final String DIRECTORY_logo = System.getProperty("user.dir")
//				+ "/src/main/resources/static//_002.png";
//		File file_path = new File(StringUtils.join(DIRECTORY_logo));
//		if (!file_path.exists()) {
//			file_path.mkdirs();
//		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date2 = formatter.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date2);
		String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());

		String signature = " RECONCCILIATION SYSTEM " + "fixed asset report " + monthName + " "
				+ date2.getDate() + ", " + date2.toString().split(" ")[5];
		byte[] image = new byte[0];
		image = QRCodeGenerator.getQRCodeImage(signature, 300, 300);

		int inputImagePictureID = workbook.addPicture(image, Workbook.PICTURE_TYPE_PNG);
		ClientAnchor anchor = new HSSFClientAnchor();

		anchor.setCol1(5);
		anchor.setCol2(6);
		anchor.setRow1(rowNum - 1);
		anchor.setRow2(rowNum);
		anchor.setDx1(150);
		anchor.setDy1(0);

		final Picture pict = drawing.createPicture(anchor, inputImagePictureID);
		pict.resize();
		pict.resize(0.20, 0.25);
	}

	private static void insertLogoToCell(Workbook workbook, int rowNum, Drawing drawing) throws IOException {

		final String DIRECTORY_logo = System.getProperty("user.dir")
				+ "/src/main/resources/static//_002.png";
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
		anchor.setRow2(rowNum);
//		drawing.createPicture(anchor, inputImagePictureID);

		final Picture pict = drawing.createPicture(anchor, inputImagePictureID);
		pict.resize();
		pict.resize(0.30, 0.50);

	}

	public static ByteArrayInputStream GenerateExcelStock(String date, String type, List<StockReport> data_stock_,
			List<StockReport> data_stock_mms, Double total_stock_, Double total_stock_mms)
			throws ParseException, WriterException {

		try (Workbook workbook = new HSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date date2 = formatter.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date2);
			String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());
			String SHEET = "";
			if (type.equalsIgnoreCase("121_stationary_excel")) {
				SHEET = "STATIONARY stock report " + monthName + " " + date2.getDate() + ", "
						+ date2.toString().split(" ")[5];
			} else if (type.equalsIgnoreCase("111_tools_excel")) {
				SHEET = "TOOLS stock report " + monthName + " " + date2.getDate() + ", "
						+ date2.toString().split(" ")[5];
			} else if (type.equalsIgnoreCase("113_spares_excel")) {
				SHEET = "SPARES stock report " + monthName + " " + date2.getDate() + ", "
						+ date2.toString().split(" ")[5];
			} else if (type.equalsIgnoreCase("105_uniform_excel")) {
				SHEET = "UNIFORM stock report " + monthName + " " + date2.getDate() + ", "
						+ date2.toString().split(" ")[5];
			} else if (type.equalsIgnoreCase("119_accessory_excel")) {
				SHEET = "ACCESSORY stock report " + monthName + " " + date2.getDate() + ", "
						+ date2.toString().split(" ")[5];
			} else if (type.equalsIgnoreCase("120_check_excel")) {
				SHEET = "CHECK stock report " + monthName + " " + date2.getDate() + ", "
						+ date2.toString().split(" ")[5];
			} else if (type.equalsIgnoreCase("112_sanitory_excel")) {
				SHEET = "SANITORY stock report " + monthName + " " + date2.getDate() + ", "
						+ date2.toString().split(" ")[5];
			} else if (type.equalsIgnoreCase("106_computer_excel")) {
				SHEET = "COMPUTER stock report " + monthName + " " + date2.getDate() + ", "
						+ date2.toString().split(" ")[5];
			} else if (type.equalsIgnoreCase("107_furniture_excel")) {
				SHEET = "FURNITURE stock report " + monthName + " " + date2.getDate() + ", "
						+ date2.toString().split(" ")[5];
			} else if (type.equalsIgnoreCase("104_office_equipment_excel")) {
				SHEET = "OFFICE EQUIPMENT stock report " + monthName + " " + date2.getDate() + ", "
						+ date2.toString().split(" ")[5];
			}
//			else if (type.equalsIgnoreCase("comp_fixed_excel")) {
//				SHEET = "Fixed Asset-COM report " + monthName + " " + date2.getDate() + ", "
//						+ date2.toString().split(" ")[5];
//			} else if (type.equalsIgnoreCase("vehi_fixed_excel")) {
//				SHEET = "Fixed Asset-MV report " + monthName + " " + date2.getDate() + ", "
//						+ date2.toString().split(" ")[5];
//			} else if (type.equalsIgnoreCase("equp_fixed_excel")) {
//				SHEET = "Fixed Asset-OE report " + monthName + " " + date2.getDate() + ", "
//						+ date2.toString().split(" ")[5];
//			}
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
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 4));
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 4));

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
			CellUtil.setAlignment(titleCell, HorizontalAlignment.LEFT);
			CellUtil.setVerticalAlignment(titleCell, VerticalAlignment.CENTER);

			if (type.equalsIgnoreCase("121_stationary_excel")) {
				titleCell.setCellValue("RECONCILIATION OF STATIONARY STOCK \nAs of " + monthName + " " + date2.getDate()
						+ ", " + date2.toString().split(" ")[5]);
			} else if (type.equalsIgnoreCase("111_tools_excel")) {
				titleCell.setCellValue("RECONCILIATION OF TOOLS STOCK \nAs of " + monthName + " " + date2.getDate()
						+ ", " + date2.toString().split(" ")[5]);
			} else if (type.equalsIgnoreCase("113_spares_excel")) {
				titleCell.setCellValue("RECONCILIATION OF SPARES STOCK \nAs of " + monthName + " " + date2.getDate()
						+ ", " + date2.toString().split(" ")[5]);
			} else if (type.equalsIgnoreCase("105_uniform_excel")) {
				titleCell.setCellValue("RECONCILIATION OF UNIFORM STOCK \nAs of " + monthName + " " + date2.getDate()
						+ ", " + date2.toString().split(" ")[5]);
			} else if (type.equalsIgnoreCase("119_accessory_excel")) {
				titleCell.setCellValue("RECONCILIATION OF ACCESSORY STOCK \nAs of " + monthName + " " + date2.getDate()
						+ ", " + date2.toString().split(" ")[5]);
			} else if (type.equalsIgnoreCase("120_check_excel")) {
				titleCell.setCellValue("RECONCILIATION OF CHECK STOCK \nAs of " + monthName + " " + date2.getDate()
						+ ", " + date2.toString().split(" ")[5]);
			} else if (type.equalsIgnoreCase("112_sanitory_excel")) {
				titleCell.setCellValue("RECONCILIATION OF SANITORY STOCK \nAs of " + monthName + " " + date2.getDate()
						+ ", " + date2.toString().split(" ")[5]);
			} else if (type.equalsIgnoreCase("106_computer_excel")) {
				titleCell.setCellValue("RECONCILIATION OF COMPUTER STOCK \nAs of " + monthName + " " + date2.getDate()
						+ ", " + date2.toString().split(" ")[5]);
			} else if (type.equalsIgnoreCase("107_furniture_excel")) {
				titleCell.setCellValue("RECONCILIATION OF FURNITURE STOCK \nAs of " + monthName + " " + date2.getDate()
						+ ", " + date2.toString().split(" ")[5]);
			} else if (type.equalsIgnoreCase("104_office_equipment_excel")) {
				titleCell.setCellValue("RECONCILIATION OF OFFICE EQUIPMENT STOCK \nAs of " + monthName + " " + date2.getDate()
						+ ", " + date2.toString().split(" ")[5]);
			}

//			else if (type.equalsIgnoreCase("comp_fixed_excel"))
//				titleCell.setCellValue("RECONCILIATION OF FIXED ASSET OF Computer \nAs of " + monthName + " "
//						+ date2.getDate() + ", " + date2.toString().split(" ")[5]);
//
//			else if (type.equalsIgnoreCase("vehi_fixed_excel"))
//				titleCell.setCellValue("RECONCILIATION OF FIXED ASSET OF Motor-Vehicle \nAs of " + monthName + " "
//						+ date2.getDate() + ", " + date2.toString().split(" ")[5]);
//
//			else if (type.equalsIgnoreCase("equp_fixed_excel"))
//				titleCell.setCellValue("RECONCILIATION OF FIXED ASSET OF Office-Equipment \nAs of " + monthName + " "
//						+ date2.getDate() + ", " + date2.toString().split(" ")[5]);

			Row row_0 = sheet.createRow(1);
			Cell cellt = row_0.createCell(4);
			cellt.setCellStyle(cs);
			CellUtil.setAlignment(cellt, HorizontalAlignment.CENTER);
			CellUtil.setVerticalAlignment(cellt, VerticalAlignment.CENTER);
			cellt.setCellValue(" R.M.S");

			Drawing drawing2 = sheet.createDrawingPatriarch();
			insertQrCodeToCell(workbook, 1, drawing2, sheet, date);
//			=============================================

			Row headerRow = sheet.createRow(2);
			headerRow.setHeight((short) (headerRow.getHeight()));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
			}

			Row row_1 = sheet.createRow(3);
			row_1.setHeight((short) (row_1.getHeight()));
			Cell cellz = row_1.createCell(0);
			cellz.setCellValue(
					"BFUB BALANCE As of " + monthName + " " + date2.getDate() + ", " + date2.toString().split(" ")[5]);
			Cell cellkk = row_1.createCell(5);
			cellkk.setCellStyle(style2);
			cellkk.setCellValue(String.format("%,.2f", total_stock_));
			CellUtil.setAlignment(cellz, HorizontalAlignment.CENTER);

			double total__credit = 0d;
			double total__debit = 0d;

			int rowIdx = 4;
			for (StockReport data_ : data_stock_) {
				Row row = sheet.createRow(rowIdx++);
				row.setHeight((short) (row.getHeight()));

				Cell c1 = row.createCell(0);
				c1.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c1, VerticalAlignment.CENTER);
				CellUtil.setAlignment(c1, HorizontalAlignment.RIGHT);
				c1.setCellValue(data_.getPosting_date().substring(0, 10));

				Cell c2 = row.createCell(1);
				c2.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c2, VerticalAlignment.CENTER);
				c2.setCellValue("");

				Cell c3 = row.createCell(2);
				c3.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c3, VerticalAlignment.CENTER);
				c3.setCellValue(data_.getReference());

				if (data_.getDr_cr().equalsIgnoreCase("dr")) {
					total__debit = total__debit + data_.getAmount();
					Cell c4 = row.createCell(3);
					c4.setCellStyle(style2);
					CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
					CellUtil.setAlignment(c4, HorizontalAlignment.RIGHT);
					c4.setCellValue(String.format("%,.2f", data_.getAmount()));

					Cell c5 = row.createCell(4);
					c5.setCellStyle(style2);
					CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
					CellUtil.setAlignment(c5, HorizontalAlignment.RIGHT);
					c5.setCellValue("0.00");
				} else {

					total__credit = total__credit + data_.getAmount();
					Cell c5 = row.createCell(3);
					c5.setCellStyle(style2);
					CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
					CellUtil.setAlignment(c5, HorizontalAlignment.RIGHT);
					c5.setCellValue("0.00");

					Cell c4 = row.createCell(4);
					c4.setCellStyle(style2);
					CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
					CellUtil.setAlignment(c4, HorizontalAlignment.RIGHT);
					c4.setCellValue(String.format("%,.2f", data_.getAmount()));
				}

				Cell c6 = row.createCell(5);
				c6.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c6, VerticalAlignment.CENTER);
				c6.setCellValue(String.format("%,.2f", data_.getBalance()));
			}

			Row row_22 = sheet.createRow(rowIdx++);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_22.createCell(col);
				cell.setCellStyle(cs);
				if (col == 0) {
					cell.setCellValue("Total Debit and Credit Balance");
				} else if (col == 3) {
					cell.setCellValue(NumberFormat.getCurrencyInstance().format(total__debit).replace("$", ""));
					// cell.setCellValue(String.format("%,.2f", ats_ending_balance));
				} else if (col == 4) {
					cell.setCellValue(NumberFormat.getCurrencyInstance().format(total__credit).replace("$", ""));
					// cell.setCellValue(String.format("%,.2f", ats_ending_balance));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);

				if (col == 5)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}

			Row row_33 = sheet.createRow(rowIdx++);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_33.createCell(col);
				cell.setCellStyle(cs);
				if (col == 0) {
					cell.setCellValue("Adjusted Balance ");
				} else if (col == 5) {
					cell.setCellValue(NumberFormat.getCurrencyInstance()
							.format(total_stock_ - total__debit + total__credit).replace("$", ""));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);

				if (col == 5)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}

			double total_mms_credit = 0d;
			double total_mms_debit = 0d;
			for (StockReport data_mms : data_stock_mms) {
				Row row = sheet.createRow(rowIdx++);
				row.setHeight((short) (row.getHeight()));

				Cell c1 = row.createCell(0);
				c1.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c1, VerticalAlignment.CENTER);
				CellUtil.setAlignment(c1, HorizontalAlignment.RIGHT);
				c1.setCellValue(data_mms.getDate().substring(0, 10));

				Cell c2 = row.createCell(1);
				c2.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c2, VerticalAlignment.CENTER);
				c2.setCellValue(data_mms.getDescription());

				Cell c3 = row.createCell(2);
				c3.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c3, VerticalAlignment.CENTER);
				c3.setCellValue(data_mms.getReference());

				if (data_mms.getDr_cr().equalsIgnoreCase("dr")) {
					total_mms_debit = total_mms_debit + data_mms.getAmount();
					Cell c4 = row.createCell(3);
					c4.setCellStyle(style2);
					CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
					CellUtil.setAlignment(c4, HorizontalAlignment.RIGHT);
					c4.setCellValue(String.format("%,.2f", data_mms.getAmount()));

					Cell c5 = row.createCell(4);
					c5.setCellStyle(style2);
					CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
					CellUtil.setAlignment(c5, HorizontalAlignment.RIGHT);
					c5.setCellValue("0.00");
				} else {
					total_mms_credit = total_mms_credit + data_mms.getAmount();
					Cell c5 = row.createCell(3);
					c5.setCellStyle(style2);
					CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
					CellUtil.setAlignment(c5, HorizontalAlignment.RIGHT);
					c5.setCellValue("0.00");

					Cell c4 = row.createCell(4);
					c4.setCellStyle(style2);
					CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
					CellUtil.setAlignment(c4, HorizontalAlignment.RIGHT);
					c4.setCellValue(String.format("%,.2f", data_mms.getAmount()));
				}

				Cell c6 = row.createCell(5);
				c6.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c6, VerticalAlignment.CENTER);
				c6.setCellValue("");
			}

			Row row_44 = sheet.createRow(rowIdx++);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_44.createCell(col);
				cell.setCellStyle(cs);
				if (col == 0) {
					cell.setCellValue("Total Debit and Credit Balance");
				} else if (col == 3) {
					cell.setCellValue(NumberFormat.getCurrencyInstance().format(total_mms_debit).replace("$", ""));
					// cell.setCellValue(String.format("%,.2f", ats_ending_balance));
				} else if (col == 4) {
					cell.setCellValue(NumberFormat.getCurrencyInstance().format(total_mms_credit).replace("$", ""));
					// cell.setCellValue(String.format("%,.2f", ats_ending_balance));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);

			}

			Row row_55 = sheet.createRow(rowIdx++);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_55.createCell(col);
				cell.setCellStyle(cs);
				if (col == 0) {
					cell.setCellValue("ADJUSTED BALANCE");
				} else if (col == 5) {
					cell.setCellValue(NumberFormat.getCurrencyInstance()
							.format((total_stock_ - total__debit + total__credit) + total_mms_debit
									- total_mms_credit)
							.replace("$", ""));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);

			}

			Row row_67 = sheet.createRow(rowIdx++);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_67.createCell(col);
				cell.setCellStyle(cs);
				if (col == 0) {
					cell.setCellValue("MMS LEDGER BALANCE ");
				} else if (col == 5) {
					cell.setCellValue(NumberFormat.getCurrencyInstance().format(total_stock_mms).replace("$", ""));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);

			}

			Row row_77 = sheet.createRow(rowIdx++);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_77.createCell(col);
				cell.setCellStyle(cs);
				if (col == 0) {
					cell.setCellValue("");
				} else if (col == 5) {
					cell.setCellValue(NumberFormat.getCurrencyInstance()
							.format((total_stock_ - total__debit + total__credit) + total_mms_debit
									- total_mms_credit - total_stock_mms)
							.replace("$", ""));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);

			}

			rowIdx++;
			Row row_11 = sheet.createRow(rowIdx);
			row_11.setHeight((short) (row_11.getHeight() + 400));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_11.createCell(col);
				if (col == 2) {
					cell.setCellValue("Prepared By ");
					cell.setCellStyle(cs);
				}

				else if (col == 3) {
					cell.setCellValue(" _____________ ");
					cell.setCellStyle(cs);
				}
			}

			rowIdx++;
			Row row_12 = sheet.createRow(rowIdx);
			row_12.setHeight((short) (row_12.getHeight() + 400));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_12.createCell(col);
				if (col == 2) {
					cell.setCellValue("Checked By");
					cell.setCellStyle(cs);
				} else if (col == 3) {
					cell.setCellValue(" _____________");
					cell.setCellStyle(cs);
				}
			}

			rowIdx++;
			Row row_13 = sheet.createRow(rowIdx);
			row_13.setHeight((short) (row_13.getHeight() + 400));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_13.createCell(col);
				if (col == 2) {
					cell.setCellValue("Follow Up By");
					cell.setCellStyle(cs);
				} else if (col == 3) {
					cell.setCellValue(" _____________");
					cell.setCellStyle(cs);
				}
			}

			rowIdx++;
			Row row_14 = sheet.createRow(rowIdx);
			row_14.setHeight((short) (row_14.getHeight() + 400));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_14.createCell(col);
				if (col == 2) {
					cell.setCellValue("Approved By");
					cell.setCellStyle(cs);
				} else if (col == 3) {
					cell.setCellValue(" _____________");
					cell.setCellStyle(cs);
				}
			}

			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.setColumnWidth(0, 12 * 256);
			sheet.setColumnWidth(1, 21 * 256);
			sheet.setColumnWidth(2, 21 * 256);
			sheet.setColumnWidth(3, 12 * 256);
			sheet.setColumnWidth(4, 12 * 256);
			sheet.setColumnWidth(5, 12 * 256);

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}

}
