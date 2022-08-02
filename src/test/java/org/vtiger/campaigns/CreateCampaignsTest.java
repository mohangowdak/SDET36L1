package org.vtiger.campaigns;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.tyss.genericUtility.BaseClass;

import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


public class CreateCampaignsTest extends BaseClass{

	@Test(groups = "sanity")
	public void createCampaignTest() throws Throwable {
		//Fetch the data from Excel File
		String expectedCampaignName = excelUtility.getDataFromExcel(2, 1,"Campaigns")+randomNumber;
		//login 
		String actualCampaignName =
				commonPage.clickCampaign(webdriverUtility)
				.clickOnCreateCampaignBtn()
				.createCampaign(expectedCampaignName)
				.getActualCampainName();
		expectedCampaignName="mohan";
		
		soft.assertTrue(expectedCampaignName.equals(actualCampaignName));
	}
}
