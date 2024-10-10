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

import com.example.demo.abebayehu.entity.Fixed__report;
import com.example.demo.abebayehu.entity.Fixed_mms_report;
import com.example.demo.user.model.File_rtgs__;
import com.example.demo.user.model.File_rtgs__ats;
import com.google.zxing.WriterException;

public class ExcelHelperFixed {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Date", "Asset Description", "GIV/GRV", "Department/Branch", "Original Cost", "Balance" };

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
		
		String signature = " RECONCCILIATION SYSTEM " + "fixed asset report " + monthName + " " + date2.getDate() + ", " + date2.toString().split(" ")[5];
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
		anchor.setCol2(1);
		anchor.setRow1(rowNum - 1);
		anchor.setRow2(rowNum);
		drawing.createPicture(anchor, inputImagePictureID);

	}

	public static ByteArrayInputStream GenerateExcelFixed(String date, String type,  List<Fixed__report> _debit_data,
			List<Fixed__report> _credit_data, Double total_, 
			List<Fixed_mms_report> mms_data, Double total_mms,  Double conv, Double ifb, 
			Double waiting, Double disposed, Double removed, Double mms_balance) 
					throws ParseException, WriterException {

		try (Workbook workbook = new HSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date date2 = formatter.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date2);
			String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());
			String SHEET="";
			if(type.equalsIgnoreCase("fur_fixed_excel")) {
			SHEET = "Fixed Asset-FF report " + monthName + " " + date2.getDate() + ", " + date2.toString().split(" ")[5];
			}
			else if(type.equalsIgnoreCase("comp_fixed_excel")) {
			 SHEET = "Fixed Asset-COM report " + monthName + " " + date2.getDate() + ", " + date2.toString().split(" ")[5];
			}	
			else if(type.equalsIgnoreCase("vehi_fixed_excel")) {
			 SHEET = "Fixed Asset-MV report " + monthName + " " + date2.getDate() + ", " + date2.toString().split(" ")[5];
			}
			else if(type.equalsIgnoreCase("equp_fixed_excel")) {
			 SHEET = "Fixed Asset-OE report " + monthName + " " + date2.getDate() + ", " + date2.toString().split(" ")[5];
			}
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

			sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 3));
			
			
			// Header
			Row titleRow = sheet.createRow(0);
			titleRow.setHeight((short) (1200));

//			=============================================
			Drawing drawing = sheet.createDrawingPatriarch();

			insertLogoToCell(workbook, 1, drawing);
