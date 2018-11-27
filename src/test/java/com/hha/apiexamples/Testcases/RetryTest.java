package com.hha.apiexamples.Testcases;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer {

    int counter = 0;
    int retryCount = 2;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (counter < retryCount) {
            counter ++;
            return true;
        }
        return false;
    }
}
