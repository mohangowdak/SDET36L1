package org.vtiger.practice;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.runtime.model.ExceptionDetails;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tyss.genericUtility.JavaUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalenderPopup2 {

	public static void main(String[] args) {
		JavaUtility javaUtility=new JavaUtility();
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://www.hyrtutorials.com/p/calendar-practice.html");
		String reqDate="33-January-2020";
		
		driver.findElement(By.xpath("//input[@id='first_date_picker']")).click();
		
		WebElement currentMonthYear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']"));
		wait.until(ExpectedConditions.visibilityOf(currentMonthYear));

		String[] arrMonYear = currentMonthYear.getText().split(" ");
		String currentMonth=arrMonYear[0];
		String currentYear=arrMonYear[1];
		System.out.println("current Month ---> "+currentMonth);
		System.out.println("current year --> "+currentYear);

		int currentMonthValue = javaUtility.convertMonthFromStringToInt(currentMonth, "MMMM"); 
		int requiredMonthValue=javaUtility.convertMonthFromStringToInt(reqDate.split("-")[1], "MMMM"); 
		int currentYearValue=Integer.parseInt(currentYear);
		int requiredYearValue=Integer.parseInt(reqDate.split("-")[2]);

		try{while(requiredMonthValue>currentMonthValue || requiredYearValue>currentYearValue) {
			driver.findElement(By.xpath("//a[@data-handler='next']")).click();
			arrMonYear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']"))
					.getText().split(" ");
			currentMonth=arrMonYear[0];
			currentYear=arrMonYear[1];
			currentMonthValue = javaUtility.convertMonthFromStringToInt(currentMonth, "MMMM"); 
			currentYearValue=Integer.parseInt(currentYear);	
		}
		
		while(requiredMonthValue<currentMonthValue || requiredYearValue<currentYearValue) {
			driver.findElement(By.xpath("//a[@data-handler='prev']")).click();
			arrMonYear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText().split(" ");
			currentMonth=arrMonYear[0];
			currentYear=arrMonYear[1];
			currentMonthValue = javaUtility.convertMonthFromStringToInt(currentMonth, "MMMM"); 
			currentYearValue=Integer.parseInt(currentYear);	
		}


		driver.findElement(By.xpath("//a[.='"+reqDate.split("-")[0]+"']")).click();
	}
		catch(Exception e) {
			System.out.println("please enter proper format (dd-MMMM-yyy) or Please enter valid date");
		}
	}

}
