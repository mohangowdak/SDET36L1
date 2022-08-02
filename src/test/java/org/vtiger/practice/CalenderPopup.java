package org.vtiger.practice;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tyss.genericUtility.JavaUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalenderPopup {

	public static void main(String[] args) {
		JavaUtility javaUtility=new JavaUtility();
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("http://192.168.1.108/domain/Hospital_Management_System/hms/user-login.php");

		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("patient@gmail.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test@123");
		driver.findElement(By.xpath("//button[@name='submit']")).click();
		driver.findElement(By.xpath("//span[.=' Book Appointment ']")).click();
		String reqDate="13-October-2023";
		driver.findElement(By.xpath("//input[@name='appdate']")).click();
		WebElement currentMonthYear = driver.findElement(By.xpath("//table[@class=' table-condensed']/thead/tr/th[@class='datepicker-switch']"));
		wait.until(ExpectedConditions.visibilityOf(currentMonthYear));

		String[] arrMonYear = currentMonthYear.getText().split(" ");
		String currentMonth=arrMonYear[0];
		String currentYear=arrMonYear[1];

		int currentMonthValue = javaUtility.convertMonthFromStringToInt(currentMonth, "MMMM"); 
		int requiredMonthValue=javaUtility.convertMonthFromStringToInt(reqDate.split("-")[1], "MMMM"); 
		int currentYearValue=Integer.parseInt(currentYear);
		int requiredYearValue=Integer.parseInt(reqDate.split("-")[2]);

		while(!(requiredMonthValue==currentMonthValue && requiredYearValue==currentYearValue)) {
			driver.findElement(By.xpath("//table[@class=' table-condensed']/thead/tr/th[@class='next']")).click();
			arrMonYear = driver.findElement(By.xpath("//table[@class=' table-condensed']/thead/tr/th[@class='datepicker-switch']")).getText().split(" ");
			currentMonth=arrMonYear[0];
			currentYear=arrMonYear[1];
			currentMonthValue = javaUtility.convertMonthFromStringToInt(currentMonth, "MMMM"); 
			currentYearValue=Integer.parseInt(currentYear);	
		}


		driver.findElement(By.xpath("//td[.='"+reqDate.split("-")[0]+"' and @class='day']")).click();
	}

}
