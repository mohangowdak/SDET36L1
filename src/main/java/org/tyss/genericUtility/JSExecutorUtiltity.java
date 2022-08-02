package org.tyss.genericUtility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class contains all the JS reusable Method/actions
 * @author MOHAN GOWDA
 *
 */
public final class JSExecutorUtiltity {
	private JavascriptExecutor js;
	/**
	 * Intiallize the JavaScript Executor
	 * @param driver
	 */
	public void intiallizeJSExecutor(WebDriver driver) {
		js=(JavascriptExecutor) driver;
	}
	
	/**
	 * NavigateApp by using JSExecutor
	 * @param url
	 */
	public void navigateApp(String url) {
		js.executeScript("window.location=arguments[0]",url);
	}
	
	/**
	 * This method is used to send the data to textfield using JSExecutor
	 * @param element
	 * @param data
	 */
	public void enterData(WebElement element, String data) {
		js.executeScript("arguments[0].value=arguments[1]", element, data);
	}
	
	/**
	 * This method is used to click on the element using JSExecutor
	 * @param element
	 */
	public void clickOnElement(WebElement element) {
		js.executeScript("arguments[0].click()", element);
	}
	
	/**
	 * This method is used to scroll till the end of page
	 */
	public void scrollTillEnd(String strategy) {
	
		String sign=strategy.equalsIgnoreCase("up") ? "-": "+";
		
		js.executeScript("window.scrollBy(0,"+sign+"document.body.scrollHeight)");
	}
	
	/**
	 * This method is used to scroll till some position
	 * @param y_position
	 */
	public void scrollTillSomePosition(int y_position,String strategy) {
		String sign=strategy.equalsIgnoreCase("up") ? "-" : "+";
		
		js.executeScript("window.scrollBy(0,"+sign+"arguments[0])", y_position);
	}
	
	
	/**
	 * This method is used to scroll till the element is visible
	 * @param element
	 */
	public void scrollTillElement(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView()", element);
	}
	
	/**
	 * This method is used to highlight the element
	 * @param element
	 */
	public void highlightElement(WebElement element) {
		js.executeScript("arguments[0].setAttribute('style', 'border:5px solid red;')", element);
	}
	
	
	

}
