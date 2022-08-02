package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchTheDataFromTheExcel {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		DataFormatter dataFormat = new DataFormatter();
		FileInputStream fisExcel = new FileInputStream("./src/test/resources/testData.xlsx");
		Workbook workbook = WorkbookFactory.create(fisExcel);
		Sheet sheet = workbook.getSheet("Login Data");
		int rowNum=sheet.getLastRowNum();
		int cellNum=sheet.getRow(0).getLastCellNum();
		String[][] str=new String[rowNum][cellNum];
		for(int i=1; i<=rowNum;i++) {
			for(int j=0; j<cellNum;j++) {
				str[i-1][j] = dataFormat.formatCellValue(sheet.getRow(i).getCell(j));
			}
		}		
			System.out.println(str[5][1]);
		
		workbook.close();
	}

}
