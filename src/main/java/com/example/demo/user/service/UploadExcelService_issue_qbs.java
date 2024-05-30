package com.example.demo.user.service;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
//import org.molgenis.data.excel.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.user.model.File_rtgs_awb_core;
import com.example.demo.utils.ExcelUtils;
import com.example.demo.utils.Utils;

@Service
public class UploadExcelService_issue_qbs {

	@Autowired
	private Utils util;

	// sheet name
	static String sheet_name = "QBS";
	// public static String TYPE =
	// "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	public static String TYPE = "text/csv,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	public static boolean isValidExcelFile(MultipartFile file) {
		if (!TYPE.equals(file.getContentType()))
			return false;
		return true;
	}

	// public static Map<String, Object> getAtsDataFromExcel(Long file_id,
	// InputStream inputStream, Long date) {

	public static Map<String, Object> getAtsDataFromExcel(InputStream inputStream, Long date) {

		DecimalFormat df = new DecimalFormat("#");
		df.setMaximumFractionDigits(24);

		Map<String, Object> resp = new HashMap<>();
		List<File_rtgs_awb_core> issue_core_all = new ArrayList<>();
		Double beginningBallance_ifb = 0d;
		Double endingBallance_ifb = 0d;
		int totalCredit_ifb = 0;
		int totalDebit_ifb = 0;
		Double totalCreditAmount_ifb = 0d;
		Double totalDebitAmount_ifb = 0d;

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
							beginningBallance_ifb = Double.parseDouble(ExcelUtils.toValue(cell).replace(" CR", "").replace(",", ""));
						}
						default -> {
						}
						}
						cellIndex++;
					}
				} else {
					Iterator<Cell> cellIterator = row.iterator();
					int cellIndex = 0;
					File_rtgs_awb_core issue_core = new File_rtgs_awb_core();
					boolean last = false;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (cellIndex) {

						case 0 -> {
							if (ExcelUtils.toValue(cell) != null && ExcelUtils.toValue(cell).trim() != "" && !ExcelUtils.toValue(cell).isBlank()) {
								if (String.valueOf(ExcelUtils.toValue(cell)).contains("T")) {
									LocalDateTime datetime = LocalDateTime.parse(ExcelUtils.toValue(cell));
									issue_core.setPosting_date(datetime.getYear()
											+ (String.valueOf(datetime.getMonthValue()).length() == 1
													? "0" + datetime.getMonthValue()
													: String.valueOf(datetime.getMonthValue()))
											+ (String.valueOf(datetime.getDayOfMonth()).length() == 1
													? "0" + datetime.getDayOfMonth()
													: String.valueOf(datetime.getDayOfMonth())));
								} else {
									DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd-MMM-uuuu");
									LocalDate datetime = LocalDate.parse(ExcelUtils.toValue(cell), dTF);
									issue_core.setPosting_date(datetime.getYear()
											+ (String.valueOf(datetime.getMonthValue()).length() == 1
													? "0" + datetime.getMonthValue()
													: String.valueOf(datetime.getMonthValue()))
											+ (String.valueOf(datetime.getDayOfMonth()).length() == 1
													? "0" + datetime.getDayOfMonth()
													: String.valueOf(datetime.getDayOfMonth())));
								}

							} else {
								last = true;
								break;
							}
						}
						case 1 -> {
							if (last)
								break;
							if (ExcelUtils.toValue(cell) != null && ExcelUtils.toValue(cell).trim() != "" && !ExcelUtils.toValue(cell).isBlank()) {
								if (String.valueOf(ExcelUtils.toValue(cell)).contains("T")) {
									LocalDateTime datetime = LocalDateTime.parse(ExcelUtils.toValue(cell));
									issue_core.setValue_date(datetime.getYear()
											+ (String.valueOf(datetime.getMonthValue()).length() == 1
													? "0" + datetime.getMonthValue()
													: String.valueOf(datetime.getMonthValue()))
											+ (String.valueOf(datetime.getDayOfMonth()).length() == 1
													? "0" + datetime.getDayOfMonth()
													: String.valueOf(datetime.getDayOfMonth())));
								} else {
									DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd-MMM-uuuu");
									LocalDate datetime = LocalDate.parse(ExcelUtils.toValue(cell), dTF);
									issue_core.setValue_date(datetime.getYear()
											+ (String.valueOf(datetime.getMonthValue()).length() == 1
													? "0" + datetime.getMonthValue()
													: String.valueOf(datetime.getMonthValue()))
											+ (String.valueOf(datetime.getDayOfMonth()).length() == 1
													? "0" + datetime.getDayOfMonth()
													: String.valueOf(datetime.getDayOfMonth())));
								}
							}
						}
						case 2 -> {
							if (last)
								break;
							if (ExcelUtils.toValue(cell) != null && ExcelUtils.toValue(cell).trim() != "" && !ExcelUtils.toValue(cell).isBlank()) {
								issue_core.setBranch(
										ExcelUtils.toValue(cell) == null ? "null" : ExcelUtils.toValue(cell).trim());
							}
						}
						case 3 -> {

							if (last)
								break;
							if (ExcelUtils.toValue(cell) != null && ExcelUtils.toValue(cell).trim() != "" && !ExcelUtils.toValue(cell).isBlank()) {
								issue_core.setAdditional_information(
										ExcelUtils.toValue(cell) == null ? "null" : ExcelUtils.toValue(cell).trim());
							}
						}
						case 4 -> {
							if (last)
								break;
							if (ExcelUtils.toValue(cell) != null && ExcelUtils.toValue(cell).trim() != "" && !ExcelUtils.toValue(cell).isBlank()) {
								if (Float.parseFloat(ExcelUtils.toValue(cell)) != 0.00) {
									totalDebit_ifb++;
									totalDebitAmount_ifb += cell.getNumericCellValue();
									issue_core.setDr_cr("DR".trim());
									issue_core
											.setAmount(Double.parseDouble(String.valueOf(cell.getNumericCellValue())));
								}
							}
						}
						case 5 -> {
							if (last)
								break;
							if (ExcelUtils.toValue(cell) != null && ExcelUtils.toValue(cell).trim() != "" && !ExcelUtils.toValue(cell).isBlank()) {
								if (Float.parseFloat(String.valueOf(cell.getNumericCellValue())) != 0.00) {
									totalCredit_ifb++;
									totalCreditAmount_ifb += cell.getNumericCellValue();
									issue_core.setDr_cr("CR".trim());
									issue_core
											.setAmount(Double.parseDouble(String.valueOf(cell.getNumericCellValue())));
								}
							}
						}
						case 6 -> {
							if (last)
								break;
							if (ExcelUtils.toValue(cell) != null && ExcelUtils.toValue(cell).trim() != "" && !ExcelUtils.toValue(cell).isBlank()) {
								issue_core.setBalance(Float.valueOf(String.valueOf(cell.getNumericCellValue())));
								endingBallance_ifb = cell.getNumericCellValue();
							}
						}
						default -> {
						}

						}
						if (last)
							break;
						cellIndex++;
					}
					issue_core.setAvailability("1".trim());
					issue_core.setMatch_status("0".trim());
					issue_core.setStatus("1".trim());
					issue_core.setUpload_date(date);

					if (!last)
						issue_core_all.add(issue_core);
				}
				rowIndex++;
			}

			System.out.println("=====================================");
			System.out.println("beginningBallance: " + beginningBallance_ifb);
			System.out.println("endingBallance : " + endingBallance_ifb);
			System.out.println("totalCredit : " + totalCredit_ifb);
			System.out.println("totalDebit : " + totalDebit_ifb);
			System.out.println("totalCreditAmount : " + totalCreditAmount_ifb);
			System.out.println("totalDebitAmount : " + totalDebitAmount_ifb);

			// resp.put("businessDate", businessDate);
			// resp.put("accountNumber", accountNumber);
			resp.put("data", issue_core_all);
			resp.put("beginningBallance_ifb: ", beginningBallance_ifb);
			resp.put("endingBallance_ifb", endingBallance_ifb);
			resp.put("totalCredit_ifb", totalCredit_ifb);
			resp.put("totalDebit_ifb", totalDebit_ifb);
			resp.put("totalCreditAmount_ifb", totalCreditAmount_ifb);
			resp.put("totalDebitAmount_ifb", totalDebitAmount_ifb);
			resp.put("the_new_b_b_ifb", beginningBallance_ifb);
			// resp.put("totalDebitAmount_ifb", totalDebitAmount_ifb);

			workbook.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			// throw new ExceptionsList(ex);
		}
		return resp;
	}

}