package com.qi.mapsync.common.utilities;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class CustomAssertion extends Assertion {
	WebDriver driver;
	ExtentTest test;
	public CustomAssertion(WebDriver driver, ExtentTest test){
		this.driver=driver;
		this.test=test;
	}
	@Override
	public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
		CommonDriverUtilities driverUtil = new CommonDriverUtilities(driver);
		try {
			File f = driverUtil.getScreenshot("AssertFailure_");
			Reporter.log("Test Failed: "+ex.getMessage()+System.lineSeparator() + "<a href='"+ f.getAbsolutePath() + "'> <img src='"+ f.getAbsolutePath() + "' height='100' width='100'/> <br></br></a>" + System.lineSeparator());
			test.log(Status.FAIL, "Test Failed: "+ex.getMessage()+System.lineSeparator() + "<a href='"+ f.getAbsolutePath() + "'> <img src='"+ f.getAbsolutePath() + "' height='100' width='100'/> <br></br></a>");
		}catch (Exception e) {
			 e.printStackTrace();
		}
	 }
	
	@Override
	public void onAssertSuccess(IAssert<?> assertCommand) {
		test.log(Status.PASS, assertCommand.toString());
	}
	
	@Override
	public void assertTrue(boolean condition, String message){		
		CommonDriverUtilities driverUtil = new CommonDriverUtilities(driver);
		File f = driverUtil.getScreenshot("AssertFailure_");
		try{
			Assert.assertEquals(condition,true,message);
			test.log(Status.PASS, message);
		}catch(Error err){
			test.log(Status.FAIL, "Test Failed: "+message+System.lineSeparator() + "<a href='"+ f.getAbsolutePath() + "'> <img src='"+ f.getAbsolutePath() + "' height='100' width='100'/> <br></br></a>");
		}
	}
 }
