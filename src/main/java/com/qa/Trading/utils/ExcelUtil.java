package com.qa.Trading.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	private static final String TEST_DATA_SHEET_PATH = "./src/test/resource/TestData/TestData.xlsx";
	
	private static Workbook book;
	private static Sheet sheet;
	
	
	public static Object[][] getTestData(String sheetName) {
		Object data[][] = null;
		try {
			FileInputStream ips = new FileInputStream(TEST_DATA_SHEET_PATH);
			book = WorkbookFactory.create(ips);
			System.out.println("Number of sheets: " + book.getNumberOfSheets());
			
			for (int i = 0; i < book.getNumberOfSheets(); i++) {
			    System.out.println("Sheet found: [" + book.getSheetName(i) + "]");
			}

			System.out.println("Requested sheet: [" + sheetName + "]");

			sheet = book.getSheet(sheetName);
			System.out.println("Sheet object: " + sheet);
			
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i=0;i<sheet.getLastRowNum();i++) {
				for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
