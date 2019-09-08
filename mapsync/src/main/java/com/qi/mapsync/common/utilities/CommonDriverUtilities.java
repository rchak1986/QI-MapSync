package com.qi.mapsync.common.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
    
    public File getScreenshot(String methodName){
    	File destFile=null;
    	Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/test-output/test-reports";
            destFile = new File((String) reportDirectory+"/failure_screenshots/"+methodName+"_"+formater.format(calendar.getTime())+".png");
            FileUtils.copyFile(scrFile, destFile);
            
            return destFile;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
	
	public int getRowCount(WebElement tbl) throws Exception
	{
		try{
			return tbl.findElements(By.xpath("tbody/tr")).size();
		}catch(Exception err){
			return 0;
		}
	}
	
	public int getColumnCount(WebElement tbl, int rowNumber) throws Exception
	{
		try{
			return tbl.findElements(By.xpath("tbody/tr["+Integer.toString(rowNumber)+"]/td")).size();
		}catch(Exception err){
			return 0;
		}
	}
	
	public String getCellData(WebElement tbl,int rownum,int colnum) throws Exception
	{
		try{
			return tbl.findElement(By.xpath("tbody/tr["+Integer.toString(rownum)+"]/td["+Integer.toString(colnum)+"]")).getText();
		}catch(Exception err){
			return null;
		}
	}
	
	public int getRowWithCellText(WebElement tbl, String srchString) throws Exception
	{
		String currString;
		int i=1,j=1,rownumber=0;
		int max_row=getRowCount(tbl);
		int max_col=0;
		try{
			for (i=1;i<=max_row;i++)
			{
				max_col=getColumnCount(tbl,i);
				for(j=1;j<=max_col;j++)
				{
					currString= getCellData(tbl,i,j);
					if (currString.equals(srchString))
					{
						rownumber=i;
						break;
					}
				}
			}
		}catch(Exception err){
		}
		return rownumber;
	}
}
