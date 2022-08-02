package org.vtiger.practice;

import java.io.IOException;

import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;

import com.opencsv.exceptions.CsvException;

public class FetchDataFromCsv {
	
	public static void main(String[] args) throws IOException, CsvException {
		System.out.println(new FileUtility()
				.getDataFromCSV(IConstants.VTIGERCSVFILEPATH, 2, 2));
		
	}

}
