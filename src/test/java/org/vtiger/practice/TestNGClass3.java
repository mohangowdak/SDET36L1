package org.vtiger.practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNGClass3 {
	@Test
	public void test1() {
		Reporter.log("Test1 - class3 "+ Thread.currentThread().getId(), true);
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
	
	@Test
	public void test2() {
		Reporter.log("Test2 - class3 "+ Thread.currentThread().getId(), true);
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
	
	@Test
	public void test3() {
		Reporter.log("Test3 - class3 "+ Thread.currentThread().getId(), true);
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
	
	@Test
	public void test4() {
		Reporter.log("Test4 - class3 "+ Thread.currentThread().getId(), true);
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}

}
