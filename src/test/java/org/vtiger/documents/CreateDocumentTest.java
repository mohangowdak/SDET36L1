package org.vtiger.documents;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocumentTest {

	public static void main(String[] args) throws Throwable {
		//create objects for Generic Utility
		FileUtility fileUtility=new FileUtility();
		JavaUtility javaUtility=new JavaUtility();
		ExcelUtility excelUtility=new ExcelUtility();
		WebDriverUtility webdriverUtility=new WebDriverUtility();


		//intiallize data from Property file
		fileUtility.intiallizePropertyFile(IConstants.VITIGERPROPERTYFILEPATH);

		//Generate the random number
		int randomNumber = javaUtility.getRandomNumber();

		//get the control for particular sheet in excel
		excelUtility.intiallizeExcelFile(IConstants.VTIGEREXCELFILEPATH);

		//Fetch the data from property file
		String browser=fileUtility.getDataFromProperty("browser");
		String userName=fileUtility.getDataFromProperty("username");
		String password=fileUtility.getDataFromProperty("password");
		String url=fileUtility.getDataFromProperty("url");
		String timeouts=fileUtility.getDataFromProperty("timeout");

		String sheetName="Documents";


		//Fetch the data from Excel File
		String expectedDoucmentTitle =  excelUtility.getDataFromExcel(2, 1,sheetName)+randomNumber;
		String expectedDescription = excelUtility.getDataFromExcel(2, 2,sheetName);
		String filePath = excelUtility.getDataFromExcel(2, 3,sheetName);

		//convert string to long
		long longTimeout =  javaUtility.convertStringToLong(timeouts);

		//launching the browser in run time based on browser key
		WebDriver driver = webdriverUtility.setupDriver(browser);

		//pre-setting for the browser
		webdriverUtility.maximizeBrowser();
		webdriverUtility.implicitWait(longTimeout);

		//intiallize the Explicit wait, Actions
		webdriverUtility.intiallizeActions();

		//navigating the application
		webdriverUtility.openApplication(url);

		//login to the app
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();

		driver.findElement(By.xpath("//a[.='Documents']")).click();
		driver.findElement(By.xpath("//img[@title='Create Document...']")).click();

		driver.findElement(By.xpath("//input[@name='notes_title']")).sendKeys(expectedDoucmentTitle);

		WebElement frame = driver.findElement(By.xpath("//iframe[@title='Rich text editor, notecontent, press ALT 0 for help.']"));
		webdriverUtility.switchFrame(frame);
		driver.findElement(By.xpath("//body[@class='cke_show_borders']")).sendKeys(expectedDescription, Keys.CONTROL+"a");
		webdriverUtility.switchBackFromFrame("default");
		driver.findElement(By.xpath("//a[@id='cke_5']")).click();
		driver.findElement(By.xpath("//a[@id='cke_6']")).click();
		String expectedFilePath=System.getProperty("user.dir")+filePath;
		driver.findElement(By.xpath("//input[@name='filename']")).sendKeys(expectedFilePath);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		String actualDocumentTitle= driver.findElement(By.xpath("//span[@id='dtlview_Title']")).getText();
		String actualDescription= driver.findElement(By.xpath("//td[@class='dvtCellInfo']/p")).getText().trim();
		String actualFileName= driver.findElement(By.xpath("//td[@class='dvtCellInfo']/a")).getText().replace("_", " ");
		String[] splitFilePath= javaUtility.splitString(expectedFilePath, "/");
		String expectedFileName=splitFilePath[splitFilePath.length-1];

		if(actualDocumentTitle.equals(expectedDoucmentTitle) && actualDescription.equals(expectedDescription) && actualFileName.equals(expectedFileName))
		{
			System.out.println("Document created successfully---> TC is Pass");
			excelUtility.setDataIntoExcel(sheetName, 2, 4, "Pass");
		}
		else {
			System.out.println("Document is not created---> TC is Fail");
			excelUtility.setDataIntoExcel(sheetName, 2, 4, "Fail");
		}


		WebElement adminstratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webdriverUtility.mouseHoverOnElement(adminstratorIcon);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		excelUtility.saveDataIntoExcel(IConstants.VTIGEREXCELFILEPATH);
		excelUtility.closeWorkbook();
		webdriverUtility.closeBrowser();
	}

}
