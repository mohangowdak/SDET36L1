package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignInformationPage {

	public CampaignInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//span[@id='dtlview_Campaign Name']") 
	private WebElement actualCampaignNameText;
	
	//business library
	/**
	 * This method is used to click on the create campaign button
	 * @return 
	 */
	public String getActualCampainName() {
		return actualCampaignNameText.getText();
	}
	
	

}
