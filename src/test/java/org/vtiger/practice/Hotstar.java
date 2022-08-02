package org.vtiger.practice;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Hotstar {
	
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.hotstar.com/in/list/latest-trending/t-7071_25_2");
		Thread.sleep(4000);
		List<WebElement> list = driver.findElements(By.xpath("//span[@class='content-title ellipsise']"));
		List<WebElement> detailsList = driver.findElements(By.xpath("//div[@class='details']"));
		Actions act=new Actions(driver);
		for(int i=0; i<list.size();i++)
		{
			act.moveToElement(detailsList.get(i)).pause(Duration.ofSeconds(1)).build().perform();
			System.out.println(list.get(i).getText());
		}
		
	}

}
