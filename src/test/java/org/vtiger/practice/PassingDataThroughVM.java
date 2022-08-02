package org.vtiger.practice;

import org.testng.annotations.Test;

public class PassingDataThroughVM {

	@Test
	public void data() {
		String br = System.getProperty("browser");
		System.out.println(br);
	}
	
}
