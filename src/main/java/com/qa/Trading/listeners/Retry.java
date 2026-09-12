package com.qa.Trading.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	private int count = 0;
	private static int maxTry = 0;
	
	@Override
	public boolean retry(ITestResult result) {
		
		if(!result.isSuccess()) {
			if(count<maxTry) {
				count++;
				result.setStatus(result.FAILURE);
				return true;
			}
			else {
				result.setStatus(result.FAILURE);
			}
		}
		return false;
	}

}
