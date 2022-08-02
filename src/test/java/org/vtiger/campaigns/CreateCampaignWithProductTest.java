package org.vtiger.campaigns;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.tyss.genericUtility.BaseClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignWithProductTest extends BaseClass {
@Test(groups = "regression")
	public void createCampaignWithProductTest() throws IOException {
	
	String expectedCampaignName = excelUtility.getDataFromExcel(4, 1,"Campaigns")+randomNumber;
	String expectedProductName = excelUtility.getDataFromExcel(4, 2,"Campaigns")+randomNumber;
	
		//create Products
		driver.findElement(By.xpath("//a[.='Products']")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(expectedProductName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		WebElement updateInfoTxt = driver.findElement(By.xpath("//span[@class='small']"));
		
		webdriverUtility.waitTillElementVisible(updateInfoTxt);

		//create campaign
		webdriverUtility.mouseHoverOnElement(driver.findElement(By.xpath("//a[.='More']")));
		driver.findElement(By.xpath("//a[.='Campaigns']")).click();
		driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
		driver.findElement(By.xpath("//input[@name='campaignname']")).sendKeys(expectedCampaignName);
		driver.findElement(By.xpath("//td[contains(.,'Product') and @class='dvtCellLabel']/following-sibling::td[@class='dvtCellInfo']/img")).click();

		Set<String> winIds = driver.getWindowHandles();

		for(String id:winIds)
		{
			driver.switchTo().window(id);
			if(driver.getCurrentUrl().contains("Products")) {
				break;
			}
		}

		driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(expectedProductName);
		driver.findElement(By.xpath("//input[@name='search']")).click();
		driver.findElement(By.xpath("//a[.='"+expectedProductName+"']")).click();

		Set<String> winIds2 = driver.getWindowHandles();

		for(String id:winIds2)
		{
			driver.switchTo().window(id);
			if(driver.getCurrentUrl().contains("Campaigns")) {
				break;
			}
		}

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		String actualCampaignName = driver.findElement(By.xpath("//span[@id='dtlview_Campaign Name']")).getText();
		String actualProductName = driver.findElement(By.xpath("//span[@id='dtlview_Product']/a")).getText();

		if(actualCampaignName.equals(expectedCampaignName) && actualProductName.equals(expectedProductName))
		{
			System.out.println("Campaign created with Product successfully---> TC is Pass");
		}
		else {
			System.out.println("Campaign is not created---> TC is Fail");
		}




	}
}
