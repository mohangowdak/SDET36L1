package org.vtiger.contacts;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tyss.genericUtility.JSExecutorUtiltity;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.SwitchWindow;
import org.tyss.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganizationTest {


	public static void main(String[] args) throws IOException {
		
		WebDriverUtility webdriverutility=new WebDriverUtility();
		JSExecutorUtiltity jsexecutorUtility=new JSExecutorUtiltity();
		JavaUtility javaUtility=new JavaUtility();
	
		//intiallize data from Property file
		FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
		Properties properties=new Properties();
		properties.load(fis);

		//Generate the random number
		int randomNumber = new Random().nextInt(1000);

		//get the control for particular sheet in excel
		FileInputStream fisExcel=new FileInputStream("./src/test/resources/testData.xlsx");
		Workbook workbook = WorkbookFactory.create(fisExcel);
		Sheet sheet = workbook.getSheet("Contacts");

		//Fetch the data from property file
		String browser=properties.getProperty("browser").trim();
		String userName=properties.getProperty("username").trim();
		String password=properties.getProperty("password").trim();
		String url=properties.getProperty("url").trim();
		String timeouts=properties.getProperty("timeout").trim();

		//Fetch the data from Excel File
		String expectedOrganizionName = sheet.getRow(4).getCell(1).getStringCellValue()+randomNumber;
		String expectedContactName = sheet.getRow(4).getCell(2).getStringCellValue()+randomNumber;


		//convert string to long
		long longTimeout = Long.parseLong(timeouts);

		//launching the browser in run time based on browser key
		WebDriver driver=webdriverutility.setupDriver(browser);

		jsexecutorUtility.intiallizeJSExecutor(driver);
		//pre-setting for the browser
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeout));

		//creating object for explicit wait , Action class
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		
		//navigating the application
		driver.get(url); 

		//login to the app
		WebElement userNameTxtField = driver.findElement(By.xpath("//input[@name='user_name']"));
		WebElement passwordTxtField = driver.findElement(By.xpath("//input[@name='user_password']"));
		userNameTxtField.sendKeys(userName);
		jsexecutorUtility.highlightElement(userNameTxtField);
		passwordTxtField.sendKeys(password);
		jsexecutorUtility.highlightElement(passwordTxtField);
		
	//	webdriverutility.takeScreenShotPage(new CreateContactWithOrganizationTest(), javaUtility);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();


		//create contat
		driver.findElement(By.xpath("//a[.='Organizations']")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(expectedOrganizionName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		WebElement updateInfoTxt = driver.findElement(By.xpath("//span[@class='small']"));
		wait.until(ExpectedConditions.visibilityOf(updateInfoTxt));
       
		
		//create contat
		driver.findElement(By.xpath("//a[.='Contacts']")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(expectedContactName);

		driver.findElement(By.xpath("//td[contains(.,'Organization Name') and @class='dvtCellLabel']/following-sibling::td[@class='dvtCellInfo']/img")).click();

		webdriverutility.switchWindow("Accounts", SwitchWindow.URL);
		

		driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(expectedOrganizionName);
		driver.findElement(By.xpath("//input[@name='search']")).click();
		driver.findElement(By.xpath("//a[.='"+expectedOrganizionName+"']")).click();

		webdriverutility.switchWindow("Contacts", SwitchWindow.URL);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		//fetch the actual contact last name
		String actualLastName = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		String actualOrgnizationName = driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']/a")).getText();

		//validating contact last name
		if(actualLastName.equals(expectedContactName) && actualOrgnizationName.equals(expectedOrganizionName))
		{
			System.out.println("Contact created with Orginzation successfully---> TC is Pass");
		}
		else {
			System.out.println("Contact is not created---> TC is Fail");
		}





		//logout from the application
		Actions act=new Actions(driver);
		WebElement adminstratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		act.moveToElement(adminstratorIcon).perform();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();

		//close the workbook instance of excel
		workbook.close();

		//close the browser
		driver.quit();

	}
}
