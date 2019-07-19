package tests.crm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class WriteExcelFile {
	public void writeExcel(String filePath, String sheetName, String[] dataToWrite) throws IOException {

		File file = new File(filePath);
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook Workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = Workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		Row row = sheet.getRow(0);
		Row newRow = sheet.createRow(rowCount + 1);
		for (int j = 0; j < row.getLastCellNum(); j++) {

			Cell cell = newRow.createCell(j);
			cell.setCellValue(dataToWrite[j]);
		}
		inputStream.close();
		FileOutputStream outputStream = new FileOutputStream(file);
		Workbook.write(outputStream);
		Workbook.close();
		outputStream.close();
	}

	public static void main(String... strings) throws IOException {
		String[] valueToWrite = { "Mr. E", "Noida" };
		WriteExcelFile objExcelFile = new WriteExcelFile();
		objExcelFile.writeExcel("F:\\Dummy\\sampleproject\\TestData\\writeexcel.xlsx","ExcelGuru99Demo",valueToWrite);
	}
}
