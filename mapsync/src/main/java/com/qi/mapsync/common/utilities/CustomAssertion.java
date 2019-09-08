package com.qi.mapsync.common.utilities;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

public class CustomAssertion extends Assertion {
	WebDriver driver;
	public CustomAssertion(WebDriver driver){
		this.driver=driver;
	}
	@Override
	public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
		CommonDriverUtilities driverUtil = new CommonDriverUtilities(driver);
		try {
			File f = driverUtil.getScreenshot("AssertFailure_");
			Reporter.log("Test Failed: "+ex.getMessage()+System.lineSeparator() + "<a href='"+ f.getAbsolutePath() + "'> <img src='"+ f.getAbsolutePath() + "' height='100' width='100'/> <br></br></a>" + System.lineSeparator());
		}catch (Exception e) {
			 e.printStackTrace();
		}
	 }
 }
