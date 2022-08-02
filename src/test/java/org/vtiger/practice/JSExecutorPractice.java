package org.vtiger.practice;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.tyss.genericUtility.JavaUtility;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JSExecutorPractice {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		JavaUtility javaUtility=new JavaUtility();
		JSExecutorPractice currentClass=new JSExecutorPractice();
		
		JavascriptExecutor js=(JavascriptExecutor) driver;
		String url="http://localhost:8888";
		js.executeScript("window.location=arguments[0]",url);
		WebElement userNameTxtField = driver.findElement(By.xpath("//input[@name='user_name']"));
		WebElement passwordTxtField = driver.findElement(By.xpath("//input[@name='user_password']"));
		WebElement loginBtn = driver.findElement(By.xpath("//input[@id='submitButton']"));
		js.executeScript("arguments[0].value=arguments[1]", userNameTxtField, "admin");
		js.executeScript("arguments[0].value=arguments[1]", passwordTxtField, "admin");
		js.executeScript("arguments[0].click()", loginBtn);
		//js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		//js.executeScript("window.scrollBy(0,300)");
		WebElement scrollTillElement = driver.findElement(By.xpath("//b[contains(.,'Top Quotes')]"));
		
		js.executeScript("arguments[0].scrollIntoView()", scrollTillElement);
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File("./errorshots/"+currentClass.getClass().getSimpleName()+javaUtility.getCurrentDate("dd_MM_yyyy_HH_mm_sss"));
	
		
		try {
			FileUtils.copyFile(src, dst);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
