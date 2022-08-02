package org.vtiger.contacts;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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
import org.openqa.selenium.support.ui.FluentWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactsTest {

	public static void main(String[] args) throws IOException {
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
		String expectedContactLastName = sheet.getRow(2).getCell(1).getStringCellValue()+randomNumber;


		//convert string to long
		long longTimeout = Long.parseLong(timeouts);

		//launching the browser in run time based on browser key
		WebDriver driver=null;
		//run time polymorphism
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup(); //method chaining
			driver=new ChromeDriver(); //abstraction
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup(); //method chaining
			driver=new FirefoxDriver();  //abstraction
			break;
		case "ie":
			WebDriverManager.iedriver().setup(); //method chaining
			driver=new InternetExplorerDriver(); 
			break;
		default:
			System.out.println("You entered wrong Browser key in the Property file");
			break;
		}

		//pre-setting for the browser
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeout));

		//navigating the application
		driver.get(url); 



		//login to the app
		driver.findElement(By.xpath("//input[@name='abc']")).sendKeys(userName);
		
	//	driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();

		//create contat
		driver.findElement(By.xpath("//a[.='Contacts']")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(expectedContactLastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		//		//fetch the actual contact last name
		//		String actualLastName = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		//
		//		//validating contact last name
		//		if(actualLastName.equals(expectedContactLastName))
		//		{
		//			System.out.println("Contact created successfully---> TC is Pass");
		//		}
		//		else {
		//			System.out.println("Contact is not created---> TC is Fail");
		//		}

		//logout from the application
		int timeDuration=10;
		int count=0;
		while(count<=timeDuration) {
			try {
				driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
				break;
			}
			catch(Exception e) {
				try {
				Thread.sleep(1000);
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				count++;
			}
		}

	
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

