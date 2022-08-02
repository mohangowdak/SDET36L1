package org.vtiger.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.tyss.genericUtility.ExcelUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class KeywordDrivenExample {
	@Test
	public void lunchApp() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8888");
		driver.manage().window().maximize();
		ExcelUtility excelutils = new ExcelUtility();
		excelutils.intiallizeExcelFile("./src/test/resources/Book1.xlsx");

		String[][] alldata = excelutils.getMultipleDataFromExcel("Sheet1");

		for (int i = 0; i < alldata.length; i++) {
			String locatorName=alldata[i][2].split("###")[0];
			String locatorValue = alldata[i][2].split("###")[1];
			
			if(alldata[i][1].equals("send")) {
				driver.findElement(locatorSelection(locatorName,locatorValue)).sendKeys(alldata[i][3]);
			}
			else if(alldata[i][1].equals("click")) {
				driver.findElement(locatorSelection(locatorName,locatorValue)).click();
			}
		}
		driver.quit();
	}

	public By locatorSelection(String locatorname, String locatorValue) {
		By locator=null;
		if(locatorname.equalsIgnoreCase("id"))
			locator= By.id(locatorValue);
		else if(locatorname.equalsIgnoreCase("name"))
			locator= By.name(locatorValue);
		else if(locatorname.equalsIgnoreCase("xpath"))
			locator= By.xpath(locatorValue);
		else if(locatorname.equalsIgnoreCase("linktext"))
			locator= By.linkText(locatorValue);
		if(locator==null) {
			System.out.println("Specify proper locator name in Excel file");
		}
		return locator;
	}


}