//			=============================================

			Cell titleCell = titleRow.createCell(1);
			CellStyle cs = titleCell.getCellStyle();
			cs.setWrapText(true);
			cs.setFont(font);
			cs.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			titleCell.setCellStyle(cs);
			CellUtil.setAlignment(titleCell, HorizontalAlignment.CENTER);
			CellUtil.setVerticalAlignment(titleCell, VerticalAlignment.CENTER);
			
            if(type.equalsIgnoreCase("fur_fixed_excel"))
			titleCell.setCellValue("RECONCILIATION OF FIXED ASSET OF FURNITURE \nAs of " + monthName + " "
					+ date2.getDate() + ", " + date2.toString().split(" ")[5]);
            
            else if(type.equalsIgnoreCase("comp_fixed_excel"))
            titleCell.setCellValue("RECONCILIATION OF FIXED ASSET OF Computer \nAs of " + monthName + " "
    					+ date2.getDate() + ", " + date2.toString().split(" ")[5]);
            
            else if(type.equalsIgnoreCase("vehi_fixed_excel"))
            titleCell.setCellValue("RECONCILIATION OF FIXED ASSET OF Motor-Vehicle \nAs of " + monthName + " "
    					+ date2.getDate() + ", " + date2.toString().split(" ")[5]);
            
            else if(type.equalsIgnoreCase("equp_fixed_excel"))
            titleCell.setCellValue("RECONCILIATION OF FIXED ASSET OF Office-Equipment \nAs of " + monthName + " "
    					+ date2.getDate() + ", " + date2.toString().split(" ")[5]);
            
			Row row_0 = sheet.createRow(1);
			Cell cellt = row_0.createCell(4);
			cellt.setCellStyle(cs);
			CellUtil.setAlignment(cellt, HorizontalAlignment.CENTER);
			CellUtil.setVerticalAlignment(cellt, VerticalAlignment.CENTER);
			cellt.setCellValue(" R.M.S");
			
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
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_1.createCell(col);
				if (col == 2) {
					cell.setCellValue("Balance as per BFUB");
				} else if (col == 5) {
					cell.setCellValue(NumberFormat.getCurrencyInstance().format(conv).replace("$", ""));
					// cell.setCellValue(String.format("%,.2f", ats_ending_balance));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				if (col == 5)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}

			Row row_2 = sheet.createRow(4);
			row_2.setHeight((short) (row_2.getHeight()));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_2.createCell(col);
				cell.setCellStyle(cs);
				if (col == 2) {
					if(type.equalsIgnoreCase("fur_fixed_excel"))
						cell.setCellValue("Add:IFB 01A95 Account");
					else if(type.equalsIgnoreCase("comp_fixed_excel"))
						cell.setCellValue("Add:IFB 01A97 Account");
					else if(type.equalsIgnoreCase("vehi_fixed_excel"))
						cell.setCellValue("");
					else if(type.equalsIgnoreCase("equp_fixed_excel"))
						cell.setCellValue("Add:IFB 01A96 Account");
				}
				 else if (col == 5) {
						cell.setCellValue(NumberFormat.getCurrencyInstance().format(ifb).replace("$", ""));
						// cell.setCellValue(String.format("%,.2f", ats_ending_balance));
					}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				
				if (col == 5)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}
			Row row_22 = sheet.createRow(5);
			row_22.setHeight((short) (row_22.getHeight()));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_22.createCell(col);
				cell.setCellStyle(cs);
				if (col == 2) {
					cell.setCellValue("Total Con. and IFB");
				}
				 else if (col == 5) {
						cell.setCellValue(NumberFormat.getCurrencyInstance().format(conv+ifb).replace("$", ""));
						// cell.setCellValue(String.format("%,.2f", ats_ending_balance));
					}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				
				if (col == 5)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}
			int rowIdx = 6;
			for (Fixed__report data_ : _debit_data) {
				Row row = sheet.createRow(rowIdx++);
				row.setHeight((short) (row.getHeight()));
				
				Cell c1 = row.createCell(0);
				c1.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c1, VerticalAlignment.CENTER);
				CellUtil.setAlignment(c1, HorizontalAlignment.RIGHT);
				c1.setCellValue(data_.getTransaction_date());
				
				Cell c2 = row.createCell(1);
				c2.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c2, VerticalAlignment.CENTER);
				c2.setCellValue(data_.getAccount_description());
				
				Cell c3 = row.createCell(2);
				c3.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c3, VerticalAlignment.CENTER);
				c3.setCellValue(data_.getNaration());
				
				Cell c4 = row.createCell(3);
				c4.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
				c4.setCellValue(data_.getAccount_name());
				
				Cell c5 = row.createCell(4);
				c5.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
				CellUtil.setAlignment(c5, HorizontalAlignment.RIGHT);
				c5.setCellValue(data_.getAmount());
				
				Cell c6 = row.createCell(5);
				c6.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c6, VerticalAlignment.CENTER);
				// c5.setCellValue(data_.getID());
				c6.setCellValue("");
			}
			
			for (Fixed__report data_ : _credit_data) {
				Row row = sheet.createRow(rowIdx++);
				row.setHeight((short) (row.getHeight()));
				
				Cell c1 = row.createCell(0);
				c1.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c1, VerticalAlignment.CENTER);
				CellUtil.setAlignment(c1, HorizontalAlignment.RIGHT);
				c1.setCellValue(data_.getTransaction_date());
				
				Cell c2 = row.createCell(1);
				c2.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c2, VerticalAlignment.CENTER);
				c2.setCellValue(data_.getAccount_description());
				
				Cell c3 = row.createCell(2);
				c3.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c3, VerticalAlignment.CENTER);
				c3.setCellValue(data_.getNaration());
				
				Cell c4 = row.createCell(3);
				c4.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
				c4.setCellValue(data_.getAccount_name());
				
				Cell c5 = row.createCell(4);
				c5.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
				CellUtil.setAlignment(c5, HorizontalAlignment.RIGHT);
				c5.setCellValue(data_.getAmount());
				
				Cell c6 = row.createCell(5);
				c6.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c6, VerticalAlignment.CENTER);
				// c5.setCellValue(data_.getID());
				c6.setCellValue("");
			}

			Row row_3 = sheet.createRow(rowIdx);
			row_3.setHeight((short) (row_3.getHeight()));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_3.createCell(col);
			  if (col == 5) {
					cell.setCellValue(NumberFormat.getCurrencyInstance().format(total_).replace("$", ""));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				if (col == 5)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}

			rowIdx++;
			Row row_4 = sheet.createRow(rowIdx);
			row_4.setHeight((short) (row_4.getHeight()));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_4.createCell(col);
				cell.setCellStyle(cs);
				if (col == 2) {
					cell.setCellValue("Minus MMS Outstanding");
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
			}

			rowIdx++;
			for (Fixed_mms_report data_mms : mms_data) {
				Row row = sheet.createRow(rowIdx++);
				row.setHeight((short) (row.getHeight()));
				
				Cell c1 = row.createCell(0);
				c1.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c1, VerticalAlignment.CENTER);
				CellUtil.setAlignment(c1, HorizontalAlignment.RIGHT);
				c1.setCellValue(data_mms.getCreated_date());
				
				Cell c2 = row.createCell(1);
				c2.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c2, VerticalAlignment.CENTER);
				c2.setCellValue(data_mms.getAsset_description());
				
				Cell c3 = row.createCell(2);
				c3.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c3, VerticalAlignment.CENTER);
				c3.setCellValue(data_mms.getTag_number());
				
				Cell c4 = row.createCell(3);
				c4.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
				c4.setCellValue(data_mms.getBranch_name());
				
				Cell c5 = row.createCell(4);
				c5.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
				CellUtil.setAlignment(c5, HorizontalAlignment.RIGHT);
				c5.setCellValue(data_mms.getAmount());
				
				Cell c6 = row.createCell(5);
				c6.setCellStyle(style2);
				CellUtil.setVerticalAlignment(c6, VerticalAlignment.CENTER);
				// c5.setCellValue(data_.getID());
				c6.setCellValue("");
			}
			
