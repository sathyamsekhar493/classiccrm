package framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

public class ExcelUtils {
	
	public static HashMap<String, Object> readExcelData(String filePath,String sheetName, String testCaseId) {
		
		HashMap<String, Object> tcData = new HashMap<String, Object>();
		try {
			XSSFWorkbook wb = getWorkBook(filePath);
			XSSFSheet dataSheet = getWorkSheet(wb, sheetName);
			
			XSSFRow testCaseRow = dataSheet.getRow(get_tc_row_number(dataSheet, testCaseId));
			XSSFRow headerRow = dataSheet.getRow(0);
			int totalCols = headerRow.getLastCellNum();
			
			for (int cellNum = 0; cellNum <= totalCols; cellNum++) {
				String header = getCellValue(headerRow.getCell(cellNum)).toString();
				Object value = getCellValue(testCaseRow.getCell(cellNum));
				tcData.put(header, value);
			}
			
			
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
		
		return tcData;
		
	}
	
	
	private static Object getCellValue(XSSFCell cell) {
		Object val = "";
		
		if (cell == null) {
			val = "";			
		} else {			
			CellType cellType=	cell.getCellTypeEnum();
			
			switch (cellType) {
			case STRING:
				val = cell.getStringCellValue();
				break;
				
			case NUMERIC:
				val = cell.getNumericCellValue();
				break;
				
			case BOOLEAN:
				val = cell.getBooleanCellValue();
				break;
							
			default:
				if (DateUtil.isCellDateFormatted(cell)) {
					val = cell.getDateCellValue();
				}else {
					val = "";
				}
				
				break;
			}
		}
		return val;
		
	}
	
	
	private static int get_tc_row_number(XSSFSheet sheet, String tcId) {
		int tcRowIndex = -1;
		int lastRow = sheet.getLastRowNum();
		int colIndexForTC = get_column_number_by_name(sheet, "TC_ID");
		
		for (int rowIndex = 1; rowIndex <=lastRow; rowIndex++) {
			XSSFRow row = sheet.getRow(rowIndex);
			if (row != null) {
				XSSFCell cell = row.getCell(colIndexForTC);
				String actTCId = cell.getStringCellValue();
				if (actTCId.equalsIgnoreCase(tcId)) {
					tcRowIndex = rowIndex;
					break;
				}
				
			}
		}
		
		if (tcRowIndex == -1) {
			Assert.assertTrue(false, "Sheet : " + sheet.getSheetName()+ " does not contain the test case : " +tcId);
		}
		
		return tcRowIndex;
		
	}
	
	private static int get_column_number_by_name(XSSFSheet sheet,String columnHeader) {
		int cellIndex = -1;
		
		XSSFRow headerRow = sheet.getRow(0);
		if (headerRow != null) {
			int totalCells = headerRow.getLastCellNum();
			for (int cellNum = 0; cellNum <= totalCells; cellNum++) {
				XSSFCell cell = headerRow.getCell(cellNum);
				if (cell != null) {
					if (cell.getStringCellValue().equalsIgnoreCase(columnHeader)) {
						cellIndex = cellNum;
						break;
					}
				}
			}
			
			if (cellIndex == -1) {
				Assert.assertTrue(false, "Sheet : " + sheet.getSheetName()+ " does not contain the column : " +columnHeader);
			}
		} else {
			Assert.assertTrue(false, "Sheet : " + sheet.getSheetName()+ " does not contain the header row.");
		}
		
		return cellIndex;
	}
	
	private static XSSFSheet getWorkSheet(XSSFWorkbook wb,String sheetName) {
		XSSFSheet sheet = null;
		int sheetIndex = wb.getSheetIndex(sheetName);
		
		if (sheetIndex >= 0) {
			sheet = wb.getSheetAt(sheetIndex);
			
		} else {
			Assert.assertTrue(false, "Data sheet : " + sheetName + " is not found in data file: " );
		}
		
		return sheet;
	}
	
	private static XSSFWorkbook getWorkBook(String filePath) throws IOException {
		Data.logger.trace("Entering into the Getting Workbook");
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis); 
		if (UtilityMethods.check_file_exists(filePath)) {
		
			Data.logger.info("File Exist");
		} else {
			Data.logger.info("Workbook filepath was not found: "+filePath);
			Assert.assertTrue(false, "The data file : " + filePath + " does not exist.");
		}
		Data.logger.trace("Succesfully get the Workbook Data");
		return workbook;
		
	}
}
