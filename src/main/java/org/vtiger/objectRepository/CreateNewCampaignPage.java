package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewCampaignPage {

	private WebDriver driver;
	public CreateNewCampaignPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//input[@name='campaignname']") 
	private WebElement campaignNameTextField;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']") 
	private WebElement saveBtn;
	
	
	//business library
	/**
	 * This method is used to click on the create campaign button
	 * @param expectedCampaignName
	 */
	public CampaignInformationPage createCampaign(String expectedCampaignName) {
		campaignNameTextField.sendKeys(expectedCampaignName);
		saveBtn.click();
		return new CampaignInformationPage(driver);
	}
	
	

}
