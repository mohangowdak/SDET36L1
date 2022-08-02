package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage {

	private WebDriver driver;
	public CampaignPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//img[@title='Create Campaign...']") 
	private WebElement createCampaignBtn;
	
	//business library
	/**
	 * This method is used to click on the create campaign button
	 */
	public CreateNewCampaignPage clickOnCreateCampaignBtn() {
		createCampaignBtn.click();
		return new CreateNewCampaignPage(driver);
	}
	
	

}