//			for (Fixed_mms_report data_mms : mms_debit_data) {
//				Row row = sheet.createRow(rowIdx++);
//				row.setHeight((short) (row.getHeight()));
//				
//				Cell c1 = row.createCell(0);
//				c1.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c1, VerticalAlignment.CENTER);
//				CellUtil.setAlignment(c1, HorizontalAlignment.RIGHT);
//				c1.setCellValue(data_mms.getTransaction_date());
//				
//				Cell c2 = row.createCell(1);
//				c2.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c2, VerticalAlignment.CENTER);
//				c2.setCellValue(data_mms.getCategory_description());
//				
//				Cell c3 = row.createCell(2);
//				c3.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c3, VerticalAlignment.CENTER);
//				c3.setCellValue(data_mms.getReference());
//				
//				Cell c4 = row.createCell(3);
//				c4.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c4, VerticalAlignment.CENTER);
//				c4.setCellValue(data_mms.getAdditional_information());
//				
//				Cell c5 = row.createCell(4);
//				c5.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c5, VerticalAlignment.CENTER);
//				CellUtil.setAlignment(c5, HorizontalAlignment.RIGHT);
//				c5.setCellValue(data_mms.getAmount());
//				
//				Cell c6 = row.createCell(5);
//				c6.setCellStyle(style2);
//				CellUtil.setVerticalAlignment(c6, VerticalAlignment.CENTER);
//				// c5.setCellValue(data_.getID());
//				c6.setCellValue("");
//			}
			
			Row row_311 = sheet.createRow(rowIdx);
			row_311.setHeight((short) (row_311.getHeight()));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_311.createCell(col);
			  if (col == 5) {
					cell.setCellValue(NumberFormat.getCurrencyInstance().format(total_mms).replace("$", ""));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				if (col == 5)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}
			
			rowIdx++;
			Row row_6 = sheet.createRow(rowIdx);
			row_6.setHeight((short) (row_6.getHeight()));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_6.createCell(col);
				if (col == 2) {
					cell.setCellValue( "Adjusted BFUB Balance ");
				}
				if (col == 5) {
					cell.setCellValue(NumberFormat.getCurrencyInstance().format((conv+ifb+total_mms)-total_).replace("$", ""));
					}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				
				if (col == 5)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}

			rowIdx++;
			Row row_7 = sheet.createRow(rowIdx);
			row_7.setHeight((short) (row_7.getHeight()));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_7.createCell(col);
				if (col == 2) {
					cell.setCellValue( "Balance as per MMS ");
				}
				
				if (col == 5) {
					cell.setCellValue(NumberFormat.getCurrencyInstance().format(mms_balance).replace("$", ""));
					}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				
				if (col == 5)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}

			rowIdx++;
			Row row_8 = sheet.createRow(rowIdx);
			row_8.setHeight((short) (row_8.getHeight()));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_8.createCell(col);
				if (col == 2) {
					cell.setCellValue("Add: on waiting list");
				} 
				else if (col == 5) {
					cell.setCellValue(
							NumberFormat.getCurrencyInstance().format(waiting).replace("$", ""));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				if (col == 5)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}
			
			rowIdx++;
			Row row_88 = sheet.createRow(rowIdx);
			row_88.setHeight((short) (row_88.getHeight()));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_88.createCell(col);
				if (col == 2) {
					cell.setCellValue("Add: on disposed list");
				} 
				else if (col == 5) {
					cell.setCellValue(
							NumberFormat.getCurrencyInstance().format(disposed).replace("$", ""));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				if (col == 5)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}
			
			rowIdx++;
			Row row_888 = sheet.createRow(rowIdx);
			row_888.setHeight((short) (row_888.getHeight()));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_888.createCell(col);
				if (col == 2) {
					cell.setCellValue("Add: on removed list");
				} 
				else if (col == 5) {
					cell.setCellValue(
							NumberFormat.getCurrencyInstance().format(removed).replace("$", ""));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				if (col == 5)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}

			rowIdx++;
			Row row_9 = sheet.createRow(rowIdx);
			row_9.setHeight((short) (row_9.getHeight()));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_9.createCell(col);
				if (col == 2) {
					cell.setCellValue("Adjusted MMS Balance");
				} 
				else if (col == 5) {
					cell.setCellValue(
							NumberFormat.getCurrencyInstance().format(mms_balance+waiting+disposed+removed).replace("$", ""));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				if (col == 5)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}

			rowIdx++;
			Row row_10 = sheet.createRow(rowIdx);
			row_10.setHeight((short) (row_10.getHeight()));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_10.createCell(col);
				if (col == 2) {
					cell.setCellValue("Existing Diff between MMS");
				} 
				else if (col == 5) {
					cell.setCellValue(
							NumberFormat.getCurrencyInstance().format(((conv+ifb+total_mms)-total_)-(mms_balance+waiting+disposed+removed)).replace("$", ""));
				}
				cell.setCellStyle(style);
				CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
				if (col == 5)
					CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
			}

			rowIdx++;
			Row row_11 = sheet.createRow(rowIdx);
			row_11.setHeight((short) (row_11.getHeight()+400));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_11.createCell(col);
				if (col == 2) {
					cell.setCellValue( "Prepared By ");
					cell.setCellStyle(cs);
				}
				
				else if (col == 3) {
					cell.setCellValue( " _________________ ");
					cell.setCellStyle(cs);
				}
			}
			
			rowIdx++;
			Row row_12 = sheet.createRow(rowIdx);
			row_12.setHeight((short) (row_12.getHeight()+400));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_12.createCell(col);
				if (col == 2) {
					cell.setCellValue( "Checked By");
					cell.setCellStyle(cs);
				}
				else if (col == 3) {
					cell.setCellValue( " _________________");
					cell.setCellStyle(cs);
				}
			}
			
			rowIdx++;
			Row row_13 = sheet.createRow(rowIdx);
			row_13.setHeight((short) (row_13.getHeight()+400));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_13.createCell(col);
				if (col == 2) {
					cell.setCellValue( "Follow Up By");
					cell.setCellStyle(cs);
				}
				else if (col == 3) {
					cell.setCellValue( " _________________");
					cell.setCellStyle(cs);
				}
			}
			
			rowIdx++;
			Row row_14 = sheet.createRow(rowIdx);
			row_14.setHeight((short) (row_14.getHeight()+400));
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = row_14.createCell(col);
				if (col == 2) {
					cell.setCellValue( "Approved By");
					cell.setCellStyle(cs);
				}
				else if (col == 3) {
					cell.setCellValue( " _________________");
					cell.setCellStyle(cs);
				}
			}
			
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.setColumnWidth(0, 10 * 256);
			sheet.setColumnWidth(1, 16 * 256);
			sheet.setColumnWidth(2, 17 * 256);
			sheet.setColumnWidth(3, 17 * 256);
			sheet.setColumnWidth(4, 15 * 256);
			sheet.setColumnWidth(5, 15 * 256);

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}

}
