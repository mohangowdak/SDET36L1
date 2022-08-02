package org.vtiger.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tyss.genericUtility.DatabaseUtility;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

public class ValidateGuiWrtoDataBase_rmgyantraTest {
	public static void main(String[] args)  {
		JavaUtility javaUtility=new JavaUtility();
		ExcelUtility excelUtility=new ExcelUtility();
		DatabaseUtility databaseUtility=new DatabaseUtility();
		FileUtility fileUtility=new FileUtility();
		WebDriverUtility webDriverUtility=new WebDriverUtility();
		
		excelUtility.intiallizeExcelFile(IConstants.RMGYANTRAEXCELFILEPATH);
		fileUtility.intiallizePropertyFile(IConstants.RMGYANTRAPROPERTYFILEPATH);
		
		int randomNumber=javaUtility.getRandomNumber(100);
		String expectedProjectName=excelUtility.getDataFromExcel(2, 1, "Projects")+randomNumber;
		System.out.println("ExpectedProjectName ==> "+expectedProjectName);
		String dbUrl=fileUtility.getDataFromProperty("dburl");
		String dbUserName=fileUtility.getDataFromProperty("dbusername");
		String dbPassword=fileUtility.getDataFromProperty("dbpassword");
		String dbName=fileUtility.getDataFromProperty("dbname");
		String browser=fileUtility.getDataFromProperty("browser");
		String appURL=fileUtility.getDataFromProperty("rmgyantraurl");
		String timeouts=fileUtility.getDataFromProperty("timeout");

		//convert string to long
		long longTimeout =  javaUtility.convertStringToLong(timeouts);

		String projectName="SDET36L1_761";
		
		WebDriver driver = webDriverUtility.setupDriver(browser);
		javaUtility.printStatement("Browser launched");
		webDriverUtility.openApplication(appURL);
		webDriverUtility.maximizeBrowser();
		webDriverUtility.implicitWait(longTimeout);
		
		
		driver.findElement(By.xpath("//input[@id='usernmae']")).sendKeys("rmgyantra");
		driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		javaUtility.printStatement("Successfully Login");
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		driver.findElement(By.xpath("//span[.='Create Project']")).click();
		driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys(projectName);
	    driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("DEEPAK");
		WebElement projectStatusDropDown = driver.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select"));
		
		
		webDriverUtility.handleSelectDropdown(projectStatusDropDown, "On Goging");
		
	driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();
	
	javaUtility.printStatement("Successfully Created project");
		
	
		databaseUtility.getConnectionWithDB(dbUrl+dbName, dbUserName, dbPassword);
		String query="select * from project;";
		 
		
		boolean status = databaseUtility.verifyDataInDB(query, "project_name", projectName);
		
	if(status==true) {
		javaUtility.printStatement("Project is present in Database");
		javaUtility.printStatement("Actual Project Name ==>>> "+ expectedProjectName);
		}
	else {
		javaUtility.printStatement("Project is not present in Database");
	}
	
		
	databaseUtility.closeDBConnection();
		
		webDriverUtility.closeBrowser();
	
		
		
		
		
	}

}
