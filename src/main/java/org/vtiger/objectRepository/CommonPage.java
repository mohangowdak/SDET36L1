package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.tyss.genericUtility.WebDriverUtility;

public class CommonPage {
private WebDriver driver;
	public CommonPage(WebDriver driver) {
		this.driver=driver; 
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath="//a[.='More']") 
	private WebElement moreTab;
	@FindBy(xpath="//a[.='Organizations']") 
	private WebElement organizationsTab;
	@FindBy(xpath="//a[.='Campaigns']") 
	private WebElement campaignsTab;
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']") 
	private WebElement adminstratorIcon;
	@FindBy(xpath="//a[.='Sign Out']") 
	private WebElement signOutLink;

	//business library

	/**
	 *  This method is used to click on campaign tab in common page
	 * @param webdriverUtility
	 */
	public CampaignPage clickCampaign(WebDriverUtility webdriverUtility) {
		webdriverUtility.mouseHoverOnElement(moreTab);
		campaignsTab.click();
		Assert.fail();
		return new CampaignPage(driver);
	}

	/**
	 * This method is used to signout from the application
	 * @param webdriverUtility
	 */
	public void logoutAction(WebDriverUtility webdriverUtility) {
		 webdriverUtility.mouseHoverOnElement(adminstratorIcon);
		signOutLink.click();
	}
	
	/**
	 * This method is used to click on Organization tab
	 */
	public void clickOrganization() {
		organizationsTab.click();
	}


}
