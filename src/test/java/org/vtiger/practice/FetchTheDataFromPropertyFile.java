package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FetchTheDataFromPropertyFile {

	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
	Properties properties=new Properties();
	properties.load(fis);
	
	String data = properties.getProperty("url").trim();
	System.out.println(data);
	}
}
