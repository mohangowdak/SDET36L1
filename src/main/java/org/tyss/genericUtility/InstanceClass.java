package org.tyss.genericUtility;

import org.testng.asserts.SoftAssert;
import org.vtiger.objectRepository.CommonPage;
import org.vtiger.objectRepository.LoginPage;

public class InstanceClass {
	public WebDriverUtility webdriverUtility;
	public FileUtility fileUtility;
	public JavaUtility javaUtility;
	public ExcelUtility excelUtility;
	protected String browser;
	protected String userName;
	protected String password;
	protected String url;
	protected long longTimeout;
	protected LoginPage loginPage;
	public CommonPage commonPage;
	public int randomNumber;
	public SoftAssert soft;
}
