package com.qi.mapsync.common.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonDriverUtilities {
	WebDriver driver;
	 
    public CommonDriverUtilities(WebDriver driver){ 
             this.driver=driver; 
    }

	public void waitForLoad(WebDriver driver) {
		Utilities util = new Utilities();
		String timeout = util.getPropertyValue("GlobalTimeOutInSecs");
		if (timeout==null)timeout="30";
		ExpectedCondition<Boolean> pageLoadCondition = new
				ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver driver) {
						return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
					}
				};
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(timeout));
		wait.until(pageLoadCondition);
	}
	
	public boolean waitUntilObjEnabled(WebDriver driver, WebElement wle) throws Exception
	{
		Utilities util = new Utilities();
		String timeout = util.getPropertyValue("GlobalTimeOutInSecs");
		if (timeout==null)timeout="30";
		try {
			WebDriverWait wdwait = new WebDriverWait(driver, Long.parseLong(timeout));
			wdwait.until(ExpectedConditions.elementToBeClickable(wle));
			return true;
		}catch(Exception err){
			return false;
		}
	}
	
	public boolean waitUntilObjDisplayed(WebDriver driver, WebElement wle) throws Exception
	{
		Utilities util = new Utilities();
		String timeout = util.getPropertyValue("GlobalTimeOutInSecs");
		if (timeout==null)timeout="30";
		try {
			WebDriverWait wdwait = new WebDriverWait(driver, Long.parseLong(timeout));
			wdwait.until(ExpectedConditions.visibilityOf(wle));
			return true;
		}catch(Exception err){
			return false;
		}
	}
	
	public boolean waitUntilObjClickable(WebDriver driver, WebElement wle) throws Exception
	{
		Utilities util = new Utilities();
		String timeout = util.getPropertyValue("GlobalTimeOutInSecs");
		if (timeout==null)timeout="30";
		try {
			WebDriverWait wdwait = new WebDriverWait(driver, Long.parseLong(timeout));
			wdwait.until(ExpectedConditions.elementToBeClickable(wle));
			return true;
		}catch(Exception err){
			return false;
		}
	}
}
