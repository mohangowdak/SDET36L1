package org.vtiger.practice;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MakeMyTrip {

	public static void main(String[] args) throws InterruptedException {
		String departureDate="10-September-2022";
		String returnDate="10-October-2022";

		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.makemytrip.com/");
		driver.findElement(By.xpath("//span[@class='langCardClose']")).click();
		driver.findElement(By.xpath("//li[@data-cy='roundTrip']")).click();
		driver.findElement(By.xpath("//label[@for='fromCity']/span")).click();
		driver.findElement(By.xpath("//input[@placeholder='From']/following-sibling::div//p[.='Mumbai, India']")).click();
		driver.findElement(By.xpath("//input[@placeholder='To']/following-sibling::div//p[.='Bengaluru, India']")).click();
		selectDate(driver, "departure", departureDate);
		selectDate(driver, "return", returnDate);
		driver.findElement(By.xpath("//a[.='Search']")).click();
	}

	public static void selectDate(WebDriver driver, String typeOfTrip , String date) {
	int i=typeOfTrip.equals("return")? 2 : 1;
		
		int reqMonth=LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MMMM-yyyy")).getMonthValue();
		int reqYear=Integer.parseInt(date.split("-")[2]);
		
		String currentMonthYear = driver.findElement(By.xpath("//div[@class='DayPicker-Months']/div["+i+"]/div[@class='DayPicker-Caption']/div")).getText();
		int currentMonth=DateTimeFormatter.ofPattern("MMMMyyyy").withLocale(Locale.ENGLISH).parse(currentMonthYear).get(ChronoField.MONTH_OF_YEAR);
		int currentYear=DateTimeFormatter.ofPattern("MMMMyyyy").withLocale(Locale.ENGLISH).parse(currentMonthYear).get(ChronoField.YEAR);
		while(!(currentMonth==reqMonth && currentYear==reqYear))
		{
			driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
			currentMonthYear = driver.findElement(By.xpath("//div[@class='DayPicker-Months']/div[2]/div[@class='DayPicker-Caption']/div")).getText();
			currentMonth=DateTimeFormatter.ofPattern("MMMMyyyy").withLocale(Locale.ENGLISH).parse(currentMonthYear).get(ChronoField.MONTH_OF_YEAR);
			currentYear=DateTimeFormatter.ofPattern("MMMMyyyy").withLocale(Locale.ENGLISH).parse(currentMonthYear).get(ChronoField.YEAR);
		}
		driver.findElement(By.xpath("//div[@class='DayPicker-Month' and contains(.,'"+date.split("-")[1]+"')]//p[.='"+date.split("-")[0]+"']")).click();
	}
}