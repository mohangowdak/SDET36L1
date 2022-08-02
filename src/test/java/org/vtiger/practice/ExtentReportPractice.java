package org.vtiger.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.ListenerImplementation;
import org.tyss.genericUtility.UtilityObjectClass;
import org.tyss.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
@Listeners(org.tyss.genericUtility.ListenerImplementation.class)
public class ExtentReportPractice {

	@Test
	public void extentReport() throws InterruptedException {
		WebDriverUtility webDriverUtility=new WebDriverUtility();
		UtilityObjectClass.setWebdriverUtility(webDriverUtility);
		UtilityObjectClass.setJavaUtility(new JavaUtility());
		WebDriverManager.chromedriver().setup();
		ListenerImplementation.testlog.info("Browser executable files intiallized");
		WebDriver driver=new ChromeDriver();
		UtilityObjectClass.setDriver(driver);
		ListenerImplementation.testlog.info("Browser Launched successfully");
		driver.manage().window().maximize();
		ListenerImplementation.testlog.info("Browser Maximized Successfully");
		driver.get("https://google.com");
		ListenerImplementation.testlog.info("Application opened successfully");
		driver.findElement(By.name("q")).sendKeys("Testyantra", Keys.ENTER);
		ListenerImplementation.testlog.info("Product searched successfully");
		
		Thread.sleep(3000);
		ListenerImplementation.testlog.info("Application wait 3 sec");
		driver.quit();
		ListenerImplementation.testlog.info("Browser Closed successfully");



	}

}
