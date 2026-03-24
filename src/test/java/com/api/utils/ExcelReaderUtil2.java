package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFAnchor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.UserCredentials;

public class ExcelReaderUtil2 {
	
	private ExcelReaderUtil2() {
		
		
	}

	public static Iterator<UserCredentials> loadTestData() {

		// Apache POI OOXML LIB
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData/PhoenixTestData.xlsx");
		XSSFWorkbook myWorkbook = null;
		try {
			myWorkbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet mySheet = myWorkbook.getSheet("LoginTestData");
		
		XSSFRow myRow;
		XSSFCell myCell;
		//Read the Excel file----Stored in ArrayList<UserCredentials>
		//I want to know the indexes for the username and password in our sheet
		XSSFRow headersRows=mySheet.getRow(0);//HeaderRows
		int userNameIndex=-1;
		int passwordIndex=-1;
		for(Cell cell:headersRows) {
			if(cell.getStringCellValue().trim().equalsIgnoreCase("username")) {
				userNameIndex= cell.getColumnIndex();
			}
			if(cell.getStringCellValue().trim().equalsIgnoreCase("password")) {
				passwordIndex= cell.getColumnIndex();
			}
		}
		
		System.out.println(userNameIndex+ " "+passwordIndex );
		
		int lastRowIndex=mySheet.getLastRowNum();
		XSSFRow rowData;
		UserCredentials userCredentials;
		ArrayList<UserCredentials> userList= new ArrayList<UserCredentials>();
		
		for(int rowIndex=1; rowIndex<=lastRowIndex; rowIndex++) {
			rowData= mySheet.getRow(rowIndex);
			 userCredentials= new UserCredentials(rowData.getCell(userNameIndex).toString(),rowData.getCell(passwordIndex).toString());
			 userList.add(userCredentials);
		}
		
		return userList.iterator();
	}

}
