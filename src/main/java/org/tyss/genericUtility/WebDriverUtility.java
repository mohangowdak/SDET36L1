package org.tyss.genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;

/**
 * This class is contains all the webdriver actions
 * 
 * @author MOHAN GOWDA
 *
 */
public final class WebDriverUtility {
	private WebDriver driver;
	private Actions act;
	private WebDriverWait wait;

	/**
	 * This method is used to setup the driver instance
	 * 
	 * @param browser
	 * @return
	 */
	public WebDriver setupDriver(String browser) {
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox": 
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "ie":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;
		default:
			System.out.println("You entered wrong Browser key in the Property file");
			break;
		}
		
		return driver;
	}

	/**
	 * This method is used to maximize the browser
	 */
	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}

	/**
	 * This method is used to wait the page by using implicit wait
	 * 
	 * @param longTimeout
	 */
	public void implicitWait(long longTimeout) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeout));
	}

	/**
	 * This method is used to navigate the application
	 * 
	 * @param url
	 */
	public void openApplication(String url) {
		driver.get(url);
	}

	/**
	 * This method is used to intiallize the Actions class
	 */
	public void intiallizeActions() {
		act = new Actions(driver);
	}

	/**
	 * This method is used to Mouse hover on Element
	 * 
	 * @param element
	 */
	public void mouseHoverOnElement(WebElement element) {
		act.moveToElement(element).perform();
	}

	/**
	 * This method is used to perform right click action on current mouse location
	 */
	public void rightClickAction() {
		act.contextClick().perform();
	}

	/**
	 * This method is used to perform right click action on particular webelement
	 * 
	 * @param element
	 */
	public void rightClickAction(WebElement element) {
		act.contextClick(element).perform();
	}

	/**
	 * This method is used to close particular Browser
	 */
	public void closeBrowser() {
		driver.quit();
	}

	/**
	 * This method is used to close particular Tab
	 */
	public void closeTab() {
		driver.close();
	}

	/**
	 * This method is used to switch frame based on index
	 * 
	 * @param index
	 */
	public void switchFrame(int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * This method is used to switch frame based on Webelement address
	 * 
	 * @param element
	 */
	public void switchFrame(WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * This method is used to switch frame based on name or id attribute
	 * 
	 * @param nameOrID
	 */
	public void switchFrame(String nameOrID) {
		driver.switchTo().frame(nameOrID);
	}

	/**
	 * This method is used to swich back from frame to parent web page
	 */
	public void switchBackFromFrame(String strategy) {
		switch (strategy.toLowerCase().trim()) {
		case "default":
			driver.switchTo().defaultContent();
			break;
		case "parent":
			driver.switchTo().parentFrame();
			break;
		default:
			System.out.println("please enter valid strategy either <default or parent>");
			break;
		}

	}

	/**
	 * This method is used to handle <select> tag Dropdown by using visible text
	 * 
	 * @param dropDownElement
	 * @param visibleText
	 */
	public void handleSelectDropdown(WebElement dropDownElement, String visibleText) {
		Select select = new Select(dropDownElement);
		select.selectByVisibleText(visibleText);
	}

	/**
	 * This method is used to handle <select> tag Dropdown by using value attribue
	 * of the <options> tag
	 * 
	 * @param value
	 * @param dropDownElement
	 */
	public void handleSelectDropdown(String value, WebElement dropDownElement) {
		Select select = new Select(dropDownElement);
		select.selectByValue(value);
	}

	/**
	 * This method is used to handle <select> tag Dropdown by using index
	 * @param dropDownElement
	 * @param indexNumber
	 */
	public void handleSelectDropdown(WebElement dropDownElement, int indexNumber) {
		Select select = new Select(dropDownElement);
		select.selectByIndex(indexNumber);
	}

	/**
	 * This method is used to take the current page screen Shot
	 * @param currentClass
	 * @param javaUtility
	 */
	public String takeScreenShotPage(String currentClass, JavaUtility javaUtility, WebDriver driver) {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File("./errorshots/"+currentClass+"_"+javaUtility.getCurrentDate("dd_MM_yyyy_HH_mm_sss")+".png");

		try {
			FileUtils.copyFile(src, dst);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dst.getAbsolutePath();
	}
	@Attachment(value = "abc", type = "image/png")
	public String takeScreenShotInBase64(WebDriver driver) {
		TakesScreenshot ts=(TakesScreenshot)driver;
		String path = ts.getScreenshotAs(OutputType.BASE64);
		return path;
	}
	@Attachment(value = "abc", type = "image/png")
	public byte[] takeScreenShotInBytes(WebDriver driver) {
		TakesScreenshot ts=(TakesScreenshot)driver;
		byte[] path = ts.getScreenshotAs(OutputType.BYTES);
		return path;
	}

	/**
	 * This method is used to take the particulat element screen Shot
	 * @param element
	 * @param currentClass
	 * @param javaUtility
	 */
	public void takeScreenShotElement(WebElement element, Object currentClass, JavaUtility javaUtility) {
		File src = element.getScreenshotAs(OutputType.FILE);
		File dst = new File("./elementScreenShot/"+currentClass.getClass().getSimpleName()+javaUtility.getCurrentDate("dd_MM_yyyy_HH_mm_sss"));
		try {
			FileUtils.copyFile(src, dst);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * This method will wait till the element is clickable(custom wait)
	 * @param totalDuration
	 * @param pollingTime
	 * @param element
	 */
	public void waitTillElementClickable(int totalDuration, long pollingTime, WebElement element) {
		int currentTime=0;
		while(currentTime<=totalDuration) {
			try {
				element.click();
				break;
			}
			catch(Exception e) {
				try {
					Thread.sleep(pollingTime);
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				currentTime++;
			}
		}
	}

	/**
	 * This method is used to intiallize the Explicit wait or WebdriverWait
	 * @param timeOuts
	 */
	public void intiallizeExplicitWait(long timeOuts, long pollingTime) {
		wait=new WebDriverWait(driver, Duration.ofSeconds(timeOuts));
		wait.pollingEvery(Duration.ofMillis(pollingTime));
		wait.ignoring(Exception.class);
	}

	/**
	 * This method is used to wait untill element is visible
	 */
	public void waitTillElementVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	/**
	 * This method is used to wait untill element is invisible
	 * @param element
	 */
	public void waitTillElementInvisible(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * This method is used to accept the js popup/confirmation popup/alert popup
	 */
	public void jsPopupaccept() {
		driver.switchTo().alert().accept();
	}

	/**
	 * This method is used to Dismiss the js popup/confirmation popup/alert popup
	 */
	public void jsPopupdecline() {
		driver.switchTo().alert().dismiss();
	}

	/**
	 * This method is used to send the data to the js popup/confirmation popup/alert popup
	 */
	public void jsPopupSendData(String data) {
		driver.switchTo().alert().sendKeys(data);
	}

	/**
	 * This method is used to fetch the data from the js popup/confirmation popup/alert popup
	 */
	public void jsPopupgetText() {
		driver.switchTo().alert().getText();
	}


	/**
	 * This method is used to switch the window
	 * @param partialText
	 * @param strategy
	 */
	public void switchWindow(String partialText, SwitchWindow strategy) {

		Set<String> winIds = driver.getWindowHandles();

		for(String id:winIds)
		{
			driver.switchTo().window(id);
			if(strategy.toString().equalsIgnoreCase("URL")) {
				if(driver.getCurrentUrl().contains(partialText)) {
					break;
				}
			}
			else if(strategy.toString().equalsIgnoreCase("TITLE")) {
				if(driver.getTitle().contains(partialText)) {
					break;
				}
			}
		}
	}


	public void switchWindowUsingIterator(String partialText) {

		Iterator<String> winIds = driver.getWindowHandles().iterator();

		while(winIds.hasNext())
		{
			String id=winIds.next();
			driver.switchTo().window(id);
			if(driver.getCurrentUrl().contains(partialText)) {
				break;
			}
		}

	}
}



