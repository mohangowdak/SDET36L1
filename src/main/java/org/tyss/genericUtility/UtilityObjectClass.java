package org.tyss.genericUtility;

import org.openqa.selenium.WebDriver;

public class UtilityObjectClass {
	
	private static ThreadLocal<WebDriver> driver=new ThreadLocal<>();
	private static ThreadLocal<WebDriverUtility> webdriverUtility=new ThreadLocal<>();
	private static ThreadLocal<ExcelUtility> excelutility=new ThreadLocal<>();
	private static ThreadLocal<FileUtility> fileUtility=new ThreadLocal<>();
	private static ThreadLocal<JavaUtility> javaUtility=new ThreadLocal<>();
	private static ThreadLocal<JSExecutorUtiltity> jsExecutorUtility=new ThreadLocal<>();

	public static WebDriver getDriver() {
		return driver.get();
	}
	public static void setDriver(WebDriver actDriver) {
		driver.set(actDriver);
	}
	public static WebDriverUtility getWebdriverUtility() {
		return webdriverUtility.get();
	}
	public static void setWebdriverUtility(WebDriverUtility actWebdriverUtility) {
		webdriverUtility.set(actWebdriverUtility);;
	}
	public static ExcelUtility getExcelutility() {
		return excelutility.get();
	}
	public static void setExcelutility(ExcelUtility actExcelutility) {
		excelutility.set(actExcelutility);;
	}
	public static FileUtility getFileUtility() {
		return fileUtility.get();
	}
	public static void setFileUtility(FileUtility actFileUtility) {
		fileUtility.set(actFileUtility);;
	}
	public static JavaUtility getJavaUtility() {
		return javaUtility.get();
	}
	public static void setJavaUtility(JavaUtility actJavaUtility) {
		javaUtility.set(actJavaUtility);;
	}
	public static JSExecutorUtiltity getJsExecutorUtility() {
		return jsExecutorUtility.get();
	}
	public static void setJsExecutorUtility(JSExecutorUtiltity actJsExecutorUtility) {
		jsExecutorUtility.set(actJsExecutorUtility);;
	}




}
