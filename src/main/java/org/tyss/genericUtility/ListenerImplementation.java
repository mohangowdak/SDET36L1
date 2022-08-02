package org.tyss.genericUtility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.qameta.allure.Attachment;

public class ListenerImplementation implements ITestListener {

	private ExtentReports report;
	private ExtentTest test;
	public static ExtentTest testlog;
	//@BeforeTest
	@Override
	public void onStart(ITestContext context) {
		ExtentSparkReporter spark=new ExtentSparkReporter("./extentreport-output/emailable-extentreport.html");
		spark.config().setDocumentTitle("Document Title");
		spark.config().setReportName("Report Name");
		spark.config().setTheme(Theme.DARK);

		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 10");
		report.setSystemInfo("Browser Name", "Chrome");
		report.setSystemInfo("Browser Version", "103.11.234");

	}
	//@BeforeMethod
	@Override
	public void onTestStart(ITestResult result) {
		test = report.createTest(result.getMethod().getMethodName());
		test.assignAuthor("Mohan");
		test.assignCategory("smoke");
		testlog=test;
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	
@Attachment("image/png")
	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getMethod().getMethodName()+" is Failed");
		test.fail(result.getThrowable());
	//	String pathOfScreenShot = UtilityObjectClass.getWebdriverUtility().takeScreenShotPage(result.getMethod().getMethodName(),UtilityObjectClass.getJavaUtility() , UtilityObjectClass.getDriver());
		String pathOfScreenShot = UtilityObjectClass.getWebdriverUtility().takeScreenShotInBase64(UtilityObjectClass.getDriver());
		test.addScreenCaptureFromBase64String(pathOfScreenShot, result.getMethod().getMethodName());
		//test.addScreenCaptureFromPath(pathOfScreenShot);
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.skip(result.getMethod().getMethodName()+" is Skipped");
		test.skip(result.getThrowable());
		
	}



	@Override
	public void onTestSuccess(ITestResult result) {
		test.pass(result.getMethod().getMethodName()+" is pass");
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}


}
