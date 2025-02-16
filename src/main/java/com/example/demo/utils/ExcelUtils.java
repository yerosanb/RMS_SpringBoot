package com.example.demo.utils;

import static java.time.ZoneOffset.UTC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.LocaleUtil;
import org.molgenis.data.MolgenisDataException;
import org.molgenis.data.excel.ExcelFileExtensions;
import org.molgenis.data.file.processor.AbstractCellProcessor;
import org.molgenis.data.file.processor.CellProcessor;
import org.springframework.stereotype.Component;

@Component
public class ExcelUtils {
	private ExcelUtils() {}
	
	  public static String toValue(Cell cell) {
	    return toValue(cell, null);
	  }

	  // Gets a cell value as String and process the value with the given cellProcessors
	  static String toValue(Cell cell, List<CellProcessor> cellProcessors) {
	    String value;
	    switch (cell.getCellTypeEnum()) {
	      case BLANK:
	        value = null;
	        break;
	      case STRING:
	        value = cell.getStringCellValue();
	        break;
	      case NUMERIC:
	        if (DateUtil.isCellDateFormatted(cell)) {
	          try {
	            // Excel dates are LocalDateTime, stored without timezone.
	            // Interpret them as UTC to prevent ambiguous DST overlaps which happen in other
	            // timezones.
	            LocaleUtil.setUserTimeZone(LocaleUtil.TIMEZONE_UTC);
	            Date dateCellValue = cell.getDateCellValue();
	            value = formatUTCDateAsLocalDateTime(dateCellValue);
	          } finally {
	            LocaleUtil.resetUserTimeZone();
	          }
	        } else {
	          // excel stores integer values as double values
	          // read an integer if the double value equals the
	          // integer value
	          double x = cell.getNumericCellValue();
	          if (x == Math.rint(x) && !Double.isNaN(x) && !Double.isInfinite(x))
	            value = String.valueOf((long) x);
	          else value = String.valueOf(x);
	        }
	        break;
	      case BOOLEAN:
	        value = String.valueOf(cell.getBooleanCellValue());
	        break;
	      case FORMULA:
	        // evaluate formula
	        FormulaEvaluator evaluator =
	            cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
	        CellValue cellValue;
	        try {
	          cellValue = evaluator.evaluate(cell);
	        } catch (StackOverflowError e) {
	          throw new RuntimeException(
	              "Error evaluating formula, possibly due to deep formula nesting.");
	        }
	        switch (cellValue.getCellTypeEnum()) {
	          case BOOLEAN:
	            value = String.valueOf(cellValue.getBooleanValue());
	            break;
	          case NUMERIC:
	            if (DateUtil.isCellDateFormatted(cell)) {
	              try {
	                // Excel dates are LocalDateTime, stored without timezone.
	                // Interpret them as UTC to prevent ambiguous DST overlaps which happen in other
	                // timezones.
	                LocaleUtil.setUserTimeZone(LocaleUtil.TIMEZONE_UTC);
	                Date javaDate = DateUtil.getJavaDate(cellValue.getNumberValue(), false);
	                value = formatUTCDateAsLocalDateTime(javaDate);

	              } finally {
	                LocaleUtil.resetUserTimeZone();
	              }
	            } else {
	              // excel stores integer values as double values
	              // read an integer if the double value equals the
	              // integer value
	              double x = cellValue.getNumberValue();
	              if (x == Math.rint(x) && !Double.isNaN(x) && !Double.isInfinite(x))
	                value = String.valueOf((long) x);
	              else value = String.valueOf(x);
	            }
	            break;
	          case STRING:
	            value = cellValue.getStringValue();
	            break;
	          case BLANK:
	            value = null;
	            break;
	          default:
	            throw new MolgenisDataException(
	                "unsupported cell type: " + cellValue.getCellTypeEnum());
	        }
	        break;
	      default:
	        throw new MolgenisDataException("unsupported cell type: " + cell.getCellTypeEnum());
	    }

	    return AbstractCellProcessor.processCell(value, false, cellProcessors);
	  }

	public static void renameSheet(String newSheetname, File file, int index) {
	    try (FileInputStream fis = new FileInputStream(file);
	      Workbook workbook = WorkbookFactory.create(fis)) {
	      workbook.setSheetName(index, newSheetname);
	      workbook.write(new FileOutputStream(file));

	    } catch (Exception e) {
	      throw new MolgenisDataException(e);
	    }
	  }

	 public static int getNumberOfSheets(File file) {
	    if (!isExcelFile(file.getName())) return -1;
	    try (FileInputStream fis = new FileInputStream(file);
	        Workbook workbook = WorkbookFactory.create(fis)) {
	      return workbook.getNumberOfSheets();
	    } catch (Exception e) {
	      throw new MolgenisDataException(e);
	    }
	  }

	 public static boolean isExcelFile(String filename) {
	    String extension = FilenameUtils.getExtension(filename);
	    return ExcelFileExtensions.getExcel().contains(extension);
	  }

	  /**
	   * Formats parsed Date as LocalDateTime string at zone UTC to express that we don't know the
	   * timezone.
	   *
	   * @param javaDate Parsed Date representing start of day in UTC
	   * @return Formatted {@link LocalDateTime} string of the java.util.Date
	   */
	  private static String formatUTCDateAsLocalDateTime(Date javaDate) {
	    // Now back from start of day in UTC to LocalDateTime to express that we don't know the
	    // timezone.
	     LocalDateTime localDateTime = javaDate.toInstant().atZone(UTC).toLocalDateTime();
	    // And format to string
	     
	    return localDateTime.toString();
	  }
}
