package org.tyss.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

/**
 * This Class contains reusable methods for Csv file and property file
 * @author MOHAN GOWDA
 *
 */
public final class FileUtility {
	
	private Properties properties;
	
	
	/**
	 * This method is used for intiallize the property file
	 * @param filePath
	 */
 public void intiallizePropertyFile(String propertyFilePath) {
		FileInputStream fis=null;
			try {
				fis = new FileInputStream(propertyFilePath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			 properties = new Properties();
			try {
				properties.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	
		
 }
 
 /**
  * This method is used to fetch the data from property file
  * @param key
  * @return
  */
 public String getDataFromProperty(String key) {
	return properties.getProperty(key).trim();
 }
 
 /**
  * This method is used to fetch the data from csv file
  * @param csvFilePath
  * @param rowNumber
  * @param cellNumber
  * @return
  */
 public String getDataFromCSV(String csvFilePath, int rowNumber, int cellNumber) {
	 CSVReader reader = null;
	 String data=null;
	try {
		reader = new CSVReader(new FileReader(csvFilePath));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
		try {
			 data= reader.readAll().get(rowNumber)[cellNumber];
		} catch (IOException | CsvException e) {
			e.printStackTrace();
		}
		return data;
		
 }

}
