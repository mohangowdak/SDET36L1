package org.vtiger.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.UtilityObjectClass;
import org.tyss.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;


@Listeners(org.tyss.genericUtility.AllureListenerImplementation.class)
@Epic("Allure EPIC 1")
@Story("Allure Story 1")

public class AllureReportPractice {

	@Description("Allure Description 124")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void extentReport() throws InterruptedException {
		WebDriverUtility webDriverUtility=new WebDriverUtility();
		UtilityObjectClass.setWebdriverUtility(webDriverUtility);
		UtilityObjectClass.setJavaUtility(new JavaUtility());
		WebDriverManager.chromedriver().setup();
		printStatement("Browser executable files intiallized");
		WebDriver driver=new ChromeDriver();
		UtilityObjectClass.setDriver(driver);
		Allure.step("Browser Launched successfully");
		driver.manage().window().maximize();
		printStatement("Browser Maximized Successfully");
		driver.get("https://google.com");
		printStatement("Application opened successfully");
		driver.findElement(By.name("q")).sendKeys("Testyantra", Keys.ENTER);
		printStatement("Product searched successfully");
	
		Assert.fail();
		Thread.sleep(3000);
		printStatement("Application wait 3 sec");
		driver.quit();
		printStatement("Browser Closed successfully");


	}
	@Step("{str}")
	public void printStatement(String str) {
		System.out.println(str);
	}



}
