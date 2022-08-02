package org.tyss.genericUtility;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;
import org.vtiger.objectRepository.CommonPage;
import org.vtiger.objectRepository.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class BaseClass extends InstanceClass{
	
public 	WebDriver driver;
@Description("Allure Description")
	//comment by engg 2

//xyz code by engg 2
//@Parameters("BROWSER")

@Story("Extent Report Story")
	@BeforeClass(groups = "baseclass")
	public void classSetup(/*String browser*/) {
		//create objects for Generic Utility
		fileUtility=new FileUtility();
		javaUtility=new JavaUtility();
		excelUtility=new ExcelUtility();
		webdriverUtility=new WebDriverUtility();

		UtilityObjectClass.setFileUtility(fileUtility);
		UtilityObjectClass.setJavaUtility(javaUtility);
		UtilityObjectClass.setExcelutility(excelUtility);
		UtilityObjectClass.setWebdriverUtility(webdriverUtility);
		
		//intiallize data from Property file
		fileUtility.intiallizePropertyFile(IConstants.VITIGERPROPERTYFILEPATH);
		//get the control for particular sheet in excel
		excelUtility.intiallizeExcelFile(IConstants.VTIGEREXCELFILEPATH);
	

		//Fetch the data from property file
		browser=fileUtility.getDataFromProperty("browser");
		userName=fileUtility.getDataFromProperty("username");
		password=fileUtility.getDataFromProperty("password");
		url=fileUtility.getDataFromProperty("url");
		String timeouts=fileUtility.getDataFromProperty("timeout");
		//convert string to long
		longTimeout =  javaUtility.convertStringToLong(timeouts);
		//launching the browser in run time based on browser key
		 driver = webdriverUtility.setupDriver(browser);
		
		 UtilityObjectClass.setDriver(driver);
		//pre-setting for the browser
		webdriverUtility.maximizeBrowser();
		webdriverUtility.implicitWait(longTimeout);
		//intiallize the Explicit wait, Actions
		webdriverUtility.intiallizeActions();
		webdriverUtility.intiallizeExplicitWait(longTimeout, 500);
		//create object for Common POM repo classes
		loginPage=new LoginPage(driver);
		commonPage=new CommonPage(driver);

		//navigating the application
		webdriverUtility.openApplication(url);
	}

@Step("Step1")
	@BeforeMethod(groups = "baseclass")
	public void methodSetup() {
		//Generate the random number
		randomNumber = javaUtility.getRandomNumber();
		 soft=new SoftAssert();
		loginPage.loginAction(userName, password);
	}


	@AfterMethod(groups = "baseclass")
	public void methodTearDown() {
		//Signout
		commonPage.logoutAction(webdriverUtility);
		soft.assertAll();
	}

	@AfterClass(groups = "baseclass")
	public void classTearDown() {
		//close Browser
		webdriverUtility.closeBrowser();
	}
}
