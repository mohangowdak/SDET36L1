package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
   private WebDriver driver;
	@FindAll({@FindBy(xpath = "//input[@name='user_name']"), @FindBy(name = "user_nae")}) 
	private WebElement userNameTextField;
	
	@FindBy(xpath = "//input[@name='user_password']") 
	private WebElement passwordTextField;
	
	@FindBy(xpath = "//input[@id='submitButton']") 
	private WebElement loginBtn;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//business library
	/**
	 * This method is used  to login to the application
	 * @param userName
	 * @param password
	 */
	public CommonPage loginAction(String userName, String password) {
		userNameTextField.sendKeys(userName);
		passwordTextField.sendKeys(password);
		loginBtn.click();
		return new CommonPage(driver);
	}
	
	
	
	

}
