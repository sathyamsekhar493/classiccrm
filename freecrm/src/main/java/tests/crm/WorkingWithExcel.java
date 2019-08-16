package tests.crm;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class WorkingWithExcel {

	public static void main(String[] args) {
		// ReadBasicExcel();
		ReadMainRowData("EMPID");
		ReadMainRowData("EI103");
		//ReadSelectedRow("RAJU");

	}
	@Test
	public static void ReadBasicExcel() {
		try {
			FileInputStream fis = new FileInputStream("C:\\Users\\Gourav Singh\\git\\classiccrm\\freecrm\\TestData\\Data.xlsx");
			XSSFWorkbook w = new XSSFWorkbook(fis);
			XSSFSheet st = w.getSheet("Sheet1");
			int usedrows = st.getPhysicalNumberOfRows();
			for (int row = 0; row <= usedrows - 1; row++) {
				XSSFRow r = st.getRow(row);
				int usedcolumns = r.getPhysicalNumberOfCells();
				for (int cell = 0; cell <= usedcolumns - 1; cell++) {
					XSSFCell cl = r.getCell(cell);
					String cellValue = cl.getStringCellValue();
					System.out.print(cellValue + " : ");
				}
				System.out.println();
			}
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void ReadMainRowData(String RowValue) {
		int rno = ReadSelectedRow(RowValue);
		System.out.println();
		try {
			FileInputStream fis = new FileInputStream("F:\\Dummy\\sampleproject\\TestData\\Data.xlsx");
			XSSFWorkbook w = new XSSFWorkbook(fis);
			XSSFSheet st = w.getSheet("Sheet1");
			XSSFRow r = st.getRow(rno);
			int cls = r.getLastCellNum();
			for (int j = 0; j <= cls-1; j++) {
				String str = r.getCell(j).getStringCellValue();

				System.out.print(str + "  ");

			}
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
@Test
	public static int ReadSelectedRow(String rowValue) {
		int rowFound = -1;
		try {
			FileInputStream fis = new FileInputStream("F:\\Dummy\\sampleproject\\TestData\\Data.xlsx");
			XSSFWorkbook w = new XSSFWorkbook(fis);
			XSSFSheet st = w.getSheet("Sheet1");
			int usedrows = st.getPhysicalNumberOfRows();
			for (int row = 0; row <= usedrows; row++) {
				
				String va = st.getRow(row).getCell(0).getStringCellValue();
				if (va.equals(rowValue)) {
					rowFound = row;
					//System.out.println("Given String " + rowValue + " is found at Row number: " + row);  //It Gives The Row Index Number
					break;
				}
			}
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rowFound;
	}

	
}
