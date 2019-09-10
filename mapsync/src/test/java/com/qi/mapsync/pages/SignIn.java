package com.qi.mapsync.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Reporter;

import com.qi.mapsync.common.utilities.CommonDriverUtilities;
import com.qi.mapsync.common.utilities.Utilities;

public class SignIn {
	WebDriver driver;
	Utilities util;
	CommonDriverUtilities driverUtil;
    public SignIn(WebDriver driver){ 
             this.driver=driver; 
             util= new Utilities();
             driverUtil = new CommonDriverUtilities(driver);
    }

    @FindBy(how=How.ID, using="notice") WebElement lblErrorMsg;
    @FindBy(how=How.ID, using="name") WebElement txtUsername;
    @FindBy(how=How.ID, using="password") WebElement txtPassword;
    @FindBy(how=How.NAME, using="commit") WebElement btnSignin;
    @FindBy(how=How.XPATH, using="//*[@name='commit']/following-sibling::a") WebElement lnkForgotPassword;
    @FindBy(how=How.XPATH, using="//*[@class='text_create_account']/child::a") WebElement lnkCreateAccount;
    
    public boolean validateErrorMessage(boolean expectedState){
    	try{
	    	if(expectedState){
	    		if (lblErrorMsg.isDisplayed()) return true;
	    		else return false;
	    	}else{
	    		if (lblErrorMsg.isDisplayed()) return false;
	    		else return true;
	    	}
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    
    public boolean validateCreateAccountLink(){
    	try{
    		return lnkCreateAccount.isDisplayed();
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    
    public boolean validateForgotPasswordLink(){
    	try{
    		return lnkForgotPassword.isDisplayed();
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    
    public void setUserName(String userName){
    	try{ 
    		txtUsername.sendKeys(userName);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public void setPassword(String password){
    	try{
    		txtPassword.sendKeys(password);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public void clickOnSignIn(){
    	try{
    		btnSignin.click();
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public boolean validateSignInPageLoad(){
    	try{
	    	driverUtil.waitForLoad(driver);
	    	return driverUtil.waitUntilObjDisplayed(driver, btnSignin);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    
    public void reNavigate(){
    	try{
	    	String url = util.getPropertyValue("URL");
			if (url==null) url="http://www.mapsynq.com";
	      	driver.navigate().to(url);	
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    public void pageRefresh(){
    	try{
	    	driver.navigate().refresh();
	    	driverUtil.waitForLoad(driver);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
}
