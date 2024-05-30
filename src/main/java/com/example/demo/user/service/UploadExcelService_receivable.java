package com.example.demo.user.service;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.user.model.File_rtgs_awb_core;
import com.example.demo.utils.ExcelUtils;
import com.example.demo.utils.Utils;

public class UploadExcelService_receivable {
	@Autowired
	private Utils util;

	// sheet name
	static String sheet_name = "Receivable";
	// public static String TYPE =
	// "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	public static String TYPE = "text/csv,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	public static boolean isValidExcelFile(MultipartFile file) {
		if (!TYPE.equals(file.getContentType()))
			return false;
		return true;
	}

	//public static Map<String, Object> getAtsDataFromExcel(Long file_id, InputStream inputStream, Long date) {

	public static Map<String, Object> getAtsDataFromExcel( InputStream inputStream, Long date) {
		
		DecimalFormat df = new DecimalFormat("#");
		df.setMaximumFractionDigits(24);

		Map<String, Object> resp = new HashMap<>();
		List<File_rtgs_awb_core> awb_core_all = new ArrayList<>();
		Double beginningBallance = 0d;
		Double endingBallance= 0d;
		int totalCredit = 0;
		int totalDebit = 0;
		Double totalCreditAmount= 0d;
		Double totalDebitAmount = 0d;
		String businessDate = "";
		String accountNumber = "";
		boolean con = true;
		try {
			Workbook workbook = new HSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheet(sheet_name);
			int rowIndex = 0;
			for (Row row : sheet) {
				if (rowIndex == 0) {
					rowIndex++;
					continue;
				} else if (rowIndex == 1) {
					Iterator<Cell> cellIterator = row.iterator();
					int cellIndex = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (cellIndex) {
						case 1 -> {
							System.out.println("value: " + String.valueOf(cell.getNumericCellValue()));
							beginningBallance = cell.getNumericCellValue();
							// beginningBallance_con = (long) (Long
							// .parseLong(df.format(cell.getNumericCellValue()).replace(".", "")
							// .trim())
							// / Math.pow(10, (df.format(cell.getNumericCellValue()).length() - 1
							// - df.format(cell.getNumericCellValue()).indexOf("."))));
						}
						default -> {
						}
						}
						cellIndex++;
					}
				} else {
					Iterator<Cell> cellIterator = row.iterator();
					int cellIndex = 0;
					File_rtgs_awb_core awb_core = new File_rtgs_awb_core();
					boolean checker = true;
					boolean last = true;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (cellIndex) {

						case 0 -> {
							String value = ExcelUtils.toValue(cell);
							if (value != null && value.equals("BBF:")) {
								con = false;
								checker = false;
							} else if (value == null) {
								checker = false;
								last = false;
							} else {
								LocalDateTime datetime = LocalDateTime.parse(ExcelUtils.toValue(cell));
								awb_core.setPosting_date(datetime.getYear()
										+ (String.valueOf(datetime.getMonthValue()).length() == 1
												? "0" + datetime.getMonthValue()
												: String.valueOf(datetime.getMonthValue()))
										+ (String.valueOf(datetime.getDayOfMonth()).length() == 1
												? "0" + datetime.getDayOfMonth()
												: String.valueOf(datetime.getDayOfMonth())));
							}
						}
						case 1 -> {
							if (!checker) {
								beginningBallance= cell.getNumericCellValue();
								System.out.println("beginning balance of ifb"+beginningBallance);
								cellIndex++;
								continue;
							}
							LocalDateTime datetime = LocalDateTime.parse(ExcelUtils.toValue(cell));

							awb_core.setValue_date(datetime.getYear()
									+ (String.valueOf(datetime.getMonthValue()).length() == 1
											? "0" + datetime.getMonthValue()
											: String.valueOf(datetime.getMonthValue()))
									+ (String.valueOf(datetime.getDayOfMonth()).length() == 1
											? "0" + datetime.getDayOfMonth()
											: String.valueOf(datetime.getDayOfMonth())));
						}
						case 2 -> {
							if (!checker)
								continue;
							awb_core.setAdditional_information(
									ExcelUtils.toValue(cell) == null ? "null" : ExcelUtils.toValue(cell).trim());
						}
						case 3 -> {
							if (!checker)
								continue;
							awb_core.setTransaction_reference(
									ExcelUtils.toValue(cell) == null ? "null" : ExcelUtils.toValue(cell).trim());
						}
						case 4 -> {
							if (!checker)
								continue;
							awb_core.setBranch_code(
									ExcelUtils.toValue(cell) == null ? "null" : ExcelUtils.toValue(cell).trim());
						}
						case 5 -> {
							if (!last) {
								totalDebitAmount = cell.getNumericCellValue();
								cellIndex++;
							}
							if (!checker)
								continue;
							if (Float.parseFloat(ExcelUtils.toValue(cell)) != 0.00) {
								if (con)
									totalDebit++;
								
								if (con)
									totalDebitAmount += (float) (Long
											.parseLong(df.format(cell.getNumericCellValue()).replace(".", "").trim())
											/ Math.pow(10, (df.format(cell.getNumericCellValue()).length() - 1
													- df.format(cell.getNumericCellValue()).indexOf("."))));
								awb_core.setDr_cr("DR".trim());
								awb_core.setAmount(Double.parseDouble(String.valueOf(cell.getNumericCellValue())));
							}
						}
						case 6 -> {
							if (!last) {
								totalCreditAmount = cell.getNumericCellValue();
								cellIndex++;
							}
							if (!checker)
								continue;
							if (Float.parseFloat(String.valueOf(cell.getNumericCellValue())) != 0.00) {
								if (con)
									totalCredit++;
								if (con)
									totalCreditAmount += (long) (Long
											.parseLong(df.format(cell.getNumericCellValue()).replace(".", "").trim())
											/ Math.pow(10, (df.format(cell.getNumericCellValue()).length() - 1
													- df.format(cell.getNumericCellValue()).indexOf("."))));
								awb_core.setDr_cr("CR".trim());
								awb_core.setAmount(Double.parseDouble(String.valueOf(cell.getNumericCellValue())));
							}
						}
						case 7 -> {
							if (!checker)
								continue;
							// System.out.println("type: " + cell.getCellTypeEnum());
							// System.out.println("value: " + ExcelUtils.toValue(cell));
							awb_core.setBalance(Float.valueOf(String.valueOf(cell.getNumericCellValue())));
							if (con)

								endingBallance = cell.getNumericCellValue();
							// endingBallance_con = (long) (Long
							// .parseLong(df.format(cell.getNumericCellValue()).replace(".", "")
							// .trim())
							// / Math.pow(10, (df.format(cell.getNumericCellValue()).length() - 1
							// - df.format(cell.getNumericCellValue()).indexOf("."))));
						}

						default -> {
						}

						}
						cellIndex++;
					}
					awb_core.setAvailability("1".trim());
					awb_core.setMatch_status("0".trim());
					awb_core.setStatus("1".trim());
					awb_core.setUpload_date(date);

					//awb_core.setFile_id(file_id);


					if (checker)
						awb_core_all.add(awb_core);
					checker = true;
					last = true;
				}
				rowIndex++;
			}

			System.out.println("=====================================");
		
			System.out.println("beginningBallance: " + beginningBallance);
			System.out.println("endingBallance : " + endingBallance);
			System.out.println("totalCredit : " + totalCredit);
			System.out.println("totalDebit : " + totalDebit);
			System.out.println("totalCreditAmount : " + totalCreditAmount);
			System.out.println("totalDebitAmount : " + totalDebitAmount);

			// resp.put("businessDate", businessDate);
			// resp.put("accountNumber", accountNumber);
			resp.put("data", awb_core_all);
			resp.put("beginningBallance", beginningBallance);
			resp.put("endingBallance", endingBallance);
			resp.put("totalCredit", totalCredit);
			resp.put("totalDebit", totalDebit);
			resp.put("totalCreditAmount", totalCreditAmount);
			resp.put("totalDebitAmount", totalDebitAmount);
			resp.put("the_new_b_b", beginningBallance);
			// resp.put("totalDebitAmount_ifb", totalDebitAmount_ifb);

			workbook.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			// throw new ExceptionsList(ex);
		}
		return resp;
	}

}
