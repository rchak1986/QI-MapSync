package com.qi.mapsync.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Reporter;

import com.qi.mapsync.common.utilities.CommonDriverUtilities;
import com.qi.mapsync.common.utilities.Utilities;

public class Personal {
	WebDriver driver;
	Utilities util;
	CommonDriverUtilities driverUtil;
    public Personal(WebDriver driver){ 
             this.driver=driver; 
             util= new Utilities();
             driverUtil = new CommonDriverUtilities(driver);
    }

    @FindBy(how=How.XPATH, using="//input[@value='Sign in']") WebElement btnSignIn;
    @FindBy(how=How.XPATH, using="//input[@value='Register']") WebElement btnRegister;
    
    public boolean validateAndClickSignin(){
    	try{
	    	boolean flag=false;
	    	if (btnSignIn.isDisplayed()){
	    		btnSignIn.click();
	    		flag=true;
	    	}
	    	return flag;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    
    public boolean validateAndClickRegister(){
    	try{
	    	boolean flag=false;
	    	if (btnRegister.isDisplayed()){
	    		btnRegister.click();
	    		flag=true;
	    	}
	    	return flag;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
}
