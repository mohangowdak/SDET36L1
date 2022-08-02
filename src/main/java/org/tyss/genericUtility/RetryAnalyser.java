package org.tyss.genericUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer{
	int start=0;
	int maxLimit=3;
	@Override
	public boolean retry(ITestResult result) {
		
		if(start<maxLimit) {
			start++;
			return true;
		}
		return false;
	}
}
