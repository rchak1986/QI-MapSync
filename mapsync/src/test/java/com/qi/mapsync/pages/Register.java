package com.qi.mapsync.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

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
    
    public void clickOnBack() throws InterruptedException{
    	Actions action= new Actions(driver);
    	action.click().perform();
    	Thread.sleep(3000);
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
    
    public boolean validateRegisterPageLoad() throws Exception{
    	try{
    		driverUtil.waitForLoad(driver);
    		driverUtil.waitUntilObjDisplayed(driver, btnCreateProfile);
    		return true;
    	}catch(Error e){
    		return false;
    	}
    }
    public void setFName(String fName){
    	txtFName.sendKeys(fName);
    }
    
    public void setLName(String lName){
    	txtLName.sendKeys(lName);
    }
    
    public void selectCountry(String country){
    	Select cntry= new Select(ddlCountry);
    	cntry.selectByVisibleText(country);
    }
    
    public void setAddress(String address){
    	txtAddress.sendKeys(address);
    }
    
    public void setContact(String contact){
    	txtContact.sendKeys(contact);
    }
    public void setGender(String gender){
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
    }
    
    public void setDOB(String dob){
    	txtDOB.sendKeys(dob);
    }
    
    public void setEmail(String email){
    	txtEmail.sendKeys(email);
    }
    
    public void setPassword(String password){
    	txtPassword.sendKeys(password);
    }
    public boolean validateUserName(){
    	if (txtUserName.getText().equals(txtEmail.getText())) return true;
    	else return false;
    }
    public void setConfirmPassword(String password){
    	txtConfirmPassword.sendKeys(password);
    }
    
    public void checkAgreement(boolean state) throws InterruptedException{
    	Thread.sleep(2000);
    	if (state && !chkAgree.isSelected()) chkAgree.sendKeys(Keys.ENTER);;
    	if(!state && chkAgree.isSelected()) chkAgree.sendKeys(Keys.ENTER);
    }
    
    public void checkSubscription(boolean state){
    	if (state && !chkSubcribe.isSelected()) chkSubcribe.click();
    	if(!state && chkSubcribe.isSelected()) chkSubcribe.click();
    }
    
    public void clickOnCreateProfile() throws InterruptedException{
    	btnCreateProfile.click();
    	Thread.sleep(2000);
    }
    
    public boolean validateProfileCreation(boolean expectedBehavior){
    	if (expectedBehavior){
    		if(msgErrorMsg.isDisplayed()) return false;
    		else return true;
    	}else{
    		if(msgErrorMsg.isDisplayed()) return true;
    		else return false;
    	}
    }
}
