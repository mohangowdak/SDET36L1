package org.vtiger.practice;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGClassPracticeTest2 {

	@Test
	public void test1() {
		SoftAssert soft=new SoftAssert();
		soft.assertTrue(3==1, "This assert method checking 3 is equal to 1");
		soft.assertTrue(1==2,"This assert method checking 2 is equal to 1");
		System.out.println("Test1 -> statement1");
		System.out.println("Test1 -> statement2");
		soft.assertAll();
	}
	@Test(groups = {"sanity", "regression"})
	public void test2() {
		System.out.println("Test2 -> statement1");
	
	}
	@Test(groups = "sanity")
	public void test3() {
		System.out.println("Test3 -> statement1	");
		
	}
}
