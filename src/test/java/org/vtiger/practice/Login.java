package org.vtiger.practice;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;
import org.vtiger.objectRepository.CommonPage;
import org.vtiger.objectRepository.LoginPage;

public class Login {

	

	@Test(dataProvider = "getData")
	public void loginTest(String userName, String password) {

		 FileUtility fileUtility = new FileUtility();
		 JavaUtility javaUtility = new JavaUtility();
		
		 WebDriverUtility webdriverUtility = new WebDriverUtility();
		
		//intiallize data from Property file
		fileUtility.intiallizePropertyFile(IConstants.VITIGERPROPERTYFILEPATH);
		//get the control for particular sheet in excel
		
		//Fetch the data from property file
		String browser = fileUtility.getDataFromProperty("browser");
		
		String url = fileUtility.getDataFromProperty("url");
		String timeouts=fileUtility.getDataFromProperty("timeout");
		//convert string to long
		long longTimeout = javaUtility.convertStringToLong(timeouts);
		
		//launching the browser in run time based on browser key
				 
		WebDriver driver = webdriverUtility.setupDriver(browser);
		//pre-setting for the browser
		webdriverUtility.maximizeBrowser();
		webdriverUtility.implicitWait(longTimeout);
		//intiallize the Explicit wait, Actions
		webdriverUtility.intiallizeActions();
		//create object for Common POM repo classes
		
SoftAssert soft=new SoftAssert();
		//navigating the application
		webdriverUtility.openApplication(url);
		LoginPage loginPage = new LoginPage(driver);
				loginPage.loginAction(userName, password);
				
				soft.assertTrue(driver.getCurrentUrl().equals("http://localhost:8888/index.php?action=index&module=Home"));
	driver.quit();
	
	}
	
	
	@DataProvider
	public String[][] getData(){
		ExcelUtility excelUtility = new ExcelUtility();
		excelUtility.intiallizeExcelFile(IConstants.VTIGEREXCELFILEPATH);

	return	excelUtility.getMultipleDataFromExcel("Login Data");
	}

}
