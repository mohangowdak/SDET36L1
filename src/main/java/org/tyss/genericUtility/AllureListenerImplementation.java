package org.tyss.genericUtility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Allure;

public class AllureListenerImplementation implements ITestListener {

	//@BeforeTest
	@Override
	public void onStart(ITestContext context) {
	}
	//@BeforeMethod
	@Override
	public void onTestStart(ITestResult result) {
	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	
	@Override
	public void onTestFailure(ITestResult result) {
		UtilityObjectClass.getWebdriverUtility().takeScreenShotInBytes(UtilityObjectClass.getDriver());
	//UtilityObjectClass.getWebdriverUtility().takeScreenShotInBase64(UtilityObjectClass.getDriver());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}



	@Override
	public void onTestSuccess(ITestResult result) {
		Allure.step("TC Pass");
	}

	@Override
	public void onFinish(ITestContext context) {
	
	}


}
