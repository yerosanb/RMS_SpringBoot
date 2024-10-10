package com.example.demo.user.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
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
import org.springframework.stereotype.Service;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.user.model.File_rtgs__ats;
import com.example.demo.utils.ExcelUtils;

@Service
public class UploadExcelService_rtgs__ats {
	// sheet name
	static String sheet_name = "ATS";
	// public static String TYPE =
	// "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	public static String TYPE = "text/csv,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	public static boolean isValidExcelFile(MultipartFile file) {
		if (!TYPE.equals(file.getContentType()))
			return false;
		return true;
	}


	//public static Map<String, Object> getAtsDataFromExcel(Long file_id, InputStream inputStream, Long date) {

	public static Map<String, Object> getAtsDataFromExcel(InputStream inputStream, Long date) {

		DecimalFormat df = new DecimalFormat("#");
		df.setMaximumFractionDigits(24);
		
		Map<String, Object> resp = new HashMap<>();
		List<File_rtgs__ats> ats = new ArrayList<>();
		Double beginningBallance = 0d;
		Double endingBallance = 0d;
		int totalCredit = 0;
		int totalDebit = 0;
		Double totalCreditAmount = 0d;
		Double totalDebitAmount = 0d;
		String businessDate = "";
		String accountNumber = "";
		try {
			Workbook workbook = new HSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheet(sheet_name);
			int rowIndex = 0;
			for (Row row : sheet) {
				if (rowIndex == 0 || rowIndex == 8) {
					rowIndex++;
					continue;
				} else if (rowIndex == 1) {
					Iterator<Cell> cellIterator = row.iterator();
					int cellIndex = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (cellIndex) {
							case 1 -> {
								System.out.println("value: " + ExcelUtils.toValue(cell));
								beginningBallance = Double.parseDouble(ExcelUtils.toValue(cell).substring(0, ExcelUtils.toValue(cell).length() - 3)
												.replace("\"", "").replace(",", "").replace(" ", "")
											.trim());
								// beginningBallance = (long) (Long
								// 	.parseLong(df.format(ExcelUtils.toValue(cell).substring(0, ExcelUtils.toValue(cell).length() - 3)
								// 				.replace("\"", "").replace(",", "")).replace(".", "")
								// 			.trim())
								// 	/ Math.pow(10, (df.format(ExcelUtils.toValue(cell).substring(0, ExcelUtils.toValue(cell).length() - 3)
								// 				.replace("\"", "").replace(",", "")).length() - 1
								// 			- df.format(ExcelUtils.toValue(cell).substring(0, ExcelUtils.toValue(cell).length() - 3)
								// 				.replace("\"", "").replace(",", "")).indexOf("."))));
								// beginningBallance = Float.parseFloat(
								// 		ExcelUtils.toValue(cell).substring(0, ExcelUtils.toValue(cell).length() - 3)
								// 				.replace("\"", "").replace(",", ""));
							}
							default -> {
							}
						}
						cellIndex++;
					}
				} else if (rowIndex == 2) {
					Iterator<Cell> cellIterator = row.iterator();
					int cellIndex = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (cellIndex) {
							case 1 -> {
								System.out.println("value: " + ExcelUtils.toValue(cell));
								endingBallance = Math.abs(Double.parseDouble(ExcelUtils.toValue(cell).substring(0, ExcelUtils.toValue(cell).length() - 3)

												.replace("\"", "").replace(",", "").replace(" ", "")

											.trim()));
								// endingBallance = (long) (Long
								// 	.parseLong(df.format(ExcelUtils.toValue(cell).substring(0, ExcelUtils.toValue(cell).length() - 3)
								// 				.replace("\"", "").replace(",", "")).replace(".", "")
								// 			.trim())
								// 	/ Math.pow(10, (df.format(ExcelUtils.toValue(cell).substring(0, ExcelUtils.toValue(cell).length() - 3)
								// 				.replace("\"", "").replace(",", "")).length() - 1
								// 			- df.format(ExcelUtils.toValue(cell).substring(0, ExcelUtils.toValue(cell).length() - 3)
								// 				.replace("\"", "").replace(",", "")).indexOf("."))));
							}
							default -> {
							}
						}
						cellIndex++;
					}
				} else if (rowIndex == 3) {
					Iterator<Cell> cellIterator = row.iterator();
					int cellIndex = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (cellIndex) {
							case 1 -> {
								System.out.println("value: " + ExcelUtils.toValue(cell));
								totalDebit = ExcelUtils.toValue(cell) == null ? 0
										: Integer.parseInt(ExcelUtils.toValue(cell));
							}

							default -> {
							}
						}
						cellIndex++;
					}
				} else if (rowIndex == 4) {
					Iterator<Cell> cellIterator = row.iterator();
					int cellIndex = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (cellIndex) {
							case 1 -> {
								System.out.println("value: " + ExcelUtils.toValue(cell));
								totalCredit = ExcelUtils.toValue(cell) == null ? 0
										: Integer.parseInt(ExcelUtils.toValue(cell));
							}
							default -> {
							}
						}
						cellIndex++;
					}
				} else if (rowIndex == 5) {
					Iterator<Cell> cellIterator = row.iterator();
					int cellIndex = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (cellIndex) {
							case 1 -> {
								System.out.println("value: " + ExcelUtils.toValue(cell));
								totalDebitAmount = Double.parseDouble(ExcelUtils.toValue(cell).substring(0, ExcelUtils.toValue(cell).length() - 3)
												.replace("\"", "").replace(",", "").replace(".", "")
											.trim());
								// totalDebitAmount = (long) (Long
								// 	.parseLong(df.format(ExcelUtils.toValue(cell).substring(0, ExcelUtils.toValue(cell).length() - 3)
								// 				.replace("\"", "").replace(",", "")).replace(".", "")
								// 			.trim())
								// 	/ Math.pow(10, (df.format(ExcelUtils.toValue(cell).substring(0, ExcelUtils.toValue(cell).length() - 3)
								// 				.replace("\"", "").replace(",", "")).length() - 1
								// 			- df.format(ExcelUtils.toValue(cell).substring(0, ExcelUtils.toValue(cell).length() - 3)
								// 				.replace("\"", "").replace(",", "")).indexOf("."))));
							}
							default -> {
							}
						}
						cellIndex++;
					}
				} else if (rowIndex == 6) {
					Iterator<Cell> cellIterator = row.iterator();
					int cellIndex = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (cellIndex) {
							case 1 -> accountNumber = ExcelUtils.toValue(cell);
							case 3 -> {
								System.out.println("value: " + ExcelUtils.toValue(cell));
								totalCreditAmount = Double.parseDouble(ExcelUtils.toValue(cell).substring(0, ExcelUtils.toValue(cell).length() - 3)
												.replace("\"", "").replace(",", "").replace(".", "")
											.trim());
								// totalCreditAmount = (long) (Long
								// 	.parseLong(df.format(ExcelUtils.toValue(cell).substring(0, ExcelUtils.toValue(cell).length() - 3)
								// 				.replace("\"", "").replace(",", "")).replace(".", "")
								// 			.trim())
								// 	/ Math.pow(10, (df.format(ExcelUtils.toValue(cell).substring(0, ExcelUtils.toValue(cell).length() - 3)
								// 				.replace("\"", "").replace(",", "")).length() - 1
								// 			- df.format(ExcelUtils.toValue(cell).substring(0, ExcelUtils.toValue(cell).length() - 3)
								// 				.replace("\"", "").replace(",", "")).indexOf("."))));
							}
							default -> {
							}
						}
						cellIndex++;
					}
				} else if (rowIndex == 7) {
					Iterator<Cell> cellIterator = row.iterator();
					int cellIndex = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (cellIndex) {
							case 2 -> {
								System.out.println("value: " + ExcelUtils.toValue(cell));
								businessDate = ExcelUtils.toValue(cell) == null ? null : ExcelUtils.toValue(cell);
							}
							default -> {
							}
						}
						cellIndex++;
					}
				} else {
					Iterator<Cell> cellIterator = row.iterator();
					int cellIndex = 0;
					File_rtgs__ats Ats = new File_rtgs__ats();
					boolean checker = true;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (cellIndex) {

							case 0 -> {
								System.out.println("first cell: " + ExcelUtils.toValue(cell));
								Ats.setValue_date_type(
										ExcelUtils.toValue(cell) == null ? "null" : ExcelUtils.toValue(cell).trim());
							}
							case 1 -> {
								System.out.println("second cell: " + ExcelUtils.toValue(cell));
								Ats.setReference(ExcelUtils.toValue(cell) == null ? "null"
										: ExcelUtils.toValue(cell).replace("null", "").trim());
							}
							case 2 ->
								Ats.setSender(
										ExcelUtils.toValue(cell) == null ? "null" : ExcelUtils.toValue(cell).trim());
							case 3 -> Ats.setAdditional_information(
									ExcelUtils.toValue(cell) == null ? "null" : ExcelUtils.toValue(cell).trim());
							case 4 -> {Ats.setAmount(
									ExcelUtils.toValue(cell) == null ? 0f : Double.parseDouble(ExcelUtils.toValue(cell)));
									System.out.println("amounts: " + ExcelUtils.toValue(cell));
								}
							case 5 -> {
								// if (!ExcelUtils.toValue(cell).equalsIgnoreCase("cr"))
								Ats.setDr_cr(
										ExcelUtils.toValue(cell) == null ? "null" : ExcelUtils.toValue(cell).trim());
								// else
								// checker = false;
							}
							default -> {
							}

						}
						cellIndex++;
					}
					Ats.setAvailability("1".trim());
					Ats.setMatch_status("0".trim());
					Ats.setStatus("1".trim());
					Ats.setUpload_date(date);
					if (Ats.getSender() != null) {
						String temp = Ats.getSender();
						Ats.setReceiver(Ats.getSender()
								.substring(Ats.getSender().indexOf(':', 3) + 2, Ats.getSender().length()).trim());
						Ats.setSender(temp.substring(3, temp.indexOf(':', 3) - 2).trim());
					}
					//Ats.setFile_id(file_id);

					if (checker)
						ats.add(Ats);
				}
				rowIndex++;
			}

			System.out.println("beginningBallance: " + beginningBallance);
			System.out.println("endingBallance : " + endingBallance);
			System.out.println("totalCredit : " + totalCredit);
			System.out.println("totalDebit : " + totalDebit);
			System.out.println("totalCreditAmount : " + totalCreditAmount);
			System.out.println("totalDebitAmount : " + totalDebitAmount);
			System.out.println("businessDate : " + businessDate);
			System.out.println("accountNumber : " + accountNumber);

			resp.put("beginningBallance", beginningBallance);
			resp.put("endingBallance", endingBallance);
			resp.put("totalCredit", totalCredit);
			resp.put("totalDebit", totalDebit);
			resp.put("totalCreditAmount", totalCreditAmount);
			resp.put("totalDebitAmount", totalDebitAmount);
			resp.put("businessDate", businessDate);
			resp.put("accountNumber", accountNumber);
			resp.put("data", ats);

			workbook.close();
		} catch (IOException ex) {
			// System.out.println("hhhhhhhhhheeeeeeeeeeeerrrrrrrrrrrrrrrreeeeeeeeeeeeeee:
			// ");
			// throw new ExceptionsList(ex);
			ex.printStackTrace();
		}
		return resp;
	}

}