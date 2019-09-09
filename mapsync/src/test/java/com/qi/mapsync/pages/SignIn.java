package com.qi.mapsync.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

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
    	if(expectedState){
    		if (lblErrorMsg.isDisplayed()) return true;
    		else return false;
    	}else{
    		if (lblErrorMsg.isDisplayed()) return false;
    		else return true;
    	}
    }
    
    public boolean validateCreateAccountLink(){
    	return lnkCreateAccount.isDisplayed();
    }
    
    public boolean validateForgotPasswordLink(){
    	return lnkForgotPassword.isDisplayed();
    }
    
    public void setUserName(String userName){
    	txtUsername.sendKeys(userName);
    }
    
    public void setPassword(String password){
    	txtPassword.sendKeys(password);
    }
    
    public void clickOnSignIn(){
    	btnSignin.click();
    }
    
    public boolean validateSignInPageLoad() throws Exception{
    	driverUtil.waitForLoad(driver);
    	return driverUtil.waitUntilObjDisplayed(driver, btnSignin);
    }
    
    public void reNavigate(){
    	String url = util.getPropertyValue("URL");
		if (url==null) url="http://www.mapsynq.com";
      	driver.navigate().to(url);	
    }
    public void pageRefresh(){
    	driver.navigate().refresh();
    	driverUtil.waitForLoad(driver);
    }
}
