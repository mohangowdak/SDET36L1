package org.vtiger.organizations;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;
import org.vtiger.objectRepository.CommonPage;
import org.vtiger.objectRepository.LoginPage;
import org.vtiger.objectRepository.OrganizationPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteOrganizationTest2 {


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
		Sheet sheet = workbook.getSheet("Organizations");

		//Fetch the data from property file
		String browser=properties.getProperty("browser").trim();
		String userName=properties.getProperty("username").trim();
		String password=properties.getProperty("password").trim();
		String url=properties.getProperty("url").trim();
		String timeouts=properties.getProperty("timeout").trim();

		//Fetch the data from Excel File
		String expectedDeleteOrganization="TYSS_180";

		//convert string to long
		long longTimeout = Long.parseLong(timeouts);

		//create Object for Generic Utiltiy
		JavaUtility javaUtility=new JavaUtility();
		WebDriverUtility webDriverUtility=new WebDriverUtility();
		//launching the browser in run time based on browser key
		WebDriver driver=webDriverUtility.setupDriver(browser);


		//pre-setting for the browser
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeout));

		//creating object for explicit wait , Action class
		webDriverUtility.intiallizeExplicitWait(longTimeout, 1000);

		//Create object for POM repository
		LoginPage loginPage=new LoginPage(driver);
		CommonPage commonPage=new CommonPage(driver);
		OrganizationPage organizationPage=new OrganizationPage(driver);


		//navigating the application
		driver.get(url); 

		//login to the app
		loginPage.loginAction(userName, password);
		commonPage.clickOrganization();
		int pageCount=organizationPage.getPageCount();

		boolean flag=true;
		for(int j=0;j<pageCount;j++) {
			List<String> listOrganization = organizationPage.getProjectNameList();

			for(int i=0; i<listOrganization.size();i++)
			{
				if(listOrganization.get(i).equals(expectedDeleteOrganization)) {
					String replaceData = javaUtility.convertFromIntegerToString(i+2);
					organizationPage.clickParticularCheckbox(replaceData);
					flag=false;
					break;
				}
			}
			if(flag==false)
			{
				break;
			}
			else {
				organizationPage.clickNext();
				organizationPage.waitTillStatusInvisible(webDriverUtility);
			}

		}
		organizationPage.deleteOrganization(webDriverUtility);



		//
		//		//logout from the application
		//		Actions act=new Actions(driver);
		//		WebElement adminstratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		//		act.moveToElement(adminstratorIcon).perform();
		//		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		//
		//		//close the workbook instance of excel
		//		workbook.close();
		//
		//		//close the browser
		//		driver.quit();

	}
}
