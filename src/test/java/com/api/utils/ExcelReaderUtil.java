package com.api.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFAnchor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtil {

	public static void main(String[] args) throws IOException {

		// Apache POI OOXML LIB
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData/PhoenixTestData.xlsx");
		XSSFWorkbook myWorkbook = new XSSFWorkbook(is);
		XSSFSheet mySheet = myWorkbook.getSheet("LoginTestData");
		
		XSSFRow myRow;
		XSSFCell myCell;
		/*
		 * XSSFRow myRow=mySheet.getRow(1); XSSFCell myCell=myRow.getCell(0);
		 * 
		 * System.out.println(myCell.getStringCellValue());
		 */
		int lastRowIndex = mySheet.getLastRowNum();
		System.out.println(lastRowIndex);
		XSSFRow rowHeader = mySheet.getRow(0);
		int lastIndexofCol = rowHeader.getLastCellNum() - 1;// return the total number of columns
		System.out.println(lastIndexofCol);
		
		for(int rowIndex=1; rowIndex<=lastRowIndex;rowIndex++) {
			
			for(int colIndex=0; colIndex<=lastIndexofCol; colIndex++) {
				
				//System.out.println(rowIndex + " "+colIndex);
				myRow= mySheet.getRow(rowIndex);
				myCell=myRow.getCell(colIndex);
				System.out.print(myCell + " ");
			}
		}
	}

}
