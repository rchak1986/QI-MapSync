package com.qi.mapsync.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.qi.mapsync.common.utilities.CommonDriverUtilities;
import com.qi.mapsync.common.utilities.Utilities;

public class Register {
	WebDriver driver;
	Utilities util;
	CommonDriverUtilities driverUtil;
    public Register(WebDriver driver){ 
             this.driver=driver; 
             util= new Utilities();
             driverUtil = new CommonDriverUtilities(driver);
    }

    @FindBy(how=How.ID, using="profile_first_name") WebElement txtFName;
    @FindBy(how=How.ID, using="profile_last_name") WebElement txtLName;
    @FindBy(how=How.ID, using="profile_country") WebElement ddlCountry;
    @FindBy(how=How.ID, using="profile_address") WebElement txtAddress;
    @FindBy(how=How.ID, using="profile_contact_no") WebElement txtContact;
    @FindBy(how=How.XPATH, using="//input[@name='profile[gender]']") List<WebElement> rdoGender;
    @FindBy(how=How.ID, using="profile_dob") WebElement txtDOB;
    @FindBy(how=How.ID, using="profile_email") WebElement txtEmail;
    @FindBy(how=How.ID, using="user_name") WebElement txtUserName;
    @FindBy(how=How.ID, using="password") WebElement txtPassword;
    @FindBy(how=How.ID, using="identity[password_confirmation]") WebElement txtConfirmPassword;
    @FindBy(how=How.ID, using="profile_subscribe_to_newsletter") WebElement chkSubcribe;
    @FindBy(how=How.ID, using="chk_agree") WebElement chkAgree;
    @FindBy(how=How.NAME, using="commit") WebElement btnCreateProfile;
    @FindBy(how=How.XPATH, using="//*[@id='new_profile']/div[@class='error_signup']") WebElement msgErrorMsg;
    @FindBy(how=How.XPATH, using="//*[@class='header_item'][contains(@href,'history')]") WebElement lnkBack;
    
    public void clickOnBack(){
    	try{
	    	Actions action= new Actions(driver);
	    	action.click().perform();
	    	Thread.sleep(3000);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
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
    
    public boolean validateRegisterPageLoad() {
    	try{
    		driverUtil.waitForLoad(driver);
    		driverUtil.waitUntilObjDisplayed(driver, btnCreateProfile);
    		return true;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    public void setFName(String fName){
    	try{
    		txtFName.sendKeys(fName);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public void setLName(String lName){
    	try{
    		txtLName.sendKeys(lName);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public void selectCountry(String country){
    	try{
	    	Select cntry= new Select(ddlCountry);
	    	cntry.selectByVisibleText(country);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public void setAddress(String address){
    	try{
    		txtAddress.sendKeys(address);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public void setContact(String contact){
    	try{
    		txtContact.sendKeys(contact);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    public void setGender(String gender){
    	try{
	    	if (gender.toLowerCase().equals("m") || gender.toLowerCase().equals("male")){
	    		for(WebElement elem:rdoGender){
	    			if(elem.getAttribute("value").toLowerCase().equals("m")){
	    				elem.click();
	    				break;
	    			}
	    		}
	    	}else{
	    		for(WebElement elem:rdoGender){
	    			if(elem.getAttribute("value").toLowerCase().equals("f")){
	    				elem.click();
	    				break;
	    			}
	    		}
	    	}
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public void setDOB(String dob){
    	try{
    		txtDOB.sendKeys(dob);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public void setEmail(String email){
    	try{
    		txtEmail.sendKeys(email);
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
    public boolean validateUserName(){
    	try{
	    	if (txtUserName.getText().equals(txtEmail.getText())) return true;
	    	else return false;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    public void setConfirmPassword(String password){
    	try{
    		txtConfirmPassword.sendKeys(password);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public void checkAgreement(boolean state) {
    	try{
	    	Thread.sleep(2000);
	    	if (state && !chkAgree.isSelected()) chkAgree.sendKeys(Keys.ENTER);
	    	if(!state && chkAgree.isSelected()) chkAgree.sendKeys(Keys.ENTER);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public void checkSubscription(boolean state){
    	try{
	    	if (state && !chkSubcribe.isSelected()) chkSubcribe.sendKeys(Keys.ENTER);;
	    	if(!state && chkSubcribe.isSelected()) chkSubcribe.sendKeys(Keys.ENTER);;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public void clickOnCreateProfile(){
    	try{
	    	btnCreateProfile.click();
	    	Thread.sleep(2000);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public boolean validateProfileCreation(boolean expectedBehavior){
    	try{
	    	if (expectedBehavior){
	    		if(msgErrorMsg.isDisplayed()) return false;
	    		else return true;
	    	}else{
	    		if(msgErrorMsg.isDisplayed()) return true;
	    		else return false;
	    	}
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
}
