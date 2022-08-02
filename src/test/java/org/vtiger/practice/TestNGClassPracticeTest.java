package org.vtiger.practice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGClassPracticeTest {
	@AfterClass
	public void afterClass() {
		System.out.println("After Class -> class1");
	}
	
	@AfterMethod
	public void AfterMethod() {
		System.out.println("After Method -> class1");
	}
	@AfterSuite
	public void AfterSuite() {
		System.out.println("After Suite -> class1");
	}
	@AfterTest
	public void AfterTest() {
		System.out.println("After Test -> class1");
	}
	@BeforeClass
	public void beforeClass() {
		System.out.println("Before Class -> class1");
	}
	
	@BeforeMethod
	public void beforeMethod1() {
		System.out.println("Before Method -> class1");
	}
	
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Before Suite -> class1");
	}
	@BeforeTest
	public void beforeTest() {
		System.out.println("Before Test -> class1");
	}
	@Test
	public void test1() {
		System.out.println("Test -> class1");
	}
}
