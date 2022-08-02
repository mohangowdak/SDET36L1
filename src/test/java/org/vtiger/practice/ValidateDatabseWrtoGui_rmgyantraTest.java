package org.vtiger.practice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tyss.genericUtility.DatabaseUtility;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

public class ValidateDatabseWrtoGui_rmgyantraTest {
	public static void main(String[] args) {
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

		databaseUtility.getConnectionWithDB(dbUrl+dbName, dbUserName, dbPassword);
		String query="insert into project values('TY_PROJ_7"+randomNumber+"', 'Mohan', '"+javaUtility.getCurrentDate("dd/MM/yyyy")+"', '"+expectedProjectName+"', 'Still Not completed', 7)";
		databaseUtility.modifyDataInDB(query);
		javaUtility.printStatement("Project added into database Successfully");
		
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
		
		List<WebElement> listOfProjects = driver.findElements(By.xpath("//table[@class='table table-striped table-hover']/tbody/tr/td[2]"));
		
		for(WebElement project:listOfProjects) {
			String actualProjectName=project.getText();
			if(actualProjectName.equals(expectedProjectName))
			{javaUtility.printStatement("project is present in the list of projects page");
			javaUtility.printStatement("ActualProjectName ==> "+actualProjectName);
			break;
			}
		}
		
		databaseUtility.closeDBConnection();
		webDriverUtility.closeBrowser();
		
	}

}
