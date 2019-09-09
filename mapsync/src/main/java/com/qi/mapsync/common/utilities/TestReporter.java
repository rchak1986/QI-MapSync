package com.qi.mapsync.common.utilities;

import java.io.*;

import org.openqa.selenium.*;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qi.mapsync.tests.*;

import org.testng.*;


public class TestReporter extends TestListenerAdapter {
  
    @Override
    public void onTestFailure(ITestResult result) {
    	Object currentClass = result.getInstance();
        WebDriver driver = ((TestBase) currentClass).getDriver();
        ExtentTest test = ((TestBase) currentClass).getETest();
    	CommonDriverUtilities driverUtil = new CommonDriverUtilities(driver);
        String methodName = result.getName();
        if(!result.isSuccess()){
            try {
                File destFile = driverUtil.getScreenshot(methodName);
                Reporter.log(System.lineSeparator()+"test Failed at: " + result.getTestClass().getName()+System.lineSeparator() + "<a href='"+ destFile.getAbsolutePath() + "'> <img src='"+ destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
                test.log(Status.FAIL, "test Failed at: " + result.getTestClass().getName()+System.lineSeparator() + "<a href='"+ destFile.getAbsolutePath() + "'> <img src='"+ destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public String getTestName(ITestResult result){
    	return result.getTestClass().getName();
    }
}
